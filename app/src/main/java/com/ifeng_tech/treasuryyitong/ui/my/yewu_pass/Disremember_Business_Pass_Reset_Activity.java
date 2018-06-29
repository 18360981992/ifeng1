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
 * 不记得业务密码的重置
 */
public class Disremember_Business_Pass_Reset_Activity extends BaseMVPActivity<Disremember_Business_Pass_Reset_Activity, MyPresenter<Disremember_Business_Pass_Reset_Activity>> implements View.OnClickListener {

    private RelativeLayout disremember_Business_Pass_Reset_Fan;
    private TextView disremember_Business_Pass_Reset_shoujihao;
    private EditText disremember_Business_Pass_Reset_new_pass;
    private EditText disremember_Business_Pass_Reset_zaici_pass;
    private EditText disremember_Business_Pass_Reset_tu_yan;
    private ImageView disremember_Business_Pass_Reset_tu_yan_img;
    private EditText disremember_Business_Pass_Reset_duan;
    private TextView disremember_Business_Pass_Reset_duan_btn;
    private Button disremember_Business_Pass_Reset_btn;
    private ImageView disremember_Business_Pass_Reset_weitanchuan_img;
    private TextView disremember_Business_Pass_Reset_weitanchuan_text;
    private LinearLayout disremember_Business_Pass_Reset_weitanchuan;
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
                    disremember_Business_Pass_Reset_duan_btn.setText("点击发送");
                    disremember_Business_Pass_Reset_duan_btn.setEnabled(true);
                } else {
                    disremember_Business_Pass_Reset_duan_btn.setText("重新发送" + time + "(s)");
                    h.sendEmptyMessageDelayed(0, 1000);
                }
            }

        }
    };
    private String shouji;

    @Override
    public MyPresenter<Disremember_Business_Pass_Reset_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disremember__business__pass__reset_);
        initView();

        // 点击返回
        disremember_Business_Pass_Reset_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 获取手机号
        shouji = DashApplication.sp.getString(SP_String.SHOUJI, "");
        String tou = shouji.substring(0, 3);
        String wei = shouji.substring(8, shouji.length());
        disremember_Business_Pass_Reset_shoujihao.setText(tou + "*****" + wei);

        // 点击切换图形验证码
        disremember_Business_Pass_Reset_tu_yan_img.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 获取图形验证码
                myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
                    @Override
                    public void chenggong(Bitmap bitmap) {
                        if(bitmap!=null){
                            disremember_Business_Pass_Reset_tu_yan_img.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast("图形验证码获取失败！");
                    }
                });
            }
        });

        disremember_Business_Pass_Reset_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yan = disremember_Business_Pass_Reset_tu_yan.getText().toString().trim();
                if (TextUtils.isEmpty(yan)) {
                    Toast.makeText(Disremember_Business_Pass_Reset_Activity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 重置交易密码 先验证图形验证码
                HashMap<String, String> map = new HashMap<>();
                map.put("verifyCode",yan);

                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Disremember_Business_Pass_Reset_Activity.this, SP_String.JIAZAI);

                myPresenter.postPreContent(APIs.verifyCode, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                disremember_Business_Pass_Reset_duan_btn.setText("重新发送" + time + "(s)");
                                disremember_Business_Pass_Reset_duan_btn.setEnabled(false);
                                h.sendEmptyMessageDelayed(0, 1000);
//                              MyUtils.setToast("请求网络。。。");
                                HashMap<String, String> map = new HashMap<>();
                                map.put("mobile", shouji);
                                map.put("codeType","10");  // ("重置交易密码", 10);  状态写10 有异常
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
                            }else{
                                MyUtils.setObjectAnimator(disremember_Business_Pass_Reset_weitanchuan,
                                        disremember_Business_Pass_Reset_weitanchuan_img,
                                        disremember_Business_Pass_Reset_weitanchuan_text,
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
                        MyUtils.setObjectAnimator(disremember_Business_Pass_Reset_weitanchuan,
                                disremember_Business_Pass_Reset_weitanchuan_img,
                                disremember_Business_Pass_Reset_weitanchuan_text,
                                weitanchuan_height,
                                false, ss);
                    }
                });


            }
        });

        // 再次确认密码的失焦事件
        isPass_Old_New(disremember_Business_Pass_Reset_zaici_pass,disremember_Business_Pass_Reset_new_pass);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 获取图形验证码
        myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
            @Override
            public void chenggong(Bitmap bitmap) {
                if(bitmap!=null){
                    disremember_Business_Pass_Reset_tu_yan_img.setImageBitmap(bitmap);
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast("图形验证码获取失败！");
            }
        });
    }

    private void initView() {
        disremember_Business_Pass_Reset_Fan = (RelativeLayout) findViewById(R.id.disremember_Business_Pass_Reset_Fan);
        disremember_Business_Pass_Reset_shoujihao = (TextView) findViewById(R.id.disremember_Business_Pass_Reset_shoujihao);
        disremember_Business_Pass_Reset_new_pass = (EditText) findViewById(R.id.disremember_Business_Pass_Reset_new_pass);
        disremember_Business_Pass_Reset_zaici_pass = (EditText) findViewById(R.id.disremember_Business_Pass_Reset_zaici_pass);
        disremember_Business_Pass_Reset_tu_yan = (EditText) findViewById(R.id.disremember_Business_Pass_Reset_tu_yan);
        disremember_Business_Pass_Reset_tu_yan_img = (ImageView) findViewById(R.id.disremember_Business_Pass_Reset_tu_yan_img);
        disremember_Business_Pass_Reset_duan = (EditText) findViewById(R.id.disremember_Business_Pass_Reset_duan);
        disremember_Business_Pass_Reset_duan_btn = (TextView) findViewById(R.id.disremember_Business_Pass_Reset_duan_btn);
        disremember_Business_Pass_Reset_btn = (Button) findViewById(R.id.disremember_Business_Pass_Reset_btn);
        disremember_Business_Pass_Reset_weitanchuan_img = (ImageView) findViewById(R.id.disremember_Business_Pass_Reset_weitanchuan_img);
        disremember_Business_Pass_Reset_weitanchuan_text = (TextView) findViewById(R.id.disremember_Business_Pass_Reset_weitanchuan_text);
        disremember_Business_Pass_Reset_weitanchuan = (LinearLayout) findViewById(R.id.disremember_Business_Pass_Reset_weitanchuan);

        disremember_Business_Pass_Reset_btn.setOnClickListener(this);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        disremember_Business_Pass_Reset_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                disremember_Business_Pass_Reset_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = disremember_Business_Pass_Reset_weitanchuan.getMeasuredHeight();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.disremember_Business_Pass_Reset_btn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        final String pass = disremember_Business_Pass_Reset_new_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入交易密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(pass.length()!=6){
            MyUtils.setToast("请输入6位的交易密码");
            return;
        }
        String zaipass = disremember_Business_Pass_Reset_zaici_pass.getText().toString().trim();
        if (TextUtils.isEmpty(zaipass)) {
            Toast.makeText(this, "请再次输入交易密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!zaipass.equals(pass)){
            MyUtils.setToast("两次密码输入不一致");
            return;
        }

        String yan = disremember_Business_Pass_Reset_tu_yan.getText().toString().trim();
        if (TextUtils.isEmpty(yan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        final String duan = disremember_Business_Pass_Reset_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }


        // 在调重置业务密码 接口
        HashMap<String, String> map = new HashMap<>();
        map.put("businessPassword",pass);
        map.put("smsCode",duan);
        map.put("oldBusinessPwd","");
        map.put("type","1");     //  0：表示设置或者修改业务密码  1：表示重置业务密码

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(Disremember_Business_Pass_Reset_Activity.this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.setBusinessPassword, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        MyUtils.setObjectAnimator_anquan(disremember_Business_Pass_Reset_weitanchuan,
                                disremember_Business_Pass_Reset_weitanchuan_img,
                                disremember_Business_Pass_Reset_weitanchuan_text,
                                weitanchuan_height,
                                true, "重置成功!");


                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                setResult(DashApplication.BUJIDE_TO_YEWUPASS_res);
                                finish();
                            }
                        });
                    }else{
                        MyUtils.setObjectAnimator(disremember_Business_Pass_Reset_weitanchuan,
                                disremember_Business_Pass_Reset_weitanchuan_img,
                                disremember_Business_Pass_Reset_weitanchuan_text,
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
                MyUtils.setObjectAnimator(disremember_Business_Pass_Reset_weitanchuan,
                        disremember_Business_Pass_Reset_weitanchuan_img,
                        disremember_Business_Pass_Reset_weitanchuan_text,
                        weitanchuan_height,
                        false, "设置失败!");
            }
        });


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
