package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

public class My_Given_Datail_Activity extends BaseMVPActivity<My_Given_Datail_Activity, MyPresenter<My_Given_Datail_Activity>> implements View.OnClickListener {

    private RelativeLayout my_Given_Datail_Fan;
    private TextView my_Given_Datail_name;
    private TextView my_Given_Datail_cword;
    private TextView my_Given_Datail_xiangqing;
    private TextView my_Given_Datail_dword;
    private TextView my_Given_Datail_cangku;
    private TextView my_Given_Datail_shouxufei;
    private TextView my_Given_Datail_leibie;
    private TextView my_Given_Datail_zhangtai;
    private Button my_Given_Datail_tongyi;
    private Button my_Given_Datail_jujue;

    @Override
    public MyPresenter<My_Given_Datail_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__given__datail_);
        initView();
        my_Given_Datail_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        Give_List_Bean give_list_bean = (Give_List_Bean) intent.getSerializableExtra("Give_List_Bean");
        int leibie = intent.getIntExtra("leibie", 0);
        my_Given_Datail_name.setText(give_list_bean.getName());
        my_Given_Datail_cword.setText(""+give_list_bean.getCword());
        my_Given_Datail_dword.setText(""+568974895);
        my_Given_Datail_cangku.setText("福利特仓库");
        my_Given_Datail_shouxufei.setText("￥"+ DashApplication.decimalFormat.format(30.5));
        if(leibie==0){
            my_Given_Datail_leibie.setText("转入");
        }else{
            my_Given_Datail_leibie.setText("转出");
        }

        my_Given_Datail_zhangtai.setText("等待确认");

    }

    private void initView() {
        my_Given_Datail_Fan = (RelativeLayout) findViewById(R.id.my_Given_Datail_Fan);
        my_Given_Datail_name = (TextView) findViewById(R.id.my_Given_Datail_name);
        my_Given_Datail_cword = (TextView) findViewById(R.id.my_Given_Datail_cword);
        my_Given_Datail_xiangqing = (TextView) findViewById(R.id.my_Given_Datail_xiangqing);
        my_Given_Datail_dword = (TextView) findViewById(R.id.my_Given_Datail_dword);
        my_Given_Datail_cangku = (TextView) findViewById(R.id.my_Given_Datail_cangku);
        my_Given_Datail_shouxufei = (TextView) findViewById(R.id.my_Given_Datail_shouxufei);
        my_Given_Datail_leibie = (TextView) findViewById(R.id.my_Given_Datail_leibie);
        my_Given_Datail_zhangtai = (TextView) findViewById(R.id.my_Given_Datail_zhangtai);
        my_Given_Datail_tongyi = (Button) findViewById(R.id.my_Given_Datail_tongyi);
        my_Given_Datail_jujue = (Button) findViewById(R.id.my_Given_Datail_jujue);

        my_Given_Datail_tongyi.setOnClickListener(this);
        my_Given_Datail_jujue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_Given_Datail_tongyi:
                MyUtils.setToast("点击同意。。。");
                break;
            case R.id.my_Given_Datail_jujue:
                MyUtils.setToast("点击拒绝。。。");
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
