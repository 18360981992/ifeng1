package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;

/**
 *  安全保护
 */
public class Safe_Activity extends BaseMVPActivity<Safe_Activity,MyPresenter<Safe_Activity>> {

    private RelativeLayout safe_Fan;
    private ImageView img1;
    private RelativeLayout safe_chongzhi;
    private ImageView img2;
    private RelativeLayout safe_zhaohui;
    private ImageView img3;
    private RelativeLayout safe_geng_shouji;

    @Override
    public MyPresenter<Safe_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_);
        initView();

        safe_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        safe_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击重置。。。");
                Intent intent = new Intent(Safe_Activity.this, Reset_Activity.class);
                startActivity(intent);
            }
        });

        safe_zhaohui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击找回。。。");
                Intent intent = new Intent(Safe_Activity.this, Retrieve_Activity.class);
                startActivity(intent);
            }
        });

        safe_geng_shouji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击更改。。。");
                Intent intent = new Intent(Safe_Activity.this, Change_Activity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        safe_Fan = (RelativeLayout) findViewById(R.id.safe_Fan);
        img1 = (ImageView) findViewById(R.id.img1);
        safe_chongzhi = (RelativeLayout) findViewById(R.id.safe_chongzhi);
        img2 = (ImageView) findViewById(R.id.img2);
        safe_zhaohui = (RelativeLayout) findViewById(R.id.safe_zhaohui);
        img3 = (ImageView) findViewById(R.id.img3);
        safe_geng_shouji = (RelativeLayout) findViewById(R.id.safe_geng_shouji);
    }
}
