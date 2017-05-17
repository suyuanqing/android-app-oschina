package com.usian.android_app_oschina.model.http.biz.findbus;

import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.contact.Urls;
import com.usian.android_app_oschina.model.http.HttpFactory;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/17.
 * 发现的业务实现类
 */

public class LoadFindImpl implements ILoadFind{

    //分类
    @Override
    public void getClassify(NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<>();
        params.put("type", Arguments.FIND_OSS_TYPE);

        HttpFactory.create().doGet(Urls.FIND_OSS_FL, params, networkCallback);

    }

    //二级列表分类
    @Override
    public void getTwoClassify(String tag, NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<>();
        params.put("tag", tag);

        HttpFactory.create().doGet(Urls.FIND_OSS_FL, params, networkCallback);

    }
}
