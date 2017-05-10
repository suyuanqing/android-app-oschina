package com.usian.android_app_oschina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.InformationModel;

import java.util.ArrayList;

/**
 * Created by 苏元庆 on 2017/5/9.
 */
public class OpenAdapter extends RecyclerView.Adapter{
    private ArrayList<InformationModel.NewsBean> data;
    Context context;
    public OpenAdapter (Context context, ArrayList<InformationModel.NewsBean> data){
        this.data = data;
        this.context = context;
    }

    class OpenViewHolder extends RecyclerView.ViewHolder {
        TextView title,description;
        ImageView img_today;
        public OpenViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            img_today = (ImageView) itemView.findViewById(R.id.iv_today);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = View.inflate(parent.getContext(), R.layout.item_open_news,null);

        return new OpenViewHolder(v);
    }

    public interface Onclickitemre{
        void onclickitems(View v, int pos);
    }

    private Onclickitemre onclickitems;

    public void setOnClickitems(Onclickitemre onclickitems){
        this.onclickitems = onclickitems;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final OpenViewHolder openholder = (OpenViewHolder) holder;

        openholder.title.setText(data.get(position).getTitle());
        openholder.description.setText(data.get(position).getBody());

        openholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = openholder.getLayoutPosition();
                onclickitems.onclickitems(openholder.itemView,pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
