package com.usian.android_app_oschina.model.http.biz.minebus;

import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/20.
 *  业务顶层接口
 */

public interface ILoadLogin {

    //用户登录
    void getLoginInfo(String user, String pwd, NetworkCallback networkCallback);

    //获取用户信息
    void getUserInfo(String uid, NetworkCallback networkCallback);


}
