package br.com.vp.poweradapter.item

/**
 * @author diegovidal on 11/05/2018.
 */
interface RecyclerItem {

    fun getId(): Long

    fun renderKey(): String
}