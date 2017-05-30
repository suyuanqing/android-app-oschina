package com.usian.android_app_oschina.model.http.biz.tweetbus;

import android.widget.Toast;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

/**
 * Created by 苏元庆 on 2017/5/30.
 */

public class ThumbUpLike {

    private ILoadTweet iLoadTweet;

    private ThumbUpLike(){
        iLoadTweet = new LoadTweetImpl();
    }

    private static ThumbUpLike thumbUpLike;

    public static ThumbUpLike getInstance(){

        if (thumbUpLike == null){
            synchronized (ThumbUpLike.class){
                if (thumbUpLike == null)
                    thumbUpLike = new ThumbUpLike();
            }
        }

        return thumbUpLike;
    }


    public void addLike(String tweetid, String tweetisid){
        String uid = (String) SPUtils.getParam(App.getContext(), "uid", "");
        iLoadTweet.addLike(uid, tweetid, tweetisid, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e("ADDLIKE", result);
                ThreadUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(App.getContext(), "点赞成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(String errormsg) {

            }
        });

    }

    public void deleteLike(String tweetid, String tweetisid){
        String uid = (String) SPUtils.getParam(App.getContext(), "uid", "");
        iLoadTweet.deleteLike(uid, tweetid, tweetisid, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e("DELETELIKE", result);
            }

            @Override
            public void onError(String errormsg) {

            }
        });
    }

}
