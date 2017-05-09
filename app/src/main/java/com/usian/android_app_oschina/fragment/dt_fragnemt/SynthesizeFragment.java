package com.usian.android_app_oschina.fragment.dt_fragnemt;

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

    @Bind(R.id.title_name)
    TextView titleName;
    @Bind(R.id.title_toolbar)
    Toolbar titleToolbar;
    @Bind(R.id.zh_tab)
    TabLayout zhTab;
    @Bind(R.id.zh_viewpager)
    ViewPager zhViewpager;
    private View mRoot;
    private ArrayList<Fragment> data = new ArrayList<>();
    private ZHPagerAdapter title_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.fragment_synthesize, null);

        ButterKnife.bind(this, mRoot);

        //TODO 还有一个搜索按钮没有添加
        titleName.setText(R.string.title_zonghe);
        initData();
        return mRoot;
    }

    @Override
    public void initData() {
        super.initData();

        data.add(new OpenFragment());
        data.add(new ReBlogFragment());

        title_adapter = new ZHPagerAdapter(getActivity().getSupportFragmentManager(),data);

        zhViewpager.setAdapter(title_adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
