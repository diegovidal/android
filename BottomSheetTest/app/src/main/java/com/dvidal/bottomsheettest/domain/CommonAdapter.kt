package com.dvidal.bottomsheettest.domain

import android.widget.TextView
import android.view.LayoutInflater
import android.widget.BaseAdapter
import android.widget.ImageView


/**
 * @author diegovidal on 20/11/18.
 */
abstract class CommonAdapter : BaseAdapter() {

    protected var items: List<*>? = null
    protected var inflater: LayoutInflater? = null

    override fun getCount(): Int {
        return items!!.size
    }

    override fun getItem(position: Int): Any? {
        return items!![position]
    }

    override fun getItemId(position: Int): Long {
        val item = items!![position] as Item
        return item.getIconId().toLong()
    }

    internal class ViewHolder {
        var icon: ImageView? = null
        var label: TextView? = null
    }
}