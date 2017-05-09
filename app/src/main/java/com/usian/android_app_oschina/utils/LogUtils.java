package com.usian.android_app_oschina.utils;

import android.util.Log;

/**
 * Created by 苏元庆 on 2017/5/9.
 */

public class LogUtils {

    private static boolean isDebug = true;

    public static void e(String tag, String msg){

        if(isDebug){
            Log.e(tag,msg);
        }

    }

    public static void d(String tag, String msg){

        if(isDebug){
            Log.d(tag,msg);
        }

    }

    public static void i(String tag, String msg){

        if(isDebug){
            Log.i(tag,msg);
        }

    }

    public static void v(String tag, String msg){

        if(isDebug){
            Log.v(tag,msg);
        }

    }

}
