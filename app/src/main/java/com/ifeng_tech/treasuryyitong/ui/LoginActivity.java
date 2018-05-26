package com.ifeng_tech.treasuryyitong.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.service.HeartbeatService;
import com.ifeng_tech.treasuryyitong.ui.my.Retrieve_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.hua_dong.CustomSlideToUnlockView;

import java.util.HashMap;

/**
 * 登录
 */
public class LoginActivity extends BaseMVPActivity<LoginActivity, MyPresenter<LoginActivity>> {


    private RelativeLayout login_Fan;
    private EditText logo_name;
    private EditText logo_pass;
    private CustomSlideToUnlockView logo_to_unlock;
    private TextView login_regist;
    private TextView login_forgetpwd;
    private ImageView login_weitanchuan_img;
    private TextView login_weitanchuan_text;
    private LinearLayout login_weitanchuan;
    private int weitanchuan_height;

    @Override
    public MyPresenter<LoginActivity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

//        // 自动登录
//        boolean isLogin = DashApplication.sp.getBoolean(SP_String.ISLOGIN, false);
//        if(isLogin){
//            LogUtils.i("jiba","isLogin==="+isLogin);
//            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
//            startActivity(intent);
//
//            startService(new Intent(LoginActivity.this, HeartbeatService.class));  // 启动心跳
//            finish();
//        }

        login_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 点击跳到注册
        login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Login_Register_Activity.class);
                startActivityForResult(intent, DashApplication.LOGIN_TO_REGISTER_req);

            }
        });

        // 点击跳到忘记密码
        login_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Retrieve_Activity.class);
                intent.putExtra("type",1);  // 用于手机验证页面的下次跳转识别码
                intent.putExtra("select",1);   //  用于手机验证页面中的手机号的隐藏/显示
                startActivity(intent);
            }
        });

        // 滑动解锁的监听
        logo_to_unlock.setmCallBack(new CustomSlideToUnlockView.CallBack() {
            @Override
            public void onSlide(int distance) {

            }

            @Override
            public void onUnlocked() {
//                MyUtils.setToast("请求网络，开始登陆。。。");
                submit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DashApplication.LOGIN_TO_REGISTER_req && resultCode == DashApplication.LOGIN_TO_REGISTER_res) {
//            MyUtils.setToast("注册成功后的回显。。。");
            logo_name.setText(data.getStringExtra("name"));
            logo_pass.setText(data.getStringExtra("pass"));
        }
    }

    private void initView() {
        login_Fan = (RelativeLayout) findViewById(R.id.login_Fan);
        logo_name = (EditText) findViewById(R.id.logo_name);
        logo_pass = (EditText) findViewById(R.id.logo_pass);
        logo_to_unlock = (CustomSlideToUnlockView) findViewById(R.id.logo_to_unlock);
        login_regist = (TextView) findViewById(R.id.login_regist);
        login_forgetpwd = (TextView) findViewById(R.id.login_forgetpwd);
        login_weitanchuan_img = (ImageView) findViewById(R.id.login_weitanchuan_img);
        login_weitanchuan_text = (TextView) findViewById(R.id.login_weitanchuan_text);
        login_weitanchuan = (LinearLayout) findViewById(R.id.login_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        login_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                login_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = login_weitanchuan.getMeasuredHeight();
            }
        });
    }

    private void submit() {
        // validate
        final String name = logo_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            logo_to_unlock.resetView();   // 将滑动条重置
            return;
        }

        if(MyUtils.isPhoneNumber(name)==false){
            Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
            logo_to_unlock.resetView();   // 将滑动条重置
            return;
        }

        String pass = logo_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            logo_to_unlock.resetView();  // 将滑动条重置
            return;
        }

        // TODO validate success, do something
        HashMap<String, String> map = new HashMap<>();
        map.put("userName",name);
        map.put("password",pass);
        map.put("loginType","0");


//        myPresenter.postPreContent(APIs.login, map, new MyInterfaces() {
//            @Override
//            public void chenggong(String json) {
////                LogUtils.i("wc","===="+json);
//                logo_to_unlock.setVisibility(View.GONE);
//                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
//                finish();
//
//                DashApplication.edit.putString("shouji", name)
//                        .putBoolean("isLogin",true)
//                        .putString("uid","0")
//                        .commit();
//            }
//
//            @Override
//            public void shibai(String ss) {
//                logo_to_unlock.resetView();  // 将滑动条重置
//                MyUtils.setObjectAnimator(login_weitanchuan,
//                        login_weitanchuan_img,
//                        login_weitanchuan_text,
//                        weitanchuan_height,
//                        false, "登录失败!");
//            }
//        });

        if (true) {
            logo_to_unlock.setVisibility(View.GONE);
            DashApplication.edit.putString(SP_String.SHOUJI, name)
                    .putBoolean(SP_String.ISLOGIN,true)
                    .putString(SP_String.UID,"0")
                    .putInt(SP_String.NEWS_NUM,0)
                    .commit();
            startService(new Intent(LoginActivity.this, HeartbeatService.class));  // 启动心跳

            finish();
        } else {
            logo_to_unlock.resetView();  // 将滑动条重置
            MyUtils.setObjectAnimator(login_weitanchuan,
                    login_weitanchuan_img,
                    login_weitanchuan_text,
                    weitanchuan_height,
                    false, "登录失败!");
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
