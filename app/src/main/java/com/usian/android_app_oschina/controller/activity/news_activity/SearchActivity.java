package com.usian.android_app_oschina.controller.activity.news_activity;

import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @Bind(R.id.sv_global_search)
    SearchView svGlobalSearch;
    @Bind(R.id.tv_cencel)
    TextView tvCencel;
    @Bind(R.id.activity_search)
    LinearLayout activitySearch;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        svGlobalSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    @Override
    protected void loadData() {

    }



    @OnClick({R.id.sv_global_search, R.id.tv_cencel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cencel:
                finish();
                break;
        }
    }
}
