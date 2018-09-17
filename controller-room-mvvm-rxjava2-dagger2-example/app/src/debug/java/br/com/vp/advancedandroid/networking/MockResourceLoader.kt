package br.com.vp.advancedandroid.networking

import android.content.Context
import android.support.annotation.Nullable
import timber.log.Timber
import java.io.IOException
import java.util.*
import java.util.Arrays.asList
import kotlin.collections.HashSet
import android.R.attr.path
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * @author diegovidal on 10/05/2018.
 */
object MockResourceLoader {

    fun getResponseString(context: Context, method: String, endpointParts: List<String>): String?{

        try {

            var currentPath = "mock"
            var mockList = HashSet(Arrays.asList(*context.assets.list(currentPath)))

            for (endpointPart in endpointParts){
                if (mockList.contains(endpointPart)){
                    currentPath = "$currentPath/$endpointPart"
                    mockList = HashSet(Arrays.asList(*context.assets.list(currentPath)))
                }
            }

            // At this stage, our mock list will be the list of files in the matching directory for
            // the endpoint parts.
            var finalPath: String? = null
            for (path in mockList){
                if (path.contains(method.toLowerCase())){
                    finalPath = "$currentPath/$path"
                    break
                }
            }

            finalPath?.let {
                return responseFromPath(context, finalPath)
            }

            return null

        } catch (e: IOException){
            Timber.e(e, "Error loading mock response from assets")
            return null
        }
    }

    private fun responseFromPath(context: Context, path: String): String?{

        try {
            context.assets.open(path).use { assetStream ->
                BufferedReader(InputStreamReader(assetStream)).use({ reader ->

                    return reader.buffered().use(BufferedReader::readText)
                })
            }
        } catch (e: IOException) {

            Timber.e(e, "Error reading mock response")
            throw IllegalArgumentException("Could not read from resource at: $path")
        }
    }
}