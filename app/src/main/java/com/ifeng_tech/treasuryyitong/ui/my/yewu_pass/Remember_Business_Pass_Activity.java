package com.ifeng_tech.treasuryyitong.ui.my.yewu_pass;

import android.app.ProgressDialog;
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
 * 记得业务密码
 */
public class Remember_Business_Pass_Activity extends BaseMVPActivity<Remember_Business_Pass_Activity, MyPresenter<Remember_Business_Pass_Activity>>  {

    private RelativeLayout remember_Business_pass_Fan;
    private TextView remember_Business_pass_shoujihao;
    private EditText remember_Business_pass_old_pass;
    private EditText remember_Business_pass_new_pass;
    private EditText remember_Business_pass_zaici_pass;
    private EditText remember_Business_pass_tu_yan;
    private ImageView remember_Business_pass_tu_yan_img;
    private EditText remember_Business_pass_duan;
    private TextView remember_Business_pass_duan_btn;
    private Button remember_Business_pass_btn;
    private ImageView remember_Business_pass_weitanchuan_img;
    private TextView remember_Business_pass_weitanchuan_text;
    private LinearLayout remember_Business_pass_weitanchuan;
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
                    remember_Business_pass_duan_btn.setText("点击发送");
                    remember_Business_pass_duan_btn.setEnabled(true);
                } else {
                    remember_Business_pass_duan_btn.setText("重新发送" + time + "(s)");
                    h.sendEmptyMessageDelayed(0, 1000);
                }
            }

        }
    };
    private String shouji;

    @Override
    public MyPresenter<Remember_Business_Pass_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember__business__pass_);
        initView();

        remember_Business_pass_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //  点击切换图形验证码
        remember_Business_pass_tu_yan_img.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
                    @Override
                    public void chenggong(Bitmap bitmap) {
                        if(bitmap!=null){
                            remember_Business_pass_tu_yan_img.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast("图形验证码获取失败！");
                    }
                });
            }
        });


        // 点击发送短信验证码
        remember_Business_pass_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String yan = remember_Business_pass_tu_yan.getText().toString().trim();
                if (TextUtils.isEmpty(yan)) {
                    Toast.makeText(Remember_Business_Pass_Activity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("verifyCode",yan);
                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Remember_Business_Pass_Activity.this, SP_String.JIAZAI);

                myPresenter.postPreContent(APIs.verifyCode, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                remember_Business_pass_duan_btn.setText("重新发送" + time + "(s)");
                                remember_Business_pass_duan_btn.setEnabled(false);
                                h.sendEmptyMessageDelayed(0, 1000);

                                HashMap<String, String> map = new HashMap<>();
                                map.put("mobile",shouji);
                                map.put("codeType","9");  // ("设置交易密码", 9)  状态写10 有异常
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
                                MyUtils.setObjectAnimator(remember_Business_pass_weitanchuan,
                                        remember_Business_pass_weitanchuan_img,
                                        remember_Business_pass_weitanchuan_text,
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
                        MyUtils.setObjectAnimator(remember_Business_pass_weitanchuan,
                                remember_Business_pass_weitanchuan_img,
                                remember_Business_pass_weitanchuan_text,
                                weitanchuan_height,
                                false, ss);
                    }
                });
            }
        });

        // 失焦是判断两次密码是否一致
        isPass_Old_New(remember_Business_pass_zaici_pass,remember_Business_pass_new_pass);


        remember_Business_pass_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(remember_Business_pass_duan.getWindowToken(), 0);
                submit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 手机号应该是在登录成功后保存的数据
        shouji = DashApplication.sp.getString(SP_String.SHOUJI, "");
        String tou = shouji.substring(0, 3);
        String wei = shouji.substring(8, shouji.length());
        remember_Business_pass_shoujihao.setText(tou + "*****" + wei);


        // 获取图形验证码
        myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
            @Override
            public void chenggong(Bitmap bitmap) {
                if(bitmap!=null){
                    remember_Business_pass_tu_yan_img.setImageBitmap(bitmap);
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast("图形验证码获取失败！");
            }
        });
    }

    private void initView() {
        remember_Business_pass_Fan = (RelativeLayout) findViewById(R.id.remember_Business_pass_Fan);
        remember_Business_pass_shoujihao = (TextView) findViewById(R.id.remember_Business_pass_shoujihao);
        remember_Business_pass_old_pass = (EditText) findViewById(R.id.remember_Business_pass_old_pass);
        remember_Business_pass_new_pass = (EditText) findViewById(R.id.remember_Business_pass_new_pass);
        remember_Business_pass_zaici_pass = (EditText) findViewById(R.id.remember_Business_pass_zaici_pass);
        remember_Business_pass_tu_yan = (EditText) findViewById(R.id.remember_Business_pass_tu_yan);
        remember_Business_pass_tu_yan_img = (ImageView) findViewById(R.id.remember_Business_pass_tu_yan_img);
        remember_Business_pass_duan = (EditText) findViewById(R.id.remember_Business_pass_duan);
        remember_Business_pass_duan_btn = (TextView) findViewById(R.id.remember_Business_pass_duan_btn);
        remember_Business_pass_btn = (Button) findViewById(R.id.remember_Business_pass_btn);
        remember_Business_pass_weitanchuan_img = (ImageView) findViewById(R.id.remember_Business_pass_weitanchuan_img);
        remember_Business_pass_weitanchuan_text = (TextView) findViewById(R.id.remember_Business_pass_weitanchuan_text);
        remember_Business_pass_weitanchuan = (LinearLayout) findViewById(R.id.remember_Business_pass_weitanchuan);

        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        remember_Business_pass_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                remember_Business_pass_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = remember_Business_pass_weitanchuan.getMeasuredHeight();
            }
        });
    }


    private void submit() {
        // validate
        final String oldpass = remember_Business_pass_old_pass.getText().toString().trim();
        if (TextUtils.isEmpty(oldpass)) {
            Toast.makeText(this, "请输入业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(oldpass.length()!=6){
            Toast.makeText(this, "请输入6位旧业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        final String newpass = remember_Business_pass_new_pass.getText().toString().trim();
        if (TextUtils.isEmpty(newpass)) {
            Toast.makeText(this, "请输入业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(newpass.length()!=6){
            Toast.makeText(this, "请输入6位新业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String zaipass = remember_Business_pass_zaici_pass.getText().toString().trim();
        if (TextUtils.isEmpty(zaipass)) {
            Toast.makeText(this, "请再次输入交易密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!newpass.equals(zaipass)){
            MyUtils.setToast("两次密码输入不一致！");
            return;
        }

        String yan = remember_Business_pass_tu_yan.getText().toString().trim();
        if (TextUtils.isEmpty(yan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        final String duan = remember_Business_pass_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        HashMap<String, String> map = new HashMap<>();
        map.put("businessPassword",newpass);
        map.put("smsCode",duan);
        map.put("oldBusinessPwd",oldpass);
        map.put("type","0");
        myPresenter.postPreContent(APIs.setBusinessPassword, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        MyUtils.setObjectAnimator_anquan(remember_Business_pass_weitanchuan,
                                remember_Business_pass_weitanchuan_img,
                                remember_Business_pass_weitanchuan_text,
                                weitanchuan_height,
                                true, "重置成功!");

                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                setResult(DashApplication.BUSINESS_TO_REMEM_res);
                                finish();
                            }
                        });
                    }else{
                        MyUtils.setObjectAnimator(remember_Business_pass_weitanchuan,
                                remember_Business_pass_weitanchuan_img,
                                remember_Business_pass_weitanchuan_text,
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
                MyUtils.setObjectAnimator(remember_Business_pass_weitanchuan,
                        remember_Business_pass_weitanchuan_img,
                        remember_Business_pass_weitanchuan_text,
                        weitanchuan_height,
                        false, "重置失败!");
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
