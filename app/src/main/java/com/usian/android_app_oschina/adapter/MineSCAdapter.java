package com.usian.android_app_oschina.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.MineSCModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/26.
 */
public class MineSCAdapter extends BaseAdapter {

    private ArrayList<MineSCModel.FavoriteBean> data;

    public MineSCAdapter(ArrayList<MineSCModel.FavoriteBean> data) {
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
            convertView = View.inflate(parent.getContext(), R.layout.item_mine_sc, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvMineScTitle.setText(data.get(position).getTitle());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_mine_sc_title)
        TextView tvMineScTitle;
        @Bind(R.id.tv_mine_sc_name)
        TextView tvMineScName;
        @Bind(R.id.tv_mine_sc_date)
        TextView tvMineScDate;
        @Bind(R.id.iv_mine_sc)
        ImageView ivMineSc;
        @Bind(R.id.tv_mine_scnum)
        TextView tvMineScnum;
        @Bind(R.id.iv_mine_pinglun)
        ImageView ivMinePinglun;
        @Bind(R.id.tv_mine_pinglunnum)
        TextView tvMinePinglunnum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
