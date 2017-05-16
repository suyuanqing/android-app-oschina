package com.usian.android_app_oschina.model.http.callback;

import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/11.
 *  自定义接口回调
 */

public interface InfoIdCallback {

    void onSuccess(Map<String, String> result);

    void onError(String errormsg);

}
