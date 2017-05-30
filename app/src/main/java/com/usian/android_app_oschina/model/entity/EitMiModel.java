package com.usian.android_app_oschina.model.entity;

import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/30.
 */

public class EitMiModel {

    private String activeCount;
    private String pagesize;
    private NoticeBean notice;
    private List<ActiveBean> activies;

    public String getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(String activeCount) {
        this.activeCount = activeCount;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public List<ActiveBean> getActivies() {
        return activies;
    }

    public void setActivies(List<ActiveBean> activies) {
        this.activies = activies;
    }

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

    public static class ActiveBean {
        private String id;
        private String portrait;
        private String author;
        private String authorid;
        private String catalog;
        private String objecttype;
        private String objectcatalog;
        private String objecttitle;
        private String appclient;
        private String url;
        private String objectID;
        private String message;
        private String commentCount;
        private String pubDate;
        private String tweetimage;
        private String tweetattach;
        private String objectreply;

        public String getObjectreply() {
            return objectreply;
        }

        public void setObjectreply(String objectreply) {
            this.objectreply = objectreply;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
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

        public String getCatalog() {
            return catalog;
        }

        public void setCatalog(String catalog) {
            this.catalog = catalog;
        }

        public String getObjecttype() {
            return objecttype;
        }

        public void setObjecttype(String objecttype) {
            this.objecttype = objecttype;
        }

        public String getObjectcatalog() {
            return objectcatalog;
        }

        public void setObjectcatalog(String objectcatalog) {
            this.objectcatalog = objectcatalog;
        }

        public String getObjecttitle() {
            return objecttitle;
        }

        public void setObjecttitle(String objecttitle) {
            this.objecttitle = objecttitle;
        }

        public String getAppclient() {
            return appclient;
        }

        public void setAppclient(String appclient) {
            this.appclient = appclient;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getObjectID() {
            return objectID;
        }

        public void setObjectID(String objectID) {
            this.objectID = objectID;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getTweetimage() {
            return tweetimage;
        }

        public void setTweetimage(String tweetimage) {
            this.tweetimage = tweetimage;
        }

        public String getTweetattach() {
            return tweetattach;
        }

        public void setTweetattach(String tweetattach) {
            this.tweetattach = tweetattach;
        }
    }
}
