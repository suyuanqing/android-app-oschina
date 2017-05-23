package com.usian.android_app_oschina.model.http.biz.findbus;

import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/17.
 * 发现的业务接口
 */

public interface ILoadFind {

    //分类
    void getClassify(NetworkCallback networkCallback);

    //二级分类
    void getTwoClassify(String tag, NetworkCallback networkCallback);

    //摇一摇资讯
    void getShakeNews(NetworkCallback networkCallback);

    //线下活动
    void getOffEvent(String index, NetworkCallback networkCallback);

    //活动详情
    void getEventInfo(String id, NetworkCallback networkCallback);

    //开源软件下
    void getSoftwareList(String index, String searchTag, NetworkCallback networkCallback);

}
