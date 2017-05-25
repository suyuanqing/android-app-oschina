package com.usian.android_app_oschina.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.SearchResultModel;

import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public class SearchResultAdapter extends BaseAdapter{

    List<SearchResultModel.ResultBean> list;
    public SearchResultAdapter(List<SearchResultModel.ResultBean> list){
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
            convertView = View.inflate(parent.getContext(), R.layout.item_softinfo,null);
            holder = new MineViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.item_soft_title);
            holder.msg = (TextView) convertView.findViewById(R.id.item_soft_content);
            convertView.setTag(holder);
        }else{
            holder = (MineViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position).getTitle());
        holder.msg.setText(list.get(position).getDescription());
        return convertView;
    }

    class MineViewHolder{
        TextView title,msg;
    }
}
