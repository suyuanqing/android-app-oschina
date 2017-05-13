package com.usian.android_app_oschina.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 苏元庆 on 2017/5/12.
 *  自定义初始化页面的Viewpage （禁止滑动）
 */

public class MainViewPager extends ViewPager{

    public MainViewPager(Context context) {
        super(context);
    }

    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // 绝不允许在页面之间切换
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 绝不允许在页面之间切换
        return false;
    }

}
