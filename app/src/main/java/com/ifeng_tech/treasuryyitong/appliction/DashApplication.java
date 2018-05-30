package com.ifeng_tech.treasuryyitong.appliction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import java.text.DecimalFormat;

import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by Dash on 2017/12/6.
 */
public class DashApplication extends TinkerApplication {

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
    public static String android;

    // 这是为了打印retrofit的log日志
    //打印retrofit日志
    public static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印retrofit日志
            Log.i("RetrofitLog","retrofitBack = "+message);
        }
    });

    public DashApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.ifeng_tech.treasuryyitong.appliction.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

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

        //获取安卓手机的唯一标识的方法
        android = 'A' + Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);


        // 对log 的初始化
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

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
