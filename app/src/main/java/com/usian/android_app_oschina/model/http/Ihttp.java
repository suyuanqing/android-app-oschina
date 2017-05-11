package com.usian.android_app_oschina.model.http;

import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/11.
 * model 网络请求的顶层接口
 */

public interface Ihttp {

    /**
     * get请求
     * @param url 传入的url
     * @param params 传入的参数
     * @param networkCallback
     */
    void doGet(String url, Map<String, String> params, NetworkCallback networkCallback);

    /**
     * post请求
     * @param url 传入的url
     * @param params 传入的参数
     * @param networkCallback
     */
    void dpPost(String url, Map<String, String> params, NetworkCallback networkCallback);

}
