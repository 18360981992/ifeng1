package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
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

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

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

                MyUtils.setToast("请求网络。。。");
            }
        });

        bind_email_btn2.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                submit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String newEmail = getEmail(email);
        bind_email_youxiang2.setText("邮件已发送到: "+newEmail+"请收到邮件后及时输入验证码");
    }



    private void initView() {
        bind_email_Fan2 = (RelativeLayout) findViewById(R.id.bind_email_Fan2);
        bind_email_youxiang2 = (TextView) findViewById(R.id.bind_email_youxiang2);
        bind_email_duan2 = (EditText) findViewById(R.id.bind_email_duan2);
        bind_email_duan_btn2 = (TextView) findViewById(R.id.bind_email_duan_btn2);
        bind_email_btn2 = (Button) findViewById(R.id.bind_email_btn2);
        bind_email_weitanchuan_img2 = (ImageView) findViewById(R.id.bind_email_weitanchuan_img2);
        bind_email_weitanchuan_text2 = (TextView) findViewById(R.id.bind_email_weitanchuan_text2);
        bind_email_weitanchuan2 = (LinearLayout) findViewById(R.id.bind_email_weitanchuan2);

        SoftHideKeyBoardUtil.assistActivity(this);

//        bind_email_btn2.setOnClickListener(this);

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

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.bind_email_btn2:
//
//                break;
//        }
//    }



    private void submit() {
        // validate
        String duan2 = bind_email_duan2.getText().toString().trim();
        if (TextUtils.isEmpty(duan2)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        MyUtils.setToast("点击确认，请求网络。。。");

        if(true){
            MyUtils.setObjectAnimator_anquan(bind_email_weitanchuan2,
                    bind_email_weitanchuan_img2,
                    bind_email_weitanchuan_text2,
                    weitanchuan_height,
                    true, "绑定邮箱成功,2秒跳回...");
            MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                @Override
                public void chuan() {

                    setResult(DashApplication.EMAIL1_TO_EMAIL2_res);
                    finish();
                }
            });

        }else{
            MyUtils.setObjectAnimator(bind_email_weitanchuan2,
                    bind_email_weitanchuan_img2,
                    bind_email_weitanchuan_text2,
                    weitanchuan_height,
                    false, "绑定邮箱失败!");
        }

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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }


}
