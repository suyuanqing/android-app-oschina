package com.usian.android_app_oschina.controller.fragment.dt_fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.DongTanAdapter;
import com.usian.android_app_oschina.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 动弹的Fragment。
 */

//TODO 评论 点赞 转发 需要登录后再实现
public class TweetFragnemt extends BaseFragment {

    @Bind(R.id.dt_tab)
    TabLayout dtTab;
    @Bind(R.id.dt_viewpager)
    ViewPager dtViewpager;

    private ArrayList<Fragment> data = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stir;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        dtViewpager.setOffscreenPageLimit(4);
        data.add(new LatestTweetFragment());
        data.add(new HotTweetFragment());
        data.add(new ThrumFragment());
        data.add(new MineTweetFragment());
        DongTanAdapter adapter = new DongTanAdapter(getActivity().getSupportFragmentManager(),data);
        dtViewpager.setAdapter(adapter);
        dtTab.setupWithViewPager(dtViewpager);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
