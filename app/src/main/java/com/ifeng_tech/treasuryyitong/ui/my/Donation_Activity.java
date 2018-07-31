package com.ifeng_tech.treasuryyitong.ui.my;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.alipay.PayResult;
import com.ifeng_tech.treasuryyitong.alipay.ResultInfo_Bean;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.bean.my.PersonalUserAccount_Bean;
import com.ifeng_tech.treasuryyitong.bean.my.QR_Bean;
import com.ifeng_tech.treasuryyitong.bean.tixian_congzhi.Recharge_AliPay_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.my.cangku.My_Warehouse_Activity2;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SignUtils;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.Search_Pop_View;
import com.ifeng_tech.treasuryyitong.view.TakeDonation_AddText_Dialog;
import com.ifeng_tech.treasuryyitong.view.TakeDonation_Dialog;
import com.ifeng_tech.treasuryyitong.view.TakeDonation_ZF_Dialog;
import com.jwsd.libzxing.OnQRCodeListener;
import com.jwsd.libzxing.QRCodeManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  转赠  表单填写  两种情况  从仓库进入  从首页进入
 */
public class Donation_Activity extends BaseMVPActivity<Donation_Activity,MyPresenter<Donation_Activity>> {

    private RelativeLayout donation_Fan;
    private EditText donation_huiyuan_word;
    private EditText donation_cangpin_wrod;
    private EditText donation_name;
    private EditText donation_shuliang;
    private TextView donation_danjia;
    private EditText donation_yewu_pass;
    private Button donation_tijiao;

    private int measuredWidth;
    private Search_Pop_View search_pop_view;

    private TextView donation_zuidae;

    private LinearLayout donation_weitanchuan;
    private ImageView donation_weitanchuan_img;
    private TextView donation_weitanchuan_text;
    private int weitanchuan_height;

    String goodsId="";  // 个人转赠 藏品id
    String amount="";   // 转赠数量
    String oppositeUserCode="";   //  转赠对方账号
    String businessPwd="";    //  业务密码
    String beizhu="";

    double qianKuan=0;  // 欠款
    double keYong=0;  // 可用资金

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

                        final ProgressDialog aniDialog = MyUtils.getProgressDialog(Donation_Activity.this, SP_String.JIAZAI);

                        Map<String, String> map = new HashMap<>();
                        map.put("outTradeNo",resultInfo_bean.getAlipay_trade_app_pay_response().getOut_trade_no()+"");

