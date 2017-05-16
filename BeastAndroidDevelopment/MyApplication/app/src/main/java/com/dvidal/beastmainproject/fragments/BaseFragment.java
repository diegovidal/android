package com.dvidal.beastmainproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.squareup.otto.Bus;

/**
 * Created by diegovidal on 21/02/17.
 */

public class BaseFragment extends Fragment {

    private BeastApplication application;
    protected Bus bus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (BeastApplication) getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        bus.unregister(this);
    }
}
