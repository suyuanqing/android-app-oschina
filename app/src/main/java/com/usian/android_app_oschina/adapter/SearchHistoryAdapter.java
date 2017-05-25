package com.usian.android_app_oschina.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.sql.bean.SearchHistory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public class SearchHistoryAdapter extends BaseAdapter {

    private List<SearchHistory> searchHistories;

    public SearchHistoryAdapter(List<SearchHistory> searchHistories) {
        this.searchHistories = searchHistories;
    }

    @Override
    public int getCount() {
        return searchHistories.size();
    }

    @Override
    public Object getItem(int position) {
        return searchHistories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = View.inflate(parent.getContext(), R.layout.item_search_history, null);
            holder = new ViewHolder(convertView);
            holder.searchHisText = (TextView) convertView.findViewById(R.id.search_his_text);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.searchHisText.setText(searchHistories.get(position).getHistory());

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.search_his_text)
        TextView searchHisText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
