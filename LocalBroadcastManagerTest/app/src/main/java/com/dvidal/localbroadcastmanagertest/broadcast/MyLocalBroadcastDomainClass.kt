package com.dvidal.localbroadcastmanagertest.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dvidal.localbroadcastmanagertest.domain.DomainClass
import com.dvidal.localbroadcastmanagertest.service.ServiceTest

/**
 * @author diegovidal on 23/10/18.
 */
class MyLocalBroadcastDomainClass(private val domainClass: DomainClass)
    : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val message = intent?.getStringExtra(DomainClass.MESSAGE_KEY)
        domainClass.logMessage(message)
    }
}