package com.ifeng_tech.treasuryyitong.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzt on 2018/4/17.
 *
 * 心跳机制
 */

public class HeartbeatService extends Service {

    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private String uid;
    String kind="1000";
    private static int time=3;
    private static Map<String, String> map = new HashMap<>();

    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time=time+3;
            if(time>=30) time=3;  // 监测心跳 间隔不易太长，已10次为一轮回，也就是30秒
            getXinTiao();
        }
    };

    public interface HearbeatJieKou{
        void hearbeatChuan(int type,int num);
    }

    public static HearbeatJieKou hearbeatJieKou;

    public static void setHearbeatJieKou(HearbeatJieKou hearbeatJieKou) {
        HeartbeatService.hearbeatJieKou = hearbeatJieKou;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sp = getSharedPreferences("wc", MODE_PRIVATE);
        edit = sp.edit();
        uid = sp.getString("uid", "");

        map.clear();
        // 获取时间搓 （精确到秒）
        long time = new Date().getTime() / 1000;
        // 将时间搓加入集合
        map.put("t",String.valueOf(time));
        map.put("uid",uid);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getXinTiao(); // 首次监测 心跳
        return super.onStartCommand(intent, flags, startId);
    }

    private  void getXinTiao() {

//        LogUtils.i("jiba","====="+time);

        if(5== DashApplication.sp.getInt(SP_String.NEWS_NUM,0)){ // 如果网络获取到的数据和本地保存的相同，继续下次的心跳

            h.sendEmptyMessageDelayed(0,time*1000);

        }else{     //  如果消息数目不同 将获取到的消息数目 以及消息的类型 传回消息页面，并跟新ui界面
            // 同时将时间重新开始计数,并重新开始心跳
            time=3;
            /**
             *  第一个参数  消息类型
             *  第二个参数  消息数目
             */
            // 计算出消息差 并传回
            int anInt = DashApplication.sp.getInt(SP_String.NEWS_NUM, 0);
            int num= 5-anInt;
            int type=3;

            DashApplication.edit.putInt(SP_String.NEWS_NUM,num).commit();

            hearbeatJieKou.hearbeatChuan(type,num);

            getXinTiao();
        }
    }




}
