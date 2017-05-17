package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.FindOssFLModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/17.
 */
public class FindOssFlAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<FindOssFLModel.SoftwareTypeBean> fldata;

    public FindOssFlAdapter(Context context, ArrayList<FindOssFLModel.SoftwareTypeBean> fldata) {
        this.context = context;
        this.fldata = fldata;
    }

    @Override
    public int getCount() {
        return fldata.size();
    }

    @Override
    public Object getItem(int position) {
        return fldata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_find_oss_fl, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvOssFl.setText(fldata.get(position).getName());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_oss_fl)
        TextView tvOssFl;
        @Bind(R.id.iv_oss_img)
        ImageView ivOssImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
