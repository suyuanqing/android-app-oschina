package com.usian.android_app_oschina.model.http.biz.searchbus;

import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public interface ILoadSearch {

    //搜索
    void getSearchResult(String catalog, String content, String pageIndex, NetworkCallback networkCallback);

    void getLookPerson(String name, NetworkCallback networkCallback);
}
