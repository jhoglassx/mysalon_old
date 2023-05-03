package com.jt.mysalon.ui.salon

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jt.mysalon.R
import com.jt.mysalon.databinding.ActivitySalonBinding
import com.jt.mysalon.utils.Constants.IntentKeys.ESTABLISHMENT_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EstablishmentActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySalonBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salon)
        startHomeFragment()
    }

    private fun startHomeFragment() {
        supportFragmentManager.beginTransaction().replace(
            binding.salonContainer.id,
            EstablishmentFragment.newInstance(
                establishmentId = getEstablishmentId(),
            ),
        ).commit()
    }

    private fun getEstablishmentId() = intent.getStringExtra(ESTABLISHMENT_ID)

    companion object {
        fun launcherIntent(
            activity: Activity,
            establishmentId: String,
        ): Intent = Intent(activity, EstablishmentActivity::class.java).apply {
            putExtra(ESTABLISHMENT_ID, establishmentId)
        }
    }
}
