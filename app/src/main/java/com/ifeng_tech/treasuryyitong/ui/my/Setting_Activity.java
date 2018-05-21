package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

public class Setting_Activity extends BaseMVPActivity<Setting_Activity,MyPresenter<Setting_Activity>> implements View.OnClickListener {

    private RelativeLayout setting_Fan;
    private RelativeLayout setting_guanyu;
    private RelativeLayout setting_banben;
    private Button setting_btn;

    @Override
    public MyPresenter<Setting_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_);
        initView();

        setting_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setting_guanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("点击了关于我们。。。");
            }
        });

        setting_banben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("检索版本。。。");
            }
        });
    }

    private void initView() {
        setting_Fan = (RelativeLayout) findViewById(R.id.setting_Fan);
        setting_guanyu = (RelativeLayout) findViewById(R.id.setting_guanyu);
        setting_banben = (RelativeLayout) findViewById(R.id.setting_banben);
        setting_btn = (Button) findViewById(R.id.setting_btn);

        setting_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_btn:
                MyUtils.setToast("退出登录。。。");
                break;
        }
    }
}
