package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.Pick_Up_Goods_Bean;
import com.ifeng_tech.treasuryyitong.utils.EPickUpStage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/23.
 */

public class Pick_Up_Goods_Cha_Adapter extends BaseAdapter{

    Context context;
    List<Pick_Up_Goods_Bean.DataBean.ListBean> list;

    public Pick_Up_Goods_Cha_Adapter(Context context, List<Pick_Up_Goods_Bean.DataBean.ListBean> list) {
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
            convertView=View.inflate(context, R.layout.pick_up_goods_cha_list_item,null);
        }

        TextView pick_up_goods_cha_list_danhao = convertView.findViewById(R.id.pick_up_goods_cha_list_danhao);
        TextView pick_up_goods_cha_list_name = convertView.findViewById(R.id.pick_up_goods_cha_list_name);
        TextView pick_up_goods_cha_list_cword = convertView.findViewById(R.id.pick_up_goods_cha_list_cword);
        TextView pick_up_goods_cha_list_num = convertView.findViewById(R.id.pick_up_goods_cha_list_num);
        TextView pick_up_goods_cha_list_price = convertView.findViewById(R.id.pick_up_goods_cha_list_price);
        TextView pick_up_goods_cha_list_time = convertView.findViewById(R.id.pick_up_goods_cha_list_time);
        TextView pick_up_goods_cha_list_type = convertView.findViewById(R.id.pick_up_goods_cha_list_type);

        pick_up_goods_cha_list_danhao.setText(list.get(position).getBillId());  // 提货单号
        pick_up_goods_cha_list_cword.setText(""+list.get(position).getGoodsCode());
        if(list.get(position).getGoodsName().length()>18){
            String name = list.get(position).getGoodsName().substring(0, 18);
            pick_up_goods_cha_list_name.setText(name+"...");
        }else{
            pick_up_goods_cha_list_name.setText(list.get(position).getGoodsName());
        }

        pick_up_goods_cha_list_num.setText(""+list.get(position).getQuantity());  // 提货数量

        pick_up_goods_cha_list_price.setText("￥"+ DashApplication.decimalFormat.format(list.get(position).getDeliveryFee()));

        Date date = new Date(list.get(position).getAddTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        pick_up_goods_cha_list_time.setText(simpleDateFormat.format(date));

        pick_up_goods_cha_list_type.setText(EPickUpStage.getName(list.get(position).getBillStage()));
        return convertView;
    }
}
