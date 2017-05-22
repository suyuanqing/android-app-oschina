package com.usian.android_app_oschina.controller.activity.play_activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.contact.ATotalOf;
import com.usian.android_app_oschina.model.entity.PubTweetModel;
import com.usian.android_app_oschina.model.http.biz.playbus.ILoadPlay;
import com.usian.android_app_oschina.model.http.biz.playbus.LoadPlayImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class PlayActivity extends BaseActivity implements NetworkCallback{

    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_icon_name)
    TextView titleIconName;
    @Bind(R.id.send_tweet)
    TextView sendTweet;
    @Bind(R.id.title_icon_toolbar)
    LinearLayout titleIconToolbar;
    @Bind(R.id.play_editweet)
    EditText playEditweet;
    @Bind(R.id.play_image)
    ImageView playImage;
    @Bind(R.id.play_aite)
    ImageView playAite;
    @Bind(R.id.play_huati)
    ImageView playHuati;
    @Bind(R.id.play_em)
    ImageView playEm;
    @Bind(R.id.activity_play)
    LinearLayout activityPlay;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_play;
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


    @OnClick({R.id.title_back, R.id.send_tweet, R.id.play_image, R.id.play_aite, R.id.play_huati, R.id.play_em})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.send_tweet:
                String sendMag = playEditweet.getText().toString().trim();
                if (TextUtils.isEmpty(sendMag)) {
                    Toast.makeText(this, "发表内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(PlayActivity.this, "正在发表动弹", Toast.LENGTH_SHORT).show();
                pubTweet(sendMag);
                break;
            case R.id.play_image:
                break;
            case R.id.play_aite:
                break;
            case R.id.play_huati:
                break;
            case R.id.play_em:
                break;
        }
    }

    public void pubTweet(String msg){

        String uid = (String) SPUtils.getParam(App.subActivity, "uid", "");
        ILoadPlay iLoadPlay = new LoadPlayImpl();
        iLoadPlay.pubTweet(uid, msg, this);

    }

    @Override
    public void onSuccess(String result) {
        XStream xStream = new XStream();
        xStream.alias("oschina", PubTweetModel.class);
        xStream.alias("result", PubTweetModel.ResultBean.class);
        xStream.alias("notice", PubTweetModel.NoticeBean.class);
        final PubTweetModel o = (PubTweetModel) xStream.fromXML(result);

        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PlayActivity.this, o.getResult().getErrorMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        Intent intent = new Intent(ATotalOf.SENTBROADACTION);
        intent.putExtra("pubtweet", 2);
        LocalBroadcastManager.getInstance(App.subActivity).sendBroadcast(intent);

    }

    @Override
    public void onError(String errormsg) {

    }
}
