package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.My_Collocation_list_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Collocation_list_Bean;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

public class My_Collocation_Activity extends BaseMVPActivity<My_Collocation_Activity, MyPresenter<My_Collocation_Activity>> {

    private RelativeLayout my_collocation_Fan;
    private MyListView my_collocation_MyListView;
    private PullToRefreshScrollView my_collocation_pulltoscroll;
    List<Collocation_list_Bean> list = new ArrayList<>();
    @Override
    public MyPresenter<My_Collocation_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__collocation_);
        initView();
        initData();

        my_collocation_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 设置刷新
        initRefreshListView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // 初始化数据 与适配器
        setMy_Collocation_list_Adapter();

        my_collocation_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                my_collocation_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                my_collocation_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    private void setMy_Collocation_list_Adapter() {
        My_Collocation_list_Adapter my_collocation_adapter = new My_Collocation_list_Adapter(My_Collocation_Activity.this,list);

    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        my_collocation_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = my_collocation_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    private void initView() {
        my_collocation_Fan = (RelativeLayout) findViewById(R.id.my_collocation_Fan);
        my_collocation_MyListView = (MyListView) findViewById(R.id.my_collocation_MyListView);
        my_collocation_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.my_collocation_pulltoscroll);
    }

    private void initData() {
        // 征集
        for (int i = 0; i < 15; i++) {
            if(i%2==0)  // type==0 转出状态
                list.add(new Collocation_list_Bean("68947594615661",689715675,"世博四连体",20,100,1025689468,0));
            else
                list.add(new Collocation_list_Bean("36987569448952",689715675,"世博四连体",20,99.99,1564897425,1));
        }
    }
}
