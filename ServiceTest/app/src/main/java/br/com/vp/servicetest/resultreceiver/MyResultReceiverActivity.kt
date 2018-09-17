package br.com.vp.servicetest.resultreceiver

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.view.View
import android.widget.Toast
import br.com.vp.servicetest.R
import br.com.vp.servicetest.resultreceiver.MySecondIntentService.Companion.PUT_COUNT
import br.com.vp.servicetest.resultreceiver.MySecondIntentService.Companion.PUT_RECEIVER
import br.com.vp.servicetest.resultreceiver.MySecondIntentService.Companion.PUT_TURN_OFF
import br.com.vp.servicetest.resultreceiver.MySecondIntentService.Companion.RESULT_CODE_RECEIVER

class MyResultReceiverActivity : AppCompatActivity() {

    private var resultReceiverListener: ResultReceiverListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_result_receiver)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun startService(v: View) {

        resultReceiverListener = null
        resultReceiverListener = ResultReceiverListener(null)

        Intent("MY_SECOND_INTENT_SERVICE").also {
            it.setPackage(packageName)
            it.putExtra(PUT_RECEIVER, resultReceiverListener)
            startService(it)
        }
    }

    fun stopService(v: View) {

        Intent("MY_SECOND_INTENT_SERVICE").also {
            it.setPackage(packageName)
            it.putExtra(PUT_TURN_OFF, 1)
            startService(it)
        }
    }


    inner class ResultReceiverListener(handler: Handler?): ResultReceiver(handler) {

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)

            if (resultCode == RESULT_CODE_RECEIVER){
                val count = resultData?.getInt(PUT_COUNT)

                runOnUiThread {
                    Toast.makeText(this@MyResultReceiverActivity, "Count is $count", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
