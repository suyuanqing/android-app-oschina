package com.usian.android_app_oschina.model.util;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.costom.XMLRequest;
import com.usian.android_app_oschina.model.http.Ihttp;
import com.usian.android_app_oschina.model.http.callback.InfoIdCallback;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
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

    //get请求
    @Override
    public void doGet(String url, Map<String, String> params, final NetworkCallback networkCallback) {

        StringBuffer apiurl = new StringBuffer();
        apiurl.append(url+"?");
        int index = 0;
        for (String s : params.keySet()){
            index++;
            apiurl.append(s+"="+params.get(s));
            if (index < params.size())
                apiurl.append("&");
        }

        String string = apiurl.toString();
        LogUtils.d("TAG", "doGet: "+string);

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
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<>();
                headerMap.put("Cookie", Arguments.MY_COOKIE);
                return headerMap;
            }
        };

        stringRequest.setTag("GET");
        requestQueue.add(stringRequest);
    }


    //POST请求
    @Override
    public void doPost(final String url, final Map<String, String> params, final NetworkCallback networkCallback) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogUtils.e("你好啊",url);
                networkCallback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                networkCallback.onError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    //get请求xml数据
    @Override
    public void doXml(String url, Map<String, String> params, final InfoIdCallback infoIdCallback) {
        StringBuffer apiurl = new StringBuffer();
        apiurl.append(url+"?");
        int index = 0;
        for (String s : params.keySet()){
            index++;
            apiurl.append(s+"="+params.get(s));
            if (index < params.size())
                apiurl.append("&");
        }

        String string = apiurl.toString();
        LogUtils.d("TAG", "doXml: "+string);
        XMLRequest xmlRequest = new XMLRequest(Request.Method.GET, string, new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser response) {
                try {
                    int eventType = response.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                String nodeName = response.getName();
                                Map<String, String> params = new HashMap<>();

                                if ("url".equals(nodeName)) {
                                    String url = response.nextText();
                                    params.put("id", url);
                                    infoIdCallback.onSuccess(params);
                                }else if ("commentCount".equals(nodeName)){
                                    String commentCount = response.nextText();
                                    params.put("commentCount", commentCount);
                                    infoIdCallback.onSuccess(params);
                                }
                                break;
                        }
                        eventType = response.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
             }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                infoIdCallback.onError(volleyError.getMessage());
            }
        });
        requestQueue.add(xmlRequest);
    }


}
