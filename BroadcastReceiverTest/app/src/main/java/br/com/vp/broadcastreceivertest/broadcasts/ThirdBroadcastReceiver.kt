package br.com.vp.broadcastreceivertest.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.vp.broadcastreceivertest.StubActivity

/**
 * @author diegovidal on 12/09/2018.
 */
class ThirdBroadcastReceiver: BroadcastReceiver() {

    // Can only spend 10 seconds
    override fun onReceive(context: Context?, p1: Intent?) {

        Log.d("MyBroadcast", "ThirdBroadcastReceiver")
        Intent(context, StubActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(it)
        }
    }
}