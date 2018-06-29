package com.ifeng_tech.treasuryyitong.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.Authenticate_Details_Time_Bean;
import com.ifeng_tech.treasuryyitong.ui.Authenticate_Details_Activity;
import com.ifeng_tech.treasuryyitong.ui.LoginActivity;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzt on 2018/5/17.
 */

public class Authenticate_Details_list_Adapter extends BaseAdapter {
    Context context;
    List<Authenticate_Details_Time_Bean.DataBean.ListBean> list;
    private final boolean aBoolean;
    private final Authenticate_Details_Activity activity;

    public Authenticate_Details_list_Adapter(Context context, List<Authenticate_Details_Time_Bean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;

        activity = (Authenticate_Details_Activity) context;
        aBoolean = DashApplication.sp.getBoolean(SP_String.ISLOGIN, false);
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
        LinearLayout authenticate_Details_list_shangwu = convertView.findViewById(R.id.authenticate_Details_list_shangwu);  // 上午
        TextView authenticate_Details_list_shangwu_num = convertView.findViewById(R.id.authenticate_Details_list_shangwu_num);

        LinearLayout authenticate_Details_list_xiawu = convertView.findViewById(R.id.authenticate_Details_list_xiawu);  // 下午
        TextView authenticate_Details_list_xiawu_num = convertView.findViewById(R.id.authenticate_Details_list_xiawu_num);

        Date date = new Date(list.get(position).getOrderDate());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        authenticate_Details_list_time.setText(simpleDateFormat.format(date));

        if(list.get(position).getRestNumber_am()==null||list.get(position).getRestNumber_am().equals("")){
            authenticate_Details_list_shangwu_num.setText("--");
            authenticate_Details_list_shangwu.setEnabled(false);
        }else{
            authenticate_Details_list_shangwu_num.setText(list.get(position).getRestNumber_am()+"");
            authenticate_Details_list_shangwu.setEnabled(true);
        }

        if(list.get(position).getRestNumber_pm()==null||list.get(position).getRestNumber_pm().equals("")){
            authenticate_Details_list_xiawu_num.setText("--");
            authenticate_Details_list_xiawu.setEnabled(false);
        }else{
            authenticate_Details_list_xiawu_num.setText(list.get(position).getRestNumber_pm()+"");
            authenticate_Details_list_xiawu.setEnabled(true);
        }


        authenticate_Details_list_shangwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aBoolean){
                    authenticate_details_list_jieKou.shangWu(position);
                }else{
                    Intent intent1 = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });

        authenticate_Details_list_xiawu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aBoolean){
                    authenticate_details_list_jieKou.xiaWu(position);
                }else{
                    Intent intent1 = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
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
