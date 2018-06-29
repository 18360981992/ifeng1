package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.cangpin.Collection_directory_Fragment_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/24.
 */

public class Collection_directory_Adapter extends BaseAdapter{
    Context context;
    List<Collection_directory_Fragment_Bean.DataBean.ListBean> topList;
    public Collection_directory_Adapter(Context context,  List<Collection_directory_Fragment_Bean.DataBean.ListBean> topList) {
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

        if(topList.get(position).getCommodityLink()==null){
            collection_mulu_item_img.setImageResource(R.drawable.img_erroy);
        }else{
            Glide.with(context).load(topList.get(position).getCommodityLink()).error(R.drawable.img_erroy).into(collection_mulu_item_img);
        }
        if(topList.get(position).getCommodityName().length()>18){
            String name = topList.get(position).getCommodityName().substring(0, 18);
            collection_mulu_item_name.setText(name+"...");
        }else{
            collection_mulu_item_name.setText(topList.get(position).getCommodityName());
        }

        collection_mulu_item_cword.setText(""+topList.get(position).getCommodityCode());

        return convertView;
    }
}
