package com.usian.android_app_oschina.controller.fragment.fx_fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.FindOssFlAdapter;
import com.usian.android_app_oschina.base.BaseBackFragment;
import com.usian.android_app_oschina.contact.ATotalOf;
import com.usian.android_app_oschina.model.entity.FindOssFLModel;
import com.usian.android_app_oschina.model.http.biz.findbus.ILoadFind;
import com.usian.android_app_oschina.model.http.biz.findbus.LoadFindImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/17.
 */
// TODO 有bug未修复
public class OssFyFragment extends BaseBackFragment implements NetworkCallback {


    @Bind(R.id.find_oss_fy)
    ListView findOssFy;
    @Bind(R.id.find_oss_rongqi)
    FrameLayout findOssRongqi;
    private ILoadFind iLoadFind;
    private ArrayList<FindOssFLModel.SoftwareTypeBean> fldata = new ArrayList<>();
    private FindOssFlAdapter adapter;
    private FragmentManager manager;
    private IntentFilter intentFilter;
    private LocalBroadcastManager broadcastManager;
    private BroadcastReceiver mReceiver;
    private FragmentTransaction transaction;

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ossfy;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView(View view) {

        manager = App.subActivity.getSupportFragmentManager();

        adapter = new FindOssFlAdapter(App.activity, fldata);

        findOssFy.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        findOssFy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("tag", fldata.get(position).getTag());
                FLTagFragment flTagFragment = new FLTagFragment();
                transaction = manager.beginTransaction();
                transaction.add(R.id.find_oss_rongqi, flTagFragment, "FLTagFragment");
                flTagFragment.setParams(bundle);
                transaction.addToBackStack("FLTagFragment");
                transaction.show(flTagFragment);
                transaction.commit();
                findOssFy.setVisibility(View.GONE);

            }

        });
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        intentFilter = new IntentFilter();
        intentFilter.addAction(ATotalOf.SHUAXINADAPTER);
        //收到广播后所作的操作
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){

                findOssFy.setVisibility(View.VISIBLE);
                manager.popBackStack();
            }
        };
        broadcastManager.registerReceiver(mReceiver, intentFilter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        broadcastManager.unregisterReceiver(mReceiver);
    }

    @Override
    public void onHidden() {
        super.onHidden();

    }

    @Override
    protected void loadData() {
        iLoadFind = new LoadFindImpl();
        iLoadFind.getClassify(this);
    }


    @Override
    public void onSuccess(String result) {
        XStream xStream = new XStream();
        xStream.alias("oschina", FindOssFLModel.class);
        xStream.alias("softwareType", FindOssFLModel.SoftwareTypeBean.class);

        FindOssFLModel o = (FindOssFLModel) xStream.fromXML(result);
        List<FindOssFLModel.SoftwareTypeBean> softwareTypes = o.getSoftwareTypes();
        fldata.addAll(softwareTypes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errormsg) {

    }


    public static OssFyFragment newInstance(List<String> contentList, int flag) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("content", (ArrayList<String>) contentList);
        bundle.putInt("flag", flag);
        OssFyFragment testFm = new OssFyFragment();
        testFm.setArguments(bundle);

        return testFm;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FLTagFragment f = new FLTagFragment();
        transaction.remove(f);
        transaction.commitAllowingStateLoss();
    }


}
