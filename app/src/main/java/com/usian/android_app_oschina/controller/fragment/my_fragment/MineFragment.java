package com.usian.android_app_oschina.controller.fragment.my_fragment;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.controller.activity.mine_activity.LoginActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.SettingActivity;
import com.usian.android_app_oschina.model.entity.UserInfoModel;
import com.usian.android_app_oschina.model.http.biz.minebus.ILoadLogin;
import com.usian.android_app_oschina.model.http.biz.minebus.LoginImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.SPUtils;

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
    @Bind(R.id.iv_mima_userimg)
    ImageView ivMimaUserimg;
    @Bind(R.id.tv_mine_username)
    TextView tvMineUsername;
    @Bind(R.id.tv_mine_jifen)
    TextView tvMineJifen;
    @Bind(R.id.mine_tweet_num)
    TextView mineTweetNum;
    @Bind(R.id.mime_tweet)
    LinearLayout mimeTweet;
    @Bind(R.id.mine_shoucang_num)
    TextView mineShoucangNum;
    @Bind(R.id.mime_shoucang)
    LinearLayout mimeShoucang;
    @Bind(R.id.mine_guanzhu_num)
    TextView mineGuanzhuNum;
    @Bind(R.id.mime_guanzhu)
    LinearLayout mimeGuanzhu;
    @Bind(R.id.mine_fensi_num)
    TextView mineFensiNum;
    @Bind(R.id.mime_fensi)
    LinearLayout mimeFensi;
    @Bind(R.id.mime_userinfo)
    LinearLayout mimeUserinfo;
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
    private Object uid;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        uid = SPUtils.getParam(App.getContext(), "uid", "");
        Log.e("TAG", uid+"--------------------");
        if (uid != null) {

            mimeUserinfo.setVisibility(View.VISIBLE);
        } else {
            mimeUserinfo.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        ILoadLogin iLoadLogin = new LoginImpl();
        if (uid != null) {
            iLoadLogin.getUserInfo(uid + "", new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    XStream xStream = new XStream();
                    xStream.alias("oschina", UserInfoModel.class);
                    xStream.alias("user", UserInfoModel.UserBean.class);
                    xStream.alias("notice", UserInfoModel.NoticeBean.class);


                    UserInfoModel o = (UserInfoModel) xStream.fromXML(result);
                    setMimeUserinfo(o);

                }

                @Override
                public void onError(String errormsg) {

                }
            });
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_mine_shezhi, R.id.iv_mine_erweima, R.id.iv_mima_userimg, R.id.lin_mime_xiaoxi, R.id.lin_mime_blog, R.id.lin_mime_wenda, R.id.lin_mime_huodong, R.id.lin_mime_tuandui, R.id.mime_tweet, R.id.mime_shoucang, R.id.mime_guanzhu, R.id.mime_fensi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mine_shezhi:

                startActivity(new Intent(App.subActivity, SettingActivity.class));

                break;
            case R.id.iv_mine_erweima:
                showQrCode();
                break;
            case R.id.iv_mima_userimg:

                if (uid != null){
                    Toast.makeText(App.activity, "你好", Toast.LENGTH_SHORT).show();
                }else{

                    startActivity(new Intent(App.subActivity, LoginActivity.class));
                }

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
            case R.id.mime_tweet:
                break;
            case R.id.mime_shoucang:
                break;
            case R.id.mime_guanzhu:
                break;
            case R.id.mime_fensi:
                break;
        }
    }

    public void showQrCode() {
        qrCodedialog = new AlertDialog.Builder(App.activity).create();
        View qrview = LayoutInflater.from(App.activity).inflate(R.layout.qr_code, null);
        qrCodedialog.setView(qrview, 0, 0, 0, 0);
        qrCodedialog.show();
        WindowManager.LayoutParams params = qrCodedialog.getWindow().getAttributes();
        params.width = 400;
        params.height = 500;
        qrCodedialog.getWindow().setAttributes(params);

    }



    public void setMimeUserinfo(UserInfoModel o){
        Glide.with(App.activity).load(o.getUser().getPortrait())
                .transform(new GlideCircleTransform(App.activity))
                .into(ivMimaUserimg);
        tvMineUsername.setText(o.getUser().getName());
        tvMineJifen.setText("积分 "+o.getUser().getScore());
        mineTweetNum.setText(o.getUser().getScore());
        mineFensiNum.setText(o.getUser().getFans());
        mineGuanzhuNum.setText(o.getUser().getFollowers());
        mineShoucangNum.setText("0");

    }
}
