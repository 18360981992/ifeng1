package com.ifeng_tech.treasuryyitong.ui.login;

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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.login.LoginNewBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.interfaces.MyJieKou;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.TakeImage_Dialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

/**
 * 登录新页面
 * <p>
 * 短息验证登录
 */
public class Login_New_Activity extends BaseMVPActivity<Login_New_Activity, MyPresenter<Login_New_Activity>> {

    private EditText login_new_name;
    private EditText login_new_duan;
    private TextView login_new_duan_btn;
    private Button login_new_btn;
    private TextView login_new_zhanghao;
    private TextView login_new_weitanchuan_text;
    private LinearLayout login_new_weitanchuan;
    private int weitanchuan_height;

    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                time--;
                if (time == 0) {
                    time = 60;
                    login_new_duan_btn.setText("获取验证码");
                    login_new_duan_btn.setEnabled(true);
                } else {
                    login_new_duan_btn.setText( time + "s");
                    h.sendEmptyMessageDelayed(0, 1000);
                }
            }else{
                submit();  // 延迟200毫秒执行登录接口 为了让键盘消失后重绘界面
            }

        }
    };
    private int codeType;

    @Override
    public MyPresenter<Login_New_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__new_);
        initView();

        sp = getSharedPreferences("ifeng", MODE_PRIVATE);
        boolean aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);
        if(aBoolean){
            String uid = DashApplication.sp.getString(SP_String.UID, "");
            setTagAndAlias(uid+"");  // 注册极光的别名

            Intent intent = new Intent(Login_New_Activity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }

        // 点击确认按钮  登录
        login_new_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(login_new_duan.getWindowToken(), 0);
                h.sendEmptyMessageDelayed(1,200);// 延迟200毫秒执行登录接口 为了让键盘消失后重绘界面

            }
        });

        // 点击切换到账户登录
        login_new_zhanghao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_New_Activity.this, Login_New_ZH_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                finish();
            }
        });

        // 点击弹出图形验证码的框
        login_new_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = login_new_name.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,"请输入手机号码");
                    return;
                }

                if (MyUtils.isPhoneNumber(name) == false) {
                    MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,"手机号码输入错误");
                    return;
                }

                // 初始化图形验证码
                myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
                    @Override
                    public void chenggong(Bitmap bitmap) {
                        if(bitmap!=null){

//                            register_tu_yan_img.setImageBitmap(bitmap);
                            // 使用自定义的dialog框
                            final TakeImage_Dialog takeCommonDialog = new TakeImage_Dialog(Login_New_Activity.this, R.style.dialog_setting);
                            MyUtils.getPuTongDiaLog(Login_New_Activity.this,takeCommonDialog);
                            takeCommonDialog.setTakeImage_Dialog_img(bitmap);
                            takeCommonDialog.setTakeImage_Dialog_JieKou(new TakeImage_Dialog.TakeImage_Dialog_JieKou() {
                                // 切换图形验证码
                                @Override
                                public void qiehuan(final ImageView takeImage_Dialog_img) {
                                    // 初始化图形验证码
                                    myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
                                        @Override
                                        public void chenggong(Bitmap bitmap) {
                                            takeImage_Dialog_img.setImageBitmap(bitmap);
                                        }

                                        @Override
                                        public void shibai(String ss) {
                                            MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,"图形验证码获取失败");
                                        }
                                    });
                                }

                                // 获取4个输入框的值 验证图形验证码
                                @Override
                                public void chuan(final String ss) {
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("verifyCode",ss);
                                    //  进度框
                                    final ProgressDialog aniDialog = MyUtils.getProgressDialog(Login_New_Activity.this, SP_String.JIAZAI);

                                    myPresenter.postPreContent(APIs.verifyCode, map, new MyInterfaces() {

                                        @Override
                                        public void chenggong(String json) {

                                            try {
                                                JSONObject jsonObject = new JSONObject(json);
                                                String code = (String) jsonObject.get("code");
                                                if(code.equals("2000")){
                                                    HashMap<String, String> map1 = new HashMap<>();
                                                    map1.put("account",name);
                                                    myPresenter.postPreContent(APIs.getUserByAccount, map1, new MyInterfaces() {
                                                        @Override
                                                        public void chenggong(String json) {
                                                            try {
                                                                JSONObject jsonObject = new JSONObject(json);
                                                                String code = (String) jsonObject.get("code");
                                                                if(code.equals("2000")){
                                                                    takeCommonDialog.dismiss();
                                                                    login_new_duan_btn.setText( time + "s");
                                                                    login_new_duan_btn.setEnabled(false);
                                                                    h.sendEmptyMessageDelayed(0, 1000);
                                                                    codeType = 2;
                                                                    HashMap<String, String> map = new HashMap<>();
                                                                    map.put("mobile",name);
                                                                    map.put("codeType",""+codeType);
                                                                    map.put("verifyCode",ss);
                                                                    getSmsCode(map, aniDialog);  // 执行获取短信验证码
                                                                }else{
                                                                    takeCommonDialog.dismiss();
                                                                    login_new_duan_btn.setText( time + "s");
                                                                    login_new_duan_btn.setEnabled(false);
                                                                    h.sendEmptyMessageDelayed(0, 1000);
                                                                    codeType = 1;
                                                                    HashMap<String, String> map = new HashMap<>();
                                                                    map.put("mobile",name);
                                                                    map.put("codeType",""+codeType);
                                                                    map.put("verifyCode",ss);
                                                                    getSmsCode(map, aniDialog); // 执行获取短信验证码
                                                                }
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }

                                                        @Override
                                                        public void shibai(String ss) {
                                                            aniDialog.dismiss();
                                                            takeCommonDialog.dismiss();
                                                            MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,ss);
                                                        }
                                                    });

                                                }else{
                                                    takeCommonDialog.dismiss();
                                                    aniDialog.dismiss();
                                                    MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,(String) jsonObject.get("message"));
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void shibai(String ss) {
                                            aniDialog.dismiss();
                                            takeCommonDialog.dismiss();
                                            MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,ss);
                                        }
                                    });

                                }
                            });

                        }
                    }
                    @Override
                    public void shibai(String ss) {
                        MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,ss);
                    }
                });
            }
        });
    }

    // 执行获取短信验证码
    private void getSmsCode(HashMap<String, String> map, final ProgressDialog aniDialog) {
        myPresenter.postPreContent(APIs.getSmsCode, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        MyUtils.setToast("短信发送成功");
                    }else{
                        time = 60;
                        login_new_duan_btn.setText("获取验证码");
                        login_new_duan_btn.setEnabled(true);
                        h.removeMessages(0);
                        MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,(String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                time = 60;
                login_new_duan_btn.setText("获取验证码");
                login_new_duan_btn.setEnabled(true);
                h.removeMessages(0);
                MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,ss);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DashApplication.LOGINNEW_TO_SETTINGPASS_req&&resultCode==DashApplication.LOGINNEW_TO_SETTINGPASS_res){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeMessages(0);
        h.removeMessages(1);
    }

    private void initView() {
        login_new_name = (EditText) findViewById(R.id.login_new_name);
        login_new_duan = (EditText) findViewById(R.id.login_new_duan);
        login_new_duan_btn = (TextView) findViewById(R.id.login_new_duan_btn);
        login_new_btn = (Button) findViewById(R.id.login_new_btn);
        login_new_zhanghao = (TextView) findViewById(R.id.login_new_zhanghao);
        login_new_weitanchuan_text = (TextView) findViewById(R.id.login_new_weitanchuan_text);
        login_new_weitanchuan = (LinearLayout) findViewById(R.id.login_new_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        login_new_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                login_new_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = login_new_weitanchuan.getMeasuredHeight();
            }
        });

        // edittext默认不显示软键盘，只有edittext被点击时，软键盘才弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    private void submit() {
        // validate
        final String name = login_new_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,"请输入手机号码");
            return;
        }

        if (MyUtils.isPhoneNumber(name) == false) {
            MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,"手机号码输入错误");
            return;
        }

        String duan = login_new_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,"请输入验证码");
            return;
        }

        if(duan.length()!=4){
            MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,"验证码长度有误");
            return;
        }
        // TODO validate success, do something

        if(codeType==1){
            Intent intent = new Intent(Login_New_Activity.this, Setting_Pass_Activity.class);
            intent.putExtra("shouji",name);
            intent.putExtra("smsCode",duan);
            startActivityForResult(intent,DashApplication.LOGINNEW_TO_SETTINGPASS_req);
            overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);

        }else{
            HashMap<String, String> map = new HashMap<>();
            map.put("mobile",name);
            map.put("smsCode",""+duan);
            map.put("codeType",codeType+"");
            map.put("token", DashApplication.android);
            myPresenter.postPreContent(APIs.loginBySmsCode, map, new MyInterfaces() {
                @Override
                public void chenggong(String json) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String code = (String) jsonObject.get("code");
                        if(code.equals("2000")){
                            LoginNewBean loginNewBean = new Gson().fromJson(json, LoginNewBean.class);
                            DashApplication.edit
                                    .putString(SP_String.SHOUJI, name)
                                    .putString(SP_String.TOKEN,DashApplication.android)
                                    .putBoolean(SP_String.ISLOGIN,true)
                                    .putString(SP_String.UID,loginNewBean.getData().getUser().getId()+"")
                                    .putString(SP_String.USERCODE,loginNewBean.getData().getUser().getUserCode()+"")
                                    .commit();

                            setTagAndAlias(loginNewBean.getData().getUser().getId()+"");  // 注册极光的别名

                            Intent intent = new Intent(Login_New_Activity.this, HomePageActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            finish();
                        }else{
                            MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,(String) jsonObject.get("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void shibai(String ss) {
                    MyUtils.setObjectAnimator_login(login_new_weitanchuan,login_new_weitanchuan_text,weitanchuan_height,ss);
                }
            });

        }
    }
}
