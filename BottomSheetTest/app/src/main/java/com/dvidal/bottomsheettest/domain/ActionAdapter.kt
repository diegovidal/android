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
class ActionAdapter(context: Context, actions: List<*>, val clickListener: (Int) -> (Unit)) : CommonAdapter() {

    init {
        this.items = actions
        this.inflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        var cv = convertView
        val holder: ViewHolder
        val action = items?.get(position) as Action

        if (cv == null) {
            holder = ViewHolder()
            cv = inflater?.inflate(R.layout.item_action, parent, false)
            cv?.tag = holder

            holder.icon = cv?.findViewById(R.id.iv_icon) as ImageView
            holder.label = cv.findViewById(R.id.tv_label) as TextView

            cv.setOnClickListener {
                clickListener(position)
            }
        } else {
            holder = cv.tag as ViewHolder
        }

        holder.icon?.setImageResource(action.getIconId())
        holder.label?.text = action.label

        return cv
    }
}