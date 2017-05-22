package com.usian.android_app_oschina.model.http.biz.playbus;

import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/22.
 */

public interface ILoadPlay {

    void pubTweet(String uid, String msg, NetworkCallback networkCallback);

}
