package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.FirstGpsBean;

import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 */

class HomeGPSAdapter extends RecyclerView.Adapter<HomeGPSAdapter.GPSHolder> {

    Context context;
    List<FirstGpsBean> gpslist;

    public interface GPSAdapterJieKou{
        void gpsChuan(int i);
    }

    GPSAdapterJieKou gpsAdapterJieKou;

    public void setGpsAdapterJieKou(GPSAdapterJieKou gpsAdapterJieKou) {
        this.gpsAdapterJieKou = gpsAdapterJieKou;
    }

    public HomeGPSAdapter(Context context, List<FirstGpsBean> gpslist) {
        this.context = context;
        this.gpslist = gpslist;
    }

    @Override
    public GPSHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_daohang_buju, parent,false);
        GPSHolder gpsHolder = new GPSHolder(view);
        return gpsHolder;
    }

    @Override
    public void onBindViewHolder(GPSHolder holder, final int position) {
        holder.imgid.setImageResource(gpslist.get(position).getImg());
        holder.name.setText(gpslist.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsAdapterJieKou.gpsChuan(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gpslist.size();
    }

    class GPSHolder extends RecyclerView.ViewHolder{

        public ImageView imgid;
        public TextView name;

        public GPSHolder(View itemView) {
            super(itemView);
            imgid = itemView.findViewById(R.id.home_daohang_img);
            name = itemView.findViewById(R.id.home_daohang_name);
        }
    }
}
