package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import static com.ifeng_tech.treasuryyitong.R.id.take_Authenticate_Dialog1_shuliang;

/**
 * Created by zzt on 2018/5/17.
 */

public class Take_Authenticate_Dialog1 extends Dialog {

    private EditText take_authenticate_dialog1_shuliang;
    private TextView take_authenticate_dialog1_zuida;
    private TextView take_authenticate_dialog1_shouxufei;
    private Button take_authenticate_dialog1_queren;
    private Button take_authenticate_dialog1_quxiao;
    private TextView take_authenticate_dialog1_zuixiao;

    double price=0;  // 记住费率
    int minNum=0;  // 记录最小
    int maxNum=0;  // 记录最大
    int shuliang=0;  // 记住输入的数量
    private EditText take_authenticate_dialog1_yewu_pass;

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

                final String pass = take_authenticate_dialog1_yewu_pass.getText().toString().trim();
                if (TextUtils.isEmpty(pass)) {
                    MyUtils.setToast("业务密码不能为空!");
                    return;
                }

                if(pass.length()!=6){
                    MyUtils.setToast("请输入6位数的业务密码！");
                    return;
                }


                final String shu = take_authenticate_dialog1_shuliang.getText().toString().trim();
                if (TextUtils.isEmpty(shu)) {
                    MyUtils.setToast("请输入数量");
                    return;
                }

                if(shuliang < minNum||shuliang > maxNum){
                    MyUtils.setToast("输入的数量不合格...");
                    return;
                }

                Integer num = Integer.valueOf(shu);
                take_Authenticate_Dialog1_JieKou.QuanRen(num,pass);
            }
        });

        take_authenticate_dialog1_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        take_authenticate_dialog1_shuliang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    if(s.toString().length()<10){
                        shuliang= Integer.valueOf(s.toString().trim());
                        setTake_authenticate_dialog1_shouxufei(price);
                    }
                }
            }
        });


    }

    public void init(){
        take_authenticate_dialog1_shuliang = findViewById(take_Authenticate_Dialog1_shuliang);
        take_authenticate_dialog1_zuida = findViewById(R.id.take_Authenticate_Dialog1_zuida);
        take_authenticate_dialog1_shouxufei = findViewById(R.id.take_Authenticate_Dialog1_shouxufei);
        take_authenticate_dialog1_yewu_pass = findViewById(R.id.take_Authenticate_Dialog1_yewu_pass);
        take_authenticate_dialog1_queren = findViewById(R.id.take_Authenticate_Dialog1_queren);
        take_authenticate_dialog1_quxiao = findViewById(R.id.take_Authenticate_Dialog1_quxiao);
        take_authenticate_dialog1_zuixiao = findViewById(R.id.take_Authenticate_Dialog1_zuixiao);
    }

    public void setTake_authenticate_dialog1_zuida(int num) {
        this.maxNum=num;
        take_authenticate_dialog1_zuida.setText("剩余预约数量:"+num);
    }

    public void setTake_authenticate_dialog1_zuixiao(int num) {
        this.minNum=num;
        this.shuliang=num;
        take_authenticate_dialog1_zuixiao.setText("最小预约数量:"+num);
        take_authenticate_dialog1_shuliang.setText(num+"");
        take_authenticate_dialog1_shuliang.setSelection(String.valueOf(num).length());
    }


    public void setTake_authenticate_dialog1_shouxufei(double price) {
        this.price=price;
        take_authenticate_dialog1_shouxufei.setText("￥"+ DashApplication.decimalFormat.format(price*shuliang));
    }

    public interface Take_Authenticate_Dialog1_JieKou{
        /**
         *
         * @param num  第一次弹出框的数量
         */
        void QuanRen(int num,String pass);
    }
    Take_Authenticate_Dialog1_JieKou take_Authenticate_Dialog1_JieKou;

    public void setTake_Authenticate_Dialog1_JieKou(Take_Authenticate_Dialog1_JieKou take_Authenticate_Dialog1_JieKou) {
        this.take_Authenticate_Dialog1_JieKou = take_Authenticate_Dialog1_JieKou;
    }
}
