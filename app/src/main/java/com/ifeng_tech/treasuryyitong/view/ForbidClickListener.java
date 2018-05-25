package com.ifeng_tech.treasuryyitong.view;

import android.view.View;

/**
 * Created by zzt on 2018/5/25.
 *
 *  自定义点击事件  好处就是禁止按钮连续点击
 */

public abstract class ForbidClickListener implements View.OnClickListener{
    public static int MIN_CLICK_TIME=1800;
    private long lastTime=0;

    @Override
    public void onClick(View v) {

        if ((System.currentTimeMillis() - lastTime) > 1800) {
//            LogUtils.i("jiba","点击了几次");
            lastTime = System.currentTimeMillis();
            forbidClick(v);
        }
    }

    public abstract void forbidClick(View v);
}
