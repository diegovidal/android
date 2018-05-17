package br.com.vp.advancedandroid.model

import br.com.vp.advancedandroid.poweradapter.item.RecyclerItem
import com.squareup.moshi.Json

/**
 * @author diegovidal on 23/04/2018.
 */

data class Contributor(var id: Long,
                var login: String,
                @Json(name = "avatar_url") var avatarUrl: String)
    : RecyclerItem{

    override fun getItemId(): Long {
        return id
    }

    override fun renderKey(): String {
        return "Contributor"
    }
}