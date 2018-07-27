package com.ifeng_tech.treasuryyitong.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.login.LoginNewBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.Conceal_Activity;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 设置登录密码
 */
public class Setting_Pass_Activity extends BaseMVPActivity<Setting_Pass_Activity, MyPresenter<Setting_Pass_Activity>> {

    private RelativeLayout setting_Pass_Fan;
    private EditText setting_Pass_pass;
    private EditText setting_Pass_zaici_pass;
    private ImageView setting_Pass_fuxuan;
    private TextView setting_Pass_text;
    private Button setting_Pass_btn;
    boolean fuxuan = false;
    private String shouji;
    private String smsCode;

    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            submit();
        }
    };
    @Override
    public MyPresenter<Setting_Pass_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__pass_);
        initView();

        Intent intent = getIntent();
        shouji = intent.getStringExtra("shouji");
        smsCode = intent.getStringExtra("smsCode");

        setting_Pass_fuxuan.setImageResource(R.drawable.zhuce_hui); // 为复选框设置默认值

        setting_Pass_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 复选框的点击
        setting_Pass_fuxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fuxuan) {
                    fuxuan = false;
                    setting_Pass_fuxuan.setImageResource(R.drawable.zhuce_hui);
                } else {
                    fuxuan = true;
                    setting_Pass_fuxuan.setImageResource(R.drawable.zhuce_lan);
                }
            }
        });

        // 点击隐私政策
        setting_Pass_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting_Pass_Activity.this, Conceal_Activity.class);
                startActivityForResult(intent, DashApplication.REGISTER_TO_CONCEAL_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 点击确认按钮
        setting_Pass_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(setting_Pass_zaici_pass.getWindowToken(), 0);
                h.sendEmptyMessageDelayed(0,200);// 延迟200毫秒 为键盘消失预留时间
            }
        });
    }

    private void initView() {
        setting_Pass_Fan = (RelativeLayout) findViewById(R.id.setting_Pass_Fan);
        setting_Pass_pass = (EditText) findViewById(R.id.setting_Pass_pass);
        setting_Pass_zaici_pass = (EditText) findViewById(R.id.setting_Pass_zaici_pass);
        setting_Pass_fuxuan = (ImageView) findViewById(R.id.setting_Pass_fuxuan);
        setting_Pass_text = (TextView) findViewById(R.id.setting_Pass_text);
        setting_Pass_btn = (Button) findViewById(R.id.setting_Pass_btn);

        SoftHideKeyBoardUtil.assistActivity(this);
    }



    private void submit() {
        // validate
        final String pass = setting_Pass_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if(MyUtils.isPassWord(pass)==false){
            Toast.makeText(this, SP_String.IS_PASS, Toast.LENGTH_SHORT).show();
            return;
        }

        String zaipass = setting_Pass_zaici_pass.getText().toString().trim();
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

        HashMap<String, String> map = new HashMap<>();
        map.put("mobile",shouji);
        map.put("smsCode",""+smsCode);
        map.put("password",pass);
        map.put("rePassword",zaipass);
        map.put("token", DashApplication.android);
        myPresenter.postPreContent(APIs.register, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        LoginNewBean loginNewBean = new Gson().fromJson(json, LoginNewBean.class);
                        DashApplication.edit
                                .putString(SP_String.SHOUJI, shouji)
                                .putString(SP_String.PASS,pass)
                                .putString(SP_String.TOKEN,DashApplication.android)
                                .putBoolean(SP_String.ISLOGIN,true)
                                .putString(SP_String.UID,loginNewBean.getData().getUser().getId()+"")
                                .putString(SP_String.USERCODE,loginNewBean.getData().getUser().getUserCode()+"")
                                .commit();

                        setTagAndAlias(loginNewBean.getData().getUser().getId()+"");  // 注册极光的别名

                        Intent intent = new Intent(Setting_Pass_Activity.this, HomePageActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);

                        setResult(DashApplication.LOGINNEW_TO_SETTINGPASS_res);
                        finish();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DashApplication.REGISTER_TO_CONCEAL_req&&resultCode==DashApplication.REGISTER_TO_CONCEAL_res){
            fuxuan = true;
            setting_Pass_fuxuan.setImageResource(R.drawable.zhuce_lan);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        fuxuan=false;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
