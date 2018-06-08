package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Collect_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/10.
 */

public class CollectAdapter extends BaseAdapter{
    Context context;
    List<Collect_Bean.DataBean.ListBean> list;

    public CollectAdapter(Context context, List<Collect_Bean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            view=View.inflate(context, R.layout.collect_list_item,null);
        }
        ImageView collect_img = view.findViewById(R.id.collect_img);
        TextView collect_name = view.findViewById(R.id.collect_name);
        TextView collect_cword = view.findViewById(R.id.collect_cword);
        TextView collect_title = view.findViewById(R.id.collect_title);
        TextView collect_text = view.findViewById(R.id.collect_text);
        ImageView collect_imgflag = view.findViewById(R.id.collect_imgflag);


        if(list.get(position).getGoodsImg()==null){
            collect_img.setImageResource(R.drawable.img_erroy);
        }else{
            Glide.with(context).load(list.get(position).getGoodsImg()).error(R.drawable.img_erroy).into(collect_img);
        }

        if(list.get(position).getGoodsName().length()>10){
            String name = list.get(position).getGoodsName().substring(0, 10);
            collect_name.setText(name+"...");
        }else{
            collect_name.setText(list.get(position).getGoodsName());
        }

        collect_cword.setText(""+list.get(position).getGoodsCode());

        if(list.get(position).getAgencyName().equals("")||list.get(position).getAgencyName()==null){
            collect_title.setText("福利特寄卖平台");
        }else{
            collect_title.setText(list.get(position).getAgencyName());
        }

        collect_text.setText(list.get(position).getResidueAmount()+"/"+list.get(position).getAllAmount());
        if(list.get(position).getStage()==4){ //  3 "征集未开始" 4 "征集中"
            collect_imgflag.setImageResource(R.drawable.dengdai);
        }else{
            collect_imgflag.setImageResource(R.drawable.kaishi);
        }

        return view;
    }
}
