package com.dvidal.localbroadcastmanagertest.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dvidal.localbroadcastmanagertest.MainActivity
import com.dvidal.localbroadcastmanagertest.fragment.FragmentThread
import com.dvidal.localbroadcastmanagertest.service.ServiceTest

/**
 * @author diegovidal on 23/10/18.
 */
class MyLocalBroadcastMainActivity(private val mainActivity: MainActivity)
    : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val message = intent?.getStringExtra(MainActivity.MESSAGE_KEY)
        mainActivity.logMessage(message)
    }
}