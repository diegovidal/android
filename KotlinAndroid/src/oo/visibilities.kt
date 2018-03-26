package oo

/**
 * Created by diegovidal on 13/03/2018.
 */

// private - same as in Java
// protected - same as in Java
// internal - visible inside the same module
// public - same as in Java (default)

private val i = 42

open class Car(val brand: String, private val model: String) {

    protected fun tellMeYourModel() = model
}

class SpecificCar() : Car("", "") {

    fun a() = tellMeYourModel()
}

fun main(args: Array<String>) {

    val car = Car("BRAND", "MODEL")

    car.brand
//    car.tellMeYourModel() // YOU CAN'T
    println(i)

}