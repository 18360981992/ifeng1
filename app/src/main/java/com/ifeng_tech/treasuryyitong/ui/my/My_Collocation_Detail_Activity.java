package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.my.My_Colloction_Bean;

/**
 *  托管详情
 */
public class My_Collocation_Detail_Activity extends AppCompatActivity {

    private RelativeLayout my_collocation_detail_Fan;
    private TextView my_collocation_detail_name;
    private TextView my_collocation_detail_cword;
    private TextView my_collocation_detail_danwei;
    private TextView my_collocation_detail_xiangqing;
    private TextView my_collocation_detail_jiandingfei;
    private TextView my_collocation_detail_tianshu;
    private TextView my_collocation_detail_type;
    private TextView my_collocation_detail_kaishi_time;
    private TextView my_collocation_detail_dizhi;

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
        My_Colloction_Bean.DataBean.PageInfoBean.ListBean collocation_list_bean = (My_Colloction_Bean.DataBean.PageInfoBean.ListBean) intent.getSerializableExtra("Collocation_list_Bean");

        my_collocation_detail_name.setText(collocation_list_bean.getGoodsName());
        my_collocation_detail_cword.setText(""+collocation_list_bean.getGoodsCode());
        my_collocation_detail_danwei.setText("枚");
        my_collocation_detail_jiandingfei.setText(DashApplication.decimalFormat.format(collocation_list_bean.getAppraisalFee()));
        my_collocation_detail_tianshu.setText(""+collocation_list_bean.getLeaseFate());

        if(collocation_list_bean.getPmORam().equals("am")){
            my_collocation_detail_kaishi_time.setText(collocation_list_bean.getTrusteeTime()+" 上午");
        }else{
            my_collocation_detail_kaishi_time.setText(collocation_list_bean.getTrusteeTime()+" 下午");
        }


//        if(collocation_list_bean.getType()==0){ // 这里的type和my_colloction_list_adapter中的type是不一样的，这里是模拟数据
//            my_collocation_detail_type.setText("等待鉴定");
//        }else{
//            my_collocation_detail_type.setText("未开始");
//        }
    }



    private void initView() {
        my_collocation_detail_Fan = (RelativeLayout) findViewById(R.id.my_collocation_detail_Fan);
        my_collocation_detail_name = (TextView) findViewById(R.id.my_collocation_detail_name);
        my_collocation_detail_cword = (TextView) findViewById(R.id.my_collocation_detail_cword);
        my_collocation_detail_danwei = (TextView) findViewById(R.id.my_collocation_detail_danwei);
        my_collocation_detail_xiangqing = (TextView) findViewById(R.id.my_collocation_detail_xiangqing);
        my_collocation_detail_jiandingfei = (TextView) findViewById(R.id.my_collocation_detail_jiandingfei);
        my_collocation_detail_tianshu = (TextView) findViewById(R.id.my_collocation_detail_tianshu);
        my_collocation_detail_type = (TextView) findViewById(R.id.my_collocation_detail_type);
        my_collocation_detail_kaishi_time = (TextView) findViewById(R.id.my_collocation_detail_kaishi_time);
        my_collocation_detail_dizhi = (TextView) findViewById(R.id.my_collocation_detail_dizhi);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
