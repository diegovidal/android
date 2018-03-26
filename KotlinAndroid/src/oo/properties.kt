package oo

/**
 * Created by diegovidal on 08/03/2018.
 */

class City {

    var name: String = ""
        get() {

            return field + "1234"
        }
        set(value) {
            if (value.isNotBlank()) {
                field = value
            }
        }

    var population: Int = 0
}

fun main(args: Array<String>) {

    val berlin = City()
    berlin.name = "Berlin"
    berlin.name = "  "
    berlin.population = 3500000

    println(berlin.name)
    println(berlin.population)
}