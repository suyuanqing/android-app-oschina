package com.usian.android_app_oschina.model.http;

import com.usian.android_app_oschina.model.util.VolleyUtil;

/**
 * Created by 苏元庆 on 2017/5/11.
 * 便于更换网络请求框架
 */

public class HttpFactory {

    private static final int VOLLEY = 0;
    private static final int OKTTP = 1;
    private static final int RETROFIT = 2;
    private static final int TYPE = VOLLEY;

    public static Ihttp create(){
        Ihttp ihttp = null;
        switch (TYPE){
            case VOLLEY:
                ihttp = VolleyUtil.getInstance();
                break;

            case OKTTP:

                break;
        }
        return ihttp;

    }

}
