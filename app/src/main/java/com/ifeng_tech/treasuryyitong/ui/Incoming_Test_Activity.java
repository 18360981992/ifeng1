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
 * 入库管理
 */
public class Incoming_Test_Activity extends BaseMVPActivity<Incoming_Test_Activity,MyPresenter<Incoming_Test_Activity>> {

    private RelativeLayout incoming_Fan;
    private XRecyclerView incoming_xRecyclerView;
    List<Incoming_bean> incominglist = new ArrayList<>();
    private Incoming_Test_Adapter incoming_test_adapter;

    @Override
    public MyPresenter<Incoming_Test_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming__test_);
        initView();


        incoming_xRecyclerView.setLayoutManager(new LinearLayoutManager(Incoming_Test_Activity.this, OrientationHelper.VERTICAL,false));
        incoming_Fan.setOnClickListener(new View.OnClickListener() {
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

        incoming_xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                incoming_xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                incoming_xRecyclerView.loadMoreComplete();
            }
        });
    }


    public void setAdapter(){
        if (incoming_test_adapter==null){
            incoming_test_adapter = new Incoming_Test_Adapter(Incoming_Test_Activity.this, incominglist);
            incoming_xRecyclerView.setAdapter(incoming_test_adapter);
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
    private void initView() {
        incoming_Fan = (RelativeLayout) findViewById(R.id.incoming_Fan);
        incoming_xRecyclerView = (XRecyclerView) findViewById(R.id.incoming_xRecyclerView);

        incoming_xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        incoming_xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        initData();
    }


    public void initData(){
        for (int i=0;i<20;i++){
            incominglist.add(new Incoming_bean("福利特","655286224","世博四连体",20,"待审核","2018-05-09 09:52:36"));
        }
    }
}
