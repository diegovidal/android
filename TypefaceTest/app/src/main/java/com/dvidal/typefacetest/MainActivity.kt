package com.dvidal.typefacetest

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val font1 = Typeface.createFromAsset(assets, "fonts/Charmonman.ttf")
        val font2 = Typeface.createFromAsset(assets, "fonts/Cinzel-Bold.ttf")

        tv1.typeface = font1
        tv2.typeface = font2
    }
}
