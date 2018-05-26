package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Stock_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Stock_Removal_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

// 出库管理

public class Stock_Removal_Activity extends BaseMVPActivity<Stock_Removal_Activity,MyPresenter<Stock_Removal_Activity>> {

    private RelativeLayout stock_Fan;
    List<Stock_Removal_Bean> stocklist = new ArrayList<>();
    private Stock_Adapter stock_adapter;
    private XRecyclerView stock_xRecyclerView;

    @Override
    public MyPresenter<Stock_Removal_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock__removal);
        initView();

        // 点击返回
        stock_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        stock_xRecyclerView.setLayoutManager(new LinearLayoutManager(Stock_Removal_Activity.this, OrientationHelper.VERTICAL,false));

    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();

        //设置 上拉下拉刷新监听器
        stock_xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                stock_xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                stock_xRecyclerView.loadMoreComplete();
            }
        });


    }

    public void setAdapter(){
        if(stock_adapter==null){
            stock_adapter = new Stock_Adapter(Stock_Removal_Activity.this, stocklist);
            stock_xRecyclerView.setAdapter(stock_adapter);
        }else{
            stock_adapter.notifyDataSetChanged();
        }
        stock_adapter.setStock_jieKou(new Stock_Adapter.Stock_JieKou() {
            @Override
            public void chuan(int postin) {
                MyUtils.setToast("点击了条目==》"+postin);
            }
        });
    }

    private void initView() {
        stock_Fan = (RelativeLayout) findViewById(R.id.stock_Fan);
        stock_xRecyclerView = (XRecyclerView) findViewById(R.id.stock_xRecyclerview);

        stock_xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        stock_xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SemiCircleSpin);
        initData();
    }

    public void initData(){
        for (int i=0;i<20;i++){
            stocklist.add(new Stock_Removal_Bean("福利特","655286224","世博四连体",20,"2018-05-09 09:52:36"));
        }
    }
}
