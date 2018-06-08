package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.tihuo.DepotListByGoodsId_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/17.
 */

public class MyPopList_Atapter extends android.widget.BaseAdapter{
    Context context;
    List<DepotListByGoodsId_Bean.DataBean.ListBean> list;

    public MyPopList_Atapter(Context context, List<DepotListByGoodsId_Bean.DataBean.ListBean> list) {
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
        if(list.get(position).getDepotName()!=null && !list.get(position).getDepotName().equals("")){
            pop_list_text.setText(list.get(position).getDepotName());
        }else{
            pop_list_text.setText("测试仓库");
        }


        return convertView;
    }
}
