package com.usian.android_app_oschina.controller.activity.mine_activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.contact.ATotalOf;
import com.usian.android_app_oschina.model.entity.LoginModel;
import com.usian.android_app_oschina.model.http.biz.minebus.ILoadLogin;
import com.usian.android_app_oschina.model.http.biz.minebus.LoginImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements NetworkCallback{

    @Bind(R.id.iv_login_logo)
    ImageView ivLoginLogo;
    @Bind(R.id.iv_login_username_icon)
    ImageView ivLoginUsernameIcon;
    @Bind(R.id.et_login_username)
    EditText etLoginUsername;
    @Bind(R.id.iv_login_username_del)
    ImageView ivLoginUsernameDel;
    @Bind(R.id.ll_login_username)
    LinearLayout llLoginUsername;
    @Bind(R.id.iv_login_pwd_icon)
    ImageView ivLoginPwdIcon;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.iv_login_pwd_del)
    ImageView ivLoginPwdDel;
    @Bind(R.id.ll_login_pwd)
    LinearLayout llLoginPwd;
    @Bind(R.id.iv_login_hold_pwd)
    ImageView ivLoginHoldPwd;
    @Bind(R.id.tv_login_forget_pwd)
    TextView tvLoginForgetPwd;
    @Bind(R.id.bt_login_submit)
    Button btLoginSubmit;
    @Bind(R.id.lay_login_container)
    LinearLayout layLoginContainer;
    @Bind(R.id.activity_login)
    RelativeLayout activityLogin;

    private String username;
    private String password;
    private ProgressDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

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

    @OnClick({R.id.tv_login_forget_pwd, R.id.bt_login_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_forget_pwd:
                break;
            case R.id.bt_login_submit:
                dialog = new ProgressDialog(this);
                dialog.setMessage("登录中...");
                dialog.show();

                username = etLoginUsername.getText().toString().trim();
                password = etLoginPwd.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    dialog.dismiss();
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    dialog.dismiss();
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                ILoadLogin iloadlogin = new LoginImpl();
                iloadlogin.getLoginInfo(username, password, this);


                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        dialog.dismiss();
        XStream xStream = new XStream();
        xStream.alias("oschina", LoginModel.class);
        LoginModel o = (LoginModel) xStream.fromXML(result);

        if (o.getResult().getErrorCode().equals("1")){

            Intent intent = new Intent(ATotalOf.SENTBROADACTION);
            intent.putExtra("userlogin", 1);
            LocalBroadcastManager.getInstance(App.subActivity).sendBroadcast(intent);

            LogUtils.e("Login", username+"-----"+password);
           ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        });
            SPUtils.putParam(App.getContext(), "username", username);
            SPUtils.putParam(App.getContext(), "password", password);
            SPUtils.putParam(App.getContext(), "uid", o.getUser().getUid());
            SPUtils.putParam(App.getContext(), "isLogin", "已登录");
            finish();
        }else{
            LogUtils.e("Login", username+"-----"+password);
            ThreadUtils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this, "用户名密码不正确", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    public void onError(String errormsg) {

    }


}
