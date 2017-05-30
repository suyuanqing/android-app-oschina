package com.usian.android_app_oschina.controller.activity.find_activity;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.EventAdapter;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.model.entity.OffEventModel;
import com.usian.android_app_oschina.model.http.biz.findbus.ILoadFind;
import com.usian.android_app_oschina.model.http.biz.findbus.LoadFindImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class OffEventActivity extends BaseActivity implements NetworkCallback {

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
    @Bind(R.id.find_pager_img)
    RollPagerView findPagerImg;
    @Bind(R.id.find_offevent)
    RecyclerView findOffevent;
    @Bind(R.id.event_shuxin)
    PtrFrameLayout eventShuxin;
    @Bind(R.id.activity_off_event)
    LinearLayout activityOffEvent;
    private int index = 1;
    private ArrayList<OffEventModel.EventBean> eventdata = new ArrayList<>();
    private EventAdapter adapter;
    private LinearLayoutManager mLinear;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_off_event;
    }

    @Override
    protected void initView() {
        ptrShow();
        initImg();

        mLinear = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLinear.setSmoothScrollbarEnabled(true);
        mLinear.setAutoMeasureEnabled(true);

        findOffevent.setHasFixedSize(true);
        findOffevent.setLayoutManager(mLinear);
        findOffevent.setNestedScrollingEnabled(false);
        findOffevent.setItemAnimator(new DefaultItemAnimator());
        findOffevent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new EventAdapter(this, eventdata);

        findOffevent.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        titleIconName.setText(R.string.fx_xxhd);
        tvInfoPinglun.setVisibility(View.GONE);
        ivInfoImg.setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        ILoadFind iLoadFind = new LoadFindImpl();
        iLoadFind.getOffEvent(index + "", this);
    }

    @Override
    public void onSuccess(String result) {
        XStream xStream = new XStream();
        xStream.alias("oschina", OffEventModel.class);
        xStream.alias("event", OffEventModel.EventBean.class);
        OffEventModel o = (OffEventModel) xStream.fromXML(result);
        List<OffEventModel.EventBean> events = o.getEvents();
        eventdata.addAll(events);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errormsg) {

    }

    //设置轮播图
    public void initImg() {
        //设置播放时间间隔
        findPagerImg.setPlayDelay(2000);
        //设置透明度
        findPagerImg.setAnimationDurtion(500);
        //设置适配器
        findPagerImg.setAdapter(new TestNormalAdapter());

        findPagerImg.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
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

    //上拉加载和下拉刷新
    public void ptrShow() {
        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(this);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);

        eventShuxin.setHeaderView(header);
        eventShuxin.setFooterView(footer);
        eventShuxin.addPtrUIHandler(header);
        eventShuxin.addPtrUIHandler(footer);

        eventShuxin.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {

                eventShuxin.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        index++;
                        loadData();
                        eventShuxin.refreshComplete();
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                eventShuxin.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        eventShuxin.refreshComplete();
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);

            }
        });

    }
}