                        myPresenter.postPreContent(APIs.pullAndUpdatePendingPaymentState, map, new MyInterfaces() {
                            @Override
                            public void chenggong(String json) {
                                try {
                                    JSONObject jsonObject = new JSONObject(json);
                                    String code = (String) jsonObject.get("code");
                                    if(code.equals("2000")){
                                        HashMap<String, String> map = new HashMap<>();
                                        map.put("goodsId",goodsId);
                                        map.put("amount",amount);
                                        map.put("oppositeUserCode",oppositeUserCode);
                                        map.put("businessPwd",businessPwd);
                                        map.put("remarks",beizhu);
                                        getDonation_JieKou(map, aniDialog);  // 执行转赠接口
                                    }else{
                                        aniDialog.dismiss();
                                        MyUtils.setObjectAnimator(donation_weitanchuan,
                                                donation_weitanchuan_img,
                                                donation_weitanchuan_text,
                                                weitanchuan_height,
                                                false, (String) jsonObject.get("message"));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void shibai(String ss) {
                                aniDialog.dismiss();
                                MyUtils.setObjectAnimator(donation_weitanchuan,
                                        donation_weitanchuan_img,
                                        donation_weitanchuan_text,
                                        weitanchuan_height,
                                        false, ss);
                            }
                        });

                    }else if(TextUtils.equals(resultStatus, "4000")){
                        MyUtils.setObjectAnimator(donation_weitanchuan,
                                donation_weitanchuan_img,
                                donation_weitanchuan_text,
                                weitanchuan_height,
                                false, "支付失败！");

                        LogUtils.i("jiba","支付失败===4000");
                    } else{
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            MyUtils.setToast("支付结果确认中");

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            MyUtils.setObjectAnimator(donation_weitanchuan,
                                    donation_weitanchuan_img,
                                    donation_weitanchuan_text,
                                    weitanchuan_height,
                                    false, "支付失败！");
                        }
                    }

                    /**
                     * 不管订单支付成功或者失败 订单是已经生成了 如果成功 跳转订单列表的已支付,,,如果失败跳转的是订单列表的待支付
                     */

                    break;
                }
                default:
                    submit();  // 点击提交
                    break;
            }
        }

    };

    List<WarehouseBean.DataBean.ListBean> list = new ArrayList<WarehouseBean.DataBean.ListBean>();
    private String type;
    private ImageView donation_saoyisao;
    private RelativeLayout donation_title;
    private TextView donation_beizhu;


    @Override
    public MyPresenter<Donation_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_);

        initView();

        // 藏品代码失焦
        setTextView(donation_cangpin_wrod,false);

        // 藏品名称失焦
        setTextView(donation_name,false);

        // 模拟数据 做页面回显，其中暂无单价回显
        final Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if(type ==null){
            setTextView(donation_huiyuan_word,true);
            WarehouseBean.DataBean.ListBean warehouseBean = (WarehouseBean.DataBean.ListBean) intent.getSerializableExtra("WarehouseBean");
            if(warehouseBean!=null){
                donation_cangpin_wrod.setText(warehouseBean.getGoodsCode());
                if(warehouseBean.getGoodsName().length()>15){
                    String name = warehouseBean.getGoodsName().substring(0, 15);
                    donation_name.setText(name+"...");
                }else{
                    donation_name.setText(warehouseBean.getGoodsName());
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("goodsId",warehouseBean.getGoodsId()+"");
                getShouXuFei(map,donation_shuliang);  // 根据藏品id获取手续费
                donation_shuliang.setSelection(donation_shuliang.getText().length());
                goodsId= warehouseBean.getGoodsId()+"";  // 为藏品id赋值
            }else{
                donation_shuliang.setText("");
            }
        }else if(type.equals("1")){   //  从二维码进来后 , 从新仓库进来后  会员账号不让改
            String cword = intent.getStringExtra("cword");
            String name = intent.getStringExtra("name");
            String shuliang = intent.getStringExtra("shuliang");
            goodsId = intent.getStringExtra("goodsId");
            donation_cangpin_wrod.setText(cword);
            donation_name.setText(name);
            donation_shuliang.setHint(shuliang);

            get_QR_Moth(intent, type);

        }else if(type.equals("2")){  // 从旧仓库进来后 ，所有输入框禁止输入
            String cword = intent.getStringExtra("cword");
            String name = intent.getStringExtra("name");
            String shuliang = intent.getStringExtra("shuliang");
            goodsId = intent.getStringExtra("goodsId");
            donation_cangpin_wrod.setText(cword);
            donation_name.setText(name);
            donation_shuliang.setHint(shuliang);

            get_QR_Moth(intent, type);
        }

        // 点击返回
        donation_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        // 藏品代码输入监听  弹出pop框
//        donation_cangpin_wrod.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(s.toString().length()>4){ // 当输入框中数据长度大于4时，先去请求网络，然后将请求出来的数据装入集合，传入pop框
//                    String json = DashApplication.sp.getString(SP_String.CHICANG, "");
////                    LogUtils.i("jiba","===="+json);
//                    WarehouseBean warehouseBean1 = new Gson().fromJson(json, WarehouseBean.class);
//                    List<WarehouseBean.DataBean.ListBean> zilist = warehouseBean1.getData().getList();
//                    list.clear();
//                    for (WarehouseBean.DataBean.ListBean bean:zilist){
//                        if(bean.getGoodsCode().contains(s.toString())){
//                            list.addAll(zilist);
//                        }
//                    }
//                    if(list.size()>0){
//                        if(search_pop_view==null){
//                            search_pop_view = new Search_Pop_View(getApplicationContext(),measuredWidth,list);
//                            search_pop_view.setBackgroundDrawable(new BitmapDrawable());
//                            search_pop_view.showAsDropDown(donation_cangpin_wrod);
//                            donation_Pop_JieKou.chuan();
//                        }else{
//                            if(search_pop_view.isShowing()){  // 判断pop是隐藏/显示
//                                search_pop_view.setPopShuJu(list);
//                            }else{
//                                search_pop_view.showAsDropDown(donation_cangpin_wrod);
//                                search_pop_view.setPopShuJu(list);
//                            }
//                            donation_Pop_JieKou.chuan();
//                        }
//                    }
//                }else{
//                    if(search_pop_view!=null){
//                        search_pop_view.dismiss();
//                    }
//                }
//            }
//        });


        // 点击藏品代码跳转仓库  新仓库页面
        donation_cangpin_wrod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Donation_Activity.this, My_Warehouse_Activity2.class);
                String word = donation_huiyuan_word.getText().toString().trim();
                intent1.putExtra("userCode",word+"");
                intent1.putExtra("select","转赠");
                startActivityForResult(intent1,DashApplication.ZHUAN_TO_CANGKU_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        donation_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Donation_Activity.this, My_Warehouse_Activity2.class);
                String word = donation_huiyuan_word.getText().toString().trim();
                intent1.putExtra("userCode",word+"");
                intent1.putExtra("select","转赠");
                startActivityForResult(intent1,DashApplication.ZHUAN_TO_CANGKU_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });



        // 点击提交事件
        donation_tijiao.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 点击 强制关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(donation_yewu_pass.getWindowToken(), 0);
                mHandler.sendEmptyMessageDelayed(1,200);

            }
        });

        // 点击扫描二维码
        donation_saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(Donation_Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Donation_Activity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    return;
                }
                QRCodeManager.getInstance()
                        .with(Donation_Activity.this)
                        .setReqeustType(DashApplication.DONATION_SAOMIAO_req)
