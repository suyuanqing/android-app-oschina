package com.usian.android_app_oschina.controller.fragment.my_fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.contact.ATotalOf;
import com.usian.android_app_oschina.controller.activity.mine_activity.LoginActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineBlogActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineFnsActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineGZActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineIsActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineMsgActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineSCActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineTuanDuiActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineTweetActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineUserInfo;
import com.usian.android_app_oschina.controller.activity.mine_activity.MineWenDaActivity;
import com.usian.android_app_oschina.controller.activity.mine_activity.SettingActivity;
import com.usian.android_app_oschina.model.entity.UserInfoModel;
import com.usian.android_app_oschina.model.http.biz.minebus.ILoadMine;
import com.usian.android_app_oschina.model.http.biz.minebus.LoadMineImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.NetUtils;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import java.io.File;

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
    @Bind(R.id.lin_mine_lin)
    LinearLayout linMineLin;
    @Bind(R.id.mine_user_info)
    RelativeLayout mineUserInfo;
    @Bind(R.id.mine_home)
    LinearLayout mineHome;
    private AlertDialog qrCodedialog;
    private Object isLogin;
    private String uid;
    private boolean flag;
    private IntentFilter intentFilter;
    private LocalBroadcastManager broadcastManager;
    private BroadcastReceiver mReceiver;
    private FragmentTransaction transaction;
    private MineMsgActivity mineMsgFragment;
    private ILoadMine iLoadLogin;
    private ProgressDialog progressdialog;
    private AlertDialog.Builder uoload;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initData() {
        isLogin = SPUtils.getParam(App.getContext(), "isLogin", "");
        uid = (String) SPUtils.getParam(App.getContext(), "uid", "");
        Log.e("TAG", isLogin + "--------------------");
        Log.e("TAG", uid + "--------------------");
        flag = isLogin.equals("已登录");
        if (flag) {
            mimeUserinfo.setVisibility(View.VISIBLE);
            loadData();
        } else {
            mimeUserinfo.setVisibility(View.GONE);
            tvMineJifen.setVisibility(View.GONE);
            tvMineUsername.setText(R.string.dianji_login);
            Glide.with(App.activity).load(R.mipmap.widget_default_face)
                    .transform(new GlideCircleTransform(App.activity))
                    .into(ivMimaUserimg);
        }
    }

    @Override
    protected void initView(View view) {
    }


    @Override
    protected void initListener() {
        mineUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){

                    startActivity(new Intent(App.activity, MineUserInfo.class));
                }else{
                    startActivity(new Intent(App.activity, LoginActivity.class));
                }
            }
        });
    }


    @Override
    protected void loadData() {
        iLoadLogin = new LoadMineImpl();
        if (flag) {
            iLoadLogin.getUserInfo(uid + "", new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    XStream xStream = new XStream();
                    xStream.alias("oschina", UserInfoModel.class);
                    xStream.alias("user", UserInfoModel.UserBean.class);
                    xStream.alias("notice", UserInfoModel.NoticeBean.class);

                    if (!NetUtils.isConnected(App.activity)) {
                        ThreadUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                mimeUserinfo.setVisibility(View.GONE);
                                Toast.makeText(App.activity, R.string.isNet, Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        UserInfoModel o = (UserInfoModel) xStream.fromXML(result);
                        setMimeUserinfo(o);
                    }

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

                if (flag) {
                    uploadPic();
                } else {

                    startActivity(new Intent(App.activity, LoginActivity.class));
                }

                break;
            case R.id.lin_mime_xiaoxi:

                startActivity(new Intent(App.activity, MineMsgActivity.class));

                break;
            case R.id.lin_mime_blog:

                startActivity(new Intent(App.activity, MineBlogActivity.class));

                break;
            case R.id.lin_mime_wenda:

                startActivity(new Intent(App.activity, MineWenDaActivity.class));

                break;
            case R.id.lin_mime_huodong:

                startActivity(new Intent(App.activity, MineIsActivity.class));

                break;
            case R.id.lin_mime_tuandui:

                startActivity(new Intent(App.activity, MineTuanDuiActivity.class));

                break;
            case R.id.mime_tweet:
                startActivity(new Intent(App.activity, MineTweetActivity.class));
                break;
            case R.id.mime_shoucang:
                startActivity(new Intent(App.activity, MineSCActivity.class));
                break;
            case R.id.mime_guanzhu:
                startActivity(new Intent(App.activity, MineGZActivity.class));
                break;
            case R.id.mime_fensi:
                startActivity(new Intent(App.activity, MineFnsActivity.class));
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

    public void setMimeUserinfo(final UserInfoModel o) {
        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(App.activity).load(o.getUser().getPortrait())
                        .transform(new GlideCircleTransform(App.activity))
                        .error(R.mipmap.ic_default_image)
                        .into(ivMimaUserimg);
                tvMineUsername.setText(o.getUser().getName());
                tvMineJifen.setText("积分 " + o.getUser().getScore());
                mineTweetNum.setText(o.getUser().getFollowers());
                mineFensiNum.setText(o.getUser().getFans());
                mineGuanzhuNum.setText(o.getUser().getFollowers());
                mineShoucangNum.setText(o.getUser().getFavoritecount());
                tvMineJifen.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        intentFilter = new IntentFilter();
        intentFilter.addAction(ATotalOf.SENTBROADACTION);
        //收到广播后所作的操作
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //收到广播后所作的操作
                if (SPUtils.getParam(App.activity, "isLogin", "").equals("已登录")) {
                    initData();
                    LogUtils.e("TAG", "登录广播");
                } else {
                    LogUtils.e("TAG", "注销广播");
                    initData();
                }

            }
        };
        broadcastManager.registerReceiver(mReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(mReceiver);
    }


    //上传头像
    public void uploadPic(){
        progressdialog = new ProgressDialog(App.activity);

        uoload = new AlertDialog.Builder(App.activity);
        View picview = LayoutInflater.from(App.activity).inflate(R.layout.alert_uploadic, null);
        uoload.setView(picview, 0, 0, 0, 0);

        uoload.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();

        TextView genghuan,bigpic;
        genghuan = (TextView) picview.findViewById(R.id.tv_upload_pic);
        bigpic = (TextView) picview.findViewById(R.id.tv_big_pic);

        genghuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                progressdialog.setTitle("请稍等...");
                progressdialog.setMessage("正在上传图片");
                progressdialog.show();

                startActivityForResult(i, 4);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == getActivity().RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            LogUtils.d("UpLoadPic", picturePath);
            cursor.close();

            File file = new File(picturePath);

            if (file != null) {
                iLoadLogin.uploadUserPic(uid, picturePath, new NetworkCallback() {
                    @Override
                    public void onSuccess(final String result) {
                        progressdialog.dismiss();
                        ThreadUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "上传头像成功", Toast.LENGTH_LONG).show();
                                initData();

                            }
                        });
                    }

                    @Override
                    public void onError(final String errormsg) {
                        progressdialog.dismiss();
                        ThreadUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "操作失败", Toast.LENGTH_LONG).show();
                                LogUtils.e("TAG",errormsg);
                            }
                        });
                    }
                });
            } else {
                Toast.makeText(getActivity(), "请选择图片", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
