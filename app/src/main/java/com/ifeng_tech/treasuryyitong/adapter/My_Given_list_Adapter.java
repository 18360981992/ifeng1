package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/18.
 */

public class My_Given_list_Adapter extends BaseAdapter {
    Context context;
    List<Give_List_Bean> list;

    public My_Given_list_Adapter(Context context, List<Give_List_Bean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(context, R.layout.my_given_list_item,null);
        }
        TextView my_given_list_danhao = convertView.findViewById(R.id.my_given_list_danhao);
        TextView my_given_list_cword = convertView.findViewById(R.id.my_given_list_cword);
        TextView my_given_list_name = convertView.findViewById(R.id.my_given_list_name);
        TextView my_given_list_num = convertView.findViewById(R.id.my_given_list_num);
        TextView my_given_list_dfuid = convertView.findViewById(R.id.my_given_list_dfuid);
        TextView my_given_list_time = convertView.findViewById(R.id.my_given_list_time);
        LinearLayout my_given_list_zhuanchu_img = convertView.findViewById(R.id.my_given_list_zhuanchu_img);
        LinearLayout my_given_list_zhuanru_img = convertView.findViewById(R.id.my_given_list_zhuanru_img);
        LinearLayout my_given_list_zhuanchu_btn = convertView.findViewById(R.id.my_given_list_zhuanchu_btn);
        final TextView my_given_list_jieshou = convertView.findViewById(R.id.my_given_list_jieshou);
        TextView my_given_list_jujue = convertView.findViewById(R.id.my_given_list_jujue);
        LinearLayout my_given_list_zhuanru_btn = convertView.findViewById(R.id.my_given_list_zhuanru_btn);
        TextView my_given_list_zhuanru_text = convertView.findViewById(R.id.my_given_list_zhuanru_text);

        my_given_list_danhao.setText(list.get(position).getDanhao());
        my_given_list_cword.setText(""+list.get(position).getCword());
        my_given_list_name.setText(list.get(position).getName());
        my_given_list_num.setText(list.get(position).getNum()+"");
        my_given_list_dfuid.setText(""+list.get(position).getDfuid());
        Date date = new Date(list.get(position).getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        my_given_list_time.setText(simpleDateFormat.format(date));

        if(list.get(position).getType()==0){ // 转出
            my_given_list_zhuanchu_img.setVisibility(View.VISIBLE);my_given_list_zhuanchu_btn.setVisibility(View.VISIBLE);
            my_given_list_zhuanru_img.setVisibility(View.GONE); my_given_list_zhuanru_btn.setVisibility(View.GONE);

        }else{
            my_given_list_zhuanchu_img.setVisibility(View.GONE);my_given_list_zhuanchu_btn.setVisibility(View.GONE);
            my_given_list_zhuanru_img.setVisibility(View.VISIBLE); my_given_list_zhuanru_btn.setVisibility(View.VISIBLE);
        }

        // 接收的点击事件
        my_given_list_jieshou.setOnClickListener(new ForbidClickListener() {

            @Override
            public void forbidClick(View v) {
                my_given_list_adapter_jieKou.jieShou(position);
            }
        });

        // 拒绝的点击事件
        my_given_list_jujue.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                my_given_list_adapter_jieKou.juJue(position);
            }
        });

        return convertView;
    }

    public interface My_Given_list_Adapter_JieKou{
        void jieShou(int postion);
        void juJue(int postion);
    }

    My_Given_list_Adapter_JieKou my_given_list_adapter_jieKou;

    public void setMy_given_list_adapter_jieKou(My_Given_list_Adapter_JieKou my_given_list_adapter_jieKou) {
        this.my_given_list_adapter_jieKou = my_given_list_adapter_jieKou;
    }
}
