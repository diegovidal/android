package com.dvidal.androidpermissiontest

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.location.LocationManager
import android.os.Environment
import android.location.Location
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View

import com.squareup.picasso.Picasso;
import kotlinx.android.synthetic.main.activity_main.*

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import me.drakeet.materialdialog.MaterialDialog;


class MainActivity : AppCompatActivity(), LocationListener {

    companion object {

        const val TAG = "LOG"
        const val REQUEST_PERMISSIONS_CODE = 128
    }

    private var mMaterialDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        Log.i(TAG, "test")
        when (requestCode) {
            REQUEST_PERMISSIONS_CODE -> for (i in permissions.indices) {

                if (permissions[i].equals(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        ignoreCase = true
                    ) && grantResults[i] == PackageManager.PERMISSION_GRANTED
                ) {

                    readMyCurrentCoordinates()
                } else if (permissions[i].equals(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        ignoreCase = true
                    ) && grantResults[i] == PackageManager.PERMISSION_GRANTED
                ) {

                    createDeleteFolder()
                } else if (permissions[i].equals(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        ignoreCase = true
                    ) && grantResults[i] == PackageManager.PERMISSION_GRANTED
                ) {

                    readFile(Environment.getExternalStorageDirectory().toString() + "/myFolder")
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    // LISTENERS
    fun callLoadImage(view: View) {
        Log.i(TAG, "callLoadImage()")
        Picasso.with(this).load("https://www.thiengo.com.br/img/system/logo/logo-thiengo-calopsita-70x70.png").into(iv_logo)
    }

    fun callWriteOnSDCard(view: View) {
        Log.i(TAG, "callWriteOnSDCard()")

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                callDialog(
                    "É preciso a permission WRITE_EXTERNAL_STORAGE para apresentação do content.",
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE
                )
            }
        } else {
            createDeleteFolder()
        }

    }

    fun callReadFromSDCard(view: View) {
        Log.i(TAG, "callReadFromSDCard()")

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                callDialog(
                    "É preciso a permission READ_EXTERNAL_STORAGE para apresentação do content.",
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_PERMISSIONS_CODE
                )
            }
        } else {
            readFile(Environment.getExternalStorageDirectory().toString() + "/myFolder")
        }
    }

    fun callAccessLocation(view: View) {
        Log.i(TAG, "callAccessLocation()")

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                callDialog(
                    "É preciso a permission ACCESS_FINE_LOCATION para apresentação dos eventos locais.",
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSIONS_CODE
                )
            }
        } else {
            readMyCurrentCoordinates()
        }
    }


    // FILE
    private fun createDeleteFolder() {
        val path = Environment.getExternalStorageDirectory().toString() + "/myFolder"
        val file = File(Environment.getExternalStorageDirectory().toString() + "/myFolder")

        if (file.exists()) {
            File(Environment.getExternalStorageDirectory().toString() + "/myFolder", "myFile.txt").delete()
            if (file.delete()) {
                Log.i(TAG, "Folder DELETED!")
            } else {
                Log.i(TAG, "Folder delete action was FAIL, take some permissions!")
            }
        } else {
            if (file.mkdir()) {
                createFile(path)
                Log.i(TAG, "Folder CREATED!")
            } else {
                Log.i(TAG, "Folder create action was FAIL, take some permissions!")
            }
        }
    }

    private fun createFile(path: String) {
        val file = File("$path/myFile.txt")
        var outputStream: OutputStream? = null

        try {
            outputStream = FileOutputStream(file)
            outputStream.write("Just a test".toByteArray())

            Log.i(TAG, "File CREATED!")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    private fun readFile(path: String) {
        val file = File(path, "myFile.txt")
        val text = readFileDirectlyAsText(file)

        Log.i(TAG, text)
    }

    private fun readFileDirectlyAsText(file: File): String
            = file.readText(Charsets.UTF_8)


    // GEOLOCATION
    @SuppressLint("MissingPermission")
    private fun readMyCurrentCoordinates() {

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        var location: Location? = null
        var latitude = 0.0
        var longitude = 0.0

        if (!isGPSEnabled && !isNetworkEnabled) {
            Log.i(TAG, "No geo resource able to be used.")
        } else {
            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0f, this)
                Log.d(TAG, "Network")
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (location != null) {
                    latitude = location.latitude
                    longitude = location.longitude
                }
            }

            if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0f, this)
                    Log.d(TAG, "GPS Enabled")
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (location != null) {
                        latitude = location.latitude
                        longitude = location.longitude
                    }
                }
            }
        }
        Log.i(TAG, "READ -- Lat: $latitude | Long: $longitude")
    }

    override fun onLocationChanged(location: Location) {
        Log.i(TAG, "Lat: " + location.latitude + " | Long: " + location.longitude)
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    // UTIL
    private fun callDialog(message: String, permissions: Array<String>) {
        mMaterialDialog = MaterialDialog(this)
            .setTitle("Permission")
            .setMessage(message)
            .setPositiveButton("Ok") {

                ActivityCompat.requestPermissions(this@MainActivity, permissions, REQUEST_PERMISSIONS_CODE)
                mMaterialDialog?.dismiss()

            }
            .setNegativeButton("Cancel") {

                mMaterialDialog?.dismiss()
            }

        mMaterialDialog?.show()
    }
}
