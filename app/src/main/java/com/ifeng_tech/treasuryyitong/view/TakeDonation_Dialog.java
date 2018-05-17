package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;


/**
 * Created by zzt on 2018/4/10.
 * <p>
 * 提交 弹出 转赠的dialog框
 */

public class TakeDonation_Dialog extends Dialog {

    private TextView donation_dialog_title;
    private TextView donation_dialog_word;
    private TextView donation_dialog_shuliang;
    private TextView donation_dialog_zongjia;
    private Button donation_dialog_queren;
    private Button donation_dialog_quxiao;

    public TakeDonation_Dialog(Context context) {
        super(context);
        this.show();
    }

    public TakeDonation_Dialog(Context context, int theme) {
        super(context, theme);
        this.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takedonation_dialog);
        init();
        donation_dialog_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeDonation_Dialog_JieKou.QuanRen();
            }
        });

        donation_dialog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeDonation_Dialog_JieKou.QuXiao();
            }
        });
    }

    public void init(){
        donation_dialog_title=findViewById(R.id.donation_dialog_title);
        donation_dialog_word=findViewById(R.id.donation_dialog_word);
        donation_dialog_shuliang=findViewById(R.id.donation_dialog_shuliang);
        donation_dialog_zongjia=findViewById(R.id.donation_dialog_zongjia);
        donation_dialog_queren=findViewById(R.id.donation_dialog_queren);
        donation_dialog_quxiao=findViewById(R.id.donation_dialog_quxiao);
    }

    public void setTitle(String title){
        donation_dialog_title.setText(title);
    }
    public void setWord(String word){
        donation_dialog_word.setText(word);
    }
    public void setShuLiang(int num){
        donation_dialog_shuliang.setText(""+num);
    }
    public void setZongJia(String price){
        donation_dialog_zongjia.setText(price);
    }



    public interface TakeDonation_Dialog_JieKou{
        void QuanRen();
        void QuXiao();
    }
    TakeDonation_Dialog_JieKou takeDonation_Dialog_JieKou;

    public void setTakeDonation_Dialog_JieKou(TakeDonation_Dialog_JieKou takeDonation_Dialog_JieKou) {
        this.takeDonation_Dialog_JieKou = takeDonation_Dialog_JieKou;
    }
}
