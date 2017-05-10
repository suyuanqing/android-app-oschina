package com.usian.android_app_oschina.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by 苏元庆 on 2017/5/9.
 */

public class ThreadUtils {

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void runOnUIThread(Runnable task){
        sHandler.post(task);
    }

    public static void runOnSubThread(Runnable task){
        new Thread(task).start();
    }

}
