package com.jt.mysalon.ui.auth

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.jt.mysalon.databinding.ActivityRegisterBinding
import com.jt.mysalon.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity :
    AppCompatActivity(),
    OnClickListener,
    OnFocusChangeListener,
    OnKeyListener,
    TextWatcher {

    val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.fullName.onFocusChangeListener = this
        binding.email.onFocusChangeListener = this
        binding.password.onFocusChangeListener = this
        binding.confirmPassword.onFocusChangeListener = this
        binding.confirmPassword.setOnKeyListener(this)
        binding.confirmPassword.addTextChangedListener(this)
        binding.registerBtn.setOnClickListener(this)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getIsLoading().observe(this) {
            binding.progressbar.isVisible = it
        }
        viewModel.getEmailIsUnique().observe(this) {
            if (validateEmail(shouldUpdateView = false)) {
                if (it) {
                    binding.emailTil.apply {
                        if (isErrorEnabled) isErrorEnabled = false
                        setStartIconDrawable(R.drawable.check_success)
                        setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                    }
                } else {
                    binding.emailTil.apply {
                        if (startIconDrawable != null) startIconDrawable = null
                        isErrorEnabled = true
                        error = "Email is already taken"
                    }
                }
            }
        }
        viewModel.getErrorMessage().observe(this) {
            val formErrorKeys = arrayOf("fullName", "email", "password")
            val message = StringBuilder()
            it.map { entry ->
                if (formErrorKeys.contains(entry.key)) {
                    when (entry.key) {
                        "fullName" -> {
                            binding.fullNameTil.apply {
                                isErrorEnabled = true
                                error = entry.value
                            }
                        }

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
                        }
                        .show()
                }
            }
        }

        viewModel.getRegisterIsSuccess().observe(this) {
            if (it == true) {
                val intent = LoginActivity.launcherIntent(this)
                startActivity(intent)
                Activity().finish()
            }
        }
    }

    private fun validateFullName(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.fullName.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Full name is required"
        }

        if (errorMessage != null) {
            binding.fullNameTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
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

    private fun validateConfirmPassword(shouldUpdateView: Boolean = true): Boolean {
        var errorMessage: String? = null
        val value: String = binding.confirmPassword.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Confirm Password is required"
        } else if (value.length < 6) {
            errorMessage = "Confirm Password must be 6 characters long"
        }

        if (errorMessage != null && shouldUpdateView) {
            binding.confirmPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePasswordAndConfirmPassword(shouldUpdateView: Boolean = true): Boolean {
        var errorMessage: String? = null
        val password: String = binding.password.text.toString()
        val confirmPassword: String = binding.confirmPassword.text.toString()
        if (password != confirmPassword) {
            errorMessage = "Confirm Password doesn`t with password"
        }

        if (errorMessage != null && shouldUpdateView) {
            binding.confirmPasswordTil.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    override fun onClick(view: View?) {
        view?.let {
            if (it.id == binding.registerBtn.id) {
                onSubmit()
            }
        }
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                binding.fullName.id -> {
                    if (hasFocus) {
                        if (binding.fullNameTil.isErrorEnabled) {
                            binding.fullNameTil.isErrorEnabled = false
                        }
                    } else {
                        validateFullName()
                    }
                }

                binding.email.id -> {
                    if (hasFocus) {
                        if (binding.emailTil.isErrorEnabled) {
                            binding.emailTil.isErrorEnabled = false
                        }
                    } else {
                        if (validateEmail()) {
                            viewModel.validateEmailIsUnique(
                                binding.email.text.toString()
                            )
                        }
                    }
                }

                binding.password.id -> {
                    if (hasFocus) {
                        if (binding.passwordTil.isErrorEnabled) {
                            binding.passwordTil.isErrorEnabled = false
                        }
                    } else {
                        if (validatePassword() &&
                            binding.confirmPassword.text!!.isEmpty() &&
                            validateConfirmPassword() &&
                            validatePasswordAndConfirmPassword()
                        ) {
                            if (binding.passwordTil.isErrorEnabled) {
                                binding.passwordTil.isErrorEnabled = false
                            }
                            binding.confirmPasswordTil.apply {
                                setStartIconDrawable(R.drawable.check_success)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }

                binding.confirmPassword.id -> {
                    if (hasFocus) {
                        if (binding.confirmPasswordTil.isErrorEnabled) {
                            binding.confirmPasswordTil.isErrorEnabled = false
                        }
                    } else {
                        if (validateConfirmPassword() && validatePassword() && validatePasswordAndConfirmPassword()) {
                            if (binding.passwordTil.isErrorEnabled) {
                                binding.passwordTil.isErrorEnabled = false
                            }

                            binding.confirmPasswordTil.apply {
                                setStartIconDrawable(R.drawable.check_success)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onKey(view: View?, keyCode: Int, keyEvent: KeyEvent?): Boolean {
        if (KeyEvent.KEYCODE_ENTER == keyCode && keyEvent!!.action == KeyEvent.ACTION_UP) {
            onSubmit()
        }
        return false
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (validatePassword(false) &&
            validateConfirmPassword(false) &&
            validatePasswordAndConfirmPassword(false)
        ) {
            binding.confirmPasswordTil.apply {
                if (isErrorEnabled) isErrorEnabled = false
                setStartIconDrawable(R.drawable.check_success)
                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
            }
        } else {
            if (binding.confirmPasswordTil.startIconDrawable != null) {
                binding.confirmPasswordTil.startIconDrawable = null
            }
        }
    }

    override fun afterTextChanged(p0: Editable?) {}

    private fun onSubmit() {
        if (validate()) {
            viewModel.requestRegistration(
                fullName = binding.fullName.text.toString(),
                email = binding.email.text.toString(),
                password = binding.confirmPassword.text.toString()
            )
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (!validateFullName()) isValid = false
        if (!validateEmail()) isValid = false
        if (!validatePassword()) isValid = false
        if (!validateConfirmPassword()) isValid = false
        if (isValid && !validatePasswordAndConfirmPassword()) isValid = false

        return isValid
    }

    companion object {
        fun launcherIntent(
            activity: Activity,
        ): Intent = Intent(activity, RegisterActivity::class.java)
    }
}