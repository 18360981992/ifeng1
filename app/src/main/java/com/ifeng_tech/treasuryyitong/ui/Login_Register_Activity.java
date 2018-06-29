package com.ifeng_tech.treasuryyitong.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.login.RegisterBean;
import com.ifeng_tech.treasuryyitong.bean.login.SmsCodeBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.interfaces.MyJieKou;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 注册
 */
public class Login_Register_Activity extends BaseMVPActivity<Login_Register_Activity, MyPresenter<Login_Register_Activity>>  {

    private RelativeLayout register_Fan;
    private EditText register_name;
    private EditText register_duan;
    private TextView register_duan_btn;
    private EditText register_pass;
    private EditText register_zaici_pass;
    private ImageView register_fuxuan;
    private TextView register_yuedu_text;
    private Button register_btn;

    boolean fuxuan = false;
    private ImageView login_register_weitanchuan_img;
    private TextView login_register_weitanchuan_text;
    private LinearLayout login_register_weitanchuan;
    private int weitanchuan_height;

    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (time == 0) {
                time = 60;
                register_duan_btn.setText("获取验证码");
                register_duan_btn.setEnabled(true);
                register_duan_btn.setTextColor(getResources().getColor(R.color.zhuse));
            } else {
                register_duan_btn.setText("重新发送" + time + "(s)");
                h.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };
    private EditText register_tu_yan;
    private ImageView register_tu_yan_img;


    @Override
    public MyPresenter<Login_Register_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        initView();

