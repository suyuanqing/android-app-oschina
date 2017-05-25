package com.usian.android_app_oschina.model.http.biz.searchbus;

import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.contact.NetWork;
import com.usian.android_app_oschina.contact.Urls;
import com.usian.android_app_oschina.model.http.HttpFactory;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public class LoadSearchImpl implements ILoadSearch{

    @Override
    public void getSearchResult(String catalog, String content, String pageIndex, NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<>();
        params.put("catalog", catalog);
        params.put("content", content);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create(NetWork.VOLLEY).doGet(Urls.SEARCH_URL, params, networkCallback);
    }

    @Override
    public void getLookPerson(String name, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);

        HttpFactory.create(NetWork.VOLLEY).doGet(Urls.LOOK_PERSON_URL, params, networkCallback);
    }


}
