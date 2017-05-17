package com.usian.android_app_oschina.model.http.biz.findbus;

import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/17.
 * 发现的业务接口
 */

public interface ILoadFind {

    //分类
    void getClassify(NetworkCallback networkCallback);

    //
    void getTwoClassify(String tag, NetworkCallback networkCallback);
}
