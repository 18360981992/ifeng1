package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.CollectBean;

import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 */

public class HomeCollectAdapter extends RecyclerView.Adapter<HomeCollectAdapter.HomeZhengJi> {
    Context context;
    List<CollectBean> collectlist;

    public HomeCollectAdapter(Context context, List<CollectBean> collectlist) {
        this.context = context;
        this.collectlist = collectlist;
    }

    @Override
    public HomeZhengJi onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_zhengji_buju, parent,false);
        HomeZhengJi homeZhengJi = new HomeZhengJi(view);
        return homeZhengJi;
    }

    @Override
    public void onBindViewHolder(HomeZhengJi holder, int position) {
        holder.home_zhengji_img.setImageResource(collectlist.get(position).getImg());
        holder.home_daohang_name.setText(collectlist.get(position).getName());
        holder.home_zhengji_title.setText(collectlist.get(position).getTitle());
        holder.home_zhengji_text.setText(collectlist.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return collectlist.size();
    }

    class HomeZhengJi extends RecyclerView.ViewHolder{

        public ImageView home_zhengji_img;
        public TextView home_daohang_name;
        public TextView home_zhengji_title;
        public TextView home_zhengji_text;

        public HomeZhengJi(View itemView) {
            super(itemView);
            home_zhengji_img = itemView.findViewById(R.id.home_zhengji_img);
            home_daohang_name = itemView.findViewById(R.id.home_zhengji_name);
            home_zhengji_title = itemView.findViewById(R.id.home_zhengji_title);
            home_zhengji_text = itemView.findViewById(R.id.home_zhengji_text);

        }
    }
}
