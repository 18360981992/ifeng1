package com.ifeng_tech.treasuryyitong.ui.my;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
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
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 提现确认页面
 */
public class Withdraw_Activity2 extends BaseMVPActivity<Withdraw_Activity2, MyPresenter<Withdraw_Activity2>> implements View.OnClickListener {

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


        withdraw_queren.setOnClickListener(this);
        withdraw_quxiao.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.withdraw_queren:
                submit();
                break;
            case R.id.withdraw_quxiao:
                setResult(DashApplication.WITHDRAW_TO_WITHDRAW2_res);
                finish();
                break;
        }
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
        Map<String, String> map = new HashMap<>();
        map.put("withdrawalAmount",withdrawalAmount);
        map.put("paymentPlatform",paymentPlatform);
        map.put("paymentAccountInfo",pass);

        //  进度框
        final ProgressDialog aniDialog = new ProgressDialog(Withdraw_Activity2.this);
        aniDialog.setCancelable(true);
        aniDialog.setMessage("正在登录...");
        aniDialog.show();

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
                                true,"申请成功!");

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
