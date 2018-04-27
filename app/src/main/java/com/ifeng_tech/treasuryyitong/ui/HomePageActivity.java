package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.fragmet.AuthenticateFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.CollectFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.HomeFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.MyFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.TreasuryFragmet;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

public class HomePageActivity extends BaseMVPActivity<HomePageActivity,MyPresenter<HomePageActivity>> {

    private TextView homepage_title;
    private FrameLayout homepage_FrameLayout;
    private ImageView shouyeImg;
    private TextView shouyeName;
    private LinearLayout shouye;
    private ImageView jiandingImg;
    private TextView jiandingName;
    private LinearLayout jianding;
    private ImageView zhengjiImg;
    private TextView zhengjiName;
    private LinearLayout zhengji;
    private ImageView baokuImg;
    private TextView baokuName;
    private LinearLayout baoku;
    private ImageView wodeImg;
    private TextView wodeName;
    private LinearLayout wode;
    private FragmentManager fragmentManager;

    HomeFragmet homeFragmet = new HomeFragmet();  // 首页
    TreasuryFragmet treasuryFragmet = new TreasuryFragmet();  // 鉴定
    CollectFragmet collectFragmet = new CollectFragmet(); // 征集
    AuthenticateFragmet authenticateFragmet = new AuthenticateFragmet();  // 宝库
    MyFragmet myFragmet = new MyFragmet();  // 我的
    private ImageView homepage_xiaoxi;

    @Override
    public MyPresenter<HomePageActivity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initView();


        setBeiJing(true,false,false,false,false);

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.homepage_FrameLayout,homeFragmet)
                .add(R.id.homepage_FrameLayout,treasuryFragmet)
                .add(R.id.homepage_FrameLayout,collectFragmet)
                .add(R.id.homepage_FrameLayout,authenticateFragmet)
                .add(R.id.homepage_FrameLayout,myFragmet)
                .show(homeFragmet)
                .hide(treasuryFragmet)
                .hide(collectFragmet)
                .hide(authenticateFragmet)
                .hide(myFragmet)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(true,false,false,false,false);
                fragmentManager.beginTransaction()
                        .show(homeFragmet)
                        .hide(treasuryFragmet)
                        .hide(collectFragmet)
                        .hide(authenticateFragmet)
                        .hide(myFragmet)
                        .commit();
            }
        });

        jianding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(false,true,false,false,false);
                fragmentManager.beginTransaction()
                        .hide(homeFragmet)
                        .show(treasuryFragmet)
                        .hide(collectFragmet)
                        .hide(authenticateFragmet)
                        .hide(myFragmet)
                        .commit();
            }
        });

        zhengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(false,false,true,false,false);
                fragmentManager.beginTransaction()
                        .hide(homeFragmet)
                        .hide(treasuryFragmet)
                        .show(collectFragmet)
                        .hide(authenticateFragmet)
                        .hide(myFragmet)
                        .commit();
            }
        });

        baoku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(false,false,false,true,false);
                fragmentManager.beginTransaction()
                        .hide(homeFragmet)
                        .hide(treasuryFragmet)
                        .hide(collectFragmet)
                        .show(authenticateFragmet)
                        .hide(myFragmet)
                        .commit();
            }
        });

        wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(false,false,false,false,true);
                fragmentManager.beginTransaction()
                        .hide(homeFragmet)
                        .hide(treasuryFragmet)
                        .hide(collectFragmet)
                        .hide(authenticateFragmet)
                        .show(myFragmet)
                        .commit();
            }
        });

        homepage_xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("点击了消息。。。");
            }
        });

    }

    //主页面点击切换视图
    public void setBeiJing(boolean syFlag, boolean jdFlag, boolean zjFlag,boolean bkFlag,boolean wdFlag) {
        if (syFlag) {
            shouyeImg.setImageResource(R.drawable.shouye_lan);
            shouyeName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("存管系统");
        } else {
            shouyeImg.setImageResource(R.drawable.shouye_hui);
            shouyeName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
        if (jdFlag) {
            jiandingImg.setImageResource(R.drawable.jianding_lan);
            jiandingName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("托管预约");
        } else {
            jiandingImg.setImageResource(R.drawable.jianding_hui);
            jiandingName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
        if (zjFlag) {
            zhengjiImg.setImageResource(R.drawable.zhengji_lan);
            zhengjiName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("线上征集");
        } else {
            zhengjiImg.setImageResource(R.drawable.zhengji_hui);
            zhengjiName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
        if (bkFlag) {
            baokuImg.setImageResource(R.drawable.baoku_lan);
            baokuName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("宝库浏览器");
        } else {
            baokuImg.setImageResource(R.drawable.baoku_hui);
            baokuName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
        if (wdFlag) {
            wodeImg.setImageResource(R.drawable.wode_lan);
            wodeName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("个人中心");
        } else {
            wodeImg.setImageResource(R.drawable.wode_hui);
            wodeName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
    }


    private void initView() {
        homepage_title = (TextView) findViewById(R.id.homepage_title);
        homepage_FrameLayout = (FrameLayout) findViewById(R.id.homepage_FrameLayout);
        shouyeImg = (ImageView) findViewById(R.id.shouyeImg);
        shouyeName = (TextView) findViewById(R.id.shouyeName);
        shouye = (LinearLayout) findViewById(R.id.shouye);
        jiandingImg = (ImageView) findViewById(R.id.jiandingImg);
        jiandingName = (TextView) findViewById(R.id.jiandingName);
        jianding = (LinearLayout) findViewById(R.id.jianding);
        zhengjiImg = (ImageView) findViewById(R.id.zhengjiImg);
        zhengjiName = (TextView) findViewById(R.id.zhengjiName);
        zhengji = (LinearLayout) findViewById(R.id.zhengji);
        baokuImg = (ImageView) findViewById(R.id.baokuImg);
        baokuName = (TextView) findViewById(R.id.baokuName);
        baoku = (LinearLayout) findViewById(R.id.baoku);
        wodeImg = (ImageView) findViewById(R.id.wodeImg);
        wodeName = (TextView) findViewById(R.id.wodeName);
        wode = (LinearLayout) findViewById(R.id.wode);
        homepage_xiaoxi = (ImageView) findViewById(R.id.homepage_xiaoxi);
    }
}
