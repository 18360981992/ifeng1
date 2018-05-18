package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.CollocationBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/18.
 */

public class Collocation_Subscribe_list_Adapter extends BaseAdapter {
    Context context;
    List<CollocationBean> list;

    public Collocation_Subscribe_list_Adapter(Context context, List<CollocationBean> list) {
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

        collocation_Subscribe_list_img.setImageResource(list.get(position).getImg());
        collocation_Subscribe_list_name.setText(list.get(position).getName());
        collocation_Subscribe_list_cword.setText(""+list.get(position).getCword());

        collocation_Subscribe_list_text.setText(list.get(position).getText());

        if(list.get(position).getType()==0){ // 0==等待 1==未开始
            collocation_Subscribe_list_imgflag.setImageResource(R.drawable.dengdai);
            Date date = new Date(list.get(position).getTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            collocation_Subscribe_list_time.setText("截止时间:"+simpleDateFormat.format(date));

        }else{
            collocation_Subscribe_list_imgflag.setImageResource(R.drawable.kaishi);
            // 状态处于为开始的时候，返回时间应该是当前时间
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            collocation_Subscribe_list_time.setText("开始时间:"+simpleDateFormat.format(date));
        }
        return convertView;
    }
}
