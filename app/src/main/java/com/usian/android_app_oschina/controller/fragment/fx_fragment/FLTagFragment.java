package com.usian.android_app_oschina.controller.fragment.fx_fragment;

import android.view.View;
import android.widget.ListView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/17.
 */

public class FLTagFragment extends BaseFragment {

    @Bind(R.id.find_oss_fy)
    ListView findOssFy;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ossfy;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

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
