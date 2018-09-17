package br.com.vp.intentfiltertest

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun triggerFirstIntentScreenAction(v: View){

        Intent("ACTION_SCREEN").also {
            startActivity(it)
        }
    }

    fun triggerSecondIntentScreenAction(v: View){

        Intent("ACTION_SCREEN").also {

            it.addCategory("CATEGORY_SECOND_SCREEN")
            startActivity(it)
        }
    }

    fun triggerBrowser(v: View){

        Intent(Intent.ACTION_VIEW).also {

            it.data = Uri.parse("http://www.google.com")
            startActivity(it)
        }
    }
}
