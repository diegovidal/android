package com.dvidal.localbroadcastmanagertest.domain

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.dvidal.localbroadcastmanagertest.MainActivity
import com.dvidal.localbroadcastmanagertest.broadcast.MyLocalBroadcastDomainClass

/**
 * @author diegovidal on 23/10/18.
 */
class DomainClass(private val context: Context) {

    private var broadcast: MyLocalBroadcastDomainClass
            = MyLocalBroadcastDomainClass(this)

    init {

        IntentFilter(FILTER_KEY).also {
            LocalBroadcastManager.getInstance(context)
                .registerReceiver(broadcast, it)
        }
    }

    fun logMessage(message: String?) {

        val auxMsg = "$message MainActivity: message ok<br>"

        Intent(MainActivity.FILTER_KEY).also {
            it.putExtra(MainActivity.MESSAGE_KEY, auxMsg)
            LocalBroadcastManager.getInstance(context)
                .sendBroadcast(it)
        }
    }

    fun destroy() {

        LocalBroadcastManager.getInstance(context)
            .unregisterReceiver(broadcast)
    }

    companion object {

        const val FILTER_KEY = "DOMAIN_CLASS_FILTER_KEY"
        const val MESSAGE_KEY = "DOMAIN_CLASS_MESSAGE_KEY"
    }
}