package functional

/**
 * Created by diegovidal on 06/03/2018.
 */

fun main(args: Array<String>) {

    val possiblePrimesAfter2 = generateSequence(3) { it + 2 }

    val primes = generateSequence (2 to possiblePrimesAfter2) {

        // Next prime number
        val p = it.second.first()

        // Filter out all elements divisble by p
        val possiblePrimesAfterP = it.second.filter { it % p != 0 }

        // Return the next element in the sequence
        p to possiblePrimesAfterP

    }.map { it.first }

    println(primes.take(10).toList())
}