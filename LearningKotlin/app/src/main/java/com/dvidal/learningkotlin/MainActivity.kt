package com.dvidal.learningkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val price = 29

        when(price) {
            0 -> println("For free today")
            !in 1..19 -> {
                println("Not on sale")
                println("Not on saledsdasdsd")
            }
            in 20..29 -> println("Normal price")
            10 + 20 -> println("Slightly overpriced")
            else -> println("Overpriced")
        }
    }

    fun permitEntrance(vararg ages: Int): Boolean {
        return ages.any {age -> age >= 18}
    }
}
