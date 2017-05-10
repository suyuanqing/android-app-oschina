package com.usian.android_app_oschina.fragment.dt_fragnemt;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.ImageAdapter;
import com.usian.android_app_oschina.adapter.OpenAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.InformationModel;
import com.usian.android_app_oschina.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/9.
 */

public class OpenFragment extends BaseFragment {

    @Bind(R.id.open_recycler)
    RecyclerView openRecycler;
    @Bind(R.id.open_pager)
    RollPagerView openPager;

    private View mRoot;
    private ArrayList<InformationModel.NewsBean> data;
    private ArrayList<View> imgdata;
    private String baseurl;
    private ImageView img;
    private ImageAdapter imgadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.fragment_open, null);
        ButterKnife.bind(this, mRoot);
        initData();
        initImg();
        return mRoot;
    }

    @Override
    public void initData() {
        super.initData();

        openRecycler.setHasFixedSize(true);
        openRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        openRecycler.setItemAnimator(new DefaultItemAnimator());
        openRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        data = new ArrayList<>();

        baseurl = "http://www.oschina.net/action/api/news_list?catalog=1&pageIndex=1&pageSize=10";


        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                XStream xstream = new XStream();
                xstream.alias("oschina", InformationModel.class);
                xstream.alias("news", InformationModel.NewsBean.class);
                InformationModel o = (InformationModel) xstream.fromXML(s);
                List<InformationModel.NewsBean> newslist = o.getNewslist();
                data.addAll(newslist);
                OpenAdapter adapter = new OpenAdapter(getContext(), data);
                openRecycler.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtils.e("OpenFragment","获取失败："+volleyError.getMessage().toString());
            }
        });
        queue.add(stringRequest);


    }



    public void initImg(){
        //设置播放时间间隔??
        openPager.setPlayDelay(2000);
        //设置透明度??
        openPager.setAnimationDurtion(500);
        //设置适配器??
        openPager.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）??
        //自定义指示器图片??
        //设置圆点指示器颜色??
        //设置文字指示器??
        //隐藏指示器??
        openPager.setHintView(new ColorPointHintView(getContext(), Color.YELLOW,Color.WHITE));
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
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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
