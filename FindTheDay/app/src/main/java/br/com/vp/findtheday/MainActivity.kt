package br.com.vp.findtheday

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DayFragment.OnFragmentInteractionListener {

    private var input = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnView.setOnClickListener {

            if (etIndex.text.toString() != "") {

                input = Integer.parseInt(etIndex.text.toString())

                val fragmentManager = supportFragmentManager
                val dayFragment = DayFragment.newInstance(input)
                dayFragment.show(fragmentManager, "Sample Fragment")
            }
        }
    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
