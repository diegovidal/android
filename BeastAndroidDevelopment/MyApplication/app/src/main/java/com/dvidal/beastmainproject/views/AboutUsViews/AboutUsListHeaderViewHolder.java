package com.dvidal.beastmainproject.views.AboutUsViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dvidal.beastmainproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 15/05/17.
 */

public class AboutUsListHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.simple_header_textView)
    TextView hTxtHeader;

    public AboutUsListHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(String header){

        hTxtHeader.setText(header);
    }
}
