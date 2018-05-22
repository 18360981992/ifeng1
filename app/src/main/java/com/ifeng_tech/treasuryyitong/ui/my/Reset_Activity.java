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
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

/**
 * 重置密码
 */
public class Reset_Activity extends BaseMVPActivity<Reset_Activity, MyPresenter<Reset_Activity>> implements View.OnClickListener {

    private RelativeLayout reset_Fan;
    private EditText reset_old;
    private EditText reset_new;
    private EditText reset_queren;
    private Button reset_btn;
    private ImageView reset_weitanchuan_img;
    private TextView reset_weitanchuan_text;
    private LinearLayout reset_weitanchuan;
    private int weitanchuan_height;

    @Override
    public MyPresenter<Reset_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_);
        initView();

        reset_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        reset_Fan = (RelativeLayout) findViewById(R.id.reset_Fan);
        reset_old = (EditText) findViewById(R.id.reset_old);
        reset_new = (EditText) findViewById(R.id.reset_new);
        reset_queren = (EditText) findViewById(R.id.reset_queren);
        reset_btn = (Button) findViewById(R.id.reset_btn);

        reset_btn.setOnClickListener(this);
        reset_weitanchuan_img = (ImageView) findViewById(R.id.reset_weitanchuan_img);
        reset_weitanchuan_text = (TextView) findViewById(R.id.reset_weitanchuan_text);
        reset_weitanchuan = (LinearLayout) findViewById(R.id.reset_weitanchuan);

        //通过设置监听来获取 微弹窗 控件的高度
        reset_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                reset_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = reset_weitanchuan.getMeasuredHeight();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_btn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String old = reset_old.getText().toString().trim();
        if (TextUtils.isEmpty(old)) {
            Toast.makeText(this, "原登录密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String news = reset_new.getText().toString().trim();
        if (TextUtils.isEmpty(news)) {
            Toast.makeText(this, "新登录密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String queren = reset_queren.getText().toString().trim();
        if (TextUtils.isEmpty(queren)) {
            Toast.makeText(this, "确认新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        MyUtils.setToast("请求网络。。。");

        if (true) {

            MyUtils.setObjectAnimator_anquan(reset_weitanchuan,
                    reset_weitanchuan_img,
                    reset_weitanchuan_text,
                    weitanchuan_height,
                    true,"修改成功!");
            MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                @Override
                public void chuan() {
                    finish();
                }
            });

        } else {
            MyUtils.setObjectAnimator(reset_weitanchuan,
                    reset_weitanchuan_img,
                    reset_weitanchuan_text,
                    weitanchuan_height,
                    false,"修改失败!");
        }

    }

}
