package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.BlogAdapter;
import com.usian.android_app_oschina.adapter.NewsAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.entity.OpenNewsModel;
import com.usian.android_app_oschina.model.entity.ReBlogModel;
import com.usian.android_app_oschina.model.http.biz.newsbus.ILoadNetNews;
import com.usian.android_app_oschina.model.http.biz.newsbus.LoadNewsImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 综合下开源资讯的Fragmeng
 */

public class NewsFragment extends BaseFragment implements NetworkCallback {


    @Bind(R.id.open_pager)
    RollPagerView openPager;
    @Bind(R.id.open_recycler)
    RecyclerView openRecycler;
    @Bind(R.id.ptr_news)
    PtrFrameLayout ptrNews;
    private ArrayList<OpenNewsModel.NewsBean> newsdata;
    private ArrayList<ReBlogModel.BlogBean> blogdata;
    private NewsAdapter newsAdapter;
    private BlogAdapter blogAdapter;
    private LinearLayoutManager mLinear;
    private int index = 1;
    private ProgressDialog dialog;
    private ArrayList<String> list;
    private int flag;
    private ILoadNetNews iLoadNetNews;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_open;
    }

    @Override
    protected void initData() {

        newsAdapter = new NewsAdapter(getContext(), newsdata);
        blogAdapter = new BlogAdapter(getContext(), blogdata);

        if (list.get(flag).equals("开源资讯")){

            openRecycler.setAdapter(newsAdapter);
        }else if (list.get(flag).equals("推荐博客")){

            openRecycler.setAdapter(blogAdapter);
        }else if (list.get(flag).equals("热门资讯")){

            openRecycler.setAdapter(newsAdapter);
        }else if (list.get(flag).equals("最新博客")){

            openRecycler.setAdapter(blogAdapter);
        }
    }

    @Override
    protected void initView(View view) {
        Bundle bundle = this.getArguments();
        if(bundle != null){
            list = bundle.getStringArrayList("content");
            flag = bundle.getInt("flag");
        }
        Log.e("NewsInfoActivity", "当前Fragmeng位置："+list.get(flag));
        openPager.setVisibility(View.GONE);
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
        newsdata = new ArrayList<>();
        blogdata = new ArrayList<>();

    }

    @Override
    protected void initListener() {



    }

    @Override
    protected void loadData() {
        dialog = new ProgressDialog(App.activity);
        dialog.setMessage("loading");
//        dialog.show();
        iLoadNetNews = new LoadNewsImpl();

        if (list.get(flag).equals("开源资讯")){

            iLoadNetNews.getNews(index + "", this);
        }else if (list.get(flag).equals("推荐博客")){

            iLoadNetNews.getRecommBlog(index + "", this);
        }else if (list.get(flag).equals("热门资讯")){

            iLoadNetNews.getHotNews(index + "", this);
        }else if (list.get(flag).equals("最新博客")){

            iLoadNetNews.getLatestBlog(index + "", this);
        }

    }

    @Override
    public void onSuccess(String result) {

        dialog.dismiss();

        if (list.get(flag).equals("开源资讯")){
            openPager.setVisibility(View.VISIBLE);
            XStream xstream = new XStream();
            xstream.alias("oschina", OpenNewsModel.class);
            xstream.alias("news", OpenNewsModel.NewsBean.class);
            OpenNewsModel o = (OpenNewsModel) xstream.fromXML(result);
            List<OpenNewsModel.NewsBean> newslist = o.getNewslist();
            newsdata.addAll(newslist);
            newspositioning();
        }else if (list.get(flag).equals("推荐博客")){

            XStream xstream = new XStream();
            xstream.alias("oschina", ReBlogModel.class);
            xstream.alias("blog", ReBlogModel.BlogBean.class);
            ReBlogModel o = (ReBlogModel) xstream.fromXML(result);
            List<ReBlogModel.BlogBean> newslist = o.getBlogs();
            blogdata.addAll(newslist);
            blogpositioning();
        }else if (list.get(flag).equals("热门资讯")){

            XStream xstream = new XStream();
            xstream.alias("oschina", OpenNewsModel.class);
            xstream.alias("news", OpenNewsModel.NewsBean.class);
            OpenNewsModel o = (OpenNewsModel) xstream.fromXML(result);
            List<OpenNewsModel.NewsBean> newslist = o.getNewslist();
            newsdata.addAll(newslist);
            newspositioning();
        }else if (list.get(flag).equals("最新博客")){
            XStream xstream = new XStream();
            xstream.alias("oschina", ReBlogModel.class);
            xstream.alias("blog", ReBlogModel.BlogBean.class);
            ReBlogModel o = (ReBlogModel) xstream.fromXML(result);
            List<ReBlogModel.BlogBean> newslist = o.getBlogs();
            blogdata.addAll(newslist);
            blogpositioning();

        }
//        ptrNews.refreshComplete();
    }


    //定位当前浏览位置
    public void newspositioning(){
        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                newsAdapter.notifyDataSetChanged();
                mLinear.scrollToPositionWithOffset(newsdata.size(), newsdata.size());
                mLinear.setStackFromEnd(true);
            }
        });
    }

    public void blogpositioning(){
        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                newsAdapter.notifyDataSetChanged();
                mLinear.scrollToPositionWithOffset(blogdata.size(), blogdata.size());
                mLinear.setStackFromEnd(true);
            }
        });
    }

    @Override
    public void onError(String errormsg) {
        LogUtils.e("NewsFragment", "获取失败：" + errormsg);
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

    private static class TestNormalAdapter extends StaticPagerAdapter {
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

    public static NewsFragment newInstance(List<String> contentList, int flag){
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("content", (ArrayList<String>) contentList);
        bundle.putInt("flag", flag);
        NewsFragment testFm = new NewsFragment();
        testFm.setArguments(bundle);

        return testFm;

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

                ptrNews.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        index++;
                        loadData();
                        ptrNews.refreshComplete();
                        if (list.get(flag).equals("开源资讯") &&
                                list.get(flag).equals("热门资讯")){
                            newsAdapter.notifyDataSetChanged();
                        }else{
                            blogAdapter.notifyDataSetChanged();
                        }

                    }
                }, 2000);

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrNews.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ptrNews.refreshComplete();
                        if (list.get(flag).equals("开源资讯") &&
                                list.get(flag).equals("热门资讯")){
                            newsAdapter.notifyDataSetChanged();
                        }else{
                            blogAdapter.notifyDataSetChanged();
                        }

                    }
                }, 2000);

            }
        });

    }

    @Override
    public void onHidden() {
        super.onHidden();
    }

    @Override
    public void onShow() {
        super.onShow();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}