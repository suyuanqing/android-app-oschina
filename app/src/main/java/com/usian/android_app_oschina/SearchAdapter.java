package com.usian.android_app_oschina;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.usian.android_app_oschina.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 苏元庆 on 2017/5/25.
 */
public class SearchAdapter extends FragmentPagerAdapter{

    private String[] str;
    private ArrayList<BaseFragment> data;
    public SearchAdapter(FragmentManager fm, String[] str, ArrayList<BaseFragment> data) {
        super(fm);
        this.data = data;
        this.str = str;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
