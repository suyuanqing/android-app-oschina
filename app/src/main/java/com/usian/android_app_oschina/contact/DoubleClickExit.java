package com.usian.android_app_oschina.contact;

/**
 * Created by 苏元庆 on 2017/5/22.
 */

public class DoubleClickExit {

    private static boolean againBack = true;
    private static boolean isBack = true;

    public static boolean isAgainBack() {
        return againBack;
    }

    public static void setAgainBack(boolean againBack) {
        DoubleClickExit.againBack = againBack;
    }

    public static boolean isBack() {
        return isBack;
    }

    public static void setIsBack(boolean isBack) {
        DoubleClickExit.isBack = isBack;
    }
}