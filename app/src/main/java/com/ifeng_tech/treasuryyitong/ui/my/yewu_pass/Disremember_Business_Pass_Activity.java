package com.ifeng_tech.treasuryyitong.ui.my.yewu_pass;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;

/**
 * 不记得业务密码的绑定邮箱
 */
public class Disremember_Business_Pass_Activity extends BaseMVPActivity<Disremember_Business_Pass_Activity, MyPresenter<Disremember_Business_Pass_Activity>> implements View.OnClickListener {

    private RelativeLayout disremember_business_pass_Fan;
    private RelativeLayout authenticate_Details_Toobar;
    private TextView disremember_business_pass_youxiang;
    private EditText disremember_business_pass_duan;
    private TextView disremember_business_pass_duan_btn;
    private Button disremember_business_pass_btn;
    private ImageView disremember_business_pass_weitanchuan_img;
    private TextView disremember_business_pass_weitanchuan_text;
    private LinearLayout disremember_business_pass_weitanchuan;

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

        String email = DashApplication.sp.getString(SP_String.EMAIL, "");
        String newEmail = getEmail(email);
        disremember_business_pass_youxiang.setText("邮件已发送到: "+newEmail+"请收到邮件后及时输入验证码");

    }

    // 获取拼接的email
    public String getEmail(String email){
        String tou = email.substring(0, 3);
        String wei = email.substring(email.length()-9, email.length());
        int i=email.length()-9-3-1;  // 获取“*”的个数
        String ss="";
        for (int j=0;j<i;j++){
            ss=ss+"*";
        }
        String newEmail=tou+ss+wei;
        return newEmail;
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

        disremember_business_pass_btn.setOnClickListener(this);

        SoftHideKeyBoardUtil.assistActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.disremember_business_pass_btn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String duan = disremember_business_pass_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
