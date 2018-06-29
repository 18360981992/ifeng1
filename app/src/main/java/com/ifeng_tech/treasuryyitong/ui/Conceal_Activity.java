package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;

/**
 *  隐私政策
 */
public class Conceal_Activity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout conceal_Fan;
    private Button conceal_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conceal_);
        initView();

        conceal_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initView() {
        conceal_Fan = (RelativeLayout) findViewById(R.id.conceal_Fan);
        conceal_btn = (Button) findViewById(R.id.conceal_btn);

        conceal_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.conceal_btn:
                setResult(DashApplication.REGISTER_TO_CONCEAL_res);
                finish();
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
