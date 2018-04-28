package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.fragmet.LoginFragment;
import com.ifeng_tech.treasuryyitong.fragmet.RegisterFragment;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;

/**
 * 登录
 */
public class LoginActivity extends BaseMVPActivity<LoginActivity,MyPresenter<LoginActivity>> {

    private TextView login_deng;
    private TextView login_zhuce;
    private FrameLayout login_FrameLayout;

    @Override
    public MyPresenter<LoginActivity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        getSupportFragmentManager().beginTransaction().replace(R.id.login_framelayout,new LoginFragment()).commit();

        login_deng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_deng.setTextColor(getResources().getColor(R.color.name_se));
                login_zhuce.setTextColor(getResources().getColor(R.color.zhuse_ziti));
                getSupportFragmentManager().beginTransaction().replace(R.id.login_framelayout,new LoginFragment()).commit();
            }
        });
        login_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_deng.setTextColor(getResources().getColor(R.color.zhuse_ziti));
                login_zhuce.setTextColor(getResources().getColor(R.color.name_se));
                getSupportFragmentManager().beginTransaction().replace(R.id.login_framelayout,new RegisterFragment()).commit();
            }
        });


    }


    private void initView() {
        login_deng = (TextView) findViewById(R.id.login_deng);
        login_zhuce = (TextView) findViewById(R.id.login_zhuce);
        login_FrameLayout = (FrameLayout) findViewById(R.id.login_framelayout);
    }
}
