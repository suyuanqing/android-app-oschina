package com.usian.android_app_oschina;

import android.app.Application;
import android.content.Context;

/**
 * Created by 苏元庆 on 2017/5/9.
 */

public class App extends Application{

    private static Context context;

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
