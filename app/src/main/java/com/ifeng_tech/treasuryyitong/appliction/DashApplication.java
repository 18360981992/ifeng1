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

import cn.jpush.android.api.JPushInterface;
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

    public static int REGISTER_TO_CONCEAL_req=100;  // 从注册跳到隐私页面
    public static int REGISTER_TO_CONCEAL_res=101;

    public static int CERTIFICATION_TO_ADVP_req=100;
    public static int CERTIFICATION_TO_ADVP_res=101;

    public static int LOGIN_TO_REGISTER_req=100;   //
    public static int LOGIN_TO_REGISTER_res=101;

    public static int EMAIL1_TO_EMAIL2_req=100;  // 从绑定邮箱1跳到邮箱2
    public static int EMAIL1_TO_EMAIL2_res=101;

    public static int EMAIL2_TO_YEWU_req=300;  // 从绑定邮箱2跳到业务密码设置
    public static int EMAIL2_TO_YEWU_res=301;

    public static int BUSINESS_TO_DISREM_req=100;  // 从 不记得 跳到 绑定邮箱
    public static int BUSINESS_TO_DISREM_res=101;

    public static int BUSINESS_TO_REMEM_req=200;  // 从 记得 跳到 重置
    public static int BUSINESS_TO_REMEM_res=201;

    public static int BUJIDE_TO_YEWUPASS_req=100;  // 不记得邮箱 跳到 业务密码设置
    public static int BUJIDE_TO_YEWUPASS_res=101;


    public static int WITHDRAW_TO_WITHDRAW2_req=100;  // 从提现跳到提现2
    public static int WITHDRAW_TO_WITHDRAW2_res=101;

    public static int QR_TO_QR2_req=100;  // 从商品二维码跳到设置数量
    public static int QR_TO_QR2_res=101;

    public static int ERWIMA_SAOMIAO_req=300;  // 首页二维码扫描
    public static int ERWIMA_SAOMIAO_res=301;

    public static int DONATION_SAOMIAO_req=300;  // 转赠二维码扫描
    public static int DONATION_SAOMIAO_res=301;


    public static int ZHUAN_TO_CANGKU_req=100;  // 转赠跳到新仓库
    public static int ZHUAN_TO_CANGKU_res=101;

    public static int TIHUO_TO_CANGKU_req=200;  // 提货注册跳到新仓库
    public static int TIHUO_TO_CANGKU_res=201;


    public static  int ANQUAN_TYPE_ZHAOHUI=1;   // 找回密码/忘记密码  两个用的是相同的
    public static  int ANQUAN_TYPE_GENGGAI=2;  // 更改

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

    public static SharedPreferences sp_huan;
    public static SharedPreferences.Editor edit_huan;


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

        // 全局捕获异常
//        CrashHandler.getInstance().init(getApplicationContext());

        decimalFormat = new DecimalFormat("0.00");// 全局为double设置精度

        initScreenSize();

        sp = getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();

        sp_huan = getSharedPreferences("huan_cun", MODE_PRIVATE);
        edit_huan = sp_huan.edit();



//        sp_message_xitong = getSharedPreferences("ifeng_message_xitong", MODE_PRIVATE);
//        edit_message_xitong = sp_message_xitong.edit();
//
//        sp_message_chongzhi = getSharedPreferences("ifeng_message_chongzhi", MODE_PRIVATE);
//        edit_message_chongzhi = sp_message_chongzhi.edit();
//
//        sp_message_anquan = getSharedPreferences("ifeng_message_anquan", MODE_PRIVATE);
//        edit_message_anquan = sp_message_anquan.edit();



        //获取安卓手机的唯一标识的方法
        android = 'A' + Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);


        // 对log 的初始化
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        JPushInterface.init(this); // 初始化 JPushs。如果已经初始化，但没有登录成功，则执行重新登录。
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
