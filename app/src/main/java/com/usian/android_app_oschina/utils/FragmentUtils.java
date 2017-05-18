package com.usian.android_app_oschina.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.exception.NotFoundContainerException;

/**
 * Created by xingge on 2017/5/12.
 *
 * 用于Fragment切换跳转 Activity跳转Fragment、Fragment跳转Fragment、普通类跳转Fragment
 */
public class FragmentUtils {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private static FragmentUtils turing;
    private int containerViewId;
    private BaseFragment lastFragment;
    private BaseFragment fragment;
    private boolean isDefault = true;


    private FragmentUtils(){
        manager = App.subActivity.getSupportFragmentManager();
    }


    public static FragmentUtils create(){
        if (turing == null){
            synchronized (FragmentUtils.class){
                if(turing == null)
                    turing = new FragmentUtils();
            }
        }
        return turing;
    }

    public FragmentUtils containerViewId(int containerViewId){
        this.containerViewId = containerViewId;
        return this;
    }

    public FragmentUtils start(Class<? extends Fragment> fragmentClass) throws NotFoundContainerException {
        if(containerViewId == 0)
            throw new NotFoundContainerException("Please add containerViewId before invoking start(Class<? extends Fragment> fragmentClass)");
        transaction = manager.beginTransaction();
        String simpleName = fragmentClass.getSimpleName();
        fragment = (BaseFragment) manager.findFragmentByTag(simpleName);
        if(fragment == null){
            try {
                //动态代理 动态创建对象
                fragment = (BaseFragment) fragmentClass.newInstance();
                transaction.add(containerViewId,fragment,simpleName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        setLastFragment(lastFragment);
        return this;
    }

    public FragmentUtils hide(){
        if (lastFragment != null){
            transaction.hide(lastFragment);
        }
        transaction.show(fragment);
        return this;
    }

    public FragmentUtils replace(){
        isDefault = false;
        transaction.replace(containerViewId,fragment,fragment.getClass().getSimpleName());
        return this;
    }

    public FragmentUtils params(Bundle bundle){
        if (bundle != null)
            fragment.setParams(bundle);
        return this;
    }

    public FragmentUtils isBack(boolean isBack){
        if(isBack)
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        return this;
    }

    public Fragment build(){
        if (isDefault) {
            hide();
            isBack(true);
        }
        transaction.commit();
        lastFragment = fragment;
        isDefault = true;
        return fragment;
    }

    public void setLastFragment(BaseFragment lastFragment){
        this.lastFragment = lastFragment;
    }

    public Fragment getLastFragment() {
        return lastFragment;
    }

}
