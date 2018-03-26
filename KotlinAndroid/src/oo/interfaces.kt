package oo

/**
 * Created by diegovidal on 13/03/2018.
 */

interface Drivable {

    val model: Int

    fun drive() {

        println("Driving (interface)")
    }
}

class Bicycle : Drivable {

    override val model: Int
        get() = throw UnsupportedOperationException()

    override fun drive() {
        println("Driving bicycle")
    }
}

class Boat : Drivable {

    override val model: Int
        get() = throw UnsupportedOperationException()

    override fun drive() = println("Driving boat")
}

fun main(args: Array<String>) {

    val drivable: Drivable = Bicycle()
    drivable.drive()
}