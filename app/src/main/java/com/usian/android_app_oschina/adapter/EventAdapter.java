package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.bumptech.glide.Glide;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.OffEventModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 苏元庆 on 2017/5/20.
 */
public class EventAdapter extends BaseAdapter<OffEventModel.EventBean>{

    private Date date;

    public EventAdapter(Context context, ArrayList<OffEventModel.EventBean> datas) {
        super(context, R.layout.item_event, datas);
    }


    @Override
    public void convert(ViewHolder holder, OffEventModel.EventBean eventBean) {

        ImageView img = (ImageView) holder.itemView.findViewById(R.id.item_event_img);
        TextView tosignup = (TextView) holder.itemView.findViewById(R.id.item_event_tosign);
        Glide.with(context).load(eventBean.getCover()).error(R.mipmap.ic_default_image)
                .into(img);

        long l = System.currentTimeMillis();

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = eventBean.getStartTime();
        try {
            date = sim.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long tian = 24*3600*1000*3;

        if (date.getTime()-tian > l){
            tosignup.setText(R.string.fx_to_sign_up);
            tosignup.setTextColor(context.getResources().getColor(R.color.actionbar_background));
            tosignup.setBackground(context.getDrawable(R.drawable.to_sign_up_color));
        }else{
            tosignup.setText(R.string.fx_to_sign_down);
            tosignup.setTextColor(context.getResources().getColor(R.color.dan_bai));
            tosignup.setBackground(context.getDrawable(R.drawable.to_sign_down_color));
        }

        holder.setText(R.id.item_event_title, eventBean.getTitle());
        holder.setText(R.id.item_event_date, eventBean.getStartTime());

    }
}
