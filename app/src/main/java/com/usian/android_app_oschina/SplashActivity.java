package com.usian.android_app_oschina;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.usian.android_app_oschina.base.BaseActivity;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.start_img)
    ImageView startImg;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;

    private Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        new Thread(){
            @Override
            public void run() {
//                TODO 发布项目时修改闪屏页面停留时间
                SystemClock.sleep(0);
                hand.sendEmptyMessage(999);
            }
        }.start();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

}
