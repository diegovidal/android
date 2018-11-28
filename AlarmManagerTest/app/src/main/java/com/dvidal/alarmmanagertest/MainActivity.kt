package com.dvidal.alarmmanagertest

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configAlarmManager()
    }

    private fun configAlarmManager() {

        val intent = Intent("BROADCAST_TRIGGER")
        intent.setPackage(packageName)

        val isInactive = (PendingIntent.getBroadcast(this, 0, intent,
            PendingIntent.FLAG_NO_CREATE)) == null

        if (isInactive) {

            Log.i("Script", "New Alarm")
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.SECOND, 3)

            val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
//            alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 60000, pendingIntent)
        } else {
            Log.i("Script", "Alarm Already Created")
        }
    }

    override fun onDestroy() {

        destroyAlarm()
        super.onDestroy()
    }

    private fun destroyAlarm() {

        val intent = Intent("BROADCAST_TRIGGER")
        intent.setPackage(packageName)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        alarmManager?.cancel(pendingIntent)
    }
}
