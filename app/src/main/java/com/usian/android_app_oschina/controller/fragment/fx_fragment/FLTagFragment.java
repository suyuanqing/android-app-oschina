package com.usian.android_app_oschina.controller.fragment.fx_fragment;

import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/17.
 */

public class FLTagFragment extends BaseFragment implements NetworkCallback {


    @Bind(R.id.find_twooss_fy)
    ListView findTwoossFy;
    private ILoadFind iLoadFind;
    private String tag;
    private ArrayList<FindOssFLModel.SoftwareTypeBean> twofldata = new ArrayList<>();
    private FindOssFlAdapter adapter;

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
        adapter = new FindOssFlAdapter(App.activity, twofldata);
        findTwoossFy.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

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
