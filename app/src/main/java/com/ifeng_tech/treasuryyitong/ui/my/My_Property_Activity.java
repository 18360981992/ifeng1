package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.PropertyListAdapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.DetailBean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

// 我的资产

public class My_Property_Activity extends BaseMVPActivity<My_Property_Activity,MyPresenter<My_Property_Activity>> implements View.OnClickListener {

    private RelativeLayout property_Fan;
    private ImageView property_zhangdan;
    private TextView property_money;
    private TextView property_dongjie;
    private TextView property_keyong;
    private TextView qiankuan;
    private ListView property_ListView;
    private Button property_tixian;
    private Button property_congzhi;

    List<DetailBean> detailList = new ArrayList<>();

    @Override
    public MyPresenter<My_Property_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__property_);
        initView();

        // 点击返回
        property_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        property_zhangdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("点击了账单。。。");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        property_ListView.setAdapter(new PropertyListAdapter(My_Property_Activity.this,detailList));
    }

    private void initView() {
        property_Fan = (RelativeLayout) findViewById(R.id.property_Fan);
        property_zhangdan = (ImageView) findViewById(R.id.property_zhangdan);
        property_money = (TextView) findViewById(R.id.property_money);
        property_dongjie = (TextView) findViewById(R.id.property_dongjie);
        property_keyong = (TextView) findViewById(R.id.property_keyong);
        qiankuan = (TextView) findViewById(R.id.qiankuan);
        property_ListView = (ListView) findViewById(R.id.property_ListView);
        property_tixian = (Button) findViewById(R.id.property_tixian);
        property_congzhi = (Button) findViewById(R.id.property_congzhi);

        property_tixian.setOnClickListener(this);
        property_congzhi.setOnClickListener(this);

        initData();
    }


    public void initData(){

        detailList.add(new DetailBean("充值","2018-05-09","+1000"));
        detailList.add(new DetailBean("缴费","2018-05-09","-1000"));
        detailList.add(new DetailBean("提现","2018-05-09","-1000"));
        detailList.add(new DetailBean("充值","2018-05-09","+1000"));
        detailList.add(new DetailBean("提现","2018-05-09","-1000"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.property_tixian:
                MyUtils.setToast("点击了提现。。。");
                break;
            case R.id.property_congzhi:
                MyUtils.setToast("点击了充值。。。");
                break;
        }
    }
}
