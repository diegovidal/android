package br.com.vp.poweradapter.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

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

        return RecyclerViewHolder(parent, dataSource.rendererForType(viewType))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(dataSource.getItem(position))
    }

    override fun getItemCount(): Int {
        return dataSource.getCount()
    }

    override fun getItemViewType(position: Int): Int {
        return dataSource.viewResourceForPosition(position)!!
    }

    override fun getItemId(position: Int): Long {
        return dataSource.getItem(position).getId()
    }
}