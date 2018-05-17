package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.fragmet.CollectFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.HomeFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.InformationFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.MessageFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.MyFragmet;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

public class HomePageActivity extends BaseMVPActivity<HomePageActivity,MyPresenter<HomePageActivity>> {

    private TextView homepage_title;
    private RelativeLayout homepage_RelativeLayout;

    private FrameLayout homepage_FrameLayout;
    private ImageView shouyeImg;
    private TextView shouyeName;
    private LinearLayout shouye;
    private ImageView zixunImg;
    private TextView zixunName;
    private LinearLayout zixun;
    private ImageView zhengjiImg;
    private TextView zhengjiName;
    private LinearLayout zhengji;
    private ImageView xiaoxiImg;
    private TextView xiaoxiName;
    private LinearLayout xiaoxi;
    private ImageView wodeImg;
    private TextView wodeName;
    private LinearLayout wode;
    private FragmentManager fragmentManager;

    HomeFragmet homeFragmet = new HomeFragmet();  // 首页
    InformationFragmet treasuryFragmet = new InformationFragmet();  // 资讯
    CollectFragmet collectFragmet = new CollectFragmet(); // 征集
    MessageFragmet authenticateFragmet = new MessageFragmet();  // 消息
    MyFragmet myFragmet = new MyFragmet();  // 我的

    long exitTim=0;

    public interface HomePageActivity_JieKou{
        void chuan(int i);
    }
    public static HomePageActivity_JieKou homePageActivity_JieKou;

    public void setHomePageActivity_JieKou(HomePageActivity_JieKou homePageActivity_JieKou) {
        this.homePageActivity_JieKou = homePageActivity_JieKou;
    }

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

        zixun.setOnClickListener(new View.OnClickListener() {
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

        xiaoxi.setOnClickListener(new View.OnClickListener() {
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

        setHomePageActivity_JieKou(new HomePageActivity_JieKou() {
            @Override
            public void chuan(int i) {
                switch (i){
                    case 0:
                        setBeiJing(false,false,true,false,false);
                        fragmentManager.beginTransaction()
                                .hide(homeFragmet)
                                .hide(treasuryFragmet)
                                .show(collectFragmet)
                                .hide(authenticateFragmet)
                                .hide(myFragmet)
                                .commit();
                        break;
                    case 1:
                        MyUtils.setToast("点击了广告。。。");
                        break;
                    case 2:
                        MyUtils.setToast("点击了托管。。。");
                        break;
                    case 3:
                        setBeiJing(false,true,false,false,false);
                        fragmentManager.beginTransaction()
                                .hide(homeFragmet)
                                .show(treasuryFragmet)
                                .hide(collectFragmet)
                                .hide(authenticateFragmet)
                                .hide(myFragmet)
                                .commit();
                        break;
                }
            }
        });
    }

    //主页面点击切换视图
    public void setBeiJing(boolean syFlag, boolean zxFlag, boolean zjFlag,boolean xxFlag,boolean wdFlag) {
        if (syFlag) {
            shouyeImg.setImageResource(R.drawable.shouye_lan);
            shouyeName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_RelativeLayout.setVisibility(View.GONE);
        } else {
            shouyeImg.setImageResource(R.drawable.shouye_hui);
            shouyeName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
            homepage_RelativeLayout.setVisibility(View.VISIBLE);
        }
        if (zxFlag) {
            zixunImg.setImageResource(R.drawable.zixun_lan);
            zixunName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("资讯");
        } else {
            zixunImg.setImageResource(R.drawable.zixun_hui);
            zixunName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
        if (zjFlag) {
            zhengjiImg.setImageResource(R.drawable.zhengji_lan);
            zhengjiName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("征集");
        } else {
            zhengjiImg.setImageResource(R.drawable.zhengji_hui);
            zhengjiName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
        if (xxFlag) {
            xiaoxiImg.setImageResource(R.drawable.xiaoxi_lan);
            xiaoxiName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("消息");
        } else {
            xiaoxiImg.setImageResource(R.drawable.xiaoxi_hui);
            xiaoxiName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
        if (wdFlag) {
            wodeImg.setImageResource(R.drawable.wode_lan);
            wodeName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("我的");
        } else {
            wodeImg.setImageResource(R.drawable.wode_hui);
            wodeName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
    }


    private void initView() {
        homepage_title = (TextView) findViewById(R.id.homepage_title);
        homepage_RelativeLayout = (RelativeLayout) findViewById(R.id.homepage_RelativeLayout);

        homepage_FrameLayout = (FrameLayout) findViewById(R.id.homepage_FrameLayout);
        shouyeImg = (ImageView) findViewById(R.id.shouyeImg);
        shouyeName = (TextView) findViewById(R.id.shouyeName);
        shouye = (LinearLayout) findViewById(R.id.shouye);
        zixunImg = (ImageView) findViewById(R.id.zixunImg);
        zixunName = (TextView) findViewById(R.id.zixunName);
        zixun = (LinearLayout) findViewById(R.id.zixun);
        zhengjiImg = (ImageView) findViewById(R.id.zhengjiImg);
        zhengjiName = (TextView) findViewById(R.id.zhengjiName);
        zhengji = (LinearLayout) findViewById(R.id.zhengji);
        xiaoxiImg = (ImageView) findViewById(R.id.xiaoxiImg);
        xiaoxiName = (TextView) findViewById(R.id.xiaoxiName);
        xiaoxi = (LinearLayout) findViewById(R.id.xiaoxi);
        wodeImg = (ImageView) findViewById(R.id.wodeImg);
        wodeName = (TextView) findViewById(R.id.wodeName);
        wode = (LinearLayout) findViewById(R.id.wode);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTim) > 2000) {
                MyUtils.setToast("再按一次退出程序");
                exitTim = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
