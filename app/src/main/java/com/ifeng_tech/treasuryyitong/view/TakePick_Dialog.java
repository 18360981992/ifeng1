package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by zzt on 2018/6/14.
 */

public class TakePick_Dialog extends Dialog {


    private TextView up_goods_zhuce_pass_tishi;
    private EditText up_goods_zhuce_pass;
    private TextView up_goods_zhuce_zai_tishi;
    private EditText up_goods_zhuce_zai;
    private Button up_goods_zhuce_dialog_queren;
    private Button up_goods_zhuce_dialog_quxiao;
    Context context;

    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            takePick_dialog_JieKou.chuan(tipass);
        }
    };
    private String tipass;

    public TakePick_Dialog(@NonNull Context context) {
        super(context);
        this.context=context;
        this.show();
    }

    public TakePick_Dialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context=context;

        this.show();
    }

    protected TakePick_Dialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
       this.context=context;

        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takepick_dialog);
        init();

        // 确认按钮
        up_goods_zhuce_dialog_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(up_goods_zhuce_pass_tishi.getWindowToken(), 0);

                tipass = up_goods_zhuce_pass.getText().toString().trim();
                if (TextUtils.isEmpty(tipass)) {
                    up_goods_zhuce_pass_tishi.setText("提货密码不能为空");
                    up_goods_zhuce_pass_tishi.setVisibility(View.VISIBLE);
                    return;
                }else{
                    up_goods_zhuce_pass_tishi.setVisibility(View.INVISIBLE);
                }

                if(tipass.length()!=8){
                    up_goods_zhuce_pass_tishi.setText("请设置8位纯数字的提货密码");
                    up_goods_zhuce_pass_tishi.setVisibility(View.VISIBLE);
                    return;
                }else{
                    up_goods_zhuce_pass_tishi.setVisibility(View.INVISIBLE);
                }

                String zaipass = up_goods_zhuce_zai.getText().toString().trim();
                if (!tipass.equals(zaipass)) {
                    up_goods_zhuce_zai_tishi.setText("两次输入不一致");
                    up_goods_zhuce_zai_tishi.setVisibility(View.VISIBLE);
                    return;
                }else{
                    up_goods_zhuce_zai_tishi.setVisibility(View.INVISIBLE);
                }

                dismiss();
                h.sendEmptyMessageDelayed(0,500);
            }
        });

        // 取消按钮
        up_goods_zhuce_dialog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(up_goods_zhuce_pass_tishi.getWindowToken(), 0);
                dismiss();
            }
        });
    }

    public void init() {
        up_goods_zhuce_pass_tishi = findViewById(R.id.up_goods_zhuce_pass_tishi);
        up_goods_zhuce_pass = findViewById(R.id.up_goods_zhuce_pass);
        up_goods_zhuce_zai_tishi = findViewById(R.id.up_goods_zhuce_zai_tishi);
        up_goods_zhuce_zai = findViewById(R.id.up_goods_zhuce_zai);
        up_goods_zhuce_dialog_queren = findViewById(R.id.up_goods_zhuce_dialog_queren);
        up_goods_zhuce_dialog_quxiao = findViewById(R.id.up_goods_zhuce_dialog_quxiao);
    }

    public interface TakePick_dialog_JieKou{
        void chuan(String tipass);
    }
    TakePick_dialog_JieKou takePick_dialog_JieKou;

    public void setTakePick_dialog_JieKou(TakePick_dialog_JieKou takePick_dialog_JieKou) {
        this.takePick_dialog_JieKou = takePick_dialog_JieKou;
    }
}
