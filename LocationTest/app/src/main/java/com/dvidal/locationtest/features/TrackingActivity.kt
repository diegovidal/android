package com.dvidal.locationtest.features

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dvidal.locationtest.R
import com.dvidal.locationtest.domain.MessageEB
import com.dvidal.locationtest.handler.SharedPreferencesHandler
import com.dvidal.locationtest.handler.SharedPreferencesHandler.LATITUDE_KEY
import com.dvidal.locationtest.handler.SharedPreferencesHandler.LONGITUDE_KEY
import com.dvidal.locationtest.service.JobSchedulerService
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class TrackingActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private var mMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking)

        EventBus.getDefault().register(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.frag_map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onDestroy() {

        EventBus.getDefault().unregister(this)
        mMarker = null
        mMap = null
        super.onDestroy()
    }

    override fun onMapReady(map: GoogleMap?) {

        this.mMap = map
        map?.mapType = GoogleMap.MAP_TYPE_NORMAL

        val target = LatLng(
            SharedPreferencesHandler.fetch(this, LATITUDE_KEY, "-3.74109").toDouble(),
            SharedPreferencesHandler.fetch(this, LONGITUDE_KEY, "-38.49964").toDouble()
        )

        val cameraPosition = CameraPosition.builder()
            .target(target)
            .zoom(13f)
            .tilt(90f)
            .build()

        map?.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        addMarkerIntoMap(target, "My marker", "My snippet")
    }

    private fun addMarkerIntoMap(latLng: LatLng, title: String, snippet: String) {

        mMarker = mMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(snippet)
                .draggable(false)
        )
    }

    private fun updateMarkerPosition(latLng: LatLng) {

        mMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mMarker?.position = latLng
    }

    @Subscribe
    fun onEvent(message: MessageEB) {
        if (message.name?.equals(TrackingActivity::class.java.name, true) == true){
            updateMarkerPosition(LatLng(message.location?.latitude ?: 0.0, message.location?.longitude ?: 0.0))
        }
    }

    fun startTracking(v: View) {

        val cp = ComponentName(this, JobSchedulerService::class.java)

        val jbi = JobInfo.Builder(JOB_ID, cp)
                .setBackoffCriteria(2000, JobInfo.BACKOFF_POLICY_LINEAR)
                .setOverrideDeadline(1000)
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .build()

        val js = getSystemService(Context.JOB_SCHEDULER_SERVICE) as? JobScheduler
        js?.schedule(jbi)
    }

    fun stopTracking(v: View) {

        val js = getSystemService(Context.JOB_SCHEDULER_SERVICE) as? JobScheduler
        js?.cancelAll()
    }

    companion object {

        const val JOB_ID = 1201
    }
}
