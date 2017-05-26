package com.usian.android_app_oschina.model.http.biz.comment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.contact.ATotalOf;
import com.usian.android_app_oschina.model.entity.CollectionModel;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.LogUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

/**
 * Created by 苏元庆 on 2017/5/26.
 */

public class CollectionBus {

    private CollectionBus (){

    }

    private static CollectionBus collectionBus;

    public static CollectionBus getInstance(){

        if (collectionBus == null){
            synchronized (CollectionBus.class){
                if (collectionBus == null)
                    collectionBus = new CollectionBus();
            }
        }
        return collectionBus;
    }

    public void addCollection(final Context context, String uid, String objid, String type){

        ILoadComment iLoadComment = new LoadCommentImpl();
        iLoadComment.addCollection(uid, objid, type, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e("Collection", result);
                XStream xStream = new XStream();
                xStream.alias("oschina", CollectionModel.class);
                xStream.alias("result", CollectionModel.ResultBean.class);
                final CollectionModel o = (CollectionModel) xStream.fromXML(result);

                if (o.getResult().getErrorCode().equals("1")){
                    ThreadUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, o.getResult().getErrorMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ATotalOf.SENTBROADACTION);
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        }
                    });

                }else{
                    ThreadUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "已添加该收藏", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onError(String errormsg) {

            }
        });

    }

    public void deleteCollection(final Context context, String uid, String objid, String type){

        ILoadComment iLoadComment = new LoadCommentImpl();
        iLoadComment.deleteCollection(uid, objid, type, new NetworkCallback() {
            @Override
            public void onSuccess(String result) {

                XStream xStream = new XStream();
                xStream.alias("oschina", CollectionModel.class);
                xStream.alias("result", CollectionModel.ResultBean.class);
                final CollectionModel o = (CollectionModel) xStream.fromXML(result);

                if (o.getResult().getErrorCode().equals("1")){
                    ThreadUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, o.getResult().getErrorMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ATotalOf.SENTBROADACTION);
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        }
                    });

                }else{
                    ThreadUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, o.getResult().getErrorMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onError(String errormsg) {

            }
        });

    }


}
