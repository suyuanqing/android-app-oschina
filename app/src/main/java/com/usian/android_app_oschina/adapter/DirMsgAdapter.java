package com.usian.android_app_oschina.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.DirMsgModel;
import com.usian.android_app_oschina.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/27.
 */
public class DirMsgAdapter extends BaseAdapter {

    private ArrayList<DirMsgModel.MessageBean> data;

    public DirMsgAdapter(ArrayList<DirMsgModel.MessageBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dirmsg, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(parent.getContext()).load(data.get(position).getPortrait())
                .transform(new GlideCircleTransform(parent.getContext()))
                .into(holder.mineFnsTitle);
            holder.mineSxName.setText(data.get(position).getFriendname());
            holder.mineSxContent.setText(data.get(position).getContent());

        String time = null;
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sim.parse(data.get(position).getPubDate());
            time = DateUtils.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

            holder.mineSxDate.setText(time);

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.mine_fns_title)
        ImageView mineFnsTitle;
        @Bind(R.id.mine_sx_name)
        TextView mineSxName;
        @Bind(R.id.mine_sx_content)
        TextView mineSxContent;
        @Bind(R.id.mine_sx_date)
        TextView mineSxDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
