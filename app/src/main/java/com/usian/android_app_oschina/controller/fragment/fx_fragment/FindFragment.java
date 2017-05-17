package com.usian.android_app_oschina.controller.fragment.fx_fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.controller.activity.OSSActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 苏元庆 on 2017/5/11.
 */

public class FindFragment extends BaseFragment {

    @Bind(R.id.fa_mytj)
    LinearLayout faMytj;
    @Bind(R.id.fa_kyrj)
    LinearLayout faKyrj;
    @Bind(R.id.fa_sys)
    LinearLayout faSys;
    @Bind(R.id.fa_yyy)
    LinearLayout faYyy;
    @Bind(R.id.fa_fj)
    LinearLayout faFj;
    @Bind(R.id.fa_xxhd)
    LinearLayout faXxhd;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fx;
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

    @OnClick({R.id.fa_mytj, R.id.fa_kyrj, R.id.fa_sys, R.id.fa_yyy, R.id.fa_fj, R.id.fa_xxhd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fa_mytj:
                break;
            case R.id.fa_kyrj:

                startActivity(new Intent(App.activity, OSSActivity.class));

                break;
            case R.id.fa_sys:
                break;
            case R.id.fa_yyy:
                break;
            case R.id.fa_fj:
                break;
            case R.id.fa_xxhd:
                break;
        }
    }
}
