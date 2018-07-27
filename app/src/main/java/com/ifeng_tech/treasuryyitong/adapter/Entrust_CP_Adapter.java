package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.weituo.Entrust_ClientGoodsByClientCode_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/7/20.
 */

public class Entrust_CP_Adapter extends BaseAdapter {
    Context context;
    List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> list;

    public Entrust_CP_Adapter(Context context, List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> list) {
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

        convertView=View.inflate(context, R.layout.entrust_client_list_item,null);
        ImageView entrust_Client_list_item_fuxuan = convertView.findViewById(R.id.entrust_Client_list_item_fuxuan);
        TextView entrust_Client_list_item_text = convertView.findViewById(R.id.entrust_Client_list_item_text);
        if(list.get(position).isCpFlag()){
            entrust_Client_list_item_fuxuan.setImageResource(R.drawable.zhuce_lan);
        }else{
            entrust_Client_list_item_fuxuan.setImageResource(R.drawable.zhuce_hui);
        }

        if(list.get(position).getCommodityName().length()>28){
            String substring = list.get(position).getCommodityName().substring(0, 28);
            entrust_Client_list_item_text.setText(substring+"...");
        }else{
            entrust_Client_list_item_text.setText(list.get(position).getCommodityName());
        }

        return convertView;
    }
}
