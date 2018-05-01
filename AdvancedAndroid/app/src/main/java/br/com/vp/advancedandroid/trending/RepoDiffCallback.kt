package br.com.vp.advancedandroid.trending

import android.support.v7.util.DiffUtil

import br.com.vp.advancedandroid.model.Repo

/**
 * @author diegovidal on 26/04/2018.
 */
class RepoDiffCallback(private val oldList: List<Repo>, private val newList: List<Repo>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}