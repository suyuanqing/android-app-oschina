package com.usian.android_app_oschina.utils;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.exception.NotFoundContainerException;

/**
 * Created by 苏元庆 on 2017/5/12.
 */

public class FragmentBuilder {

    private FragmentBuilder(){}

    private static FragmentBuilder fragmentBuilder = null;
    private int containerViewId;
    private BaseFragment lastFragment;

    public static FragmentBuilder getInstance(){
        if (fragmentBuilder == null){

            synchronized (FragmentBuilder.class){

                if (fragmentBuilder == null)
                    fragmentBuilder = new FragmentBuilder();
            }
        }
        return fragmentBuilder;
    }

    public FragmentBuilder containerId(int containerViewId){
        this.containerViewId = containerViewId;
        return this;
    }

    public FragmentBuilder start(Class<? extends BaseFragment> fragmentClass){

        String simpleName = fragmentClass.getSimpleName();
        if (containerViewId == 0)
            throw new NotFoundContainerException("请添加Fragment布局id，"+ simpleName);

        FragmentManager manager = App.activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        BaseFragment baseFragment = (BaseFragment) manager.findFragmentByTag(simpleName);

        if (baseFragment == null){
            try {
                baseFragment = fragmentClass.newInstance();
                transaction.add(containerViewId, baseFragment, simpleName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (lastFragment != null)
            transaction.hide(lastFragment);

        //显示当前的Fragment
        transaction.show(baseFragment);
        transaction.addToBackStack(simpleName);
        lastFragment = baseFragment;
        transaction.commit();

        return this;
    }

    public BaseFragment getLastFragment() {
        return lastFragment;
    }

    public void setLastFragment(BaseFragment lastFragment) {
        this.lastFragment = lastFragment;
    }
}
