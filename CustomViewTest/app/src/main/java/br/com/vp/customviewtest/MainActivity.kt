package br.com.vp.customviewtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var myCustomView: MyCustomView
    private lateinit var myCustomSquare: MyCustomSquare

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myCustomView = MyCustomView(this)
        myCustomView.hint = "Put an email here."

        content.addView(myCustomView)

        myCustomSquare = MyCustomSquare(this)
        myCustomSquare.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        content.addView(myCustomSquare)
    }

    fun verifyEmail(v: View) {

        Toast.makeText(this, if (myCustomView.isEmail()) "Is Valid" else "Is Invalid", Toast.LENGTH_SHORT).show()
    }
}
