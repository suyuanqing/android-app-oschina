package com.usian.android_app_oschina.controller.activity.find_activity;

import android.os.Vibrator;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;

import butterknife.Bind;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * 扫描二维码
 */
public class QrCodeActivity extends BaseActivity implements QRCodeView.Delegate {

    @Bind(R.id.zxingview)
    ZXingView zxingview;
    @Bind(R.id.activity_qr_code)
    RelativeLayout activityQrCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected void initView() {
        zxingview.startSpot();
        zxingview.setResultHandler(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        zxingview.startCamera();
        zxingview.startSpot();
        vibrate();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "打开相机出错", Toast.LENGTH_SHORT).show();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
