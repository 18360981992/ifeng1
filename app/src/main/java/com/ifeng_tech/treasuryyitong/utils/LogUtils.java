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
    public static final  boolean isDebug = false;

//    public static void i(String TAG,String info){
//        if(isDebug){
//            Log.i(TAG,info);
//        }
//    }
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

    public static void i(String tag, String msg) {  //信息太长,分段打印

        if(isDebug){
            //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
            //  把4*1024的MAX字节打印长度改为2001字符数
            int max_str_length = 2001 - tag.length();
            //大于4000时
            while (msg.length() > max_str_length) {
                Log.i(tag, msg.substring(0, max_str_length));
                msg = msg.substring(max_str_length);
            }
            //剩余部分
            Log.i(tag, msg);
        }

    }

}
