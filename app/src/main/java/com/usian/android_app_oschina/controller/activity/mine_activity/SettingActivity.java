package com.usian.android_app_oschina.controller.activity.mine_activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.kyleduo.switchbutton.SwitchButton;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.contact.ATotalOf;
import com.usian.android_app_oschina.contact.DoubleClickExit;
import com.usian.android_app_oschina.model.http.Cookie.ClearCookiejar;
import com.usian.android_app_oschina.utils.SPUtils;

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
        if (SPUtils.getParam(App.getContext(), "isLogin", "").equals("已登录")){
            settingZhuxiao.setVisibility(View.VISIBLE);
        }else{
            settingZhuxiao.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        titleIconName.setText(R.string.setting);
        tvInfoPinglun.setVisibility(View.GONE);
        ivInfoImg.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {

        settindDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    DoubleClickExit.setAgainBack(true);
                }else{
                    DoubleClickExit.setAgainBack(false);
                }
            }
        });

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

                Intent intent = new Intent(ATotalOf.SENTBROADACTION);
                intent.putExtra("zhuxiao", 0);
                LocalBroadcastManager.getInstance(App.subActivity).sendBroadcast(intent);

                if (SPUtils.getParam(App.getContext(), "isLogin", "").equals("已登录")){
                    ClearableCookieJar clear = new ClearCookiejar();
                    clear.clear();
                    SPUtils.remove(App.getContext(), "isLogin");
                    Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show();
                    settingZhuxiao.setVisibility(View.GONE);
                }else{
                    settingZhuxiao.setVisibility(View.GONE);
                }

                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}
