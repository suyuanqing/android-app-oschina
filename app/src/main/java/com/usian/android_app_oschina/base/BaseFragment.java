package com.usian.android_app_oschina.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.exception.NotFoundContainerException;
import com.usian.android_app_oschina.utils.NetUtils;

import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 初始的Fragment
 */

public abstract class BaseFragment extends Fragment {


    private Bundle params;
    private boolean isFirst = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getLayoutId() != 0){
            return inflater.inflate(getLayoutId(),container,false);
        }else{
            throw new NotFoundContainerException("Fragment布局ID不能为0,currentFragment = " + getClass().getSimpleName());
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        isFirst = true;
        initView(view);
        initData();
        initListener();
    }

    /**
     * 获取布局ID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化控件
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 设置监听
     */
    protected abstract void initListener();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            //当前Fragment被隐藏
            onHidden();
        }else{
            //当前Fragment显示
            onShow();
            if (!NetUtils.isConnected(App.getContext())) {
                Toast.makeText(App.getContext(), R.string.isNet, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            onShow();
            if (!NetUtils.isConnected(App.getContext())) {
                Toast.makeText(App.getContext(), R.string.isNet, Toast.LENGTH_SHORT).show();
            }
        } else {
            //相当于Fragment的onPause
            onHidden();
        }
    }



    public void onShow(){

    }

    public void onHidden(){

    }

    public Bundle getParams() {
        return params;
    }

    /**
     * 设置传过来的参数
     * @param params
     */
    public void setParams(Bundle params) {
        this.params = params;
    }

    //当页面可见时加载数据，仅仅加载一次
    @Override
    public void onResume() {
        super.onResume();
        if (isFirst){
            loadData();
            isFirst = false;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



}
