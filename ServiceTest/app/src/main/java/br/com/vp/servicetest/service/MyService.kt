package br.com.vp.servicetest.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @author diegovidal on 13/09/2018.
 */
class MyService: Service() {

    private val threadArrayList = arrayListOf<Worker>()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Worker(startId).also {

            Log.d("MyService", "onStartCommand()")
            it.start()
            threadArrayList.add(it)
        }

        return super.onStartCommand(intent, flags, startId) // -> mesma coisa que o START_STICK

        // START_STICK  -> Se por alguma razão o serviço tiver sido paralisado pelo SO ele irá retornar quando puder
        // START_NOT_STICK  -> Se por alguma razão o serviço tiver sido paralisado pelo SO ele não irá retornar quando puder
        // START_REDELIVER_INTENT  -> Se por alguma razão o serviço tiver sido paralisado pelo SO ele irá retornar quando puder e sua intent não será nula
    }

    override fun onDestroy() {
        super.onDestroy()
        threadArrayList.forEach {
            it.isActive = false
        }
    }

    private inner class Worker(val startId: Int): Thread() {

        var count = 0
        var isActive = true

        override fun run() {

            while (isActive && count < 1000) {

                Thread.sleep(1000)
                count++
                Log.d("MyService", "Count is $count")
            }

            stopSelf(startId)
        }
    }
}