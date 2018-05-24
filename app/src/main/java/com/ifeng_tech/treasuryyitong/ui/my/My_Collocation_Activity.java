package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.My_Collocation_list_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Collocation_list_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 *  我的托管
 */
public class My_Collocation_Activity extends BaseMVPActivity<My_Collocation_Activity, MyPresenter<My_Collocation_Activity>> {

    private RelativeLayout my_collocation_Fan;
    private MyListView my_collocation_MyListView;
    private PullToRefreshScrollView my_collocation_pulltoscroll;
    List<Collocation_list_Bean> list = new ArrayList<>();
    private My_Collocation_list_Adapter my_collocation_adapter;
    private LinearLayout my_collocation_null;

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


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(list.size()>0){
            my_collocation_null.setVisibility(View.GONE);
            my_collocation_pulltoscroll.setVisibility(View.VISIBLE);
            // 初始化数据 与适配器
            setMy_Collocation_list_Adapter();
        }else{
            my_collocation_null.setVisibility(View.VISIBLE);
            my_collocation_pulltoscroll.setVisibility(View.GONE);
        }


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

        my_collocation_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(My_Collocation_Activity.this, My_Collocation_Detail_Activity.class);
                intent.putExtra("Collocation_list_Bean",list.get(position));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }

    private void setMy_Collocation_list_Adapter() {

        if(my_collocation_adapter==null){
            my_collocation_adapter = new My_Collocation_list_Adapter(My_Collocation_Activity.this,list);
            my_collocation_MyListView.setAdapter(my_collocation_adapter);
        }else{
            my_collocation_adapter.notifyDataSetChanged();
        }
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
        my_collocation_null = (LinearLayout) findViewById(R.id.my_collocation_null);

        // 设置刷新
        initRefreshListView();
    }

    private void initData() {
        // 征集
        for (int i = 0; i < 15; i++) {
            if(i%2==0)  // type==0 已过期
                list.add(new Collocation_list_Bean("68947594615661",689715675,"世博四连体",20,100,1025689468,0));
            else
                list.add(new Collocation_list_Bean("36987569448952",689715675,"世博四连体",20,99.99,1564897425,1));
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
