package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.controller.activity.news_activity.NewsInfoActivity;
import com.usian.android_app_oschina.model.entity.LatestModel;
import com.usian.android_app_oschina.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        holder.setText(R.id.tv_title, newsBean.getTitle());

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = null;
        try {
            Date parse = sim.parse(newsBean.getPubDate());
            time = DateUtils.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.setText(R.id.tv_pubdate, time);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.activity, NewsInfoActivity.class);
                intent.putExtra("id", newsBean.getId());
                App.activity.startActivity(intent);
            }
        });

    }
}
