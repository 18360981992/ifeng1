package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

/**
 * Created by zzt on 2018/5/17.
 */

public class Take_Authenticate_Dialog1 extends Dialog {

    private EditText take_authenticate_dialog1_shuliang;
    private TextView take_authenticate_dialog1_zuida;
    private TextView take_authenticate_dialog1_shouxufei;
    private Button take_authenticate_dialog1_queren;
    private Button take_authenticate_dialog1_quxiao;

    public Take_Authenticate_Dialog1(@NonNull Context context) {
        super(context);
        this.show();
    }

    public Take_Authenticate_Dialog1(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_authenticate_dialog1);
        init();
        take_authenticate_dialog1_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(take_authenticate_dialog1_shuliang.length()>0){
                    Integer num = Integer.valueOf(take_authenticate_dialog1_shuliang.getText().toString());
                    take_Authenticate_Dialog1_JieKou.QuanRen(num);
                }else{
                    MyUtils.setToast("数量不能为空。。。");
                }
            }
        });

        take_authenticate_dialog1_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void init(){
        take_authenticate_dialog1_shuliang = findViewById(R.id.take_Authenticate_Dialog1_shuliang);
        take_authenticate_dialog1_zuida = findViewById(R.id.take_Authenticate_Dialog1_zuida);
        take_authenticate_dialog1_shouxufei = findViewById(R.id.take_Authenticate_Dialog1_shouxufei);
        take_authenticate_dialog1_queren = findViewById(R.id.take_Authenticate_Dialog1_queren);
        take_authenticate_dialog1_quxiao = findViewById(R.id.take_Authenticate_Dialog1_quxiao);
    }

    public void setTake_authenticate_dialog1_zuida(int num) {
        take_authenticate_dialog1_zuida.setText("最大转赠数量:"+num);
    }

    public void setTake_authenticate_dialog1_shouxufei(double price) {
        take_authenticate_dialog1_shouxufei.setText("￥"+ DashApplication.decimalFormat.format(price));
    }

    public interface Take_Authenticate_Dialog1_JieKou{
        /**
         *
         * @param num  第一次弹出框的数量
         */
        void QuanRen(int num);
    }
    Take_Authenticate_Dialog1_JieKou take_Authenticate_Dialog1_JieKou;

    public void setTake_Authenticate_Dialog1_JieKou(Take_Authenticate_Dialog1_JieKou take_Authenticate_Dialog1_JieKou) {
        this.take_Authenticate_Dialog1_JieKou = take_Authenticate_Dialog1_JieKou;
    }
}
