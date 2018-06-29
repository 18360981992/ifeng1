package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;

public class About_My_Activity extends AppCompatActivity {

    private RelativeLayout about_My_Fan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__my_);
        initView();

        about_My_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        about_My_Fan = (RelativeLayout) findViewById(R.id.about_My_Fan);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
