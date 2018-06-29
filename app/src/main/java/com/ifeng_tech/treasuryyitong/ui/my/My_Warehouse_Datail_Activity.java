package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.My_Warehouse_Datail_Bean;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.Goods_QR_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *  仓库详情
 */
public class My_Warehouse_Datail_Activity extends BaseMVPActivity<My_Warehouse_Datail_Activity, MyPresenter<My_Warehouse_Datail_Activity>> implements View.OnClickListener {

    private RelativeLayout my_Warehouse_Datail_Fan;
    private TextView my_Warehouse_Datail_name;
    private TextView my_Warehouse_Datail_cword;
    private TextView my_Warehouse_Datail_keyong_num;
    private TextView my_Warehouse_Datail_dongjie_num;
    private TextView my_Warehouse_Datail_zong_num;
    private TextView my_Warehouse_Datail_cangchufei;
    private Button my_Warehouse_Datail_zhuanzeng;
    private Button my_Warehouse_Datail_tihuo;
    private WarehouseBean.DataBean.ListBean warehouseBean;
    private ImageView my_warehouse_datail_erweima;
    private WarehouseBean.DataBean.MaxTrasferableAmountAndFeeBean maxTrasferableAmountAndFeeBean;

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
        Intent intent = getIntent();
        warehouseBean = (WarehouseBean.DataBean.ListBean)intent .getSerializableExtra("WarehouseBean");
        maxTrasferableAmountAndFeeBean = (WarehouseBean.DataBean.MaxTrasferableAmountAndFeeBean) intent.getSerializableExtra("MaxTrasferableAmountAndFeeBean");

        my_Warehouse_Datail_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        my_Warehouse_Datail_name.setText(warehouseBean.getGoodsName());
        my_Warehouse_Datail_cword.setText(""+warehouseBean.getGoodsCode());
        my_Warehouse_Datail_keyong_num.setText(""+warehouseBean.getAvailableQty());
        my_Warehouse_Datail_dongjie_num.setText(""+warehouseBean.getFrozenQty());
        my_Warehouse_Datail_zong_num.setText(""+(warehouseBean.getHoldQty()));

        // 点击二维码图标跳转
        my_warehouse_datail_erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Warehouse_Datail_Activity.this, Goods_QR_Activity.class);
                intent.putExtra("WarehouseBean",warehouseBean);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

    }
    Map<String, String> map = new HashMap<>();
    @Override
    protected void onResume() {
        super.onResume();
        map.put("goodsId",warehouseBean.getGoodsId()+"");
        myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        My_Warehouse_Datail_Bean my_Warehouse_Datail_Bean = new Gson().fromJson(json, My_Warehouse_Datail_Bean.class);
                        if(my_Warehouse_Datail_Bean.getData().getGoodsCountForStorageFeePayment().size()>0){
                            double price= my_Warehouse_Datail_Bean.getData().getMaxTrasferableAmountAndFee().getCommonFeeRate()*  // 会员折扣
                                    my_Warehouse_Datail_Bean.getData().getList().get(0).getGuidingPrice()*  // 指导价
                                    my_Warehouse_Datail_Bean.getData().getList().get(0).getStorageFeeRate()*  //  仓储费率
                                    my_Warehouse_Datail_Bean.getData().getGoodsCountForStorageFeePayment().get(0).getHoldQty();  // 数量
                            my_Warehouse_Datail_cangchufei.setText("￥"+ DashApplication.decimalFormat.format(price));
                        }else{
                            my_Warehouse_Datail_cangchufei.setText("￥"+ DashApplication.decimalFormat.format(0));
                        }
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
            }
        });
    }

    private void initView() {
        my_Warehouse_Datail_Fan = (RelativeLayout) findViewById(R.id.my_Warehouse_Datail_Fan);
        my_Warehouse_Datail_name = (TextView) findViewById(R.id.my_Warehouse_Datail_name);
        my_Warehouse_Datail_cword = (TextView) findViewById(R.id.my_Warehouse_Datail_cword);
//        my_Warehouse_Datail_xiangqing = (TextView) findViewById(R.id.my_Warehouse_Datail_xiangqing);
        my_warehouse_datail_erweima = (ImageView) findViewById(R.id.my_Warehouse_Datail_erweima);
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
//                MyUtils.setToast("点击转赠");
                Intent intent = new Intent(My_Warehouse_Datail_Activity.this, Donation_Activity.class);
                intent.putExtra("WarehouseBean", warehouseBean);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                break;
            case R.id.my_Warehouse_Datail_tihuo:
//                MyUtils.setToast("点击提货");

                Intent intent1 = new Intent(My_Warehouse_Datail_Activity.this, Pick_up_goods_Activity.class);
                intent1.putExtra("WarehouseBean", warehouseBean);
                intent1.putExtra("MaxTrasferableAmountAndFeeBean",maxTrasferableAmountAndFeeBean);
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
