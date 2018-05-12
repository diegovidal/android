package br.com.vp.poweradapter.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.vp.poweradapter.item.ItemRenderer
import br.com.vp.poweradapter.item.RecyclerItem

/**
 * @author diegovidal on 11/05/2018.
 */
class RecyclerViewHolder
        constructor(parent: ViewGroup, private val renderer: ItemRenderer<RecyclerItem>?)
    : RecyclerView.ViewHolder(renderer?.createView(parent)) {

    fun bind(item: RecyclerItem){
        renderer?.render(itemView, item)
    }
}