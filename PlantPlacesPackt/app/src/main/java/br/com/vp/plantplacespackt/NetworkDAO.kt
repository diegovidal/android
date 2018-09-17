package br.com.vp.plantplacespackt

import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL


class NetworkDAO {

    fun fetch(uri: String): String {

        // open the connection to this URL.
        val url = URL(uri)
        val urlConnection = url.openConnection() as HttpURLConnection

        val input = BufferedInputStream(urlConnection.inputStream)
        return input.bufferedReader().use { it.readText() }
    }
}
