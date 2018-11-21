package com.dvidal.sharedpreferencestest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    private val PREF_NAME = "MainActivityPreferences"

    private var count1 = 0
    private var count2 = 0

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener{ sharedPreferences, key ->

        Log.i("Script", "$key updated.")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("Script", "MainActivity")

        val sp1 = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        count1 = sp1.getInt("count_1", 0)
        Log.i("Script", "COUNT 1: $count1")

        sp1.registerOnSharedPreferenceChangeListener(listener)

        val sp2 = getPreferences(Context.MODE_PRIVATE)
        count2 = sp2.getInt("count_2", 0)
        Log.i("Script", "COUNT 2: $count2")
    }

    override fun onDestroy() {
        super.onDestroy()

        val sp1 = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sp1.unregisterOnSharedPreferenceChangeListener(listener)

//        sp1.edit()
//            .clear()
//            .apply()
    }

    override fun onStop() {
        super.onStop()

        val sp1 = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        count1 = sp1.getInt("count_1", 0)
        val editor1 = sp1.edit()
        editor1.putInt("count_1", count1 + 1)
        editor1.apply()

        val sp2 = getPreferences(Context.MODE_PRIVATE)
        count2 = sp2.getInt("count_2", 0)
        val editor2 = sp2.edit()
        editor2.putInt("count_2", count2 + 1)
        editor2.apply()
    }

    fun callSecondActivity(v: View) {

        Intent(this, SecondActivity::class.java).also {
            startActivity(it)
        }
    }
}
