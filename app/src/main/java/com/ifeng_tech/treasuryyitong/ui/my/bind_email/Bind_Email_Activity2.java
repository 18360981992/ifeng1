package com.ifeng_tech.treasuryyitong.ui.my.bind_email;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.my.yewu_pass.Business_Pass_Setting_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 绑定邮箱的确认页面
 *
 *  这个页面被重复用了三次
 *   业务密码的设置   按钮 下一步
 *   绑定邮箱的设置   按钮 确认
 *   绑定邮箱的重置   按钮 确认
 */
public class Bind_Email_Activity2 extends BaseMVPActivity<Bind_Email_Activity2, MyPresenter<Bind_Email_Activity2>> {

    private RelativeLayout bind_email_Fan2;
    private TextView bind_email_youxiang2;
    private EditText bind_email_duan2;
    private TextView bind_email_duan_btn2;
    private Button bind_email_btn2;
    private ImageView bind_email_weitanchuan_img2;
    private TextView bind_email_weitanchuan_text2;
    private LinearLayout bind_email_weitanchuan2;
    private int weitanchuan_height;


    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (time == 0) {
                time = 60;
                bind_email_duan_btn2.setText("再发一封");
                bind_email_duan_btn2.setEnabled(true);
            } else {
                bind_email_duan_btn2.setText("重新发送" + time + "(s)");
                h.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };
    private String email;
    private TextView bind_email_title2;
    private Button bind_email_btn2_xiayibu;

    @Override
    public MyPresenter<Bind_Email_Activity2> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind__email_2);
        initView();

        bind_email_Fan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bind_email_duan_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bind_email_duan_btn2.setText("重新发送" + time + "(s)");
                bind_email_duan_btn2.setEnabled(false);
                h.sendEmptyMessageDelayed(0, 1000);
//                MyUtils.setToast("请求网络。。。");
                HashMap<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("type","8");
                myPresenter.postPreContent(APIs.sendMailAgain, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                MyUtils.setToast("发送成功！");
                            }else{
                                MyUtils.setToast((String) jsonObject.get("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast(ss);
                    }
                });
            }
        });


        // 点击确认按钮
        bind_email_btn2.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(bind_email_duan2.getWindowToken(), 0);
                submit_queren();
            }
        });

        // 从业务密码进入 点击下一步 校验邮箱是否正确
        bind_email_btn2_xiayibu.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(bind_email_duan2.getWindowToken(), 0);
                submit_xiayibu();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        String title = intent.getStringExtra("title");
        String select = intent.getStringExtra("select");
        bind_email_title2.setText(title+"");
        String newEmail = MyUtils.getEmail(email);
        bind_email_youxiang2.setText("邮件已发送到: "+newEmail+"请收到邮件后及时输入验证码");

        if(select.equals(SP_String.YEWUMIMA)){  // select == 业务密码 下一步按钮显示
            bind_email_btn2_xiayibu.setVisibility(View.VISIBLE);
            bind_email_btn2.setVisibility(View.GONE);
        }else{
            bind_email_btn2_xiayibu.setVisibility(View.GONE);
            bind_email_btn2.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        bind_email_title2 = (TextView) findViewById(R.id.bind_email_title2);
        bind_email_Fan2 = (RelativeLayout) findViewById(R.id.bind_email_Fan2);
        bind_email_youxiang2 = (TextView) findViewById(R.id.bind_email_youxiang2);
        bind_email_duan2 = (EditText) findViewById(R.id.bind_email_duan2);
        bind_email_duan_btn2 = (TextView) findViewById(R.id.bind_email_duan_btn2);
        bind_email_btn2 = (Button) findViewById(R.id.bind_email_btn2);
        bind_email_btn2_xiayibu = (Button) findViewById(R.id.bind_email_btn2_xiayibu);

        bind_email_weitanchuan_img2 = (ImageView) findViewById(R.id.bind_email_weitanchuan_img2);
        bind_email_weitanchuan_text2 = (TextView) findViewById(R.id.bind_email_weitanchuan_text2);
        bind_email_weitanchuan2 = (LinearLayout) findViewById(R.id.bind_email_weitanchuan2);

        SoftHideKeyBoardUtil.assistActivity(this);


        //通过设置监听来获取 微弹窗 控件的高度
        bind_email_weitanchuan2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                bind_email_weitanchuan2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = bind_email_weitanchuan2.getMeasuredHeight();
            }
        });
    }

    // 点击确认 按钮
    private void submit_queren() {
        // validate
        String duan2 = bind_email_duan2.getText().toString().trim();
        if (TextUtils.isEmpty(duan2)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
//        MyUtils.setToast("点击确认，请求网络。。。");

        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("code",duan2);
        map.put("type","8");

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.checkeMailCodeAndBindMailbox, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        MyUtils.setObjectAnimator_anquan(bind_email_weitanchuan2,
                                bind_email_weitanchuan_img2,
                                bind_email_weitanchuan_text2,
                                weitanchuan_height,
                                true, "绑定邮箱成功!");
                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                DashApplication.edit
                                        .putString(SP_String.EMAIL,email+"")
                                        .commit();
                                setResult(DashApplication.EMAIL1_TO_EMAIL2_res);
                                finish();
                            }
                        });
                    }else{
                        MyUtils.setObjectAnimator(bind_email_weitanchuan2,
                                bind_email_weitanchuan_img2,
                                bind_email_weitanchuan_text2,
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
                MyUtils.setObjectAnimator(bind_email_weitanchuan2,
                        bind_email_weitanchuan_img2,
                        bind_email_weitanchuan_text2,
                        weitanchuan_height,
                        false, "绑定邮箱失败！");
            }
        });
    }

    // 点击下一步 按钮  校验邮箱验证码
    private void submit_xiayibu() {
        // validate
        String duan2 = bind_email_duan2.getText().toString().trim();
        if (TextUtils.isEmpty(duan2)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
//        MyUtils.setToast("点击确认，请求网络。。。");

        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("code",duan2);
        map.put("type","8");

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.checkeMailCode, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Intent intent = new Intent(Bind_Email_Activity2.this, Business_Pass_Setting_Activity.class);
                        intent.putExtra("email",email);
                        startActivityForResult(intent,DashApplication.EMAIL2_TO_YEWU_req);
                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast("绑定邮箱失败！");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DashApplication.EMAIL2_TO_YEWU_req&&resultCode==DashApplication.EMAIL2_TO_YEWU_res){
            setResult(DashApplication.EMAIL1_TO_EMAIL2_res);
            finish();
        }
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
