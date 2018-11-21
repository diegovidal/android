package com.dvidal.localbroadcastmanagertest.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import com.dvidal.localbroadcastmanagertest.broadcast.MyLocalBroadcastFragment
import com.dvidal.localbroadcastmanagertest.domain.DomainClass

/**
 * @author diegovidal on 23/10/18.
 */
class FragmentThread: Fragment() {

    private lateinit var broadcast: MyLocalBroadcastFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        broadcast = MyLocalBroadcastFragment(this)
        IntentFilter(FILTER_KEY).also {
            LocalBroadcastManager.getInstance(requireContext())
                .registerReceiver(broadcast, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(requireContext())
            .unregisterReceiver(broadcast)
    }

    fun logMessage(message: String?) {

        val auxMsg = "$message DomainClass: message ok<br>"

        Thread{

            Intent(DomainClass.FILTER_KEY).also {
                it.putExtra(DomainClass.MESSAGE_KEY, auxMsg)
                LocalBroadcastManager.getInstance(requireContext())
                    .sendBroadcast(it)
            }

        }.start()
    }

    companion object {

        const val FILTER_KEY = "FRAGMENT_THREAD_FILTER_KEY"
        const val MESSAGE_KEY = "FRAGMENT_THREAD_MESSAGE_KEY"
        const val KEY = "FRAGMENT_THREAD_KEY"
    }
}