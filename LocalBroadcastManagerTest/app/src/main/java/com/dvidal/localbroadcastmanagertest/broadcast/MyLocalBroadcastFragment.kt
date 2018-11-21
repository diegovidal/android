package com.dvidal.localbroadcastmanagertest.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dvidal.localbroadcastmanagertest.fragment.FragmentThread
import com.dvidal.localbroadcastmanagertest.service.ServiceTest

/**
 * @author diegovidal on 23/10/18.
 */
class MyLocalBroadcastFragment(private val fragmentThread: FragmentThread)
    : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val message = intent?.getStringExtra(FragmentThread.MESSAGE_KEY)
        fragmentThread.logMessage(message)
    }
}