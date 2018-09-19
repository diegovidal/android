package br.com.vp.batterytest

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @author diegovidal on 18/09/2018.
 */
class BatteryBroadcastReceiver: BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent?) {

        val level = intent?.getIntExtra("level", 0)
        Log.d("MyLog", "Battery is $level%")
    }
}
