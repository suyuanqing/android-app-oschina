package com.usian.android_app_oschina.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 苏元庆 on 2017/5/9.
 */

public class ImageAdapter extends PagerAdapter{

    private ArrayList<View> imgdata;

    private String[] titlename = {"0","0","0","0","0"};
    public ImageAdapter(ArrayList<View> imgdata){
        this.imgdata = imgdata;
    }

    @Override
    public int getCount() {
        return imgdata.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imgdata.get(position),0);
        return imgdata.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(imgdata.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlename[position];
    }
}
