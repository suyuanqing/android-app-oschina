package com.usian.android_app_oschina.controller.fragment.search_fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.model.entity.BlogInfoModel;
import com.usian.android_app_oschina.model.entity.FenLeiThree;
import com.usian.android_app_oschina.model.entity.NewsInfoModel;
import com.usian.android_app_oschina.model.entity.TqInfoModel;
import com.usian.android_app_oschina.model.http.biz.findbus.ILoadFind;
import com.usian.android_app_oschina.model.http.biz.findbus.LoadFindImpl;
import com.usian.android_app_oschina.model.http.biz.newsbus.ILoadNetNews;
import com.usian.android_app_oschina.model.http.biz.newsbus.LoadNewsImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 详情页面
 */

public class DetailsActivity extends BaseActivity implements View.OnClickListener {

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
    @Bind(R.id.infor_webview)
    WebView inforWebview;
    @Bind(R.id.info_rongqi)
    FrameLayout infoRongqi;
    @Bind(R.id.send_comment)
    LinearLayout sendComment;
    @Bind(R.id.info_collection)
    CheckBox infoCollection;
    @Bind(R.id.info_share)
    ImageView infoShare;
    @Bind(R.id.activity_open)
    LinearLayout activityOpen;
    private String id;
    private String name;
    private String url;
    private String inteurl;

    private ILoadNetNews iLoadNetNews;

    @Override
    public int getLayoutId() {
        return R.layout.activity_open;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        iLoadNetNews = new LoadNewsImpl();
        inteurl = getIntent().getStringExtra("url");
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        titleIconName.setText(name);
        getUrl();
        if (inteurl != null && !inteurl.equals("")) {

        }
        loadWebView();
    }

    @Override
    public void initListener() {
        infoShare.setOnClickListener(this);
        infoCollection.setOnClickListener(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onClick(View v) {
        //// TODO: 2017/5/14 分享和收藏功能 
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void getUrl() {
        if (name.equals("资讯详情")) {
            iLoadNetNews.getNewsId(id, new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    XStream xs = new XStream();
                    xs.alias("oschina", NewsInfoModel.class);
                    xs.alias("news", NewsInfoModel.NewsBean.class);
                    NewsInfoModel bean = (NewsInfoModel) xs.fromXML(result);

                    url = bean.getNews().getUrl();
                    loadWebView();
                }

                @Override
                public void onError(String errormsg) {

                }
            });
        } else if (name.equals("博客详情")) {
            iLoadNetNews.getBlogId(id, new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    XStream xs = new XStream();
//                    Log.e("DetailsActivity", result);
                    xs.alias("oschina", BlogInfoModel.class);
                    xs.alias("blog", BlogInfoModel.BlogBean.class);
                    BlogInfoModel bean = (BlogInfoModel) xs.fromXML(result);
                    url = bean.getBlog().getUrl();
                    loadWebView();
                }

                @Override
                public void onError(String errormsg) {

                }
            });
        } else if (name.equals("技术详情")) {
            iLoadNetNews.getQnnInfo(id, new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    XStream xs = new XStream();
                    xs.alias("oschina", TqInfoModel.class);
                    xs.alias("post", TqInfoModel.PostBean.class);
                    TqInfoModel bean = (TqInfoModel) xs.fromXML(result);
                    url = bean.getPost().getUrl();
                    loadWebView();
                }

                @Override
                public void onError(String errormsg) {

                }
            });
        } else if (name.equals("软件详情")) {
            ILoadFind iLoadFind = new LoadFindImpl();
            iLoadFind.getSoftInfo(id, new NetworkCallback() {
                @Override
                public void onSuccess(String result) {
                    XStream xStream = new XStream();
                    xStream.alias("oschina", FenLeiThree.class);
                    xStream.alias("software", FenLeiThree.SoftwareBean.class);
                    FenLeiThree o = (FenLeiThree) xStream.fromXML(result);
                    url = o.getSoftware().getUrl();

                }

                @Override
                public void onError(String errormsg) {

                }
            });
        }
    }

    private void loadWebView() {
        if (inteurl != null && !inteurl.equals("")) {
            inforWebview.loadUrl(inteurl);
            inforWebview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        } else {
            if (url != null) {
                inforWebview.loadUrl(url);
                inforWebview.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
