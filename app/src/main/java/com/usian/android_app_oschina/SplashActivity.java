package com.usian.android_app_oschina;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.model.entity.InfoVerSion;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import butterknife.Bind;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.start_img)
    ImageView startImg;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;
    private InfoVerSion infoVerSion;
    private ProgressDialog proDialog;

    private Handler hand = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            SplashActivity.this.finish();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        welcome();
       /* if (isNeedUpdate()) {
            showUpdateDialog(); //需要更新版本
        }else{


        }*/
    }

    public void welcome(){
        new Thread(){
            @Override
            public void run() {
//                TODO 发布项目时修改闪屏页面停留时间
                SystemClock.sleep(0);
                hand.sendEmptyMessage(999);
            }
        }.start();
    }

    @Override
    protected void initView() {
//        getInfo();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    //获取版本信息
    private int getVersion() {
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private boolean isNeedUpdate() {
        // 最新版本的版本号，info就是上面封装了更新日志信息的对象
        int v = infoVerSion.getVersionCode();
        if (v > getVersion()) {
            //需要升级
            return true;
        } else {
            //不需要升级，直接启动启动Activity
            return false;
        }
    }

    public void getInfo(){

        String url = "http://172.16.42.51:8889/newApkInfo.txt";

        RequestQueue reque = Volley.newRequestQueue(this);
        StringRequest stringrequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Gson gson = new Gson();
                infoVerSion = gson.fromJson(s, InfoVerSion.class);
                Log.e("TAG",infoVerSion.getVersionCode()+"---------------");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        reque.add(stringrequest);

    }

    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("请升级APP版本至" + infoVerSion.getVersionName());
        builder.setMessage(infoVerSion.getContentDesc());
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    downFile(infoVerSion.getNewApkUrl());//点击确定将apk下载
                } else {
                    Toast.makeText(SplashActivity.this, "SD卡不可用，请插入SD卡", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //用户点击了取消
            }
        });
        builder.create().show();
    }

    private void downFile(String newApkUrl) {
        RequestParams params = new RequestParams(newApkUrl);

        params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/myapp/");
        params.setAutoRename(true);

        x.http().post(params, new Callback.ProgressCallback<File>() {

            @Override
            public void onSuccess(File result) {
                //Apk下载完成后，调用系统的安装方法
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {


            }

            @Override
            public void onStarted() {
                proDialog.show();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

                proDialog.setMax((int) total);
                proDialog.setMessage("正在下载中"+current+"%");
            }
        });

    }

}
