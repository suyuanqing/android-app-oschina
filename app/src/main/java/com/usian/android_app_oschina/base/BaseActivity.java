package com.usian.android_app_oschina.base;

import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.utils.FragmentBuilder;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

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


    /**
     * 捕获back键 当back键被按下时
     */
    @Override
    public void onBackPressed() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
        String name = entry.getName();
        if("SynthesizeFragment".equals(name) || "StirFragnemt".equals(name)
                || "MineFragment".equals(name) || "FxFragment".equals(name)){
            Process.killProcess(Process.myPid());
            System.exit(0);
        }else {
            manager.popBackStackImmediate();
            String fragmentName = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
            BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
            FragmentBuilder.getInstance().setLastFragment(fragment);
        }

    }

}
