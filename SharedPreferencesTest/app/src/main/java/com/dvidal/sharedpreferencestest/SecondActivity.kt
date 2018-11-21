package com.dvidal.sharedpreferencestest

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {

    private val PREF_NAME = "MainActivityPreferences"

    private var count1 = 0
    private var count2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.i("Script", "SecondActivity")

        val sp1 = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        count1 = sp1.getInt("count_1", 0)
        Log.i("Script", "COUNT 1: $count1")

        val sp2 = getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
        count2 = sp2.getInt("count_2", 0)
        Log.i("Script", "COUNT 2: $count2")
    }
}
