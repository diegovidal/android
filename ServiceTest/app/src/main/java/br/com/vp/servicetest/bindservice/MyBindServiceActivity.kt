package br.com.vp.servicetest.bindservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import br.com.vp.servicetest.R

class MyBindServiceActivity : AppCompatActivity(), ServiceConnection {

    private lateinit var countListener: CountListener
    private lateinit var serviceConnection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bind_service)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        serviceConnection = this

        Intent("MY_BIND_SERVICE").also {
            it.setPackage(packageName)
            bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun startService(v: View) {

        Intent("MY_BIND_SERVICE").also {
            it.setPackage(packageName)
            startService(it)
        }

    }

    fun stopService(v: View) {

        Intent("MY_BIND_SERVICE").also {
            it.setPackage(packageName)
            stopService(it)
            unbindService(serviceConnection)
        }
    }

    fun showCount(v: View) {

        Toast.makeText(this, "Count is ${countListener.count}", Toast.LENGTH_SHORT).show()
    }

    override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {

        if (service is MyBindService.Controller){
            countListener = service.consumeCountListener()
        }
    }

    override fun onServiceDisconnected(p0: ComponentName?) {

    }
}
