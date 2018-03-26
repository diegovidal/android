package basics

/**
 * Created by diegovidal on 26/02/2018.
 */

fun main(args: Array<String>) {

    val together = concat(separator = " : ", texts = listOf("Kotlin", "Java", "Scala"))
    println(together)
}

fun concat(texts: List<String>, separator: String = ", ")  = texts.joinToString(separator)