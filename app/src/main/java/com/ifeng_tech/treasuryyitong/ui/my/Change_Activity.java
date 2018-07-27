package com.ifeng_tech.treasuryyitong.ui.my;

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
 * 更改手机
 */
public class Change_Activity extends BaseMVPActivity<Change_Activity,MyPresenter<Change_Activity>> {

    private RelativeLayout change_Fan;
    private EditText change_new_phone;
    private EditText change_duan;
    private TextView change_duan_btn;
    private ImageView change_weitanchuan_img;
    private TextView change_weitanchuan_text;
    private LinearLayout change_weitanchuan;
    private Button change_btn;
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
                    change_duan_btn.setText("点击发送");
                    change_duan_btn.setEnabled(true);
                } else {
                    change_duan_btn.setText("重新发送" + time + "(s)");
                    h.sendEmptyMessageDelayed(0, 1000);
                }
            }else{
                submit();
            }

        }
    };
    private String oldSmsCode;   // 旧手机的短信验证码
    private EditText change_tu_yan;
    private ImageView change_tu_yan_img;

    @Override
    public MyPresenter<Change_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_);
        initView();

        oldSmsCode = getIntent().getStringExtra("oldSmsCode");// 旧手机的短信验证码


        change_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        change_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String shuru = change_new_phone.getText().toString().trim();
                if (TextUtils.isEmpty(shuru)) {
                    MyUtils.setToast("请输入手机号");
                    return;
                }

                if(MyUtils.isPhoneNumber(shuru)==false){
                    MyUtils.setToast("手机号码格式不正确");
                    return;
                }

                final String yan = change_tu_yan.getText().toString().trim();
                if (TextUtils.isEmpty(yan)) {
                    Toast.makeText(Change_Activity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("verifyCode",yan);
                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Change_Activity.this, SP_String.JIAZAI);
                myPresenter.postPreContent(APIs.verifyCode, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                change_duan_btn.setText("重新发送" + time + "(s)");
                                change_duan_btn.setEnabled(false);
                                h.sendEmptyMessageDelayed(0, 1000);
//                              MyUtils.setToast("请求网络。。。");

                                HashMap<String, String> map = new HashMap<>();
                                map.put("mobile",shuru);
                                map.put("codeType","7");  // ("绑定新手机", 7)
                                map.put("verifyCode",yan);
                                myPresenter.postPreContent(APIs.getSmsCode, map, new MyInterfaces() {
                                    @Override
                                    public void chenggong(String json) {
                                        SmsCodeBean smCodeBean = new Gson().fromJson(json, SmsCodeBean.class);
                                        if(smCodeBean.getCode().equals("2000")){
                                            MyUtils.setToast("短信发送成功");
                                        }else{
                                            time = 60;
                                            change_duan_btn.setText("点击发送");
                                            change_duan_btn.setEnabled(true);
                                            h.removeMessages(0);
                                            MyUtils.setToast(smCodeBean.getMessage()+"");
                                        }
                                    }
                                    @Override
                                    public void shibai(String ss) {
                                        time = 60;
                                        change_duan_btn.setText("点击发送");
                                        change_duan_btn.setEnabled(true);
                                        h.removeMessages(0);
                                        MyUtils.setToast("短信发送失败");
                                    }
                                });
                            }else{
                                MyUtils.setObjectAnimator(change_weitanchuan,
                                        change_weitanchuan_img,
                                        change_weitanchuan_text,
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
                        MyUtils.setObjectAnimator(change_weitanchuan,
                                change_weitanchuan_img,
                                change_weitanchuan_text,
                                weitanchuan_height,
                                false,"修改失败!");
                    }
                });
            }
        });

        change_tu_yan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 初始化图形验证码
                getTuXing_Code();
            }
        });


        change_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(change_duan.getWindowToken(), 0);
                h.sendEmptyMessageDelayed(1,200);
                //submit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTuXing_Code();// 初始化图形验证码
    }
    // 初始化图形验证码
    private void getTuXing_Code() {
        // 初始化图形验证码
        myPresenter.getPro_TuXingYanZheng( APIs.newImageCode, new MyJieKou() {
            @Override
            public void chenggong(Bitmap bitmap) {
                if(bitmap!=null){
                    change_tu_yan_img.setImageBitmap(bitmap);
                }
            }
            @Override
            public void shibai(String ss) {
                MyUtils.setToast("图形验证码获取失败！");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeMessages(0);  // 避免内存泄漏
    }

    private void initView() {
        change_Fan = (RelativeLayout) findViewById(R.id.change_Fan);
        change_new_phone = (EditText) findViewById(R.id.change_new_phone);
        change_tu_yan = (EditText) findViewById(R.id.change_tu_yan);
        change_tu_yan_img = (ImageView) findViewById(R.id.change_tu_yan_img);
        change_duan = (EditText) findViewById(R.id.change_duan);
        change_duan_btn = (TextView) findViewById(R.id.change_duan_btn);
        change_weitanchuan_img = (ImageView) findViewById(R.id.change_weitanchuan_img);
        change_weitanchuan_text = (TextView) findViewById(R.id.change_weitanchuan_text);
        change_weitanchuan = (LinearLayout) findViewById(R.id.change_weitanchuan);
        change_btn = (Button) findViewById(R.id.change_btn);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        change_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                change_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = change_weitanchuan.getMeasuredHeight();
            }
        });
    }


    private void submit() {
        // validate
        final String phone = change_new_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "输入新手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isPhoneNumber(phone)==false){
            Toast.makeText(this, "输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String yan = change_tu_yan.getText().toString().trim();
        if (TextUtils.isEmpty(yan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String duan = change_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

//        MyUtils.setToast("请求网络。。。");
        HashMap<String, String> map = new HashMap<>();
        map.put("oldSmsCode",oldSmsCode);
        map.put("newMobile",phone);
        map.put("newSmsCode",duan);  // ("绑定新手机", 7)

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.changeMobile, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")) {

                        MyUtils.setObjectAnimator_anquan(change_weitanchuan,
                                change_weitanchuan_img,
                                change_weitanchuan_text,
                                weitanchuan_height,
                                true,"修改成功!");

                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {

                                DashApplication.edit
                                        .putString(SP_String.SHOUJI, phone)
                                        .commit();

                                setResult(DashApplication.RETRIEVE_TO_CHANGE_res);
                                change_btn.setEnabled(false);
                                finish();
                            }
                        });
                    }else{
                        getTuXing_Code();// 初始化图形验证码
                        MyUtils.setObjectAnimator(change_weitanchuan,
                                change_weitanchuan_img,
                                change_weitanchuan_text,
                                weitanchuan_height,
                                false,(String)jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                getTuXing_Code();// 初始化图形验证码
                MyUtils.setObjectAnimator(change_weitanchuan,
                        change_weitanchuan_img,
                        change_weitanchuan_text,
                        weitanchuan_height,
                        false,"修改失败!");
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
