package com.usian.android_app_oschina.model.http.biz;

import com.usian.android_app_oschina.model.http.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/11.
 * 业务的顶层接口
 */

public interface LoadNetNews {

    void getNews(String pageIndex, NetworkCallback networkCallback);

}
