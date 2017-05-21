package com.usian.android_app_oschina.controller.activity.find_activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.model.entity.ShakeNewsModel;
import com.usian.android_app_oschina.model.http.biz.findbus.ILoadFind;
import com.usian.android_app_oschina.model.http.biz.findbus.LoadFindImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

public class ShakeActivity extends BaseActivity implements NetworkCallback{


    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_icon_name)
    TextView titleIconName;
    @Bind(R.id.iv_info_img)
    ImageView ivInfoImg;
    @Bind(R.id.tv_info_pinglun)
    TextView tvInfoPinglun;
    @Bind(R.id.title_icon_toolbar)
    LinearLayout titleIconToolbar;
    @Bind(R.id.iv_shake)
    ImageView ivShake;
    @Bind(R.id.tv_yyy_choose)
    TextView tvYyyChoose;
    @Bind(R.id.rb_yyy_lipin)
    RadioButton rbYyyLipin;
    @Bind(R.id.rb_yyy_news)
    RadioButton rbYyyNews;
    @Bind(R.id.rg_yyy_choose)
    RadioGroup rgYyyChoose;
    @Bind(R.id.activity_shake)
    LinearLayout activityShake;
    @Bind(R.id.yyy_net_img)
    ImageView yyyNetImg;
    @Bind(R.id.yyy_net_title)
    TextView yyyNetTitle;
    @Bind(R.id.yyy_net_date)
    TextView yyyNetDate;
    @Bind(R.id.yyy_net_news)
    LinearLayout yyyNetNews;

    private SensorManager sensorManager;
    private Sensor sensor;
    private Vibrator vibrator;
    private static final int UPTATE_INTERVAL_TIME = 50;
    private static final int SPEED_SHRESHOLD = 40;//这个值调节灵敏度
    private long lastUpdateTime;
    private float lastX;
    private float lastY;
    private float lastZ;
    private Date parse;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shake;
    }

    @Override
    protected void initView() {
        titleIconName.setText(R.string.fx_yyy);
        ivInfoImg.setVisibility(View.GONE);
        tvInfoPinglun.setVisibility(View.GONE);
        yyyNetNews.setVisibility(View.GONE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    protected void initData() {
        if (sensorManager != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener,
                    sensor,
                    SensorManager.SENSOR_DELAY_GAME);//这里选择感应频率
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.title_back, R.id.rb_yyy_lipin, R.id.rb_yyy_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                vibrator.cancel();
                finish();
                break;
            case R.id.rb_yyy_lipin:
                yyyNetNews.setVisibility(View.GONE);
                tvYyyChoose.setText(R.string.fx_text_lipin);
                break;
            case R.id.rb_yyy_news:
                tvYyyChoose.setText(R.string.fx_text_news);
                break;
        }
    }


    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            long currentUpdateTime = System.currentTimeMillis();
            long timeInterval = currentUpdateTime - lastUpdateTime;
            if (timeInterval < UPTATE_INTERVAL_TIME) {
                return;
            }
            lastUpdateTime = currentUpdateTime;
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            float deltaX = x - lastX;
            float deltaY = y - lastY;
            float deltaZ = z - lastZ;

            lastX = x;
            lastY = y;
            lastZ = z;
            double speed = (Math.sqrt(deltaX * deltaX + deltaY * deltaY
                    + deltaZ * deltaZ) / timeInterval) * 100;
            if (speed >= SPEED_SHRESHOLD) {
                vibrator.vibrate(300);

                if (rbYyyLipin.isChecked()) {

                    tvYyyChoose.setText(R.string.fx_text_huodong);
                } else if (rbYyyNews.isChecked()) {

                    tvYyyChoose.setText("正在获取资讯");
                    ILoadFind iLoadFind = new LoadFindImpl();
                    iLoadFind.getShakeNews(ShakeActivity.this);
                }


            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    @Override
    public void onSuccess(String result) {
        yyyNetNews.setVisibility(View.VISIBLE);
        tvYyyChoose.setText(R.string.fx_text_news);
        XStream xStream = new XStream();
        xStream.alias("oschina", ShakeNewsModel.class);
        ShakeNewsModel o = (ShakeNewsModel) xStream.fromXML(result);

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            parse = sim.parse(o.getPubDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String format = DateUtils.format(parse);

        Glide.with(App.subActivity).load(o.getImage()).error(R.mipmap.ic_split_graph)
                .placeholder(R.mipmap.ic_split_graph).into(yyyNetImg);
        yyyNetTitle.setText(o.getTitle());
        yyyNetDate.setText(format);

    }

    @Override
    public void onError(String errormsg) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (sensorManager != null) {// 取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
}
