package com.dvidal.beastweather.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.dvidal.beastweather.R;

/**
 * Created by diegovidal on 05/12/2017.
 */

public abstract class BaseFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_main_container);

        if (fragment == null){
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_main_container, fragment)
                    .commit();
        }
    }
}