        register_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 复选框的点击
        register_fuxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fuxuan) {
                    fuxuan = false;
                    register_fuxuan.setImageResource(R.drawable.zhuce_hui);
                } else {
                    fuxuan = true;
                    register_fuxuan.setImageResource(R.drawable.zhuce_lan);
                }
            }
        });
        // 短信验证码的点击
        register_duan_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                String tuyan = register_tu_yan.getText().toString().trim();
                if (TextUtils.isEmpty(tuyan)) {
                    MyUtils.setToast("请输入图形验证码");
                    return;
                }

                final String name = register_name.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    MyUtils.setToast("请输入手机号码");
                    return;
                }

                if(MyUtils.isPhoneNumber(name)==false){
                    MyUtils.setToast("请输入正确的手机号");
                    return;
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("verifyCode",tuyan);
                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Login_Register_Activity.this, SP_String.JIAZAI);

                myPresenter.postPreContent(APIs.verifyCode, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                register_duan_btn.setText("重新发送" + time + "(s)");
                                register_duan_btn.setEnabled(false);
                                register_duan_btn.setTextColor(getResources().getColor(R.color.name_se));
                                h.sendEmptyMessageDelayed(0, 1000);

//                              MyUtils.setToast("请求网络。。。");
                                HashMap<String, String> map = new HashMap<>();
                                map.put("mobile",name);
                                map.put("codeType",""+1);
                                myPresenter.postPreContent(APIs.getSmsCode, map, new MyInterfaces() {
                                    @Override
                                    public void chenggong(String json) {
                                        SmsCodeBean smCodeBean = new Gson().fromJson(json, SmsCodeBean.class);
                                        if(smCodeBean.getCode().equals("2000")){
                                            MyUtils.setToast("短信发送成功");
                                        }else{
                                            MyUtils.setToast(smCodeBean.getMessage()+"");
                                        }
                                    }

                                    @Override
                                    public void shibai(String ss) {
                                        MyUtils.setToast("短信发送失败");
                                    }
                                });
                            }else{
                                MyUtils.setObjectAnimator(login_register_weitanchuan,
                                        login_register_weitanchuan_img,
                                        login_register_weitanchuan_text,
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
                        MyUtils.setObjectAnimator(login_register_weitanchuan,
                                login_register_weitanchuan_img,
                                login_register_weitanchuan_text,
                                weitanchuan_height,
                                false, ss);
                    }
                });

            }
        });


        register_tu_yan_img.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                //                MyUtils.setToast("点击了图形验证。。。");
                myPresenter.getPro_TuXingYanZheng( APIs.newImageCode, new MyJieKou() {
                    @Override
                    public void chenggong(Bitmap bitmap) {
                        if(bitmap!=null){
                            register_tu_yan_img.setImageBitmap(bitmap);
                        }
                    }
                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast("图形验证码获取失败！");
                    }
                });
            }
        });


        // 点击隐私政策
        register_yuedu_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Register_Activity.this, Conceal_Activity.class);
                startActivityForResult(intent,DashApplication.REGISTER_TO_CONCEAL_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 初始化图形验证码
        myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
            @Override
            public void chenggong(Bitmap bitmap) {
                if(bitmap!=null){
                    register_tu_yan_img.setImageBitmap(bitmap);
                }
            }
            @Override
            public void shibai(String ss) {
                MyUtils.setToast("图形验证码获取失败！");
            }
        });


    }

    private void initView() {
        register_Fan = (RelativeLayout) findViewById(R.id.register_Fan);
        register_name = (EditText) findViewById(R.id.register_name);
        register_tu_yan = (EditText) findViewById(R.id.register_tu_yan);
        register_tu_yan_img = (ImageView) findViewById(R.id.register_tu_yan_img);
        register_duan = (EditText) findViewById(R.id.register_duan);
        register_duan_btn = (TextView) findViewById(R.id.register_duan_btn);
        register_pass = (EditText) findViewById(R.id.register_pass);
        register_zaici_pass = (EditText) findViewById(R.id.register_zaici_pass);
        register_fuxuan = (ImageView) findViewById(R.id.register_fuxuan);
        register_yuedu_text = (TextView) findViewById(R.id.register_yuedu_text);
        register_btn = (Button) findViewById(R.id.register_btn);
        login_register_weitanchuan_img = (ImageView) findViewById(R.id.login_register_weitanchuan_img);
        login_register_weitanchuan_text = (TextView) findViewById(R.id.login_register_weitanchuan_text);
        login_register_weitanchuan = (LinearLayout) findViewById(R.id.login_register_weitanchuan);

        // 点击注册按钮
        register_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(register_zaici_pass.getWindowToken(), 0);

                submit();
            }
        });

        SoftHideKeyBoardUtil.assistActivity(this);

        register_fuxuan.setImageResource(R.drawable.zhuce_hui);  // 初始值

        //通过设置监听来获取 微弹窗 控件的高度
        login_register_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                login_register_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = login_register_weitanchuan.getMeasuredHeight();
            }
        });
    }


    private void submit() {
        // validate
        final String name = register_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isPhoneNumber(name)==false){
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String tuyan = register_tu_yan.getText().toString().trim();
        if (TextUtils.isEmpty(tuyan)) {
            Toast.makeText(this, "请输入图形验证码", Toast.LENGTH_SHORT).show();
            return;
        }


        String duan = register_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        final String pass = register_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isPassWord(pass)==false){
            Toast.makeText(this, SP_String.IS_PASS, Toast.LENGTH_SHORT).show();
            return;
        }

        String zaipass = register_zaici_pass.getText().toString().trim();
        if (TextUtils.isEmpty(zaipass)) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!pass.equals(zaipass)){
            Toast.makeText(this, "两次密码输入不一致!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fuxuan == false) {
            Toast.makeText(this, "请勾选条约", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something

//        MyUtils.setToast("请求网络。。。");

        HashMap<String, String> map = new HashMap<>();
        map.put("mobile",name);
        map.put("smsCode",duan);
        map.put("password",pass);
        map.put("rePassword",zaipass);

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.register, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                RegisterBean registerBean = new Gson().fromJson(json, RegisterBean.class);
                if(registerBean.getCode().equals("2000")){
                    Intent intent = getIntent();
                    intent.putExtra("name",name);
                    intent.putExtra("pass",pass);
                    setResult(DashApplication.LOGIN_TO_REGISTER_res,intent);
                    finish();
                }else{
                    MyUtils.setObjectAnimator(login_register_weitanchuan,
                            login_register_weitanchuan_img,
                            login_register_weitanchuan_text,
                            weitanchuan_height,
                            false, registerBean.getMessage());
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setObjectAnimator(login_register_weitanchuan,
                        login_register_weitanchuan_img,
                        login_register_weitanchuan_text,
                        weitanchuan_height,
                        false, "注册失败!");
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DashApplication.REGISTER_TO_CONCEAL_req&&resultCode==DashApplication.REGISTER_TO_CONCEAL_res){
            fuxuan = true;
            register_fuxuan.setImageResource(R.drawable.zhuce_lan);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        fuxuan=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeMessages(0);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
