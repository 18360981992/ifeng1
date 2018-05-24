package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Incoming_bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/10.
 */

public class Incoming_Test_Adapter extends BaseAdapter{

    Context context;
    List<Incoming_bean> incominglist;

    public Incoming_Test_Adapter(Context context, List<Incoming_bean> incominglist) {
        this.context = context;
        this.incominglist = incominglist;
    }

    @Override
    public int getCount() {
        return incominglist.size();
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
            convertView=View.inflate(context,R.layout.incoming_xrecycle_item,null);
        }
        TextView incoming_trade_name = convertView.findViewById(R.id.incoming_trade_name);
        TextView incoming_word = convertView.findViewById(R.id.incoming_word);
        TextView incoming_shopping_name = convertView.findViewById(R.id.incoming_shopping_name);
        TextView incoming_num = convertView.findViewById(R.id.incoming_num);
        TextView incoming_type = convertView.findViewById(R.id.incoming_type);
        TextView incoming_time = convertView.findViewById(R.id.incoming_time);

        incoming_trade_name.setText(incominglist.get(position).getTrade_name());
        incoming_word.setText(incominglist.get(position).getWord());
        incoming_shopping_name.setText(incominglist.get(position).getShopping_name());
        incoming_num.setText(incominglist.get(position).getNum()+"");
        incoming_type.setText(incominglist.get(position).getType());
        incoming_time.setText(incominglist.get(position).getTime());

        return convertView;
    }



}
