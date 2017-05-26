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
public class LoadMineImpl implements ILoadMine {

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

    //获取用户消息
    @Override
    public void getUserMsg(String uid, String catalog, String index, NetworkCallback networkCallback) {

        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("catalog", catalog);
        params.put("pageIndex", index);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create(NetWork.OKHTTP).doGet(Urls.USER_MSG_URL, params, networkCallback);

    }

    @Override
    public void getDirMsg(String uid, String index, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("pageIndex", index);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create(NetWork.OKHTTP).doGet(Urls.DIR_MSG_URL, params, networkCallback);

    }

    //获取粉丝列表
    @Override
    public void getFnsList(String uid, String index, String relation, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("relation", relation);
        params.put("pageIndex", index);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create(NetWork.OKHTTP).doGet(Urls.FNS_LIST_URL, params, networkCallback);

    }

    @Override
    public void getSCList(String uid, String type, String index, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("uid", uid);
        params.put("type", type);
        params.put("pageIndex", index);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create(NetWork.OKHTTP).doGet(Urls.SC_LIST_URL, params, networkCallback);
    }
}
