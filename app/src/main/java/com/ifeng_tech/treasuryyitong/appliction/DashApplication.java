package com.ifeng_tech.treasuryyitong.appliction;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.util.DisplayMetrics;

import com.ifeng_tech.treasuryyitong.utils.CrashHandler;


/**
 * Created by Dash on 2017/12/6.
 */
public class DashApplication extends Application {

    private static Context context;
    private static Handler handler;
    private static int mainId;
    public static boolean isLoginSuccess;//是否已经登录的状态
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
        CrashHandler.getInstance().init(getApplicationContext());

        initScreenSize();

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
