package com.usian.android_app_oschina.controller.activity.tweet_activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.ZHPagerAdapter;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.controller.fragment.dt_fragment.TweetZanFragment;
import com.usian.android_app_oschina.model.entity.LatestTweetModel;
import com.usian.android_app_oschina.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//TODO  发表评论还未完成 分享 还有bug未修复
public class TweetInfoActivity extends BaseActivity {


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
    @Bind(R.id.tweet_info_userimg)
    ImageView tweetInfoUserimg;
    @Bind(R.id.tweet_info_username)
    TextView tweetInfoUsername;
    @Bind(R.id.tweet_info_content)
    TextView tweetInfoContent;
    @Bind(R.id.tweet_info_imgsmall)
    ImageView tweetInfoImgsmall;
    @Bind(R.id.tweet_info_date)
    TextView tweetInfoDate;
    @Bind(R.id.tweet_info_dianzan)
    CheckBox tweetInfoDianzan;
    @Bind(R.id.tweet_info_pinglun)
    ImageView tweetInfoPinglun;
    @Bind(R.id.tweet_info_zhuanfa)
    ImageView tweetInfoZhuanfa;
    @Bind(R.id.tweet_info_tab)
    TabLayout tweetInfoTab;
    @Bind(R.id.tweet_info_pager)
    ViewPager tweetInfoPager;
    @Bind(R.id.tweet_info_send)
    LinearLayout tweetInfoSend;
    @Bind(R.id.activity_tweet_info2)
    LinearLayout activityTweetInfo2;
    private LatestTweetModel.TweetBean userInfo;
    private String time;
    private ArrayList<BaseFragment> datas = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tweet_info2;
    }

    @Override
    protected void initView() {
        tweetInfoImgsmall.setVisibility(View.GONE);
        titleIconName.setText("动弹详情");
        tweetInfoImgsmall.setVisibility(View.GONE);
        tvInfoPinglun.setVisibility(View.GONE);
        ivInfoImg.setImageResource(R.mipmap.btn_share_white_normal);
        userInfo = (LatestTweetModel.TweetBean) getIntent().getSerializableExtra("userInfo");
        time = getIntent().getStringExtra("time");

        titles.add("赞" + "(" + userInfo.getAppclient() + ")");
        titles.add("评论" + "(" + userInfo.getCommentCount() + ")");
    }

    @Override
    protected void initData() {
        datas.add(new TweetZanFragment());
        datas.add(new TweetZanFragment());
        tweetInfoTab.setupWithViewPager(tweetInfoPager);
        ZHPagerAdapter adapter = new ZHPagerAdapter(getSupportFragmentManager(), datas, titles);
        tweetInfoPager.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        Glide.with(this).load(userInfo.getPortrait())
                .transform(new GlideCircleTransform(this))
                .error(R.mipmap.ic_default_image).into(tweetInfoUserimg);

        if (userInfo.getImgSmall() == null) {
            LogUtils.e("TAG", "隐藏" + userInfo.getImgSmall() + "------------");
        } else {
            tweetInfoImgsmall.setVisibility(View.VISIBLE);
            Glide.with(this).load(userInfo.getImgSmall())
                    .into(tweetInfoImgsmall);
        }
        tweetInfoUsername.setText(userInfo.getAuthor());
        tweetInfoContent.setText(userInfo.getBody());
        tweetInfoDate.setText(time);
    }

    @OnClick({R.id.tweet_info_userimg, R.id.tweet_info_pinglun, R.id.tweet_info_zhuanfa, R.id.title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tweet_info_userimg:
                break;
            case R.id.tweet_info_pinglun:
                break;
            case R.id.tweet_info_zhuanfa:
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
