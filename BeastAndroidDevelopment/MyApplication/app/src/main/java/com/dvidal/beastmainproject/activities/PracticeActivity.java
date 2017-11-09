package com.dvidal.beastmainproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.Brother;
import com.dvidal.beastmainproject.fragments.AboutUsFragment;
import com.dvidal.beastmainproject.fragments.BrotherDetailsFragment;
import com.dvidal.beastmainproject.fragments.MeetABroFragment;
import com.dvidal.beastmainproject.fragments.RushFragment;

/**
 * Created by diegovidal on 15/05/17.
 */

public class PracticeActivity extends BaseActivity {

    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_practice_frameLayoutContainer);
        if (fragment == null){

            fragment = RushFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_practice_frameLayoutContainer, fragment)
                    .commit();
        }
    }

    public static Intent newIntent(Context context, Brother brother){

        Intent intent = new Intent(context, PracticeActivity.class);
        intent.putExtra(BROTHER_EXTRA_INFO, brother);

        return intent;
    }
}
