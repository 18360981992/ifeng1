package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.utils.ItemSlideHelper;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import java.util.List;

/**
 * Created by zzt on 2018/5/10.
 */

public class Warehouse_Adapter extends RecyclerView.Adapter<Warehouse_Adapter.Warehouse_Holde> implements ItemSlideHelper.Callback {

    Context context;
    List<WarehouseBean> warehouselist;

    private RecyclerView mRecyclerView;

    public interface Warehouse_Adapter_JieKou{
        void tihuo_chuan(int postion);
        void zhuanzeng_chuan(int postion);
    }
    Warehouse_Adapter_JieKou warehouse_adapter_jieKou;

    public void setWarehouse_adapter_jieKou(Warehouse_Adapter_JieKou warehouse_adapter_jieKou) {
        this.warehouse_adapter_jieKou = warehouse_adapter_jieKou;
    }

    public Warehouse_Adapter(Context context, List<WarehouseBean> warehouselist) {
        this.context = context;
        this.warehouselist = warehouselist;
    }

    @Override
    public Warehouse_Holde onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.warehouse_xrecycle_item, parent,false);
        Warehouse_Holde warehouse_Holde = new Warehouse_Holde(view);
        return warehouse_Holde;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));
    }

    @Override
    public void onBindViewHolder(Warehouse_Holde holder, final int position) {
        holder.warehouse_word.setText(warehouselist.get(position).getWord());
        holder.warehouse_img.setImageResource(warehouselist.get(position).getImg());
        holder.warehouse_shopping_name.setText(warehouselist.get(position).getShopping_name());
        holder.warehouse_keyong_num.setText(warehouselist.get(position).getKeyong_num()+"");
        holder.warehouse_dongjie_num.setText(warehouselist.get(position).getDongjie_num()+"");

        holder.warehouse_zhuanzeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warehouse_adapter_jieKou.zhuanzeng_chuan(position);
            }
        });

        holder.warehouse_tihuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warehouse_adapter_jieKou.tihuo_chuan(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return warehouselist.size();
    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        if (holder.itemView instanceof LinearLayout) {
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            //viewGroup包含3个控件，即消息主item、标记已读、删除，返回为标记已读宽度+删除宽度
            return viewGroup.getChildAt(0).getLayoutParams().width
                    + viewGroup.getChildAt(1).getLayoutParams().width;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }

    class Warehouse_Holde extends RecyclerView.ViewHolder{

        public TextView warehouse_word;
        public ImageView warehouse_img;
        public TextView warehouse_shopping_name;
        public TextView warehouse_keyong_num;
        public TextView warehouse_dongjie_num;
        public RelativeLayout warehouse_zhuanzeng;
        public RelativeLayout warehouse_tihuo;

        public Warehouse_Holde(View itemView) {
            super(itemView);
            warehouse_word = itemView.findViewById(R.id.warehouse_word);
            warehouse_img = itemView.findViewById(R.id.warehouse_img);
            warehouse_shopping_name = itemView.findViewById(R.id.warehouse_shopping_name);
            warehouse_keyong_num = itemView.findViewById(R.id.warehouse_keyong_num);
            warehouse_dongjie_num = itemView.findViewById(R.id.warehouse_dongjie_num);

            warehouse_zhuanzeng = itemView.findViewById(R.id.warehouse_zhuanzeng);
            warehouse_tihuo = itemView.findViewById(R.id.warehouse_tihuo);

        }
    }
}
