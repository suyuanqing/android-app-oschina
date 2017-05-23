package com.usian.android_app_oschina.controller.fragment.fx_fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.SoftAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.model.entity.SoftInfoModel;
import com.usian.android_app_oschina.model.http.biz.findbus.ILoadFind;
import com.usian.android_app_oschina.model.http.biz.findbus.LoadFindImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by 苏元庆 on 2017/5/17.
 */

public class SubListFragment extends BaseFragment implements NetworkCallback{

    @Bind(R.id.find_sub_rec)
    RecyclerView findSubRec;
    @Bind(R.id.ptr_sublist)
    PtrFrameLayout ptrSublist;

    private ArrayList<String> list;
    private int flag;
    private LinearLayoutManager mLinear;
    private int index = 1;
    private ProgressDialog dialog;
    private ILoadFind iLoadFind;
    private ArrayList<SoftInfoModel.SoftwareBean> softdata = new ArrayList<>();
    private SoftAdapter softAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sublist;
    }

    @Override
    protected void initData() {

        softAdapter = new SoftAdapter(App.subActivity, softdata);
        findSubRec.setAdapter(softAdapter);
    }

    @Override
    protected void initView(View view) {
        Bundle bundle = this.getArguments();
        if(bundle != null){
            list = bundle.getStringArrayList("softcontent");
            flag = bundle.getInt("softflag");
        }
        Log.e("SubListFragment", "当前Fragment位置："+list.get(flag));

        ptrShow();

        mLinear = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mLinear.setSmoothScrollbarEnabled(true);
        mLinear.setAutoMeasureEnabled(true);

        findSubRec.setHasFixedSize(true);
        findSubRec.setLayoutManager(mLinear);
        findSubRec.setNestedScrollingEnabled(false);
        findSubRec.setItemAnimator(new DefaultItemAnimator());
        findSubRec.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        dialog = new ProgressDialog(App.activity);
        dialog.setMessage("loading");
//        dialog.show();
        iLoadFind = new LoadFindImpl();

        if (list.get(flag).equals("推荐")){

            iLoadFind.getSoftwareList(index+"", Arguments.FIND_OSS_RECOMMEND, this);
        }else if (list.get(flag).equals("最新")){

            iLoadFind.getSoftwareList(index+"", Arguments.FIND_OSS_TIME, this);
        }else if (list.get(flag).equals("热门")){

            iLoadFind.getSoftwareList(index+"", Arguments.FIND_OSS_VIEW, this);
        }else if (list.get(flag).equals("国产")){

            iLoadFind.getSoftwareList(index+"", Arguments.FIND_OSS_LIST_CN, this);
        }

    }


    @Override
    public void onSuccess(String result) {
        dialog.dismiss();

        XStream xStream = new XStream();
        xStream.alias("oschina", SoftInfoModel.class);
        xStream.alias("software", SoftInfoModel.SoftwareBean.class);

        SoftInfoModel o = (SoftInfoModel) xStream.fromXML(result);
        List<SoftInfoModel.SoftwareBean> softwares = o.getSoftwares();
        softdata.addAll(softwares);
        Log.e("TAG", softdata.size()+"=-=-=-=-=-=-");
        positioning();
    }

    @Override
    public void onError(String errormsg) {

    }

    //定位当前浏览位置
    public void positioning(){
        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                softAdapter.notifyDataSetChanged();
                mLinear.scrollToPositionWithOffset(softdata.size(), softdata.size());
                mLinear.setStackFromEnd(true);
            }
        });
    }


    public static SubListFragment newInstance(List<String> contentList, int flag) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("softcontent", (ArrayList<String>) contentList);
        bundle.putInt("softflag", flag);
        SubListFragment testFm = new SubListFragment();
        testFm.setArguments(bundle);

        return testFm;
    }


    //上拉加载和下拉刷新
    public void ptrShow() {
        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(getContext());
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getContext());

        ptrSublist.setHeaderView(header);
        ptrSublist.setFooterView(footer);
        ptrSublist.addPtrUIHandler(header);
        ptrSublist.addPtrUIHandler(footer);

        ptrSublist.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {

                ptrSublist.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        index++;
                        loadData();
                        ptrSublist.refreshComplete();
                        softAdapter.notifyDataSetChanged();

                    }
                }, 2000);

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrSublist.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        softdata.clear();
                        ptrSublist.refreshComplete();
                        loadData();
                        softAdapter.notifyDataSetChanged();


                    }
                }, 2000);

            }
        });

    }

}
