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
import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.Collect_Bean;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.LoginActivity;
import com.ifeng_tech.treasuryyitong.ui.my.Collect_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 */

public class HomeCollectAdapter extends RecyclerView.Adapter<HomeCollectAdapter.HomeZhengJi> {
    Context context;
    List<Collect_Bean.DataBean.ListBean> collectlist;
    private final HomePageActivity activity;
    private final boolean aBoolean;

    public HomeCollectAdapter(Context context, List<Collect_Bean.DataBean.ListBean> collectlist) {
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

        if(collectlist.get(position).getGoodsImg()==null){
            holder.home_zhengji_img.setImageResource(R.drawable.img_erroy);
        }else{
            Glide.with(context).load(collectlist.get(position).getGoodsImg()).error(R.drawable.img_erroy).into( holder.home_zhengji_img);
        }

        if(collectlist.get(position).getGoodsName().length()>18){
            String name = collectlist.get(position).getGoodsName().substring(0, 18);
            holder.home_daohang_name.setText(name+"...");
        }else{
            holder.home_daohang_name.setText(collectlist.get(position).getGoodsName());
        }

        if(collectlist.get(position).getAgencyName().equals("")||collectlist.get(position).getAgencyName()==null){
            holder.home_zhengji_title.setText("福利特寄卖平台");
        }else{
            holder.home_zhengji_title.setText(collectlist.get(position).getAgencyName());
        }

        holder.home_zhengji_text.setText(collectlist.get(position).getResidueAmount()+"/"+collectlist.get(position).getAllAmount());

        if(collectlist.get(position).getStage()==4){ //  3 "征集未开始" 4 "征集中"
            holder.home_zhengji_imgflag.setImageResource(R.drawable.dengdai);
        }else{
            holder.home_zhengji_imgflag.setImageResource(R.drawable.kaishi);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 首页 中征集的点击

                if(aBoolean){
                    if(collectlist.get(position).getStage()==4){ // 为0的时候可以点击进入征集页面
                        // 获取最大征集数量  和仓库进行比较
                        final int JIN_DU = collectlist.get(position).getAllAmount() - collectlist.get(position).getResidueAmount();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("goodsId",collectlist.get(position).getGoodsId()+"");
                        activity.myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
                            @Override
                            public void chenggong(String json) {
                                WarehouseBean warehouseBean = new Gson().fromJson(json, WarehouseBean.class);
                                if(warehouseBean.getCode().equals("2000")){
                                    if(warehouseBean.getData().getList().size()>0){
                                        WarehouseBean.DataBean.ListBean listBean = warehouseBean.getData().getList().get(0);
                                        if(listBean.getAvailableQty()>=JIN_DU){
                                            Intent intent = new Intent(activity, Collect_Activity.class);
                                            intent.putExtra("CollectBean",collectlist.get(position));
                                            intent.putExtra("MAX_NUM",JIN_DU);
                                            activity.startActivity(intent);
                                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                        }else{
                                            Intent intent = new Intent(activity, Collect_Activity.class);
                                            intent.putExtra("CollectBean",collectlist.get(position));
                                            intent.putExtra("MAX_NUM",listBean.getAvailableQty());
                                            activity.startActivity(intent);
                                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                        }
                                    }else{
                                        MyUtils.setToast("您的仓库中暂无该商品！");
                                    }
                                }else{
                                    MyUtils.setToast(warehouseBean.getMessage());
                                }
                            }

                            @Override
                            public void shibai(String ss) {
                                MyUtils.setToast(ss);
                            }
                        });

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
