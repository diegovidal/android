package br.com.vp.broadcastreceivertest.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @author diegovidal on 12/09/2018.
 */
class FirstBroadcastReceiver: BroadcastReceiver() {

    // Can only spend 10 seconds
    override fun onReceive(p0: Context?, p1: Intent?) {

        Log.d("MyBroadcast", "FirstBroadcastReceiver")
    }
}