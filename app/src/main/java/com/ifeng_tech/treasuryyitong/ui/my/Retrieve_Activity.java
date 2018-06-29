package com.ifeng_tech.treasuryyitong.ui.my;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * 手机验证页面 用于 找回密码 与 更改绑定手机
 */
public class Retrieve_Activity extends BaseMVPActivity<Retrieve_Activity, MyPresenter<Retrieve_Activity>> {

    private RelativeLayout retrieve_Fan;
    private EditText retrieve_duan;
    private TextView retrieve_duan_btn;
    private Button retrieve_btn;

    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (time == 0) {
                time = 60;
                retrieve_duan_btn.setText("点击发送");
                retrieve_duan_btn.setEnabled(true);
            } else {
                retrieve_duan_btn.setText("重新发送" + time + "(s)");
                h.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private TextView retrieve_shoujihao;
    private ImageView retrieve_weitanchuan_img;
    private TextView retrieve_weitanchuan_text;
    private LinearLayout retrieve_weitanchuan;
    private int weitanchuan_height;
    private int type;
    private EditText retrieve_shoujihao_shuru;
    private int select;

    String mobile="";  // 短信发送对象

    /*
    ("通用", 0),("注册", 1),("登录", 2),("找回密码", 3),("开户", 4),("重置登录密码", 5),
    ("更换绑定手机", 6),("绑定新手机", 7),("绑定邮箱", 8),("设置交易密码", 9),("重置交易密码", 10);
     */
    String codeType="0";
    private EditText retrieve_tu_yan;
    private ImageView retrieve_tu_yan_img;

    @Override
    public MyPresenter<Retrieve_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_);
        initView();

        Intent intent = getIntent();
        /**
         *  因为这个页面被重复使用了3次  所以逻辑要。。。
         */
        type = intent.getIntExtra("type", 0); // 用于识别是哪个验证  分为找回密码/忘记密码==1，更改手机==2
        // 用于判断手机号码的输入框 的隐藏/显示
        select = intent.getIntExtra("select", 0);


        if (type==1&&select == 1) {
            retrieve_shoujihao.setVisibility(View.GONE);
            retrieve_shoujihao_shuru.setVisibility(View.VISIBLE);
            codeType="5";   // 根据select来设定codetype的值
        } else {
            retrieve_shoujihao.setVisibility(View.VISIBLE);
            retrieve_shoujihao_shuru.setVisibility(View.GONE);
            String shouji = DashApplication.sp.getString(SP_String.SHOUJI, "");
            mobile=shouji;
            String tou = shouji.substring(0, 3);
            String wei = shouji.substring(8, shouji.length());
            retrieve_shoujihao.setText(tou + "*****" + wei);
            if(type==1){
                codeType="5";  // 找回密码和忘记密码的状态是相同的 都是5
            }else{
                codeType="6";
            }
        }


        retrieve_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 短信 点击
        retrieve_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(select==1){
                    String shuru = retrieve_shoujihao_shuru.getText().toString().trim();
                    if (TextUtils.isEmpty(shuru)) {
                        MyUtils.setToast("请输入手机号");
                        return;
                    }

                    if(MyUtils.isPhoneNumber(shuru)==false){
                        MyUtils.setToast("手机号码格式不正确");
                        return;
                    }
                    mobile=shuru ;
                }

