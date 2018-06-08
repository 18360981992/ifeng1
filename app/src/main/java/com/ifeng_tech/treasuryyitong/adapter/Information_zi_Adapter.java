package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.bean.Information_Zi_Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/7.
 */

public class Information_zi_Adapter extends BaseAdapter {
    Context context;
    List<Information_Zi_Bean.DataBean.ListBean> topList;

    String top;

    public Information_zi_Adapter(Context context, List<Information_Zi_Bean.DataBean.ListBean> topList,String top) {
        this.context = context;
        this.topList = topList;
        this.top=top;
    }

    @Override
    public int getCount() {
        return topList.size();
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
        if(getItemViewType(position)==0){
            View convertView1 = null;
            if(convertView1==null){
                convertView1=View.inflate(context,R.layout.information_zi_fragment_buju_right,null);
            }
            TextView information_zi_fragment_buju_right_text = convertView1.findViewById(R.id.information_zi_fragment_buju_right_text);
            TextView information_zi_fragment_buju_right_time = convertView1.findViewById(R.id.information_zi_fragment_buju_right_time);
            ImageView information_zi_fragment_buju_right_img = convertView1.findViewById(R.id.information_zi_fragment_buju_right_img);

            if(topList.get(position).getAppUrl()==null||topList.get(position).getAppUrl().equals("")){
                information_zi_fragment_buju_right_img.setImageResource(R.drawable.img_erroy);
            }else{
                Glide.with(context).load(topList.get(position).getAppUrl()).error(R.drawable.img_erroy).into(information_zi_fragment_buju_right_img);
            }


            Date date = new Date(topList.get(position).getAddTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            information_zi_fragment_buju_right_time.setText(simpleDateFormat.format(date));

            // 设置文本
            String text=top+"\t\t"+topList.get(position).getTitle();
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            // 设置textview 的富文本颜色
            ForegroundColorSpan span = new ForegroundColorSpan(context.getResources().getColor(R.color.zhuse));
            builder.setSpan(span, 0, top.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            information_zi_fragment_buju_right_text.setText(builder);

            return convertView1;
        }else{
            View convertView2 = null;
            if(convertView2 ==null){
                convertView2=View.inflate(context,R.layout.information_zi_fragment_buju_bottom,null);
            }
            TextView information_zi_fragment_buju_bottom_text = convertView2.findViewById(R.id.information_zi_fragment_buju_bottom_text);
            ImageView information_zi_fragment_buju_bottom_img1 = convertView2.findViewById(R.id.information_zi_fragment_buju_bottom_img1);
            ImageView information_zi_fragment_buju_bottom_img2 = convertView2.findViewById(R.id.information_zi_fragment_buju_bottom_img2);
            ImageView information_zi_fragment_buju_bottom_img3 = convertView2.findViewById(R.id.information_zi_fragment_buju_bottom_img3);
            TextView information_zi_fragment_buju_bottom_time = convertView2.findViewById(R.id.information_zi_fragment_buju_bottom_time);

            Date date = new Date(topList.get(position).getAddTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            information_zi_fragment_buju_bottom_time.setText(simpleDateFormat.format(date));

//            switch (topList.get(position).getAppUrl().length){
//                case 1:
//                    Glide.with(context).load(topList.get(position).getAppUrl()).into(information_zi_fragment_buju_bottom_img1);
//                    break;
//                case 2:
//                    Glide.with(context).load(topList.get(position).getAppUrl()).into(information_zi_fragment_buju_bottom_img1);
//                    Glide.with(context).load(topList.get(position).getAppUrl()).into(information_zi_fragment_buju_bottom_img2);
//                    break;
//                default:
//                    Glide.with(context).load(topList.get(position).getAppUrl()).into(information_zi_fragment_buju_bottom_img1);
//                    Glide.with(context).load(topList.get(position).getAppUrl()).into(information_zi_fragment_buju_bottom_img2);
//                    Glide.with(context).load(topList.get(position).getAppUrl()).into(information_zi_fragment_buju_bottom_img3);
//                    break;
//            }
            if(topList.get(position).getWebUrl()==null||topList.get(position).getWebUrl().equals("")){
                information_zi_fragment_buju_bottom_img1.setImageResource(R.drawable.img_erroy);
                information_zi_fragment_buju_bottom_img2.setImageResource(R.drawable.img_erroy);
                information_zi_fragment_buju_bottom_img3.setImageResource(R.drawable.img_erroy);
            }else{
                Glide.with(context).load(topList.get(position).getWebUrl()).error(R.drawable.img_erroy).into(information_zi_fragment_buju_bottom_img1);
                Glide.with(context).load(topList.get(position).getWebUrl()).error(R.drawable.img_erroy).into(information_zi_fragment_buju_bottom_img2);
                Glide.with(context).load(topList.get(position).getWebUrl()).error(R.drawable.img_erroy).into(information_zi_fragment_buju_bottom_img3);
            }



            // 设置文本
            String text=top+"\t\t"+topList.get(position).getTitle();
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            // 设置textview 的富文本颜色
            ForegroundColorSpan span = new ForegroundColorSpan(context.getResources().getColor(R.color.zhuse));
            builder.setSpan(span, 0, top.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            information_zi_fragment_buju_bottom_text.setText(builder);

            return convertView2;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(topList.get(position).getAppUrl()==null){ // 如果图片的长度大于1 就加载多图布局
            return 1;
        }else{
            return 0;
        }
    }


}
