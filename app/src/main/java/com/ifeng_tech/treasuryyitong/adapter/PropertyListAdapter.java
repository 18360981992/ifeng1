package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.DetailBean;

import java.util.List;

/**
 * Created by zzt on 2018/5/9.
 */

public class PropertyListAdapter extends BaseAdapter {
    Context context;
    List<DetailBean> detailList;

    public PropertyListAdapter(Context context, List<DetailBean> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public int getCount() {
        return detailList.size();
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

        property_list_title.setText(detailList.get(position).getTitle());
        property_list_time.setText(detailList.get(position).getTime());
        property_list_detail.setText(detailList.get(position).getDetail());

        return convertView;
    }
}
