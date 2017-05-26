package com.usian.android_app_oschina.model.http.biz.comment;

import com.usian.android_app_oschina.model.http.callback.NetworkCallback;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public interface ILoadComment {


    //发表新闻评论
    void pubNewsComment(String catalog, String id, String uid, String content, int isPostToMyZone, NetworkCallback networkCallback);

    //发表博客评论
    void pubBlogComment(String blog, String uid, String content, NetworkCallback networkCallback);

    //获取新闻评论列表
    void getNewsCommentList(String catalog, String id, String pageIndex, NetworkCallback networkCallback);

    //获取博客评论列表
    void getBlogCommentList(String id, String pageIndex, NetworkCallback networkCallback);

    //添加收藏
    void addCollection(String uid, String objid, String type, NetworkCallback networkCallback);

    //删除收藏
    void deleteCollection(String uid, String objid, String type, NetworkCallback networkCallback);

}
