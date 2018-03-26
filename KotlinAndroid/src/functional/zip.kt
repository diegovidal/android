package functional

/**
 * Created by diegovidal on 05/03/2018.
 */

fun main(args: Array<String>) {

    val list = listOf("Hi", "there", "Kotlin", "fans")
    val containsT = listOf(false, true, true, false)

    // Pair<String, Boolean>
    val zipped: List<Pair<String, Boolean>> = list.zip(containsT)
    val mapping = list.zip(list.map { it.contains("t") })

    println(zipped)
    println(mapping)
}