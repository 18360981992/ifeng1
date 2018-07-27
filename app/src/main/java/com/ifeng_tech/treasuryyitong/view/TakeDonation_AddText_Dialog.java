package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by zzt on 2018/7/4.
 */

public class TakeDonation_AddText_Dialog extends Dialog {

    private EditText takeDonation_AddText_Dialog_text;
    private TextView takeDonation_AddText_Dialog_quxiao;
    private TextView takeDonation_AddText_Dialog_queren;

    Context context;

    public TakeDonation_AddText_Dialog(@NonNull Context context) {
        super(context);
        this.show();
    }

    public TakeDonation_AddText_Dialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.show();
        this.context=context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takedonation_addtext_dialog);

        takeDonation_AddText_Dialog_text=findViewById(R.id.takeDonation_AddText_Dialog_text);
        takeDonation_AddText_Dialog_quxiao=findViewById(R.id.takeDonation_AddText_Dialog_quxiao);
        takeDonation_AddText_Dialog_queren=findViewById(R.id.takeDonation_AddText_Dialog_queren);

        takeDonation_AddText_Dialog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击 强制关闭软键盘
                InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(takeDonation_AddText_Dialog_text.getWindowToken(), 0);
                dismiss();
            }
        });

        takeDonation_AddText_Dialog_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击 强制关闭软键盘
                InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(takeDonation_AddText_Dialog_text.getWindowToken(), 0);
                String trim = takeDonation_AddText_Dialog_text.getText().toString().trim();
                takeDonation_AddText_Dialog_JieKou.queren(trim+"");
                dismiss();
            }
        });
    }

    public interface TakeDonation_AddText_Dialog_JieKou{
        void queren(String text);
    }

    TakeDonation_AddText_Dialog_JieKou takeDonation_AddText_Dialog_JieKou;

    public void setTakeDonation_AddText_Dialog_JieKou(TakeDonation_AddText_Dialog_JieKou takeDonation_AddText_Dialog_JieKou) {
        this.takeDonation_AddText_Dialog_JieKou = takeDonation_AddText_Dialog_JieKou;
    }
}
