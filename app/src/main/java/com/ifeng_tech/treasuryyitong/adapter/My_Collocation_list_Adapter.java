package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.my.My_Colloction_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/18.
 *
 * 我的托管 适配器
 */

public class My_Collocation_list_Adapter extends BaseAdapter{
    Context context;
    List<My_Colloction_Bean.DataBean.PageInfoBean.ListBean> list;

    public My_Collocation_list_Adapter(Context context, List<My_Colloction_Bean.DataBean.PageInfoBean.ListBean> list) {
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
            convertView = View.inflate(context, R.layout.my_collocation_list_item,null);
        }

        TextView my_collocation_list_danhao = convertView.findViewById(R.id.my_collocation_list_danhao);
        TextView my_collocation_list_cword = convertView.findViewById(R.id.my_collocation_list_cword);
        TextView my_collocation_list_name = convertView.findViewById(R.id.my_collocation_list_name);
        TextView my_collocation_list_num = convertView.findViewById(R.id.my_collocation_list_num);
        TextView my_collocation_list_price = convertView.findViewById(R.id.my_collocation_list_price);
        TextView my_collocation_list_time = convertView.findViewById(R.id.my_collocation_list_time);
        TextView my_collocation_list_type = convertView.findViewById(R.id.my_collocation_list_type);

        my_collocation_list_danhao.setText(list.get(position).getOrderNo());
        my_collocation_list_cword.setText(""+list.get(position).getGoodsCode());

        if(list.get(position).getGoodsName().length()>10){
            String name = list.get(position).getGoodsName().substring(0, 10);
            my_collocation_list_name.setText(name+"...");
        }else{
            my_collocation_list_name.setText(list.get(position).getGoodsName());
        }

        my_collocation_list_num.setText(""+list.get(position).getNumber());

        double price=list.get(position).getNumber()*list.get(position).getAppraisalFee();
        my_collocation_list_price.setText("￥"+ DashApplication.decimalFormat.format(price));


        if(list.get(position).getPmORam().equals("am")){
            my_collocation_list_time.setText(list.get(position).getTrusteeTime()+" 上午");
        }else{
            my_collocation_list_time.setText(list.get(position).getTrusteeTime()+" 下午");
        }


//        if(list.get(position).getType()==0){
//            my_collocation_list_type.setText("已过期");
//        }else{
//            my_collocation_list_type.setText("未过期");
//        }

        return convertView;
    }
}
