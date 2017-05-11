package com.usian.android_app_oschina.controller.fragment.dt_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private View mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_latest;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(getLayoutId(), null);
        return mRoot;
    }

    @Override
    protected void initData(Bundle bun) {

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
