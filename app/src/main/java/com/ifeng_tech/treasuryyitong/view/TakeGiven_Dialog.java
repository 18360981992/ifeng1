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
 * Created by zzt on 2018/7/13.
 */

public class TakeGiven_Dialog extends Dialog {
    Context context;
    private TextView takeGiven_Dialog_text;
    private TextView takeGiven_Dialog_quxiao;
    private TextView takeGiven_Dialog_tongyi;

    public TakeGiven_Dialog(@NonNull Context context) {
        super(context);
        this.show();
        this.context = context;
    }

    public TakeGiven_Dialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.show();
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takegiven_dialog);
        takeGiven_Dialog_text = findViewById(R.id.takeGiven_Dialog_text);
        takeGiven_Dialog_quxiao = findViewById(R.id.takeGiven_Dialog_quxiao);
        takeGiven_Dialog_tongyi = findViewById(R.id.takeGiven_Dialog_tongyi);

        takeGiven_Dialog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        takeGiven_Dialog_tongyi.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                dismiss();
                takeGiven_Dialog_JieKou.chuan();
            }
        });
    }

    public void setTakeGiven_Dialog_text(String ss) {
        takeGiven_Dialog_text.setText(ss);
    }

    public interface TakeGiven_Dialog_JieKou{
        void chuan();
    }

    TakeGiven_Dialog_JieKou takeGiven_Dialog_JieKou;

    public void setTakeGiven_Dialog_JieKou(TakeGiven_Dialog_JieKou takeGiven_Dialog_JieKou) {
        this.takeGiven_Dialog_JieKou = takeGiven_Dialog_JieKou;
    }
}
