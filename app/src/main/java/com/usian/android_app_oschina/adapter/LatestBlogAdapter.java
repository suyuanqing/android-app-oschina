package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.controller.activity.OpenActivity;
import com.usian.android_app_oschina.model.entity.LatestModel;

import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/12.
 */

public class LatestBlogAdapter extends BaseAdapter<LatestModel.BlogBean> {

    public LatestBlogAdapter(Context context, List<LatestModel.BlogBean> datas) {
        super(context, R.layout.item_open_latestblog, datas);
    }

    @Override
    public void convert(ViewHolder holder, final LatestModel.BlogBean newsBean) {

        holder.setText(R.id.tv_author, newsBean.getAuthorname());
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
