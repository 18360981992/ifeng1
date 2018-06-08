package com.ifeng_tech.treasuryyitong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;

/**
 *  二维码设置数量
 */
public class Goods_Setting_Num_Activity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout goods_setting_num_Fan;
    private EditText goods_setting_num_shuliang;
    private Button goods_setting_num_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods__setting__num_);
        initView();

        goods_setting_num_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        goods_setting_num_Fan = (RelativeLayout) findViewById(R.id.goods_setting_num_Fan);
        goods_setting_num_shuliang = (EditText) findViewById(R.id.goods_setting_num_shuliang);
        goods_setting_num_btn = (Button) findViewById(R.id.goods_setting_num_btn);

        goods_setting_num_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goods_setting_num_btn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String shuliang = goods_setting_num_shuliang.getText().toString().trim();
        if (TextUtils.isEmpty(shuliang)) {
            Toast.makeText(this, "请输入数量", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = getIntent().putExtra("amount", shuliang);
        setResult(DashApplication.QR_TO_QR2_res,intent);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
