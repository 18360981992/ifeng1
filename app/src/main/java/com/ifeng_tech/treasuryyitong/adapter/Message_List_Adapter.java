package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Message_Lists_Bean;
import com.ifeng_tech.treasuryyitong.utils.Date_Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/22.
 */

public class Message_List_Adapter extends BaseAdapter{
    Context context;
    List<Message_Lists_Bean> list;

    public Message_List_Adapter(Context context, List<Message_Lists_Bean> list) {
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
            convertView=View.inflate(context, R.layout.message_lists_item,null);
        }
        TextView message_lists_item_time = convertView.findViewById(R.id.message_lists_item_time);
        TextView message_lists_item_title = convertView.findViewById(R.id.message_lists_item_title);
        TextView message_lists_item_text = convertView.findViewById(R.id.message_lists_item_text);

        message_lists_item_title.setText(list.get(position).getTitle());
        message_lists_item_text.setText(list.get(position).getText());

        Date date = new Date(list.get(position).getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean b = Date_Utils.IsToday(simpleDateFormat.format(date));
        if(b){
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
            if(date.getHours()<12){
                message_lists_item_time.setText("上午 "+simpleDateFormat1.format(date));
            }else if(date.getHours()>12){
                message_lists_item_time.setText("下午 "+simpleDateFormat1.format(date));
            }else{
                message_lists_item_time.setText("中午 "+simpleDateFormat1.format(date));
            }

        }else{
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM月dd日");
            message_lists_item_time.setText(simpleDateFormat1.format(date));
        }
        return convertView;
    }
}
