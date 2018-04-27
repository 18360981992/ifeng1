package com.ifeng_tech.treasuryyitong.utils;

import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.appliction.DashApplication;

/**
 * Created by mypc on 2018/4/27.
 */

public class MyUtils {

    private static Toast toast;
    public static void setToast(String ss){
        if(toast==null){
            toast=Toast.makeText(DashApplication.getAppContext(),ss,Toast.LENGTH_SHORT);
        }
        toast.setText(ss);
        toast.show();
    }
}
