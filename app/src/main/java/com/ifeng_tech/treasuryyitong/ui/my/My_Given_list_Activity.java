package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.My_Given_list_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 *  转赠列表
 */
public class My_Given_list_Activity extends BaseMVPActivity<My_Given_list_Activity,MyPresenter<My_Given_list_Activity>> {

    private RelativeLayout my_Given_Fan;
    private MyListView my_Given_MyListView;
    private PullToRefreshScrollView my_Given_pulltoscroll;

    List<Give_List_Bean> list = new ArrayList<>();
    private My_Given_list_Adapter my_given_list_adapter;

    @Override
    public MyPresenter<My_Given_list_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__given_list_);
        initView();

        my_Given_Fan.setOnClickListener(new View.OnClickListener() {
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
        setMy_Given_list_Adapter();

        my_Given_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                my_Given_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                my_Given_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 初始化数据 与适配器

    private void setMy_Given_list_Adapter() {
        if(my_given_list_adapter==null){
            my_given_list_adapter = new My_Given_list_Adapter(My_Given_list_Activity.this, list);
            my_Given_MyListView.setAdapter(my_given_list_adapter);
        }else{
            my_given_list_adapter.notifyDataSetChanged();
        }

        my_given_list_adapter.setMy_given_list_adapter_jieKou(new My_Given_list_Adapter.My_Given_list_Adapter_JieKou() {
            @Override
            public void jieShou(int postion) {
                MyUtils.setToast("点击了接收=="+postion);
            }

            @Override
            public void juJue(int postion) {
                MyUtils.setToast("点击了拒绝=="+postion);
            }
        });

    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        my_Given_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = my_Given_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    private void initView() {
        my_Given_Fan = (RelativeLayout) findViewById(R.id.my_Given_Fan);
        my_Given_MyListView = (MyListView) findViewById(R.id.my_Given_MyListView);
        my_Given_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.my_Given_pulltoscroll);

        initData();
    }


    private void initData() {
        // 征集
        for (int i = 0; i < 15; i++) {
            if(i%2==0)  // type==0 转出状态
                list.add(new Give_List_Bean("68947594615661",689715675,"世博四连体",20,5689,1025689468,0));
            else
                list.add(new Give_List_Bean("36987569448952",689715675,"世博四连体",20,5568,1564897425,1));
        }
    }

}
