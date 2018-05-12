package br.com.vp.poweradapter.adapter

import android.support.v7.util.DiffUtil
import br.com.vp.poweradapter.item.RecyclerItem

/**
 * @author diegovidal on 11/05/2018.
 */
class RecyclerDiffCallback
        constructor(private val oldList: List<RecyclerItem>,
                    private val newList: List<RecyclerItem>)
    : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].getId() == newList[newItemPosition].getId()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}