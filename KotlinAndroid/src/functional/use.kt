package functional

import java.io.FileReader

/**
 * Created by diegovidal on 06/03/2018.
 */

fun main(args: Array<String>) {

    FileReader("mayexist.txt").use {

        val str = it.readText()
        println(str)
//        it.close() // IS INFERRED
    }
}