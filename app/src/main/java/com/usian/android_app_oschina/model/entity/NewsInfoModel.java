package com.usian.android_app_oschina.model.entity;

/**
 * Created by 苏元庆 on 2017/5/14.
 */

public class NewsInfoModel {


    private NewsBean news;
    private String blog;

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public NewsBean getNews() {
        return news;
    }

    public void setNews(NewsBean news) {
        this.news = news;
    }


    public static class NewsBean {
        private String id;
        private String title;
        private String url;
        private String body;
        private String commentCount;
        private String author;
        private String authorid;
        private String pubDate;
        private String softwarelink;
        private String softwarename;
        private String favorite;
        private String relativies;

        public String getRelativies() {
            return relativies;
        }

        public void setRelativies(String relativies) {
            this.relativies = relativies;
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

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
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

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getSoftwarelink() {
            return softwarelink;
        }

        public void setSoftwarelink(String softwarelink) {
            this.softwarelink = softwarelink;
        }

        public String getSoftwarename() {
            return softwarename;
        }

        public void setSoftwarename(String softwarename) {
            this.softwarename = softwarename;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }

    }
}
