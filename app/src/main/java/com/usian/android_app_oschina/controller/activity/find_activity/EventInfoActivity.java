package com.usian.android_app_oschina.controller.activity.find_activity;

import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.model.entity.EventInfoModel;
import com.usian.android_app_oschina.model.http.biz.findbus.ILoadFind;
import com.usian.android_app_oschina.model.http.biz.findbus.LoadFindImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class EventInfoActivity extends BaseActivity implements NetworkCallback {


    @Bind(R.id.event_title)
    ImageView eventTitle;
    @Bind(R.id.event_back)
    ImageView eventBack;
    @Bind(R.id.event_fenxiang)
    ImageView eventFenxiang;
    @Bind(R.id.event_shoucang)
    CheckBox eventShoucang;
    @Bind(R.id.event_rela)
    RelativeLayout eventRela;
    @Bind(R.id.event_page)
    WebView eventPage;
    @Bind(R.id.event_scroll)
    NestedScrollView eventScroll;
    @Bind(R.id.event_text_pinglun)
    TextView eventTextPinglun;
    @Bind(R.id.event_img)
    ImageView eventImg;
    @Bind(R.id.event_pinglun)
    RelativeLayout eventPinglun;
    @Bind(R.id.event_imgs)
    ImageView eventImgs;
    @Bind(R.id.event_baoming)
    RelativeLayout eventBaoming;
    @Bind(R.id.activity_event_info)
    LinearLayout activityEventInfo;
    private String eventid;
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_event_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        eventid = getIntent().getStringExtra("eventid");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        ILoadFind iLoadFind = new LoadFindImpl();
        iLoadFind.getEventInfo(eventid, this);

    }


    @OnClick({R.id.event_back, R.id.event_fenxiang, R.id.event_shoucang, R.id.event_pinglun, R.id.event_baoming})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.event_back:
                finish();
                break;
            case R.id.event_fenxiang:
                break;
            case R.id.event_shoucang:
                if (eventShoucang.isChecked()) {
                    Toast.makeText(EventInfoActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EventInfoActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.event_pinglun:

                break;
            case R.id.event_baoming:
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        XStream xStream = new XStream();
        xStream.alias("oschina", EventInfoModel.class);
        xStream.alias("post", EventInfoModel.PostBean.class);
        xStream.alias("event", EventInfoModel.PostBean.EventBean.class);

        EventInfoModel o = (EventInfoModel) xStream.fromXML(result);
        url = o.getPost().getUrl();
        Glide.with(App.subActivity).load(o.getPost().getEvent().getCover()).into(eventTitle);
        eventTextPinglun.setText("评论"+"("+o.getPost().getAnswerCount()+")");
        getWebView(url);
    }

    @Override
    public void onError(String errormsg) {

    }

    public void getWebView(String url) {

        WebSettings webSettings = eventPage.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        eventPage.loadUrl(url);
        Log.e("TAG", url + "webview");

        eventPage.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("NewsInfoActivity", "webview：" + url);

                view.loadUrl(url);
                return true;
            }
        });
    }


}
