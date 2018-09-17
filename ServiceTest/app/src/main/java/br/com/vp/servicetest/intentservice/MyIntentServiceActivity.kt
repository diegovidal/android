package br.com.vp.servicetest.intentservice

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
import br.com.vp.servicetest.bindservice.CountListener
import br.com.vp.servicetest.intentservice.MyIntentService.Companion.PUT_TURN_OFF

class MyIntentServiceActivity : AppCompatActivity(), ServiceConnection {

    private lateinit var countListener: CountListener
    private lateinit var serviceConnection: ServiceConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_intent_service)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        serviceConnection = this

        Intent("MY_INTENT_SERVICE").also {
            it.setPackage(packageName)
            bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun startService(v: View) {

        Intent("MY_INTENT_SERVICE").also {
            it.setPackage(packageName)
            it.putExtra(PUT_TURN_OFF, 0)
            startService(it)
        }
    }

    fun stopService(v: View) {

        Intent("MY_INTENT_SERVICE").also {
            it.setPackage(packageName)
            it.putExtra(PUT_TURN_OFF, 1)
            startService(it)
        }

    }

    fun showCount(v: View) {

        Toast.makeText(this, "Count is ${countListener.count}", Toast.LENGTH_SHORT).show()
    }

    override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {

        if (service is MyIntentService.Controller){
            countListener = service.consumeCountListener()
        }
    }

    override fun onServiceDisconnected(p0: ComponentName?) {

    }
}
