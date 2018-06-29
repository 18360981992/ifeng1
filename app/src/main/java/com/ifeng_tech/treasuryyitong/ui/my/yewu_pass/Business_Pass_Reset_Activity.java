package com.ifeng_tech.treasuryyitong.ui.my.yewu_pass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *  业务密码重置  根据状态值判断是否跳到改页面  记得/不记得
 */
public class Business_Pass_Reset_Activity extends BaseMVPActivity<Business_Pass_Reset_Activity,MyPresenter<Business_Pass_Reset_Activity>> implements View.OnClickListener {

    private RelativeLayout business_pass_reset_Fan;
    private Button business_pass_reset_bujide;
    private Button business_pass_reset_jide;

    @Override
    public MyPresenter<Business_Pass_Reset_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business__pass__reset_);
        initView();

        business_pass_reset_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        business_pass_reset_Fan = (RelativeLayout) findViewById(R.id.business_pass_reset_Fan);
        business_pass_reset_bujide = (Button) findViewById(R.id.business_pass_reset_bujide);
        business_pass_reset_jide = (Button) findViewById(R.id.business_pass_reset_jide);

        business_pass_reset_bujide.setOnClickListener(this);
        business_pass_reset_jide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.business_pass_reset_bujide:  // 不记得 跳到绑定邮箱 再到重置
                // 点击不记得的时候先将邮箱验证码发走

                String email = DashApplication.sp.getString(SP_String.EMAIL, "");
                HashMap<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("type","8");
                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Business_Pass_Reset_Activity.this, SP_String.JIAZAI);

                myPresenter.postPreContent(APIs.sendMailAgain, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                Intent intent = new Intent(Business_Pass_Reset_Activity.this, Disremember_Business_Pass_Activity.class);
                                startActivityForResult(intent, DashApplication.BUSINESS_TO_DISREM_req);
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
                        MyUtils.setToast("邮件发送失败！");
                    }
                });

                break;
            case R.id.business_pass_reset_jide:  // 点击记得业务密码
                Intent intent1 = new Intent(Business_Pass_Reset_Activity.this, Remember_Business_Pass_Activity.class);
                startActivityForResult(intent1, DashApplication.BUSINESS_TO_REMEM_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //  不记得业务密码  到邮箱验证
        if(requestCode==DashApplication.BUSINESS_TO_DISREM_req&resultCode==DashApplication.BUSINESS_TO_DISREM_res){
            finish();

        }else if(requestCode==DashApplication.BUSINESS_TO_REMEM_req&&resultCode==DashApplication.BUSINESS_TO_REMEM_res){

            // 记得业务密码
            finish();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
