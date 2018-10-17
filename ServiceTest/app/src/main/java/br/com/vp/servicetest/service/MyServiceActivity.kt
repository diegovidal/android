package br.com.vp.servicetest.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import br.com.vp.servicetest.R
import br.com.vp.servicetest.bindservice.CountListener
import br.com.vp.servicetest.bindservice.MyBindService

class MyServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_service)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun startService(v: View) {

        Intent("MY_SERVICE").also {
            it.setPackage(packageName)
            startService(it)
        }
    }

    fun stopService(v: View) {

        Intent("MY_SERVICE").also {
            it.setPackage(packageName)
            stopService(it)
        }
    }
}
