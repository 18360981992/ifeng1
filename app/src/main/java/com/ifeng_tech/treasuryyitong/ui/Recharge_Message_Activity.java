package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Message_List_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Message_Lists_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值消息
 */
public class Recharge_Message_Activity extends BaseMVPActivity<Recharge_Message_Activity, MyPresenter<Recharge_Message_Activity>> {

    private RelativeLayout recharge_Fan;
    private MyListView recharge_MyListView;
    private PullToRefreshScrollView recharge_pulltoscroll;
    private LinearLayout recharge_null;

    List<Message_Lists_Bean> list = new ArrayList<Message_Lists_Bean>();
    private Message_List_Adapter message_list_adapter;
    @Override
    public MyPresenter<Recharge_Message_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge__message_);
        initView();

        recharge_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(list.size()>0){
            recharge_null.setVisibility(View.GONE);
            recharge_pulltoscroll.setVisibility(View.VISIBLE);
            setMessageAdapter();

        }else{
            recharge_null.setVisibility(View.VISIBLE);
            recharge_pulltoscroll.setVisibility(View.GONE);
        }

        recharge_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                recharge_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                recharge_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    private void setMessageAdapter() {
        if(message_list_adapter==null){
            message_list_adapter = new Message_List_Adapter(Recharge_Message_Activity.this, list);
            recharge_MyListView.setAdapter(message_list_adapter);
        }else{
            message_list_adapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        recharge_Fan = (RelativeLayout) findViewById(R.id.recharge_Fan);
        recharge_MyListView = (MyListView) findViewById(R.id.recharge_MyListView);
        recharge_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.recharge_pulltoscroll);
        recharge_null = (LinearLayout) findViewById(R.id.recharge_null);

        // 设置刷新
        initRefreshListView();
    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        recharge_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = recharge_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
