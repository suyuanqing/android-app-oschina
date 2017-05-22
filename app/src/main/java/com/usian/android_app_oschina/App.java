package com.usian.android_app_oschina;

import android.app.Application;
import android.content.Context;

import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.base.BaseMainActivity;

/**
 * Created by 苏元庆 on 2017/5/9.
 */

public class App extends Application{

    //获取全局Activity
    public static BaseActivity subActivity;
    public static BaseMainActivity activity;
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

}
