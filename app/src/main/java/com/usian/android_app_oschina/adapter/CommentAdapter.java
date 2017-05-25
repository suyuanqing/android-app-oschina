package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.GetCommentModel;
import com.usian.android_app_oschina.utils.DateUtils;
import com.usian.android_app_oschina.utils.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/25.
 */
public class CommentAdapter extends BaseAdapter<GetCommentModel.CommentBean> {

    public CommentAdapter(Context context, List<GetCommentModel.CommentBean> datas) {
        super(context, R.layout.item_comment_list, datas);
    }

    @Override
    public void convert(ViewHolder holder, GetCommentModel.CommentBean commentBean) {

        ImageView titleimg = (ImageView) holder.itemView.findViewById(R.id.iv_comment_usertitle);
        Glide.with(App.getContext()).load(commentBean.getPortrait())
                .transform(new GlideCircleTransform(App.getContext()))
                .into(titleimg);

        holder.setText(R.id.tv_comment_name, commentBean.getAuthor());
        LogUtils.e("TAg", commentBean.getPortrait());

        String time = null;
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sim.parse(commentBean.getPubDate());
            time = DateUtils.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.setText(R.id.tv_comment_date, time);
        holder.setText(R.id.tv_comment_content, commentBean.getContent());
        LogUtils.e("TAg", commentBean.getContent());
    }
}
