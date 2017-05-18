package com.usian.android_app_oschina.model.http.biz.tweetbus;

import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.contact.Urls;
import com.usian.android_app_oschina.model.http.HttpFactory;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/14.
 */

public class LoadTweetImpl implements ILoadTweet{

    //获取最新动弹
    @Override
    public void getLatestTweet(String pageIndex, NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<>();
        params.put("uid", Arguments.LATEST_TWEET_UID+"");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create().doGet(Urls.TWEETURL, params, networkCallback);
    }

    //获取热门动弹
    @Override
    public void getHotTweet(String pageIndex, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", Arguments.LATEST_TWEET_UID+"");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create().doGet(Urls.TWEETURL, params, networkCallback);
    }

    @Override
    public void getMineTweet(String pageIndex, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", Arguments.LATEST_TWEET_UID+"");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create().doGet(Urls.TWEETURL, params, networkCallback);
    }


}
