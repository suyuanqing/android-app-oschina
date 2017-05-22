package com.usian.android_app_oschina.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.utils.NetUtils;

import butterknife.ButterKnife;

public abstract class BaseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        App.activity = this;
        ButterKnife.bind(this);
        if (!NetUtils.isConnected(this)) {
            Toast.makeText(this, R.string.isNet, Toast.LENGTH_SHORT).show();
        }
        initView();
        initData();
        initListener();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetUtils.isConnected(this)) {
            Toast.makeText(this, R.string.isNet, Toast.LENGTH_SHORT).show();
        }

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
