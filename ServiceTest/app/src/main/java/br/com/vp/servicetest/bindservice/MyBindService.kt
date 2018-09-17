package br.com.vp.servicetest.bindservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * @author diegovidal on 13/09/2018.
 */
class MyBindService: Service(), CountListener {

    override var count = 0
    private var isActive = true

    private val controller = Controller()

    override fun onBind(p0: Intent?): IBinder? {

        return controller
    }

    override fun onCreate() {
        super.onCreate()
//        updateThread()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        updateThread()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        isActive = false
    }

    private fun updateThread() {

        count = 0
        Thread{

            while (isActive && count < 100) {

                Thread.sleep(1000)
                count++
                Log.d("MyBindService", "Count is $count")
            }
        }.start()
    }

    inner class Controller: Binder() {

        fun consumeCountListener(): CountListener {
            return this@MyBindService
        }
    }
}