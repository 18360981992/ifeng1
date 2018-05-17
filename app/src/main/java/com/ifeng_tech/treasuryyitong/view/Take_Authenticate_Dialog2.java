package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;

/**
 * Created by zzt on 2018/5/17.
 */

public class Take_Authenticate_Dialog2 extends Dialog {

    private TextView take_Authenticate_Dialog2_time;
    private TextView take_Authenticate_Dialog2_shuliang;
    private TextView take_Authenticate_Dialog2_shouxufei;
    private Button take_authenticate_dialog2_queren;
    private Button take_authenticate_dialog2_quxiao;

    public Take_Authenticate_Dialog2(@NonNull Context context) {
        super(context);
        this.show();
    }

    public Take_Authenticate_Dialog2(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_authenticate_dialog2);
        init();
        take_authenticate_dialog2_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                take_Authenticate_Dialog2_JieKou.QuanRen();
            }
        });

        take_authenticate_dialog2_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void init(){
        take_Authenticate_Dialog2_time = findViewById(R.id.take_Authenticate_Dialog2_time);
        take_Authenticate_Dialog2_shuliang = findViewById(R.id.take_Authenticate_Dialog2_shuliang);
        take_Authenticate_Dialog2_shouxufei = findViewById(R.id.take_Authenticate_Dialog2_shouxufei);
        take_authenticate_dialog2_queren = findViewById(R.id.take_Authenticate_Dialog2_queren);
        take_authenticate_dialog2_quxiao = findViewById(R.id.take_Authenticate_Dialog2_quxiao);
    }

    public void setTake_Authenticate_Dialog2_time(String time) {
        take_Authenticate_Dialog2_time.setText(time);
    }

    public void setTake_Authenticate_Dialog2_shuliang(int num) {
        take_Authenticate_Dialog2_shuliang.setText(""+num);
    }
    public void setTtake_Authenticate_Dialog2_shouxufei(double price) {
        take_Authenticate_Dialog2_shouxufei.setText("￥"+DashApplication.decimalFormat.format(price));
    }


    public interface Take_Authenticate_Dialog2_JieKou{
        void QuanRen();
    }
    Take_Authenticate_Dialog2_JieKou take_Authenticate_Dialog2_JieKou;

    public void setTake_Authenticate_Dialog2_JieKou(Take_Authenticate_Dialog2_JieKou take_Authenticate_Dialog2_JieKou) {
        this.take_Authenticate_Dialog2_JieKou = take_Authenticate_Dialog2_JieKou;
    }
}
