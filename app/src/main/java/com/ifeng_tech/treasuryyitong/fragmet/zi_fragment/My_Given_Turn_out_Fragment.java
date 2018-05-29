package com.ifeng_tech.treasuryyitong.fragmet.zi_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.My_Given_list_Adapter;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_Datail_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_list_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2018/5/29.
 * <p>
 * 转出
 */

public class My_Given_Turn_out_Fragment extends Fragment {
    private MyListView my_Given_Turn_out_MyListView;
    private PullToRefreshScrollView my_Given_Turn_out_pulltoscroll;
    private LinearLayout my_Given_Turn_out_null;

    List<Give_List_Bean> list = new ArrayList<>();
    private My_Given_list_Adapter my_given_list_adapter;
    private My_Given_list_Activity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_given_turn_out_fragment, container, false);
        activity = (My_Given_list_Activity) getActivity();

        initView(view);

        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(list.size()>0){
            my_Given_Turn_out_null.setVisibility(View.GONE);
            my_Given_Turn_out_pulltoscroll.setVisibility(View.VISIBLE);
            // 初始化数据 与适配器
            setMy_Given_list_Adapter();
        }else{
            my_Given_Turn_out_null.setVisibility(View.VISIBLE);
            my_Given_Turn_out_pulltoscroll.setVisibility(View.GONE);
        }

        my_Given_Turn_out_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                my_Given_Turn_out_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                my_Given_Turn_out_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });


        // 条目点击事件
        my_Given_Turn_out_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, My_Given_Datail_Activity.class);
                intent.putExtra("Give_List_Bean",list.get(position));
                intent.putExtra("leibie",1);
                startActivity(intent);
            }
        });
    }

    // 初始化数据 与适配器

    private void setMy_Given_list_Adapter() {


        if (my_given_list_adapter == null) {
            my_given_list_adapter = new My_Given_list_Adapter(activity, list,1); // 0==转入
            my_Given_Turn_out_MyListView.setAdapter(my_given_list_adapter);
        } else {
            my_given_list_adapter.notifyDataSetChanged();
        }

    }

    private void initView(View view) {
        my_Given_Turn_out_MyListView = (MyListView) view.findViewById(R.id.my_Given_Turn_out_MyListView);
        my_Given_Turn_out_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.my_Given_Turn_out_pulltoscroll);
        my_Given_Turn_out_null = (LinearLayout) view.findViewById(R.id.my_Given_Turn_out_null);

        initRefreshListView();
    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        my_Given_Turn_out_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = my_Given_Turn_out_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }


    private void initData() {
        // 征集
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0)  // type==0 转出状态
                list.add(new Give_List_Bean("68947594615661", 689715675, "世博四连体", 20, 5689, 1025689468, 0));
            else
                list.add(new Give_List_Bean("36987569448952", 689715675, "世博四连体", 20, 5568, 1564897425, 1));
        }
    }
}
