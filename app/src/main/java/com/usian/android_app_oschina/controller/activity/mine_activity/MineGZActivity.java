package com.usian.android_app_oschina.controller.activity.mine_activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.FnsOrGzAdapter;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.model.entity.FnsOrGZModel;
import com.usian.android_app_oschina.model.http.biz.minebus.ILoadMine;
import com.usian.android_app_oschina.model.http.biz.minebus.LoadMineImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MineGZActivity extends BaseActivity {

    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_icon_name)
    TextView titleIconName;
    @Bind(R.id.iv_info_img)
    ImageView ivInfoImg;
    @Bind(R.id.tv_info_pinglun)
    TextView tvInfoPinglun;
    @Bind(R.id.title_icon_toolbar)
    LinearLayout titleIconToolbar;
    @Bind(R.id.mine_fns_list)
    ListView mineFnsList;
    @Bind(R.id.activity_mine_fns)
    LinearLayout activityMineFns;

    private String uid;
    private ArrayList<FnsOrGZModel.FriendBean> data = new ArrayList<>();
    private FnsOrGzAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_fns;
    }

    @Override
    protected void initView() {
        titleIconName.setText("关注列表");
        ivInfoImg.setVisibility(View.GONE);
        tvInfoPinglun.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        uid = (String) SPUtils.getParam(App.getContext(), "uid", "");

        adapter = new FnsOrGzAdapter(data);
        mineFnsList.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void loadData() {
        ILoadMine iLoadMine = new LoadMineImpl();
        iLoadMine.getFnsList(uid, 0 + "", Arguments.MINE_GZ, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
//                LogUtils.e("MineGZActivity", result);
                XStream xStream = new XStream();
                xStream.alias("oschina", FnsOrGZModel.class);
                xStream.alias("friend", FnsOrGZModel.FriendBean.class);

                FnsOrGZModel o = (FnsOrGZModel) xStream.fromXML(result);
                List<FnsOrGZModel.FriendBean> friends = o.getFriends();
                data.addAll(friends);

                ThreadUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onError(String errormsg) {

            }
        });
    }


}
