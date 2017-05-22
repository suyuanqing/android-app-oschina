package com.usian.android_app_oschina.model.http.callback;

import okhttp3.Response;

/**
 * Created by 苏元庆 on 2017/5/22.
 */

public interface OkhttpCallback {

    void onSuccess(Response response);

    void onError(String errormsg);

}
