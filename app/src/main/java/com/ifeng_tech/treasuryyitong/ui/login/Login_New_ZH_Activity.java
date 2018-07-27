package com.ifeng_tech.treasuryyitong.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.login.LoginBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import java.util.HashMap;

/**
 * 登录页面
 * <p>
 * 账号登录页面
 */
public class Login_New_ZH_Activity extends BaseMVPActivity<Login_New_ZH_Activity, MyPresenter<Login_New_ZH_Activity>>  {

    private EditText login_new_zh_name;
    private EditText login_new_zh_pass;
    private Button login_new_zh_btn;
    private TextView login_new_zh_shouji;
    private TextView login_new_zh_weitanchuan_text;
    private LinearLayout login_new_zh_weitanchuan;
    private int weitanchuan_height;

    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            submit();
        }
    };

    @Override
    public MyPresenter<Login_New_ZH_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__new__zh_);
        initView();

        // 点击切换账号手机号登录
        login_new_zh_shouji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_New_ZH_Activity.this, Login_New_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
                finish();
            }
        });

        // 点击确认按钮
        login_new_zh_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(login_new_zh_pass.getWindowToken(), 0);
                h.sendEmptyMessageDelayed(0,200);  // 延迟200毫秒 让键盘消失与界面重绘留有时间
            }
        });

    }

    private void initView() {
        login_new_zh_name = (EditText) findViewById(R.id.login_new_zh_name);
        login_new_zh_pass = (EditText) findViewById(R.id.login_new_zh_pass);
        login_new_zh_btn = (Button) findViewById(R.id.login_new_zh_btn);
        login_new_zh_shouji = (TextView) findViewById(R.id.login_new_zh_shouji);
        login_new_zh_weitanchuan_text = (TextView) findViewById(R.id.login_new_zh_weitanchuan_text);
        login_new_zh_weitanchuan = (LinearLayout) findViewById(R.id.login_new_zh_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        login_new_zh_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                login_new_zh_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = login_new_zh_weitanchuan.getMeasuredHeight();
            }
        });

        // edittext默认不显示软键盘，只有edittext被点击时，软键盘才弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }


    private void submit() {
        // validate
        final String name = login_new_zh_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            MyUtils.setObjectAnimator_login(login_new_zh_weitanchuan,login_new_zh_weitanchuan_text,weitanchuan_height,"请输入手机号码");
            return;
        }
        if (MyUtils.isPhoneNumber(name) == false) {
            MyUtils.setObjectAnimator_login(login_new_zh_weitanchuan,login_new_zh_weitanchuan_text,weitanchuan_height,"手机号码输入错误");
            return;
        }

        final String pass = login_new_zh_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            MyUtils.setObjectAnimator_login(login_new_zh_weitanchuan,login_new_zh_weitanchuan_text,weitanchuan_height,"请输入密码");
            return;
        }

        if (MyUtils.isPassWord(pass)==false) {
            MyUtils.setObjectAnimator_login(login_new_zh_weitanchuan,login_new_zh_weitanchuan_text,weitanchuan_height,SP_String.IS_PASS);
            return;
        }
        // TODO validate success, do something

        HashMap<String, String> map = new HashMap<>();
        map.put("userName",name);
        map.put("password",pass);
        map.put("loginType","0");
        map.put("token",DashApplication.android);
        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, "正在登录...");

        myPresenter.postPreContent(APIs.login, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {

                aniDialog.dismiss();
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                if(loginBean.getCode().equals("2000")){

                    DashApplication.edit
                            .putString(SP_String.SHOUJI, name)
                            .putString(SP_String.PASS,pass)
                            .putString(SP_String.TOKEN,DashApplication.android)
                            .putBoolean(SP_String.ISLOGIN,true)
                            .putString(SP_String.UID,loginBean.getData().getUser().getId()+"")
                            .putString(SP_String.USERCODE,loginBean.getData().getUser().getUserCode()+"")
                            .commit();

                    setTagAndAlias(loginBean.getData().getUser().getId()+"");  // 注册极光的别名

                    Intent intent = new Intent(Login_New_ZH_Activity.this, HomePageActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                    finish();

                }else{
                    MyUtils.setObjectAnimator_login(login_new_zh_weitanchuan,login_new_zh_weitanchuan_text,weitanchuan_height,loginBean.getMessage());
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setObjectAnimator_login(login_new_zh_weitanchuan,login_new_zh_weitanchuan_text,weitanchuan_height,ss);
            }
        });


    }
}
