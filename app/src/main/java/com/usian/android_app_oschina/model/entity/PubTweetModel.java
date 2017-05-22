package com.usian.android_app_oschina.model.entity;

/**
 * Created by 苏元庆 on 2017/5/22.
 */

//@org.simpleframework.xml.Root(name = "oschina")
public class PubTweetModel {


    private ResultBean result;
    private NoticeBean notice;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

//    @org.simpleframework.xml.Root(name = "result")
    public static class ResultBean {
        private String errorCode;
        private String errorMessage;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
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
