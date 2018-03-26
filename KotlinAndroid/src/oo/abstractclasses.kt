package oo

/**
 * Created by diegovidal on 13/03/2018.
 */

abstract class Shape(val name: String){

    abstract fun area(): Double

    fun a() = 1
}

class Circle(name: String, val radius: Double) : Shape(name) {

    override fun area() = Math.PI * Math.pow(radius, 2.0)
}

fun main(args: Array<String>) {

//    val shape = Shape("Name"); YOU CAN'T

    val shape = Circle("Large Circle", 17.0)
    println(shape.area())
}