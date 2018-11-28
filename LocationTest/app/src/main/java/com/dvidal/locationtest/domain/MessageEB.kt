package com.dvidal.locationtest.domain

import android.location.Location

/**
 * @author diegovidal on 22/11/18.
 */

class MessageEB(val message: String,
                val user: User? = null,
                val location: Location? = null,
                val name: String? = null)