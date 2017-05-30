package com.usian.android_app_oschina.controller.fragment.my_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.DirMsgAdapter;
import com.usian.android_app_oschina.adapter.EitMiAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.entity.DirMsgModel;
import com.usian.android_app_oschina.model.entity.EitMiModel;
import com.usian.android_app_oschina.model.http.biz.minebus.ILoadMine;
import com.usian.android_app_oschina.model.http.biz.minebus.LoadMineImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/26.
 */

public class SubMsgFragment extends BaseFragment {

    @Bind(R.id.submsg_list)
    ListView submsgList;
    @Bind(R.id.image_wu)
    ImageView imageWu;

    private ArrayList<String> list;
    private int flag;
    private ILoadMine iLoadLogin;
    private String uid;
    private ArrayList<DirMsgModel.MessageBean> data = new ArrayList<>();
    private ArrayList<EitMiModel.ActiveBean> eitdata = new ArrayList<>();
    private DirMsgAdapter dirMsgAdapter;
    private EitMiAdapter eitMiAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_submsg;
    }

    @Override
    protected void initData() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            list = bundle.getStringArrayList("content");
            flag = bundle.getInt("flag");
        }
        uid = (String) SPUtils.getParam(App.getContext(), "uid", "");

        if (list.get(flag).equals("@我")) {
            submsgList.setAdapter(eitMiAdapter);
        } else if (list.get(flag).equals("评论")) {

        } else if (list.get(flag).equals("私信")) {
            submsgList.setAdapter(dirMsgAdapter);
        }

    }

    @Override
    protected void initView(View view) {
        iLoadLogin = new LoadMineImpl();
        dirMsgAdapter = new DirMsgAdapter(data);
        eitMiAdapter = new EitMiAdapter(eitdata);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        if (list.get(flag).equals("@我")) {

            iLoadLogin.getUserMsg(uid, "2", 0 + "", new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    iLoadLogin.eitMine(uid, 2+"",0 + "", new NetworkCallback() {
                        @Override
                        public void onSuccess(String result) {
                            XStream xStream = new XStream();
                            xStream.alias("oschina", EitMiModel.class);
                            xStream.alias("active", EitMiModel.ActiveBean.class);
                            EitMiModel o = (EitMiModel) xStream.fromXML(result);
                            List<EitMiModel.ActiveBean> activies = o.getActivies();
                            eitdata.addAll(activies);
                            ThreadUtils.runOnUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    eitMiAdapter.notifyDataSetChanged();
                                    if (eitdata.size() != 0) {
                                        imageWu.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }

                        @Override
                        public void onError(String errormsg) {

                        }
                    });
                }

                @Override
                public void onError(String errormsg) {

                }
            });

        } else if (list.get(flag).equals("评论")) {
            iLoadLogin.getUserMsg(uid, "3", 0 + "", new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    LogUtils.e("SubMsg：评论", result);
                }

                @Override
                public void onError(String errormsg) {

                }
            });
        } else if (list.get(flag).equals("私信")) {
            iLoadLogin.getDirMsg(uid, 0 + "", new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
//                    LogUtils.e("SubMsg：私信", result);
                    XStream xStream = new XStream();
                    xStream.alias("oschina", DirMsgModel.class);
                    xStream.alias("message", DirMsgModel.MessageBean.class);
                    DirMsgModel o = (DirMsgModel) xStream.fromXML(result);
                    List<DirMsgModel.MessageBean> messages = o.getMessages();
                    data.addAll(messages);
                    ThreadUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            dirMsgAdapter.notifyDataSetChanged();
                            if (data.size() != 0) {
                                imageWu.setVisibility(View.GONE);
                            }
                        }
                    });
                }

                @Override
                public void onError(String errormsg) {

                }
            });


        }


    }

    public static SubMsgFragment newInstance(List<String> contentList, int flag) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("content", (ArrayList<String>) contentList);
        bundle.putInt("flag", flag);
        SubMsgFragment testFm = new SubMsgFragment();
        testFm.setArguments(bundle);

        return testFm;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
