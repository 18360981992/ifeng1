package com.ifeng_tech.treasuryyitong.ui.my.tuoguan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.my.My_Colloction_Bean;
import com.ifeng_tech.treasuryyitong.ui.Collection_Directory_Detail_Activity;
import com.ifeng_tech.treasuryyitong.utils.ApplicationFormEnum;

/**
 *  托管详情
 */
public class My_Collocation_Detail_Activity extends AppCompatActivity {

    private RelativeLayout my_collocation_detail_Fan;
    private TextView my_collocation_detail_name;
    private TextView my_collocation_detail_cword;
    private TextView my_collocation_detail_danwei;
    private LinearLayout my_collocation_detail_xiangqing;
    private TextView my_collocation_detail_jiandingfei;
    private TextView my_collocation_detail_tianshu;
    private TextView my_collocation_detail_type;
    private TextView my_collocation_detail_kaishi_time;
    private TextView my_collocation_detail_dizhi;
    private TextView my_collocation_detail_danhao;
    private TextView my_collocation_detail_shuliang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__collocation__detail_);
        initView();

        my_collocation_detail_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 以下都是模拟数据
         */
        Intent intent = getIntent();
        final My_Colloction_Bean.DataBean.ListBean collocation_list_bean = (My_Colloction_Bean.DataBean.ListBean) intent.getSerializableExtra("Collocation_list_Bean");

        my_collocation_detail_name.setText(collocation_list_bean.getGoodsName());
        my_collocation_detail_cword.setText("编号："+collocation_list_bean.getGoodsCode());
        my_collocation_detail_danwei.setText("枚");
        my_collocation_detail_danhao.setText(collocation_list_bean.getOrderNo()+"");  // 单号
        my_collocation_detail_shuliang.setText(collocation_list_bean.getNumber()+"枚");  // 数量
        double price = collocation_list_bean.getNumber() * collocation_list_bean.getAppraisalFee();
        my_collocation_detail_jiandingfei.setText(DashApplication.decimalFormat.format(price)); // 鉴定费
        my_collocation_detail_tianshu.setText(collocation_list_bean.getLeaseFate()+"天");  // 预收仓储天数

        if(collocation_list_bean.getPmORam().equals("am")){
            my_collocation_detail_kaishi_time.setText(collocation_list_bean.getTrusteeTime()+" 上午");
        }else{
            my_collocation_detail_kaishi_time.setText(collocation_list_bean.getTrusteeTime()+" 下午");
        }

        my_collocation_detail_dizhi.setText(collocation_list_bean.getAddress()+"");

        Integer state = Integer.valueOf(collocation_list_bean.getState());   // 为状态赋值
        my_collocation_detail_type.setText(ApplicationFormEnum.getName(state));



        // 托管详情中的 商品详情 跳到 藏品详情
        my_collocation_detail_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsId = collocation_list_bean.getGoodsId();
                Intent intent = new Intent(My_Collocation_Detail_Activity.this, Collection_Directory_Detail_Activity.class);
                intent.putExtra("goodsId",""+goodsId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }



    private void initView() {
        my_collocation_detail_Fan = (RelativeLayout) findViewById(R.id.my_collocation_detail_Fan);
        my_collocation_detail_name = (TextView) findViewById(R.id.my_collocation_detail_name);
        my_collocation_detail_cword = (TextView) findViewById(R.id.my_collocation_detail_cword);
        my_collocation_detail_danwei = (TextView) findViewById(R.id.my_collocation_detail_danwei);
        my_collocation_detail_xiangqing = (LinearLayout) findViewById(R.id.my_collocation_detail_xiangqing);
        my_collocation_detail_jiandingfei = (TextView) findViewById(R.id.my_collocation_detail_jiandingfei);
        my_collocation_detail_tianshu = (TextView) findViewById(R.id.my_collocation_detail_tianshu);
        my_collocation_detail_type = (TextView) findViewById(R.id.my_collocation_detail_type);
        my_collocation_detail_kaishi_time = (TextView) findViewById(R.id.my_collocation_detail_kaishi_time);
        my_collocation_detail_dizhi = (TextView) findViewById(R.id.my_collocation_detail_dizhi);

        my_collocation_detail_danhao = (TextView) findViewById(R.id.my_collocation_detail_danhao);
        my_collocation_detail_shuliang = (TextView) findViewById(R.id.my_collocation_detail_shuliang);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
