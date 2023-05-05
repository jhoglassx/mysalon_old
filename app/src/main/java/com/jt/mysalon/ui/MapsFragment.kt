package com.jt.mysalon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jt.mysalon.R
import com.jt.mysalon.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Add a marker in Sydney and move the camera
        val location = LatLng(-19.9395284, -44.152267)
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("Salão do Jhoglas")
        )
        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(16f)
            .bearing(90f)
            .tilt(30f)
            .build()

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    companion object {
        @JvmStatic
        fun newInstance(
            establishmentId: String?,
        ) = MapsFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.IntentKeys.ESTABLISHMENT_ID, establishmentId)
            }
        }
    }
}