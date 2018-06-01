package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.my.My_Collect_Bean;
import com.ifeng_tech.treasuryyitong.utils.ECollectStage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.ifeng_tech.treasuryyitong.R.id.incoming_word;

/**
 * Created by zzt on 2018/5/10.
 */

public class Incoming_Test_Adapter extends BaseAdapter{

    Context context;
    List<My_Collect_Bean.DataBean.ListBean> incominglist;

    public Incoming_Test_Adapter(Context context, List<My_Collect_Bean.DataBean.ListBean> incominglist) {
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
        TextView incoming_cword = convertView.findViewById(incoming_word);
        TextView incoming_shopping_name = convertView.findViewById(R.id.incoming_shopping_name);
        TextView incoming_num = convertView.findViewById(R.id.incoming_num);
        TextView incoming_type = convertView.findViewById(R.id.incoming_type);
        TextView incoming_time = convertView.findViewById(R.id.incoming_time);

        if(incominglist.get(position).getAgencyName().equals("")||incominglist.get(position).getAgencyName()==null){
            incoming_trade_name.setText("福利特");
        }else{
            incoming_trade_name.setText(incominglist.get(position).getAgencyName());
        }

        if(incominglist.get(position).getGoodsName().length()>10){
            String name = incominglist.get(position).getGoodsName().substring(0, 10);
            incoming_shopping_name.setText(name+"...");
        }else{
            incoming_shopping_name.setText(incominglist.get(position).getGoodsName());
        }

        incoming_cword.setText(incominglist.get(position).getGoodsCode());

        incoming_num.setText(incominglist.get(position).getAmount()+"");

        incoming_type.setText(ECollectStage.getName(incominglist.get(position).getStatus()));  // 根据状态显示ui

        Date date = new Date(incominglist.get(position).getAddTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        incoming_time.setText(simpleDateFormat.format(date));

        return convertView;
    }



}
