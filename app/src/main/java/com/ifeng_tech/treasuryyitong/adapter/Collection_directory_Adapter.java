package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Collection_directory_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/24.
 */

public class Collection_directory_Adapter extends BaseAdapter{
    Context context;
    List<Collection_directory_Bean> topList;


    public Collection_directory_Adapter(Context context, List<Collection_directory_Bean> topList) {
        this.context = context;
        this.topList = topList;
    }

    @Override
    public int getCount() {
        return topList.size();
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
            convertView=View.inflate(context, R.layout.collection_mulu_item,null);
        }
        ImageView collection_mulu_item_img = convertView.findViewById(R.id.collection_mulu_item_img);
        TextView collection_mulu_item_name = convertView.findViewById(R.id.collection_mulu_item_name);
        TextView collection_mulu_item_cword = convertView.findViewById(R.id.collection_mulu_item_cword);

        collection_mulu_item_img.setImageResource(topList.get(position).getImg());
        collection_mulu_item_name.setText(topList.get(position).getName());
        collection_mulu_item_cword.setText(""+topList.get(position).getCword());

        return convertView;
    }
}
