package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.controller.activity.news_activity.BlogInfoActivity;
import com.usian.android_app_oschina.model.entity.ReBlogModel;
import com.usian.android_app_oschina.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 苏元庆 on 2017/5/9.
 */
public class BlogAdapter extends RecyclerView.Adapter{
    private ArrayList<ReBlogModel.BlogBean> data;
    Context context;
    private String time;

    public BlogAdapter(Context context, ArrayList<ReBlogModel.BlogBean> data){
        this.data = data;
        this.context = context;
    }

    class BlogViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,aite,num,padate;
        ImageView img;
        public BlogViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            aite = (TextView) itemView.findViewById(R.id.tv_author);
            num = (TextView) itemView.findViewById(R.id.tv_num);
            padate = (TextView) itemView.findViewById(R.id.tv_pubdate);
            img = (ImageView) itemView.findViewById(R.id.iv_today);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = View.inflate(parent.getContext(), R.layout.item_open_reblog,null);

        return new BlogViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final BlogViewHolder openholder = (BlogViewHolder) holder;

        openholder.title.setText(data.get(position).getTitle());
        openholder.description.setText(data.get(position).getBody());
        openholder.aite.setText(data.get(position).getAuthorname());
        openholder.num.setText(data.get(position).getCommentCount());

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sim.parse(data.get(position).getPubDate());
            time = DateUtils.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        openholder.padate.setText(time);

        openholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.activity, BlogInfoActivity.class);
                intent.putExtra("id", data.get(position).getId());
                App.activity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
