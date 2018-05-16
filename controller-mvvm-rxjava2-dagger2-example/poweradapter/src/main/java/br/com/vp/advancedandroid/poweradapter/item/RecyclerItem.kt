package br.com.vp.advancedandroid.poweradapter.item

/**
 * @author diegovidal on 11/05/2018.
 */
interface RecyclerItem {

    fun getItemId(): Long
    fun renderKey(): String
}