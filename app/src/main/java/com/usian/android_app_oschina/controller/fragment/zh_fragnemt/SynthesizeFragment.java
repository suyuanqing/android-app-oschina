package com.usian.android_app_oschina.controller.fragment.zh_fragnemt;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.DragAdapter;
import com.usian.android_app_oschina.adapter.ZHPagerAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.view.DragGridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 苏元庆 on 2017/5/9.
 * 动弹的Fragment。
 */

public class SynthesizeFragment extends BaseFragment {


    @Bind(R.id.zh_tab)
    TabLayout zhTab;
    @Bind(R.id.zh_delete)
    Button zhDelete;
    @Bind(R.id.zh_lanmu)
    LinearLayout zhLanmu;
    @Bind(R.id.iv_ic_add)
    ImageView ivIcAdd;
    @Bind(R.id.zh_viewpager)
    ViewPager zhViewpager;
    private ZHPagerAdapter title_adapter;
    private Animation animation;
    private Animation animation1;
    private boolean iv_rotation = true;
    private DragGridView gridView;
    private DragGridView gridView_other;
    private DragAdapter dragAdapter;
    private DragAdapter other_adapter;


    private ArrayList<BaseFragment> fragmentList = new ArrayList<BaseFragment>(); //碎片链表
    private PopupWindow popupWindow;
    private ArrayList<String> channels = new ArrayList<>();
    private ArrayList<String> channels_other = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_synthesize;
    }

    @Override
    protected void initData() {
        initList();
        zhViewpager.setOffscreenPageLimit(3);
        title_adapter = new ZHPagerAdapter(App.activity.getSupportFragmentManager(), fragmentList, channels);

        zhTab.setupWithViewPager(zhViewpager);
        zhTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        zhViewpager.setAdapter(title_adapter);
    }

    @Override
    protected void initView(View view) {
        zhLanmu.setVisibility(View.GONE);

        animation = AnimationUtils.loadAnimation(App.activity, R.anim.ic_add_rotation);
        animation.setFillAfter(true);
        iv_rotation = animation.getFillAfter();
        animation.setDuration(500);

        animation1 = AnimationUtils.loadAnimation(App.activity, R.anim.ic_add_rotation_false);
        animation1.setFillAfter(true);
        animation1.setDuration(500);

    }

    public void initList() {
        initDataOther();
        initDatatitle();

        Log.e("TAG", channels.size() + "=------=-=-=-==-");
        for (int i = 0; i < channels.size()-1 ; i++) {
            NewsFragment testFm = NewsFragment.newInstance(channels, i);
            fragmentList.add(testFm);
        }
        fragmentList.add(4, new TqFragment());

    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.zh_delete, R.id.iv_ic_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zh_delete:

                break;
            case R.id.iv_ic_add:

                if (iv_rotation) {
                    upPopupWindow();
                    ivIcAdd.startAnimation(animation);
                    startAnim();
                } else {
                    ivIcAdd.startAnimation(animation1);
                    endAnim();
                }
                break;
        }
    }

    private void startAnim() {

        zhLanmu.setVisibility(View.VISIBLE);
        zhTab.setVisibility(View.GONE);
        popupWindow.showAsDropDown(ivIcAdd, 10, 10, 10);
        iv_rotation = false;
    }


    private void endAnim() {
        zhLanmu.setVisibility(View.GONE);
        zhTab.setVisibility(View.VISIBLE);
        popupWindow.dismiss();
        iv_rotation = true;
    }


    public void upPopupWindow() {
        View v = LayoutInflater.from(App.activity).inflate(R.layout.activity_popup_columns, null);
        popupView(v);
        popupWindow = new PopupWindow(v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }


    public void popupView(View v) {

        gridView = (DragGridView) v.findViewById(R.id.gridView_channel);
        gridView_other = (DragGridView) v.findViewById(R.id.gridView_channel_other);

        gridView.setNumColumns(4);
        dragAdapter = new DragAdapter(App.activity, channels);
        gridView.setAdapter(dragAdapter);

        other_adapter = new DragAdapter(App.activity, channels_other);
        gridView_other.setAdapter(other_adapter);
        gridView_other.setNumColumns(4);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String channel = channels.get(position);
                if (position != 0 && position != 1 && position != 2 && position != 3
                        && position != 4){
                    channels.remove(position);
                    channels_other.add(channel);
                }

                dragAdapter.notifyDataSetChanged();
                other_adapter.notifyDataSetChanged();
                title_adapter.notifyDataSetChanged();
            }
        });
        gridView_other.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String channel = channels_other.get(position);
                channels_other.remove(position);
                channels.add(channel);
                dragAdapter.notifyDataSetChanged();
                other_adapter.notifyDataSetChanged();
                title_adapter.notifyDataSetChanged();
            }
        });

    }

    private void initDataOther() {
        channels_other.add("码云推荐");
        channels_other.add("最新翻译");
        channels_other.add("移动开发");
        channels_other.add("开源硬件");
        channels_other.add("云计算");
        channels_other.add("系统运维");
        channels_other.add("图像多媒体");
        channels_other.add("企业开发");
        channels_other.add("职业生涯");
        channels_other.add("行业杂烩");
        channels_other.add("推荐软件");
        channels_other.add("技术分享");
        channels_other.add("高手问答");
        channels_other.add("开源访谈");
        channels_other.add("游戏开发");
        channels_other.add("站务建议");
        channels_other.add("前端开发");
        channels_other.add("源创军");
        channels_other.add("数据库");
        channels_other.add("编程语言");
        channels_other.add("服务端开发");
        channels_other.add("软件工程");
        channels_other.add("热门博客");
    }

    private void initDatatitle() {
        channels.add(0, "开源资讯");
        channels.add(1, "推荐博客");
        channels.add(2, "热门资讯");
        channels.add(3, "最新博客");
        channels.add(4, "技术问答");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
