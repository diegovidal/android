package com.dvidal.beastmainproject.views.RushViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dvidal.beastmainproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 08/09/17.
 */

public class RushExpandableHeaderHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_expandable_header_txtButtonToggle)
    ImageView buttonToggle;

    @BindView(R.id.list_expandable_header_linLayout)
    View backgroundView;

    @BindView(R.id.list_expandable_header_txtName)
    TextView txtName;

    public Item referralItem;

    public RushExpandableHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
