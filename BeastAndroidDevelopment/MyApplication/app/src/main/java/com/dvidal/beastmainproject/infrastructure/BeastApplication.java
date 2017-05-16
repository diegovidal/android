package com.dvidal.beastmainproject.infrastructure;

import android.app.Application;

import com.dvidal.beastmainproject.inmemory.Module;
import com.squareup.otto.Bus;

/**
 * Created by diegovidal on 21/02/17.
 */

public class BeastApplication extends Application {

    private Bus bus;

    public BeastApplication() {
        this.bus = new Bus();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Module.Register(this);
    }

    public Bus getBus() {
        return bus;
    }
}
