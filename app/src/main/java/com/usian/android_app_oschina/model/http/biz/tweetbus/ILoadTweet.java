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
    void getMineTweet(String pageIndex, String uid, NetworkCallback networkCallback);

    //点赞 取消点赞
    void addLike(String uid, String tweetid, String tweetisid, NetworkCallback networkCallback);
    void deleteLike(String uid, String tweetid, String tweetisid, NetworkCallback networkCallback);

}