//                .setRequestCode(1001)
                        .scanningQRCode(new OnQRCodeListener() {
                            @Override
                            public void onCompleted(String des) {
                                LogUtils.i("jba","donation==="+des);
                                String result = null;
                                try {
                                    result = SignUtils.decode(des);
                                    if(result.contains(SP_String.QR_ZHUANZENG)){
                                        if(result.length()>20){
                                            String path = result.substring(0, result.indexOf("?"));
                                            String referralCode = result.substring(result.indexOf("=")+1, result.length());

//                            LogUtils.i("jiba","referralCode===="+referralCode);

                                            QR_Bean qr_bean = new Gson().fromJson(referralCode, QR_Bean.class);
                                            if(path.equals(SP_String.QR_ZHUANZENG)){  // 转赠自己跳自己
                                                Intent intent = new Intent(Donation_Activity.this, Donation_Activity.class);
                                                intent.putExtra("QR_Bean", referralCode);
                                                // 如果二维码扫描出来的结果藏品数量为0的时候 type传1 数量的输入框可以输入
                                                if(qr_bean.getGoodsInfo()==null||qr_bean.getGoodsInfo().getGoodsNum().equals(""))
                                                    intent.putExtra("type","1");  // 表示从扫描二维码跳入转赠  1 == 输入框可输入

                                                else  intent.putExtra("type","2");  // 表示从扫描二维码跳入转赠  2 == 输入框不可输入
                                                String cword = donation_cangpin_wrod.getText().toString().trim();
                                                String name = donation_name.getText().toString().trim();
                                                String shuliang = donation_shuliang.getHint().toString().trim();

                                                intent.putExtra("cword",cword+"");
                                                intent.putExtra("name",name+"");
                                                intent.putExtra("goodsId",goodsId+"");
                                                intent.putExtra("shuliang",shuliang+"");

                                                startActivityForResult(intent,500);
                                                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                            }
                                        }else{
                                            MyUtils.setToast(des);
                                        }
                                    }else{
                                        MyUtils.setToast(des);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    MyUtils.setToast(des);
                                }
                            }

                            @Override
                            public void onError(Throwable errorMsg) {
                                MyUtils.setToast("解析二维码失败=="+errorMsg);
                            }

                            @Override
                            public void onCancel() {
                                MyUtils.setToast("扫描任务取消了");
                            }

                            /**
                             * 当点击手动添加时回调
                             *
                             * @param requestCode
                             * @param resultCode
                             * @param data
                             */
                            @Override
                            public void onManual(int requestCode, int resultCode, Intent data) {
                                Log.i("jba","点击了手动添加了");
                            }


                        });
            }
        });

        // 点击添加备注
        donation_beizhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = donation_beizhu.getText().toString().trim();
                if(trim.equals("添加备注")){
                    getDiaLog();  // 获取弹出框
                }
            }
        });
    }

    // 获取弹出添加备注的框
    private void getDiaLog() {
        //设置dialog的样式
        final TakeDonation_AddText_Dialog dialog = new TakeDonation_AddText_Dialog(Donation_Activity.this, R.style.dialog_setting);
        MyUtils.getPuTongDiaLog(Donation_Activity.this,dialog);
        dialog.setTakeDonation_AddText_Dialog_JieKou(new TakeDonation_AddText_Dialog.TakeDonation_AddText_Dialog_JieKou() {
            @Override
            public void queren(String text) {
                if(!text.equals("")){
                    beizhu=text;
                    final SpannableStringBuilder style = new SpannableStringBuilder(text);
                    //设置文字
                    style.append("\t修改");

                    //设置部分文字点击事件
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View widget) {
//                            MyUtils.setToast("点击了修改。。。");
                            getDiaLog();
                        }
                        @Override
                        public void updateDrawState(TextPaint ds) {
                            ds.setUnderlineText(false);  // 去除修改下面的下划线
//                             super.updateDrawState(ds);
                        }
                    };

                    style.setSpan(clickableSpan, style.length()-2, style.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //设置部分文字颜色  Color.parseColor("#0000FF")
                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.name_se));
                    style.setSpan(foregroundColorSpan, 0, style.length()-2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    //配置给TextView
                    donation_beizhu.setMovementMethod(LinkMovementMethod.getInstance());

                    donation_beizhu.setText(style);

                }else{
                    donation_beizhu.setEnabled(true);
                }
            }
        });
    }

    // 获取从 二维码 跳转过来的json传
    private void get_QR_Moth(Intent intent,String type) {
        String json = intent.getStringExtra("QR_Bean");
        QR_Bean qr_bean = new Gson().fromJson(json, QR_Bean.class);

        if(!qr_bean.getUserCode().equals("")){
            // 会员号 赋值  并禁止输入
            donation_huiyuan_word.setText(qr_bean.getUserCode());
            setTextView(donation_huiyuan_word,false);
        }

        if(qr_bean.getGoodsInfo()==null){
            donation_shuliang.setText("");

        }else{
            donation_cangpin_wrod.setText(qr_bean.getGoodsInfo().getGoodsCode());  // 藏品代码

            if(qr_bean.getGoodsInfo().getGoodsName().length()>15){
                String name = qr_bean.getGoodsInfo().getGoodsName().substring(0, 15);
                donation_name.setText(name+"...");      // 藏品名称
            }else{
                donation_name.setText(qr_bean.getGoodsInfo().getGoodsName());
            }

            HashMap<String, String> map = new HashMap<>();
            map.put("goodsId",qr_bean.getGoodsInfo().getGoodsId()+"");
            getShouXuFei(map,donation_shuliang);  // 根据藏品id获取手续费  以及最大可用数量

            donation_shuliang.setText(qr_bean.getGoodsInfo().getGoodsNum());  // 设置转赠数量
            donation_shuliang.setSelection(qr_bean.getGoodsInfo().getGoodsNum().length());

            if(type.equals("2")){
                // 数量的失焦
                setTextView(donation_shuliang,false);
            }
            // 业务密码的获取焦点
            setTextView(donation_yewu_pass,true);
            donation_yewu_pass.setText("");

            donation_beizhu.setText("添加备注");
            beizhu="";
            donation_beizhu.setEnabled(true);

            goodsId= qr_bean.getGoodsInfo().getGoodsId()+"";
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //注册onActivityResult
        QRCodeManager.getInstance().with(this).onActivityResult(requestCode, resultCode, data);

        if(requestCode==DashApplication.ZHUAN_TO_CANGKU_req&&resultCode==DashApplication.ZHUAN_TO_CANGKU_res){
            String type = data.getStringExtra("type");
            get_QR_Moth(data, type);
        }

        if(requestCode==500){
            finish();
        }

    }

    // 设置textview 是否可点击
    private void setTextView(TextView textView,boolean focusable) {
        textView.setFocusable(focusable);
        textView.setFocusableInTouchMode(focusable); // user touches widget on phone with touch screen
        textView.setClickable(focusable);
    }

//    // 输入框获取焦点的时候获取我的持仓
//    private void getFirstConect(final HashMap<String, String> map) {
//        myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
//            @Override
//            public void chenggong(String json) {
//                try {
//                    JSONObject jsonObject = new JSONObject(json);
//                    String code = (String) jsonObject.get("code");
//                    if(code.equals("2000")){
//                        SharedPreferences.Editor edit = DashApplication.edit;
//                        edit.putString(SP_String.CHICANG,json).commit();
////                        LogUtils.i("jiba","==="+json);
//                    }else{
//                        MyUtils.setToast((String) jsonObject.get("message"));
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void shibai(String ss) {
//                MyUtils.setToast("获取我的持仓=="+ss);
//            }
//        });
//    }

    @Override
    protected void onResume() {
        super.onResume();

        donation_beizhu.setText("添加备注");
        beizhu="";
        donation_beizhu.setEnabled(true);

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(Donation_Activity.this, SP_String.JIAZAI);

        // 获取用户信息  获取资金余额 以及获取欠款信息
        myPresenter.getPreContent(APIs.findPersonalUserAccount, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        PersonalUserAccount_Bean userBean = new Gson().fromJson(json, PersonalUserAccount_Bean.class);
                        qianKuan=userBean.getData().getAccountInfo().getArrearage();
                        keYong=userBean.getData().getAccountInfo().getDesirableFunds();
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
            }
        });
//        setDonation_Pop_JieKou(new Donation_Pop_JieKou() {
//            @Override
//            public void chuan() {
//                search_pop_view.setSearch_Pop_JieKou(new Search_Pop_View.Search_Pop_JieKou() {
//                    @Override
//                    public void chuan(List<WarehouseBean.DataBean.ListBean> list,int postion) {
////                        MyUtils.setToast("请做回显操作。。。");
//                        // 模拟数据
//                        donation_cangpin_wrod.setText(list.get(postion).getGoodsCode());
//                        donation_cangpin_wrod.setSelection(list.get(postion).getGoodsCode().length());
//                        donation_name.setText(list.get(postion).getGoodsName());
//
//                        // 价格会从请求的数据来获取
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put("goodsId",list.get(postion).getGoodsId()+"");
//                        getShouXuFei(map,donation_danjia,donation_zuidae);  // 根据藏品id获取手续费
//
//                        goodsId=list.get(postion).getGoodsId()+"";  // 为藏品id赋值
//                    }
//                });
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        donation_title = (RelativeLayout) findViewById(R.id.donation_title);
        donation_Fan = (RelativeLayout) findViewById(R.id.donation_Fan);
        donation_huiyuan_word = (EditText) findViewById(R.id.donation_huiyuan_word);
        donation_saoyisao = (ImageView) findViewById(R.id.donation_saoyisao);
        donation_cangpin_wrod = (EditText) findViewById(R.id.donation_cangpin_wrod);
        donation_name = (EditText) findViewById(R.id.donation_name);
        donation_shuliang = (EditText) findViewById(R.id.donation_shuliang);
//        donation_danjia = (TextView) findViewById(R.id.donation_danjia);

        donation_yewu_pass = (EditText) findViewById(R.id.donation_yewu_pass);

        donation_beizhu = (TextView) findViewById(R.id.donation_beizhu);

//        donation_zuidae = (TextView) findViewById(R.id.donation_zuidae);
        donation_tijiao = (Button) findViewById(R.id.donation_tijiao);
        donation_weitanchuan = (LinearLayout) findViewById(R.id.donation_weitanchuan);
        donation_weitanchuan_img = (ImageView) findViewById(R.id.donation_weitanchuan_img);
        donation_weitanchuan_text = (TextView) findViewById(R.id.donation_weitanchuan_text);
        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取控件的高度
        donation_cangpin_wrod.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                donation_cangpin_wrod.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                measuredWidth = donation_cangpin_wrod.getMeasuredWidth();
            }
        });

        //通过设置监听来获取 微弹窗 控件的高度
        donation_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                donation_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = donation_weitanchuan.getMeasuredHeight();
            }
        });


    }

    private void submit() {
        // validate

        String cword = donation_cangpin_wrod.getText().toString().trim();
        if (TextUtils.isEmpty(cword)) {
            Toast.makeText(this, "请选择藏品", Toast.LENGTH_SHORT).show();
            return;
        }

        final String name = donation_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请选择藏品", Toast.LENGTH_SHORT).show();
            return;
        }

        final String word = donation_huiyuan_word.getText().toString().trim();
        if (TextUtils.isEmpty(word)) {
            Toast.makeText(this, "请输入对方交易会员代码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(word.length()!=12){
            MyUtils.setToast("您输入的对方交易会员代码不正确");
            return;
        }
        oppositeUserCode=word;  // 为对方账号赋值

        final String shuliang = donation_shuliang.getText().toString().trim();
        if (TextUtils.isEmpty(shuliang)||Integer.valueOf(shuliang)<=0) {
            Toast.makeText(this, "转让数量不能为空且不能小于0", Toast.LENGTH_SHORT).show();
            return;
        }

        if(shuliang.length()>=10|| Integer.valueOf(shuliang) > BaseMVPActivity.zhuidashuliang){
            MyUtils.setToast("您已超出最大转让数量");
            return;
        }

        amount=shuliang;   // 为转赠数量赋值

        String yewu_pass = donation_yewu_pass.getText().toString().trim();
        if (TextUtils.isEmpty(yewu_pass)) {
            Toast.makeText(this, "请输入六位业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(yewu_pass.length()!=6){
            Toast.makeText(this, "请输入六位业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        businessPwd=yewu_pass;

        // 在弹出底部框之前先校验业务密码是否正确  调接口
        HashMap<String, String> map = new HashMap<>();
        map.put("businessPwd",yewu_pass);
        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(Donation_Activity.this, SP_String.JIAZAI);
        myPresenter.postPreContent(APIs.verifyServicePassword, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        //设置dialog的样式
                        final TakeDonation_Dialog dialog = new TakeDonation_Dialog(Donation_Activity.this, R.style.dialog_setting);
                        MyUtils.getDiaLogDiBu(Donation_Activity.this,dialog); // 设置dialog弹出框弹出时的动画
                        dialog.setTitle("确认转让信息");
                        dialog.setName(name);
                        dialog.setWord(word);
                        dialog.setShuLiang(Integer.valueOf(shuliang));

                        dialog.setShouxuFei("￥"+DashApplication.decimalFormat.format(BaseMVPActivity.shouxufei));
                        dialog.setQianKuan("￥"+DashApplication.decimalFormat.format(qianKuan));
                        final double zongjia = BaseMVPActivity.shouxufei + qianKuan;  // 计算总价
                        dialog.setZongJia("￥"+DashApplication.decimalFormat.format(zongjia));
                        dialog.setTakeDonation_Dialog_JieKou(new TakeDonation_Dialog.TakeDonation_Dialog_JieKou() {
                            @Override
                            public void QuanRen() { // 点击确认
//                MyUtils.setToast("正在请求网络。。。");
                                dialog.dismiss();
                                //设置dialog的样式
                                final TakeDonation_ZF_Dialog zfdialog = new TakeDonation_ZF_Dialog(Donation_Activity.this, R.style.dialog_setting);
                                MyUtils.getPuTongDiaLog(Donation_Activity.this,zfdialog);
                                zfdialog.setZongjia("￥"+DashApplication.decimalFormat.format(zongjia));
                                if(zongjia>keYong){
                                    String text="账户余额不足（￥"+DashApplication.decimalFormat.format(keYong)+"）";
                                    zfdialog.setKeYong_Anniu(false,text);
                                }else{
                                    if(zongjia==0){  // 如果总价==0的话 将支付宝的按钮屏蔽
                                        zfdialog.setZhifuBao_anniu();
                                    }
                                    String text="账户余额（￥"+DashApplication.decimalFormat.format(keYong)+"）";
                                    zfdialog.setKeYong_Anniu(true,text);
                                }

                                zfdialog.setTakeDonation_ZF_Dialog_JieKou(new TakeDonation_ZF_Dialog.TakeDonation_ZF_Dialog_JieKou() {
                                    @Override
                                    public void queren(int type) {
                                        if(type==0){  // 选择余额
                                            HashMap<String, String> map = new HashMap<>();
                                            map.put("goodsId",goodsId);
                                            map.put("amount",amount);
                                            map.put("oppositeUserCode",oppositeUserCode);
                                            map.put("businessPwd",businessPwd);
                                            map.put("remarks",beizhu);
                                            //  进度框
                                            final ProgressDialog aniDialog = MyUtils.getProgressDialog(Donation_Activity.this, SP_String.JIAZAI);

                                            getDonation_JieKou(map, aniDialog);  // 执行转赠接口
                                        }else{
//                            MyUtils.setToast("选择了支付宝，暂无接口...");
                                            /**
                                             *  先充值 后转赠
                                             */
                                            Map<String, String> map = new HashMap<>();
                                            map.put("amount",DashApplication.decimalFormat.format(zongjia));  // 上传总价
                                            map.put("paymentPlatform","1");  // 选择支付宝
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

//                                                                    //正式环境不需要下面这行代码
//                                                                    EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
                                                                    // 构造PayTask 对象
                                                                    PayTask alipay = new PayTask(Donation_Activity.this);
                                                                    // 调用支付接口，获取支付结果
                                                                    Map<String, String> result = alipay.payV2(orderStr, true);
                                                                    Message msg = new Message();
                                                                    msg.what = 0;
                                                                    msg.obj = result;
                                                                    mHandler.sendMessage(msg);
                                                                }
                                                            }).start();


                                                        }else{
                                                            MyUtils.setObjectAnimator(donation_weitanchuan,
                                                                    donation_weitanchuan_img,
                                                                    donation_weitanchuan_text,
                                                                    weitanchuan_height,
                                                                    false, (String) jsonObject.get("message"));
                                                        }

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                @Override
                                                public void shibai(String ss) {
                                                    MyUtils.setObjectAnimator(donation_weitanchuan,
                                                            donation_weitanchuan_img,
                                                            donation_weitanchuan_text,
                                                            weitanchuan_height,
                                                            false, ss);
                                                }
                                            });

                                        }
                                    }
                                });

                            }

                            @Override
                            public void QuXiao() {// 点击取消
                                dialog.dismiss();
                            }
                        });
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
            }
        });



    }

    // 执行转赠接口
    private void getDonation_JieKou(HashMap<String, String> map, final ProgressDialog aniDialog) {
        myPresenter.postPreContent(APIs.getUserZhuanzeng, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){

                        MyUtils.setObjectAnimator_anquan(donation_weitanchuan,
                                donation_weitanchuan_img,
                                donation_weitanchuan_text,
                                weitanchuan_height,
                                true,"转让成功");
                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                goodsId="";
                                amount="";
                                oppositeUserCode="";
                                businessPwd="";
                                donation_huiyuan_word.setText("");
                                donation_cangpin_wrod.setText("");
                                donation_name.setText("");
                                donation_shuliang.setText("");
                                donation_shuliang.setHint("最大可转让数量为:0");
                                donation_yewu_pass.setText("");
                                donation_beizhu.setText("添加备注");
                                setTextView(donation_huiyuan_word,true);
                                setTextView(donation_shuliang,true);
                                setTextView(donation_yewu_pass,true);
                            }
                        });
                    }else{
                        MyUtils.setObjectAnimator(donation_weitanchuan,
                                donation_weitanchuan_img,
                                donation_weitanchuan_text,
                                weitanchuan_height,
                                false, (String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setObjectAnimator(donation_weitanchuan,
                        donation_weitanchuan_img,
                        donation_weitanchuan_text,
                        weitanchuan_height,
                        false,ss);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }

    /**
     *  这个接口回调用于输入藏品代码的pop的弹出
     *
     */
    public interface Donation_Pop_JieKou{
        void chuan();
    }

    Donation_Pop_JieKou donation_Pop_JieKou;

    public void setDonation_Pop_JieKou(Donation_Pop_JieKou donation_Pop_JieKou) {
        this.donation_Pop_JieKou = donation_Pop_JieKou;
    }
}
