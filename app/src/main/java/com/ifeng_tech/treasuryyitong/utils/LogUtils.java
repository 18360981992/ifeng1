package com.ifeng_tech.treasuryyitong.utils;

import android.util.Log;

/**
 * Created by Dash
 *
 * 现在未签名的应用都是debug版本的...
 * 上线的时候要对应用签名
 */
public class LogUtils {
    //是否是调试模式
    public static final  boolean isDebug = true;

    public static void i(String TAG,String info){
        if(isDebug){
            Log.i(TAG,info);
        }
    }
    public static void d(String TAG,String info){
        if(isDebug){
            Log.d(TAG,info);
        }
    }
    public static void e(String TAG,String info){
        if(isDebug){
            Log.e(TAG,info);
        }
    }
}
