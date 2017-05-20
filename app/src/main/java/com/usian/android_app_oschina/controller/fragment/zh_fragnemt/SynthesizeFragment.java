package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.ZHPagerAdapter;
import com.usian.android_app_oschina.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 动弹的Fragment。
 */

public class SynthesizeFragment extends BaseFragment {

    @Bind(R.id.zh_tab)
    TabLayout zhTab;
    @Bind(R.id.zh_viewpager)
    ViewPager zhViewpager;
    @Bind(R.id.iv_ic_add)
    ImageView ivIcAdd;
    private ZHPagerAdapter title_adapter;
    private Animation animation;
    private Animation animation1;
    private boolean iv_rotation = true;


    private List<String> titleList = new ArrayList<String>(); //标题链表
    private List<String> contentList = new ArrayList<String>(); //内容链表
    private ArrayList<BaseFragment> fragmentList = new ArrayList<BaseFragment>(); //碎片链表

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_synthesize;
    }

    @Override
    protected void initData() {
        animation = AnimationUtils.loadAnimation(App.activity, R.anim.ic_add_rotation);
        animation.setFillAfter(true);
        iv_rotation = animation.getFillAfter();
        animation.setDuration(500);

        animation1 = AnimationUtils.loadAnimation(App.activity, R.anim.ic_add_rotation_false);
        animation1.setFillAfter(true);
        animation1.setDuration(500);


    }

    @Override
    protected void initView(View view) {
//        data.add(new NewsFragment());
//        data.add(new ReBlogFragment());
//        data.add(new HotNewsFragment());
//        data.add(new LatestBlogFragment());

        initList();
        Log.e("TAG",titleList.size()+"=------=-=-=-==-");
        for(int i=0;i<titleList.size();i++){
            NewsFragment testFm = NewsFragment.newInstance(contentList, i);
            fragmentList.add(testFm);
        }

        zhViewpager.setOffscreenPageLimit(4);
        title_adapter = new ZHPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList, titleList);

        zhTab.setupWithViewPager(zhViewpager);

        zhViewpager.setAdapter(title_adapter);
    }

    public void initList(){

        String[] nametitle = getResources().getStringArray(R.array.newsTitle);
        for (int i=0;i<nametitle.length;i++){
            titleList.add(nametitle[i]);
            Log.e("TAG",nametitle[i]);
        }

        contentList.add(0,"开源资讯");
        contentList.add(1,"推荐博客");
        contentList.add(2,"热门资讯");
        contentList.add(3,"最新博客");

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.iv_ic_add)
    public void onViewClicked() {
        if (iv_rotation){
            ivIcAdd.startAnimation(animation);
            iv_rotation = false;
        }else{
            ivIcAdd.startAnimation(animation1);
            iv_rotation = true;
        }

    }

    public void upPopupWindow(){
        PopupWindow popupWindow = new PopupWindow();

    }

}
