package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Incoming_Test_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Incoming_bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的征集
 */
public class MyCollectActivity extends BaseMVPActivity<MyCollectActivity, MyPresenter<MyCollectActivity>> {

    private RelativeLayout mycollect_Fan;

    List<Incoming_bean> incominglist = new ArrayList<>();
    private Incoming_Test_Adapter incoming_test_adapter;
    private LinearLayout mycollect_null;
    private MyListView mycollect_MyListView;
    private PullToRefreshScrollView mycollect_pulltoscroll;

    @Override
    public MyPresenter<MyCollectActivity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        initView();
        initData();

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

        if (incominglist.size() > 0) {
            mycollect_null.setVisibility(View.GONE);
            mycollect_pulltoscroll.setVisibility(View.VISIBLE);
            setAdapter();
        } else {
            mycollect_null.setVisibility(View.VISIBLE);
            mycollect_pulltoscroll.setVisibility(View.GONE);
        }

        // 下拉刷新  加载
        mycollect_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                mycollect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                mycollect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });

    }


    public void setAdapter() {
        if (incoming_test_adapter == null) {
            incoming_test_adapter = new Incoming_Test_Adapter(MyCollectActivity.this, incominglist);
            mycollect_MyListView.setAdapter(incoming_test_adapter);
        } else {
            incoming_test_adapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        mycollect_Fan = (RelativeLayout) findViewById(R.id.mycollect_Fan);
        mycollect_MyListView = (MyListView) findViewById(R.id.mycollect_MyListView);
        mycollect_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.mycollect_pulltoscroll);
        mycollect_null = (LinearLayout) findViewById(R.id.mycollect_null);
        // 设置刷新
        initRefreshListView();
    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        mycollect_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = mycollect_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }



    public void initData() {
        for (int i = 0; i < 20; i++) {
            incominglist.add(new Incoming_bean("福利特", "655286224", "世博四连体", 20, "征集中", "2018-05-09 09:52:36"));
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
