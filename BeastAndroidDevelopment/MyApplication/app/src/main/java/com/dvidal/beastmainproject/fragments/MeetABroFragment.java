package com.dvidal.beastmainproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.activities.BaseActivity;
import com.dvidal.beastmainproject.activities.BrotherPagerActivity;
import com.dvidal.beastmainproject.enties.Brother;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.BrotherServices;
import com.dvidal.beastmainproject.views.MeetABroViews.MeetABroAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by diegovidal on 08/12/16.
 */

public class MeetABroFragment extends BaseFragment implements MeetABroAdapter.BrotherClickedListener {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private MeetABroAdapter mMeetABroAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Brother> brothers;

    public static MeetABroFragment newInstance(){

        return new MeetABroFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_meet_a_bro, container, false);

        mMeetABroAdapter = new MeetABroAdapter(this, (BaseActivity) getActivity());

        brothers = mMeetABroAdapter.getBrothers();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_meet_a_bro_recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        setUpAdapter();
        bus.post(new BrotherServices.SearchBrotherRequest(BeastApplication.FIREBASE_BROTHER_REFERENCE));

        return rootView;
    }

    private void setUpAdapter(){

        if (isAdded()){
            mRecyclerView.setAdapter(mMeetABroAdapter);
        }
    }

    @Override
    public void onBrotherClicked(Brother brother) {

        Intent intent = BrotherPagerActivity.newIntent(getActivity(), brother);
        startActivity(intent);
    }

    @Subscribe
    public void getBrothers(BrotherServices.SearchBrotherResponse response){

        int oldSize = brothers.size();

        if (oldSize == 0){

            brothers.clear();
            mMeetABroAdapter.notifyItemRangeRemoved(0, oldSize);
            brothers.addAll(response.brothers);
            mMeetABroAdapter.notifyItemRangeChanged(0, brothers.size());
        }

    }
}
