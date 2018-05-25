package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;

import java.util.List;

/**
 * Created by zzt on 2018/5/10.
 */

public class Warehouse_Adapter extends BaseAdapter{

    Context context;
    List<WarehouseBean> warehouselist;

    public Warehouse_Adapter(Context context, List<WarehouseBean> warehouselist) {
        this.context = context;
        this.warehouselist = warehouselist;
    }
    @Override
    public int getCount() {
        return warehouselist.size();
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

         TextView warehouse_word;
         ImageView warehouse_img;
         TextView warehouse_shopping_name;
         TextView warehouse_keyong_num;
         TextView warehouse_dongjie_num;
            TextView warehouse_zhuanzeng;
            TextView warehouse_tihuo;

        if(convertView==null){
            convertView=View.inflate(context,R.layout.warehouse_xrecycle_item,null);
        }
        warehouse_word = convertView.findViewById(R.id.warehouse_word);
        warehouse_img = convertView.findViewById(R.id.warehouse_img);
        warehouse_shopping_name = convertView.findViewById(R.id.warehouse_shopping_name);
        warehouse_keyong_num = convertView.findViewById(R.id.warehouse_keyong_num);
        warehouse_dongjie_num = convertView.findViewById(R.id.warehouse_dongjie_num);

        warehouse_zhuanzeng = convertView.findViewById(R.id.warehouse_zhuanzeng);
        warehouse_tihuo = convertView.findViewById(R.id.warehouse_tihuo);

        warehouse_word.setText(warehouselist.get(position).getWord());
        warehouse_img.setImageResource(warehouselist.get(position).getImg());
        warehouse_shopping_name.setText(warehouselist.get(position).getShopping_name());
        warehouse_keyong_num.setText("可用数量："+warehouselist.get(position).getKeyong_num());
        warehouse_dongjie_num.setText("冻结数量："+warehouselist.get(position).getDongjie_num());


        warehouse_zhuanzeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warehouse_adapter_jieKou.zhuanzeng_chuan(position);
            }
        });

        warehouse_tihuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warehouse_adapter_jieKou.tihuo_chuan(position);
            }
        });

        return convertView;
    }


    public interface Warehouse_Adapter_JieKou{
        void tihuo_chuan(int postion);
        void zhuanzeng_chuan(int postion);
    }
    Warehouse_Adapter_JieKou warehouse_adapter_jieKou;

    public void setWarehouse_adapter_jieKou(Warehouse_Adapter_JieKou warehouse_adapter_jieKou) {
        this.warehouse_adapter_jieKou = warehouse_adapter_jieKou;
    }
}
