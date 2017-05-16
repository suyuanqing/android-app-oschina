package com.usian.android_app_oschina.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 苏元庆 on 2017/5/16.
 */

public class SimDateUtils {

    public static String getTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt2 = null;
        try {
            dt2 = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 继续转换得到秒数的long型
        long lTime = dt2.getTime();
        long l = System.currentTimeMillis();
        int time = (int) (l - lTime) / 1000 / 60;
        if (time < 60) {
            return time / 10 + "分钟前";
        } else {
            int time1 = time / 60;
            Date date1 = new Date(l);
            String currentTime = sdf.format(date1);
            if (currentTime.substring(0, 10).equals(date.substring(0, 10))) {
                return time1 + "小时前";
            } else {
                if (currentTime.substring(5, 7).equals(date.substring(5, 7))) {
                    int currentDay = Integer.parseInt(currentTime.substring(8, 10));
                    int day = Integer.parseInt(date.substring(8, 10));
                    int i = currentDay - day;
                    if (i == 1) {
                        return "昨天";
                    } else if (i == 2) {
                        return "前天";
                    } else {
                        return i + "天前";
                    }
                } else {
                    int currentMonth = Integer.parseInt(currentTime.substring(5, 7));
                    int Month = Integer.parseInt(date.substring(5, 7));
                    int i = currentMonth - currentMonth;
                    return i + "月前";
                }
            }
        }
    }


}
