package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.CollectBean;
import com.ifeng_tech.treasuryyitong.bean.FirstGpsBean;
import com.ifeng_tech.treasuryyitong.bean.InformationBean;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 */

public class HomeAdapter extends RecyclerView.Adapter{
    Context context;
    List<Object> list;

    public HomeAdapter(Context context, List<Object> list) {
        this.context = context;
        this.list = list;
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
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.home_zixun, parent,false);
            HomeZiXun homeZiXun = new HomeZiXun(view);
            return homeZiXun;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            XBanner home_xBanner = ((HomeLunBo) holder).home_xBanner;
            final List<String> imgs = (List<String>) list.get(position);

            home_xBanner.setData(imgs,null);//设置数据源
            home_xBanner.setmAdapter(new XBanner.XBannerAdapter() {//xbanner的适配器，加载图片
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(imgs.get(position)).into((ImageView) view);
                }
            });
            home_xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, int position) {
                    MyUtils.setToast("=="+position);
                }
            });

        }else if(getItemViewType(position)==1){  // 导航
            GridView home_gridView = ((HomeDaoHang) holder).home_gridView;
            final List<FirstGpsBean> gpslist = (List<FirstGpsBean>) list.get(position);
            home_gridView.setAdapter(new HomeGPSAdapter(context,gpslist));
            home_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MyUtils.setToast(gpslist.get(position).getName());
                }
            });


        }else if(getItemViewType(position)==2){  // 征集
            RecyclerView home_zhengji_recyclerView = ((HomeZhengJi) holder).home_zhengji_recyclerView;
            List<CollectBean> collectlist = (List<CollectBean>) list.get(position);
            home_zhengji_recyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL,false));
            home_zhengji_recyclerView.setAdapter(new HomeCollectAdapter(context,collectlist));


        }else if(getItemViewType(position)==3){  // 托管
            RecyclerView home_tuoguan_recyclerView = ((HomeTuoGuan) holder).home_tuoguan_recyclerView;
            List<CollectBean> trusteeshiplist = (List<CollectBean>) list.get(position);
            home_tuoguan_recyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL,false));
            home_tuoguan_recyclerView.setAdapter(new HomeCollectAdapter(context,trusteeshiplist));


        }else{  // 资讯
            MyListView home_zixun_myListView = ((HomeZiXun) holder).home_zixun_myListView;
            RelativeLayout home_zixun_relativeLayout = ((HomeZiXun) holder).home_zixun_relativeLayout;
            List<InformationBean> informationlist = (List<InformationBean>) list.get(position);
            home_zixun_myListView.setAdapter(new HomeInformationAdapter(context,informationlist));
            home_zixun_relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyUtils.setToast("点击了资讯。。。");
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
        }else{
            return 4;
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

        public GridView home_gridView;

        public HomeDaoHang(View itemView) {
            super(itemView);
            home_gridView = itemView.findViewById(R.id.home_GridView);
        }
    }

    // 首页征集
    class HomeZhengJi extends RecyclerView.ViewHolder{

        public RecyclerView home_zhengji_recyclerView;

        public HomeZhengJi(View itemView) {
            super(itemView);
            home_zhengji_recyclerView = itemView.findViewById(R.id.home_zhengji_RecyclerView);
        }
    }

    // 首页托管
    class HomeTuoGuan extends RecyclerView.ViewHolder{

        public RecyclerView home_tuoguan_recyclerView;

        public HomeTuoGuan(View itemView) {
            super(itemView);
            home_tuoguan_recyclerView = itemView.findViewById(R.id.home_tuoguan_RecyclerView);
        }
    }

    // 首页资讯
    class  HomeZiXun extends RecyclerView.ViewHolder{

        public MyListView home_zixun_myListView;
        public RelativeLayout home_zixun_relativeLayout;

        public HomeZiXun(View itemView) {
            super(itemView);
            home_zixun_myListView = itemView.findViewById(R.id.home_zixun_MyListView);
            home_zixun_relativeLayout = itemView.findViewById(R.id.home_zixun_RelativeLayout);
        }
    }

}
