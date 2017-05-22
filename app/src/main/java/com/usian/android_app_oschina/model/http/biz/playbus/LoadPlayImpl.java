package com.usian.android_app_oschina.model.http.biz.playbus;

import com.usian.android_app_oschina.contact.NetWork;
import com.usian.android_app_oschina.contact.Urls;
import com.usian.android_app_oschina.model.http.HttpFactory;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/22.
 */

public class LoadPlayImpl implements ILoadPlay{

    //发表动弹
    @Override
    public void pubTweet(String uid, String msg, NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("msg", msg);

        HttpFactory.create(NetWork.OKHTTP).doPost(Urls.PUBTWEET_URL, params, networkCallback);
    }
}
