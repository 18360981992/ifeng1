package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Authenticate_Details_Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/17.
 */

public class Authenticate_Details_list_Adapter extends BaseAdapter {
    Context context;
    List<Authenticate_Details_Bean> list;

    public Authenticate_Details_list_Adapter(Context context, List<Authenticate_Details_Bean> list) {
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
            convertView=View.inflate(context, R.layout.authenticate_details_list_item,null);
        }
        TextView authenticate_Details_list_time = convertView.findViewById(R.id.authenticate_Details_list_time);
        LinearLayout authenticate_Details_list_shangwu = convertView.findViewById(R.id.authenticate_Details_list_shangwu);
        TextView authenticate_Details_list_shangwu_num = convertView.findViewById(R.id.authenticate_Details_list_shangwu_num);

        LinearLayout authenticate_Details_list_xiawu = convertView.findViewById(R.id.authenticate_Details_list_xiawu);
        TextView authenticate_Details_list_xiawu_num = convertView.findViewById(R.id.authenticate_Details_list_xiawu_num);

        Date date = new Date(list.get(position).getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        authenticate_Details_list_time.setText(simpleDateFormat.format(date));

        authenticate_Details_list_shangwu_num.setText(list.get(position).getAMsy()+"");
        authenticate_Details_list_xiawu_num.setText(list.get(position).getPMsy()+"");

        authenticate_Details_list_shangwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticate_details_list_jieKou.shangWu(position);
            }
        });

        authenticate_Details_list_xiawu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticate_details_list_jieKou.xiaWu(position);
            }
        });
        return convertView;
    }

    public interface Authenticate_Details_list_JieKou{
        void shangWu(int postion);
        void xiaWu(int postion);
    }

    Authenticate_Details_list_JieKou authenticate_details_list_jieKou;

    public void setAuthenticate_details_list_jieKou(Authenticate_Details_list_JieKou authenticate_details_list_jieKou) {
        this.authenticate_details_list_jieKou = authenticate_details_list_jieKou;
    }
}
