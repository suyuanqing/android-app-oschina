package com.usian.android_app_oschina.model.util;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.usian.android_app_oschina.model.http.Cookie.ClearCookiejar;
import com.usian.android_app_oschina.model.http.Ihttp;
import com.usian.android_app_oschina.model.http.callback.InfoIdCallback;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 苏元庆 on 2017/5/22.
 */

public class OkhttpUtils implements Ihttp{


    private OkHttpClient.Builder okHttpBuild;
    private OkHttpClient okHttpClient;

    private OkhttpUtils(){
        okHttpBuild = new OkHttpClient.Builder();
    }

    private static OkhttpUtils okhttpUtils = null;

    public static synchronized OkhttpUtils getInstance(){

        if (okhttpUtils == null){
            okhttpUtils = new OkhttpUtils();
        }
        return okhttpUtils;
    }


    @Override
    public void doGet(String url, Map<String, String> params, final NetworkCallback networkCallback) {

        ClearableCookieJar cookieJar = new ClearCookiejar();

        okHttpBuild.cookieJar(cookieJar);
        okHttpClient = okHttpBuild.build();

        StringBuffer apiurl = new StringBuffer();

        apiurl.append(url+"?");
        int index = 0;
        for (String s : params.keySet()){
            index++;
            apiurl.append(s+"="+params.get(s));
            if (index < params.size()) {
                apiurl.append("&");
            }
        }

        String string = apiurl.toString();
        LogUtils.d("TAG", "doGet: "+string);


        Request request = new Request.Builder().url(string).get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                networkCallback.onSuccess(e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                networkCallback.onSuccess(response.body().string());
            }
        });


    }

    @Override
    public void doPost(String url, Map<String, String> params, final NetworkCallback networkCallback) {

        FormBody.Builder builder = new FormBody.Builder();

        ClearableCookieJar cookieJar = new ClearCookiejar();

        okHttpBuild.cookieJar(cookieJar);
        okHttpClient = okHttpBuild.build();

        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
            LogUtils.e("TAG", key+"------------"+params.get(key));
        }

        final Request request = new Request.Builder().url(url).post(builder.build()).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                networkCallback.onSuccess(e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                networkCallback.onSuccess(response.body().string());
            }
        });



    }

    //okhttp 不需要这个
    @Override
    public void doXml(String url, Map<String, String> params, InfoIdCallback infoIdCallback) {

    }
}
