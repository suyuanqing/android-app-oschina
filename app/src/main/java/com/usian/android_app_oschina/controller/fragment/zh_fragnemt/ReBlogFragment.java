package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

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
 */

public class ReBlogFragment extends BaseFragment {


    @Bind(R.id.reblog_recycler)
    RecyclerView reblogRecycler;

    private View mRoot;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reblog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(getLayoutId(), null);

        return mRoot;
    }


    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(Bundle bun) {
        reblogRecycler.setHasFixedSize(true);
        reblogRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        reblogRecycler.setItemAnimator(new DefaultItemAnimator());
        reblogRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
