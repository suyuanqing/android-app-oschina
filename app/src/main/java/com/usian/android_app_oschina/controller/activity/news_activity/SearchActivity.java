package com.usian.android_app_oschina.controller.activity.news_activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.SearchAdapter;
import com.usian.android_app_oschina.adapter.SearchHistoryAdapter;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.controller.fragment.search_fragment.SearchItemFragment;
import com.usian.android_app_oschina.model.sql.bean.SearchHistory;
import com.usian.android_app_oschina.model.sql.mydatabase.MyDataBase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

//TODO 仍有bug尚未解决
public class SearchActivity extends BaseActivity {


    @Bind(R.id.sv_global_search)
    SearchView svGlobalSearch;
    @Bind(R.id.tv_cencel)
    TextView tvCencel;
    @Bind(R.id.activity_search)
    LinearLayout activitySearch;
    @Bind(R.id.search_history_list)
    ListView searchHistoryList;
    @Bind(R.id.search_tab)
    TabLayout searchTab;
    @Bind(R.id.search_pager)
    ViewPager searchPager;
    private Dao<SearchHistory, Integer> dao;
    private List<SearchHistory> searchHistories;
    private SearchHistoryAdapter searchAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        MyDataBase open = new MyDataBase(App.activity, "search.db", null, 1);
        try {
            dao = open.getDao(SearchHistory.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {
        svGlobalSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchHistory search = new SearchHistory();
                search.setHistory(query);
                try {
                    dao.create(search);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                startSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchTab.setVisibility(View.GONE);
                searchPager.setVisibility(View.GONE);
                try {
                    searchHistories = dao.queryForAll();
                    if (searchHistories.size() > 0) {
                        searchHistoryList.setVisibility(View.VISIBLE);
                        View view = LayoutInflater.from(App.activity).inflate(R.layout.item_search_empty, null);
                        if (searchHistoryList.getFooterViewsCount() == 0) {
                            searchHistoryList.addFooterView(view);
                        }
                        searchAdapter = new SearchHistoryAdapter(searchHistories);
                        searchHistoryList.setAdapter(searchAdapter);
                        setLintener();
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (int i = 0; i < searchHistories.size(); i++) {
                                    try {
                                        dao.delete(searchHistories.get(i));
                                        searchHistories = dao.queryForAll();
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                                searchAdapter = new SearchHistoryAdapter(searchHistories);
                                searchHistoryList.setAdapter(searchAdapter);
                            }
                        });
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });


        svGlobalSearch.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return true;
            }
        });

    }

    private void setLintener() {
        searchHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startSearch(searchHistories.get(position).getHistory());

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

    private void startSearch(String query) {

        searchHistoryList.setVisibility(View.GONE);
        searchTab.setVisibility(View.VISIBLE);
        searchPager.setVisibility(View.VISIBLE);
        ArrayList<BaseFragment> list = new ArrayList<>();
        list.add(new SearchItemFragment(1, query));
        list.add(new SearchItemFragment(2, query));
        list.add(new SearchItemFragment(3, query));
        list.add(new SearchItemFragment(4, query));
        list.add(new SearchItemFragment(5, query));
        String[] str = new String[]{"软件", "问答", "博客", "资讯", "找人"};
        SearchAdapter adapter = new SearchAdapter(getSupportFragmentManager(), str, list);
        searchPager.setAdapter(adapter);
        searchPager.setOffscreenPageLimit(5);
        searchTab.setupWithViewPager(searchPager);
    }

}
