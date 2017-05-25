package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.controller.activity.find_activity.SoftInfoActivity;
import com.usian.android_app_oschina.model.entity.SoftInfoModel;

import java.util.ArrayList;

/**
 * Created by 苏元庆 on 2017/5/23.
 */
public class SoftAdapter extends BaseAdapter<SoftInfoModel.SoftwareBean>{


    public SoftAdapter(Context context, ArrayList<SoftInfoModel.SoftwareBean> datas) {
        super(context, R.layout.item_softinfo, datas);
    }

    @Override
    public void convert(ViewHolder holder, final SoftInfoModel.SoftwareBean softwareBean) {

        holder.setText(R.id.item_soft_title, softwareBean.getName());
        holder.setText(R.id.item_soft_content, softwareBean.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.subActivity, SoftInfoActivity.class);
                intent.putExtra("ident", softwareBean.getId());
                App.subActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
