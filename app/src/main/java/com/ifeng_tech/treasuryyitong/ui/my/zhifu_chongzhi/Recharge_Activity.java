package com.ifeng_tech.treasuryyitong.ui.my.zhifu_chongzhi;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.alipay.PayResult;
import com.ifeng_tech.treasuryyitong.alipay.ResultInfo_Bean;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.tixian_congzhi.Recharge_AliPay_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *  充值
 */
public class Recharge_Activity extends BaseMVPActivity<Recharge_Activity, MyPresenter<Recharge_Activity>> implements View.OnClickListener {

    private RelativeLayout recharge_Fan;
    private TextView recharge_wushi;
    private TextView recharge_sanshi;
    private TextView recharge_yibai;
    private TextView recharge_wubai;
    private TextView recharge_sanbai;
    private TextView recharge_qita;
    private EditText recharge_chongzhi_qian;
    private LinearLayout recharge_zhifubao_fuxuan;
    private LinearLayout recharge_weixin_fuxuan;
    private Button recharge_btn;

    String amount="0";
    int paymentPlatform=1;

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    //使用返回的支付结果字符串构建一个支付结果对象
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);

                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&docType=1) 建议商户依赖异步通知
                     */
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。

                     String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                     String resultStatus = payResult.getResultStatus();
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    //----------app端拿到支付宝同步返回的结果,需要发送给服务器端,服务器端经过验证签名和解析支付结果,然后给app端返回最终支付的结果

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {

                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                       // MyUtils.setToast("支付成功,同步结果发送到服务端。。。");
//                        LogUtils.i("jiba","9000==="+resultInfo);
                        ResultInfo_Bean resultInfo_bean = new Gson().fromJson(resultInfo, ResultInfo_Bean.class);
                        //LogUtils.i("jiba","9000==="+resultInfo_bean.getAlipay_trade_app_pay_response().getOut_trade_no());

                        final ProgressDialog aniDialog = MyUtils.getProgressDialog(Recharge_Activity.this, SP_String.JIAZAI);

                        Map<String, String> map = new HashMap<>();
                        map.put("outTradeNo",resultInfo_bean.getAlipay_trade_app_pay_response().getOut_trade_no()+"");

                        myPresenter.postPreContent(APIs.pullAndUpdatePendingPaymentState, map, new MyInterfaces() {
                            @Override
                            public void chenggong(String json) {
                                aniDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(json);
                                    String code = (String) jsonObject.get("code");
                                    if(code.equals("2000")){
                                        MyUtils.setObjectAnimator_anquan(recharge_weitanchuan,
                                                recharge_weitanchuan_img,
                                                recharge_weitanchuan_text,
                                                weitanchuan_height,
                                                true,"支付成功！");
                                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                                            @Override
                                            public void chuan() {
                                                finish();
                                            }
                                        });
                                    }else{
                                        MyUtils.setObjectAnimator(recharge_weitanchuan,
                                                recharge_weitanchuan_img,
                                                recharge_weitanchuan_text,
                                                weitanchuan_height,
                                                false,(String) jsonObject.get("message"));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void shibai(String ss) {
                                aniDialog.dismiss();
                                MyUtils.setObjectAnimator(recharge_weitanchuan,
                                        recharge_weitanchuan_img,
                                        recharge_weitanchuan_text,
                                        weitanchuan_height,
                                        false,"支付失败！");
                            }
                        });

                    }else if(TextUtils.equals(resultStatus, "4000")){
                        MyUtils.setObjectAnimator(recharge_weitanchuan,
                                recharge_weitanchuan_img,
                                recharge_weitanchuan_text,
                                weitanchuan_height,
                                false,"支付失败！");

                        LogUtils.i("jiba","支付失败===4000");
                    } else{
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            MyUtils.setToast("支付结果确认中");

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            MyUtils.setObjectAnimator(recharge_weitanchuan,
                                    recharge_weitanchuan_img,
                                    recharge_weitanchuan_text,
                                    weitanchuan_height,
                                    false,"支付失败！");
                        }
                    }

                    /**
                     * 不管订单支付成功或者失败 订单是已经生成了 如果成功 跳转订单列表的已支付,,,如果失败跳转的是订单列表的待支付
                     */

                    break;
                }
                default:
                    break;
            }
        }

    };
    private ImageView recharge_zhifubao_img;
    private ImageView recharge_weixin_img;
    private LinearLayout recharge_weitanchuan;
    private ImageView recharge_weitanchuan_img;
    private TextView recharge_weitanchuan_text;
    private int weitanchuan_height;

    boolean isflag=true;
    @Override
    public MyPresenter<Recharge_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_);
        initView();

        recharge_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recharge_sanshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecharge_BG(true,false,false,false,false,false);
                recharge_chongzhi_qian.setText(DashApplication.decimalFormat.format(30));
                recharge_chongzhi_qian.setSelection(recharge_chongzhi_qian.getText().length());
                amount=DashApplication.decimalFormat.format(30);
                setTextView(recharge_chongzhi_qian,false);
            }
        });
        recharge_wushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecharge_BG(false,true,false,false,false,false);
                recharge_chongzhi_qian.setText(DashApplication.decimalFormat.format(50));
                recharge_chongzhi_qian.setSelection(recharge_chongzhi_qian.getText().length());
                amount=DashApplication.decimalFormat.format(50);
                setTextView(recharge_chongzhi_qian,false);
            }
        });
        recharge_yibai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecharge_BG(false,false,true,false,false,false);
                recharge_chongzhi_qian.setText(DashApplication.decimalFormat.format(100));
                recharge_chongzhi_qian.setSelection(recharge_chongzhi_qian.getText().length());
                amount=DashApplication.decimalFormat.format(100);
                setTextView(recharge_chongzhi_qian,false);
            }
        });
        recharge_sanbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecharge_BG(false,false,false,true,false,false);
                recharge_chongzhi_qian.setText(DashApplication.decimalFormat.format(300));
                amount=DashApplication.decimalFormat.format(300);
                recharge_chongzhi_qian.setSelection(recharge_chongzhi_qian.getText().length());
                setTextView(recharge_chongzhi_qian,false);
            }
        });
        recharge_wubai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecharge_BG(false,false,false,false,true,false);
                recharge_chongzhi_qian.setText(DashApplication.decimalFormat.format(500));
                amount=DashApplication.decimalFormat.format(500);
                recharge_chongzhi_qian.setSelection(recharge_chongzhi_qian.getText().length());
                setTextView(recharge_chongzhi_qian,false);
            }
        });

        recharge_qita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecharge_BG(false,false,false,false,false,true);
                amount=DashApplication.decimalFormat.format(0);
                recharge_chongzhi_qian.setText("");
                recharge_chongzhi_qian.setCursorVisible(true);
                setEditTexttHuo(recharge_chongzhi_qian);  // 使输入框获取焦点并弹出软键盘

            }
        });

        recharge_zhifubao_fuxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isflag){
                    recharge_zhifubao_img.setImageResource(R.drawable.zhuce_hui);
                    paymentPlatform=0;  // 任意值
                    isflag=false;
                }else{
                    recharge_zhifubao_img.setImageResource(R.drawable.zhuce_lan);
//                    recharge_weixin_img.setImageResource(R.drawable.zhuce_hui);
                    paymentPlatform=1;   // 支付宝
                    isflag=true;
                }

            }
        });

