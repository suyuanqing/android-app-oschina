package com.usian.android_app_oschina.controller.fragment.my_fragment;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.controller.activity.LoginActivity;
import com.usian.android_app_oschina.controller.activity.SettingActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 苏元庆 on 2017/5/11.
 * 我的Fragment页面
 */

public class MineFragment extends BaseFragment {

    @Bind(R.id.iv_mine_shezhi)
    ImageView ivMineShezhi;
    @Bind(R.id.iv_mine_erweima)
    ImageView ivMineErweima;
    @Bind(R.id.iv_mima_login)
    ImageView ivMimaLogin;
    @Bind(R.id.tv_mine_tishi)
    TextView tvMineTishi;
    @Bind(R.id.lin_mime_xiaoxi)
    LinearLayout linMimeXiaoxi;
    @Bind(R.id.lin_mime_blog)
    LinearLayout linMimeBlog;
    @Bind(R.id.lin_mime_wenda)
    LinearLayout linMimeWenda;
    @Bind(R.id.lin_mime_huodong)
    LinearLayout linMimeHuodong;
    @Bind(R.id.lin_mime_tuandui)
    LinearLayout linMimeTuandui;

    private AlertDialog qrCodedialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_mine_shezhi, R.id.iv_mine_erweima, R.id.iv_mima_login, R.id.lin_mime_xiaoxi, R.id.lin_mime_blog, R.id.lin_mime_wenda, R.id.lin_mime_huodong, R.id.lin_mime_tuandui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_shezhi:

                startActivity(new Intent(App.subActivity, SettingActivity.class));

                break;
            case R.id.iv_mine_erweima:
                showQrCode();
                break;
            case R.id.iv_mima_login:

                startActivity(new Intent(App.subActivity, LoginActivity.class));

                break;
            case R.id.lin_mime_xiaoxi:
                break;
            case R.id.lin_mime_blog:
                break;
            case R.id.lin_mime_wenda:
                break;
            case R.id.lin_mime_huodong:
                break;
            case R.id.lin_mime_tuandui:
                break;
        }
    }

    public void showQrCode(){
        qrCodedialog = new AlertDialog.Builder(App.activity).create();
        View qrview = LayoutInflater.from(App.activity).inflate(R.layout.qr_code, null);
        qrCodedialog.setView(qrview, 0, 0, 0, 0);
        qrCodedialog.show();
        WindowManager.LayoutParams params = qrCodedialog.getWindow().getAttributes();
        params.width = 400;
        params.height = 500;
        qrCodedialog.getWindow().setAttributes(params);

    }

}
