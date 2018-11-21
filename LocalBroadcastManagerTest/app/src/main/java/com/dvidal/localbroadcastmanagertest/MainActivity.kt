package com.dvidal.localbroadcastmanagertest

import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.text.Html
import android.view.View
import com.dvidal.localbroadcastmanagertest.broadcast.MyLocalBroadcastMainActivity
import com.dvidal.localbroadcastmanagertest.domain.DomainClass
import com.dvidal.localbroadcastmanagertest.fragment.FragmentThread
import com.dvidal.localbroadcastmanagertest.service.ServiceTest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var domainClass: DomainClass
    private lateinit var broadcast: MyLocalBroadcastMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Intent(this, ServiceTest::class.java).also {
            startService(it)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(FragmentThread(), FragmentThread.KEY)
                .commit()
        }

        domainClass = DomainClass(this)

        broadcast = MyLocalBroadcastMainActivity(this)
        IntentFilter(FILTER_KEY).also {
            LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcast, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        domainClass.destroy()
        Intent(this, ServiceTest::class.java).also {
            stopService(it)
        }

        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(broadcast)
    }

    fun startCycleMessage(v: View) {

        Intent(ServiceTest.FILTER_KEY).also {

            it.putExtra(ServiceTest.MESSAGE_KEY, "ServiceTest: message ok. <br>")
            LocalBroadcastManager.getInstance(this).sendBroadcast(it)
        }
    }

    fun logMessage(message: String?) {

        tv_content.text = Html.fromHtml(message)
    }

    companion object {

        const val FILTER_KEY = "MAIN_ACTIVITY_FILTER_KEY"
        const val MESSAGE_KEY = "MAIN_ACTIVITY_MESSAGE_KEY"
    }
}
