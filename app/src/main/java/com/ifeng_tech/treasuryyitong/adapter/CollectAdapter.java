package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.CollectBean;

import java.util.List;

/**
 * Created by zzt on 2018/5/10.
 */

public class CollectAdapter extends BaseAdapter{
    Context context;
    List<CollectBean> list;

    public CollectAdapter(Context context, List<CollectBean> list) {
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
    public View getView(int position, View view, ViewGroup parent) {
        if(view==null){
            view=View.inflate(context, R.layout.collect_list_item,null);
        }
        ImageView collect_img = view.findViewById(R.id.collect_img);
        TextView collect_name = view.findViewById(R.id.collect_name);
        TextView collect_title = view.findViewById(R.id.collect_title);
        TextView collect_text = view.findViewById(R.id.collect_text);
        ImageView collect_imgflag = view.findViewById(R.id.collect_imgflag);

        collect_img.setImageResource(list.get(position).getImg());
        collect_name.setText(list.get(position).getName());
        collect_title.setText(list.get(position).getTitle());
        collect_text.setText(list.get(position).getText());
        collect_imgflag.setImageResource(list.get(position).getImgflag());
        return view;
    }
}
