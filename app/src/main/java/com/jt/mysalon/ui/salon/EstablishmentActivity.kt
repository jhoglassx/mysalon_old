package com.jt.mysalon.ui.salon

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.jt.mysalon.R
import com.jt.mysalon.databinding.ActivityEstablishmentBinding
import com.jt.mysalon.ui.MapsFragment
import com.jt.mysalon.utils.Constants.IntentKeys.ESTABLISHMENT_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EstablishmentActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityEstablishmentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_establishment)
        startHomeFragment()
        startMapsFragment()
    }

    private fun startHomeFragment() {
        this.supportFragmentManager.beginTransaction().replace(
            binding.salonContainer.id,
            EstablishmentFragment.newInstance(
                establishmentId = getEstablishmentId(),
            )
        ).addToBackStack(null).commit()
    }

    private fun startMapsFragment() {
        this.supportFragmentManager.beginTransaction().replace(
            binding.containerMaps.id,
            MapsFragment.newInstance(
                establishmentId = getEstablishmentId(),
            )
        ).addToBackStack(null).commit()
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
