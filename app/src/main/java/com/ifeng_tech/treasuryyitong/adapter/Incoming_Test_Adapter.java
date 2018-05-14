package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Incoming_bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/10.
 */

public class Incoming_Test_Adapter extends RecyclerView.Adapter<Incoming_Test_Adapter.Incoming_Holde>{

    Context context;
    List<Incoming_bean> incominglist;

    public Incoming_Test_Adapter(Context context, List<Incoming_bean> incominglist) {
        this.context = context;
        this.incominglist = incominglist;
    }


    @Override
    public Incoming_Holde onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.incoming_xrecycle_item, parent,false);
        Incoming_Holde incoming_Holde = new Incoming_Holde(view);
        return incoming_Holde;
    }

    @Override
    public void onBindViewHolder(Incoming_Holde holder, final int position) {
        holder.incoming_trade_name.setText(incominglist.get(position).getTrade_name());
        holder.incoming_word.setText(incominglist.get(position).getWord());
        holder.incoming_shopping_name.setText(incominglist.get(position).getShopping_name());
        holder.incoming_num.setText(incominglist.get(position).getNum()+"");
        holder.incoming_type.setText(incominglist.get(position).getType());
        holder.incoming_time.setText(incominglist.get(position).getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incoming_JieKou.chuan(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return incominglist.size();
    }


    public interface Incoming_JieKou{
        void chuan(int postin);
    }

    Incoming_JieKou incoming_JieKou;

    public void setIncoming_JieKou(Incoming_JieKou incoming_JieKou) {
        this.incoming_JieKou = incoming_JieKou;
    }

    class Incoming_Holde extends RecyclerView.ViewHolder{
        public TextView incoming_trade_name;
        public TextView incoming_word;
        public TextView incoming_shopping_name;
        public TextView incoming_num;
        public TextView incoming_type;
        public TextView incoming_time;

        public Incoming_Holde(View itemView) {
            super(itemView);

            incoming_trade_name = itemView.findViewById(R.id.incoming_trade_name);
            incoming_word = itemView.findViewById(R.id.incoming_word);
            incoming_shopping_name = itemView.findViewById(R.id.incoming_shopping_name);
            incoming_num = itemView.findViewById(R.id.incoming_num);
            incoming_type = itemView.findViewById(R.id.incoming_type);
            incoming_time = itemView.findViewById(R.id.incoming_time);
        }
    }
}
