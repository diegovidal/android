package com.dvidal.pedrapapeltesoura;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by diegovidal on 16/04/16.
 */
public class TabsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View minhaView = inflater.inflate(R.layout.fragment_tabs, container, false);

        TabLayout meuTabLayout = (TabLayout) minhaView.findViewById((R.id.meuTabLayout));
        ViewPager meuViewPager = (ViewPager) minhaView.findViewById(R.id.meuViewPager);

        //ViewPager --  coloca alem da página ativa mais 2 páginas em memoria
        meuViewPager.setOffscreenPageLimit(2);

        meuViewPager.setAdapter(new TabsAdapter(getContext(),getChildFragmentManager()));

        //TabLayout
        meuTabLayout.setupWithViewPager(meuViewPager);


        return minhaView;
    }
}
