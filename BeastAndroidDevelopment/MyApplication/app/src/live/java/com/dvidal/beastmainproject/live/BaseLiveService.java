package com.dvidal.beastmainproject.live;

import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.otto.Bus;

/**
 * Created by diegovidal on 30/10/17.
 */

public class BaseLiveService {

    protected Bus bus;
    protected BeastApplication application;
    protected final DatabaseReference databaseReference;

    public BaseLiveService(BeastApplication application) {
        this.application = application;
        bus = application.getBus();
        bus.register(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }
}
