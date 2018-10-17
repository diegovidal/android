package com.dvidal.cache.utils

import com.dvidal.cache.model.Config

/**
 * @author diegovidal on 01/10/2018.
 */

object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }
}