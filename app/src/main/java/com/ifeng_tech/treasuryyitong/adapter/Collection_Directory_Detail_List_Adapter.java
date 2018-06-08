package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.cangpin.Collection_Directory_Detail_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/6/7.
 */

public class Collection_Directory_Detail_List_Adapter extends BaseAdapter {
    Context context;
    List<Collection_Directory_Detail_Bean.DataBean.DescriptionListBean> list;

    public Collection_Directory_Detail_List_Adapter(Context context, List<Collection_Directory_Detail_Bean.DataBean.DescriptionListBean> list) {
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
            convertView=View.inflate(context, R.layout.collection_directory_detail_list_item,null);
        }
        TextView collocation_mulu_detail_list_item_name = convertView.findViewById(R.id.collocation_mulu_detail_list_item_name);
        TextView collocation_mulu_detail_list_item_conect = convertView.findViewById(R.id.collocation_mulu_detail_list_item_conect);
        collocation_mulu_detail_list_item_name.setText(list.get(position).getName());
        collocation_mulu_detail_list_item_conect.setText(list.get(position).getDescription());
        return convertView;
    }
}
