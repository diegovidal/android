package br.com.vp.broadcastreceivertest.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.vp.broadcastreceivertest.MainActivity

/**
 * @author diegovidal on 12/09/2018.
 */
class SecondBroadcastReceiver: BroadcastReceiver() {

    // Can only spend 10 seconds
    override fun onReceive(context: Context?, p1: Intent?) {

        Log.d("MyBroadcast", "SecondBroadcastReceiver")

        if (context is MainActivity){
            Log.d("MyBroadcast", "WOWWWW IS THE SAME")
            context.updateScreen()
        }
    }
}