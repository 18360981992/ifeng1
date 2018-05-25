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
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.CollectBean;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.LoginActivity;
import com.ifeng_tech.treasuryyitong.ui.my.Collect_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 */

public class HomeCollectAdapter extends RecyclerView.Adapter<HomeCollectAdapter.HomeZhengJi> {
    Context context;
    List<CollectBean> collectlist;
    private final HomePageActivity activity;
    private final boolean aBoolean;

    public HomeCollectAdapter(Context context, List<CollectBean> collectlist) {
        this.context = context;
        this.collectlist = collectlist;

        activity = (HomePageActivity) context;

        aBoolean = DashApplication.sp.getBoolean(SP_String.ISLOGIN, false);

    }

    @Override
    public HomeZhengJi onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_zhengji_buju, parent,false);
        HomeZhengJi homeZhengJi = new HomeZhengJi(view);
        return homeZhengJi;
    }

    @Override
    public void onBindViewHolder(HomeZhengJi holder, final int position) {
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

                if(aBoolean){
                    if(collectlist.get(position).getType()==0){ // 为0的时候可以点击进入征集页面
                        Intent intent = new Intent(context, Collect_Activity.class);
                        intent.putExtra("CollectBean",collectlist.get(position));
                        context.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                    }else{
                        MyUtils.setToast("该商品还未开始征集。。。");
                    }
                }else{
                    Intent intent1 = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });
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
        public ImageView home_zhengji_imgflag;

        public HomeZhengJi(View itemView) {
            super(itemView);
            home_zhengji_img = itemView.findViewById(R.id.home_zhengji_img);
            home_daohang_name = itemView.findViewById(R.id.home_zhengji_name);
            home_zhengji_title = itemView.findViewById(R.id.home_zhengji_title);
            home_zhengji_text = itemView.findViewById(R.id.home_zhengji_text);
            home_zhengji_imgflag = itemView.findViewById(R.id.home_zhengji_imgflag);

        }
    }
}
