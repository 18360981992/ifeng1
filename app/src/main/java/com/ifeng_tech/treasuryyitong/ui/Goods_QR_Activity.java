package com.ifeng_tech.treasuryyitong.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.bean.my.QR_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SignUtils;
import com.jwsd.libzxing.QRCodeManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *  商品二维码
 */
public class Goods_QR_Activity extends BaseMVPActivity<Goods_QR_Activity,MyPresenter<Goods_QR_Activity>> {

    private RelativeLayout goods_qr_Fan;
    private TextView goods_qr_title;
    private ImageView goods_qr_erweima;
    private TextView goods_qr_shezhi;
    private TextView goods_qr_baocun;
    private WarehouseBean.DataBean.ListBean warehouseBean;
    private int delivery_erweima_height;
    private int delivery_erweima_width;

    @Override
    public MyPresenter<Goods_QR_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods__qr_);
        initView();
        goods_qr_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        warehouseBean = (WarehouseBean.DataBean.ListBean) getIntent().getSerializableExtra("WarehouseBean");

        if(warehouseBean.getGoodsName().length()>10){
            String name = warehouseBean.getGoodsName().substring(0, 10);
            goods_qr_title.setText(name+"...");      // 藏品名称
        }else{
            goods_qr_title.setText(warehouseBean.getGoodsName());
        }

        goods_qr_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Goods_QR_Activity.this, Goods_Setting_Num_Activity.class);
                startActivityForResult(intent, DashApplication.QR_TO_QR2_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        goods_qr_baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("图片已保存");
            }
        });

        QR_Bean qr_bean = new QR_Bean(DashApplication.sp.getString(SP_String.USERCODE, ""), new QR_Bean.GoodsInfo(warehouseBean.getGoodsId(),warehouseBean.getGoodsCode(),warehouseBean.getGoodsName(),"1"));

        String json = new Gson().toJson(qr_bean);
//            String encodeStr = URLEncoder.encode(json, "utf-8");  // json传需要转码
//            String ReferralCode= encodeStr;
        String path=SP_String.QR_ZHUANZENG;  // 2 是跳到转赠页面 并屏蔽输入框的获焦事件
        int width=300;
        int height=300;
        getQR_Img(json, path, width, height);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void getQR_Img(String ReferralCode,String path,int width,int height) {
//        String url= APIs.debugApi+"goods/getTwoDimension?ReferralCode="+ReferralCode+"&path="+path+"&width="+width+"&height="+height;

//        myPresenter.getPro_TuXingYanZheng(url, new MyJieKou() {
//            @Override
//            public void chenggong(Bitmap bitmap) {
//                Bitmap bitmap1 = ImageUtils.zoomImage(bitmap, delivery_erweima_width, delivery_erweima_height);
//                goods_qr_erweima.setImageBitmap(bitmap1);
//            }
//
//            @Override
//            public void shibai(String ss) {
//                MyUtils.setToast(ss);
//            }
//        });

        try {
            String pp=SP_String.QR_ZHUANZENG+"?ReferralCode="+ReferralCode;

            String s = SignUtils.encode(pp);  // DES加密

//            LogUtils.i("jiba","s==="+s);

            Bitmap image =  QRCodeManager.getInstance().createQRCode(s, 300, 300);
            goods_qr_erweima.setImageBitmap(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DashApplication.QR_TO_QR2_req&&resultCode==DashApplication.QR_TO_QR2_res){

            String amount = data.getStringExtra("amount"); // 数量
//            LogUtils.i("jiba","amount==="+amount);
            QR_Bean qr_bean = new QR_Bean(DashApplication.sp.getString(SP_String.USERCODE, ""), new QR_Bean.GoodsInfo(warehouseBean.getGoodsId(),warehouseBean.getGoodsCode(),warehouseBean.getGoodsName(),amount));
            String json = new Gson().toJson(qr_bean);
//            LogUtils.i("jiba","amount==="+json);
            try {
                String encodeStr = URLEncoder.encode(json, "utf-8");  // json传需要转码
                String ReferralCode= encodeStr;
                String path=SP_String.QR_ZHUANZENG;  // 2 是跳到转赠页面 并屏蔽输入框的获焦事件
                int width=300;
                int height=300;
                getQR_Img( json, path, width, height);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void initView() {
        goods_qr_Fan = (RelativeLayout) findViewById(R.id.goods_qr_Fan);
        goods_qr_title = (TextView) findViewById(R.id.goods_qr_title);
        goods_qr_erweima = (ImageView) findViewById(R.id.goods_qr_erweima);
        goods_qr_shezhi = (TextView) findViewById(R.id.goods_qr_shezhi);
        goods_qr_baocun = (TextView) findViewById(R.id.goods_qr_baocun);

//        //通过设置监听来获取 微弹窗 控件的高度
//        goods_qr_erweima.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onGlobalLayout() {
//                goods_qr_erweima.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                //获取ImageView控件的初始高度  用来图片回弹时
//                delivery_erweima_height = goods_qr_erweima.getMeasuredHeight();
//                delivery_erweima_width = goods_qr_erweima.getMeasuredWidth();
//            }
//        });


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
