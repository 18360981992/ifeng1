package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.my.Collocation_Subscribe_bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/18.
 *
 *  托管预约 的适配器
 */

public class Collocation_Subscribe_list_Adapter extends BaseAdapter {
    Context context;
    List<Collocation_Subscribe_bean.DataBean.ListBean> list;

    public Collocation_Subscribe_list_Adapter(Context context, List<Collocation_Subscribe_bean.DataBean.ListBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.collocation_subscribe_list_item,null);
        }
        ImageView collocation_Subscribe_list_img = convertView.findViewById(R.id.collocation_Subscribe_list_img);
        TextView collocation_Subscribe_list_name = convertView.findViewById(R.id.collocation_Subscribe_list_name);
        TextView collocation_Subscribe_list_cword = convertView.findViewById(R.id.collocation_Subscribe_list_cword);
        TextView collocation_Subscribe_list_time = convertView.findViewById(R.id.collocation_Subscribe_list_time);
        TextView collocation_Subscribe_list_text = convertView.findViewById(R.id.collocation_Subscribe_list_text);
        ImageView collocation_Subscribe_list_imgflag = convertView.findViewById(R.id.collocation_Subscribe_list_imgflag);

        if(list.get(position).getGoodsImg()==null){
            collocation_Subscribe_list_img.setImageResource(R.drawable.guangao);
        }else{
            Glide.with(context).load(list.get(position).getGoodsImg()).error(R.drawable.img_erroy).into(collocation_Subscribe_list_img);
        }
        if(list.get(position).getGoodsName().length()>18){
            String name = list.get(position).getGoodsName().substring(0, 18);
            collocation_Subscribe_list_name.setText(name+"...");
        }else{
            collocation_Subscribe_list_name.setText(list.get(position).getGoodsName());
        }

        collocation_Subscribe_list_cword.setText(""+list.get(position).getGoodsCode());

        String[] times = list.get(position).getApplyTime().split("\\/");
        collocation_Subscribe_list_time.setText("截止时间:"+times[1]);


        collocation_Subscribe_list_text.setText("托管进度:"+list.get(position).getCount()+"/"+list.get(position).getNumber());

        if(list.get(position).getState().equals("1")){ // 1==等待 2==未开始
            collocation_Subscribe_list_imgflag.setImageResource(R.drawable.dengdai);
        }else{
            collocation_Subscribe_list_imgflag.setImageResource(R.drawable.kaishi);
        }
        return convertView;
    }
}
