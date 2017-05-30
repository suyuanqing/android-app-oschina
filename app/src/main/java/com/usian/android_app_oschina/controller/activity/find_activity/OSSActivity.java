package com.usian.android_app_oschina.controller.activity.find_activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.FindPagerAdapter;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.base.BaseBackFragment;
import com.usian.android_app_oschina.controller.fragment.fx_fragment.OssFyFragment;
import com.usian.android_app_oschina.controller.fragment.fx_fragment.SubListFragment;
import com.usian.android_app_oschina.utils.BackHandledInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class OSSActivity extends BaseActivity implements BackHandledInterface{


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
    @Bind(R.id.find_oss_tab)
    TabLayout findOssTab;
    @Bind(R.id.find_oss_pager)
    ViewPager findOssPager;
    @Bind(R.id.activity_oss)
    LinearLayout activityOss;

    private BaseBackFragment baseFragment;

    private FindPagerAdapter title_adapter;
    private List<String> titleList = new ArrayList<String>(); //标题链表
    private List<String> contentList = new ArrayList<String>(); //内容链表
    private ArrayList<BaseBackFragment> fragmentList = new ArrayList<BaseBackFragment>(); //碎片链表

    @Override
    protected int getLayoutId() {
        return R.layout.activity_oss;
    }


    @Override
    protected void initView() {
        initList();

        tvInfoPinglun.setVisibility(View.GONE);
        ivInfoImg.setVisibility(View.GONE);
        titleIconName.setText(R.string.fx_kyrj);
        findOssPager.setOffscreenPageLimit(5);

        OssFyFragment testFm = OssFyFragment.newInstance(contentList, 0);
        fragmentList.add(0, testFm);

        for(int i=1;i<titleList.size()+1;i++){

            SubListFragment testsub = SubListFragment.newInstance(contentList,i);
            fragmentList.add(testsub);
        }

        title_adapter = new FindPagerAdapter(getSupportFragmentManager(), fragmentList, contentList);

        findOssTab.setupWithViewPager(findOssPager);

        findOssPager.setAdapter(title_adapter);

    }

    @Override
    protected void initData() {

    }

    public void initList(){


        String[] nametitle = getResources().getStringArray(R.array.find_tab_titlename);
        for (int i=0;i<nametitle.length;i++){
            titleList.add(nametitle[i]);
            Log.e("OssFyFragment",nametitle[i]);
        }

        contentList.add(0,"分类");
        contentList.add(1,"推荐");
        contentList.add(2,"最新");
        contentList.add(3,"热门");
        contentList.add(4,"国产");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setSelectedFragment(BaseBackFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    @Override
    public void onBackPressed() {
        if(baseFragment == null || !baseFragment.onBackPressed()){
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                super.onBackPressed();
            }else{
                getSupportFragmentManager().popBackStack();
            }
        }
    }

}
