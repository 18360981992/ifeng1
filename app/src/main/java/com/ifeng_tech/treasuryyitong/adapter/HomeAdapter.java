package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.Collect_Bean;
import com.ifeng_tech.treasuryyitong.bean.FirstGpsBean;
import com.ifeng_tech.treasuryyitong.bean.Information_Zi_Bean;
import com.ifeng_tech.treasuryyitong.bean.my.Collocation_Subscribe_bean;
import com.ifeng_tech.treasuryyitong.ui.Delivery_Activity;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.Information_Details_Activity;
import com.ifeng_tech.treasuryyitong.ui.LoginActivity;
import com.ifeng_tech.treasuryyitong.ui.my.Collocation_Subscribe_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Donation_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.stx.xhb.xbanner.XBanner;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 */

public class HomeAdapter extends RecyclerView.Adapter{
    Context context;
    List<Object> list;
    private final HomePageActivity activity;
    private final boolean aBoolean;

    public HomeAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;

        activity = (HomePageActivity) context;

        aBoolean = DashApplication.sp.getBoolean(SP_String.ISLOGIN, false);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view = LayoutInflater.from(context).inflate(R.layout.home_lunbo, parent,false);
            HomeLunBo homeLunBo = new HomeLunBo(view);
            return homeLunBo;
        }else if(viewType==1){
            View view = LayoutInflater.from(context).inflate(R.layout.home_daohang, parent,false);
            HomeDaoHang homeDaoHang = new HomeDaoHang(view);
            return homeDaoHang;
        }else if(viewType==2){
            View view = LayoutInflater.from(context).inflate(R.layout.home_zhengji, parent,false);
            HomeZhengJi homeZhengJi = new HomeZhengJi(view);
            return homeZhengJi;
        }else if(viewType==3){
            View view = LayoutInflater.from(context).inflate(R.layout.home_tuoguan, parent,false);
            HomeTuoGuan homeTuoGuan = new HomeTuoGuan(view);
            return homeTuoGuan;
        }
        else if(viewType==4){
           View view = LayoutInflater.from(context).inflate(R.layout.home_guangao, parent,false);
            HomeGuangGao homeGuangGao = new HomeGuangGao(view);
            return homeGuangGao;
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.home_zixun, parent,false);
            HomeZiXun homeZiXun = new HomeZiXun(view);
            return homeZiXun;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            XBanner home_xBanner = ((HomeLunBo) holder).home_xBanner;
            final List<Integer> imgs = (List<Integer>) list.get(position);

            home_xBanner.setData(imgs,null);//设置数据源
            home_xBanner.setmAdapter(new XBanner.XBannerAdapter() {//xbanner的适配器，加载图片
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    ((ImageView) view).setImageResource(imgs.get(position));
//                    Glide.with(context).load(imgs.get(position)).into((ImageView) view);
                }
            });
            home_xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, int position) {
                    MyUtils.setToast("=="+position);
                }
            });


        }else if(getItemViewType(position)==1){  // 导航
            RecyclerView home_gridView = ((HomeDaoHang) holder).home_gridView;
            final List<FirstGpsBean> gpslist = (List<FirstGpsBean>) list.get(position);
            home_gridView.setLayoutManager(new GridLayoutManager(context,4));
            HomeGPSAdapter homeGPSAdapter = new HomeGPSAdapter(context, gpslist);

            home_gridView.setAdapter(homeGPSAdapter);
            homeGPSAdapter.setGpsAdapterJieKou(new HomeGPSAdapter.GPSAdapterJieKou() {
                @Override
                public void gpsChuan(int i) {
                    switch (i){
                        case 0:
//                            MyUtils.setToast("点击了扫一扫。。。");
                            if(aBoolean){
                                Intent intent = new Intent(activity, CaptureActivity.class);
                                activity.startActivityForResult(intent, DashApplication.ERWIMA_SAOMIAO_req);
                            }else{
                                Intent intent1 = new Intent(context, LoginActivity.class);
                                context.startActivity(intent1);
                                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            }
                            break;
                        case 1:
//                            MyUtils.setToast("点击了收货。。。");
                            if(aBoolean){
                                Intent intent1 = new Intent(context, Delivery_Activity.class);
                                context.startActivity(intent1);
                                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            }else{
                                Intent intent1 = new Intent(context, LoginActivity.class);
                                context.startActivity(intent1);
                                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            }
                            break;
                        case 2: // 转赠
                            if(aBoolean){
                                Intent intent = new Intent(context, Donation_Activity.class);
                                context.startActivity(intent);
                                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            }else{
                                Intent intent1 = new Intent(context, LoginActivity.class);
                                context.startActivity(intent1);
                                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            }
                            break;
                        case 3:
//                            MyUtils.setToast("点击了鉴定。。。");
                            if(aBoolean){
                                Intent intent2 = new Intent(context, Collocation_Subscribe_Activity.class);
                                context.startActivity(intent2);
                                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            }else{
                                Intent intent1 = new Intent(context, LoginActivity.class);
                                context.startActivity(intent1);
                                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            }
                            break;
                    }

                }
            });

        }else if(getItemViewType(position)==2){  // 征集
            RecyclerView home_zhengji_recyclerView = ((HomeZhengJi) holder).home_zhengji_recyclerView;
            RelativeLayout home_zhengji_RelativeLayout = ((HomeZhengJi) holder).home_zhengji_RelativeLayout;

            List<Collect_Bean.DataBean.ListBean> collectlist = (List<Collect_Bean.DataBean.ListBean>) list.get(position);
//            home_zhengji_recyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL,false));
//            home_zhengji_recyclerView.setAdapter(new HomeCollectAdapter(context,collectlist));
//
//            home_zhengji_RelativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    HomePageActivity.homePageActivity_JieKou.chuan(0); // 点击征集
//                }
//            });
        }else if(getItemViewType(position)==3){
            RecyclerView home_tuoguan_recyclerView = ((HomeTuoGuan) holder).home_tuoguan_recyclerView;
            RelativeLayout home_tuoguan_RelativeLayout = ((HomeTuoGuan) holder).home_tuoguan_RelativeLayout;

            List<Collocation_Subscribe_bean.DataBean.ListBean> trusteeshiplist = (List<Collocation_Subscribe_bean.DataBean.ListBean>) list.get(position);
            home_tuoguan_recyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.VERTICAL,false));
            home_tuoguan_recyclerView.setAdapter(new HomeCollocationAdapter(context,trusteeshiplist));

            home_tuoguan_RelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomePageActivity.homePageActivity_JieKou.chuan(2);// 点击托管
                }
            });

        }
        else if(getItemViewType(position)==4){  // 托管

            ImageView home_guanggao_img = ((HomeGuangGao) holder).home_guanggao_img;
            Integer imguri = (Integer) list.get(position);
            Glide.with(context).load(imguri).into(home_guanggao_img);
            home_guanggao_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomePageActivity.homePageActivity_JieKou.chuan(1); // 点击广告
                }
            });
        }else{  // 资讯
            RecyclerView home_zixun_myListView = ((HomeZiXun) holder).home_zixun_myListView;
            RelativeLayout home_zixun_relativeLayout = ((HomeZiXun) holder).home_zixun_relativeLayout;
            final List<Information_Zi_Bean.DataBean.ListBean> informationlist = (List<Information_Zi_Bean.DataBean.ListBean>) list.get(position);
            home_zixun_myListView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

            HomeInformationAdapter homeInformationAdapter = new HomeInformationAdapter(context, informationlist);
            home_zixun_myListView.setAdapter(homeInformationAdapter);

            homeInformationAdapter.setZiXunAdapterJieKou(new HomeInformationAdapter.ZiXunAdapterJieKou() {
                @Override
                public void ZiXunChuan(int i) {
//                    MyUtils.setToast("点击了资讯条目=="+i);
                    Intent intent = new Intent(context, Information_Details_Activity.class);
//                    intent.putExtra("InformationBean",informationlist.get(i));
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            });

            home_zixun_relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HomePageActivity.homePageActivity_JieKou.chuan(3);// 点击资讯
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else if(position==1){
            return 1;
        }else if(position==2){
            return 2;
        }else if(position==3){
            return 3;
        }else if(position==4){
            return 4;
        }else{
            return 5;
        }

    }

    // 首页轮播
    class HomeLunBo extends RecyclerView.ViewHolder{

        public XBanner home_xBanner;

        public HomeLunBo(View itemView) {
            super(itemView);
            home_xBanner = itemView.findViewById(R.id.home_XBanner);
        }
    }

    // 首页导航
    class HomeDaoHang extends RecyclerView.ViewHolder{

        public RecyclerView home_gridView;

        public HomeDaoHang(View itemView) {
            super(itemView);
            home_gridView = itemView.findViewById(R.id.home_GridView);
        }
    }

    // 首页征集
    class HomeZhengJi extends RecyclerView.ViewHolder{

        public RecyclerView home_zhengji_recyclerView;
        public RelativeLayout home_zhengji_RelativeLayout;

        public HomeZhengJi(View itemView) {
            super(itemView);
            home_zhengji_recyclerView = itemView.findViewById(R.id.home_zhengji_RecyclerView);
//            home_zhengji_RelativeLayout = itemView.findViewById(R.id.home_zhengji_RelativeLayout);
        }
    }

    class HomeGuangGao extends RecyclerView.ViewHolder{
        public ImageView home_guanggao_img;
        public HomeGuangGao(View itemView) {
            super(itemView);
            home_guanggao_img = itemView.findViewById(R.id.home_guanggao_img);
        }
    }

    // 首页托管
    class HomeTuoGuan extends RecyclerView.ViewHolder{

        public RecyclerView home_tuoguan_recyclerView;
        public RelativeLayout home_tuoguan_RelativeLayout;

        public HomeTuoGuan(View itemView) {
            super(itemView);
            home_tuoguan_recyclerView = itemView.findViewById(R.id.home_tuoguan_RecyclerView);
            home_tuoguan_RelativeLayout = itemView.findViewById(R.id.home_tuoguan_RelativeLayout);
        }
    }

    // 首页资讯
    class  HomeZiXun extends RecyclerView.ViewHolder{

        public RecyclerView home_zixun_myListView;
        public RelativeLayout home_zixun_relativeLayout;

        public HomeZiXun(View itemView) {
            super(itemView);
            home_zixun_myListView = itemView.findViewById(R.id.home_zixun_MyListView);
            home_zixun_relativeLayout = itemView.findViewById(R.id.home_zixun_RelativeLayout);
        }
    }

}
