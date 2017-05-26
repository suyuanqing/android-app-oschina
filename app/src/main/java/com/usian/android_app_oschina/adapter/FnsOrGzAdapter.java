package com.usian.android_app_oschina.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.FnsOrGZModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/26.
 */
public class FnsOrGzAdapter extends BaseAdapter {

    private ArrayList<FnsOrGZModel.FriendBean> data;

    public FnsOrGzAdapter(ArrayList<FnsOrGZModel.FriendBean> data) {
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
            convertView = View.inflate(parent.getContext(), R.layout.item_fns_gz, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

            Glide.with(parent.getContext()).load(data.get(position).getPortrait())
                    .transform(new GlideCircleTransform(parent.getContext()))
                    .error(R.mipmap.ic_default_image)
                    .into(holder.mineFnsTitle);
            holder.mineFnsName.setText(data.get(position).getName());
            holder.mineFnsFrom.setText(data.get(position).getFrom());
            holder.mineFnsWu.setText(data.get(position).getExpertise());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.mine_fns_title)
        ImageView mineFnsTitle;
        @Bind(R.id.mine_fns_name)
        TextView mineFnsName;
        @Bind(R.id.mine_fns_from)
        TextView mineFnsFrom;
        @Bind(R.id.mine_fns_wu)
        TextView mineFnsWu;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
