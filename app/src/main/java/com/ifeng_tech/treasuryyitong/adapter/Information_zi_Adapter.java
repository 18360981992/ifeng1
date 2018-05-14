package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Information_Zi_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/7.
 */

public class Information_zi_Adapter extends RecyclerView.Adapter {
    Context context;
    List<Information_Zi_Bean> topList;

    public interface Information_zi_Adapter_JieKou{
        void chuan(int postion);
    }

    Information_zi_Adapter_JieKou information_zi_Adapter_JieKou;

    public void setInformation_zi_Adapter_JieKou(Information_zi_Adapter_JieKou information_zi_Adapter_JieKou) {
        this.information_zi_Adapter_JieKou = information_zi_Adapter_JieKou;
    }

    public Information_zi_Adapter(Context context, List<Information_Zi_Bean> topList) {
        this.context = context;
        this.topList = topList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view = LayoutInflater.from(context).inflate(R.layout.information_zi_fragment_buju_right, parent,false);
            Information_zi_fragment_Right information_zi_fragment_right = new Information_zi_fragment_Right(view);
            return information_zi_fragment_right;

        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.information_zi_fragment_buju_bottom, parent,false);
            Information_zi_fragment_Bottom information_zi_fragment_Bottom = new Information_zi_fragment_Bottom(view);
            return information_zi_fragment_Bottom;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position)==0){
            ImageView information_zi_fragment_buju_right_img = ((Information_zi_fragment_Right) holder).information_zi_fragment_buju_right_img;
            TextView information_zi_fragment_buju_right_top = ((Information_zi_fragment_Right) holder).information_zi_fragment_buju_right_top;
            TextView information_zi_fragment_buju_right_text = ((Information_zi_fragment_Right) holder).information_zi_fragment_buju_right_text;
            TextView information_zi_fragment_buju_right_time = ((Information_zi_fragment_Right) holder).information_zi_fragment_buju_right_time;

            Glide.with(context).load(topList.get(position).getImags()[0]).into(information_zi_fragment_buju_right_img);
            information_zi_fragment_buju_right_top.setText(topList.get(position).getTop());
            information_zi_fragment_buju_right_text.setText("\t\t\t\t"+topList.get(position).getText());
            information_zi_fragment_buju_right_time.setText(topList.get(position).getTime());

            ((Information_zi_fragment_Right) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    information_zi_Adapter_JieKou.chuan(position);
                }
            });
        }else{
            TextView information_zi_fragment_buju_bottom_top = ((Information_zi_fragment_Bottom) holder).information_zi_fragment_buju_bottom_top;
            TextView information_zi_fragment_buju_bottom_text = ((Information_zi_fragment_Bottom) holder).information_zi_fragment_buju_bottom_text;
            ImageView information_zi_fragment_buju_bottom_img1 = ((Information_zi_fragment_Bottom) holder).information_zi_fragment_buju_bottom_img1;
            ImageView information_zi_fragment_buju_bottom_img2 = ((Information_zi_fragment_Bottom) holder).information_zi_fragment_buju_bottom_img2;
            ImageView information_zi_fragment_buju_bottom_img3 = ((Information_zi_fragment_Bottom) holder).information_zi_fragment_buju_bottom_img3;
            TextView information_zi_fragment_buju_bottom_time = ((Information_zi_fragment_Bottom) holder).information_zi_fragment_buju_bottom_time;

            information_zi_fragment_buju_bottom_top.setText(topList.get(position).getTop());
            information_zi_fragment_buju_bottom_text.setText("\t\t\t\t"+topList.get(position).getText());
            information_zi_fragment_buju_bottom_time.setText(topList.get(position).getTime());

            Glide.with(context).load(topList.get(position).getImags()[0]).into(information_zi_fragment_buju_bottom_img1);
            Glide.with(context).load(topList.get(position).getImags()[1]).into(information_zi_fragment_buju_bottom_img2);
            Glide.with(context).load(topList.get(position).getImags()[2]).into(information_zi_fragment_buju_bottom_img3);


            ((Information_zi_fragment_Bottom) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    information_zi_Adapter_JieKou.chuan(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(topList.get(position).getImags().length>1){ // 如果图片的长度大于1 就加载多图布局
            return 1;
        }else{
            return 0;
        }
    }

    class Information_zi_fragment_Right extends RecyclerView.ViewHolder{
        public TextView information_zi_fragment_buju_right_top;
        public TextView information_zi_fragment_buju_right_text;
        public TextView information_zi_fragment_buju_right_time;
        public ImageView information_zi_fragment_buju_right_img;

        public Information_zi_fragment_Right(View itemView) {
            super(itemView);
            information_zi_fragment_buju_right_top = itemView.findViewById(R.id.information_zi_fragment_buju_right_top);
            information_zi_fragment_buju_right_text = itemView.findViewById(R.id.information_zi_fragment_buju_right_text);
            information_zi_fragment_buju_right_time = itemView.findViewById(R.id.information_zi_fragment_buju_right_time);
            information_zi_fragment_buju_right_img = itemView.findViewById(R.id.information_zi_fragment_buju_right_img);
        }
    }

    class Information_zi_fragment_Bottom extends RecyclerView.ViewHolder{

        public TextView information_zi_fragment_buju_bottom_top;
        public TextView information_zi_fragment_buju_bottom_text;
        public ImageView information_zi_fragment_buju_bottom_img1;
        public ImageView information_zi_fragment_buju_bottom_img2;
        public ImageView information_zi_fragment_buju_bottom_img3;
        public TextView information_zi_fragment_buju_bottom_time;

        public Information_zi_fragment_Bottom(View itemView) {
            super(itemView);
            information_zi_fragment_buju_bottom_top = itemView.findViewById(R.id.information_zi_fragment_buju_bottom_top);
            information_zi_fragment_buju_bottom_text = itemView.findViewById(R.id.information_zi_fragment_buju_bottom_text);
            information_zi_fragment_buju_bottom_img1 = itemView.findViewById(R.id.information_zi_fragment_buju_bottom_img1);
            information_zi_fragment_buju_bottom_img2 = itemView.findViewById(R.id.information_zi_fragment_buju_bottom_img2);
            information_zi_fragment_buju_bottom_img3 = itemView.findViewById(R.id.information_zi_fragment_buju_bottom_img3);
            information_zi_fragment_buju_bottom_time = itemView.findViewById(R.id.information_zi_fragment_buju_bottom_time);
        }
    }
}
