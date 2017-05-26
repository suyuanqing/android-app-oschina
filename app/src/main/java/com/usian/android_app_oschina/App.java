package com.usian.android_app_oschina;

import android.app.Application;
import android.content.Context;

import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.base.BaseMainActivity;

/**
 * Created by 苏元庆 on 2017/5/9.
 */

public class App extends Application{

    //获取全局Activity
    public static BaseActivity subActivity;
    public static BaseMainActivity activity;
    public static BaseFragment fragment;
    public static boolean isLogin;

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }

    /**
     * 获取全局的Context
     * @return
     */
    public static Context getContext(){
        return context;
    }

    //获取一个全局的用户id
//    public static String myUid = (String) SPUtils.getParam(getContext(), "uid", "");

}
