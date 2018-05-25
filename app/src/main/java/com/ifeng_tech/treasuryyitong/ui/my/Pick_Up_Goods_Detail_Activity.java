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
import com.ifeng_tech.treasuryyitong.bean.Pick_Up_Goods_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Button pick_up_goods_detail_xiazai;

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
                MyUtils.setToast("点击注销。。。");
            }
        });
        pick_up_goods_detail_xiazai.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                MyUtils.setToast("点击下载。。。");
            }
        });

        /**
         * 以下模拟数据 可能是查询网络，可能是获取跳转传递
         */
        Intent intent = getIntent();
        Pick_Up_Goods_Bean pick_up_goods_bean = (Pick_Up_Goods_Bean) intent.getSerializableExtra("Pick_Up_Goods_Bean");
        pick_up_goods_detail_name.setText(pick_up_goods_bean.getName());
        pick_up_goods_detail_dword.setText(pick_up_goods_bean.getDword()+"");
        pick_up_goods_detail_cword.setText(""+pick_up_goods_bean.getCword());
        pick_up_goods_detail_cangku.setText("福利特仓库");

        Date date = new Date(pick_up_goods_bean.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        pick_up_goods_detail_zhuce_riqi.setText(simpleDateFormat.format(date));
        pick_up_goods_detail_tihuo_riqi.setText(simpleDateFormat.format(date));

        pick_up_goods_detail_jianshu.setText(""+50);
        pick_up_goods_detail_shuliang.setText(""+400);
        pick_up_goods_detail_shouxufei.setText("￥"+ DashApplication.decimalFormat.format(pick_up_goods_bean.getPrice()));
        pick_up_goods_detail_cangchufei.setText("￥"+ DashApplication.decimalFormat.format(pick_up_goods_bean.getPrice()));

        if(pick_up_goods_bean.getType()==0){
            pick_up_goods_detail_type.setText("等待提货");
        }else{
            pick_up_goods_detail_type.setText("已逾期");
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
        pick_up_goods_detail_xiazai = (Button) findViewById(R.id.pick_up_goods_detail_xiazai);


    }


}
