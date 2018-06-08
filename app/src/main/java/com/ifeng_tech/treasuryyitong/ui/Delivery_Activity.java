package com.ifeng_tech.treasuryyitong.ui;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.my.QR_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyJieKou;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.ImageUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 收货  二维码页面
 */
public class Delivery_Activity extends BaseMVPActivity<Delivery_Activity, MyPresenter<Delivery_Activity>> {

    private RelativeLayout delivery_Fan;
    private ImageView delivery_erweima;
    private int delivery_erweima_height;
    private int delivery_erweima_width;

    @Override
    public MyPresenter<Delivery_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_);
        initView();
        delivery_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    String ReferralCode="";
    String path="";
    int width;
    int height;
    @Override
    protected void onResume() {
        super.onResume();

        QR_Bean qr_bean = new QR_Bean(DashApplication.sp.getString(SP_String.USERCODE, ""), null);
        String json = new Gson().toJson(qr_bean);
//        LogUtils.i("jiba","qr_bean===="+json);
//
//        QR_Bean qr_bean1 = new Gson().fromJson(json, QR_Bean.class);
//        LogUtils.i("jiba","qr_bean1===="+qr_bean1.toString());

        try {
            String encodeStr = URLEncoder.encode(json, "utf-8");  // json传需要转码
            ReferralCode=encodeStr;
            path=SP_String.QR_ZHUANZENG;    //  跳到转赠  输入框可编辑
            width=300;
            height=300;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url= APIs.debugApi+"goods/getTwoDimension?ReferralCode="+ReferralCode+"&path="+path+"&width="+width+"&height="+height;

        myPresenter.getPro_TuXingYanZheng(url, new MyJieKou() {
            @Override
            public void chenggong(Bitmap bitmap) {
                Bitmap bitmap1 = ImageUtils.zoomImage(bitmap, delivery_erweima_width, delivery_erweima_height);
                delivery_erweima.setImageBitmap(bitmap1);
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
            }
        });
    }

    private void initView() {
        delivery_Fan = (RelativeLayout) findViewById(R.id.delivery_Fan);
        delivery_erweima = (ImageView) findViewById(R.id.delivery_erweima);
        //通过设置监听来获取 微弹窗 控件的高度
        delivery_erweima.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                delivery_erweima.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                delivery_erweima_height = delivery_erweima.getMeasuredHeight();
                delivery_erweima_width = delivery_erweima.getMeasuredWidth();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }




}
