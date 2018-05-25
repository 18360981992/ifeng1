package com.ifeng_tech.treasuryyitong.ui.my;

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

/**
 * 更改手机
 */
public class Change_Activity extends BaseMVPActivity<Change_Activity,MyPresenter<Change_Activity>> {

    private RelativeLayout change_Fan;
    private EditText change_new_phone;
    private EditText change_duan;
    private TextView change_duan_btn;
    private ImageView change_weitanchuan_img;
    private TextView change_weitanchuan_text;
    private LinearLayout change_weitanchuan;
    private Button change_btn;
    private int weitanchuan_height;

    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (time == 0) {
                time = 60;
                change_duan_btn.setText("点击发送");
                change_duan_btn.setEnabled(true);
            } else {
                change_duan_btn.setText("重新发送" + time + "(s)");
                h.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    @Override
    public MyPresenter<Change_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_);
        initView();

        change_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        change_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_duan_btn.setText("重新发送" + time + "(s)");
                change_duan_btn.setEnabled(false);
                h.sendEmptyMessageDelayed(0, 1000);

                MyUtils.setToast("请求网络。。。");
            }
        });

        change_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeMessages(0);  // 避免内存泄漏
    }

    private void initView() {
        change_Fan = (RelativeLayout) findViewById(R.id.change_Fan);
        change_new_phone = (EditText) findViewById(R.id.change_new_phone);
        change_duan = (EditText) findViewById(R.id.change_duan);
        change_duan_btn = (TextView) findViewById(R.id.change_duan_btn);
        change_weitanchuan_img = (ImageView) findViewById(R.id.change_weitanchuan_img);
        change_weitanchuan_text = (TextView) findViewById(R.id.change_weitanchuan_text);
        change_weitanchuan = (LinearLayout) findViewById(R.id.change_weitanchuan);
        change_btn = (Button) findViewById(R.id.change_btn);


        //通过设置监听来获取 微弹窗 控件的高度
        change_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                change_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = change_weitanchuan.getMeasuredHeight();
            }
        });
    }


    private void submit() {
        // validate
        String phone = change_new_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "输入新手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isPhoneNumber(phone)==false){
            Toast.makeText(this, "输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String duan = change_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        MyUtils.setToast("请求网络。。。");

        if (true) {

            MyUtils.setObjectAnimator_anquan(change_weitanchuan,
                    change_weitanchuan_img,
                    change_weitanchuan_text,
                    weitanchuan_height,
                    true,"手机号绑定成功,2秒跳回...");
            MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                @Override
                public void chuan() {
                    setResult(DashApplication.RETRIEVE_TO_CHANGE_res);
                    finish();
                }
            });

        } else {
            MyUtils.setObjectAnimator(change_weitanchuan,
                    change_weitanchuan_img,
                    change_weitanchuan_text,
                    weitanchuan_height,
                    false,"手机号绑定失败!");
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
