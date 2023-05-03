package com.jt.mysalon.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jt.mysalon.databinding.ActivityHomeBinding
import com.jt.mysalon.ui.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        startHomeFragment()
    }

    private fun startHomeFragment() {
        supportFragmentManager.beginTransaction().replace(
            binding.homeContainer.id,
            HomeFragment.newInstance(),
        ).commit()
    }

    companion object {
        fun launcherIntent(
            activity: Activity,
        ): Intent = Intent(activity, HomeActivity::class.java)
    }
}
