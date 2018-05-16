package br.com.vp.advancedandroid.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZonedDateTime

/**
 * @author diegovidal on 23/04/2018.
 */

class ZonedDateTimeAdapter {

    @FromJson
    fun fromJson(json: String): ZonedDateTime{
        return ZonedDateTime.parse(json)
    }

    @ToJson
    fun toJson(value: ZonedDateTime?): String?{
        return value?.toString()
    }
}