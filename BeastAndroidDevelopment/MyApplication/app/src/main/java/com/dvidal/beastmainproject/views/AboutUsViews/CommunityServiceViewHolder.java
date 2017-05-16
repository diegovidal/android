package com.dvidal.beastmainproject.views.AboutUsViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.EventCard;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 15/05/17.
 */

public class CommunityServiceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.list_event_card_eventDescription)
    TextView hTxtEventDescription;

    @BindView(R.id.list_event_card_progressBar)
    ProgressBar hProgressBar;

    @BindView(R.id.list_event_card_imageView)
    ImageView hImgEvent;

    @BindView(R.id.list_event_card_eventType)
    ImageView hImgEventType;

    @BindView(R.id.list_event_card_eventName)
    TextView hTxtEventName;

    public CommunityServiceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(Context context, EventCard eventCard){

        itemView.setTag(eventCard);

        if (!eventCard.isVideo()){

            hImgEventType.setImageResource(R.mipmap.camera);
        } else {

            hImgEventType.setImageResource(R.mipmap.video);
        }

        hTxtEventDescription.setText(eventCard.getEventDescription());
        hTxtEventName.setText(eventCard.getEventName());

        Picasso.with(context).load(eventCard.getEventImage())
                .fit()
                .centerCrop()
                .into(hImgEvent, new Callback() {
                    @Override
                    public void onSuccess() {

                        hProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
