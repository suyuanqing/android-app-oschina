package com.usian.android_app_oschina.controller.activity.mine_activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.MineSCAdapter;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.model.entity.MineSCModel;
import com.usian.android_app_oschina.model.http.biz.minebus.ILoadMine;
import com.usian.android_app_oschina.model.http.biz.minebus.LoadMineImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MineSCActivity extends BaseActivity {


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
    @Bind(R.id.mine_sc_list)
    ListView mineScList;
    @Bind(R.id.activity_mine_sc)
    RelativeLayout activityMineSc;
    private String uid;
    private ArrayList<MineSCModel.FavoriteBean> data = new ArrayList<>();
    private MineSCAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_sc;
    }

    @Override
    protected void initView() {
        titleIconName.setText("收藏");
        ivInfoImg.setVisibility(View.GONE);
        tvInfoPinglun.setVisibility(View.GONE);

        adapter = new MineSCAdapter(data);
        mineScList.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        uid = (String) SPUtils.getParam(App.getContext(), "uid", "");
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
        iLoadMine.getSCList(uid, 4 + "", 0 + "", new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e("MineSC", result);

                XStream xStream = new XStream();
                xStream.alias("oschina", MineSCModel.class);
                xStream.alias("favorite", MineSCModel.FavoriteBean.class);
                MineSCModel o = (MineSCModel) xStream.fromXML(result);
                List<MineSCModel.FavoriteBean> favorites = o.getFavorites();
                data.addAll(favorites);
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
