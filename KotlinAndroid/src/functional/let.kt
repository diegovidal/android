package functional

import java.io.File

/**
 * Created by diegovidal on 06/03/2018.
 */

fun main(args: Array<String>) {

    // let()

    // Scoping
    File("example.txt").bufferedReader().let {

        if (it.ready()){
            println(it.readLine())
        }
    }
    // reader should not be visible

    // Working with nullables
//    val str: String? = "Kotlin for Android"

//    if (str?.isNotEmpty()){  COMPILE ERROR, NEED !!
//        str?.toLowerCase()
//    }

    // BEST WAY
    val str: String? = "Kotlin for Android"
    str?.let {

        if (str.isNotEmpty()){
            str.toLowerCase()
        }
    }
}