package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.InformationBean;

import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 */

class HomeInformationAdapter extends RecyclerView.Adapter<HomeInformationAdapter.ZiXunHolder> {
    Context context;
    List<InformationBean> informationlist;

    public interface ZiXunAdapterJieKou{
        void ZiXunChuan(int i);
    }

    ZiXunAdapterJieKou ziXunAdapterJieKou;

    public void setZiXunAdapterJieKou(ZiXunAdapterJieKou ziXunAdapterJieKou) {
        this.ziXunAdapterJieKou = ziXunAdapterJieKou;
    }

    public HomeInformationAdapter(Context context, List<InformationBean> informationlist) {
        this.context = context;
        this.informationlist = informationlist;
    }

    @Override
    public ZiXunHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_zixun_buju, parent,false);
        ZiXunHolder ziXunHolder = new ZiXunHolder(view);
        return ziXunHolder;
    }

    @Override
    public void onBindViewHolder(ZiXunHolder holder, final int position) {

        String text=informationlist.get(position).getTitle()+"\t"+informationlist.get(position).getText();
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        // 设置textview 的富文本颜色
        ForegroundColorSpan span = new ForegroundColorSpan(context.getResources().getColor(R.color.zhuse));
        builder.setSpan(span, 0, informationlist.get(position).getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.home_zixun_text.setText(builder);
        holder.home_zixun_time.setText(informationlist.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ziXunAdapterJieKou.ZiXunChuan(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return informationlist.size();
    }


    class ZiXunHolder extends RecyclerView.ViewHolder{

        public TextView home_zixun_text;
        public TextView home_zixun_time;

        public ZiXunHolder(View itemView) {
            super(itemView);
            home_zixun_text = itemView.findViewById(R.id.home_zixun_text);
            home_zixun_time = itemView.findViewById(R.id.home_zixun_time);
        }
    }
}
