package com.usian.android_app_oschina;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.usian.android_app_oschina.adapter.MainAdapter;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.controller.fragment.zh_fragnemt.SynthesizeFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.title_name)
    TextView titleName;
    @Bind(R.id.title_toolbar)
    Toolbar titleToolbar;
    @Bind(R.id.pager)
    ViewPager pager;
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

    private ArrayList<Fragment> data = new ArrayList<>();
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        titleName.setText(R.string.title_zonghe);
        initData();
    }

    public void initData(){

        data.add(new SynthesizeFragment());

        adapter = new MainAdapter(getSupportFragmentManager(),data);

        pager.setAdapter(adapter);

    }

    @OnClick({R.id.btn_explore_comprehensive, R.id.btn_explore_move, R.id.iv_explore_plus, R.id.btn_explore_find, R.id.btn_explore_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_explore_comprehensive:
                titleName.setText(R.string.title_zonghe);
                pager.setCurrentItem(0);
                break;
            case R.id.btn_explore_move:
                break;
            case R.id.iv_explore_plus:
                break;
            case R.id.btn_explore_find:
                break;
            case R.id.btn_explore_my:
                break;
        }
    }
}
