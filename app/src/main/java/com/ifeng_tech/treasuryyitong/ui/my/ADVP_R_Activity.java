package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;

/**
 * 认证结果
 */
public class ADVP_R_Activity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout advp_r_Fan;
    private LinearLayout advp_r_zhong;
    private Button advp_r_btn;
    private RelativeLayout advp_r_shibai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advp__r_);
        initView();

        advp_r_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(DashApplication.CERTIFICATION_TO_ADVP_res);
                finish();
            }
        });

        Intent intent = getIntent();
        int rengzheng_type = intent.getIntExtra("rengzheng_type", 0);

        if (rengzheng_type == 1) {  // 根据状态 选择隐藏/显示  1== 认证中 2==认证失败
            advp_r_zhong.setVisibility(View.VISIBLE);
            advp_r_shibai.setVisibility(View.GONE);
        } else {
            advp_r_zhong.setVisibility(View.GONE);
            advp_r_shibai.setVisibility(View.VISIBLE);
        }

    }

    private void initView() {
        advp_r_Fan = (RelativeLayout) findViewById(R.id.advp_r_Fan);
        advp_r_zhong = (LinearLayout) findViewById(R.id.advp_r_zhong);
        advp_r_zhong.setOnClickListener(this);
        advp_r_btn = (Button) findViewById(R.id.advp_r_btn);
        advp_r_btn.setOnClickListener(this);
        advp_r_shibai = (RelativeLayout) findViewById(R.id.advp_r_shibai);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.advp_r_btn:
                Intent intent1 = new Intent(ADVP_R_Activity.this, Certification_Activity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }

    /**
     *  点击手机自身的返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            setResult(DashApplication.CERTIFICATION_TO_ADVP_res);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
