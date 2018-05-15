package br.com.vp.advancedandroid.poweradapter.adapter

import android.support.annotation.LayoutRes
import android.support.annotation.MainThread
import android.support.annotation.VisibleForTesting
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import br.com.vp.advancedandroid.poweradapter.item.ItemRenderer
import br.com.vp.advancedandroid.poweradapter.item.RecyclerItem
import java.lang.ref.WeakReference

/**
 * @author diegovidal on 11/05/2018.
 */

class RecyclerDataSource
        constructor(private val renderers: MutableMap<String, ItemRenderer<out RecyclerItem>>) {

    private val viewTypeToRendererKeyMap = mutableMapOf<Int, String>()
    private val data = arrayListOf<RecyclerItem>()

    private var adapterReference: WeakReference<RecyclerView.Adapter<*>>? = WeakReference<RecyclerView.Adapter<*>>(null)

    init {

        for (entry in renderers.entries){
            viewTypeToRendererKeyMap[entry.value.layoutRes()] = entry.key
        }
    }

    @MainThread
    fun setData(newData: List<RecyclerItem>){

        val diffResult = DiffUtil.calculateDiff(RecyclerDiffCallback(data, newData))
        data.clear()
        data.addAll(newData)
        val adapter = adapterReference?.get()

        adapter?.let {
            diffResult.dispatchUpdatesTo(adapter)
        }
    }

    fun rendererForType(viewType: Int): ItemRenderer<out RecyclerItem>? {

        @Suppress("UNCHECKED_CAST")
        return renderers[viewTypeToRendererKeyMap[viewType]]
    }

    @LayoutRes
    fun viewResourceForPosition(position: Int): Int? {
        return renderers[data[position].renderKey()]?.layoutRes()
    }

    fun getCount(): Int {
        return data.size
    }

    fun getItem(position: Int): RecyclerItem {
        return data[position]
    }

    fun attachToAdapter(adapter: RecyclerView.Adapter<*>) {
        adapterReference = WeakReference(adapter)
    }

    /**
     * Allows us to set data without invoking DiffUtil which would throw an exception during unit testing.
     */
    @VisibleForTesting
    fun seedData(data: List<RecyclerItem>){
        this.data.clear()
        this.data.addAll(data)
    }
}