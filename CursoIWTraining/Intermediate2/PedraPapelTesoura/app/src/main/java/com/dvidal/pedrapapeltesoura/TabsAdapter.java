package com.dvidal.pedrapapeltesoura;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by diegovidal on 16/04/16.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    Context context;

    public TabsAdapter(Context context, FragmentManager fm){
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";

        switch (position){

            case 0:
                title = context.getString(R.string.lbl_pedra);
                break;

            case 1:
                title = context.getString(R.string.lbl_papel);
                break;

            case 2:
                title = context.getString(R.string.lbl_tesoura);
                break;
        }

        return title;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = new Fragment();

        switch (position){

            case 0:
                frag = new PedraFragment();
                break;

            case 1:
                frag = new PapelFragment();
                break;

            case 2:
                frag = new TesouraFragment();
                break;
        }

        return frag;
    }
}
