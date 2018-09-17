package br.com.vp.puremvp.welcome

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.vp.puremvp.MyApplication
import br.com.vp.puremvp.R
import kotlinx.android.synthetic.main.activity_welcome.*
import javax.inject.Inject

class WelcomeActivity : AppCompatActivity(), WelcomeContract.View {

    @Inject
    lateinit var presenter: WelcomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Setup Presenter
        MyApplication.getWelcomeComponent().inject(this)

        btnSubmitClicked()
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    private fun btnSubmitClicked() {

        btnSubmit.setOnClickListener {

            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()

            if (firstName.isNotBlank()){

                presenter.submitName(firstName, lastName)
                presenter.loadWelcomeMessage()

            } else {

                Toast.makeText(applicationContext, "Please enter first name", Toast.LENGTH_LONG).show()
                etFirstName.requestFocus()
            }
        }
    }

    override fun showWelcomeMessage(message: String) {

        tvWelcomeMessage.text = message
    }
}
