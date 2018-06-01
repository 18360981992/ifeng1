package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
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
            convertView=View.inflate(context, R.layout.property_list_item,null);
        }

        TextView property_list_title = convertView.findViewById(R.id.property_list_title);
        TextView property_list_time = convertView.findViewById(R.id.property_list_time);
        TextView property_list_detail = convertView.findViewById(R.id.property_list_detail);
        if(list.get(position).getPaymentType()==1){
            property_list_title.setText("充值");
            property_list_detail.setText("+"+list.get(position).getAmount());
        }else if(list.get(position).getPaymentType()==2){
            property_list_title.setText("提现");
            property_list_detail.setText("-"+list.get(position).getAmount());
        }

        Date date = new Date(list.get(position).getAddTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        property_list_time.setText(simpleDateFormat.format(date));
        return convertView;
    }
}
