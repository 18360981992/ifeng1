package com.ifeng_tech.treasuryyitong.ui;

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

/**
 * 注册
 */
public class Login_Register_Activity extends BaseMVPActivity<Login_Register_Activity, MyPresenter<Login_Register_Activity>> implements View.OnClickListener {

    private RelativeLayout register_Fan;
    private EditText register_name;
    private EditText register_duan;
    private TextView register_duan_btn;
    private EditText register_pass;
    private EditText register_zaici_pass;
    private ImageView register_fuxuan;
    private TextView register_yuedu_text;
    private Button register_btn;

    boolean fuxuan = false;
    private ImageView login_register_weitanchuan_img;
    private TextView login_register_weitanchuan_text;
    private LinearLayout login_register_weitanchuan;
    private int weitanchuan_height;

    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (time == 0) {
                time = 60;
                register_duan_btn.setText("获取验证码");
                register_duan_btn.setEnabled(true);
                register_duan_btn.setTextColor(getResources().getColor(R.color.zhuse));
            } else {
                register_duan_btn.setText("重新发送" + time + "(s)");
                h.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };


    @Override
    public MyPresenter<Login_Register_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        initView();

        register_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 复选框的点击
        register_fuxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fuxuan) {
                    fuxuan = false;
                    register_fuxuan.setImageResource(R.drawable.zhuce_hui);
                } else {
                    fuxuan = true;
                    register_fuxuan.setImageResource(R.drawable.zhuce_lan);
                }
            }
        });
        // 短信验证码的点击
        register_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_duan_btn.setText("重新发送" + time + "(s)");
                register_duan_btn.setEnabled(false);
                register_duan_btn.setTextColor(getResources().getColor(R.color.name_se));
                h.sendEmptyMessageDelayed(0, 1000);

                MyUtils.setToast("请求网络。。。");
            }
        });

    }

    private void initView() {
        register_Fan = (RelativeLayout) findViewById(R.id.register_Fan);
        register_name = (EditText) findViewById(R.id.register_name);
        register_duan = (EditText) findViewById(R.id.register_duan);
        register_duan_btn = (TextView) findViewById(R.id.register_duan_btn);
        register_pass = (EditText) findViewById(R.id.register_pass);
        register_zaici_pass = (EditText) findViewById(R.id.register_zaici_pass);
        register_fuxuan = (ImageView) findViewById(R.id.register_fuxuan);
        register_yuedu_text = (TextView) findViewById(R.id.register_yuedu_text);
        register_btn = (Button) findViewById(R.id.register_btn);
        login_register_weitanchuan_img = (ImageView) findViewById(R.id.login_register_weitanchuan_img);
        login_register_weitanchuan_text = (TextView) findViewById(R.id.login_register_weitanchuan_text);
        login_register_weitanchuan = (LinearLayout) findViewById(R.id.login_register_weitanchuan);

        register_btn.setOnClickListener(this);

        SoftHideKeyBoardUtil.assistActivity(this);

        register_fuxuan.setImageResource(R.drawable.zhuce_hui);  // 初始值

        //通过设置监听来获取 微弹窗 控件的高度
        login_register_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                login_register_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = login_register_weitanchuan.getMeasuredHeight();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:

                submit();

                break;
        }
    }

    private void submit() {
        // validate
        String name = register_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }

        String duan = register_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String pass = register_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String zaipass = register_zaici_pass.getText().toString().trim();
        if (TextUtils.isEmpty(zaipass)) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (fuxuan == false) {
            Toast.makeText(this, "请勾选条约", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something

        MyUtils.setToast("请求网络。。。");
        if (true) {
            Intent intent = getIntent();
            intent.putExtra("name",name);
            intent.putExtra("pass",pass);
            setResult(DashApplication.LOGIN_TO_REGISTER_res,intent);
            finish();
        } else {
            MyUtils.setObjectAnimator(login_register_weitanchuan,
                    login_register_weitanchuan_img,
                    login_register_weitanchuan_text,
                    weitanchuan_height,
                    false, "注册失败!");
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        fuxuan=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeMessages(0);
    }
}
