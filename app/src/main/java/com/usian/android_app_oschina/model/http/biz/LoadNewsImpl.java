package com.usian.android_app_oschina.model.http.biz;

import com.usian.android_app_oschina.model.concall.Arguments;
import com.usian.android_app_oschina.model.concall.Urls;
import com.usian.android_app_oschina.model.http.HttpFactory;
import com.usian.android_app_oschina.model.http.NetworkCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/11.
 * 业务的具体实现类
 */

public class LoadNewsImpl implements LoadNetNews {
    @Override
    public void getNews(String pageIndex, NetworkCallback net) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("catalog", Arguments.CATALOG+"");
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create().doGet(Urls.NEWSURL,params,net);

    }
}
