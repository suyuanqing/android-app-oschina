package com.usian.android_app_oschina.model.util;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.model.http.Ihttp;
import com.usian.android_app_oschina.model.http.NetworkCallback;

import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/11.
 * Volley请求的工具类（单例模式-懒汉式）
 */

public class VolleyUtil implements Ihttp {

    private RequestQueue requestQueue;

    private VolleyUtil(){
        requestQueue = Volley.newRequestQueue(App.getContext());
    }

    private static VolleyUtil volleyUtil = null;

    public static synchronized VolleyUtil getInstance(){

        if (volleyUtil == null){
            volleyUtil = new VolleyUtil();
        }
        return volleyUtil;
    }

    @Override
    public void doGet(String url, Map<String, String> params, final NetworkCallback networkCallback) {

        StringBuffer apiurl = new StringBuffer();
        for (String s : params.keySet()){
            apiurl.append("?"+s).append("&"+s).append("&"+s);
        }

        String string = apiurl.toString();
        Log.d("TAG", "doGet: "+string);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, string, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //获取数据成功的回调
                networkCallback.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //获取数据失败的回调
                networkCallback.onError(volleyError.getMessage());
            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public void dpPost(String url, Map<String, String> params, NetworkCallback networkCallback) {

    }
}
