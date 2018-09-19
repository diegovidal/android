package br.com.vp.densitytest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculateDPI()
    }

    private fun calculateDPI() {

        val scale = resources.displayMetrics.density
        val value = (1 * scale + 0.5f).toInt()

        Log.d("Density", "Density is $value")
        Log.d("Density", "Density is ${resources.displayMetrics.densityDpi}")

    }
}
