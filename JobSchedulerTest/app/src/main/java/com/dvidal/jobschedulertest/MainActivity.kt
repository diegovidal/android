package com.dvidal.jobschedulertest

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.dvidal.jobschedulertest.domain.MessageEB
import com.dvidal.jobschedulertest.service.JobSchedulerService
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {

        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    @Subscribe
    fun onEvent(m: MessageEB) {

        tv_job_scheduler_answer.text = m.message
    }

    fun onJobExecute(v: View) {

        val pb = PersistableBundle().also {
            it.putString("string", "anything")
        }

        val cp = ComponentName(this, JobSchedulerService::class.java)

        val jbi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            JobInfo.Builder(1, cp)
                .setBackoffCriteria(5000, JobInfo.BACKOFF_POLICY_LINEAR)
                .setExtras(pb)
                .setMinimumLatency(2000)
                .setOverrideDeadline(2000)
//                .setPeriodic(15 * 60 * 1000, 15 * 60 * 1000) // MINIMUM PERIOD ACCEPTED is 15 MINUTES
                .setPersisted(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(false)
                .setRequiresDeviceIdle(false)
                .build()
        } else {

            JobInfo.Builder(1, cp)
                .setBackoffCriteria(5000, JobInfo.BACKOFF_POLICY_LINEAR)
                .setExtras(pb)
                .setPersisted(true)
                .setPeriodic(2000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(false)
                .setRequiresDeviceIdle(false)
                .build()

        }

        val js = getSystemService(Context.JOB_SCHEDULER_SERVICE) as? JobScheduler
        js?.schedule(jbi)
    }

    fun onCancelAll(v: View) {

        val js = getSystemService(Context.JOB_SCHEDULER_SERVICE) as? JobScheduler
        js?.cancelAll()
    }
}
