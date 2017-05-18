package com.usian.android_app_oschina.controller.fragment.dt_fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.TweetAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.entity.LatestTweetModel;
import com.usian.android_app_oschina.model.entity.StirModel;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.model.http.biz.tweetbus.ILoadTweet;
import com.usian.android_app_oschina.model.http.biz.tweetbus.LoadTweetImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 这个是动弹下 （最新动弹） 的Fragment
 */

public class HotTweetFragment extends BaseFragment {


    @Bind(R.id.hot_tweet_recycler)
    PullToRefreshRecyclerView hotTweetRecycler;
    private int index = 6;
    private ArrayList<StirModel.TweetBean> data = new ArrayList<>();
    private TweetAdapter adapter;
    private boolean flag = false;
    private ILoadTweet iLoadTweet;
    private List<LatestTweetModel.TweetBean> datas = new ArrayList<>();
    private ProgressDialog dialog;
    private boolean isFrist;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tweet_hot;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        dialog = new ProgressDialog(App.activity);
        dialog.setMessage("loading");
        dialog.show();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(App.activity, LinearLayoutManager.VERTICAL,false);
        hotTweetRecycler.setLayoutManager(linearLayoutManager);
        hotTweetRecycler.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));

        adapter = new TweetAdapter(App.activity, datas);

        hotTweetRecycler.displayLastRefreshTime(true);
        hotTweetRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                hotTweetRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        flag = false;
                        loadData();
                        hotTweetRecycler.setRefreshComplete();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                hotTweetRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        index++;
                        flag = true;
                        loadData();
                        hotTweetRecycler.setLoadMoreComplete();
                    }
                }, 1000);

            }
        });
        hotTweetRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        iLoadTweet = new LoadTweetImpl();
        iLoadTweet.getHotTweet(index + "", new NetworkCallback() {

            @Override
            public void onSuccess(String result) {
                dialog.dismiss();
                XStream xStream = new XStream();
                xStream.alias("oschina", LatestTweetModel.class);
                xStream.alias("tweet", LatestTweetModel.TweetBean.class);
                LatestTweetModel o = (LatestTweetModel) xStream.fromXML(result);
                List<LatestTweetModel.TweetBean> tweets = o.getTweets();
                datas.addAll(tweets);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onError(String errormsg) {

            }
        });


    }

    @Override
    public void onHidden() {
        super.onHidden();
        isFrist = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
