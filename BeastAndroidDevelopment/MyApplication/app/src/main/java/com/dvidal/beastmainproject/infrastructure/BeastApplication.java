package com.dvidal.beastmainproject.infrastructure;

import android.app.Application;

import com.dvidal.beastmainproject.live.Module;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.otto.Bus;

/**
 * Created by diegovidal on 21/02/17.
 */

public class BeastApplication extends Application {

    private Bus bus;

    public BeastApplication() {
        this.bus = new Bus();
    }

    public static final String YOUTUBE_KEY = "AIzaSyDpkcDNEktemKJJEK7xTLLxckwc4GDEcb0";
    public static final String FIREBASE_BROTHER_REFERENCE = "data/brothers";

    public static final String FIREBASE_EVENT_CARD_COMMUNITY = "data/eventCards/community";
    public static final String FIREBASE_EVENT_CARD_BROTHERHOOD = "data/eventCards/brotherHood";
    public static final String FIREBASE_EVENT_CARD_SOCIAL = "data/eventCards/social";

    public static final String FIREBASE_EVENT_PICTURE_COMMUNITY = "data/eventPics/community";
    public static final String FIREBASE_EVENT_PICTURE_BROTHERHOOD = "data/eventPics/brotherHood";
    public static final String FIREBASE_EVENT_PICTURE_SOCIAL = "data/eventPics/social";

    public static final String FIREBASE_RUSH_COMMUNITY= "data/rushEvents/community";
    public static final String FIREBASE_RUSH_SOCIAL= "data/rushEvents/social";

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Module.Register(this);
    }

    public Bus getBus() {
        return bus;
    }
}
