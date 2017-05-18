package com.usian.android_app_oschina.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.exception.NotFoundContainerException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xingge on 2017/5/12.
 *
 * 用于Fragment切换跳转 Activity跳转Fragment、Fragment跳转Fragment、普通类跳转Fragment
 */
public class FragmentBuilder {


    private static FragmentBuilder builder;
    private int containerViewId;
    private BaseFragment lastFragment;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private BaseFragment fragment;
    private String simpleName;
    private boolean isDefault = true;
    private boolean isBack = true;
    private Map<String,Integer> fragments;

    private FragmentBuilder(){

        fragmentManager = App.activity.getSupportFragmentManager();
        fragments = new HashMap<>();
    }

    public static FragmentBuilder getInstance(){
        if(builder == null) {
            synchronized (FragmentBuilder.class) {

                if (builder == null)
                    builder = new FragmentBuilder();
            }
        }

        return builder;
    }

    public FragmentBuilder containerId(int containerViewId){
        this.containerViewId = containerViewId;
        return this;
    }

    public FragmentBuilder start(Class<? extends BaseFragment> fragmentClass){

        if(containerViewId == 0)
            throw new NotFoundContainerException("containerId不能为0");
        transaction = fragmentManager.beginTransaction();
        simpleName = fragmentClass.getSimpleName();

//       进入退出动画
//        transaction.setTransition(TRANSIT_FRAGMENT_OPEN);
//        transaction.setTransition(TRANSIT_FRAGMENT_CLOSE);

        fragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);
        try {
            if(fragment == null){

                    //Java动态代理创建对象
                    fragment = fragmentClass.newInstance();
                    transaction.add(containerViewId,fragment,simpleName);
                    fragments.put(simpleName,containerViewId);

            }else {

                Integer containerId = fragments.get(simpleName);
                if(containerId != containerViewId){
                    fragment = null;
                    //Java动态代理创建对象
                    fragment = fragmentClass.newInstance();
                    transaction.add(containerViewId,fragment,simpleName);
					fragments.put(simpleName, containerId);
                }

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return this;
    }

    //隐藏上一个fragment
    public FragmentBuilder show(){
        if(isBack) {
            Log.d("FragmentBuilder", "lastFragment = "+lastFragment);
            //隐藏上一个fragment
            if (lastFragment != null)
                transaction.hide(lastFragment);

        }
        return this;
    }

    //替换上一个fragment
    public FragmentBuilder replace(){
        isDefault = false;
        transaction.replace(containerViewId,fragment,simpleName);
        return this;
    }

    //传递参数
    public FragmentBuilder params(Bundle bundle){
        if (bundle != null)
            fragment.setParams(bundle);
        return this;
    }

    public FragmentBuilder isBack(boolean isBack){

        this.isBack = isBack;
        if(isBack) {
            //添加fragment到回退栈
            transaction.addToBackStack(simpleName);
        }
        return this;
    }

    public BaseFragment getLastFragment() {
        return lastFragment;
    }

    public void setLastFragment(BaseFragment lastFragment) {
        this.lastFragment = lastFragment;
    }

    public BaseFragment build(){
        if(isDefault){
            show();
        }
        //有bug
        if(isBack) {
            isBack(true);
        }

        Log.d("FragmentBuilder", "fragment = "+fragment);
        //显示当前的Fragment
        transaction.show(fragment);
        transaction.commit();
        if(isBack)
            lastFragment = fragment;
        isDefault = true;
        isBack = true;
        return fragment;
    }
}
