package com.usian.android_app_oschina.controller.fragment.dt_fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 这个是动弹下最新动弹的Fragment
 */

public class MyTanFragment extends BaseFragment {

    @Bind(R.id.latest_recycler)
    RecyclerView latestRecycler;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_latest;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        latestRecycler.setHasFixedSize(true);
        latestRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        latestRecycler.setItemAnimator(new DefaultItemAnimator());
        latestRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
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
