package com.dvidal.alarmmanagertest.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.dvidal.alarmmanagertest.MainActivity
import com.dvidal.alarmmanagertest.R

/**
 * @author diegovidal on 28/11/18.
 */
class MyBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.i("Script", "-> Alarm Activated")
        context?.let {
            Intent(it, MainActivity::class.java).also {i ->
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                showNotification(it, i, "New Message", "My Title", "Message Description")
            }
        }
    }

    private fun showNotification(context: Context, intent: Intent?, ticker: String,
                                 title: String, desc: String) {

        val pendingIntent = PendingIntent.getActivity(context, 0,
            intent, FLAG_CANCEL_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setTicker(ticker)
            .setContentTitle(title)
            .setContentText(desc)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager?.createNotificationChannel(channel)
        }

        notificationManager?.notify(NOTIFY_ID, notificationBuilder.build())
    }

    companion object {

        const val CHANNEL_ID = "CHANNEL_ID"
        const val CHANNEL_NAME = "CHANNEL_NAME"
        const val NOTIFY_ID = 1028
    }
}