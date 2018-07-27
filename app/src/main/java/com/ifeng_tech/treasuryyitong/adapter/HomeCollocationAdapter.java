package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.my.Collocation_Subscribe_bean;
import com.ifeng_tech.treasuryyitong.ui.my.tuoguan.Authenticate_Details_Activity;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import java.util.List;

/**
 * Created by zzt on 2018/5/16.
 *
 *  首页的托管适配器
 */

public class HomeCollocationAdapter extends RecyclerView.Adapter<HomeCollocationAdapter.HomeTuoGuan> {
    Context context;
    List<Collocation_Subscribe_bean.DataBean.ListBean> list;
    private final HomePageActivity activity;

    public HomeCollocationAdapter(Context context, List<Collocation_Subscribe_bean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
        activity = (HomePageActivity) context;
    }

    @Override
    public HomeTuoGuan onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_tuoguan_buju, parent,false);
        HomeTuoGuan homeTuoGuan = new HomeTuoGuan(view);
        return homeTuoGuan;
    }

    @Override
    public void onBindViewHolder(HomeTuoGuan holder, final int position) {
        if(list.get(position).getGoodsImg()==null){
            holder.home_tuoguan_img.setImageResource(R.drawable.img_erroy);
        }else{
            Glide.with(context).load(list.get(position).getGoodsImg()).error(R.drawable.img_erroy).into(holder.home_tuoguan_img);
        }

        if(list.get(position).getGoodsName().length()>18){
            String name = list.get(position).getGoodsName().substring(0, 18);
            holder.home_tuoguan_name.setText(name+"...");
        }else{
            holder.home_tuoguan_name.setText(list.get(position).getGoodsName());
        }

        String[] times = list.get(position).getApplyTime().split("\\/");
        holder.home_tuoguan_time.setText("截止日期:"+times[1]);

        holder.home_tuoguan_text.setText("托管进度:"+list.get(position).getCount()+"/"+list.get(position).getNumber());

        if(list.get(position).getState().equals("1")){ // 1==正在 2==等待
            holder.home_tuoguan_imgflag.setText("正在申请");
            holder.home_tuoguan_imgflag.setTextColor(context.getResources().getColor(R.color.zhuse));
            holder.home_tuoguan_imgflag.setBackground(context.getResources().getDrawable(R.drawable.zhengzai_shenqing));
        }else{
            holder.home_tuoguan_imgflag.setText("等待申请");
            holder.home_tuoguan_imgflag.setTextColor(context.getResources().getColor(R.color.color_666666));
            holder.home_tuoguan_imgflag.setBackground(context.getResources().getDrawable(R.drawable.dengdai_shenqing));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 首页 中征集的点击
            if(list.get(position).getState().equals("1")){ // 1==正在 2==等待
                Intent intent = new Intent(context, Authenticate_Details_Activity.class);
                intent.putExtra("CollocationBean",list.get(position));
                context.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }else{
                MyUtils.setToast("该商品还未开始托管...");
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
        public TextView home_tuoguan_imgflag;

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
