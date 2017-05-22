package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.controller.activity.news_activity.TqInfoActivity;
import com.usian.android_app_oschina.model.entity.TQModel;
import com.usian.android_app_oschina.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 苏元庆 on 2017/5/21.
 */

public class TqAdapter extends BaseAdapter<TQModel.PostBean>{

    public TqAdapter(Context context, ArrayList<TQModel.PostBean> datas) {
        super(context, R.layout.item_tq_list, datas);
    }

    @Override
    public void convert(ViewHolder holder, final TQModel.PostBean postBean) {

        ImageView img = (ImageView) holder.itemView.findViewById(R.id.iv_tq_userTitle);
        Glide.with(App.activity).load(postBean.getPortrait())
                .transform(new GlideCircleTransform(App.activity))
                .into(img);

        holder.setText(R.id.tv_tq_author, "@"+postBean.getAuthor());
        holder.setText(R.id.tv_tq_content, postBean.getBody());
        holder.setText(R.id.tv_tq_problem, postBean.getTitle());

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = null;
        try {
            Date parse = sim.parse(postBean.getPubDate());
            time = DateUtils.format(parse);
            holder.setText(R.id.tv_tq_date, time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.setText(R.id.tv_num_tq, postBean.getAnswerCount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.activity, TqInfoActivity.class);
                intent.putExtra("Qnnid", postBean.getId());
                App.activity.startActivity(intent);
            }
        });

    }
}
