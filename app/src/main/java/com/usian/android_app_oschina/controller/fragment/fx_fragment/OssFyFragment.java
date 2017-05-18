package com.usian.android_app_oschina.controller.fragment.fx_fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.FindOssFlAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.entity.FindOssFLModel;
import com.usian.android_app_oschina.model.http.biz.findbus.ILoadFind;
import com.usian.android_app_oschina.model.http.biz.findbus.LoadFindImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/17.
 */

public class OssFyFragment extends BaseFragment implements NetworkCallback {


    @Bind(R.id.find_oss_fy)
    ListView findOssFy;
    @Bind(R.id.find_rongqi)
    FrameLayout findRongqi;
    @Bind(R.id.find_oss_rongqi)
    FrameLayout findOssRongqi;
    private ILoadFind iLoadFind;
    private ArrayList<FindOssFLModel.SoftwareTypeBean> fldata = new ArrayList<>();
    private FindOssFlAdapter adapter;
    private FragmentManager manager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ossfy;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView(View view) {
        manager = getChildFragmentManager();
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
                FragmentUtils.create().containerViewId(R.id.find_rongqi).start(FLTagFragment.class).params(bundle).build();

            }

        });
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
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
