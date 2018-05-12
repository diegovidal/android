package br.com.vp.poweradapter.item

import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup

/**
 * @author diegovidal on 11/05/2018.
 */
interface ItemRenderer<T: RecyclerItem> {

    @LayoutRes
    fun layoutRes(): Int

    fun createView(parent: ViewGroup): View

    fun render(itemView: View, item: T)

}