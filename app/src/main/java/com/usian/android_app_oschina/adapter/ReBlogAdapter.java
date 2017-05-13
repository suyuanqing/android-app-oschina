package com.usian.android_app_oschina.adapter;

import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.ReBlogModel;

import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/12.
 */

public class ReBlogAdapter extends BaseAdapter<ReBlogModel.BlogBean> {

    public ReBlogAdapter(Context context, List<ReBlogModel.BlogBean> datas) {
        super(context, R.layout.item_open_reblog, datas);
    }

    @Override
    public void convert(ViewHolder holder, ReBlogModel.BlogBean newsBean) {
        holder.setText(R.id.tv_author, newsBean.getAuthorname());
        holder.setText(R.id.tv_description, newsBean.getBody());
        holder.setText(R.id.tv_num, newsBean.getCommentCount());
        holder.setText(R.id.tv_pubdate, newsBean.getPubDate());
        holder.setText(R.id.tv_title, newsBean.getTitle());
    }
}
