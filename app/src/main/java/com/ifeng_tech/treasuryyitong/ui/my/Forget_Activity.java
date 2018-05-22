package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Build;
import android.os.Bundle;
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

/**
 *  找回密码
 */
public class Forget_Activity extends BaseMVPActivity<Forget_Activity,MyPresenter<Forget_Activity>> implements View.OnClickListener {

    private RelativeLayout forget_Fan;
    private EditText forget_new;
    private EditText forget_queren;
    private ImageView forget_weitanchuan_img;
    private TextView forget_weitanchuan_text;
    private LinearLayout forget_weitanchuan;
    private Button forget_btn;
    private int weitanchuan_height;

    @Override
    public MyPresenter<Forget_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_);
        initView();

        forget_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initView() {
        forget_Fan = (RelativeLayout) findViewById(R.id.forget_Fan);
        forget_new = (EditText) findViewById(R.id.forget_new);
        forget_queren = (EditText) findViewById(R.id.forget_queren);
        forget_weitanchuan_img = (ImageView) findViewById(R.id.forget_weitanchuan_img);
        forget_weitanchuan_text = (TextView) findViewById(R.id.forget_weitanchuan_text);
        forget_weitanchuan = (LinearLayout) findViewById(R.id.forget_weitanchuan);
        forget_btn = (Button) findViewById(R.id.forget_btn);

        forget_btn.setOnClickListener(this);

        //通过设置监听来获取 微弹窗 控件的高度
        forget_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                forget_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = forget_weitanchuan.getMeasuredHeight();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forget_btn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String news = forget_new.getText().toString().trim();
        if (TextUtils.isEmpty(news)) {
            Toast.makeText(this, "新登录密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String queren = forget_queren.getText().toString().trim();
        if (TextUtils.isEmpty(queren)) {
            Toast.makeText(this, "新登录密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        MyUtils.setToast("请求网络。。。");

        if(true){
            MyUtils.setObjectAnimator_anquan(forget_weitanchuan,
                    forget_weitanchuan_img,
                    forget_weitanchuan_text,
                    weitanchuan_height,
                    true, "找回密码成功!");

            // 微弹窗消失后的接口回调
            MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                @Override
                public void chuan() {
                    setResult(DashApplication.RETRIEVE_TO_FORGET_res);
                    finish();
                }
            });
        }else{
            MyUtils.setObjectAnimator(forget_weitanchuan,
                    forget_weitanchuan_img,
                    forget_weitanchuan_text,
                    weitanchuan_height,
                    false, "找回密码失败!");
        }

    }
}
