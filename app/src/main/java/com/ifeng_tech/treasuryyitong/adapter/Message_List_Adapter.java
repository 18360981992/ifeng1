package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
    List<Message_Lists_Bean.DataBean.ListBean> list;
    String title;

    public Message_List_Adapter(Context context, List<Message_Lists_Bean.DataBean.ListBean> list,String title) {
        this.context = context;
        this.list = list;
        this.title=title;
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
        ImageView message_lists_item_img = convertView.findViewById(R.id.message_lists_item_img);  // 这个箭头设置隐藏/显示

        message_lists_item_title.setText(title);
        message_lists_item_text.setText(list.get(position).getContent());

        Date date = new Date(list.get(position).getAddTime());
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
        int msgType = list.get(position).getMsgType();
        if(msgType==1){
            message_lists_item_img.setVisibility(View.VISIBLE);
        }else{
            message_lists_item_img.setVisibility(View.GONE);
        }

        return convertView;
    }
}
