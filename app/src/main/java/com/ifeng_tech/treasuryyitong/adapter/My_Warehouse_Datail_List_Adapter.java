package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/7/4.
 */

public class My_Warehouse_Datail_List_Adapter extends BaseAdapter {
    Context context;
    List<Give_List_Bean.DataBean.ListBean> list;

    public My_Warehouse_Datail_List_Adapter(Context context, List<Give_List_Bean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list.size()>=5)
            return 5;
        else
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
            convertView=View.inflate(context, R.layout.my_warehouse_datail_list_item,null);
        }
        TextView my_warehouse_datail_list_item_time = convertView.findViewById(R.id.my_warehouse_datail_list_item_time);
        TextView my_warehouse_datail_list_item_type = convertView.findViewById(R.id.my_warehouse_datail_list_item_type);
        TextView my_warehouse_datail_list_item_qian = convertView.findViewById(R.id.my_warehouse_datail_list_item_qian);

        Date date = new Date(list.get(position).getAddTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        my_warehouse_datail_list_item_time.setText(simpleDateFormat.format(date));

        if(list.get(position).getIsInto().equals("1")){ // 转入
            my_warehouse_datail_list_item_type.setText("转入");
            my_warehouse_datail_list_item_qian.setText("+"+list.get(position).getAmount());
            my_warehouse_datail_list_item_qian.setTextColor(context.getResources().getColor(R.color.zhuanru));
        }else{
            my_warehouse_datail_list_item_type.setText("转出");
            my_warehouse_datail_list_item_qian.setText("-"+list.get(position).getAmount());
            my_warehouse_datail_list_item_qian.setTextColor(context.getResources().getColor(R.color.zhuanchu));
        }
        return convertView;
    }
}
