package br.com.vp.servicetest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.vp.servicetest.bindservice.MyBindServiceActivity
import br.com.vp.servicetest.intentservice.MyIntentServiceActivity
import br.com.vp.servicetest.resultreceiver.MyResultReceiverActivity
import br.com.vp.servicetest.service.MyServiceActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToServiceExample(v: View){

        Intent(this, MyServiceActivity::class.java).also {
            startActivity(it)
        }
    }

    fun goToBindServiceExample(v: View){

        Intent(this, MyBindServiceActivity::class.java).also {
            startActivity(it)
        }
    }

    fun goToIntentServiceExample(v: View){

        Intent(this, MyIntentServiceActivity::class.java).also {
            startActivity(it)
        }
    }

    fun goToResultReceiverExample(v: View){

        Intent(this, MyResultReceiverActivity::class.java).also {
            startActivity(it)
        }
    }
}
