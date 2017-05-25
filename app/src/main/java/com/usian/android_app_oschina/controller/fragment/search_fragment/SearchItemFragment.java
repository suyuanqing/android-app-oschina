package com.usian.android_app_oschina.controller.fragment.search_fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.LookPerAdapter;
import com.usian.android_app_oschina.adapter.SearchResultAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.model.entity.LookPerModel;
import com.usian.android_app_oschina.model.entity.SearchResultModel;
import com.usian.android_app_oschina.model.http.biz.searchbus.ILoadSearch;
import com.usian.android_app_oschina.model.http.biz.searchbus.LoadSearchImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.List;

import butterknife.Bind;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public class SearchItemFragment extends BaseFragment implements NetworkCallback{

    @Bind(R.id.search_result_list)
    ListView searchResultList;

    private ILoadSearch iLoadSearch;
    private int index = 1;
    private List<LookPerModel.UserBean> users;
    private List<SearchResultModel.ResultBean> results;

    public SearchItemFragment(int pos, String query) {
        iLoadSearch = new LoadSearchImpl();
        index = pos;
        if (pos == 1) {
            loadSoftWare(query);
        } else if (pos == 2) {
            loadPost(query);
        } else if (pos == 3) {
            loadBlog(query);
        } else if (pos == 4) {
            loadNews(query);
        } else if (pos == 5) {
            lookPerson(query);
        }
    }

    private void loadSoftWare(String query) {
        iLoadSearch.getSearchResult(Arguments.SEARCH_SOFTWARE, query, index+"", this);
    }

    private void loadPost(String query) {
        iLoadSearch.getSearchResult(Arguments.SEARCH_POST, query, index+"", this);
    }

    private void loadBlog(String query) {
        iLoadSearch.getSearchResult(Arguments.SEARCH_BLOG, query, index+"", this);
    }

    private void loadNews(String query) {
        iLoadSearch.getSearchResult(Arguments.SEARCH_NEWS, query, index+"", this);
    }

    private void lookPerson(String query) {
        iLoadSearch.getLookPerson(query, new NetworkCallback() {

            @Override
            public void onSuccess(String result) {
                XStream xStream = new XStream();
                xStream.alias("oschina", LookPerModel.class);
                xStream.alias("user", LookPerModel.UserBean.class);
                LookPerModel o = (LookPerModel) xStream.fromXML(result);
                users = o.getUsers();

                LookPerAdapter lookPerAdapter = new LookPerAdapter(users);

                if(searchResultList!=null){
                    searchResultList.setAdapter(lookPerAdapter);
                }

            }

            @Override
            public void onError(String errormsg) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.search_fragment_item;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {
        searchResultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (index == 1) {
                    Intent inte = new Intent(App.activity, DetailsActivity.class);
                    inte.putExtra("url",results.get(position).getUrl());
                    inte.putExtra("id",results.get(position).getObjid());
                    inte.putExtra("name","软件详情");
                    parent.getContext().startActivity(inte);
                } else if (index == 2) {
                    Intent inte = new Intent(App.activity, DetailsActivity.class);
                    inte.putExtra("url",results.get(position).getUrl());
                    inte.putExtra("id",results.get(position).getObjid());
                    inte.putExtra("name","问答详情");
                    parent.getContext().startActivity(inte);
                } else if (index == 3) {
                    Intent inte = new Intent(App.activity, DetailsActivity.class);
                    inte.putExtra("url",results.get(position).getUrl());
                    inte.putExtra("id",results.get(position).getObjid());
                    inte.putExtra("name","博客详情");
                    parent.getContext().startActivity(inte);
                } else if (index == 4) {
                    Intent inte = new Intent(App.activity, DetailsActivity.class);
                    inte.putExtra("url",results.get(position).getUrl());
                    inte.putExtra("id",results.get(position).getObjid());
                    inte.putExtra("name","资讯详情");
                    parent.getContext().startActivity(inte);
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onSuccess(String result) {
        XStream xstream = new XStream();
        xstream.alias("oschina", SearchResultModel.class);
        xstream.alias("result", SearchResultModel.ResultBean.class);
        SearchResultModel o = (SearchResultModel) xstream.fromXML(result);
        results = o.getResults();
        SearchResultAdapter resultAdapter = new SearchResultAdapter(results);
        if(searchResultList!=null){
            searchResultList.setAdapter(resultAdapter);
        }
    }

    @Override
    public void onError(String errormsg) {

    }
}
