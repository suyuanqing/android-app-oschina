package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.LatestBlogAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.entity.LatestModel;
import com.usian.android_app_oschina.model.http.NetworkCallback;
import com.usian.android_app_oschina.model.http.biz.LoadNetNews;
import com.usian.android_app_oschina.model.http.biz.LoadNewsImpl;
import com.usian.android_app_oschina.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 苏元庆 on 2017/5/12.
 */

public class LatestBlogFragment extends BaseFragment {

    @Bind(R.id.latest_recycler)
    PullToRefreshRecyclerView latestrecycler;

    private LoadNetNews netNews;
    private int index = 1;
    private ArrayList<LatestModel.BlogBean> data = new ArrayList<>();
    private boolean flag = false;
    private LatestBlogAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_latestblog;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(App.activity, LinearLayoutManager.VERTICAL,false);
        latestrecycler.setLayoutManager(linearLayoutManager);
        adapter = new LatestBlogAdapter(App.activity,data);
        latestrecycler.setPullToRefreshListener(new PullToRefreshListener() {
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
        latestrecycler.setAdapter(adapter);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        netNews = new LoadNewsImpl();
        netNews.getLatestBlog(index + "", new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                XStream xstream = new XStream();
                xstream.alias("oschina", LatestModel.class);
                xstream.alias("blog", LatestModel.BlogBean.class);
                LatestModel o = (LatestModel) xstream.fromXML(result);
                List<LatestModel.BlogBean> newslist = o.getBlogs();
                data.addAll(newslist);
                LogUtils.e("TAG",index+"--------"+flag);
                adapter.notifyDataSetChanged();
                if (flag){
                    latestrecycler.setLoadMoreComplete();
                    flag = false;
                }else {
                    latestrecycler.setRefreshComplete();
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
        LogUtils.e("LatestBlogFragment", "已经被隐藏");
        flag = false;
    }
}
