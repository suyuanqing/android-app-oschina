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

    //动弹apiurl
    public static final String TWEETURL = BASEURL+"action/api/tweet_list";


    //发现 开源软件 分类
    public static final String FIND_OSS_FL = BASEURL+"action/api/softwarecatalog_list";
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

}
