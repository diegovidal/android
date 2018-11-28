package com.dvidal.locationtest.service

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.location.Location
import android.os.AsyncTask
import android.os.SystemClock
import android.util.Log
import com.dvidal.locationtest.domain.MessageEB
import com.dvidal.locationtest.domain.User
import com.dvidal.locationtest.features.TrackingActivity
import com.dvidal.locationtest.formattedHtmlText
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.default_main.*
import org.greenrobot.eventbus.EventBus
import java.text.DateFormat
import java.util.*


/**
 * @author diegovidal on 23/11/18.
 */
class JobSchedulerService: JobService() {

    private var jobParams: JobParameters? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationRequest = LocationRequest()

    override fun onStartJob(params: JobParameters?): Boolean {

        jobParams = params
        updateLocationRequest()
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {

        Log.i("Script", "onStopJob()")
        return false
    }

    private fun updateLocationRequest() {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest.interval = 500
        locationRequest.fastestInterval = 500 // HIGH PRIORITY THAN INTERVAL
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        startLocationUpdates()
    }

    private fun finishJobScheduler() {

        fusedLocationClient.removeLocationUpdates(locationCallback)
        jobFinished(jobParams, false)
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private val locationCallback = object : LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult?) {

            locationResult?.locations?.first()?.let { location ->
                EventBus.getDefault().post(
                    MessageEB("JobSchedulerServiceMessage", User(100), location, TrackingActivity::class.java.name)
                )
                finishJobScheduler()
            }
        }
    }
}