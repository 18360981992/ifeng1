package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

/**
 * 找回密码
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

        sp = getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();
        String shouji = sp.getString("shouji", "");

        // 模拟一个手机号
        shouji = "18360981992";
        String tou = shouji.substring(0, 3);
        String wei = shouji.substring(8, shouji.length() - 1);
        retrieve_shoujihao.setText(tou+"*****"+wei);


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

    private void initView() {
        retrieve_Fan = (RelativeLayout) findViewById(R.id.retrieve_Fan);
        retrieve_duan = (EditText) findViewById(R.id.retrieve_duan);
        retrieve_duan_btn = (TextView) findViewById(R.id.retrieve_duan_btn);
        retrieve_btn = (Button) findViewById(R.id.retrieve_btn);

        retrieve_btn.setOnClickListener(this);
        retrieve_shoujihao = (TextView) findViewById(R.id.retrieve_shoujihao);
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
        String duan = retrieve_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "短信验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        MyUtils.setToast("请求网络。。。");

        if(true){
            Intent intent = new Intent(Retrieve_Activity.this, Forget_Activity.class);
            startActivityForResult(intent, DashApplication.RETRIEVE_TO_FORGET_req);
        }else{

        }

    }
}
