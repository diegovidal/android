package com.dvidal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by diegovidal on 10/04/16.
 */
public class TerceiroFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.fragment_terceiro, container, false);

        return fragView;
    }
}
