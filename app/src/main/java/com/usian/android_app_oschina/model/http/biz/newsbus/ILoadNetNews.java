package com.usian.android_app_oschina.model.http.biz.newsbus;

import com.usian.android_app_oschina.model.http.callback.InfoIdCallback;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/11.
 * 业务的顶层接口
 */

public interface ILoadNetNews {

    //请求新闻
    void getNews(String pageIndex, NetworkCallback networkCallback);

    //请求推荐博客
    void getRecommBlog(String pageIndex, NetworkCallback networkCallback);

    //请求热门咨询
    void getHotNews(String pageIndex, NetworkCallback networkCallback);

    //请求最新博客
    void getLatestBlog(String pageIndex, NetworkCallback networkCallback);

    //请求新闻详情ID
    void getNewsId(String id, InfoIdCallback infoIdCallback);

    //请求博客详情ID
    void getBlogId(String id, InfoIdCallback infoIdCallback);

}
