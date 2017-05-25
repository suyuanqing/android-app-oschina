package com.usian.android_app_oschina.controller.activity.news_activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.BlogInfoModel;
import com.usian.android_app_oschina.model.entity.NewsInfoModel;
import com.usian.android_app_oschina.model.http.biz.comment.SendBlogComment;
import com.usian.android_app_oschina.model.http.biz.newsbus.ILoadNetNews;
import com.usian.android_app_oschina.model.http.biz.newsbus.LoadNewsImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/***
 * 开源资讯下的资讯详情
 *
 */
//TODO 尚未实现点赞   如果有时间， 就把动画完善
public class BlogInfoActivity extends AppCompatActivity {


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
    private String news_id;
    private ILoadNetNews iLoadNetNews;
    private String url;
    private NewsInfoModel o;
    private ProgressDialog dialog;
    private String commentCount;


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
                    Toast.makeText(BlogInfoActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BlogInfoActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });


        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = (String) SPUtils.getParam(App.activity, "uid", "");
//                SendNewsComment sendComment = SendNewsComment.getInstance();
//                sendComment.popupView(BlogInfoActivity.this, 1+"", news_id, uid);

                SendBlogComment sendBlogComment = SendBlogComment.getInstance();
                sendBlogComment.popupView(BlogInfoActivity.this, news_id, uid);

            }
        });

        ivInfoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                infoCollection.setVisibility(View.GONE);
//                infoShare.setVisibility(View.GONE);
//                ivInfoImg.setVisibility(View.GONE);
//                tvInfoPinglun.setVisibility(View.GONE);
//                titleIconName.setText("返回");
//                FragmentUtils.getInstance().containerId(R.id.info_rongqi).start(CommentFragment.class).build();
                CommentFragment commentFragment = new CommentFragment();
                FragmentManager manager = getSupportFragmentManager();
                Bundle bun = new Bundle();
                bun.putString("blog_comment_id", news_id);
                bun.putString("news_count", commentCount);
                bun.putBoolean("flag", false);
                commentFragment.setParams(bun);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.info_rongqi, commentFragment, "CommentFragment").show(commentFragment);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

    }



    public void initData() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading");
        dialog.show();

        news_id = getIntent().getStringExtra("id");
        LogUtils.e("BlogInfoActivity", news_id);
        iLoadNetNews = new LoadNewsImpl();
        iLoadNetNews.getBlogId(news_id, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                dialog.dismiss();
                XStream xStream = new XStream();
                xStream.alias("oschina", BlogInfoModel.class);
                xStream.alias("blog", BlogInfoModel.BlogBean.class);
                BlogInfoModel o = (BlogInfoModel) xStream.fromXML(result);
                url = o.getBlog().getUrl();
                commentCount = o.getBlog().getCommentCount();
                LogUtils.e("BlogInfoActivity", url);
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
                LogUtils.e("NewsInfoActivity", "webview：" + url);

                view.loadUrl(url);
                return true;
            }
        });
    }

}
