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
 * Created by zzt on 2018/7/30.
 */

public class TakeCollPhoneDialog extends Dialog {

    Context context;
    public TextView dialog_quxiao;
    public TextView dialog_queren;

    public TakeCollPhoneDialog(@NonNull Context context) {
        super(context);
        this.context=context;
        this.show();

    }

    public TakeCollPhoneDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context=context;
        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takecollphone_dialog);
        dialog_quxiao = findViewById(R.id.takeCollPhoneDialog_quxiao);
        dialog_queren = findViewById(R.id.takeCollPhoneDialog_queren);

        // 点击取消
        dialog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // 点击确认
        dialog_queren.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                dismiss();
                takeCollPhoneDialog_JieKou.chuan();
            }
        });
    }

    public interface TakeCollPhoneDialog_JieKou{
        void chuan();
    }
    TakeCollPhoneDialog_JieKou takeCollPhoneDialog_JieKou;

    public void setTakeCollPhoneDialog_JieKou(TakeCollPhoneDialog_JieKou takeCollPhoneDialog_JieKou) {
        this.takeCollPhoneDialog_JieKou = takeCollPhoneDialog_JieKou;
    }
}
