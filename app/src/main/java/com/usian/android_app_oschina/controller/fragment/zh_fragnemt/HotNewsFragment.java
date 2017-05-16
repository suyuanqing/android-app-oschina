package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.HotNewsAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.entity.HotNewsModel;
import com.usian.android_app_oschina.model.http.biz.newsbus.ILoadNetNews;
import com.usian.android_app_oschina.model.http.biz.newsbus.LoadNewsImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 苏元庆 on 2017/5/12.
 */

public class HotNewsFragment extends BaseFragment {

    @Bind(R.id.hotnews_recycler)
    PullToRefreshRecyclerView hotnewsRecycler;

    private ILoadNetNews netNews;
    private int index = 1;
    private ArrayList<HotNewsModel.NewsBean> data = new ArrayList<>();
    private boolean flag = false;
    private HotNewsAdapter adapter;
    private boolean isFrist;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hotnews;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(App.activity, LinearLayoutManager.VERTICAL,false);
        hotnewsRecycler.setLayoutManager(linearLayoutManager);
        hotnewsRecycler.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));
        adapter = new HotNewsAdapter(App.activity,data);

        hotnewsRecycler.displayLastRefreshTime(true);
        hotnewsRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                flag = false;
                loadData();
            }

            @Override
            public void onLoadMore() {
                index++;
                flag = true;
                loadData();
            }
        });
        hotnewsRecycler.setAdapter(adapter);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        netNews = new LoadNewsImpl();
        netNews.getHotNews(index + "", new NetworkCallback() {
            @Override
            public void onSuccess(String result) {

                XStream xstream = new XStream();
                xstream.alias("oschina", HotNewsModel.class);
                xstream.alias("news", HotNewsModel.NewsBean.class);
                HotNewsModel o = (HotNewsModel) xstream.fromXML(result);
                List<HotNewsModel.NewsBean> newslist = o.getNewslist();
                data.addAll(newslist);

                adapter.notifyDataSetChanged();


                if (isFrist){
                    if (flag){
                        hotnewsRecycler.setLoadMoreComplete();
                    }else {
                        hotnewsRecycler.setRefreshComplete();
                    }
                }else{
                    isFrist = true;
                }
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
        LogUtils.e("HotNewsFragment","已经被隐藏");
    }


}
