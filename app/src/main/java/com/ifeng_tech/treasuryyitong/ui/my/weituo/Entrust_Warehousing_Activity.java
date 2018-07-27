package com.ifeng_tech.treasuryyitong.ui.my.weituo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;

/**
 * 委托入库
 */
public class Entrust_Warehousing_Activity extends AppCompatActivity {

    private RelativeLayout entrust_Warehousing_Fan;
    private LinearLayout entrust_Warehousing_shenqing;
    private LinearLayout entrust_Warehousing_liebiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrust__warehousing_);
        initView();

        entrust_Warehousing_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        entrust_Warehousing_shenqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrust_Warehousing_Activity.this, Entrust_PT_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        entrust_Warehousing_liebiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrust_Warehousing_Activity.this, Entrust_List_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }

    private void initView() {
        entrust_Warehousing_Fan = (RelativeLayout) findViewById(R.id.entrust_Warehousing_Fan);
        entrust_Warehousing_shenqing = (LinearLayout) findViewById(R.id.entrust_Warehousing_shenqing);
        entrust_Warehousing_liebiao = (LinearLayout) findViewById(R.id.entrust_Warehousing_liebiao);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
