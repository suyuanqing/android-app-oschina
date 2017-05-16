package com.usian.android_app_oschina.model.http.biz.newsbus;

import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.contact.Urls;
import com.usian.android_app_oschina.model.http.HttpFactory;
import com.usian.android_app_oschina.model.http.callback.InfoIdCallback;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/11.
 * 业务的具体实现类
 */

public class LoadNewsImpl implements ILoadNetNews {
    //开源博客
    @Override
    public void getNews(String pageIndex, NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("catalog", Arguments.CATALOG1+"");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create().doGet(Urls.NEWSURL,params,networkCallback);

    }

    //推荐博客
    @Override
    public void getRecommBlog(String pageIndex, NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("type", Arguments.RECOMMENDBLOG+"");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create().doGet(Urls.RECOMMBLOG,params,networkCallback);
    }

    //热门资讯
    @Override
    public void getHotNews(String pageIndex, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("catalog", Arguments.CATALOG4+"");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");
        params.put("show", Arguments.SHOW);

        HttpFactory.create().doGet(Urls.HOTNEWS,params,networkCallback);
    }

    //最新博客
    @Override
    public void getLatestBlog(String pageIndex, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", Arguments.LATESTBLOG);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create().doGet(Urls.LATESTBLOG,params,networkCallback);
    }

    //获取新闻id
    @Override
    public void getNewsId(String id, InfoIdCallback infoIdCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("id",id);

        HttpFactory.create().doXml(Urls.NEWS_ID_URL, params, infoIdCallback);
    }
}
