package oo

/**
 * Created by diegovidal on 08/03/2018.
 */

class Country(val name: String, val areaSqKm: Int) {

//    val name: String = name
//    val area: Int = areaSqKm

//    init {
//
//        this.name = name
//        this.area = areaSqKm
//    }

    constructor(name: String): this(name, 0) {

        println("Constructor called")
    }

    fun print() = "$name, $areaSqKm km^2 "
}

fun main(args: Array<String>) {

    val australia = Country("Australia", 7700000)

//    australia.name = "Something else" // can't because is val

    println(australia.name)
    println(australia.areaSqKm)

    val spain = Country("Spain")
    println(spain.name)
    println(spain.areaSqKm)
}