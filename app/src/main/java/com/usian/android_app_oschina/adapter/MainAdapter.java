package com.usian.android_app_oschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 苏元庆 on 2017/5/9.
 */
public class MainAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> data;

    public MainAdapter(FragmentManager fm, ArrayList<Fragment> data) {
        super(fm);
        this.data = data;

    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
