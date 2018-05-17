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
import com.ifeng_tech.treasuryyitong.bean.CollectBean;
import com.ifeng_tech.treasuryyitong.ui.Authenticate_Details_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import java.util.List;

/**
 * Created by zzt on 2018/5/16.
 */

public class HomeCollocationAdapter extends RecyclerView.Adapter<HomeCollocationAdapter.HomeTuoGuan> {
    Context context;
    List<CollectBean> collectlist;

    public HomeCollocationAdapter(Context context, List<CollectBean> collectlist) {
        this.context = context;
        this.collectlist = collectlist;
    }

    @Override
    public HomeTuoGuan onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_zhengji_buju, parent,false);
        HomeTuoGuan homeTuoGuan = new HomeTuoGuan(view);
        return homeTuoGuan;
    }

    @Override
    public void onBindViewHolder(HomeTuoGuan holder, final int position) {
        holder.home_zhengji_img.setImageResource(collectlist.get(position).getImg());
        holder.home_daohang_name.setText(collectlist.get(position).getName());
        holder.home_zhengji_title.setText(collectlist.get(position).getTitle());
        holder.home_zhengji_text.setText(collectlist.get(position).getText());

        if(collectlist.get(position).getType()==0){ // 0==等待 1==未开始
            holder.home_zhengji_imgflag.setImageResource(R.drawable.dengdai);
        }else{
            holder.home_zhengji_imgflag.setImageResource(R.drawable.kaishi);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 首页 中征集的点击

                if(collectlist.get(position).getType()==0){ // 0==等待 1==未开始
                    Intent intent = new Intent(context, Authenticate_Details_Activity.class);
                    intent.putExtra("CollectBean",collectlist.get(position));
                    context.startActivity(intent);
                }else{
                    MyUtils.setToast("该商品还未开始托管。。。");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return collectlist.size();
    }

    class HomeTuoGuan extends RecyclerView.ViewHolder{

        public ImageView home_zhengji_img;
        public TextView home_daohang_name;
        public TextView home_zhengji_title;
        public TextView home_zhengji_text;
        public ImageView home_zhengji_imgflag;

        public HomeTuoGuan(View itemView) {
            super(itemView);
            home_zhengji_img = itemView.findViewById(R.id.home_zhengji_img);
            home_daohang_name = itemView.findViewById(R.id.home_zhengji_name);
            home_zhengji_title = itemView.findViewById(R.id.home_zhengji_title);
            home_zhengji_text = itemView.findViewById(R.id.home_zhengji_text);
            home_zhengji_imgflag = itemView.findViewById(R.id.home_zhengji_imgflag);

        }
    }
}
