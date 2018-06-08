package com.ifeng_tech.treasuryyitong.ui.my;

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

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *  找回密码
 */
public class Forget_Activity extends BaseMVPActivity<Forget_Activity,MyPresenter<Forget_Activity>>  {

    private RelativeLayout forget_Fan;
    private EditText forget_new;
    private EditText forget_queren;
    private ImageView forget_weitanchuan_img;
    private TextView forget_weitanchuan_text;
    private LinearLayout forget_weitanchuan;
    private Button forget_btn;
    private int weitanchuan_height;
    private String mobile;
    private String smsCode;

    @Override
    public MyPresenter<Forget_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_);
        initView();

        forget_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        forget_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        smsCode = intent.getStringExtra("smsCode");
    }

    private void initView() {
        forget_Fan = (RelativeLayout) findViewById(R.id.forget_Fan);
        forget_new = (EditText) findViewById(R.id.forget_new);
        forget_queren = (EditText) findViewById(R.id.forget_queren);
        forget_weitanchuan_img = (ImageView) findViewById(R.id.forget_weitanchuan_img);
        forget_weitanchuan_text = (TextView) findViewById(R.id.forget_weitanchuan_text);
        forget_weitanchuan = (LinearLayout) findViewById(R.id.forget_weitanchuan);
        forget_btn = (Button) findViewById(R.id.forget_btn);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        forget_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                forget_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = forget_weitanchuan.getMeasuredHeight();
            }
        });

    }


    private void submit() {
        // validate
        String news = forget_new.getText().toString().trim();
        if (TextUtils.isEmpty(news)) {
            Toast.makeText(this, "新登录密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String queren = forget_queren.getText().toString().trim();
        if (TextUtils.isEmpty(queren)) {
            Toast.makeText(this, "确认新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!queren.equals(news)){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

//        MyUtils.setToast("请求网络。。。");

        HashMap<String, String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("password","");
        map.put("smsCode",smsCode);

        myPresenter.postPreContent(APIs.modifyPasswordByMobile, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    String message = (String) jsonObject.get("message");
                    if(code.equals("2000")){
                        MyUtils.setObjectAnimator_anquan(forget_weitanchuan,
                                forget_weitanchuan_img,
                                forget_weitanchuan_text,
                                weitanchuan_height,
                                true, "找回密码成功,2秒跳回...");

                        // 微弹窗消失后的接口回调
                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                setResult(DashApplication.RETRIEVE_TO_FORGET_res);
                                finish();
                            }
                        });
                    }else{
                        MyUtils.setObjectAnimator(forget_weitanchuan,
                                forget_weitanchuan_img,
                                forget_weitanchuan_text,
                                weitanchuan_height,
                                false, message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setObjectAnimator(forget_weitanchuan,
                        forget_weitanchuan_img,
                        forget_weitanchuan_text,
                        weitanchuan_height,
                        false, ss);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
