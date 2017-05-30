package com.usian.android_app_oschina.controller.activity.mine_activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class MineTuanDuiActivity extends BaseActivity {

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
    @Bind(R.id.mine_blog_list)
    ListView mineBlogList;
    @Bind(R.id.activity_mine_blog)
    RelativeLayout activityMineBlog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_blog;
    }

    @Override
    protected void initView() {
        titleIconName.setText("我的团队");
        ivInfoImg.setVisibility(View.GONE);
        tvInfoPinglun.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

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
