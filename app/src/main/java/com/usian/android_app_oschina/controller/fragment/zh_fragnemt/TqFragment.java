package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.app.ProgressDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.TqAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.entity.TQModel;
import com.usian.android_app_oschina.model.http.biz.newsbus.ILoadNetNews;
import com.usian.android_app_oschina.model.http.biz.newsbus.LoadNewsImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 这个是动弹下 （最新动弹） 的Fragment
 */

public class TqFragment extends BaseFragment {


    @Bind(R.id.hot_tweet_recycler)
    PullToRefreshRecyclerView tqrecycler;
    private int index = 1;
    private ArrayList<TQModel.PostBean> data = new ArrayList<>();
    private ProgressDialog dialog;
    private TqAdapter tqAdapter;

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
        tqrecycler.setLayoutManager(linearLayoutManager);
        tqrecycler.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));
        tqAdapter = new TqAdapter(App.activity, data);

        tqrecycler.displayLastRefreshTime(true);
        tqrecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                tqrecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        loadData();
                        tqrecycler.setRefreshComplete();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                tqrecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        index++;
                        loadData();
                        tqrecycler.setLoadMoreComplete();
                    }
                }, 1000);

            }
        });

        tqrecycler.setAdapter(tqAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        ILoadNetNews iLoadNetNews = new LoadNewsImpl();
        iLoadNetNews.getQnn(index + "", "1", new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                dialog.dismiss();
                XStream xStream = new XStream();
                xStream.alias("oschina", TQModel.class);
                xStream.alias("notice", TQModel.NoticeBean.class);
                xStream.alias("post", TQModel.PostBean.class);

                TQModel o = (TQModel) xStream.fromXML(result);
                List<TQModel.PostBean> posts = o.getPosts();
                data.addAll(posts);
                tqAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errormsg) {

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