//        recharge_weixin_fuxuan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recharge_zhifubao_img.setImageResource(R.drawable.zhuce_hui);
//                recharge_weixin_img.setImageResource(R.drawable.zhuce_lan);
//                paymentPlatform=2;   // 微信
//            }
//        });


    }

    // 设置textview 是否可点击
    private void setTextView(EditText textView,boolean focusable) {
        textView.setFocusable(focusable);
        textView.setFocusableInTouchMode(focusable); // user touches widget on phone with touch screen
        textView.setClickable(focusable);
    }

    private void initView() {
        recharge_Fan = (RelativeLayout) findViewById(R.id.recharge_Fan);
        recharge_wushi = (TextView) findViewById(R.id.recharge_wushi);
        recharge_sanshi = (TextView) findViewById(R.id.recharge_sanshi);
        recharge_yibai = (TextView) findViewById(R.id.recharge_yibai);
        recharge_wubai = (TextView) findViewById(R.id.recharge_wubai);
        recharge_sanbai = (TextView) findViewById(R.id.recharge_sanbai);
        recharge_qita = (TextView) findViewById(R.id.recharge_qita);
        recharge_chongzhi_qian = (EditText) findViewById(R.id.recharge_chongzhi_qian);
        recharge_zhifubao_fuxuan = (LinearLayout) findViewById(R.id.recharge_zhifubao_fuxuan);
        recharge_zhifubao_img = (ImageView) findViewById(R.id.recharge_zhifubao_img);

//        recharge_weixin_fuxuan = (LinearLayout) findViewById(R.id.recharge_weixin_fuxuan);
//        recharge_weixin_img = (ImageView) findViewById(R.id.recharge_weixin_img);

        recharge_btn = (Button) findViewById(R.id.recharge_btn);

        recharge_weitanchuan = (LinearLayout) findViewById(R.id.recharge_weitanchuan);
        recharge_weitanchuan_img = (ImageView) findViewById(R.id.recharge_weitanchuan_img);
        recharge_weitanchuan_text = (TextView) findViewById(R.id.recharge_weitanchuan_text);

        recharge_btn.setOnClickListener(this);

        SoftHideKeyBoardUtil.assistActivity(this);  // 解决软键盘挡住输入框

        recharge_zhifubao_img.setImageResource(R.drawable.zhuce_lan); // 默认初始勾选支付宝
        setTextView(recharge_chongzhi_qian,false);  // 默认禁止输入框输入

        //通过设置监听来获取 微弹窗 控件的高度
        recharge_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                recharge_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = recharge_weitanchuan.getMeasuredHeight();
            }
        });
    }

    // 点击切换背景
    public void setRecharge_BG(boolean sanshi,boolean wushi,boolean yibai,boolean sanbai,boolean wubai,boolean qita){
        if(sanshi){
            recharge_sanshi.setBackgroundColor(getResources().getColor(R.color.zhuse));
            recharge_sanshi.setTextColor(getResources().getColor(R.color.baise));
        }else{
            recharge_sanshi.setBackground(getResources().getDrawable(R.drawable.chongzhi_kuang));
            recharge_sanshi.setTextColor(getResources().getColor(R.color.zhuse));
        }

        if(wushi){
            recharge_wushi.setBackgroundColor(getResources().getColor(R.color.zhuse));
            recharge_wushi.setTextColor(getResources().getColor(R.color.baise));
        }else{
            recharge_wushi.setBackground(getResources().getDrawable(R.drawable.chongzhi_kuang));
            recharge_wushi.setTextColor(getResources().getColor(R.color.zhuse));
        }

        if(yibai){
            recharge_yibai.setBackgroundColor(getResources().getColor(R.color.zhuse));
            recharge_yibai.setTextColor(getResources().getColor(R.color.baise));
        }else{
            recharge_yibai.setBackground(getResources().getDrawable(R.drawable.chongzhi_kuang));
            recharge_yibai.setTextColor(getResources().getColor(R.color.zhuse));
        }

        if(sanbai){
            recharge_sanbai.setBackgroundColor(getResources().getColor(R.color.zhuse));
            recharge_sanbai.setTextColor(getResources().getColor(R.color.baise));
        }else{
            recharge_sanbai.setBackground(getResources().getDrawable(R.drawable.chongzhi_kuang));
            recharge_sanbai.setTextColor(getResources().getColor(R.color.zhuse));
        }

        if(wubai){
            recharge_wubai.setBackgroundColor(getResources().getColor(R.color.zhuse));
            recharge_wubai.setTextColor(getResources().getColor(R.color.baise));
        }else{
            recharge_wubai.setBackground(getResources().getDrawable(R.drawable.chongzhi_kuang));
            recharge_wubai.setTextColor(getResources().getColor(R.color.zhuse));
        }

        if(qita){
            recharge_qita.setBackgroundColor(getResources().getColor(R.color.zhuse));
            recharge_qita.setTextColor(getResources().getColor(R.color.baise));
        }else{
            recharge_qita.setBackground(getResources().getDrawable(R.drawable.chongzhi_kuang));
            recharge_qita.setTextColor(getResources().getColor(R.color.zhuse));
        }

    }


   // String ss="alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016091500514662&biz_content=%7B%22out_trade_no%22%3A%22P00000000502018061111423043018603%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22APP%E7%AB%AF%E5%85%85%E5%80%BC%22%2C%22total_amount%22%3A%221000.2%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdemo.com%2Falipay%2Fnotify%2F&sign=YHpMAhq3Su1adEeF9wZfh3n09dwnbjkIQB6HrcDFyYSuCPGSZ4K%2BQ0rWpGmfpVRqzyHqBOUp63v8vEZR0PBN9RXiyjtRljZ5ZY4XvbL76NyeGVs3ZKcOTWpHZkqsI0x7ZTm7h1yYAhyW9shwVj3n%2B%2BUvB2SAW0k5ZOcf9eTclLI99Z6DVwNACOWrXCz8Gp%2FWPKYR2gMYnU14KLa2xODMQ2xTWtVuTpokt7NwNI6l2Tp5WcR4aXhy73f0FABsaZSIfq05EGPBihM69HzF3bTT9XjTgdMaCJf%2Fs7uV3KJsfJHM1YELlznghUN58h7ZRBNXMDYshLCxWuiCJdr3ir%2FZJg%3D%3D&sign_type=RSA2&timestamp=2018-06-11+11%3A43%3A20&version=1.0";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge_btn:
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(recharge_chongzhi_qian.getWindowToken(), 0);

                if(paymentPlatform==2){
                    MyUtils.setToast("微信功能正在开发中，请选择支付宝！！");
                    return;
                }
                String qian = recharge_chongzhi_qian.getText().toString();
                Double aDouble = Double.valueOf(qian);
                amount=DashApplication.decimalFormat.format(aDouble);

                Map<String, String> map = new HashMap<>();
                map.put("amount",amount);
                map.put("paymentPlatform",paymentPlatform+"");

                myPresenter.postPreContent(APIs.personalUserRecharge_app, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                Recharge_AliPay_Bean recharge_aliPay_bean = new Gson().fromJson(json, Recharge_AliPay_Bean.class);
                                final String orderStr = recharge_aliPay_bean.getData().getOrderStr();

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        //沙箱环境开启沙箱功能

                                        //正式环境不需要下面这行代码
                                        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
                                        // 构造PayTask 对象
                                        PayTask alipay = new PayTask(Recharge_Activity.this);
                                        // 调用支付接口，获取支付结果
                                        Map<String, String> result = alipay.payV2(orderStr, true);
                                        Message msg = new Message();
                                        msg.what = 0;
                                        msg.obj = result;
                                        mHandler.sendMessage(msg);
                                    }
                                }).start();


                            }else{
                                MyUtils.setObjectAnimator(recharge_weitanchuan,
                                        recharge_weitanchuan_img,
                                        recharge_weitanchuan_text,
                                        weitanchuan_height,
                                        false,(String) jsonObject.get("message"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        MyUtils.setObjectAnimator(recharge_weitanchuan,
                                recharge_weitanchuan_img,
                                recharge_weitanchuan_text,
                                weitanchuan_height,
                                false,ss);
                    }
                });
                break;
        }
    }




    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
