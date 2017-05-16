package com.dvidal.beastmainproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.Brother;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 05/04/17.
 */

public class BrotherDetailsFragment extends BaseFragment {

    private Brother mBrother;
    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";

    // UI
    @BindView(R.id.fragment_brother_detail_txtBrotherCrossed)
    TextView mTxtBrotherCrossed;

    @BindView(R.id.fragment_brother_detail_txtBrotherFunFact)
    TextView mTxtBrotherFunFact;

    @BindView(R.id.fragment_brother_detail_txtBrotherMajor)
    TextView mTxtBrotherMajor;

    @BindView(R.id.fragment_brother_detail_txtBrotherName)
    TextView mTxtBrotherName;

    @BindView(R.id.fragment_brother_details_imgBrotherPicture)
    ImageView mImgBrotherPicture;

    @BindView(R.id.fragment_brother_details_progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.fragment_brother_detail_txtBrotherWhyJoined)
    TextView mTxtBrotherWhyJoined;

    public static BrotherDetailsFragment newInstance(Brother brother){

        Bundle arguments = new Bundle();
        arguments.putParcelable(BROTHER_EXTRA_INFO, brother);
        BrotherDetailsFragment brotherDetailsFragment = new BrotherDetailsFragment();
        brotherDetailsFragment.setArguments(arguments);

        return brotherDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBrother = getArguments().getParcelable(BROTHER_EXTRA_INFO);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_brother_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getView() != null) ButterKnife.bind(this, getView());

        mTxtBrotherName.setText(mBrother.getBrotherName());
        mTxtBrotherMajor.setText(getString(R.string.txt_major_intro, mBrother.getBrotherMajor()));
        mTxtBrotherFunFact.setText(getString(R.string.txt_fact_intro, mBrother.getBrotherFunFact()));
        mTxtBrotherCrossed.setText(getString(R.string.txt_crossed_intro, mBrother.getBrotherCrossSemester()));
        mTxtBrotherWhyJoined.setText(mBrother.getBrotherWhyJoin());

        Picasso.with(getActivity()).load(mBrother.getBrotherPicture())
                .fit()
                .centerCrop()
                .into(mImgBrotherPicture, new Callback() {
                    @Override
                    public void onSuccess() {

                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
