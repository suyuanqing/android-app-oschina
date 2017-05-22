package com.usian.android_app_oschina.model.http.biz.minebus;

import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.contact.NetWork;
import com.usian.android_app_oschina.contact.Urls;
import com.usian.android_app_oschina.model.http.HttpFactory;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/20.
 *  业务的具体实现类
 */
// TODO 登录需要改为okhttp请求
public class LoginImpl implements ILoadLogin {

    @Override
    public void getLoginInfo(String user, String pwd, NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<>();
        params.put("username",user);
        params.put("pwd",pwd);
        params.put("keep_login", Arguments.KEEP_LOGIN);

        HttpFactory.create(NetWork.OKHTTP).doPost(Urls.LOGIN_URL, params, networkCallback);

    }

    @Override
    public void getUserInfo(String uid, NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);

        HttpFactory.create(NetWork.OKHTTP).doGet(Urls.MYINFO_URL, params, networkCallback);

    }
}
