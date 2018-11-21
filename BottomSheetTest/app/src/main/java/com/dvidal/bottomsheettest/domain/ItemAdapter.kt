package com.dvidal.bottomsheettest.domain

import android.content.Context
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.dvidal.bottomsheettest.R


/**
 * @author diegovidal on 20/11/18.
 */
class ItemAdapter(context: Context, items: List<Item>, val clickListener: (Int) -> (Unit)) : CommonAdapter() {

    init {
        this.items = items
        this.inflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        var cv = convertView
        val holder: ViewHolder
        val item = items?.get(position) as Item

        if (cv == null) {
            holder = ViewHolder()
            cv = inflater?.inflate(R.layout.item_grid, parent, false)
            cv?.tag = holder

            holder.icon = cv?.findViewById(R.id.iv_icon) as ImageView
            holder.label = cv.findViewById(R.id.tv_label) as TextView

            cv.setOnClickListener {
                clickListener(position)
            }
        } else {
            holder = cv.tag as ViewHolder
        }

        holder.icon?.setImageResource(item.getIconId())
        holder.label?.text = item.label

        return cv
    }
}