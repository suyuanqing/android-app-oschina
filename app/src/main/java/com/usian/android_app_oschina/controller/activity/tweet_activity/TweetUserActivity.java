package com.usian.android_app_oschina.controller.activity.tweet_activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;

import butterknife.Bind;

public class TweetUserActivity extends BaseActivity {

    @Bind(R.id.iv_tweet_user)
    ImageView ivTweetUser;
    @Bind(R.id.tv_tweet_qianming)
    TextView tvTweetQianming;
    @Bind(R.id.tv_tweet_score)
    TextView tvTweetScore;
    @Bind(R.id.tv_tweet_guanzhu)
    TextView tvTweetGuanzhu;
    @Bind(R.id.tv_tweet_fensi)
    TextView tvTweetFensi;
    @Bind(R.id.tweet_info_tab)
    TabLayout tweetInfoTab;
    @Bind(R.id.tweet_info_pager)
    ViewPager tweetInfoPager;
    @Bind(R.id.activity_tweet_info)
    LinearLayout activityTweetInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tweet_info;
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

}
