package oo

/**
 * Created by diegovidal on 13/03/2018.
 */

enum class Direction(degree: Double) {

    NORTH(0.0),
    EAST(90.0),
    SOUTH(180.0),
    WEST(270.0)
}

enum class Suits {

    HEARTS, SPADES, DIAMONDS, CLUBS
}

fun main(args: Array<String>) {

    val suit = Suits.SPADES

    val color = when(suit) {

        Suits.HEARTS, Suits.DIAMONDS -> "red"
        Suits.SPADES, Suits.CLUBS -> "black"
    }

    println(color)
}