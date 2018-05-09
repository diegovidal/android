package br.com.vp.advancedandroid.model

import com.squareup.moshi.Json
import org.threeten.bp.ZonedDateTime

/**
 * @author diegovidal on 23/04/2018.
 */

data class Repo(var id: Long,
                var name: String,
                var description: String,
                @Json(name = "owner") var owner: User,
                @Json(name = "stargazers_count") var stargazersCount: Long,
                @Json(name = "forks_count") var forksCount: Long,
                @Json(name = "contributors_url") var contributorsUrl: String,
                @Json(name = "created_at") var createdDate: ZonedDateTime,
                @Json(name = "updated_at") var updatedDate: ZonedDateTime) {


}