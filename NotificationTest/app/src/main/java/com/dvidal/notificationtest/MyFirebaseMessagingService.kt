package com.dvidal.notificationtest

import com.google.firebase.messaging.FirebaseMessagingService

/**
 * @author diegovidal on 11/12/18.
 */

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(p0: String?) {
        super.onNewToken(p0)
    }
}