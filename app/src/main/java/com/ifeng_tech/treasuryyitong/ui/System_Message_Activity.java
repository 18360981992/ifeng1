package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Message_List_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Message_Lists_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统消息
 */
public class System_Message_Activity extends BaseMVPActivity<System_Message_Activity,MyPresenter<System_Message_Activity>> {

    private RelativeLayout system_Fan;
    private MyListView system_MyListView;
    private PullToRefreshScrollView system_pulltoscroll;
    private LinearLayout system_null;

    List<Message_Lists_Bean> list = new ArrayList<Message_Lists_Bean>();
    private Message_List_Adapter message_list_adapter;

    @Override
    public MyPresenter<System_Message_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system__message_);
        initView();
        initData();

        system_Fan.setOnClickListener(new View.OnClickListener() {
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
            system_null.setVisibility(View.GONE);
            system_pulltoscroll.setVisibility(View.VISIBLE);
            setMessageAdapter();
        }else{
            system_null.setVisibility(View.VISIBLE);
            system_pulltoscroll.setVisibility(View.GONE);
        }

        system_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                system_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                system_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }


    private void setMessageAdapter() {
        if(message_list_adapter==null){
            message_list_adapter = new Message_List_Adapter(System_Message_Activity.this, list);
            system_MyListView.setAdapter(message_list_adapter);
        }else{
            message_list_adapter.notifyDataSetChanged();
        }
    }


    private void initView() {
        system_Fan = (RelativeLayout) findViewById(R.id.system_Fan);
        system_MyListView = (MyListView) findViewById(R.id.system_MyListView);
        system_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.system_pulltoscroll);
        system_null = (LinearLayout) findViewById(R.id.system_null);

        // 设置刷新
        initRefreshListView();
    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        system_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = system_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    public void initData(){
        for (int i=0;i<15;i++){
            list.add(new Message_Lists_Bean(1255689741,"版本更新提醒","系统1.1.1已经更新"));
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
