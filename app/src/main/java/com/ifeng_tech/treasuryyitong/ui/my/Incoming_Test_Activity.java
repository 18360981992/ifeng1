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
 * 入库管理
 */
public class Incoming_Test_Activity extends BaseMVPActivity<Incoming_Test_Activity, MyPresenter<Incoming_Test_Activity>> {

    private RelativeLayout incoming_Fan;
    private MyListView incoming_MyListView;
    private PullToRefreshScrollView incoming_pulltoscroll;
    private LinearLayout incoming_null;

    List<Incoming_bean> incominglist = new ArrayList<>();
    private Incoming_Test_Adapter incoming_test_adapter;


    @Override
    public MyPresenter<Incoming_Test_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming__test_);
        initView();
        initData();


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

        // 下拉刷新  加载
        incoming_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                incoming_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                incoming_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }


    public void setAdapter() {
        if (incoming_test_adapter == null) {
            incoming_test_adapter = new Incoming_Test_Adapter(Incoming_Test_Activity.this, incominglist);
            incoming_MyListView.setAdapter(incoming_test_adapter);
        } else {
            incoming_test_adapter.notifyDataSetChanged();
        }

    }

    private void initView() {
        incoming_Fan = (RelativeLayout) findViewById(R.id.incoming_Fan);
        incoming_MyListView = (MyListView) findViewById(R.id.incoming_MyListView);
        incoming_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.incoming_pulltoscroll);
        incoming_null = (LinearLayout) findViewById(R.id.incoming_null);

        // 设置刷新
        initRefreshListView();
    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        incoming_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = incoming_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    public void initData() {
        for (int i = 0; i < 20; i++) {
            incominglist.add(new Incoming_bean("福利特", "655286224", "世博四连体", 20, "待审核", "2018-05-09 09:52:36"));
        }
    }
}
