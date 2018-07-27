package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Shop_Detailed_List_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/7/5.
 */

public class Shop_Detailed_Adapter extends BaseAdapter{
    Context context;
    List<Shop_Detailed_List_Bean.DataBean.ListBean.ContentBean> list;

    public Shop_Detailed_Adapter(Context context, List<Shop_Detailed_List_Bean.DataBean.ListBean.ContentBean> list) {
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
            convertView=View.inflate(context, R.layout.shop_detailed_list_item,null);
        }
        TextView shop_detailed_list_item_text = convertView.findViewById(R.id.shop_detailed_list_item_text);
        shop_detailed_list_item_text.setText(""+list.get(position).getId());
        return convertView;
    }
}
