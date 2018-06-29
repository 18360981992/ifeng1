package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.my.bind_email.Bind_Email_Activity1;
import com.ifeng_tech.treasuryyitong.ui.my.bind_email.Bind_Email_Reset_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.yewu_pass.Business_Pass_Reset_Activity;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

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
    private RelativeLayout safe_yewu_pass;
    private RelativeLayout safe_bind_youxiang;

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
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        safe_zhaohui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击找回。。。");
                Intent intent = new Intent(Safe_Activity.this, Retrieve_Activity.class);
                intent.putExtra("type", DashApplication.ANQUAN_TYPE_ZHAOHUI);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        safe_geng_shouji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击更改手机。。。");
                Intent intent = new Intent(Safe_Activity.this, Retrieve_Activity.class);
                intent.putExtra("type", DashApplication.ANQUAN_TYPE_GENGGAI);  // type == 2
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        safe_yewu_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击业务密码设置。。。");

                String anInt = DashApplication.sp.getString(SP_String.ISUSERYEWUPASS, "");
                if(anInt.equals("0")){     // 0 == 设置过业务密码
                    Intent intent = new Intent(Safe_Activity.this, Business_Pass_Reset_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    Intent intent = new Intent(Safe_Activity.this, Bind_Email_Activity1.class);
                    intent.putExtra("title","业务密码（设置）");
                    intent.putExtra("select",SP_String.YEWUMIMA);   // 产品更改需求，业务密码设置与绑定邮箱是相同的
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });

        safe_bind_youxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击绑定邮箱设置。。。");
                String emali = DashApplication.sp.getString(SP_String.EMAIL, "");
                if(emali.equals("")){
                    Intent intent = new Intent(Safe_Activity.this, Bind_Email_Activity1.class);
                    intent.putExtra("title","绑定邮箱（设置）");
                    intent.putExtra("select",SP_String.YEWUMIMA);  // 产品更改需求，业务密码设置与绑定邮箱是相同的
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    Intent intent = new Intent(Safe_Activity.this, Bind_Email_Reset_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
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
        safe_yewu_pass = (RelativeLayout) findViewById(R.id.safe_yewu_pass);
        safe_bind_youxiang = (RelativeLayout) findViewById(R.id.safe_bind_youxiang);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
