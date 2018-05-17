package br.com.vp.advancedandroid.settings

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author diegovidal on 09/05/2018.
 */

@Singleton
class DebugPreferences @Inject
    constructor(val context: Context){

    private val sharedPreferences = context.getSharedPreferences("debug_settings", Context.MODE_PRIVATE)

    fun useMockResponsesEnabled(): Boolean{

        return sharedPreferences.getBoolean(MOCK_RESPONSES_KEY, false)
    }

    fun setUseMockResponses(useMockResponses: Boolean){

        sharedPreferences.edit().putBoolean(MOCK_RESPONSES_KEY, useMockResponses).apply()
    }

    companion object {

        private const val MOCK_RESPONSES_KEY = "mock_response"
    }
}