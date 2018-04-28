package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.fragmet.LoginFragment;
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

                getSupportFragmentManager().beginTransaction().replace(R.id.login_framelayout,new LoginFragment()).commit();
            }
        });
        login_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



//        final SlideUnlockView slideUnlockView = (SlideUnlockView) findViewById(R.id.slideUnlockView);
//
//        slideUnlockView.setOnUnLockListener(new SlideUnlockView.OnUnLockListener(){
//
//            @Override
//            public void setUnLocked(boolean lock) {
//                if(lock){
//                    MyUtils.setToast("解锁成功");
//                    slideUnlockView.setVisibility(View.GONE);
//                }else{
//                    MyUtils.setToast("解锁失败");
//                }
//            }
//        });


    }


    private void initView() {
        login_deng = (TextView) findViewById(R.id.login_deng);
        login_zhuce = (TextView) findViewById(R.id.login_zhuce);
        login_FrameLayout = (FrameLayout) findViewById(R.id.login_framelayout);
    }
}
