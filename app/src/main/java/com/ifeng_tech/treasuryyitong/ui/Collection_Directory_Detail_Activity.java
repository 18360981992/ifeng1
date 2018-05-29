package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Collection_directory_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;

/**
 * 藏品详情
 */
public class Collection_Directory_Detail_Activity extends BaseMVPActivity<Collection_Directory_Detail_Activity, MyPresenter<Collection_Directory_Detail_Activity>> {

    private RelativeLayout collection_mulu_detail_Fan;
    private TextView collocation_mulu_detail_guige;
    private TextView collocation_mulu_detail_shoujia;
    private TextView collocation_mulu_detail_faxingliang;
    private TextView collocation_mulu_detail_jianjie;
    private TextView collocation_mulu_detail_name;
    private TextView collocation_mulu_detail_cword;
    private Collection_directory_Bean collection_directory_bean;

    @Override
    public MyPresenter<Collection_Directory_Detail_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection__directory__detail_);
        initView();



        collection_mulu_detail_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        /**
         *  以下是模拟数据  这些数据可以是根据id获取信息
         */
        collection_directory_bean = (Collection_directory_Bean) getIntent().getSerializableExtra("Collection_directory_Bean");
        collocation_mulu_detail_name.setText(collection_directory_bean.getName());
        collocation_mulu_detail_cword.setText(collection_directory_bean.getCword()+"");
        collocation_mulu_detail_guige.setText("100mm*120mm");
        collocation_mulu_detail_shoujia.setText(collection_directory_bean.getPrice()+"元");
        collocation_mulu_detail_faxingliang.setText(collection_directory_bean.getNum()+"枚");
        collocation_mulu_detail_jianjie.setText("2007年11月26日发行，全套1枚（1-1）帖特6-2007全套1枚邮票 销售日纪念邮戳，首日封、戳设计者：陈曼株");
    }

    private void initView() {
        collection_mulu_detail_Fan = (RelativeLayout) findViewById(R.id.collection_mulu_detail_Fan);
        collocation_mulu_detail_name = (TextView) findViewById(R.id.collocation_mulu_detail_name);
        collocation_mulu_detail_cword = (TextView) findViewById(R.id.collocation_mulu_detail_cword);
        collocation_mulu_detail_guige = (TextView) findViewById(R.id.collocation_mulu_detail_guige);
        collocation_mulu_detail_shoujia = (TextView) findViewById(R.id.collocation_mulu_detail_shoujia);
        collocation_mulu_detail_faxingliang = (TextView) findViewById(R.id.collocation_mulu_detail_faxingliang);
        collocation_mulu_detail_jianjie = (TextView) findViewById(R.id.collocation_mulu_detail_jianjie);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
