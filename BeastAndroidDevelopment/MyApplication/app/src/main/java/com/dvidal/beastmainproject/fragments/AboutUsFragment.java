package com.dvidal.beastmainproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.activities.BaseActivity;
import com.dvidal.beastmainproject.activities.PhotoPagerActivity;
import com.dvidal.beastmainproject.activities.YoutubeActivity;
import com.dvidal.beastmainproject.enties.EventCard;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.EventCardServices;
import com.dvidal.beastmainproject.views.AboutUsViews.AboutUsAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by diegovidal on 08/12/16.
 */

public class AboutUsFragment extends BaseFragment implements AboutUsAdapter.AboutUsListener {

    private ArrayList<EventCard> mCommunityServiceCards;
    private ArrayList<EventCard> mBrotherhoodCards;
    private ArrayList<EventCard> mSocialCards;

    private AboutUsAdapter mAboutUsAdapter;

    // UI
    RecyclerView mRecyclerView;

    public static AboutUsFragment newInstance(){

        return new AboutUsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAboutUsAdapter = new AboutUsAdapter((BaseActivity) getActivity(), this);

        mCommunityServiceCards = mAboutUsAdapter.getmCommunityServiceEventCards();
        mBrotherhoodCards = mAboutUsAdapter.getmBrotherhoodEventCards();
        mSocialCards = mAboutUsAdapter.getmSocialEventCards();

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.fragment_about_us_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setUpAdapter();

        bus.post(new EventCardServices.SearchCommunityServiceCardsRequest(BeastApplication.FIREBASE_EVENT_CARD_COMMUNITY));
        bus.post(new EventCardServices.SearchBrotherhoodCardsRequest(BeastApplication.FIREBASE_EVENT_CARD_BROTHERHOOD));
        bus.post(new EventCardServices.SearchSocialCardsRequest(BeastApplication.FIREBASE_EVENT_CARD_SOCIAL));
    }

    private void setUpAdapter(){

        if (isAdded()){
            mRecyclerView.setAdapter(mAboutUsAdapter);
        }
    }

    @Override
    public void onEventCardClicked(EventCard eventCard) {

        if (!eventCard.isVideo()){
            Intent intent = PhotoPagerActivity.newIntent(getActivity(), eventCard);
            startActivity(intent);

            Log.i(AboutUsFragment.class.getSimpleName(), eventCard.getEventName() + " is a slide show");

        } else {
            Intent intent = YoutubeActivity.newIntent(getActivity(), eventCard);
            startActivity(intent);

            Log.i(AboutUsFragment.class.getSimpleName(), eventCard.getEventName() + " is a video");
        }
    }

    @Subscribe
    public void getCommunityServiceEvents(EventCardServices.SearchCommunityServiceCardsResponse response){

        int oldsize = mCommunityServiceCards.size();
        if (oldsize == 0) {
            mCommunityServiceCards.clear();
            mAboutUsAdapter.notifyItemRangeChanged(0, oldsize);
            mCommunityServiceCards.addAll(response.communityServiceCards);
            mAboutUsAdapter.notifyItemRangeChanged(0, mCommunityServiceCards.size());
        }
    }

    @Subscribe
    public void getBrotherhoodEvents(EventCardServices.SearchBrotherhoodCardsResponse response){

        int oldsize = mBrotherhoodCards.size();
        if (oldsize == 0) {
            mBrotherhoodCards.clear();
            mAboutUsAdapter.notifyItemRangeChanged(0, oldsize);
            mBrotherhoodCards.addAll(response.brotherhoodCards);
            mAboutUsAdapter.notifyItemRangeChanged(0, mBrotherhoodCards.size());
        }
    }

    @Subscribe
    public void getSocialEvents(EventCardServices.SearchSocialCardsResponse response){

        int oldsize = mSocialCards.size();
        if (oldsize == 0) {
            mSocialCards.clear();
            mAboutUsAdapter.notifyItemRangeChanged(0, oldsize);
            mSocialCards.addAll(response.socialCards);
            mAboutUsAdapter.notifyItemRangeChanged(0, mSocialCards.size());
        }
    }
}
