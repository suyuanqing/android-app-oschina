package com.usian.android_app_oschina.controller.activity.find_activity;

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

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.model.entity.FenLeiThree;
import com.usian.android_app_oschina.model.http.biz.comment.CollectionBus;
import com.usian.android_app_oschina.model.http.biz.comment.SendBlogComment;
import com.usian.android_app_oschina.model.http.biz.findbus.ILoadFind;
import com.usian.android_app_oschina.model.http.biz.findbus.LoadFindImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * 软件详情
 *
 */
//TODO 尚未实现点赞   如果有时间， 就把动画完善
public class SoftInfoActivity extends AppCompatActivity {


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
    @Bind(R.id.send_comment)
    LinearLayout sendComment;
    @Bind(R.id.info_collection)
    CheckBox infoCollection;
    @Bind(R.id.info_share)
    ImageView infoShare;
    @Bind(R.id.activity_open)
    LinearLayout activityOpen;
    private String ident;
    private String url;
    private ProgressDialog dialog;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    protected void initView() {
        titleIconName.setText(R.string.title_soft_info);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        uid = (String) SPUtils.getParam(App.activity, "uid", "");
    }

    public void initListener() {
        infoCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = infoCollection.isChecked();
                if (checked) {
                    CollectionBus.getInstance().addCollection(SoftInfoActivity.this, uid, ident, Arguments.COLLECTION_SOFT);
                } else {
                    CollectionBus.getInstance().deleteCollection(SoftInfoActivity.this, uid, ident, Arguments.COLLECTION_SOFT);
                }
            }
        });


        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendBlogComment sendBlogComment = SendBlogComment.getInstance();
                sendBlogComment.popupView(SoftInfoActivity.this, ident, uid);

            }
        });

    }



    public void initData() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading");
        dialog.show();

        ident = getIntent().getStringExtra("ident");
        LogUtils.e("TAG", ident);
        ILoadFind iLoadFind = new LoadFindImpl();
        iLoadFind.getSoftInfo(ident, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                dialog.dismiss();
                LogUtils.e("TAG", result);
                XStream xStream = new XStream();
                xStream.alias("oschina", FenLeiThree.class);
                xStream.alias("software", FenLeiThree.SoftwareBean.class);
                FenLeiThree o = (FenLeiThree) xStream.fromXML(result);
                url = o.getSoftware().getUrl();
                loadData();
            }

            @Override
            public void onError(String errormsg) {

            }
        });

    }


    protected void loadData() {

        WebSettings webSettings = inforWebview.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        inforWebview.loadUrl(url);

        inforWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("NewsInfoActivity", "webview：" + url);

                view.loadUrl(url);
                return true;
            }
        });
    }

}
