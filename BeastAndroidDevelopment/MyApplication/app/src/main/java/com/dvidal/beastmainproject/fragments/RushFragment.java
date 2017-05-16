package com.dvidal.beastmainproject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvidal.beastmainproject.R;

/**
 * Created by diegovidal on 08/12/16.
 */

public class RushFragment extends BaseFragment {

    public static RushFragment newInstance(){

        return new RushFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_rush, container, false);

        return rootView;
    }
}
