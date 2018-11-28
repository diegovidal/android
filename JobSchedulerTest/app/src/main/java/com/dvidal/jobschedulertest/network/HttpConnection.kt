package com.dvidal.jobschedulertest.network

import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL


/**
 * @author diegovidal on 23/11/18.
 */
object HttpConnection {

    fun getSetDataWeb(uri: String): String {

        // open connection
        val url = URL(uri)
        val connection = url.openConnection() as HttpURLConnection

        val input = BufferedInputStream(connection.inputStream)
        return input.bufferedReader().use { it.readText() }
    }
}