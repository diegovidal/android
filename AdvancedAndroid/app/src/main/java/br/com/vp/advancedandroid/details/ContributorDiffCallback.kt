package br.com.vp.advancedandroid.details

import android.support.v7.util.DiffUtil
import br.com.vp.advancedandroid.model.Contributor
import br.com.vp.advancedandroid.model.Repo

/**
 * @author diegovidal on 07/05/2018.
 */
class ContributorDiffCallback
        constructor(private val oldList: List<Contributor>, private val newList: List<Contributor>)
        : DiffUtil.Callback() {

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