package com.dvidal.locationtest.features

import android.annotation.SuppressLint
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dvidal.locationtest.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.default_main.*

class LastLocationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.default_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        readLastLocation()
    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun readLastLocation() {

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                Log.i("Script", "Location is $location")
                tv_content.text = "${location?.latitude} | ${location?.longitude}"
            }
    }
}
