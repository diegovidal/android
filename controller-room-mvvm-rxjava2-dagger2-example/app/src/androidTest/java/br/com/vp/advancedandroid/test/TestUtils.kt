package br.com.vp.advancedandroid.test

import br.com.vp.advancedandroid.model.ZonedDateTimeAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * @author diegovidal on 01/05/2018.
 */
class TestUtils
    @Inject constructor(private val moshi: Moshi) {

    fun <T> loadJson(path: String, type: Type): T? {
        try {
            val json = getFileString(path)

            @Suppress("UNCHECKED_CAST")
            return moshi.adapter<Any>(type).fromJson(json) as T?
        } catch (e: IOException) {
            throw IllegalArgumentException("Could not deserialize: $path into type: $type")
        }

    }

    fun <T> loadJson(path: String, clazz: Class<T>): T? {
        try {
            val json = getFileString(path)

            return moshi.adapter(clazz).fromJson(json)
        } catch (e: IOException) {
            throw IllegalArgumentException("Could not deserialize: $path into class: $clazz")
        }

    }

    private fun getFileString(path: String): String {
        try {
            val reader = BufferedReader(InputStreamReader(
                    javaClass.classLoader.getResourceAsStream(path)))
            val line = reader.buffered().use(BufferedReader::readText)
            return line
        } catch (e: IOException) {
            throw IllegalArgumentException("Could not read from resource at: $path")
        }

    }
}