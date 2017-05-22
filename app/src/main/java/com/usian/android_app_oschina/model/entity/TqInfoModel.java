package com.usian.android_app_oschina.model.entity;

/**
 * Created by 苏元庆 on 2017/5/22.
 */

//@org.simpleframework.xml.Root(name = "oschina")
public class TqInfoModel {


    private PostBean post;
    private NoticeBean notice;

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

//    @org.simpleframework.xml.Root(name = "post")
    public static class PostBean {
        private String id;
        private String title;
        private String url;
        private String portrait;
        private String event;
        private String body;
        private String author;
        private String authorid;
        private String answerCount;
        private String viewCount;
        private String pubDate;
        private String favorite;
        private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getAnswerCount() {
            return answerCount;
        }

        public void setAnswerCount(String answerCount) {
            this.answerCount = answerCount;
        }

        public String getViewCount() {
            return viewCount;
        }

        public void setViewCount(String viewCount) {
            this.viewCount = viewCount;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }
    }

//    @org.simpleframework.xml.Root(name = "notice")
    public static class NoticeBean {
        private String atmeCount;
        private String msgCount;
        private String reviewCount;
        private String newFansCount;
        private String newLikeCount;

        public String getAtmeCount() {
            return atmeCount;
        }

        public void setAtmeCount(String atmeCount) {
            this.atmeCount = atmeCount;
        }

        public String getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(String msgCount) {
            this.msgCount = msgCount;
        }

        public String getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(String reviewCount) {
            this.reviewCount = reviewCount;
        }

        public String getNewFansCount() {
            return newFansCount;
        }

        public void setNewFansCount(String newFansCount) {
            this.newFansCount = newFansCount;
        }

        public String getNewLikeCount() {
            return newLikeCount;
        }

        public void setNewLikeCount(String newLikeCount) {
            this.newLikeCount = newLikeCount;
        }
    }
}
