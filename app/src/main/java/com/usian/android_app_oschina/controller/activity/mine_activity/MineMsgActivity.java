package com.usian.android_app_oschina.controller.activity.mine_activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.ZHPagerAdapter;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.controller.fragment.my_fragment.SubMsgFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 苏元庆 on 2017/5/26.
 */

public class MineMsgActivity extends BaseActivity {


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
    @Bind(R.id.mine_msg_tab)
    TabLayout mineMsgTab;
    @Bind(R.id.mine_msg_pager)
    ViewPager mineMsgPager;
    private List<String> titlenames = new ArrayList<>();
    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private ZHPagerAdapter zhPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_msg;
    }

    @Override
    protected void initData() {
        titleIconName.setText("消息中心");
        ivInfoImg.setVisibility(View.GONE);
        tvInfoPinglun.setVisibility(View.GONE);
    }

    @Override
    protected void initView() {
        getTitlename();

        mineMsgPager.setOffscreenPageLimit(3);
        zhPagerAdapter = new ZHPagerAdapter(getSupportFragmentManager(), fragments, titlenames);

        mineMsgTab.setupWithViewPager(mineMsgPager);
        mineMsgTab.setTabMode(TabLayout.MODE_FIXED);
        mineMsgPager.setAdapter(zhPagerAdapter);
    }

    private void getTitlename() {
        titlenames.add(0, "@我");
        titlenames.add(1, "评论");
        titlenames.add(2, "私信");

        for (int i = 0; i < titlenames.size(); i++) {
            SubMsgFragment testFm = SubMsgFragment.newInstance(titlenames, i);
            fragments.add(testFm);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }

}
