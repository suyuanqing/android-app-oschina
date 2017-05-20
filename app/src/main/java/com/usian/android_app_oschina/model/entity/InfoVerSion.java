package com.usian.android_app_oschina.model.entity;

/**
 * Created by 苏元庆 on 2017/5/19.
 */

public class InfoVerSion {


    /**
     * versionName : 1.0.1
     * versionCode : 2
     * contentDesc : 1.0.2鐗堟湰鐨刟pk淇浜嗙患鍚堟ā鍧椾腑鏁版嵁鍒锋柊鍗￠】鐨勯棶棰�
     * newApkUrl : http://192.168.137.117:8889/new.apk
     */

    private String versionName;
    private int versionCode;
    private String contentDesc;
    private String newApkUrl;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public String getNewApkUrl() {
        return newApkUrl;
    }

    public void setNewApkUrl(String newApkUrl) {
        this.newApkUrl = newApkUrl;
    }
}
