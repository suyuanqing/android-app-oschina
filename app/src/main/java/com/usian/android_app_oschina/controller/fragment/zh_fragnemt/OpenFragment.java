package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.controller.activity.OpenActivity;
import com.usian.android_app_oschina.adapter.OpenAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.http.biz.LoadNewsImpl;
import com.usian.android_app_oschina.model.entity.InformationModel;
import com.usian.android_app_oschina.model.http.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 综合下开源资讯的Fragmeng
 */

public class OpenFragment extends BaseFragment implements NetworkCallback{


    @Bind(R.id.open_pager)
    RollPagerView openPager;
    @Bind(R.id.open_recycler)
    RecyclerView openRecycler;
    @Bind(R.id.ptr_news)
    PtrFrameLayout ptrNews;
    @Bind(R.id.open_progressbar)
    ProgressBar openProgressbar;
    private View mRoot;
    private ArrayList<InformationModel.NewsBean> data;
    private OpenAdapter adapter;
    private LinearLayoutManager mLinear;
    private int index = 1;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_open;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(getLayoutId(), null);
        return mRoot;
    }

    @Override
    protected void initData(Bundle bun) {
        LoadNewsImpl news = new LoadNewsImpl();
        news.getNews(index+"",this);
    }

    @Override
    protected void initView(View view) {
        initImg();
        ptrShow();
        mLinear = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mLinear.setSmoothScrollbarEnabled(true);
        mLinear.setAutoMeasureEnabled(true);

        openRecycler.setHasFixedSize(true);
        openRecycler.setLayoutManager(mLinear);
        openRecycler.setNestedScrollingEnabled(false);
        openRecycler.setItemAnimator(new DefaultItemAnimator());
        openRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        data = new ArrayList<>();
        adapter = new OpenAdapter(getContext(),data);
        openRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        adapter.setOnClickitems(new OpenAdapter.Onclickitemre() {
            @Override
            public void onclickitems(View v, int pos) {
                Intent intent = new Intent(getContext(), OpenActivity.class);
                LogUtils.e("OpenFragment", "发送给OpenAdapter的URL为：" + data.get(pos).getUrl());
                intent.putExtra("url",data.get(pos).getUrl());
                startActivity(intent);
            }
        });
    }

    //上拉加载和下拉刷新
    public void ptrShow() {
        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(getContext());
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getContext());

        ptrNews.setHeaderView(header);
        ptrNews.setFooterView(footer);
        ptrNews.addPtrUIHandler(header);
        ptrNews.addPtrUIHandler(footer);

        ptrNews.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                index++;
                initData(getArguments());
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ThreadUtils.runOnSubThread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        ThreadUtils.runOnUIThread(new Runnable() {
                            @Override
                            public void run() {
                                ptrNews.refreshComplete();
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });

            }
        });

    }

    //设置轮播图
    public void initImg() {
        //设置播放时间间隔
        openPager.setPlayDelay(2000);
        //设置透明度
        openPager.setAnimationDurtion(500);
        //设置适配器
        openPager.setAdapter(new TestNormalAdapter());

        openPager.setHintView(new ColorPointHintView(getContext(), Color.YELLOW, Color.WHITE));
    }

    @Override
    public void onSuccess(String result) {
        ptrNews.refreshComplete();
        openProgressbar.setVisibility(View.GONE);

        XStream xstream = new XStream();
        xstream.alias("oschina", InformationModel.class);
        xstream.alias("news", InformationModel.NewsBean.class);
        InformationModel o = (InformationModel) xstream.fromXML(result);
        List<InformationModel.NewsBean> newslist = o.getNewslist();
        data.addAll(newslist);


        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                mLinear.scrollToPositionWithOffset(data.size(), data.size());
                mLinear.setStackFromEnd(true);
            }
        });


    }

    @Override
    public void onError(String errormsg) {
        LogUtils.e("OpenFragment", "获取失败：" + errormsg);
    }


    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] images = {
                R.mipmap.a,
                R.mipmap.b,
                R.mipmap.c,
                R.mipmap.d,
                R.mipmap.e,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(images[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return view;
        }

        @Override
        public int getCount() {
            return images.length;
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}