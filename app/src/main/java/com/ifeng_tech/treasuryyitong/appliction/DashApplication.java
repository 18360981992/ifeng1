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
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

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

    public static int LOGIN_TO_REGISTER_req=100;   //
    public static int LOGIN_TO_REGISTER_res=101;

    public static int EMAIL1_TO_EMAIL2_req=100;  // 从绑定邮箱1跳到邮箱2
    public static int EMAIL1_TO_EMAIL2_res=101;

    public static int BUSINESS_TO_DISREM_req=100;  // 从重置密码不记得跳到绑定邮箱
    public static int BUSINESS_TO_DISREM_res=101;

    public static int BUSINESS_TO_REMEM_req=200;  // 从重置密码记得跳到重置
    public static int BUSINESS_TO_REMEM_res=201;

    public static int WITHDRAW_TO_WITHDRAW2_req=100;  // 从提现跳到提现2
    public static int WITHDRAW_TO_WITHDRAW2_res=101;

    public static int QR_TO_QR2_req=100;  // 从商品二维码跳到设置数量
    public static int QR_TO_QR2_res=101;

    public static int ERWIMA_SAOMIAO_req=300;  // 二维码扫描
    public static int ERWIMA_SAOMIAO_res=301;

    public static int ZHUAN_TO_CANGKU_req=100;  // 转赠跳到新仓库
    public static int ZHUAN_TO_CANGKU_res=101;

    public static int TIHUO_TO_CANGKU_req=200;  // 提货注册跳到新仓库
    public static int TIHUO_TO_CANGKU_res=201;


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
    public static SharedPreferences sp_message_xitong;
    public static SharedPreferences.Editor edit_message_xitong;

    public static SharedPreferences sp_message_chongzhi;
    public static SharedPreferences.Editor edit_message_chongzhi;

    public static SharedPreferences sp_message_anquan;
    public static SharedPreferences.Editor edit_message_anquan;

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

        sp_message_xitong = getSharedPreferences("ifeng_message_xitong", MODE_PRIVATE);
        edit_message_xitong = sp_message_xitong.edit();

        sp_message_chongzhi = getSharedPreferences("ifeng_message_chongzhi", MODE_PRIVATE);
        edit_message_chongzhi = sp_message_chongzhi.edit();

        sp_message_anquan = getSharedPreferences("ifeng_message_anquan", MODE_PRIVATE);
        edit_message_anquan = sp_message_anquan.edit();

        //获取安卓手机的唯一标识的方法
        android = 'A' + Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);


        // 对log 的初始化
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);

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
