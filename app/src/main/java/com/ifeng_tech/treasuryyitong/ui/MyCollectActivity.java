package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Incoming_Test_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Incoming_bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的征集
 */
public class MyCollectActivity extends BaseMVPActivity<MyCollectActivity,MyPresenter<MyCollectActivity>> {

    private RelativeLayout mycollect_Fan;
    private XRecyclerView mycollect_xRecyclerView;

    List<Incoming_bean> incominglist = new ArrayList<>();
    private Incoming_Test_Adapter incoming_test_adapter;

    @Override
    public MyPresenter<MyCollectActivity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        initView();
        mycollect_xRecyclerView.setLayoutManager(new LinearLayoutManager(MyCollectActivity.this, OrientationHelper.VERTICAL,false));

        mycollect_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();

        mycollect_xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mycollect_xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mycollect_xRecyclerView.loadMoreComplete();
            }
        });
    }

    private void initView() {
        mycollect_Fan = (RelativeLayout) findViewById(R.id.mycollect_Fan);
        mycollect_xRecyclerView = (XRecyclerView) findViewById(R.id.mycollect_xRecyclerView);

        mycollect_xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mycollect_xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        initData();
    }

    public void setAdapter(){
        if (incoming_test_adapter==null){
            incoming_test_adapter = new Incoming_Test_Adapter(MyCollectActivity.this, incominglist);
            mycollect_xRecyclerView.setAdapter(incoming_test_adapter);
        }else{
            incoming_test_adapter.notifyDataSetChanged();
        }

        incoming_test_adapter.setIncoming_JieKou(new Incoming_Test_Adapter.Incoming_JieKou() {
            @Override
            public void chuan(int postin) {
                MyUtils.setToast("点击了条目==》"+postin);
            }
        });

    }

    public void initData(){
        for (int i=0;i<20;i++){
            incominglist.add(new Incoming_bean("福利特","655286224","世博四连体",20,"征集中","2018-05-09 09:52:36"));
        }
    }
}
