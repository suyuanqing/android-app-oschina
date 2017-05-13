package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.ReBlogAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.entity.ReBlogModel;
import com.usian.android_app_oschina.model.http.NetworkCallback;
import com.usian.android_app_oschina.model.http.biz.LoadNetNews;
import com.usian.android_app_oschina.model.http.biz.LoadNewsImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 */

public class ReBlogFragment extends BaseFragment {


    @Bind(R.id.reblog_recycler)
    PullToRefreshRecyclerView reblogRecycler;

    private LoadNetNews netNews;
    private int index = 1;
    private ArrayList<ReBlogModel.BlogBean> data = new ArrayList<>();
    private ReBlogAdapter adapter;
    private boolean flag;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reblog;
    }


    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(App.activity, LinearLayoutManager.VERTICAL,false);
        reblogRecycler.setLayoutManager(linearLayoutManager);
        adapter = new ReBlogAdapter(App.activity,data);
        reblogRecycler.setPullToRefreshListener(new PullToRefreshListener() {
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
        reblogRecycler.setAdapter(adapter);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        netNews = new LoadNewsImpl();
        netNews.getRecommBlog(index + "", new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                XStream xstream = new XStream();
                xstream.alias("oschina", ReBlogModel.class);
                xstream.alias("blog", ReBlogModel.BlogBean.class);
                ReBlogModel o = (ReBlogModel) xstream.fromXML(result);
                List<ReBlogModel.BlogBean> newslist = o.getBlogs();
                data.addAll(newslist);

                adapter.notifyDataSetChanged();
                if (flag){
                    reblogRecycler.setLoadMoreComplete();
                }else {
                    reblogRecycler.setRefreshComplete();
                }
            }

            @Override
            public void onError(String errormsg) {

            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onHidden() {
        super.onHidden();
        flag = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
