package br.com.vp.memberapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var memberDataManager: MemberDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuring Component
        MyApplication.getMemberAppComponent().inject(this)

        btnSubmit.setOnClickListener {

            if( etMemberId.text.toString().isBlank()){
                Toast.makeText(applicationContext, "Member ID is empty.", Toast.LENGTH_SHORT).show()
            } else {

                val input = etMemberId.text.toString()
                val result = memberDataManager.checkMemberStatus(input)
                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
