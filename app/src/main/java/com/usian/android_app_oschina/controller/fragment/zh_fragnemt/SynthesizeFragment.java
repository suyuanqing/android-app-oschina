package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.ZHPagerAdapter;
import com.usian.android_app_oschina.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 动弹的Fragment。
 */

public class SynthesizeFragment extends BaseFragment {

    @Bind(R.id.zh_tab)
    TabLayout zhTab;
    @Bind(R.id.zh_viewpager)
    ViewPager zhViewpager;
    @Bind(R.id.iv_ic_add)
    ImageView ivIcAdd;
    private ArrayList<Fragment> data = new ArrayList<>();
    private ZHPagerAdapter title_adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_synthesize;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        data.add(new OpenFragment());
        data.add(new ReBlogFragment());
        data.add(new HotNewsFragment());
        data.add(new LatestBlogFragment());

        title_adapter = new ZHPagerAdapter(getActivity().getSupportFragmentManager(), data);

        zhTab.setupWithViewPager(zhViewpager);

        zhViewpager.setAdapter(title_adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.iv_ic_add)
    public void onViewClicked() {

        RotateAnimation rotateAnimation = new RotateAnimation(0,225);

        ivIcAdd.setAnimation(rotateAnimation);
        rotateAnimation.setDuration(2000);
    }
}
