package com.usian.android_app_oschina.model.entity;

import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

//@org.simpleframework.xml.Root(name = "oschina")
public class GetCommentModel {

    private String pagesize;
    private String allCount;
    private List<CommentBean> comments;
    private String notice;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getAllCount() {
        return allCount;
    }

    public void setAllCount(String allCount) {
        this.allCount = allCount;
    }

    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }

//    @org.simpleframework.xml.Root(name = "comment")
    public static class CommentBean {
        private String id;
        private String portrait;
        private String author;
        private String authorid;
        private String content;
        private String pubDate;
        private String appclient;
        private List<ReferBean> refers;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getAppclient() {
            return appclient;
        }

        public void setAppclient(String appclient) {
            this.appclient = appclient;
        }

        public List<ReferBean> getRefers() {
            return refers;
        }

        public void setRefers(List<ReferBean> refers) {
            this.refers = refers;
        }

//        @org.simpleframework.xml.Root(name = "refer")
        public static class ReferBean {
            private String refertitle;
            private String referbody;

            public String getRefertitle() {
                return refertitle;
            }

            public void setRefertitle(String refertitle) {
                this.refertitle = refertitle;
            }

            public String getReferbody() {
                return referbody;
            }

            public void setReferbody(String referbody) {
                this.referbody = referbody;
            }
        }
    }
}
