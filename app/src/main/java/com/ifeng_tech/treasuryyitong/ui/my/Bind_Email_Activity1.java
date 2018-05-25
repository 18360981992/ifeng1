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
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

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
            time--;
            if (time == 0) {
                time = 60;
                bind_email_duan_btn.setText("点击发送");
                bind_email_duan_btn.setEnabled(true);
            } else {
                bind_email_duan_btn.setText("重新发送" + time + "(s)");
                h.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };
    private int weitanchuan_height;

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

        bind_email_tu_yan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("点击了图形验证。。。");
            }
        });

        // 获取短信验证
        bind_email_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bind_email_duan_btn.setText("重新发送" + time + "(s)");
                bind_email_duan_btn.setEnabled(false);
                h.sendEmptyMessageDelayed(0, 1000);

                MyUtils.setToast("请求网络。。。");
            }
        });

        // 自定义点击 “下一步” 按钮
        bind_email_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                submit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String shouji = DashApplication.sp.getString(SP_String.SHOUJI, "");
        // 模拟一个手机号
        shouji = "18360981992";
        String tou = shouji.substring(0, 3);
        String wei = shouji.substring(8, shouji.length());
        bind_email_shoujihao.setText(tou + "*****" + wei);


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

//        bind_email_btn.setOnClickListener(this);
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

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.bind_email_btn:
//
//                break;
//        }
//    }

    private void submit() {
        // validate
        String youxiang = bind_email_youxiang.getText().toString().trim();
        if (TextUtils.isEmpty(youxiang)) {
            Toast.makeText(this, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isEmail(youxiang)==false){
            MyUtils.setToast("请输入正确的邮箱");
            return;
        }

        String yan = bind_email_tu_yan.getText().toString().trim();
        if (TextUtils.isEmpty(yan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String duan = bind_email_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        MyUtils.setToast("点击下一步，请求网络。。。");

        if(true){
            Intent intent = new Intent(Bind_Email_Activity1.this, Bind_Email_Activity2.class);
            intent.putExtra("email",youxiang);
            startActivityForResult(intent,DashApplication.EMAIL1_TO_EMAIL2_req);
        }else{
            MyUtils.setObjectAnimator(bind_email_weitanchuan,
                    bind_email_weitanchuan_img,
                    bind_email_weitanchuan_text,
                    weitanchuan_height,
                    false, "绑定邮箱失败!");
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
