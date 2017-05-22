package com.usian.android_app_oschina.controller.activity.news_activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.TqInfoModel;
import com.usian.android_app_oschina.model.http.biz.newsbus.ILoadNetNews;
import com.usian.android_app_oschina.model.http.biz.newsbus.LoadNewsImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * 开源资讯下的资讯详情
 */
public class TqInfoActivity extends AppCompatActivity {


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
    @Bind(R.id.send_comment)
    LinearLayout sendComment;
    @Bind(R.id.info_collection)
    CheckBox infoCollection;
    @Bind(R.id.info_share)
    ImageView infoShare;
    @Bind(R.id.activity_tq_info)
    LinearLayout activityTqInfo;
    @Bind(R.id.tq_webview)
    WebView tqWebview;
    private String qnn_id;
    private ILoadNetNews iLoadNetNews;
    private String url;
    private ProgressDialog dialog;
    private String commentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tq_info);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    protected void initView() {
        titleIconName.setText(R.string.title_zixun_info);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                String pinglun = getIntent().getStringExtra("pinglun");
                tvInfoPinglun.setVisibility(View.VISIBLE);
                tvInfoPinglun.setText(pinglun);
            }
        });

    }

    public void initListener() {
        infoCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = infoCollection.isChecked();
                if (checked) {
                    Toast.makeText(TqInfoActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TqInfoActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void initData() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading");
        dialog.show();

        qnn_id = getIntent().getStringExtra("Qnnid");

        iLoadNetNews = new LoadNewsImpl();
        iLoadNetNews.getQnnInfo(qnn_id, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                dialog.dismiss();
                XStream xStream = new XStream();
                xStream.alias("oschina", TqInfoModel.class);
                xStream.alias("post", TqInfoModel.PostBean.class);
                xStream.alias("notice", TqInfoModel.NoticeBean.class);
                TqInfoModel o = (TqInfoModel) xStream.fromXML(result);
                url = o.getPost().getUrl();
                LogUtils.e("TAG","tq="+url);
                loadData();
            }

            @Override
            public void onError(String errormsg) {
                dialog.dismiss();
            }
        });

    }


    protected void loadData() {

        WebSettings webSettings = tqWebview.getSettings();

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

        tqWebview.loadUrl(url);

//步骤3. 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        tqWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("TqInfo.", "webview：" + url);

                view.loadUrl(url);
                return true;
            }
        });
    }

}
