package com.ifeng_tech.treasuryyitong.appliction;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Process;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;


/**
 * Created by Dash on 2017/12/6.
 */
public class DashApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainId;
    private static DashApplication instance;


    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;
    public static DecimalFormat decimalFormat; // 全局为double设置精度

    public static int RETRIEVE_TO_FORGET_req=100;
    public static int RETRIEVE_TO_FORGET_res=101;

    public static int RETRIEVE_TO_CHANGE_req=100;
    public static int RETRIEVE_TO_CHANGE_res=101;

    public static int CERTIFICATION_TO_ADVP_req=100;
    public static int CERTIFICATION_TO_ADVP_res=101;

    public static int LOGIN_TO_REGISTER_req=100;
    public static int LOGIN_TO_REGISTER_res=101;

    public static int EMAIL1_TO_EMAIL2_req=100;
    public static int EMAIL1_TO_EMAIL2_res=101;

    public static  int ANQUAN_TYPE_ZHAOHUI=1;
    public static  int ANQUAN_TYPE_GENGGAI=2;

    public static SharedPreferences sp;
    public static SharedPreferences.Editor edit;



    @Override
    public void onCreate() {
        super.onCreate();
        //引用图片
        //关于context----http://blog.csdn.net/lmj623565791/article/details/40481055
        context = getApplicationContext();

        //初始化handler
        handler = new Handler();
        //主线程的id
        mainId = Process.myTid();
//        CrashHandler.getInstance().init(getApplicationContext());

        decimalFormat = new DecimalFormat("0.00");// 全局为double设置精度

        initScreenSize();

        sp = getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();

    }

    public static DashApplication getInstance() {
        if (instance == null) {
            instance = new DashApplication();
        }
        return instance;
    }
    /**
     * 对外提供了context
     * @return
     */
    public static Context getAppContext() {
        return context;
    }

    /**
     * 得到全局的handler
     * @return
     */
    public static Handler getAppHanler() {
        return handler;
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int getMainThreadId() {
        return mainId;
    }



    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();

        DisplayMetrics metrics = new DisplayMetrics();
        // 取得窗口属性
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }
}
