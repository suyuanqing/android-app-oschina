package com.usian.android_app_oschina.controller.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

//TODO 设置很多功能还未实现
public class SettingActivity extends BaseActivity {

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
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.settind_qlhc)
    LinearLayout settindQlhc;
    @Bind(R.id.settind_default)
    SwitchButton settindDefault;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.setting_yijian)
    LinearLayout settingYijian;
    @Bind(R.id.setting_guanyu)
    LinearLayout settingGuanyu;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.setting_update)
    LinearLayout settingUpdate;
    @Bind(R.id.setting_zhuxiao)
    LinearLayout settingZhuxiao;
    @Bind(R.id.activity_setting)
    LinearLayout activitySetting;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        titleIconName.setText(R.string.setting);
        tvInfoPinglun.setVisibility(View.GONE);
        ivInfoImg.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.settind_qlhc, R.id.setting_yijian, R.id.setting_guanyu, R.id.setting_update, R.id.setting_zhuxiao, R.id.title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.settind_qlhc:
                break;
            case R.id.setting_yijian:
                break;
            case R.id.setting_guanyu:
                break;
            case R.id.setting_update:
                break;
            case R.id.setting_zhuxiao:
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}
