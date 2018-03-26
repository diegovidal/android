package oo

/**
 * Created by diegovidal on 08/03/2018.
 */

// Generates hashCode(), equals(), toString(), copy(), destructuring operator
data class Address(val street: String, val number: Int,
              val postCode: String, val city: String);

fun main(args: Array<String>) {

    val residence = Address("Main Street", 42, "1234", "New York")
    val residence2 = Address("Main Street", 42, "1234", "New York")

    // toString()
    println(residence)

    // Reference Equality
    println(residence === residence2)

    // Structure Equality, equals()
    println(residence == residence2)

    // copy()
    val neighbor = residence.copy(number = 43)
    println(neighbor)

    // Destructuring operator
    residence.component1()

    val (street, number, postCode, city) = residence
    println("$street $number, $postCode $city")
}