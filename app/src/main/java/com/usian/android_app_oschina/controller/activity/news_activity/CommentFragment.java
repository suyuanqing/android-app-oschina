package com.usian.android_app_oschina.controller.activity.news_activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.adapter.CommentAdapter;
import com.usian.android_app_oschina.base.BaseFragment;
import com.usian.android_app_oschina.model.entity.GetCommentModel;
import com.usian.android_app_oschina.model.http.biz.comment.ILoadComment;
import com.usian.android_app_oschina.model.http.biz.comment.LoadCommentImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public class CommentFragment extends BaseFragment implements NetworkCallback {

    @Bind(R.id.comment_list)
    RecyclerView commentList;
    @Bind(R.id.comment_shuaxin)
    LinearLayout commentShuaxin;

    private String news_comment_id;
    private String news_catalog;
    private boolean flag;
    private int index = 1;
    private String blog_comment_id;
    private String news_count;
    private ArrayList<GetCommentModel.CommentBean> commentdata = new ArrayList<>();
    private CommentAdapter commentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comment_news;
    }

    @Override
    protected void initData() {
        commentAdapter = new CommentAdapter(App.activity, commentdata);
        commentList.setAdapter(commentAdapter);
    }

    @Override
    protected void initView(View view) {
        news_comment_id = getParams().getString("news_comment_id");
        news_catalog = getParams().getString("news_catalog");
        blog_comment_id = getParams().getString("blog_comment_id");
        news_count = getParams().getString("news_count");
        flag = getParams().getBoolean("flag");

        commentList.setHasFixedSize(true);
        commentList.setLayoutManager(new LinearLayoutManager(App.getContext(), LinearLayoutManager.VERTICAL, false));
        commentList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        ILoadComment iLoadComment = new LoadCommentImpl();
        if (flag) {
            iLoadComment.getNewsCommentList(news_catalog, news_comment_id, index + "", this);
            LogUtils.e("TAG", "true");
        } else {
            iLoadComment.getBlogCommentList(blog_comment_id, index + "", this);
            LogUtils.e("TAG", "false");
        }

    }

    @Override
    public Bundle getParams() {
        return super.getParams();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(String result) {

        LogUtils.e("CommentFragment", "新闻" + result);
        XStream xStream = new XStream();
        xStream.alias("oschina", GetCommentModel.class);
        xStream.alias("comment", GetCommentModel.CommentBean.class);
        xStream.alias("refer", GetCommentModel.CommentBean.ReferBean.class);

        GetCommentModel o = (GetCommentModel) xStream.fromXML(result);
        List<GetCommentModel.CommentBean> comments = o.getComments();
        commentdata.addAll(comments);

        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                commentAdapter.notifyDataSetChanged();
                LogUtils.e("TAG", "adapter刷新");
            }
        });

    }

    @Override
    public void onError(String errormsg) {

    }

}
