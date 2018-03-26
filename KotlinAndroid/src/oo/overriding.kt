package oo

/**
 * Created by diegovidal on 13/03/2018.
 */

abstract class Vehicle(open val brand: String = "") {

    open fun drive() {

        println("Driving")
    }

    abstract fun honk()
}

class Sedan(override val brand: String = "BRAND"): Vehicle(), Drivable {

    override val model: Int
        get() = throw UnsupportedOperationException()

    override fun drive() {
        super<Drivable>.drive()
    }

    override fun honk() {
        println("Mööp")
    }
}

fun main(args: Array<String>) {

    val sedan = Sedan()
    println(sedan.drive())
    println(sedan.honk())
}
