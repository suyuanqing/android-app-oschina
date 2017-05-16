package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.LatestTweetModel;

import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/14.
 */

public class TweetAdapter extends BaseAdapter<LatestTweetModel.TweetBean> {

    public TweetAdapter(Context context, List<LatestTweetModel.TweetBean> datas) {
        super(context, R.layout.item_tweet_latest, datas);
    }


    @Override
    public void convert(ViewHolder holder, LatestTweetModel.TweetBean tweetBean) {

        ImageView userImage = (ImageView) holder.itemView.findViewById(R.id.iv_tweet_userTitle);
        ImageView img = (ImageView) holder.itemView.findViewById(R.id.iv_tweet_image);

        Glide.with(App.activity).load(tweetBean.getPortrait())
                .transform(new GlideCircleTransform(App.activity))
                .into(userImage);

        if (tweetBean.getImgSmall() == null){
            img.setVisibility(View.GONE);
        }else{
            Glide.with(App.activity).load(tweetBean.getImgSmall())
                    .into(img);
        }

        holder.setText(R.id.tv_tweet_userName, tweetBean.getAuthor());
        holder.setText(R.id.tv_tweet_body, tweetBean.getBody());
        holder.setText(R.id.tv_tweet_date, tweetBean.getPubDate());
        holder.setText(R.id.btn_tweet_dianzan_pinglun, tweetBean.getCommentCount());

    }
}
