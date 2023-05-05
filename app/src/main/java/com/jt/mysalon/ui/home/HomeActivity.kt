package com.jt.mysalon.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.jt.mysalon.R
import com.jt.mysalon.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()

        startHomeFragment()
    }

    private fun startHomeFragment() {
        supportFragmentManager.beginTransaction().replace(
            binding.homeContainer.id,
            HomeFragment.newInstance(),
        ).commit()
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        fun launcherIntent(
            activity: Activity,
        ): Intent = Intent(activity, HomeActivity::class.java)
    }
}
