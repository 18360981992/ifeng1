package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;


/**
 * Created by zzt on 2018/4/16.
 */

public class TakeCommonDialog extends Dialog {
    String text;
    private TextView dialog_quxiao;
    private TextView dialog_queren;
    private TextView dialog_text;

    public interface CommonJieKou{
        void quxiao();
        void queren();
    }

    CommonJieKou commonJieKou;

    public void setCommonJieKou(CommonJieKou commonJieKou) {
        this.commonJieKou = commonJieKou;
    }

    public TakeCommonDialog(@NonNull Context context) {
        super(context);this.show();
    }

    public TakeCommonDialog(@NonNull Context context, @StyleRes int themeResId, String text) {
        super(context, themeResId);this.show();
        this.text=text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog);
        dialog_text = findViewById(R.id.dialog_text);
        dialog_quxiao = findViewById(R.id.dialog_quxiao);
        dialog_queren = findViewById(R.id.dialog_queren);
    }

    @Override
    public void show() {
        super.show();
        dialog_text.setText(text);
        dialog_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonJieKou.queren();
            }
        });

        dialog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commonJieKou.quxiao();
            }
        });
    }
}
