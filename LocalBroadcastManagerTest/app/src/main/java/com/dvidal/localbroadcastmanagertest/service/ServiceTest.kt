package com.dvidal.localbroadcastmanagertest.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager
import com.dvidal.localbroadcastmanagertest.broadcast.MyLocalBroadcastService
import com.dvidal.localbroadcastmanagertest.fragment.FragmentThread

/**
 * @author diegovidal on 23/10/18.
 */
class ServiceTest: Service() {

    private lateinit var broadcast: MyLocalBroadcastService

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        broadcast = MyLocalBroadcastService(this)
        IntentFilter(FILTER_KEY).also {
            LocalBroadcastManager.getInstance(this).registerReceiver(broadcast, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcast)
    }

    fun logMessage(message: String?) {

        val auxMsg = "$message FragmentThread: message ok. <br>"
        Intent(FragmentThread.FILTER_KEY).also {
            it.putExtra(FragmentThread.MESSAGE_KEY, auxMsg)
            LocalBroadcastManager.getInstance(this).sendBroadcast(it)
        }
    }

    companion object {

        const val FILTER_KEY = "SERVICE_TEST_FILTER_KEY"
        const val MESSAGE_KEY = "SERVICE_TEST_MESSAGE_KEY"
    }
}