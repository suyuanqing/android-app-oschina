package com.usian.android_app_oschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.usian.android_app_oschina.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/9.
 */

public class ZHPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<BaseFragment> data;
    private List<String> titleList;
    public ZHPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> data, List<String> titleList) {
        super(fm);
        this.data = data;
        this.titleList = titleList;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
