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
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;

public class My_Warehouse_Datail_Activity extends BaseMVPActivity<My_Warehouse_Datail_Activity, MyPresenter<My_Warehouse_Datail_Activity>> implements View.OnClickListener {

    private RelativeLayout my_Warehouse_Datail_Fan;
    private TextView my_Warehouse_Datail_name;
    private TextView my_Warehouse_Datail_cword;
    private TextView my_Warehouse_Datail_xiangqing;
    private TextView my_Warehouse_Datail_keyong_num;
    private TextView my_Warehouse_Datail_dongjie_num;
    private TextView my_Warehouse_Datail_zong_num;
    private TextView my_Warehouse_Datail_cangchufei;
    private Button my_Warehouse_Datail_zhuanzeng;
    private Button my_Warehouse_Datail_tihuo;
    private WarehouseBean warehouseBean;

    @Override
    public MyPresenter<My_Warehouse_Datail_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__warehouse__datail_);
        initView();

        warehouseBean = (WarehouseBean) getIntent().getSerializableExtra("WarehouseBean");

        my_Warehouse_Datail_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        my_Warehouse_Datail_name.setText(warehouseBean.getShopping_name());
        my_Warehouse_Datail_cword.setText(""+warehouseBean.getWord());
        my_Warehouse_Datail_keyong_num.setText(""+warehouseBean.getKeyong_num());
        my_Warehouse_Datail_dongjie_num.setText(""+warehouseBean.getDongjie_num());
        my_Warehouse_Datail_zong_num.setText(""+(warehouseBean.getKeyong_num()+warehouseBean.getDongjie_num()));

        my_Warehouse_Datail_cangchufei.setText("￥"+ DashApplication.decimalFormat.format(30.5));
    }


    private void initView() {
        my_Warehouse_Datail_Fan = (RelativeLayout) findViewById(R.id.my_Warehouse_Datail_Fan);
        my_Warehouse_Datail_name = (TextView) findViewById(R.id.my_Warehouse_Datail_name);
        my_Warehouse_Datail_cword = (TextView) findViewById(R.id.my_Warehouse_Datail_cword);
        my_Warehouse_Datail_xiangqing = (TextView) findViewById(R.id.my_Warehouse_Datail_xiangqing);
        my_Warehouse_Datail_keyong_num = (TextView) findViewById(R.id.my_Warehouse_Datail_keyong_num);
        my_Warehouse_Datail_dongjie_num = (TextView) findViewById(R.id.my_Warehouse_Datail_dongjie_num);
        my_Warehouse_Datail_zong_num = (TextView) findViewById(R.id.my_Warehouse_Datail_zong_num);
        my_Warehouse_Datail_cangchufei = (TextView) findViewById(R.id.my_Warehouse_Datail_cangchufei);
        my_Warehouse_Datail_zhuanzeng = (Button) findViewById(R.id.my_Warehouse_Datail_zhuanzeng);
        my_Warehouse_Datail_tihuo = (Button) findViewById(R.id.my_Warehouse_Datail_tihuo);

        my_Warehouse_Datail_zhuanzeng.setOnClickListener(this);
        my_Warehouse_Datail_tihuo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_Warehouse_Datail_zhuanzeng:
                Intent intent = new Intent(My_Warehouse_Datail_Activity.this, Donation_Activity.class);
                intent.putExtra("WarehouseBean", warehouseBean);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                break;
            case R.id.my_Warehouse_Datail_tihuo:
                Intent intent1 = new Intent(My_Warehouse_Datail_Activity.this, Pick_up_goods_Activity.class);
                intent1.putExtra("WarehouseBean", warehouseBean);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
