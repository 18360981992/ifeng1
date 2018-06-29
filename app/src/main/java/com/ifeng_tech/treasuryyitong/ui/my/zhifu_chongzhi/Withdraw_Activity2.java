package com.ifeng_tech.treasuryyitong.ui.my.zhifu_chongzhi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.tixian_congzhi.WithdrawalApplication_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 提现确认页面
 */
public class Withdraw_Activity2 extends BaseMVPActivity<Withdraw_Activity2, MyPresenter<Withdraw_Activity2>>{

    private RelativeLayout withdraw_Fan2;
    private EditText withdraw_zhanghao2;
    private EditText withdraw_yewu_pass;
    private Button withdraw_queren;
    private Button withdraw_quxiao;
    private ImageView withdraw_weitanchuan_img;
    private TextView withdraw_weitanchuan_text;
    private LinearLayout withdraw_weitanchuan;
    private int weitanchuan_height;
    private String paymentPlatform;
    private String withdrawalAmount;

    @Override
    public MyPresenter<Withdraw_Activity2> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_2);
        initView();

        Intent intent = getIntent();
        paymentPlatform = intent.getStringExtra("paymentPlatform");  // 支付平台
        withdrawalAmount = intent.getStringExtra("withdrawalAmount");  // 金额

        withdraw_Fan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        withdraw_queren.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 点击 强制关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(withdraw_yewu_pass.getWindowToken(), 0);
                submit();
            }
        });

        withdraw_quxiao.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                setResult(DashApplication.WITHDRAW_TO_WITHDRAW2_res);
                finish();
            }
        });

    }

    private void initView() {
        withdraw_Fan2 = (RelativeLayout) findViewById(R.id.withdraw_Fan2);
        withdraw_zhanghao2 = (EditText) findViewById(R.id.withdraw_zhanghao2);
        withdraw_yewu_pass = (EditText) findViewById(R.id.withdraw_yewu_pass);
        withdraw_queren = (Button) findViewById(R.id.withdraw_queren);
        withdraw_quxiao = (Button) findViewById(R.id.withdraw_quxiao);
        withdraw_weitanchuan_img = (ImageView) findViewById(R.id.withdraw_weitanchuan_img);
        withdraw_weitanchuan_text = (TextView) findViewById(R.id.withdraw_weitanchuan_text);
        withdraw_weitanchuan = (LinearLayout) findViewById(R.id.withdraw_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        withdraw_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                withdraw_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = withdraw_weitanchuan.getMeasuredHeight();
            }
        });

    }


    private void submit() {
        // validate
        String zhanghao2 = withdraw_zhanghao2.getText().toString().trim();
        if (TextUtils.isEmpty(zhanghao2)) {
            Toast.makeText(this, " 请输入提现账户", Toast.LENGTH_SHORT).show();
            return;
        }

        String pass = withdraw_yewu_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(pass.length()!=6){
            MyUtils.setToast("请输入6位数的业务密码");
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("withdrawalAmount",withdrawalAmount);  // 钱
        map.put("paymentPlatform",paymentPlatform);  // 支付品台
        map.put("paymentAccountInfo",zhanghao2);  // 账户
        map.put("businessPwd",pass);  // 业务密码


        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(Withdraw_Activity2.this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.withdrawalApplication, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){

                        WithdrawalApplication_Bean withdrawalApplication_bean = new Gson().fromJson(json, WithdrawalApplication_Bean.class);
                        MyUtils.setObjectAnimator_anquan(withdraw_weitanchuan,
                                withdraw_weitanchuan_img,
                                withdraw_weitanchuan_text,
                                weitanchuan_height,
                                true,"提现申请成功!");

                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                setResult(DashApplication.WITHDRAW_TO_WITHDRAW2_res);
                                finish();
                            }
                        });
                    }else{
                        MyUtils.setObjectAnimator(withdraw_weitanchuan,
                                withdraw_weitanchuan_img,
                                withdraw_weitanchuan_text,
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
                MyUtils.setObjectAnimator(withdraw_weitanchuan,
                        withdraw_weitanchuan_img,
                        withdraw_weitanchuan_text,
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
}
