package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.ZHPagerAdapter;
import com.usian.android_app_oschina.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 动弹的Fragment。
 */

public class SynthesizeFragment extends BaseFragment {

    @Bind(R.id.zh_tab)
    TabLayout zhTab;
    @Bind(R.id.zh_viewpager)
    ViewPager zhViewpager;
    private ArrayList<Fragment> data = new ArrayList<>();
    private ZHPagerAdapter title_adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_synthesize;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),null);
    }

    @Override
    protected void initData(Bundle bun) {
        data.add(new OpenFragment());
        data.add(new ReBlogFragment());

        title_adapter = new ZHPagerAdapter(getActivity().getSupportFragmentManager(),data);

        zhTab.setupWithViewPager(zhViewpager);

        zhViewpager.setAdapter(title_adapter);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
