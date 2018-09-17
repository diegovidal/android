package br.com.vp.memberapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.vp.memberapp.models.MemberDataManager
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity: AppCompatActivity() {

    @Inject
    @field:Named("online")
    lateinit var memberDataManager: MemberDataManager

    @Inject
    @field:Named("dd-MMM-yyyy")
    lateinit var dateFormat: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuring Component
        MyApplication.getMemberAppComponent().inject(this)

        // SubmitClick
        submitClick()

        // Set Result
        tvResult.text = dateFormat
    }

    private fun submitClick() {

        btnSubmit.setOnClickListener {

            if( etMemberId.text.toString().isBlank()){
                Toast.makeText(applicationContext, "Member ID is empty.", Toast.LENGTH_SHORT).show()
            } else {

                val input = etMemberId.text.toString()
                val result = memberDataManager.checkMemberStatus(input)

                if(result.toLowerCase().contains("access denied")) {

                    Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                    tvResult.text = result
                } else {

                    val intent = Intent(this, WelcomeActivity::class.java)
                    intent.putExtra("result", result)
                    startActivity(intent)
                }

            }
        }
    }
}
