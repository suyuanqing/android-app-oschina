package com.usian.android_app_oschina.controller.activity.mine_activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.TweetAdapter;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.model.entity.LatestTweetModel;
import com.usian.android_app_oschina.model.http.biz.tweetbus.ILoadTweet;
import com.usian.android_app_oschina.model.http.biz.tweetbus.LoadTweetImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MineTweetActivity extends BaseActivity {


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
    @Bind(R.id.mine_tweet_list)
    RecyclerView mineTweetList;
    @Bind(R.id.activity_mine_tweet)
    LinearLayout activityMineTweet;
    private ILoadTweet iLoadTweet;
    private List<LatestTweetModel.TweetBean> datas = new ArrayList<>();
    private String uid;
    private TweetAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_tweet;
    }

    @Override
    protected void initView() {
        titleIconName.setText("动弹列表");
        ivInfoImg.setVisibility(View.GONE);
        tvInfoPinglun.setVisibility(View.GONE);

        mineTweetList.setHasFixedSize(true);
        mineTweetList.setItemAnimator(new DefaultItemAnimator());
        mineTweetList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mineTweetList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    protected void initData() {
        uid = (String) SPUtils.getParam(App.getContext(), "uid", "");
        adapter = new TweetAdapter(App.activity, datas);
        mineTweetList.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void loadData() {

        iLoadTweet = new LoadTweetImpl();
        String uid = (String) SPUtils.getParam(App.getContext(), "uid", "");
        iLoadTweet.getMineTweet(0 + "", uid, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e("TAG", result);
                XStream xStream = new XStream();
                xStream.alias("oschina", LatestTweetModel.class);
                xStream.alias("tweet", LatestTweetModel.TweetBean.class);
                LatestTweetModel o = (LatestTweetModel) xStream.fromXML(result);
                List<LatestTweetModel.TweetBean> tweets = o.getTweets();
                datas.addAll(tweets);
                ThreadUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });


            }

            @Override
            public void onError(String errormsg) {

            }
        });

    }

}
