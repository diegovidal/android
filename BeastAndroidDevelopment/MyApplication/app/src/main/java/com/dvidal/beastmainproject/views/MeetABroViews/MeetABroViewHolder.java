package com.dvidal.beastmainproject.views.MeetABroViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.Brother;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 27/12/16.
 */

public class MeetABroViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.list_meet_a_bro_imgView)
    ImageView brotherPicture;

    @BindView(R.id.list_meet_a_bro_progressBar)
    ProgressBar brotherProgressBar;

    public MeetABroViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(Context context, Brother brother){

        itemView.setTag(brother);

        Picasso.with(context).load(brother.getBrotherPicture())
                .fit()
                .centerCrop()
                .into(brotherPicture, new Callback() {
                    @Override
                    public void onSuccess() {

                        brotherProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
