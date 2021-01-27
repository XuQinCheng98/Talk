package com.example.talk.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends FragmentPagerAdapter {
    List <Fragment> fragmentList=new ArrayList<Fragment>();
    private final int PAGER_COUNT=3;

    public MyAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        fragmentList=fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position) ;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
