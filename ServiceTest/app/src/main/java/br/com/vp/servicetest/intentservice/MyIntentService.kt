package br.com.vp.servicetest.intentservice

import android.app.IntentService
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.annotation.UiThread
import android.util.Log
import br.com.vp.servicetest.bindservice.CountListener

/**
 * @author diegovidal on 14/09/2018.
 */
class MyIntentService: IntentService("MyIntentServiceThread"), CountListener {

    override var count = 0
    private var isActive = true
    private var isStopAll = false

    private val controller = Controller()

    override fun onBind(p0: Intent?): IBinder? {

        return controller
    }

    private fun clearAttributes() {

        this.count = 0
        this.isActive = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.extras?.let {

            val turnOff = it.getInt(PUT_TURN_OFF)
            if (turnOff == 1){
                isStopAll = true
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(p0: Intent?) {

        while(!isStopAll && isActive && count < 50){
            Thread.sleep(1000)
            count++
            Log.d("MyIntentService", "Count is $count")
        }

        clearAttributes()
    }

    inner class Controller: Binder() {

        fun consumeCountListener(): CountListener {
            return this@MyIntentService
        }
    }

    companion object {

        const val PUT_TURN_OFF = "PUT_TURN_OFF"
    }
}