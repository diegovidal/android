package com.dvidal.jobschedulertest.service

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.os.AsyncTask
import android.os.Build
import android.os.SystemClock
import android.util.Log
import com.dvidal.jobschedulertest.domain.MessageEB
import com.dvidal.jobschedulertest.network.HttpConnection
import org.greenrobot.eventbus.EventBus


/**
 * @author diegovidal on 23/11/18.
 */
class JobSchedulerService: JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {

        Log.i("Script", "onStartJob(${params?.extras?.getString("string")})")
        MyAsyncTask(this).execute(params)

        return true
        // true -> executando em background (dentro de uma outra thread), false -> encerrado
    }

    override fun onStopJob(params: JobParameters?): Boolean {

        Log.i("Script", "onStopJob(${params?.extras})")

        return true
        // true -> usa backoffCriteria, false -> executa o onStartJob instantaneamente
    }

    // INNER CLASS
    companion object {

        @SuppressLint("StaticFieldLeak")
        internal class MyAsyncTask(private val job: JobSchedulerService): AsyncTask<JobParameters?, Void, String>() {

            override fun doInBackground(vararg params: JobParameters?): String {
                Log.i("Script", "doInBackground()")

                SystemClock.sleep(5000) // -> force onStopJob method (tira do wifi pra testar)

                val answer = HttpConnection
                    .getSetDataWeb("http://www.villopim.com.br/android/ExampleJobScheduler/get-random.php")

                job.jobFinished(params.first(), true)
                // true -> usa backoffCriteria, false -> usa periodic

                return answer
            }

            override fun onPostExecute(result: String?) {

                Log.i("Script", "onPostExecute()")
                EventBus.getDefault().post(MessageEB(result ?: ""))
            }
        }
    }
}