package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Warehouse_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.ifeng_tech.treasuryyitong.R.id.my_Given_pulltoscroll;
import static com.ifeng_tech.treasuryyitong.R.id.my_warehouse__XRecyclerView;

/**
 * 我的仓库
 *
 */

public class My_Warehouse_Activity extends BaseMVPActivity<My_Warehouse_Activity,MyPresenter<My_Warehouse_Activity>> {

    private RelativeLayout my_warehouse_Fan;
    private ImageView my_warehouse_zhangdan;
    private XRecyclerView my_warehouse_XRecyclerView;

    List<WarehouseBean> list = new ArrayList<WarehouseBean>();
    private Warehouse_Adapter warehouse_adapter;
    private LinearLayout my_warehouse_null;

    @Override
    public MyPresenter<My_Warehouse_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__warehouse_);
        initView();

        my_warehouse_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        my_warehouse_zhangdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("点击了账单。。。");
            }
        });

        my_warehouse_XRecyclerView.setLayoutManager(new LinearLayoutManager(My_Warehouse_Activity.this, OrientationHelper.VERTICAL,false));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(list.size()>0){
            my_warehouse_null.setVisibility(View.GONE);
            my_warehouse_XRecyclerView.setVisibility(View.VISIBLE);
            // 初始化数据 与适配器
            setAdapter();
        }else{
            my_warehouse_null.setVisibility(View.VISIBLE);
            my_warehouse_XRecyclerView.setVisibility(View.GONE);
        }


        my_warehouse_XRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                my_warehouse_XRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                my_warehouse_XRecyclerView.loadMoreComplete();
            }
        });
    }

    public void setAdapter(){
        if (warehouse_adapter==null){
            warehouse_adapter = new Warehouse_Adapter(My_Warehouse_Activity.this, list);
            my_warehouse_XRecyclerView.setAdapter(warehouse_adapter);
        }else{
            warehouse_adapter.notifyDataSetChanged();
        }

        warehouse_adapter.setWarehouse_adapter_jieKou(new Warehouse_Adapter.Warehouse_Adapter_JieKou() {
            @Override
            public void tihuo_chuan(int postion) { // 跳转提货
                Intent intent = new Intent(My_Warehouse_Activity.this, Pick_up_goods_Activity.class);
                intent.putExtra("WarehouseBean",  list.get(postion));
                startActivity(intent);
            }

            @Override
            public void zhuanzeng_chuan(int postion) { // 跳转转赠
                Intent intent = new Intent(My_Warehouse_Activity.this, Donation_Activity.class);
                intent.putExtra("WarehouseBean",  list.get(postion));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        my_warehouse_Fan = (RelativeLayout) findViewById(R.id.my_warehouse_Fan);
        my_warehouse_zhangdan = (ImageView) findViewById(R.id.my_warehouse_zhangdan);
        my_warehouse_XRecyclerView = (XRecyclerView) findViewById(my_warehouse__XRecyclerView);
        my_warehouse_null = (LinearLayout) findViewById(R.id.my_warehouse_null);

        my_warehouse_XRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        my_warehouse_XRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);


        initData();
    }

    public void initData(){

        for (int i=0;i<20;i++){
            list.add(new WarehouseBean("655286224",R.drawable.guangao,"世博四连体",2000,1000));
        }
    }
}
