package com.usian.android_app_oschina;

import android.content.Intent;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.base.BaseMainActivity;
import com.usian.android_app_oschina.controller.activity.SearchActivity;
import com.usian.android_app_oschina.controller.fragment.dt_fragment.TweetFragnemt;
import com.usian.android_app_oschina.controller.fragment.fx_fragment.FindFragment;
import com.usian.android_app_oschina.controller.fragment.my_fragment.MineFragment;
import com.usian.android_app_oschina.controller.fragment.zh_fragnemt.SynthesizeFragment;
import com.usian.android_app_oschina.utils.FragmentBuilder;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseMainActivity {


    @Bind(R.id.title_name)
    TextView titleName;
    @Bind(R.id.iv_btn_search_normal)
    ImageView ivBtnSearchNormal;
    @Bind(R.id.title_toolbar)
    Toolbar titleToolbar;
    @Bind(R.id.pager)
    FrameLayout pager;
    @Bind(R.id.linn)
    LinearLayout linn;
    @Bind(R.id.btn_explore_comprehensive)
    RadioButton btnExploreComprehensive;
    @Bind(R.id.btn_explore_move)
    RadioButton btnExploreMove;
    @Bind(R.id.iv_explore_plus)
    ImageView ivExplorePlus;
    @Bind(R.id.btn_explore_find)
    RadioButton btnExploreFind;
    @Bind(R.id.btn_explore_my)
    RadioButton btnExploreMy;
    @Bind(R.id.btns)
    RadioGroup btns;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        titleName.setText(R.string.title_zonghe);
        FragmentBuilder.getInstance().containerId(R.id.pager).start(SynthesizeFragment.class).build();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.btn_explore_comprehensive, R.id.btn_explore_move, R.id.iv_explore_plus, R.id.btn_explore_find, R.id.btn_explore_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_explore_comprehensive:
                findViewById(R.id.title_include).setVisibility(View.VISIBLE);
                titleName.setText(R.string.title_zonghe);
                FragmentBuilder.getInstance().containerId(R.id.pager).start(SynthesizeFragment.class).build();
                break;
            case R.id.btn_explore_move:
                findViewById(R.id.title_include).setVisibility(View.VISIBLE);
                titleName.setText(R.string.title_dongtan);
                FragmentBuilder.getInstance().containerId(R.id.pager).start(TweetFragnemt.class).build();
                break;
            case R.id.iv_explore_plus:

                break;
            case R.id.btn_explore_find:
                findViewById(R.id.title_include).setVisibility(View.VISIBLE);
                titleName.setText(R.string.title_faxian);
                FragmentBuilder.getInstance().containerId(R.id.pager).start(FindFragment.class).build();
                break;
            case R.id.btn_explore_my:
                findViewById(R.id.title_include).setVisibility(View.GONE);
                FragmentBuilder.getInstance().containerId(R.id.pager).start(MineFragment.class).build();
                break;
        }
    }

    //    TODO 搜索
    @OnClick(R.id.iv_btn_search_normal)
    public void onViewClicked() {
        startActivity(new Intent(this, SearchActivity.class));
    }


    /**
     * 捕获back键 当back键被按下时
     */
    @Override
    public void onBackPressed() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
        String name = entry.getName();
        if("SynthesizeFragment".equals(name) || "TweetFragnemt".equals(name)
                || "MineFragment".equals(name) || "FindFragment".equals(name)){
            Process.killProcess(Process.myPid());
            System.exit(0);
        }else {
            manager.popBackStackImmediate();
            String fragmentName = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
            BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
            FragmentBuilder.getInstance().setLastFragment(fragment);
        }

    }

}
