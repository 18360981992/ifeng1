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

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Pick_Up_Goods_Cha_Adapter;
import com.ifeng_tech.treasuryyitong.bean.Pick_Up_Goods_Bean;
import com.ifeng_tech.treasuryyitong.ui.my.Pick_Up_Goods_Detail_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2018/5/14.
 */

public class Pick_up_goods_ChaXun extends Fragment {

    private Pick_up_goods_Activity activity;
    List<Pick_Up_Goods_Bean> list = new ArrayList<>();

    private MyListView pick_up_goods_cha_MyListView;
    private PullToRefreshScrollView pick_up_goods_cha_pulltoscroll;
    private LinearLayout pick_up_goods_cha_null;
    Pick_Up_Goods_Cha_Adapter pick_Up_Goods_Cha_Adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pick_up_goods_chaxun, container, false);
        initView(view);
        initData();

        activity = (Pick_up_goods_Activity) getActivity();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(list.size()>0){
            pick_up_goods_cha_null.setVisibility(View.GONE);
            pick_up_goods_cha_pulltoscroll.setVisibility(View.VISIBLE);
            // 初始化数据 与适配器
            setPick_Up_Goods_Cha_Adapter();
        }else{
            pick_up_goods_cha_null.setVisibility(View.VISIBLE);
            pick_up_goods_cha_pulltoscroll.setVisibility(View.GONE);
        }


        pick_up_goods_cha_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                pick_up_goods_cha_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                pick_up_goods_cha_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });

        pick_up_goods_cha_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, Pick_Up_Goods_Detail_Activity.class);
                intent.putExtra("Pick_Up_Goods_Bean",list.get(position));
                startActivity(intent);
            }
        });
    }

    private void setPick_Up_Goods_Cha_Adapter() {

        if(pick_Up_Goods_Cha_Adapter==null){

            pick_Up_Goods_Cha_Adapter = new Pick_Up_Goods_Cha_Adapter(activity,list);
            pick_up_goods_cha_MyListView.setAdapter(pick_Up_Goods_Cha_Adapter);
        }else{
            pick_Up_Goods_Cha_Adapter.notifyDataSetChanged();
        }
    }


    private void initView(View view) {
        pick_up_goods_cha_MyListView = (MyListView) view.findViewById(R.id.pick_up_goods_cha_MyListView);
        pick_up_goods_cha_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.pick_up_goods_cha_pulltoscroll);
        pick_up_goods_cha_null = (LinearLayout) view.findViewById(R.id.pick_up_goods_cha_null);

        // 设置刷新
        initRefreshListView();
    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        pick_up_goods_cha_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = pick_up_goods_cha_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    private void initData() {
        // 征集
        for (int i = 0; i < 15; i++) {
            if(i%2==0)  // type==0 等待提货
                list.add(new Pick_Up_Goods_Bean("68947594615661",689715675,"世博四连体",20,100,1025689468,0));
            else
                list.add(new Pick_Up_Goods_Bean("36987569448952",689715675,"世博四连体",20,99.99,1564897425,1));
        }
    }
}
