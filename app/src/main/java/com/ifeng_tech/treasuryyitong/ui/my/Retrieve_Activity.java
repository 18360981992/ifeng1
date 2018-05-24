package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.content.SharedPreferences;
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
 * 手机验证页面 用于 找回密码 与 更改绑定手机
 */
public class Retrieve_Activity extends BaseMVPActivity<Retrieve_Activity, MyPresenter<Retrieve_Activity>> implements View.OnClickListener {

    private RelativeLayout retrieve_Fan;
    private EditText retrieve_duan;
    private TextView retrieve_duan_btn;
    private Button retrieve_btn;

    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if (time == 0) {
                time = 60;
                retrieve_duan_btn.setText("点击发送");
                retrieve_duan_btn.setEnabled(true);
            } else {
                retrieve_duan_btn.setText("重新发送" + time + "(s)");
                h.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private TextView retrieve_shoujihao;
    private ImageView retrieve_weitanchuan_img;
    private TextView retrieve_weitanchuan_text;
    private LinearLayout retrieve_weitanchuan;
    private int weitanchuan_height;
    private int type;
    private EditText retrieve_shoujihao_shuru;
    private int select;

    @Override
    public MyPresenter<Retrieve_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_);
        initView();

        Intent intent = getIntent();
        /**
         *  因为这个页面被重复使用了3次  所以逻辑要。。。
         */
        type = intent.getIntExtra("type", 0); // 用于识别是哪个验证  分为找回密码==1，更改手机==2
        // 用于判断手机号的隐藏/显示
        select = intent.getIntExtra("select", 0);

        if (select == 1) {
            retrieve_shoujihao.setVisibility(View.GONE);
            retrieve_shoujihao_shuru.setVisibility(View.VISIBLE);

        } else {

            retrieve_shoujihao.setVisibility(View.VISIBLE);
            retrieve_shoujihao_shuru.setVisibility(View.GONE);

            String shouji = DashApplication.sp.getString("shouji", "");

            // 模拟一个手机号
            shouji = "18360981992";
            String tou = shouji.substring(0, 3);
            String wei = shouji.substring(8, shouji.length());
            retrieve_shoujihao.setText(tou + "*****" + wei);
        }


        retrieve_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        retrieve_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieve_duan_btn.setText("重新发送" + time + "(s)");
                retrieve_duan_btn.setEnabled(false);
                h.sendEmptyMessageDelayed(0, 1000);

                MyUtils.setToast("请求网络。。。");
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeMessages(0); // 避免内存泄漏
    }

    private void initView() {
        retrieve_Fan = (RelativeLayout) findViewById(R.id.retrieve_Fan);
        retrieve_duan = (EditText) findViewById(R.id.retrieve_duan);
        retrieve_duan_btn = (TextView) findViewById(R.id.retrieve_duan_btn);
        retrieve_btn = (Button) findViewById(R.id.retrieve_btn);

        retrieve_btn.setOnClickListener(this);
        retrieve_shoujihao = (TextView) findViewById(R.id.retrieve_shoujihao);
        retrieve_weitanchuan_img = (ImageView) findViewById(R.id.retrieve_weitanchuan_img);
        retrieve_weitanchuan_text = (TextView) findViewById(R.id.retrieve_weitanchuan_text);
        retrieve_weitanchuan = (LinearLayout) findViewById(R.id.retrieve_weitanchuan);

        //通过设置监听来获取 微弹窗 控件的高度
        retrieve_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                retrieve_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = retrieve_weitanchuan.getMeasuredHeight();
            }
        });
        retrieve_shoujihao_shuru = (EditText) findViewById(R.id.retrieve_shoujihao_shuru);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DashApplication.RETRIEVE_TO_FORGET_req && resultCode == DashApplication.RETRIEVE_TO_FORGET_res) {
            finish();
        }
        if (requestCode == DashApplication.RETRIEVE_TO_CHANGE_req && resultCode == DashApplication.RETRIEVE_TO_CHANGE_res) {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retrieve_btn:

                submit();

                break;
        }
    }

    private void submit() {
        // validate

        if(select==1){
            String shuru = retrieve_shoujihao_shuru.getText().toString().trim();
            if (TextUtils.isEmpty(shuru)) {
                Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String duan = retrieve_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "短信验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        // TODO validate success, do something
        MyUtils.setToast("看清select的值，携带参数请求网络。。。");

        if (true) {
            if (type == DashApplication.ANQUAN_TYPE_ZHAOHUI) {
                Intent intent = new Intent(Retrieve_Activity.this, Forget_Activity.class);
                startActivityForResult(intent, DashApplication.RETRIEVE_TO_FORGET_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            } else {
                Intent intent = new Intent(Retrieve_Activity.this, Change_Activity.class);
                startActivityForResult(intent, DashApplication.RETRIEVE_TO_CHANGE_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }

        } else {
            MyUtils.setObjectAnimator(retrieve_weitanchuan,
                    retrieve_weitanchuan_img,
                    retrieve_weitanchuan_text,
                    weitanchuan_height,
                    false, "修改失败!");
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
