package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Stock_Removal_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/10.
 */

public class Stock_Adapter extends RecyclerView.Adapter<Stock_Adapter.Stock_Holde> {

    Context context;
    List<Stock_Removal_Bean> stocklist;

    public Stock_Adapter(Context context, List<Stock_Removal_Bean> stocklist) {
        this.context = context;
        this.stocklist = stocklist;
    }

    @Override
    public Stock_Holde onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stock_recycle_item, parent,false);
        Stock_Holde stock_Holde = new Stock_Holde(view);
        return stock_Holde;
    }

    @Override
    public void onBindViewHolder(Stock_Holde holder, final int position) {
        holder.stock_trade_name.setText(stocklist.get(position).getTrade_name());
        holder.stock_word.setText(stocklist.get(position).getWord());
        holder.stock_shopping_name.setText(stocklist.get(position).getShopping_name());
        holder.stock_num.setText(stocklist.get(position).getNum()+"");
        holder.stock_time.setText(stocklist.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stock_jieKou.chuan(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stocklist.size();
    }


    public interface Stock_JieKou{
        void chuan(int postin);
    }

    Stock_JieKou stock_jieKou;

    public void setStock_jieKou(Stock_JieKou stock_jieKou) {
        this.stock_jieKou = stock_jieKou;
    }

    class Stock_Holde extends RecyclerView.ViewHolder{

        public TextView stock_trade_name;
        public TextView stock_word;
        public TextView stock_shopping_name;
        public TextView stock_num;
        public TextView stock_time;

        public Stock_Holde(View itemView) {
            super(itemView);
            stock_trade_name = itemView.findViewById(R.id.stock_trade_name);
            stock_word = itemView.findViewById(R.id.stock_word);
            stock_shopping_name = itemView.findViewById(R.id.stock_shopping_name);
            stock_num = itemView.findViewById(R.id.stock_num);
            stock_time = itemView.findViewById(R.id.stock_time);


        }
    }
}
