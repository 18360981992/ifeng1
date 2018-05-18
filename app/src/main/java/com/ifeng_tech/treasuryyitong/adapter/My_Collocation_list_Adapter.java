package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Collocation_list_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/18.
 */

public class My_Collocation_list_Adapter extends BaseAdapter{
    Context context;
    List<Collocation_list_Bean> list;

    public My_Collocation_list_Adapter(Context context, List<Collocation_list_Bean> list) {
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
            convertView = View.inflate(context, R.layout.my_collocation_list_item,null);
        }
        return convertView;
    }
}
