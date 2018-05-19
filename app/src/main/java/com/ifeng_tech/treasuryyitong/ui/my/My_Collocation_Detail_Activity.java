package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.Collocation_list_Bean;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class My_Collocation_Detail_Activity extends AppCompatActivity {

    private RelativeLayout my_collocation_detail_Fan;
    private TextView my_collocation_detail_name;
    private TextView my_collocation_detail_cword;
    private TextView my_collocation_detail_danwei;
    private TextView my_collocation_detail_xiangqing;
    private TextView my_collocation_detail_zong_time;
    private TextView my_collocation_detail_jiandingfei;
    private TextView my_collocation_detail_tianshu;
    private TextView my_collocation_detail_type;
    private TextView my_collocation_detail_kaishi_time;
    private TextView my_collocation_detail_dizhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__collocation__detail_);
        initView();

        my_collocation_detail_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 以下都是模拟数据
         */
        Intent intent = getIntent();
        Collocation_list_Bean collocation_list_bean = (Collocation_list_Bean) intent.getSerializableExtra("Collocation_list_Bean");
        my_collocation_detail_name.setText(collocation_list_bean.getName());
        my_collocation_detail_cword.setText(""+collocation_list_bean.getCword());
        my_collocation_detail_danwei.setText("枚");
        Date date = new Date(collocation_list_bean.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date nextDay = MyUtils.getNextDay(date);  // 加一年

        my_collocation_detail_zong_time.setText(simpleDateFormat.format(date)+"/"+simpleDateFormat.format(nextDay));
        my_collocation_detail_jiandingfei.setText(DashApplication.decimalFormat.format(collocation_list_bean.getPrice()));
        my_collocation_detail_tianshu.setText(""+10);

        if(collocation_list_bean.getType()==0){ // 这里的type和my_colloction_list_adapter中的type是不一样的，这里是模拟数据
            my_collocation_detail_type.setText("等待鉴定");
        }else{
            my_collocation_detail_type.setText("未开始");
        }

        my_collocation_detail_kaishi_time.setText(simpleDateFormat.format(date));

    }



    private void initView() {
        my_collocation_detail_Fan = (RelativeLayout) findViewById(R.id.my_collocation_detail_Fan);
        my_collocation_detail_name = (TextView) findViewById(R.id.my_collocation_detail_name);
        my_collocation_detail_cword = (TextView) findViewById(R.id.my_collocation_detail_cword);
        my_collocation_detail_danwei = (TextView) findViewById(R.id.my_collocation_detail_danwei);
        my_collocation_detail_xiangqing = (TextView) findViewById(R.id.my_collocation_detail_xiangqing);
        my_collocation_detail_zong_time = (TextView) findViewById(R.id.my_collocation_detail_zong_time);
        my_collocation_detail_jiandingfei = (TextView) findViewById(R.id.my_collocation_detail_jiandingfei);
        my_collocation_detail_tianshu = (TextView) findViewById(R.id.my_collocation_detail_tianshu);
        my_collocation_detail_type = (TextView) findViewById(R.id.my_collocation_detail_type);
        my_collocation_detail_kaishi_time = (TextView) findViewById(R.id.my_collocation_detail_kaishi_time);
        my_collocation_detail_dizhi = (TextView) findViewById(R.id.my_collocation_detail_dizhi);
    }
}
