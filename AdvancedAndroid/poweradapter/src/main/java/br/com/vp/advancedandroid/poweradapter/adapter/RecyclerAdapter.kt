package br.com.vp.advancedandroid.poweradapter.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.vp.advancedandroid.poweradapter.item.ItemRenderer
import br.com.vp.advancedandroid.poweradapter.item.RecyclerItem

/**
 * @author diegovidal on 11/05/2018.
 */

class RecyclerAdapter
        constructor(private val dataSource: RecyclerDataSource)
    : RecyclerView.Adapter<RecyclerViewHolder>() {

    init {
        dataSource.attachToAdapter(this)
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        @Suppress("UNCHECKED_CAST")
        return RecyclerViewHolder(parent, dataSource.rendererForType(viewType) as? ItemRenderer<RecyclerItem>?)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(dataSource.getItem(position))
    }

    override fun getItemCount(): Int {
        return dataSource.getCount()
    }

    override fun getItemViewType(position: Int): Int {
        return dataSource.viewResourceForPosition(position) ?: 0
    }

    override fun getItemId(position: Int): Long {
        return dataSource.getItem(position).getItemId()
    }
}