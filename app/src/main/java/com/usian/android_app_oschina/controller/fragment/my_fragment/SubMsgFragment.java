package com.usian.android_app_oschina.controller.fragment.my_fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.http.biz.minebus.ILoadMine;
import com.usian.android_app_oschina.model.http.biz.minebus.LoadMineImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 苏元庆 on 2017/5/26.
 */

public class SubMsgFragment extends BaseFragment {

    @Bind(R.id.submsg_list)
    ListView submsgList;

    private ArrayList<String> list;
    private int flag;
    private ILoadMine iLoadLogin;
    private String uid;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_submsg;
    }

    @Override
    protected void initData() {
        Bundle bundle = this.getArguments();
        if(bundle != null){
            list = bundle.getStringArrayList("content");
            flag = bundle.getInt("flag");
        }
        uid = (String) SPUtils.getParam(App.getContext(), "uid", "");
    }

    @Override
    protected void initView(View view) {
        iLoadLogin = new LoadMineImpl();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        //TODO 接口有问题，未返回任何数据，我也没办法，我也很绝望
        if (list.get(flag).equals("@我")){

            iLoadLogin.getUserMsg(uid, "2", 1+"", new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    LogUtils.e("SubMsg：@我", result);
                    LogUtils.e("SubMsg", uid);
                }

                @Override
                public void onError(String errormsg) {

                }
            });

        }else if (list.get(flag).equals("评论")){
            iLoadLogin.getUserMsg(uid, "3", 1+"", new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    LogUtils.e("SubMsg：评论", result);
                }

                @Override
                public void onError(String errormsg) {

                }
            });
        }else if (list.get(flag).equals("私信")){
            iLoadLogin.getDirMsg(uid, 1 + "", new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    LogUtils.e("SubMsg：私信", result);
                }

                @Override
                public void onError(String errormsg) {

                }
            });


        }



    }

    public static SubMsgFragment newInstance(List<String> contentList, int flag){
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("content", (ArrayList<String>) contentList);
        bundle.putInt("flag", flag);
        SubMsgFragment testFm = new SubMsgFragment();
        testFm.setArguments(bundle);

        return testFm;

    }
}
