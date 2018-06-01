package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Pick_Up_Goods_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.EPickUpStage;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *  提货单详情
 */
public class Pick_Up_Goods_Detail_Activity extends BaseMVPActivity<Pick_Up_Goods_Detail_Activity, MyPresenter<Pick_Up_Goods_Detail_Activity>> {

    private RelativeLayout pick_up_goods_detail_Fan;
    private TextView pick_up_goods_detail_name;
    private TextView pick_up_goods_detail_dword;
    private TextView pick_up_goods_detail_xiangqing;
    private TextView pick_up_goods_detail_cword;
    private TextView pick_up_goods_detail_cangku;
    private TextView pick_up_goods_detail_zhuce_riqi;
    private TextView pick_up_goods_detail_tihuo_riqi;
    private TextView pick_up_goods_detail_jianshu;
    private TextView pick_up_goods_detail_shuliang;
    private TextView pick_up_goods_detail_shouxufei;
    private TextView pick_up_goods_detail_cangchufei;
    private TextView pick_up_goods_detail_type;
    private Button pick_up_goods_detail_zhuxiao;
    private LinearLayout pick_up_goods_detail_anniu;
    private Pick_Up_Goods_Bean.DataBean.ListBean pick_up_goods_bean;

    @Override
    public MyPresenter<Pick_Up_Goods_Detail_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick__up__goods__detail_);
        initView();

        pick_up_goods_detail_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setResult(DashApplication.PICK_TO_DETAILS_res);
                finish();
            }
        });


        pick_up_goods_detail_zhuxiao.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
//                MyUtils.setToast("点击注销。。。");
                HashMap<String, String> map = new HashMap<>();
                map.put("orderId",pick_up_goods_bean.getId()+"");
                myPresenter.postPreContent(APIs.cancelTakeDeliveryOrder, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
//                                CancelTakeDeliveryOrder_Bean cancelTakeDeliveryOrder_bean = new Gson().fromJson(json, CancelTakeDeliveryOrder_Bean.class);
                                MyUtils.setToast((String) jsonObject.get("message"));
                                finish();
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
        });

        /**
         * 以下模拟数据 可能是查询网络，可能是获取跳转传递
         */
        Intent intent = getIntent();
        pick_up_goods_bean = (Pick_Up_Goods_Bean.DataBean.ListBean) intent.getSerializableExtra("Pick_Up_Goods_Bean");
        pick_up_goods_detail_name.setText(pick_up_goods_bean.getGoodsName());
        pick_up_goods_detail_dword.setText(pick_up_goods_bean.getBillId()+"");
        pick_up_goods_detail_cword.setText(""+ pick_up_goods_bean.getGoodsCode());

        if(pick_up_goods_bean.getDepotName().equals("")|| pick_up_goods_bean.getDepotName()==null){
            pick_up_goods_detail_cangku.setText("福丽特仓库");
        }else{
            pick_up_goods_detail_cangku.setText(pick_up_goods_bean.getDepotName());
        }

        Date date = new Date(pick_up_goods_bean.getAddTime()); // 注册日期
        Date date1 = new Date(pick_up_goods_bean.getDeliveryTime());  // 提货日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        pick_up_goods_detail_zhuce_riqi.setText(simpleDateFormat.format(date));
        pick_up_goods_detail_tihuo_riqi.setText(simpleDateFormat.format(date1));

        pick_up_goods_detail_jianshu.setText(""+ pick_up_goods_bean.getDeliveryQty());  // 提货件数
        pick_up_goods_detail_shuliang.setText(""+ pick_up_goods_bean.getQuantity());  // 数量

        pick_up_goods_detail_shouxufei.setText("￥"+ DashApplication.decimalFormat.format(pick_up_goods_bean.getDeliveryFee()));  // 手续费
        pick_up_goods_detail_cangchufei.setText("￥"+ DashApplication.decimalFormat.format(0));  // 仓储费  暂无数据

        pick_up_goods_detail_type.setText(EPickUpStage.getName(pick_up_goods_bean.getBillStage()));

        if(pick_up_goods_bean.getBillStage()==0){
            pick_up_goods_detail_anniu.setVisibility(View.VISIBLE);
        }else{
            pick_up_goods_detail_anniu.setVisibility(View.GONE);
        }


    }

    private void initView() {
        pick_up_goods_detail_Fan = (RelativeLayout) findViewById(R.id.pick_up_goods_detail_Fan);
        pick_up_goods_detail_name = (TextView) findViewById(R.id.pick_up_goods_detail_name);
        pick_up_goods_detail_dword = (TextView) findViewById(R.id.pick_up_goods_detail_dword);
        pick_up_goods_detail_xiangqing = (TextView) findViewById(R.id.pick_up_goods_detail_xiangqing);
        pick_up_goods_detail_cword = (TextView) findViewById(R.id.pick_up_goods_detail_cword);
        pick_up_goods_detail_cangku = (TextView) findViewById(R.id.pick_up_goods_detail_cangku);
        pick_up_goods_detail_zhuce_riqi = (TextView) findViewById(R.id.pick_up_goods_detail_zhuce_riqi);
        pick_up_goods_detail_tihuo_riqi = (TextView) findViewById(R.id.pick_up_goods_detail_tihuo_riqi);
        pick_up_goods_detail_jianshu = (TextView) findViewById(R.id.pick_up_goods_detail_jianshu);
        pick_up_goods_detail_shuliang = (TextView) findViewById(R.id.pick_up_goods_detail_shuliang);
        pick_up_goods_detail_shouxufei = (TextView) findViewById(R.id.pick_up_goods_detail_shouxufei);
        pick_up_goods_detail_cangchufei = (TextView) findViewById(R.id.pick_up_goods_detail_cangchufei);
        pick_up_goods_detail_type = (TextView) findViewById(R.id.pick_up_goods_detail_type);
        pick_up_goods_detail_zhuxiao = (Button) findViewById(R.id.pick_up_goods_detail_zhuxiao);
        pick_up_goods_detail_anniu = (LinearLayout)findViewById(R.id.pick_up_goods_detail_anniu);

    }


}
