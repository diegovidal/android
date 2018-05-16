package br.com.vp.advancedandroid.networking

import android.content.Context
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Named

/**
 * @author diegovidal on 10/05/2018.
 */

class MockResponseFactory @Inject
        constructor(val context: Context,
                    @Named("base_url") baseUrl: String){

    private val startIndex = baseUrl.length

    fun getMockResponse(request: Request): String? {

        val endpointParts = getEndPoint(request).split("/")
        return MockResourceLoader.getResponseString(context, request.method(), endpointParts)
    }

    private fun getEndPoint(request: Request): String{

        val url = request.url().toString()
        val queryParamStart = url.indexOf("?")
        return if (queryParamStart == -1) url.substring(startIndex) else url.substring(startIndex, queryParamStart)
    }
}