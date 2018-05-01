package br.com.vp.advancedandroid.data

import br.com.vp.advancedandroid.model.Repo
import com.squareup.moshi.Json

/**
 * @author diegovidal on 24/04/2018.
 */

data class TrendingReposResponse(
        @Json(name = "items") var repos: List<Repo>
        )
