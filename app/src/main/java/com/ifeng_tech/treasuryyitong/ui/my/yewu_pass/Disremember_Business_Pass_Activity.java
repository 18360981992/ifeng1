package com.ifeng_tech.treasuryyitong.ui.my.yewu_pass;

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
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 不记得业务密码的绑定邮箱
 */
public class Disremember_Business_Pass_Activity extends BaseMVPActivity<Disremember_Business_Pass_Activity, MyPresenter<Disremember_Business_Pass_Activity>>  {

    private RelativeLayout disremember_business_pass_Fan;
    private RelativeLayout authenticate_Details_Toobar;
    private TextView disremember_business_pass_youxiang;
    private EditText disremember_business_pass_duan;
    private TextView disremember_business_pass_duan_btn;
    private Button disremember_business_pass_btn;
    private ImageView disremember_business_pass_weitanchuan_img;
    private TextView disremember_business_pass_weitanchuan_text;
    private LinearLayout disremember_business_pass_weitanchuan;
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
                    disremember_business_pass_duan_btn.setText("再发一封");
                    disremember_business_pass_duan_btn.setEnabled(true);
                } else {
                    disremember_business_pass_duan_btn.setText("重新发送" + time + "(s)");
                    h.sendEmptyMessageDelayed(0, 1000);
                }
            }

        }
    };
    private String email;

    @Override
    public MyPresenter<Disremember_Business_Pass_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disremember__business__pass_);
        initView();

        disremember_business_pass_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        email = DashApplication.sp.getString(SP_String.EMAIL, "");
        String newEmail = MyUtils.getEmail(email);
        disremember_business_pass_youxiang.setText("邮件已发送到: "+newEmail+"请收到邮件后及时输入验证码");

        disremember_business_pass_duan_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                disremember_business_pass_duan_btn.setText("重新发送" + time + "(s)");
                disremember_business_pass_duan_btn.setEnabled(false);
                h.sendEmptyMessageDelayed(0, 1000);

//                MyUtils.setToast("请求网络。。。");
                HashMap<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("type","8");  //
                myPresenter.postPreContent(APIs.sendMailAgain, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                MyUtils.setToast((String) jsonObject.get("发送成功！"));
                            }else{
                                MyUtils.setToast((String) jsonObject.get("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast("邮件发送失败");
                    }
                });
            }
        });


        // 点击下一步  校验邮箱是否正确 成功进入重置页面
        disremember_business_pass_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(disremember_business_pass_duan.getWindowToken(), 0);
                submit();
            }
        });

    }

    private void initView() {
        disremember_business_pass_Fan = (RelativeLayout) findViewById(R.id.disremember_business_pass_Fan);
        authenticate_Details_Toobar = (RelativeLayout) findViewById(R.id.authenticate_Details_Toobar);
        disremember_business_pass_youxiang = (TextView) findViewById(R.id.disremember_business_pass_youxiang);
        disremember_business_pass_duan = (EditText) findViewById(R.id.disremember_business_pass_duan);
        disremember_business_pass_duan_btn = (TextView) findViewById(R.id.disremember_business_pass_duan_btn);
        disremember_business_pass_btn = (Button) findViewById(R.id.disremember_business_pass_btn);
        disremember_business_pass_weitanchuan_img = (ImageView) findViewById(R.id.disremember_business_pass_weitanchuan_img);
        disremember_business_pass_weitanchuan_text = (TextView) findViewById(R.id.disremember_business_pass_weitanchuan_text);
        disremember_business_pass_weitanchuan = (LinearLayout) findViewById(R.id.disremember_business_pass_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        disremember_business_pass_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                disremember_business_pass_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = disremember_business_pass_weitanchuan.getMeasuredHeight();
            }
        });
    }


   // 点击下一步 校验邮箱是否正确
    private void submit() {
        // validate
        String duan = disremember_business_pass_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        HashMap<String, String> map = new HashMap<>();
        map.put("email",email);
        map.put("code",duan);
        map.put("type","8");  //

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(Disremember_Business_Pass_Activity.this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.checkeMailCode, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Intent intent = new Intent(Disremember_Business_Pass_Activity.this, Disremember_Business_Pass_Reset_Activity.class);
                        startActivityForResult(intent,DashApplication.BUJIDE_TO_YEWUPASS_req);
                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                    }else{
                        MyUtils.setObjectAnimator(disremember_business_pass_weitanchuan,
                                disremember_business_pass_weitanchuan_img,
                                disremember_business_pass_weitanchuan_text,
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
                MyUtils.setObjectAnimator(disremember_business_pass_weitanchuan,
                        disremember_business_pass_weitanchuan_img,
                        disremember_business_pass_weitanchuan_text,
                        weitanchuan_height,
                        false, "邮箱验证失败！");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== DashApplication.BUJIDE_TO_YEWUPASS_req&&resultCode==DashApplication.BUJIDE_TO_YEWUPASS_res){
            setResult(DashApplication.BUSINESS_TO_DISREM_res);
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
