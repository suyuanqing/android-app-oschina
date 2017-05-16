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
import com.usian.android_app_oschina.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/12.
 */

public class HotNewsAdapter extends BaseAdapter<HotNewsModel.NewsBean> {

    private String time;

    public HotNewsAdapter(Context context, List<HotNewsModel.NewsBean> datas) {
        super(context, R.layout.item_open_hotnews, datas);
    }

    @Override
    public void convert(ViewHolder holder, final HotNewsModel.NewsBean newsBean) {

        holder.setText(R.id.tv_author, newsBean.getAuthor());
        holder.setText(R.id.tv_description, newsBean.getBody());
        holder.setText(R.id.tv_num, newsBean.getCommentCount());

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sim.parse(newsBean.getPubDate());
            time = DateUtils.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.setText(R.id.tv_pubdate, time);
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
