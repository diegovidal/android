package com.dvidal.locationtest.service

import android.app.IntentService
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.dvidal.locationtest.R
import com.dvidal.locationtest.domain.MessageEB
import com.dvidal.locationtest.features.AddressLocationActivity
import org.greenrobot.eventbus.EventBus
import java.io.IOException
import java.util.*

/**
 * @author diegovidal on 22/11/18.
 */
class LocationIntentService: IntentService("worker_thread") {

    override fun onHandleIntent(intent: Intent?) {

        val location = intent?.getParcelableExtra<Location>(AddressLocationActivity.PUT_LOCATION)
        val type = intent?.getIntExtra(AddressLocationActivity.PUT_TYPE, 1)
        val address = intent?.getStringExtra(AddressLocationActivity.PUT_ADDRESS)

        val geocoder = Geocoder(this, Locale.getDefault())

        var errorMessage = ""
        var resultAddress = ""

        var addresses: List<Address> = emptyList()

        try {

            addresses = if (type == 2) {
                geocoder.getFromLocation(location?.latitude!!, location.longitude, 3)
            } else {
                geocoder.getFromLocationName(address, 3)
            }

        } catch (ioException: IOException) {

            // Catch network or other I/O problems.
            errorMessage = "Network problem"
            Log.e("GeocoderService", errorMessage, ioException)
        } catch (illegalArgumentException: IllegalArgumentException) {

            // Catch invalid latitude or longitude values.
            errorMessage = "Illegal arguments"
            Log.e("GeocoderService", "$errorMessage. Latitude = $location.latitude , " +
                    "Longitude =  $location.longitude", illegalArgumentException)
        }

        // Handle case where no address was found.
        if (addresses.isEmpty()) {
            if (errorMessage.isEmpty()) {

                resultAddress = errorMessage
                Log.e("GeocoderService", errorMessage)
            }

        } else {
            val a = addresses.first()

            resultAddress = if (type == 2){

                // Fetch the address lines using getAddressLine,
                // join them, and send them to the thread.
                with(a) {
                    (0..maxAddressLineIndex).map { getAddressLine(it) }
                }.joinToString(separator = "\n")
            } else {

                "${a.latitude}\n" +
                        "${a.longitude}"
            }
        }

        // Send object by eventbus
        EventBus.getDefault().post(MessageEB(resultAddress))
    }
}