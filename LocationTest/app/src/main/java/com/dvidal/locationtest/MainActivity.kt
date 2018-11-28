package com.dvidal.locationtest

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import com.dvidal.locationtest.features.AddressLocationActivity
import com.dvidal.locationtest.features.LastLocationActivity
import com.dvidal.locationtest.features.LocationUpdatesActivity
import com.dvidal.locationtest.features.TrackingActivity
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListView()
    }

    private fun initListView() {

        val data = listOf("LastLocationActivity", "LocationUpdatesActivity", "AddressLocationActivity", "TrackingActivity")
        lst_view.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        lst_view.onItemClickListener = this
    }

    private fun checkLocationPermissionIsPermissionGranted(): Boolean {

        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            true

        } else {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_PERMISSIONS_CODE)
            false
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        if (checkLocationPermissionIsPermissionGranted()) {
            when (position) {
                0 -> startActivity(Intent(this, LastLocationActivity::class.java))
                1 -> startActivity(Intent(this, LocationUpdatesActivity::class.java))
                2 -> startActivity(Intent(this, AddressLocationActivity::class.java))
                3 -> startActivity(Intent(this, TrackingActivity::class.java))
            }
        }
    }

    companion object {

        const val REQUEST_PERMISSIONS_CODE = 120
    }
}
