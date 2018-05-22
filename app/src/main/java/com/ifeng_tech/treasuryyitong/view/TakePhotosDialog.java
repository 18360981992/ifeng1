package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.ifeng_tech.treasuryyitong.R;


/**
 * Created by zzt on 2018/4/10.
 *
 * 弹出换头像的dialog框
 */

public class TakePhotosDialog extends Dialog {

    public TakePhotosDialog(Context context) {
        super(context);
        this.show();
    }

    public TakePhotosDialog(Context context, int theme) {
        super(context, theme);
        this.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_takephotos);
    }
}
