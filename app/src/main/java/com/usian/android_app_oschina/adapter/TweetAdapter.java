package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.controller.activity.tweet_activity.TweetInfoActivity;
import com.usian.android_app_oschina.model.entity.LatestTweetModel;
import com.usian.android_app_oschina.model.http.biz.tweetbus.ThumbUpLike;
import com.usian.android_app_oschina.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 苏元庆 on 2017/5/14.
 */

public class TweetAdapter extends BaseAdapter<LatestTweetModel.TweetBean> {

    public TweetAdapter(Context context, List<LatestTweetModel.TweetBean> datas) {
        super(context, R.layout.item_tweet_latest, datas);
    }


    @Override
    public void convert(ViewHolder holder, final LatestTweetModel.TweetBean tweetBean) {

        ImageView userImage = (ImageView) holder.itemView.findViewById(R.id.iv_tweet_userTitle);
        ImageView img = (ImageView) holder.itemView.findViewById(R.id.iv_tweet_image);
        Button checkBox = (Button) holder.itemView.findViewById(R.id.btn_tweet_dianzan);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThumbUpLike.getInstance().addLike(tweetBean.getId(), tweetBean.getAuthorid());
            }
        });


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
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = null;
        try {
            Date parse = sim.parse(tweetBean.getPubDate());
            time = DateUtils.format(parse);
            holder.setText(R.id.tv_tweet_date, time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.setText(R.id.btn_tweet_dianzan_pinglun, tweetBean.getCommentCount());
        holder.setText(R.id.btn_tweet_dianzan, tweetBean.getAppclient());

        final String finalTime = time;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "你好"+tweetBean.getAuthor(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(App.activity, TweetInfoActivity.class);
                intent.putExtra("userInfo", tweetBean);
                intent.putExtra("time", finalTime);
                App.activity.startActivity(intent);
            }
        });


        ImageView imgtitle = (ImageView) holder.itemView.findViewById(R.id.iv_tweet_userTitle);

        imgtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "tupian"+tweetBean.getAuthor(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
