package com.dvidal.beastmainproject.views.RushViews;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dvidal.beastmainproject.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 08/09/17.
 */

public class RushFooterHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.footer_rush_fragment_imgFacebook)
    ImageView imgFacebook;

    @BindView(R.id.footer_rush_fragment_imgTwitter)
    ImageView imgTwitter;

    @BindView(R.id.footer_rush_fragment_imgsSnapChat)
    ImageView imgSnapChat;

    @BindView(R.id.footer_rush_fragment_imgInstagram)
    ImageView imgInstagram;

    public RushFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        imgFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                try{

                    view.getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100002371011546"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (PackageManager.NameNotFoundException e) {

                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/diego.vidal.330"));
                }

                view.getContext().startActivity(intent);
            }
        });

        imgTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                try{

                    view.getContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=" + "1544019097"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (PackageManager.NameNotFoundException e) {

                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/diegovidal08"));
                }

                view.getContext().startActivity(intent);
            }
        });

        imgSnapChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://snapchat.com/add/" + "dvidal"));
                view.getContext().startActivity(intent);
            }
        });

        imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                try{

                    view.getContext().getPackageManager().getPackageInfo("com.instagram.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/_udiego_dcv"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (PackageManager.NameNotFoundException e) {

                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/diego_dcv/?hl=en"));
                }

                view.getContext().startActivity(intent);
            }
        });
    }

    public void populate(Context context){

        Picasso.with(context).load("http://i50.photobucket.com/albums/f315/carlos6024/faceBookLogo_zps5ehpqnng.png")
                .into(imgFacebook);

        Picasso.with(context).load("http://i50.photobucket.com/albums/f315/carlos6024/snapChat_logo_zpsjzwi8hpr.png")
                .into(imgSnapChat);

        Picasso.with(context).load("http://i50.photobucket.com/albums/f315/carlos6024/insta_logo_zpshg6xmz7g.jpg")
                .into(imgInstagram);

        Picasso.with(context).load("http://i50.photobucket.com/albums/f315/carlos6024/twitterLogo_zpsp0zhmlxb.png")
                .into(imgTwitter);
    }
}
