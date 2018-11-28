package com.dvidal.locationtest.handler

import android.content.Context
import android.content.Context.MODE_PRIVATE

/**
 * @author diegovidal on 27/11/18.
 */
object SharedPreferencesHandler {

    const val PREF_KEY = "PREF_KEY"
    const val LATITUDE_KEY = "LATITUDE_KEY"
    const val LONGITUDE_KEY = "LONGITUDE_KEY"
    const val ALTITUDE_KEY = "ALTITUDE_KEY"

    fun save(context: Context, key: String, value: String) {

        context.getSharedPreferences(PREF_KEY, MODE_PRIVATE)
            .edit()
            .putString(key, value)
            .apply()
    }

    fun fetch(context: Context, key: String, defaultValue: String): String {

        return context.getSharedPreferences(PREF_KEY, MODE_PRIVATE)
            .getString(key, defaultValue) ?: ""
    }
}