                String yan = retrieve_tu_yan.getText().toString().trim();
                if (TextUtils.isEmpty(yan)) {
                    Toast.makeText(Retrieve_Activity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("verifyCode",yan);
                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Retrieve_Activity.this, SP_String.JIAZAI);

                myPresenter.postPreContent(APIs.verifyCode, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                retrieve_duan_btn.setText("重新发送" + time + "(s)");
                                retrieve_duan_btn.setEnabled(false);
                                h.sendEmptyMessageDelayed(0, 1000);

//                              MyUtils.setToast("请求网络。。。");

                                HashMap<String, String> map = new HashMap<>();
                                map.put("mobile",mobile);
                                map.put("codeType",codeType);
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
                                MyUtils.setObjectAnimator(retrieve_weitanchuan,
                                        retrieve_weitanchuan_img,
                                        retrieve_weitanchuan_text,
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
                        MyUtils.setObjectAnimator(retrieve_weitanchuan,
                                retrieve_weitanchuan_img,
                                retrieve_weitanchuan_text,
                                weitanchuan_height,
                                false, ss);
                    }
                });


            }
        });


        retrieve_tu_yan_img.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 初始化图形验证码
                myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
                    @Override
                    public void chenggong(Bitmap bitmap) {
                        if(bitmap!=null){
                            retrieve_tu_yan_img.setImageBitmap(bitmap);
                        }
                    }
                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast("图形验证码获取失败！");
                    }
                });
            }
        });

        // 下一步 点击
        retrieve_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(retrieve_tu_yan.getWindowToken(), 0);
                submit();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 初始化图形验证码
        myPresenter.getPro_TuXingYanZheng( APIs.newImageCode, new MyJieKou() {
            @Override
            public void chenggong(Bitmap bitmap) {
                if(bitmap!=null){
                    retrieve_tu_yan_img.setImageBitmap(bitmap);
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
        select=0;
        h.removeMessages(0); // 避免内存泄漏
    }

    private void initView() {
        retrieve_Fan = (RelativeLayout) findViewById(R.id.retrieve_Fan);
        retrieve_duan = (EditText) findViewById(R.id.retrieve_duan);

        retrieve_tu_yan = (EditText) findViewById(R.id.retrieve_tu_yan);
        retrieve_tu_yan_img = (ImageView) findViewById(R.id.retrieve_tu_yan_img);

        retrieve_duan_btn = (TextView) findViewById(R.id.retrieve_duan_btn);
        retrieve_btn = (Button) findViewById(R.id.retrieve_btn);

        retrieve_shoujihao = (TextView) findViewById(R.id.retrieve_shoujihao);


        retrieve_weitanchuan_img = (ImageView) findViewById(R.id.retrieve_weitanchuan_img);
        retrieve_weitanchuan_text = (TextView) findViewById(R.id.retrieve_weitanchuan_text);
        retrieve_weitanchuan = (LinearLayout) findViewById(R.id.retrieve_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        retrieve_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                retrieve_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = retrieve_weitanchuan.getMeasuredHeight();
            }
        });
        retrieve_shoujihao_shuru = (EditText) findViewById(R.id.retrieve_shoujihao_shuru);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DashApplication.RETRIEVE_TO_FORGET_req && resultCode == DashApplication.RETRIEVE_TO_FORGET_res) {
            finish();
        }
        if (requestCode == DashApplication.RETRIEVE_TO_CHANGE_req && resultCode == DashApplication.RETRIEVE_TO_CHANGE_res) {
            finish();
        }
    }

    private void submit() {
        // validate

        if(select==1){
            String shuru = retrieve_shoujihao_shuru.getText().toString().trim();
            if (TextUtils.isEmpty(shuru)) {
                Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                return;
            }

            if(MyUtils.isPhoneNumber(shuru)==false){
                Toast.makeText(this, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String yan = retrieve_tu_yan.getText().toString().trim();
        if (TextUtils.isEmpty(yan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String duan = retrieve_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "短信验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("smsCode",duan);
        map.put("codeType",codeType);
        setXiaYiBu(map,duan);
    }

    // 下一步 的点击接口调用
    private void setXiaYiBu(final HashMap<String, String> map, final String duan) {
        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.verifySmsCode, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    String message = (String) jsonObject.get("message");
                    if(code.equals("2000")){
                        if (type == DashApplication.ANQUAN_TYPE_ZHAOHUI) {  // 从有输入框的页面 跳到忘记密码/ 找回密码  暂无接口
                            Intent intent = new Intent(Retrieve_Activity.this, Forget_Activity.class);
                            intent.putExtra("mobile",mobile);
                            intent.putExtra("smsCode",duan);
                            startActivityForResult(intent, DashApplication.RETRIEVE_TO_FORGET_req);
                        } else {                                          // 跳到更换手机号 页面
                            Intent intent = new Intent(Retrieve_Activity.this, Change_Activity.class);
                            intent.putExtra("oldSmsCode",duan);
                            startActivityForResult(intent, DashApplication.RETRIEVE_TO_CHANGE_req);
                        }
                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);

                    }else{
                        MyUtils.setObjectAnimator(retrieve_weitanchuan,
                                retrieve_weitanchuan_img,
                                retrieve_weitanchuan_text,
                                weitanchuan_height,
                                false, message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setObjectAnimator(retrieve_weitanchuan,
                    retrieve_weitanchuan_img,
                    retrieve_weitanchuan_text,
                    weitanchuan_height,
                    false, "操作失败!");
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
