package functional

/**
 * Created by diegovidal on 05/03/2018.
 */

fun main(args: Array<String>) {

//    val list = (1..1000).toList()
    val list = generateSequence(0) {
        println("Calculating ${it + 10}")
        it + 10
    }

    val first10 = list.take(10).toList()
    val first20 = list.take(20).toList()
//    val withoutFirst900 = list.drop(900).toList()

    println(first10)
    println(first20)
//    println(withoutFirst900)
}