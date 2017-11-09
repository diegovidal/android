package com.dvidal.beastmainproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.activities.CampusMapActivity;
import com.dvidal.beastmainproject.activities.MapsActivity;
import com.dvidal.beastmainproject.activities.BaseActivity;
import com.dvidal.beastmainproject.enties.RushEvent;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.RushEventsService;
import com.dvidal.beastmainproject.views.RushViews.Item;
import com.dvidal.beastmainproject.views.RushViews.RushEventsAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegovidal on 08/12/16.
 */

public class RushFragment extends BaseFragment implements RushEventsAdapter.RushEventListener {

    private RushEventsAdapter mRushEventsAdapter;
    private ArrayList<RushEvent> mCommunityEvents;
    private ArrayList<RushEvent> mSocialEvents;

    private Item mSocial;
    private Item mCommunity;

    private RecyclerView mRecyclerView;

    public static RushFragment newInstance(){

        return new RushFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_rush, container, false);

        mRushEventsAdapter = new RushEventsAdapter((BaseActivity) getActivity(), this);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_rush_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSocialEvents = new ArrayList<>();
        mCommunityEvents = new ArrayList<>();

        List<Item> data = mRushEventsAdapter.getData();

        mSocial = new Item(RushEventsAdapter.VIEW_TYPE_EXPANDABLE_LIST_HEADER, "Social Events");
        mSocial.invisibleChildren = new ArrayList<>();

        mCommunity = new Item(RushEventsAdapter.VIEW_TYPE_EXPANDABLE_LIST_HEADER, "Community Events");
        mCommunity.invisibleChildren = new ArrayList<>();

        bus.post(new RushEventsService.SearchRushEventsCommunityRequest(BeastApplication.FIREBASE_RUSH_COMMUNITY));
        bus.post(new RushEventsService.SearchRushEventSocialRequest(BeastApplication.FIREBASE_RUSH_SOCIAL));

        setUpAdapter();

        data.add(mCommunity);
        data.add(mSocial);

        return rootView;
    }

    private void setUpAdapter(){

        if (isAdded()){
            mRecyclerView.setAdapter(mRushEventsAdapter);
        }
    }

    @Override
    public void onRushEventClicked(RushEvent rushEvent) {

        if (!rushEvent.isOnCampus()){
            Intent intent = MapsActivity.newIntent(getActivity(), rushEvent);
            startActivity(intent);
        } else {

            Intent intent = CampusMapActivity.newIntent(getActivity(), rushEvent);
            startActivity(intent);
        }
    }

    @Subscribe
    public void getServiceEvents(RushEventsService.SearchRushEventsCommunityResponse response){

        mCommunityEvents.clear();
        mCommunityEvents.addAll(response.communityRushEvents);

        for (RushEvent rushEvent : mCommunityEvents){

            mCommunity.invisibleChildren.add(new Item(RushEventsAdapter.VIEW_TYPE_EXPANDABLE_LIST_CHILD, rushEvent));
        }
    }

    @Subscribe
    public void getSocialEvents(RushEventsService.SearchRushEventSocialResponse response){

        mSocialEvents.clear();
        mSocialEvents.addAll(response.socialEvents);

        for (RushEvent rushEvent : mSocialEvents){

            mSocial.invisibleChildren.add(new Item(RushEventsAdapter.VIEW_TYPE_EXPANDABLE_LIST_CHILD, rushEvent));
        }
    }
}
