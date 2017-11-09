package com.dvidal.beastmainproject.views.RushViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.RushEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 08/09/17.
 */

public class RushEventsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_rush_event_txtName)
    TextView rushName;

    @BindView(R.id.list_rush_event_txtDate)
    TextView rushDate;

    @BindView(R.id.list_rush_event_txtLocation)
    TextView rushLocation;

    @BindView(R.id.list_rush_event_txtTime)
    TextView rushTime;

    public RushEventsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(RushEvent rushEvent){

        itemView.setTag(rushEvent);
        rushName.setText(rushEvent.getEventName());
        rushDate.setText(rushEvent.getEventDate());
        rushLocation.setText(rushEvent.getEventLocation());
        rushTime.setText(rushEvent.getEventTime());
    }
}
