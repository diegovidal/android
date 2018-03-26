package basics

import java.io.IOException

/**
 * Created by diegovidal on 26/02/2018.
 */

fun main(args: Array<String>) {

    var input = try {

         getExternalInput()
    } catch (e: IOException) {
        e.printStackTrace()
        "Empty String"
    } finally {
        println("Finished trying to read external input.")
    }
    println(input)
}

fun getExternalInput(): String {

    throw IOException("Could not read external input.")
}