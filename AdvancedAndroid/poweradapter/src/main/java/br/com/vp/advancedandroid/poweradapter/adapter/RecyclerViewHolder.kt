package br.com.vp.advancedandroid.poweradapter.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.vp.advancedandroid.poweradapter.item.ItemRenderer
import br.com.vp.advancedandroid.poweradapter.item.RecyclerItem

/**
 * @author diegovidal on 11/05/2018.
 */
class RecyclerViewHolder
        (parent: ViewGroup, private val renderer: ItemRenderer<RecyclerItem>?)
    : RecyclerView.ViewHolder(renderer?.createView(parent)) {

    fun bind(item: RecyclerItem){
        renderer?.render(itemView, item)
    }
}