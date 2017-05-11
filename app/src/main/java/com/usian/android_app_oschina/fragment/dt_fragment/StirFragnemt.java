package com.usian.android_app_oschina.fragment.dt_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 动弹的Fragment。
 */

public class StirFragnemt extends BaseFragment {

    @Bind(R.id.title_name)
    TextView titleName;
    @Bind(R.id.title_toolbar)
    Toolbar titleToolbar;
    @Bind(R.id.dt_tab)
    TabLayout dtTab;
    @Bind(R.id.dt_viewpager)
    ViewPager dtViewpager;
    private View mRoot;
    private ArrayList<Fragment> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.fragment_stir, null);

        ButterKnife.bind(this, mRoot);

        //TODO 还有一个搜索按钮没有添加
        titleName.setText(R.string.title_dongtan);

        return mRoot;
    }

    @Override
    public void initData() {
        super.initData();



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
