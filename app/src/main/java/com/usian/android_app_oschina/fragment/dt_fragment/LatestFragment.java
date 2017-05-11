package com.usian.android_app_oschina.fragment.dt_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.lenovo.volleyutils.NetWorkListener;
import com.jiyun.lenovo.volleyutils.RequestMethod;
import com.jiyun.lenovo.volleyutils.VolleyUtils;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.StirModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 这个是动弹下最新动弹的Fragment
 */

public class LatestFragment extends BaseFragment {

    @Bind(R.id.latest_recycler)
    RecyclerView latestRecycler;
    private View mRoot;
    private int index = 1;
    private ArrayList<StirModel.TweetBean> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.fragment_latest, null);

        ButterKnife.bind(this, mRoot);
        initData();


        return mRoot;

    }

    @Override
    public void initData() {
        super.initData();
        latestRecycler.setHasFixedSize(true);
        latestRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        latestRecycler.setItemAnimator(new DefaultItemAnimator());
        latestRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        getData();
    }

    public void getData(){


        String url = "http://www.oschina.net/action/api/tweet_list?uid=0&pageIndex="+index+"&pageSize=10";

        VolleyUtils volleyUtils = new VolleyUtils();
        volleyUtils.loadNetData(getContext(), RequestMethod.GET, url, new NetWorkListener() {
            @Override
            public void onSucess(String s) {
                XStream xstream = new XStream();
                xstream.alias("oschina", StirModel.class);
                xstream.alias("news", StirModel.TweetBean.class);
                StirModel o = (StirModel) xstream.fromXML(s);
                List<StirModel.TweetBean> newslist = o.getTweets();
                data.addAll(newslist);
            }

            @Override
            public void onFailer(Exception e) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
