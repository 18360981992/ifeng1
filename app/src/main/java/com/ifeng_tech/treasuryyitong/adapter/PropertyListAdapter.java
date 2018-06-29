package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.my.My_Property_list_Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/9.
 */

public class PropertyListAdapter extends BaseAdapter {
    Context context;
    List<My_Property_list_Bean.DataBean.ListBean> list;

    public PropertyListAdapter(Context context, List<My_Property_list_Bean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list.size()<5){
            return list.size();
        }
        return 5;
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
            convertView=View.inflate(context, R.layout.property_list_item,null);
        }

        TextView property_list_title = convertView.findViewById(R.id.property_list_title);
        TextView property_list_time = convertView.findViewById(R.id.property_list_time);
        TextView property_list_detail = convertView.findViewById(R.id.property_list_detail);


            String amount = DashApplication.decimalFormat.format(list.get(position).getAmount());

            if(list.get(position).getPaymentType()==1){
                property_list_title.setText("充值");
                property_list_detail.setText("+"+amount);
            }else if(list.get(position).getPaymentType()==2){
                property_list_title.setText("提现");
                property_list_detail.setText("-"+amount);
            }

            Date date = new Date(list.get(position).getAddTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            property_list_time.setText(simpleDateFormat.format(date));


        return convertView;
    }
}
