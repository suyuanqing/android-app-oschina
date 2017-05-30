package com.usian.android_app_oschina.contact;

import com.usian.android_app_oschina.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/22.
 */

public class DataList {
    private List<String> strlists = new ArrayList<>();
    private List<Integer> images = new ArrayList<>();
    private static DataList datalist;
    private DataList(){
        strlists.add("源创会  上海南京站开始报名啦");
        strlists.add("人都说程序员人傻钱多死得快");
        strlists.add("十年后还会有今天的收入吗");
        strlists.add("四五十岁后还在编程的程序员");
        strlists.add("聊聊架构，洞悉架构之道");
        images.add(R.mipmap.a);
        images.add(R.mipmap.b);
        images.add(R.mipmap.c);
        images.add(R.mipmap.d);
        images.add(R.mipmap.e);
    }
    public static DataList getDatalist(){
        if(datalist==null){
            synchronized (DataList.class){
                if(datalist==null){
                    datalist = new DataList();
                }
            }
        }
        return datalist;
    }

    public List<String> getStrList(){

        return strlists;
    }

    public List<Integer> getImages(){

        return images;
    }

}
