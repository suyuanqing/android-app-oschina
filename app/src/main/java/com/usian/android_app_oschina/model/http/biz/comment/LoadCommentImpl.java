package com.usian.android_app_oschina.model.http.biz.comment;

import com.usian.android_app_oschina.contact.Arguments;
import com.usian.android_app_oschina.contact.NetWork;
import com.usian.android_app_oschina.contact.Urls;
import com.usian.android_app_oschina.model.http.HttpFactory;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public class LoadCommentImpl implements ILoadComment{

    //发表新闻评论
    @Override
    public void pubNewsComment(String catalog, String id, String uid, String content, int isPostToMyZone, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("catalog", catalog);
        params.put("id", id);
        params.put("uid", uid);
        params.put("content", content);
        params.put("isPostToMyZone", isPostToMyZone+"");

        HttpFactory.create(NetWork.OKHTTP).doPost(Urls.NEWS_COMMENT_URL, params, networkCallback);
     }

    @Override
    public void pubBlogComment(String blog, String uid, String content, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("blog", blog);
        params.put("uid", uid);
        params.put("content", content);

        HttpFactory.create(NetWork.OKHTTP).doPost(Urls.BLOG_COMMENT_URL, params, networkCallback);
    }

    //获取新闻评论列表
    @Override
    public void getNewsCommentList(String catalog, String id, String pageIndex, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("catalog", catalog);
        params.put("id", id);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create(NetWork.OKHTTP).doGet(Urls.NEWS_COMMENTLIST_URL, params, networkCallback);
    }

    //获取博客评论列表
    @Override
    public void getBlogCommentList(String id, String pageIndex, NetworkCallback networkCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", Arguments.PAGESIZE+"");

        HttpFactory.create(NetWork.OKHTTP).doGet(Urls.BLOG_COMMENTLIST_URL, params, networkCallback);
    }

}
