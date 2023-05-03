package com.jt.mysalon.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnFocusChangeListener
import android.view.View.OnKeyListener
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.jt.mysalon.R
import com.jt.mysalon.databinding.ActivityLoginBinding
import com.jt.mysalon.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), OnClickListener, OnFocusChangeListener, OnKeyListener {
    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.loginWithGoogleBtn.setOnClickListener(this)
        binding.loginBtn.setOnClickListener(this)
        binding.registerBtn.setOnClickListener(this)
        binding.email.onFocusChangeListener = this
        binding.password.onFocusChangeListener = this
        binding.password.setOnKeyListener(this)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getLoginIsSuccess().observe(this) {
            if (it == true) {
                val intent = HomeActivity.launcherIntent(this)
                startActivity(intent)
                Activity().finish()
            }
        }

        viewModel.getIsLoading().observe(this) {
            binding.progressbar.isVisible = it
        }

        viewModel.getErrorMessage().observe(this) {
            val formErrorKeys = arrayOf("email", "password")
            val message = StringBuilder()
            it.map { entry ->
                if (formErrorKeys.contains(entry.key)) {
                    when (entry.key) {
                        "email" -> {
                            binding.emailTil.apply {
                                isErrorEnabled = true
                                error = entry.value
                            }
                        }

                        "password" -> {
                            binding.passwordTil.apply {
                                isErrorEnabled = true
                                error = entry.value
                            }
                        }
                    }
                } else {
                    message.append(entry.value).append("\n")
                }
                if (message.isNotEmpty()) {
                    AlertDialog.Builder(this)
                        .setIcon(R.drawable.info)
                        .setTitle("Information")
                        .setMessage(message)
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }.show()
                }
            }
        }
    }

    private fun validateEmail(shouldUpdateView: Boolean = true): Boolean {
        var errorMessage: String? = null
        val value: String = binding.email.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Email address is required"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            errorMessage = "Email address is invalid"
        }

        if (errorMessage != null && shouldUpdateView) {
            binding.emailTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePassword(shouldUpdateView: Boolean = true): Boolean {
        var errorMessage: String? = null
        val value: String = binding.password.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Password is required"
        } else if (value.length < 6) {
            errorMessage = "Password must be 6 characters long"
        }

        if (errorMessage != null && shouldUpdateView) {
            binding.passwordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validate(): Boolean {
        var isValid = true
        if (!validateEmail()) isValid = false
        if (!validatePassword()) isValid = false

        return isValid
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                binding.loginBtn.id -> {
                    submitForm()
                }
                binding.registerBtn.id -> {
                    val intent = RegisterActivity.launcherIntent(this)
                    startActivity(intent)
                    Activity().finish()
                }
            }
        }
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                binding.email.id -> {
                    if (hasFocus) {
                        if (binding.emailTil.isErrorEnabled) {
                            binding.emailTil.isErrorEnabled = false
                        }
                    } else {
                        validateEmail()
                    }
                }

                binding.password.id -> {
                    if (hasFocus) {
                        if (binding.passwordTil.isErrorEnabled) {
                            binding.passwordTil.isErrorEnabled = false
                        }
                    } else {
                        validatePassword()
                    }
                }
            }
        }
    }

    private fun submitForm() {
        if (validate()) {
            viewModel.requestLogin(
                email = binding.email.text.toString(),
                password = binding.password.text.toString()
            )
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        if (event == KeyEvent.KEYCODE_ENTER && keyEvent!!.action == KeyEvent.ACTION_UP) {
            submitForm()
        }
        return false
    }

    companion object {
        fun launcherIntent(
            activity: Activity,
        ): Intent = Intent(activity, LoginActivity::class.java)
    }
}