package oo

/**
 * Created by diegovidal on 14/03/2018.
 */

interface HouseFactory {

    fun createHouse(): House
}

class House(val numberOfrooms: Int, val price: Double) {

    companion object : HouseFactory {

        val HOUSES_FOR_SALE = 10

        fun getNormalHouse() = House(6, 199999.0)
        fun getLuxuryHouse() = House(42, 7000000.0)

        override fun createHouse(): House {
            return  getNormalHouse()
        }
    }
}

fun main(args: Array<String>) {

    val normalHouse = House.getNormalHouse()
    println(normalHouse.price)
}