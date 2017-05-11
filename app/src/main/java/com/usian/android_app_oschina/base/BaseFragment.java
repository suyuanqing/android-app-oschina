package com.usian.android_app_oschina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 初始的Fragment
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected BaseActivity mActivity;

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getLayoutId() != 0){
            return inflater.inflate(getLayoutId(),null);
        }else{
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initView(view);
        initData(getArguments());
        initListener();
    }

    /**
     * 初始化数据
     * @param bun 接收从别的fragment中传过来的参数
     */
    protected abstract void initData(Bundle bun);

    /**
     * 初始化控件
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 设置监听
     */
    protected abstract void initListener();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
