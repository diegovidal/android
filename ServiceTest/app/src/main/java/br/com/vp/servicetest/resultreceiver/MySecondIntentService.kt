package br.com.vp.servicetest.resultreceiver

import android.app.IntentService
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.os.ResultReceiver
import android.util.Log
import br.com.vp.servicetest.bindservice.CountListener

/**
 * @author diegovidal on 14/09/2018.
 */
class MySecondIntentService: IntentService("MySecondIntentServiceThread"), CountListener {

    override var count = 0
    private var isActive = true
    private var isStopAll = false

    private lateinit var resultReceiver: ResultReceiver


    override fun onBind(p0: Intent?): IBinder? {

        return null
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
            } else {
                isStopAll = false
                resultReceiver = intent.getParcelableExtra(PUT_RECEIVER)
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(p0: Intent?) {

        while(!isStopAll && isActive && count < 50){
            Thread.sleep(1000)
            count++
            Log.d("MySecondIntentService", "Count is $count")

            Bundle().also {
                it.putInt(PUT_COUNT, count)
                resultReceiver.send(RESULT_CODE_RECEIVER, it)
            }
        }

        clearAttributes()
    }

    companion object {

        const val PUT_TURN_OFF = "PUT_TURN_OFF"
        const val PUT_RECEIVER = "PUT_RECEIVER"
        const val PUT_COUNT = "PUT_COUNT"

        const val RESULT_CODE_RECEIVER = 1001
    }
}