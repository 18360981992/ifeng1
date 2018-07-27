package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;
import com.ifeng_tech.treasuryyitong.utils.EGiven_ListStage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/18.
 */

public class My_Given_list_Adapter extends BaseAdapter {
    Context context;
    List<Give_List_Bean.DataBean.ListBean> list;

    int type;
    public My_Given_list_Adapter(Context context, List<Give_List_Bean.DataBean.ListBean> list,int type) {
        this.context = context;
        this.list = list;
        this.type=type;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.my_given_list_item,null);
        }
        TextView my_given_list_cword = convertView.findViewById(R.id.my_given_list_cword);
        TextView my_given_list_name = convertView.findViewById(R.id.my_given_list_name);
        TextView my_given_list_num = convertView.findViewById(R.id.my_given_list_num);
//        TextView my_given_list_dfuid = convertView.findViewById(R.id.my_given_list_dfuid);
        TextView my_given_list_time = convertView.findViewById(R.id.my_given_list_time);
        TextView my_given_list_zhuanru_text = convertView.findViewById(R.id.my_given_list_zhuanru_text);

        my_given_list_cword.setText("编号："+list.get(position).getGoodsCode());
        if(list.get(position).getGoodsName().length()>20){
            String name = list.get(position).getGoodsName().substring(0, 20);
            my_given_list_name.setText(name+"...");
        }else{
            my_given_list_name.setText(list.get(position).getGoodsName());
        }
        my_given_list_num.setText(list.get(position).getAmount()+"");

//        double price=list.get(position).getFee()*list.get(position).getProfit();
//        my_given_list_dfuid.setText("￥"+ DashApplication.decimalFormat.format(price));   // 对方id换成钱

        if(list.get(position).getStatus()==0){
            my_given_list_zhuanru_text.setTextColor(context.getResources().getColor(R.color.shenhe));
        }else if(list.get(position).getStatus()==2){
            my_given_list_zhuanru_text.setTextColor(context.getResources().getColor(R.color.shibai));
        }else {
            my_given_list_zhuanru_text.setTextColor(context.getResources().getColor(R.color.color_999999));
        }

        my_given_list_zhuanru_text.setText(EGiven_ListStage.getName(list.get(position).getStatus()));  // 枚举类状态

        if(list.get(position).getIsInto().equals("0")){  // 0==转出
            if(list.get(position).getStatus()==0){
                my_given_list_zhuanru_text.setText("等待对方确认");
            }
        }else{
            if(list.get(position).getStatus()==0) {
                my_given_list_zhuanru_text.setText("等待确认");
            }
        }

        Date date = new Date(list.get(position).getAddTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        my_given_list_time.setText(simpleDateFormat.format(date));

        return convertView;
    }


}
