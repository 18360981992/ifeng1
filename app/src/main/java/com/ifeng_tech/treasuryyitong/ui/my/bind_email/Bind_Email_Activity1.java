package com.ifeng_tech.treasuryyitong.ui.my.bind_email;

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
 * 绑定邮箱
 */
public class Bind_Email_Activity1 extends BaseMVPActivity<Bind_Email_Activity1, MyPresenter<Bind_Email_Activity1>>{

    private RelativeLayout bind_email_Fan;
    private EditText bind_email_youxiang;
    private TextView bind_email_shoujihao;
    private EditText bind_email_tu_yan;
    private ImageView bind_email_tu_yan_img;
    private EditText bind_email_duan;
    private TextView bind_email_duan_btn;
    private Button bind_email_btn;
    private ImageView bind_email_weitanchuan_img;
    private TextView bind_email_weitanchuan_text;
    private LinearLayout bind_email_weitanchuan;

    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                time--;
                if (time == 0) {
                    time = 60;
                    bind_email_duan_btn.setText("点击发送");
                    bind_email_duan_btn.setEnabled(true);
                } else {
                    bind_email_duan_btn.setText("重新发送" + time + "(s)");
                    h.sendEmptyMessageDelayed(0, 1000);
                }
            }else{
                submit();
            }

        }
    };
    private int weitanchuan_height;
    private String shouji;
    private TextView bind_email_title;
    private String title;
    private String select;

    @Override
    public MyPresenter<Bind_Email_Activity1> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind__email_);
        initView();
        bind_email_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bind_email_tu_yan_img.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                //                MyUtils.setToast("点击了图形验证。。。");
                getTuXing_Code();
            }
        });

        // 获取短信验证
        bind_email_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String yan = bind_email_tu_yan.getText().toString().trim();
                if (TextUtils.isEmpty(yan)) {
                    MyUtils.setToast("请输入验证码");
                    return;
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("verifyCode",yan);
                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Bind_Email_Activity1.this, SP_String.JIAZAI);

                myPresenter.postPreContent(APIs.verifyCode, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                bind_email_duan_btn.setText("重新发送" + time + "(s)");
                                bind_email_duan_btn.setEnabled(false);
                                h.sendEmptyMessageDelayed(0, 1000);
                                HashMap<String, String> map = new HashMap<>();
                                map.put("mobile", shouji);
                                map.put("verifyCode",yan);
                                if(select.equals(SP_String.YEWUMIMA)){
                                    map.put("codeType","9");  //  业务密码设置
                                }else {
                                    map.put("codeType","8");  //  绑定邮箱设置
                                }

                                //                MyUtils.setToast("请求网络。。。");
                                myPresenter.postPreContent(APIs.getSmsCode, map, new MyInterfaces() {
                                    @Override
                                    public void chenggong(String json) {
                                        SmsCodeBean smCodeBean = new Gson().fromJson(json, SmsCodeBean.class);
                                        if(smCodeBean.getCode().equals("2000")){
                                            MyUtils.setToast("短信发送成功");
                                        }else{
                                            time = 60;
                                            bind_email_duan_btn.setText("点击发送");
                                            bind_email_duan_btn.setEnabled(true);
                                            h.removeMessages(0);
                                            MyUtils.setToast(smCodeBean.getMessage()+"");
                                        }
                                    }
                                    @Override
                                    public void shibai(String ss) {
                                        time = 60;
                                        bind_email_duan_btn.setText("点击发送");
                                        bind_email_duan_btn.setEnabled(true);
                                        h.removeMessages(0);
                                        MyUtils.setToast("短信发送失败");
                                    }
                                });
                            }else{
                                MyUtils.setObjectAnimator(bind_email_weitanchuan,
                                        bind_email_weitanchuan_img,
                                        bind_email_weitanchuan_text,
                                        weitanchuan_height,
                                        false, (String) jsonObject.get("message"));
                                getTuXing_Code(); // 重新刷新图形
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        aniDialog.dismiss();
                        MyUtils.setObjectAnimator(bind_email_weitanchuan,
                                bind_email_weitanchuan_img,
                                bind_email_weitanchuan_text,
                                weitanchuan_height,
                                false, ss);
                    }
                });
            }
        });

        // 自定义点击 “下一步” 按钮
        bind_email_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(bind_email_duan.getWindowToken(), 0);
                h.sendEmptyMessageDelayed(1,300);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 更改title
        title = getIntent().getStringExtra("title");
        select = getIntent().getStringExtra("select");  // 获取select 状态值 用来标识下一步页面中的邮箱按钮的隐藏与显示

        bind_email_title.setText(title +"");

        shouji = DashApplication.sp.getString(SP_String.SHOUJI, "");
        String tou = shouji.substring(0, 3);
        String wei = shouji.substring(8, shouji.length());
        bind_email_shoujihao.setText(tou + "*****" + wei);
        getTuXing_Code();// 初始化图形验证码


    }
    // 初始化图形验证码
    private void getTuXing_Code() {
        // 初始化图形验证码
        myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
            @Override
            public void chenggong(Bitmap bitmap) {
                if(bitmap!=null){
                    bind_email_tu_yan_img.setImageBitmap(bitmap);
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast("图形验证码获取失败！");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DashApplication.EMAIL1_TO_EMAIL2_req&&resultCode==DashApplication.EMAIL1_TO_EMAIL2_res){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeMessages(0);
    }

    private void initView() {
        bind_email_title = (TextView) findViewById(R.id.bind_email_title);
        bind_email_Fan = (RelativeLayout) findViewById(R.id.bind_email_Fan);
        bind_email_youxiang = (EditText) findViewById(R.id.bind_email_youxiang);
        bind_email_shoujihao = (TextView) findViewById(R.id.bind_email_shoujihao);
        bind_email_tu_yan = (EditText) findViewById(R.id.bind_email_tu_yan);
        bind_email_tu_yan_img = (ImageView) findViewById(R.id.bind_email_tu_yan_img);
        bind_email_duan = (EditText) findViewById(R.id.bind_email_duan);
        bind_email_duan_btn = (TextView) findViewById(R.id.bind_email_duan_btn);
        bind_email_btn = (Button) findViewById(R.id.bind_email_btn);
        bind_email_weitanchuan_img = (ImageView) findViewById(R.id.bind_email_weitanchuan_img);
        bind_email_weitanchuan_text = (TextView) findViewById(R.id.bind_email_weitanchuan_text);
        bind_email_weitanchuan = (LinearLayout) findViewById(R.id.bind_email_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);  // 键盘挡住输入框

        //通过设置监听来获取 微弹窗 控件的高度
        bind_email_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                bind_email_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = bind_email_weitanchuan.getMeasuredHeight();
            }
        });
    }


    private void submit() {
        // validate
        final String youxiang = bind_email_youxiang.getText().toString().trim();
        if (TextUtils.isEmpty(youxiang)) {
            Toast.makeText(this, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isEmail(youxiang)==false){
            MyUtils.setToast("请输入正确的邮箱格式");
            return;
        }

        String yan = bind_email_tu_yan.getText().toString().trim();
        if (TextUtils.isEmpty(yan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        final String duan = bind_email_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
//        MyUtils.setToast("点击下一步，请求网络。。。");

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        HashMap<String, String> map = new HashMap<>();
        map.put("email",youxiang);
        map.put("smsCode",duan);
        map.put("type","8");

        myPresenter.postPreContent(APIs.checkSMSVerificationCodeAndSendEmailVerficationCode, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Intent intent = new Intent(Bind_Email_Activity1.this, Bind_Email_Activity2.class);
                        intent.putExtra("email",youxiang);
                        intent.putExtra("title",title);
                        intent.putExtra("select",select);
                        startActivityForResult(intent,DashApplication.EMAIL1_TO_EMAIL2_req);
                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                    }else{
                        getTuXing_Code();// 初始化图形验证码
                        MyUtils.setObjectAnimator(bind_email_weitanchuan,
                                bind_email_weitanchuan_img,
                                bind_email_weitanchuan_text,
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
                getTuXing_Code();// 初始化图形验证码
                MyUtils.setObjectAnimator(bind_email_weitanchuan,
                        bind_email_weitanchuan_img,
                        bind_email_weitanchuan_text,
                        weitanchuan_height,
                        false, "邮件发送失败!");

            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
