package com.dvidal.beastmainproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.EventCard;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by diegovidal on 07/09/17.
 */

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private String videoUrl;
    public static final String EXTRA_VIDEO_INFO = "EXTRA_VIDEO_INFO";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_youtube);

        videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_INFO);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.activity_youtube_playerView);
        youTubePlayerView.initialize(BeastApplication.YOUTUBE_KEY, this);
    }

    public static Intent newIntent(Context context, EventCard eventCard){

        Intent intent = new Intent(context, YoutubeActivity.class);
        intent.putExtra(EXTRA_VIDEO_INFO, eventCard.getYoutubeEnding());

        return intent;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.loadVideo(videoUrl);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
