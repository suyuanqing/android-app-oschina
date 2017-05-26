package com.usian.android_app_oschina.model.http.biz.minebus;

import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/20.
 *  业务顶层接口
 */

public interface ILoadMine {

    //用户登录
    void getLoginInfo(String user, String pwd, NetworkCallback networkCallback);

    //获取用户信息
    void getUserInfo(String uid, NetworkCallback networkCallback);

    //获取艾特和评论消息
    void getUserMsg(String uid, String catalog, String index, NetworkCallback networkCallback);

    void getDirMsg(String uid, String index, NetworkCallback networkCallback);

    //获取粉丝列表
    void getFnsList(String uid, String index, String relation, NetworkCallback networkCallback);

    //获取收藏列表
    void getSCList(String uid, String type, String index, NetworkCallback networkCallback);


}