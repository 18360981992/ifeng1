package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.CollocationBean;
import com.ifeng_tech.treasuryyitong.ui.Authenticate_Details_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/16.
 */

public class HomeCollocationAdapter extends RecyclerView.Adapter<HomeCollocationAdapter.HomeTuoGuan> {
    Context context;
    List<CollocationBean> list;

    public HomeCollocationAdapter(Context context, List<CollocationBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public HomeTuoGuan onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_tuoguan_buju, parent,false);
        HomeTuoGuan homeTuoGuan = new HomeTuoGuan(view);
        return homeTuoGuan;
    }

    @Override
    public void onBindViewHolder(HomeTuoGuan holder, final int position) {
        holder.home_tuoguan_img.setImageResource(list.get(position).getImg());
        holder.home_tuoguan_name.setText(list.get(position).getName());

        holder.home_tuoguan_text.setText(list.get(position).getText());

        if(list.get(position).getType()==0){ // 0==等待 1==未开始
            holder.home_tuoguan_imgflag.setImageResource(R.drawable.dengdai);
            Date date = new Date(list.get(position).getTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            holder.home_tuoguan_time.setText("截止时间:"+simpleDateFormat.format(date));

        }else{
            holder.home_tuoguan_imgflag.setImageResource(R.drawable.kaishi);
            // 状态处于为开始的时候，返回时间应该是当前时间
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            holder.home_tuoguan_time.setText("开始时间:"+simpleDateFormat.format(date));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 首页 中征集的点击

                if(list.get(position).getType()==0){ // 0==等待 1==未开始
                    Intent intent = new Intent(context, Authenticate_Details_Activity.class);
                    intent.putExtra("CollocationBean",list.get(position));
                    context.startActivity(intent);
                }else{
                    MyUtils.setToast("该商品还未开始托管。。。");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HomeTuoGuan extends RecyclerView.ViewHolder{

        public ImageView home_tuoguan_img;
        public TextView home_tuoguan_name;
        public TextView home_tuoguan_time;
        public TextView home_tuoguan_text;
        public ImageView home_tuoguan_imgflag;

        public HomeTuoGuan(View itemView) {
            super(itemView);
            home_tuoguan_img = itemView.findViewById(R.id.home_tuoguan_img);
            home_tuoguan_name = itemView.findViewById(R.id.home_tuoguan_name);
            home_tuoguan_time = itemView.findViewById(R.id.home_tuoguan_time);
            home_tuoguan_text = itemView.findViewById(R.id.home_tuoguan_text);
            home_tuoguan_imgflag = itemView.findViewById(R.id.home_tuoguan_imgflag);

        }
    }
}
