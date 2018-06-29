package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Entrust_List_Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/6/29.
 */

public class Entrust_List_Adapter extends BaseAdapter {
    Context context;
    List<Entrust_List_Bean.DataBean.ListBean> list;

    public Entrust_List_Adapter(Context context, List<Entrust_List_Bean.DataBean.ListBean> list) {
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
            convertView=View.inflate(context, R.layout.entrust_list_item,null);
        }
        TextView entrust_list_item_danhao = convertView.findViewById(R.id.entrust_list_item_danhao);
        TextView entrust_list_item_name = convertView.findViewById(R.id.entrust_list_item_name);
        TextView entrust_list_item_time = convertView.findViewById(R.id.entrust_list_item_time);
        TextView entrust_list_item_type = convertView.findViewById(R.id.entrust_list_item_type);
        TextView entrust_list_item_pingtai = convertView.findViewById(R.id.entrust_list_item_pingtai);

        Entrust_List_Bean.DataBean.ListBean listBean = list.get(position);

        entrust_list_item_danhao.setText(listBean.getDeliveryNo());
        entrust_list_item_name.setText(listBean.getDeliveryName());

        Date date = new Date(listBean.getAddTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        entrust_list_item_time.setText(simpleDateFormat.format(date));

        // 委托单状态 ： 1-等待审核 2-等待处理 3-已完成 4-提货失败
        if (listBean.getStatus()==1){
            entrust_list_item_type.setText("等待审核");
            entrust_list_item_type.setTextColor(context.getResources().getColor(R.color.shenhe));
        }else if(listBean.getStatus()==2){
            entrust_list_item_type.setText("等待处理");
            entrust_list_item_type.setTextColor(context.getResources().getColor(R.color.chuli));
        }else if(listBean.getStatus()==3){
            entrust_list_item_type.setText("已完成");
            entrust_list_item_type.setTextColor(context.getResources().getColor(R.color.name_se));
        }else if(listBean.getStatus()==4){
            entrust_list_item_type.setText("提货失败");
            entrust_list_item_type.setTextColor(context.getResources().getColor(R.color.shibai));
        }

        return convertView;
    }
}
