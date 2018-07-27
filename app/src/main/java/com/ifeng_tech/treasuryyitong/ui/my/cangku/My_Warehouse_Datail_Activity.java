package com.ifeng_tech.treasuryyitong.ui.my.cangku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.My_Warehouse_Datail_List_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.Collection_Directory_Detail_Activity;
import com.ifeng_tech.treasuryyitong.ui.Goods_QR_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Donation_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_list_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.tihuo.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
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

//    private TextView my_Warehouse_Datail_zong_num;
//    private TextView my_Warehouse_Datail_cangchufei;

    private Button my_Warehouse_Datail_zhuanzeng;
    private TextView my_Warehouse_Datail_tihuo;
    private WarehouseBean.DataBean.ListBean warehouseBean;
    private Button my_warehouse_datail_erweima;
    private WarehouseBean.DataBean.MaxTrasferableAmountAndFeeBean maxTrasferableAmountAndFeeBean;
    private RelativeLayout my_warehouse_datail_zhuanzeng_jilu;
    private MyListView my_warehouse_datail_myListView;
    private TextView my_warehouse_datail_chakan_mingxi;
    private LinearLayout my_warehouse_datail_null;
    private LinearLayout my_warehouse_datail_xiangqing;

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
        my_Warehouse_Datail_cword.setText("编号："+warehouseBean.getGoodsCode());
        my_Warehouse_Datail_keyong_num.setText(""+warehouseBean.getAvailableQty());
        my_Warehouse_Datail_dongjie_num.setText(""+warehouseBean.getFrozenQty());
