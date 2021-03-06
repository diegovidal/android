package com.dvidal.remote.utils

import java.util.*
import java.util.concurrent.ThreadLocalRandom

/**
 * @author diegovidal on 01/10/2018.
 */
object DataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }
}