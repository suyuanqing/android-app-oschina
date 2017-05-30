package com.usian.android_app_oschina.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.EitMiModel;
import com.usian.android_app_oschina.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 苏元庆 on 2017/5/30.
 */

public class EitMiAdapter extends android.widget.BaseAdapter {

    private ArrayList<EitMiModel.ActiveBean> list;
    private String change_time;

    public EitMiAdapter(ArrayList<EitMiModel.ActiveBean> list){
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(), R.layout.item_eitmi,null);
            holder = new MyViewHolder();
            holder.pic = (ImageView) convertView.findViewById(R.id.aitewo_item_pic);
            holder.myname = (TextView) convertView.findViewById(R.id.aitewo_item_name);
            holder.msg = (TextView) convertView.findViewById(R.id.aitewo_item_msg);
            holder.time = (TextView) convertView.findViewById(R.id.aitewo_item_time);
            convertView.setTag(holder);
        }else{
            holder = (MyViewHolder) convertView.getTag();
        }

        holder.myname.setText(list.get(position).getAuthor());
        holder.msg.setText(list.get(position).getMessage());
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sim.parse(list.get(position).getPubDate());
            change_time = DateUtils.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.time.setText(change_time);
        Glide.with(App.context)
                .load(list.get(position).getPortrait())
                .transform(new GlideCircleTransform(parent.getContext()))
                .error(R.mipmap.ic_default_image)
                .into(holder.pic);
        return convertView;
    }

    class MyViewHolder{
        TextView myname,msg,time;
        ImageView pic;
    }
}


