package com.usian.android_app_oschina.model.http;

/**
 * Created by 苏元庆 on 2017/5/11.
 *  自定义接口回调
 */

public interface NetworkCallback {

    void onSuccess(String result);

    void onError(String errormsg);

}
