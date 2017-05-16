package com.usian.android_app_oschina.model.http;

import com.usian.android_app_oschina.model.http.callback.InfoIdCallback;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

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
    void doPost(String url, Map<String, String> params, NetworkCallback networkCallback);

    /**
     * 解析自定义的XMlResquest(适用于Volley解析)
     * @param url
     * @param params
     * @param infoIdCallback (因为是pull解析，所以用另一个callback)
     */
    void doXml(String url, Map<String, String> params, InfoIdCallback infoIdCallback);

}
