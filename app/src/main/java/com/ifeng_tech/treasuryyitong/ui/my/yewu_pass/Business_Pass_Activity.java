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
 * 业务密码设置
 */
public class Business_Pass_Activity extends BaseMVPActivity<Business_Pass_Activity, MyPresenter<Business_Pass_Activity>>{

    private RelativeLayout business_pass_Fan;
    private TextView business_pass_shoujihao;
    private EditText business_pass_new_pass;
    private EditText business_pass_zaici_pass;
    private EditText business_pass_tu_yan;
    private ImageView business_pass_tu_yan_img;
    private EditText business_pass_duan;
    private TextView business_pass_duan_btn;
    private Button business_pass_btn;
    private ImageView business_pass_weitanchuan_img;
    private TextView business_pass_weitanchuan_text;
    private LinearLayout business_pass_weitanchuan;
    private String shouji;

    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                time--;
                if (time == 0) {
                    time = 60;
                    business_pass_duan_btn.setText("点击发送");
                    business_pass_duan_btn.setEnabled(true);
                } else {
                    business_pass_duan_btn.setText("重新发送" + time + "(s)");
                    h.sendEmptyMessageDelayed(0, 1000);
                }
            }

        }
    };
    private int weitanchuan_height;

    @Override
    public MyPresenter<Business_Pass_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business__pass_);
        initView();

        business_pass_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        business_pass_tu_yan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了图形验证码，请求网络。。。");
                myPresenter.getPro_TuXingYanZheng(APIs.debugApi + "" + APIs.newImageCode, new MyJieKou() {
                    @Override
                    public void chenggong(Bitmap bitmap) {
                        if(bitmap!=null){
                            business_pass_tu_yan_img.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast("图形验证码获取失败！");
                    }
                });


            }
        });

        business_pass_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了短信证码，请求网络。。。");
                business_pass_duan_btn.setText("重新发送" + time + "(s)");
                business_pass_duan_btn.setEnabled(false);
                h.sendEmptyMessageDelayed(0, 1000);

//                MyUtils.setToast("请求网络。。。");
                HashMap<String, String> map = new HashMap<>();
                map.put("mobile",shouji);
                map.put("codeType","9");  // ("设置交易密码", 9)
                myPresenter.postPreContent(APIs.getSmsCode, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        SmsCodeBean smCodeBean = new Gson().fromJson(json, SmsCodeBean.class);
                        if(smCodeBean.getCode().equals("2000")){
                            MyUtils.setToast("短信发送成功");
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast("短信发送失败");
                    }
                });

            }
        });


        // 确认按钮点击
        business_pass_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
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
        business_pass_shoujihao.setText(tou + "*****" + wei);

        myPresenter.getPro_TuXingYanZheng(APIs.debugApi + "" + APIs.newImageCode, new MyJieKou() {
            @Override
            public void chenggong(Bitmap bitmap) {
                if(bitmap!=null){
                    business_pass_tu_yan_img.setImageBitmap(bitmap);
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast("图形验证码获取失败！");
            }
        });


    }

    private void initView() {
        business_pass_Fan = (RelativeLayout) findViewById(R.id.business_pass_Fan);
        business_pass_shoujihao = (TextView) findViewById(R.id.business_pass_shoujihao);
        business_pass_new_pass = (EditText) findViewById(R.id.business_pass_new_pass);
        business_pass_zaici_pass = (EditText) findViewById(R.id.business_pass_zaici_pass);
        business_pass_tu_yan = (EditText) findViewById(R.id.business_pass_tu_yan);
        business_pass_tu_yan_img = (ImageView) findViewById(R.id.business_pass_tu_yan_img);
        business_pass_duan = (EditText) findViewById(R.id.business_pass_duan);
        business_pass_duan_btn = (TextView) findViewById(R.id.business_pass_duan_btn);
        business_pass_btn = (Button) findViewById(R.id.business_pass_btn);
        business_pass_weitanchuan_img = (ImageView) findViewById(R.id.business_pass_weitanchuan_img);
        business_pass_weitanchuan_text = (TextView) findViewById(R.id.business_pass_weitanchuan_text);
        business_pass_weitanchuan = (LinearLayout) findViewById(R.id.business_pass_weitanchuan);


        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        business_pass_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                business_pass_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = business_pass_weitanchuan.getMeasuredHeight();
            }
        });
    }


    private void submit() {
        // validate
        final String pass = business_pass_new_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入交易密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(pass.length()<6){
            Toast.makeText(this, "请输入6-12位的交易密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String zaipass = business_pass_zaici_pass.getText().toString().trim();
        if (TextUtils.isEmpty(zaipass)) {
            Toast.makeText(this, "请再次输入交易密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!pass.equals(zaipass)){
            MyUtils.setToast("两次密码输入不一致！");
            return;
        }

        String yan = business_pass_tu_yan.getText().toString().trim();
        if (TextUtils.isEmpty(yan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        final String duan = business_pass_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

//        MyUtils.setToast("点击了确定按钮，携带参数请求网络。。。");

        // 先验证图形验证码
        HashMap<String, String> map = new HashMap<>();
        map.put("verifyCode",yan);

        //  进度框
        final ProgressDialog aniDialog = new ProgressDialog(Business_Pass_Activity.this);
        aniDialog.setCancelable(true);
        aniDialog.setMessage("正在加载...");
        aniDialog.show();

        myPresenter.postPreContent(APIs.verifyCode, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){

                        HashMap<String, String> map = new HashMap<>();
                        map.put("businessPassword",pass);
                        map.put("smsCode",duan);
                        myPresenter.postPreContent(APIs.setBusinessPassword, map, new MyInterfaces() {
                            @Override
                            public void chenggong(String json) {
                                aniDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(json);
                                    String code = (String) jsonObject.get("code");
                                    if(code.equals("2000")){
                                        MyUtils.setObjectAnimator_anquan(business_pass_weitanchuan,
                                                business_pass_weitanchuan_img,
                                                business_pass_weitanchuan_text,
                                                weitanchuan_height,
                                                true, "设置成功,2秒跳回...!");

                                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                                            @Override
                                            public void chuan() {
                                                finish();
                                            }
                                        });
                                    }else{
                                        MyUtils.setObjectAnimator(business_pass_weitanchuan,
                                                business_pass_weitanchuan_img,
                                                business_pass_weitanchuan_text,
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
                                MyUtils.setObjectAnimator(business_pass_weitanchuan,
                                        business_pass_weitanchuan_img,
                                        business_pass_weitanchuan_text,
                                        weitanchuan_height,
                                        false, "设置失败!");
                            }
                        });
                    }else{
                        aniDialog.dismiss();
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setObjectAnimator(business_pass_weitanchuan,
                        business_pass_weitanchuan_img,
                        business_pass_weitanchuan_text,
                        weitanchuan_height,
                        false, "设置失败!");
            }
        });
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
