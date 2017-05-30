package com.usian.android_app_oschina.contact;

/**
 * Created by 苏元庆 on 2017/5/11.
 */

public class Urls {

    /**
     * baseurl
     */
    public static final String BASEURL = "http://www.oschina.net/";

    //新闻资讯
    public static final String NEWSURL = BASEURL+"action/api/news_list";

    //新闻id
    public static final String NEWS_ID_URL = BASEURL+"action/api/news_detail";
    public static final String BLOG_ID_URL = BASEURL+"action/api/blog_detail";

    //推荐博客
    public static final String RECOMMBLOG = BASEURL+"action/api/blog_list";

    //最新博客
    public static final String LATESTBLOG = BASEURL+"action/api/blog_list";

    //热门资讯
    public static final String HOTNEWS = BASEURL+"action/api/news_list";

    //技术问答
    public static final String QNN_URL = BASEURL+"action/api/post_list";
    //技术问答详情
    public static final String QNNINFO_URL = BASEURL+"action/api/post_detail";

    //动弹apiurl
    public static final String TWEETURL = BASEURL+"action/api/tweet_list";

    public static final String ADDTWEETLIKE = BASEURL + "action/api/tweet_like";
    public static final String DELETETWEETLIKE = BASEURL + "action/api/tweet_unlike";

    //发现 开源软件 分类
    public static final String FIND_OSS_FL = BASEURL+"action/api/softwarecatalog_list";

    public static final String FIND_OSS_FL_THREE = BASEURL+"action/api/softwaretag_list";
    //开源软件
    public static final String FIND_OSS_SOFT = BASEURL+"action/api/software_list";
    //软件详情
    public static final String SOFT_INFO_URL = BASEURL+"action/api/software_detail";
    //摇一摇资讯
    public static final String SHAKE_NEWS = BASEURL+"action/api/rock_rock";
    //线下活动
    public static final String OFF_EVENT_URL = BASEURL+"action/api/event_list";
    //活动详情
    public static final String EVENT_INFO_URL = BASEURL+"action/api/post_detail";

    //登录
    public static final String LOGIN_URL = BASEURL+"action/api/login_validate";

    //获取用户资料，我的信息
    public static final String MYINFO_URL = BASEURL+"action/api/my_information";
    //获取用户消息
    public static final String USER_MSG_URL = BASEURL+"action/api/active_list";
    public static final String DIR_MSG_URL = BASEURL+"action/api/message_list";
    //获取粉丝列表
    public static final String FNS_LIST_URL = BASEURL+"action/api/friends_list";
    public static final String SC_LIST_URL = BASEURL+"action/api/favorite_list";
    public static final String UPDATE_URL = BASEURL+"action/api/portrait_update";

    //弹一弹
    public static final String PUBTWEET_URL = BASEURL+"action/api/tweet_pub";

    //发表新闻评论
    public static final String NEWS_COMMENT_URL = BASEURL+"action/api/comment_pub";
    //发表博客评论
    public static final String BLOG_COMMENT_URL = BASEURL+"action/api/blogcomment_pub";
    //获取新闻评论列表
    public static final String NEWS_COMMENTLIST_URL = BASEURL+"action/api/comment_list";
    //获取博客评论列表
    public static final String BLOG_COMMENTLIST_URL = BASEURL+"action/api/blogcomment_list";

    //搜索的接口
    public static final String SEARCH_URL = BASEURL+"action/api/search_list";
    public static final String LOOK_PERSON_URL = BASEURL+"action/api/find_user";

    //添加收藏
    public static final String ADD_COLLECIOTN_URL = BASEURL+"action/api/favorite_add";
    //删除收藏
    public static final String DELETE_COLLECIOTN_URL = BASEURL+"action/api/favorite_delete";

}
