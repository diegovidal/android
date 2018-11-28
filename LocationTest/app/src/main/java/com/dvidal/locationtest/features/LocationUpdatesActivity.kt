package com.dvidal.locationtest.features

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dvidal.locationtest.R
import com.dvidal.locationtest.formattedHtmlText
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.default_main.*
import java.text.DateFormat
import java.util.*

class LocationUpdatesActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationRequest = LocationRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.default_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest.interval = 5000
        locationRequest.fastestInterval = 5000 // HIGH PRIORITY THAN INTERVAL
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }

    override fun onPause() {

        fusedLocationClient.removeLocationUpdates(locationCallback)
        super.onPause()
    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun startLocationUpdates() {

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private val locationCallback = object : LocationCallback() {

        @SuppressLint("SetTextI18n")
        override fun onLocationResult(locationResult: LocationResult?) {

            Log.i("Script", "onLocationResult Called!!")
            val location = locationResult?.locations?.first()
            val text = "Location: " + location?.latitude + "<br />" +
                    "Longitude: " + location?.longitude + "<br />" +
                    "Bearing: " + location?.bearing + "<br />" +
                    "Altitude: " + location?.altitude + "<br />" +
                    "Speed: " + location?.speed + "<br />" +
                    "Provider: " + location?.provider + "<br />" +
                    "Accuracy: " + location?.accuracy + "<br />" +
                    "Date: " + DateFormat.getTimeInstance().format(Date()) + "<br />"

            tv_content.text = text.formattedHtmlText()
        }
    }
}
