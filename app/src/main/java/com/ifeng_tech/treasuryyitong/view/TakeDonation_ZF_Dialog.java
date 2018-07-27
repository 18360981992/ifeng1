package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;

/**
 * Created by zzt on 2018/7/4.
 */

public class TakeDonation_ZF_Dialog extends Dialog {

    private RelativeLayout donation_zf_dialog_quxiao;
    private TextView donation_zf_zongjia;
    private ImageView donation_zf_dialog_keyong_img;
    private TextView donation_zf_dialog_keyong_text;
    private LinearLayout donation_zf_dialog_keyong;
    private ImageView donation_zf_dialog_zhifubao_img;
    private LinearLayout donation_zf_dialog_zhifubao;
    private Button donation_zf_dialog_queren;

    Context context;
    public int type=0;
    private TextView donation_zf_dialog_zhifubao_text;

    public TakeDonation_ZF_Dialog(@NonNull Context context) {
        super(context);
        this.show();
    }

    public TakeDonation_ZF_Dialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context=context;
        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takedonation_zf_dialog);

        donation_zf_dialog_quxiao=findViewById(R.id.donation_zf_dialog_quxiao);
        donation_zf_zongjia=findViewById(R.id.donation_zf_zongjia);
        donation_zf_dialog_keyong_img=findViewById(R.id.donation_zf_dialog_keyong_img);
        donation_zf_dialog_keyong_text=findViewById(R.id.donation_zf_dialog_keyong_text);
        donation_zf_dialog_keyong=findViewById(R.id.donation_zf_dialog_keyong);
        donation_zf_dialog_zhifubao_img=findViewById(R.id.donation_zf_dialog_zhifubao_img);
        donation_zf_dialog_zhifubao_text = findViewById(R.id.donation_zf_dialog_zhifubao_text);
        donation_zf_dialog_zhifubao=findViewById(R.id.donation_zf_dialog_zhifubao);

        donation_zf_dialog_queren=findViewById(R.id.donation_zf_dialog_queren);


        donation_zf_dialog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        donation_zf_dialog_keyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation_zf_dialog_keyong_img.setImageResource(R.drawable.zhifu_xuan);
                donation_zf_dialog_zhifubao_img.setImageResource(R.drawable.zhifu_weixuan);
                type=0; // 选择余额支付
            }
        });

        donation_zf_dialog_zhifubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation_zf_dialog_keyong_img.setImageResource(R.drawable.zhifu_weixuan);
                donation_zf_dialog_zhifubao_img.setImageResource(R.drawable.zhifu_xuan);
                type=1;  // 选择支付宝
            }
        });

        donation_zf_dialog_queren.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                dismiss();
                takeDonation_ZF_Dialog_JieKou.queren(type);
            }
        });

    }

    public void setZongjia(String zongjia){
        donation_zf_zongjia.setText(zongjia);
    }

    public void setKeYong_img(int img){
        donation_zf_dialog_keyong_img.setImageResource(img);
    }

    public void setZhiFuBao_img(int img){
        donation_zf_dialog_zhifubao_img.setImageResource(img);
    }


    // 设置余额禁止点击
    public void setKeYong_Anniu(boolean flag,String text){
        if(flag==false){
            donation_zf_dialog_keyong.setEnabled(false);
            donation_zf_dialog_keyong_img.setImageResource(R.drawable.zhifu_weixuan);
            donation_zf_dialog_keyong_text.setText(text);
            donation_zf_dialog_keyong_text.setTextColor(context.getResources().getColor(R.color.color_999999));
            donation_zf_dialog_zhifubao_img.setImageResource(R.drawable.zhifu_xuan);
            type=1;  // 选择支付宝
        }else{
            donation_zf_dialog_keyong.setEnabled(true);
            donation_zf_dialog_keyong_img.setImageResource(R.drawable.zhifu_xuan);
            donation_zf_dialog_keyong_text.setText(text);
            donation_zf_dialog_keyong_text.setTextColor(context.getResources().getColor(R.color.color_666666));
            donation_zf_dialog_zhifubao_img.setImageResource(R.drawable.zhifu_weixuan);
            type=0; // 选择余额支付
        }
    }

    public void setZhifuBao_anniu(){
        donation_zf_dialog_zhifubao.setEnabled(false);
        donation_zf_dialog_zhifubao_img.setImageResource(R.drawable.zhifu_weixuan);
        donation_zf_dialog_zhifubao_text.setTextColor(context.getResources().getColor(R.color.color_999999));
    }


    public interface TakeDonation_ZF_Dialog_JieKou{
        void queren(int type);
    }
    TakeDonation_ZF_Dialog_JieKou takeDonation_ZF_Dialog_JieKou;

    public void setTakeDonation_ZF_Dialog_JieKou(TakeDonation_ZF_Dialog_JieKou takeDonation_ZF_Dialog_JieKou) {
        this.takeDonation_ZF_Dialog_JieKou = takeDonation_ZF_Dialog_JieKou;
    }
}
