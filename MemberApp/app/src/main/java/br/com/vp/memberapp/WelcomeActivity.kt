package br.com.vp.memberapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.vp.memberapp.models.MemberDataManager
import br.com.vp.memberapp.models.MessageGenerator
import br.com.vp.memberapp.modules.MessagesModule
import kotlinx.android.synthetic.main.activity_welcome.*
import javax.inject.Inject
import javax.inject.Named

class WelcomeActivity : AppCompatActivity() {

    @Inject
    lateinit var messageGenerator: MessageGenerator

    @Inject
    @field:Named("local")
    lateinit var memberDataManager: MemberDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        MyApplication.getMemberAppComponent()
                .newWelcomeActivityComponent()
                .inject(this)

        tvResultAtWelcome.text = intent.getStringExtra("result")
        tvMessage.text = messageGenerator.welcomeMessage
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){

            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
