package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.controller.activity.OpenActivity;
import com.usian.android_app_oschina.model.entity.HotNewsModel;

import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/12.
 */

public class HotNewsAdapter extends BaseAdapter<HotNewsModel.NewsBean> {

    public HotNewsAdapter(Context context, List<HotNewsModel.NewsBean> datas) {
        super(context, R.layout.item_open_hotnews, datas);
    }

    @Override
    public void convert(ViewHolder holder, final HotNewsModel.NewsBean newsBean) {

        holder.setText(R.id.tv_author, newsBean.getAuthor());
        holder.setText(R.id.tv_description, newsBean.getBody());
        holder.setText(R.id.tv_num, newsBean.getCommentCount());
        holder.setText(R.id.tv_pubdate, newsBean.getPubDate());
        holder.setText(R.id.tv_title, newsBean.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.activity, OpenActivity.class);
                intent.putExtra("id", newsBean.getId());
                App.activity.startActivity(intent);
            }
        });

    }
}
