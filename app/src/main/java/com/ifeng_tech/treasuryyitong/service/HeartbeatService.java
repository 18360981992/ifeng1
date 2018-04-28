package com.ifeng_tech.treasuryyitong.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.ifeng_tech.treasuryyitong.utils.BaseServer;
import com.ifeng_tech.treasuryyitong.utils.RetrofitFacety;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzt on 2018/4/17.
 */

public class HeartbeatService extends Service {

    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private String uid;
    String kind="1000";
    private static int time=3;
    private static Map<String, String> map = new HashMap<>();

   private static Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            time=time+3;
            if(time>=30) time=3;  // 监测心跳 间隔不易太长，已10次为一轮回，也就是30秒
            getXinTiao();
        }
    };

    public interface HearbeatJieKou{
        void hearbeatChuan(int num);
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

    private static void getXinTiao() {
        RetrofitFacety.post("",map)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String myBean) {

                    }

                    @Override
                    public void onErroy(String ss) {

                    }
                });


    }


}
