package com.dvidal.rxexample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by diegovidal on 13/11/2017.
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder {

    TextView mTxtString;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        mTxtString = itemView.findViewById(R.id.list_simple_string_txtString);
    }
}
