package com.usian.android_app_oschina.controller.activity.find_activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    private String inteurl;


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
        inteurl = getIntent().getStringExtra("inurl");
        if (inteurl != null && !inteurl.equals("")) {

        }
        loadWebView();
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

        ident = getIntent().getStringExtra("ident");
        LogUtils.e("TAG", ident);
        ILoadFind iLoadFind = new LoadFindImpl();
        iLoadFind.getSoftInfo(ident, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                XStream xStream = new XStream();
                xStream.alias("oschina", FenLeiThree.class);
                xStream.alias("software", FenLeiThree.SoftwareBean.class);
                FenLeiThree o = (FenLeiThree) xStream.fromXML(result);
                url = o.getSoftware().getUrl();
                loadWebView();
            }

            @Override
            public void onError(String errormsg) {

            }
        });

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

}
