package com.dvidal.localbroadcastmanagertest.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dvidal.localbroadcastmanagertest.service.ServiceTest

/**
 * @author diegovidal on 23/10/18.
 */
class MyLocalBroadcastService(private val service: ServiceTest)
    : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val message = intent?.getStringExtra(ServiceTest.MESSAGE_KEY)
        service.logMessage(message)
    }
}