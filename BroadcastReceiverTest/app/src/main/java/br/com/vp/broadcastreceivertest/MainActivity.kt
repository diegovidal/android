package br.com.vp.broadcastreceivertest

import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import br.com.vp.broadcastreceivertest.broadcasts.SecondBroadcastReceiver

class MainActivity : AppCompatActivity() {

    private val broadcastReceiver = SecondBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createIntentFilter()
    }

    fun updateScreen() {

        findViewById<View>(android.R.id.content).setBackgroundColor(Color.CYAN)
    }

    private fun createIntentFilter() {

        IntentFilter().also {
            with(it){
                addAction("BROADCAST_API")
                addCategory("BROADCAST_API_CATEGORY")
                registerReceiver(broadcastReceiver, it)
            }
        }
    }

    fun callBroadcastReceiver(v: View) {

        val label = (v as? Button)?.text.toString().toUpperCase()
        Intent(label).also {
            sendBroadcast(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}
