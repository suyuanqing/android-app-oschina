package com.usian.android_app_oschina;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.usian.android_app_oschina.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        welcome();
    }

    public void welcome(){

        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);
                hand.sendEmptyMessage(999);
            }
        }.start();

    }

}
