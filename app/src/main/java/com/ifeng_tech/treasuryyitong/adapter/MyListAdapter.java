package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.MyListBean;

import java.util.List;

/**
 * Created by zzt on 2018/5/9.
 */

public class MyListAdapter extends BaseAdapter {

    Context context;
    List<MyListBean> myListBeen;

    public MyListAdapter(Context context, List<MyListBean> myListBeen) {
        this.context = context;
        this.myListBeen = myListBeen;
    }

    @Override
    public int getCount() {
        return myListBeen.size();
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
            convertView=View.inflate(context, R.layout.my_listview_item,null);
        }
        ImageView wode_list_img = convertView.findViewById(R.id.wode_list_img);
        TextView wode_list_text = convertView.findViewById(R.id.wode_list_text);

        wode_list_img.setImageResource(myListBeen.get(position).getImg());
        wode_list_text.setText(myListBeen.get(position).getText());
        return convertView;
    }
}
