package com.ifeng_tech.treasuryyitong.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.login.LoginBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.my.Retrieve_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.hua_dong.CustomSlideToUnlockView;

import java.util.HashMap;

import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

/**
 * 程序入口 登录页面
 */
public class MainActivity extends BaseMVPActivity<MainActivity, MyPresenter<MainActivity>> {


    private EditText main_name;
    private EditText main_pass;
    private CustomSlideToUnlockView main_to_unlock;
    private TextView main_regist;
    private TextView main_forgetpwd;
    private ImageView main_weitanchuan_img;
    private TextView main_weitanchuan_text;
    private LinearLayout main_weitanchuan;
    private int weitanchuan_height;

    @Override
    public MyPresenter<MainActivity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sp = getSharedPreferences("ifeng", MODE_PRIVATE);
        boolean aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);
        if(aBoolean){
            Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }

        // 点击跳到注册
        main_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login_Register_Activity.class);
                startActivityForResult(intent, DashApplication.LOGIN_TO_REGISTER_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 点击跳到忘记密码
        main_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Retrieve_Activity.class);
                intent.putExtra("type",1);  // 用于手机验证页面的下次跳转识别码  1==到找回密码
                intent.putExtra("select",1);   //  用于手机验证页面中的手机号输入框的隐藏/显示  1==有输入框
                startActivityForResult(intent,DashApplication.LOGIN_TO_RETRIEVE_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 滑动解锁的监听
        main_to_unlock.setmCallBack(new CustomSlideToUnlockView.CallBack() {
            @Override
            public void onSlide(int distance) {
//                LogUtils.i("jiba","distance==="+distance);
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(main_pass.getWindowToken(), 0);
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
            main_name.setText(data.getStringExtra("name"));
            main_pass.setText(data.getStringExtra("pass"));
            main_name.setSelection(data.getStringExtra("name").length());
        }
        if(requestCode == DashApplication.LOGIN_TO_RETRIEVE_req && resultCode == DashApplication.LOGIN_TO_RETRIEVE_res){
            String shouji = sp.getString(SP_String.SHOUJI, "");
            String pass = sp.getString(SP_String.PASS, "");
            main_name.setText(shouji);
            main_pass.setText(pass);
            main_name.setSelection(shouji.length());
        }
    }


    private void initView() {
        main_name = (EditText) findViewById(R.id.main_name);
        main_pass = (EditText) findViewById(R.id.main_pass);
        main_to_unlock = (CustomSlideToUnlockView) findViewById(R.id.main_to_unlock);
        main_regist = (TextView) findViewById(R.id.main_regist);
        main_forgetpwd = (TextView) findViewById(R.id.main_forgetpwd);
        main_weitanchuan_img = (ImageView) findViewById(R.id.main_weitanchuan_img);
        main_weitanchuan_text = (TextView) findViewById(R.id.main_weitanchuan_text);
        main_weitanchuan = (LinearLayout) findViewById(R.id.main_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        main_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                main_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = main_weitanchuan.getMeasuredHeight();
            }
        });
    }

    private void submit() {
        // validate
        final String name = main_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isPhoneNumber(name)==false){
            Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
            main_to_unlock.resetView();   // 将滑动条重置
            return;
        }

        final String pass = main_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (MyUtils.isPassWord(pass)==false) {
            Toast.makeText(this, SP_String.IS_PASS, Toast.LENGTH_SHORT).show();
            main_to_unlock.resetView();  // 将滑动条重置
            return;
        }
        // TODO validate success, do something
        HashMap<String, String> map = new HashMap<>();
        map.put("userName",name);
        map.put("password",pass);
        map.put("loginType","0");
        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, "正在登录...");
        myPresenter.postPreContent(APIs.login, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
//                LogUtils.i("wc","===="+json);
                aniDialog.dismiss();
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(json, LoginBean.class);
                if(loginBean.getCode().equals("2000")){

                    String uid = sp.getString(SP_String.UID, "");
                    setTagAndAlias(uid);  // 注册极光的别名
                    DashApplication.edit
                            .putString(SP_String.SHOUJI, name)
                            .putString(SP_String.PASS,pass)
                            .putBoolean(SP_String.ISLOGIN,true)
                            .putString(SP_String.UID,loginBean.getData().getUser().getId()+"")
                            .putString(SP_String.USERCODE,loginBean.getData().getUser().getUserCode()+"")
                            .commit();

                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                    finish();

                }else{
                    main_to_unlock.resetView();  // 将滑动条重置
                    MyUtils.setObjectAnimator(main_weitanchuan,
                            main_weitanchuan_img,
                            main_weitanchuan_text,
                            weitanchuan_height,
                            false, loginBean.getMessage());
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                main_to_unlock.resetView();  // 将滑动条重置
                MyUtils.setObjectAnimator(main_weitanchuan,
                        main_weitanchuan_img,
                        main_weitanchuan_text,
                        weitanchuan_height,
                        false, "登录失败!");
            }
        });

    }

}
