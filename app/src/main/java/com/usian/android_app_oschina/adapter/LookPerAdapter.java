package com.usian.android_app_oschina.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.LookPerModel;

import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public class LookPerAdapter extends BaseAdapter{

    List<LookPerModel.UserBean> list;
    public LookPerAdapter(List<LookPerModel.UserBean> list){
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
        MineViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(), R.layout.find_people,null);
            holder = new MineViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.find_people_name);
            holder.home = (TextView) convertView.findViewById(R.id.find_people_home);
            holder.msg = (TextView) convertView.findViewById(R.id.find_people_msg);
            holder.pic = (ImageView) convertView.findViewById(R.id.find_people_pic);
            convertView.setTag(holder);
        }else{
            holder = (MineViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        holder.home.setText(list.get(position).getFrom());
        holder.msg.setText("积分0 | 关注0 | 粉丝0");
        Glide.with(parent.getContext()).
                load(list.get(position).getPortrait()).
                transform(new GlideCircleTransform(parent.getContext())).
                into(holder.pic);
        return convertView;
    }

    class MineViewHolder{
        TextView name,home,msg;
        ImageView pic;
    }

}
