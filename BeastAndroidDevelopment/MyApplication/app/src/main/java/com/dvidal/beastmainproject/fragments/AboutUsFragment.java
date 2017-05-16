package com.dvidal.beastmainproject.fragments;

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
import com.dvidal.beastmainproject.enties.EventCard;
import com.dvidal.beastmainproject.services.EventCardServices;
import com.dvidal.beastmainproject.views.AboutUsViews.AboutUsAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 08/12/16.
 */

public class AboutUsFragment extends BaseFragment implements AboutUsAdapter.AboutUsListener {

    private ArrayList<EventCard> mCommunityServiceCards;
    private ArrayList<EventCard> mBrotherhoodCards;
    private ArrayList<EventCard> mSocialCards;

    private AboutUsAdapter mAboutUsAdapter;

    // UI
    RecyclerView mRecycleView;

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

        mRecycleView = (RecyclerView) getView().findViewById(R.id.fragment_about_us_recycleView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setUpAdapter();

        bus.post(new EventCardServices.SearchCommunityServiceCardsRequest("Hello"));
        bus.post(new EventCardServices.SearchBrotherhoodCardsRequest("Hello"));
        bus.post(new EventCardServices.SearchSocialCardsRequest("Hello"));
    }

    private void setUpAdapter(){

        if (isAdded()){
            mRecycleView.setAdapter(mAboutUsAdapter);
        }
    }

    @Override
    public void onEventCardClicked(EventCard eventCard) {

        if (!eventCard.isVideo()){

            Log.i(AboutUsFragment.class.getSimpleName(), eventCard.getEventName() + " is a slide show");

        } else {
            Log.i(AboutUsFragment.class.getSimpleName(), eventCard.getEventName() + " is a video");
        }
    }

    @Subscribe
    public void getCommunityServiceEvents(EventCardServices.SearchCommunityServiceCardsResponse response){

        mCommunityServiceCards.clear();
        mCommunityServiceCards.addAll(response.communityServiceCards);
    }

    @Subscribe
    public void getBrotherhoodEvents(EventCardServices.SearchBrotherhoodCardsResponse response){

        mBrotherhoodCards.clear();
        mBrotherhoodCards.addAll(response.brotherhoodCards);
    }

    @Subscribe
    public void getSocialEvents(EventCardServices.SearchSocialCardsResponse response){

        mSocialCards.clear();
        mSocialCards.addAll(response.socialCards);
    }
}
