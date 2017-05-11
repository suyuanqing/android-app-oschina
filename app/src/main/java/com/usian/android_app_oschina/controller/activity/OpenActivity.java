package com.usian.android_app_oschina.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.utils.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * 开源资讯下的资讯详情
 */
public class OpenActivity extends AppCompatActivity {

    @Bind(R.id.infor_webview)
    WebView inforWebview;
    @Bind(R.id.send_comment)
    SearchView sendComment;
    @Bind(R.id.activity_open)
    LinearLayout activityOpen;
    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_icon_name)
    TextView titleIconName;
    @Bind(R.id.title_icon_toolbar)
    Toolbar titleIconToolbar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        ButterKnife.bind(this);

        url = getIntent().getStringExtra("url");
        LogUtils.e("OpenActivity", url);
        initData();

        titleIconName.setText(R.string.title_zixun_info);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void initData() {

        WebSettings webSettings = inforWebview.getSettings();

        webSettings.setJavaScriptEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        inforWebview.loadUrl(url);

//步骤3. 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        inforWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

}
