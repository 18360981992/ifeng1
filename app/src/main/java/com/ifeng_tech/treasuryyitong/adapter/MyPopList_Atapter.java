package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;

import java.util.List;

/**
 * Created by zzt on 2018/5/17.
 */

public class MyPopList_Atapter extends android.widget.BaseAdapter{
    Context context;
    List<String> list;

    public MyPopList_Atapter(Context context, List<String> list) {
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
            convertView=View.inflate(context, R.layout.pop_list_item,null);
        }
        TextView pop_list_text = convertView.findViewById(R.id.pop_list_text);
        pop_list_text.setText(list.get(position));

        return convertView;
    }
}
