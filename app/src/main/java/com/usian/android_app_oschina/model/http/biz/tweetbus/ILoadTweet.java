package com.usian.android_app_oschina.model.http.biz.tweetbus;

import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/11.
 * 业务的顶层接口
 */

public interface ILoadTweet {

    //最新动弹
    void getLatestTweet(String pageIndex, NetworkCallback networkCallback);

    //热门动弹
    void getHotTweet(String pageIndex, NetworkCallback networkCallback);

    //我的动弹
    void getMineTweet(String pageIndex, NetworkCallback networkCallback);

    //动弹中用户详情

}
