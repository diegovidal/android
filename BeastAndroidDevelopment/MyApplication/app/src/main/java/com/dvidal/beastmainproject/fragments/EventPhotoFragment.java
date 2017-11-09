package com.dvidal.beastmainproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.EventPicture;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 07/09/17.
 */

public class EventPhotoFragment extends BaseFragment {

    @BindView(R.id.fragment_event_picture_image)
    ImageView eventImage;

    @BindView(R.id.fragment_event_picture_progressBar)
    ProgressBar eventProgressBar;

    public static final String EVENT_PHOTO_INFO = "EVENT_PHOTO_INFO";
    private String photoUrl;

    public static EventPhotoFragment newInstance(EventPicture eventPicture){

        Bundle arguments = new Bundle();
        arguments.putString(EVENT_PHOTO_INFO, eventPicture.getPictureUrl());
        EventPhotoFragment eventPhotoFragment = new EventPhotoFragment();
        eventPhotoFragment.setArguments(arguments);

        return eventPhotoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photoUrl = getArguments().getString(EVENT_PHOTO_INFO);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_event_picture, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getView() != null) ButterKnife.bind(this, getView());

        Picasso.with(getActivity()).load(photoUrl)
                .fit()
                .centerCrop()
                .into(eventImage, new Callback() {
                    @Override
                    public void onSuccess() {

                        eventProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