//        my_Warehouse_Datail_zong_num.setText(""+(warehouseBean.getHoldQty()));

        // 点击二维码图标跳转  现在改成了点击最下方的收货按钮
        my_warehouse_datail_erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Warehouse_Datail_Activity.this, Goods_QR_Activity.class);
                intent.putExtra("WarehouseBean",warehouseBean);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 点击转赠记录
        my_warehouse_datail_zhuanzeng_jilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了转赠记录...");
                Intent  intent = new Intent(My_Warehouse_Datail_Activity.this, My_Given_list_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 点击仓库明细
        my_warehouse_datail_chakan_mingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(My_Warehouse_Datail_Activity.this, Shop_Detailed_Activity.class);
                intent.putExtra("goodsId",warehouseBean.getGoodsId()+"");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 点击顶部大波浪跳到藏品目录详情
        my_warehouse_datail_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsId = warehouseBean.getGoodsId();
                Intent intent = new Intent(My_Warehouse_Datail_Activity.this, Collection_Directory_Detail_Activity.class);
                intent.putExtra("goodsId",""+goodsId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

    }
    Map<String, String> map = new HashMap<>();
    @Override
    protected void onResume() {
        super.onResume();

        map.put("goodsId",""+warehouseBean.getGoodsId());
        map.put("queryMode","0");
        getZhuanZeng_list(map);  // 根据goosid查询商品的转赠记录
//        map.put("goodsId",warehouseBean.getGoodsId()+"");
//        myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
//            @Override
//            public void chenggong(String json) {
//                try {
//                    JSONObject jsonObject = new JSONObject(json);
//                    String code = (String) jsonObject.get("code");
//                    if(code.equals("2000")){
//                        My_Warehouse_Datail_Bean my_Warehouse_Datail_Bean = new Gson().fromJson(json, My_Warehouse_Datail_Bean.class);
//                        if(my_Warehouse_Datail_Bean.getData().getGoodsCountForStorageFeePayment().size()>0){
//                            double price= my_Warehouse_Datail_Bean.getData().getMaxTrasferableAmountAndFee().getCommonFeeRate()*  // 会员折扣
//                                    my_Warehouse_Datail_Bean.getData().getList().get(0).getGuidingPrice()*  // 指导价
//                                    my_Warehouse_Datail_Bean.getData().getList().get(0).getStorageFeeRate()*  //  仓储费率
//                                    my_Warehouse_Datail_Bean.getData().getGoodsCountForStorageFeePayment().get(0).getHoldQty();  // 数量
//                            my_Warehouse_Datail_cangchufei.setText("￥"+ DashApplication.decimalFormat.format(price));
//                        }else{
//                            my_Warehouse_Datail_cangchufei.setText("￥"+ DashApplication.decimalFormat.format(0));
//                        }
//                    }else{
//                        MyUtils.setToast((String) jsonObject.get("message"));
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void shibai(String ss) {
//                MyUtils.setToast(ss);
//            }
//        });
    }

    private void getZhuanZeng_list(Map<String, String> map) {
        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);
        myPresenter.postPreContent(APIs.getBestowList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Give_List_Bean give_List_Bean = new Gson().fromJson(json, Give_List_Bean.class);
                        List<Give_List_Bean.DataBean.ListBean> zilist = give_List_Bean.getData().getList();
                        setMy_Warehouse_Datail_Adapter(zilist);
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
            }
        });
    }

    public void setMy_Warehouse_Datail_Adapter(List<Give_List_Bean.DataBean.ListBean> zilist){
        if(zilist.size()>0){
            my_warehouse_datail_null.setVisibility(View.GONE);
            my_warehouse_datail_myListView.setVisibility(View.VISIBLE);
            my_warehouse_datail_myListView.setAdapter(new My_Warehouse_Datail_List_Adapter(My_Warehouse_Datail_Activity.this,zilist));
        }else{
            my_warehouse_datail_null.setVisibility(View.VISIBLE);
            my_warehouse_datail_myListView.setVisibility(View.GONE);
        }
    }

    private void initView() {
        my_Warehouse_Datail_Fan = (RelativeLayout) findViewById(R.id.my_Warehouse_Datail_Fan);
        my_warehouse_datail_xiangqing = (LinearLayout) findViewById(R.id.my_Warehouse_Datail_xiangqing);

        my_Warehouse_Datail_name = (TextView) findViewById(R.id.my_Warehouse_Datail_name);
        my_Warehouse_Datail_cword = (TextView) findViewById(R.id.my_Warehouse_Datail_cword);
//        my_Warehouse_Datail_xiangqing = (TextView) findViewById(R.id.my_Warehouse_Datail_xiangqing);
        my_warehouse_datail_erweima = (Button) findViewById(R.id.my_Warehouse_Datail_erweima);
        my_Warehouse_Datail_keyong_num = (TextView) findViewById(R.id.my_Warehouse_Datail_keyong_num);
        my_Warehouse_Datail_dongjie_num = (TextView) findViewById(R.id.my_Warehouse_Datail_dongjie_num);
//        my_Warehouse_Datail_zong_num = (TextView) findViewById(R.id.my_Warehouse_Datail_zong_num);
//        my_Warehouse_Datail_cangchufei = (TextView) findViewById(R.id.my_Warehouse_Datail_cangchufei);
        my_Warehouse_Datail_zhuanzeng = (Button) findViewById(R.id.my_Warehouse_Datail_zhuanzeng);
        my_Warehouse_Datail_tihuo = (TextView) findViewById(R.id.my_Warehouse_Datail_tihuo);
        my_warehouse_datail_chakan_mingxi = (TextView) findViewById(R.id.my_Warehouse_Datail_chakan_mingxi);

        my_warehouse_datail_zhuanzeng_jilu = (RelativeLayout) findViewById(R.id.my_Warehouse_Datail_zhuanzeng_jilu);
        my_warehouse_datail_myListView = (MyListView) findViewById(R.id.my_Warehouse_Datail_MyListView);
        my_warehouse_datail_null = (LinearLayout) findViewById(R.id.my_Warehouse_Datail_null);

        my_Warehouse_Datail_zhuanzeng.setOnClickListener(this);
        my_Warehouse_Datail_tihuo.setOnClickListener(this);

        /**
         * 解决scrollview 显示不在顶部问题
         */
        my_Warehouse_Datail_cword.setFocusable(true);
        my_Warehouse_Datail_cword.setFocusableInTouchMode(true);
        my_Warehouse_Datail_cword.requestFocus();


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
