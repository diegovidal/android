package com.dvidal.beastmainproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.Brother;
import com.dvidal.beastmainproject.fragments.BrotherDetailsFragment;
import com.dvidal.beastmainproject.infrastructure.BeastApplication;
import com.dvidal.beastmainproject.services.BrotherServices;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 15/05/17.
 */

public class BrotherPagerActivity extends BaseActivity {

    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";
    private ArrayList<Brother> mBrothers;

    @BindView(R.id.activity_brother_pager_viewPager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brother_pager);
        ButterKnife.bind(this);

        mBrothers = new ArrayList<>();
        bus.post(new BrotherServices.SearchBrotherRequest(BeastApplication.FIREBASE_BROTHER_REFERENCE));
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                Brother brother = mBrothers.get(position);
                return BrotherDetailsFragment.newInstance(brother);
            }

            @Override
            public int getCount() {

                return mBrothers.size();
            }
        });

//        Brother brother = getIntent().getParcelableExtra(BROTHER_EXTRA_INFO);
//        int brotherId = brother.getBrotherId();
//
//        for (int i = 0; i < mBrothers.size(); i++){
//
//            if (mBrothers.get(i).getBrotherId() == brotherId){
//                mViewPager.setCurrentItem(i);
//                break;
//            }
//        }
    }

    @Subscribe
    public void getBrothers(BrotherServices.SearchBrotherResponse response){

        mBrothers.clear();
        mBrothers.addAll(response.brothers);
        mViewPager.getAdapter().notifyDataSetChanged();
    }

    public static Intent newIntent(Context context, Brother brother){

        Intent intent = new Intent(context, BrotherPagerActivity.class);
        intent.putExtra(BROTHER_EXTRA_INFO, brother);

        return intent;
    }
}
