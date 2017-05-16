package com.dvidal.beastmainproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.squareup.otto.Bus;

/**
 * Created by diegovidal on 21/02/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected BeastApplication application;
    protected Bus bus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (BeastApplication) getApplication();
        bus = application.getBus();
        bus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        bus.unregister(this);
    }
}
