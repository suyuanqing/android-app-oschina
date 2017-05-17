package com.usian.android_app_oschina.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.usian.android_app_oschina.App;

import butterknife.ButterKnife;

public abstract class BaseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        App.activity = this;
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
        loadData();
    }

    //加载布局ID
    protected abstract int getLayoutId();

    //初始化view
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    //初始化监听
    protected abstract void initListener();

    //加载数据
    protected abstract void loadData();

}
