package oo

/**
 * Created by diegovidal on 08/03/2018.
 */


fun Int.isEven(): Boolean = (this % 2 == 0)

fun City.isLarge() = population > 1000000

fun main(args: Array<String>) {

    println(5.isEven())
    val naturals = listOf(2, 5, 11 ,3, 8 , 2)

    println(naturals.filter { it.isEven() })

    val austin = City()
    austin.name = "Austin"
    austin.population = 950000
    println(austin.isLarge())
}