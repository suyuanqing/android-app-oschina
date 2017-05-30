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
// TODO 还有三个页面没有请求网路数据
public class FLTagFragment extends BaseBackFragment implements NetworkCallback {


    @Bind(R.id.find_twooss_fy)
    ListView findTwoossFy;
    @Bind(R.id.three_rongqi)
    FrameLayout threeRongqi;
    private ILoadFind iLoadFind;
    private String tag;
    private ArrayList<FindOssFLModel.SoftwareTypeBean> twofldata = new ArrayList<>();
    private FindOssFlAdapter adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private IntentFilter intentFilter;
    private LocalBroadcastManager broadcastManager;
    private BroadcastReceiver mReceiver;

    @Override
    public boolean onBackPressed() {

        Intent intent = new Intent(ATotalOf.SHUAXINADAPTER);
        LocalBroadcastManager.getInstance(App.subActivity).sendBroadcast(intent);

        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_twoossfy;
    }

    @Override
    protected void initData() {
        tag = getParams().getString("tag");
    }

    @Override
    public void setParams(Bundle params) {
        super.setParams(params);

    }

    @Override
    public Bundle getParams() {
        return super.getParams();
    }

    @Override
    protected void initView(View view) {

        manager = App.subActivity.getSupportFragmentManager();

        adapter = new FindOssFlAdapter(App.activity, twofldata);
        findTwoossFy.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        findTwoossFy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("threetag", twofldata.get(position).getTag());
                ThreeListFragment flTagFragment = new ThreeListFragment();
                transaction = manager.beginTransaction();
                transaction.add(R.id.three_rongqi, flTagFragment, "ThreeListFragment");
                flTagFragment.setParams(bundle);
                transaction.addToBackStack("ThreeListFragment");
                transaction.show(flTagFragment);
                transaction.commit();
                findTwoossFy.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        intentFilter = new IntentFilter();
        intentFilter.addAction(ATotalOf.SHUAXINTHREE);
        //收到广播后所作的操作
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent){

                findTwoossFy.setVisibility(View.VISIBLE);
//                manager.popBackStack();
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
    protected void loadData() {
        iLoadFind = new LoadFindImpl();
        iLoadFind.getTwoClassify(tag, this);
    }


    @Override
    public void onSuccess(String result) {
        XStream xStream = new XStream();
        xStream.alias("oschina", FindOssFLModel.class);
        xStream.alias("softwareType", FindOssFLModel.SoftwareTypeBean.class);

        FindOssFLModel o = (FindOssFLModel) xStream.fromXML(result);
        List<FindOssFLModel.SoftwareTypeBean> softwareTypes = o.getSoftwareTypes();
        twofldata.addAll(softwareTypes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String errormsg) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

}
