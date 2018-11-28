package com.dvidal.locationtest.features

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.dvidal.locationtest.R
import com.dvidal.locationtest.domain.MessageEB
import com.dvidal.locationtest.service.LocationIntentService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_address_location.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * @author diegovidal on 22/11/18.
 */

class AddressLocationActivity: AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var mLastLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_location)

        EventBus.getDefault().register(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        readLastLocation()
    }

    override fun onDestroy() {

        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    private fun callIntentService(type: Int, address: String?) {

        Intent(this, LocationIntentService::class.java).also {
            it.putExtra(PUT_TYPE, type)
            it.putExtra(PUT_ADDRESS, address)
            it.putExtra(PUT_LOCATION, mLastLocation)
            startService(it)
        }
    }

    @SuppressLint("MissingPermission")
    private fun readLastLocation() {

        fusedLocationProviderClient
            .lastLocation
            .addOnSuccessListener { location ->
                mLastLocation = location
                bt_name_to_coord.isEnabled = true
                bt_coord_to_name.isEnabled = true
            }
    }

    fun getLocationListener(v: View) {
        val type: Int
        var address: String? = null

        when(v.id) {

            R.id.bt_name_to_coord -> {
                type = 1
                address = et_address.text.toString()
            }
            else -> {
                type = 2
            }
        }

        callIntentService(type, address)
    }

    @Subscribe
    fun onEvent(msg: MessageEB) {

        runOnUiThread {
            tv_address_location.text = msg.message
        }
    }

    companion object {

        const val PUT_TYPE = "PUT_TYPE"
        const val PUT_ADDRESS= "PUT_ADDRESS"
        const val PUT_LOCATION = "PUT_LOCATION"
    }
}