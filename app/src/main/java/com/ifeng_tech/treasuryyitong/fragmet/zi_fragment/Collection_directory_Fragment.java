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
import com.ifeng_tech.treasuryyitong.adapter.Collection_directory_Adapter;
import com.ifeng_tech.treasuryyitong.bean.Collection_directory_Bean;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.Collection_Directory_Detail_Activity;
import com.ifeng_tech.treasuryyitong.ui.Collection_directory_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2018/5/24.
 */

public class Collection_directory_Fragment extends Fragment {
    private MyListView collection_mulu_fragment_MyListView;
    private PullToRefreshScrollView collection_mulu_fragment_pulltoscroll;
    private LinearLayout collection_mulu_null;

    List<Collection_directory_Bean> arrs = new ArrayList<>();
    List<Collection_directory_Bean> arr1 = new ArrayList<>();
    List<Collection_directory_Bean> arr2 = new ArrayList<>();
    List<Collection_directory_Bean> arr3 = new ArrayList<>();
    private String top;
    private List<Collection_directory_Bean> collection_mulu_Adapter;
    private Collection_directory_Activity activity;
    private Collection_directory_Adapter collection_directory_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collection_directory_fragment, container, false);
        initView(view);
        initData();

        activity = (Collection_directory_Activity) getActivity();
        //获取传递的top值，，，
        top = getArguments().getString("top");

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        final List<Collection_directory_Bean> topList = getTopList(top);

        if (topList != null) {
            collection_mulu_fragment_pulltoscroll.setVisibility(View.VISIBLE);
            collection_mulu_null.setVisibility(View.GONE);
            setcollection_mulu_Adapter(topList);
        } else {
            collection_mulu_fragment_pulltoscroll.setVisibility(View.GONE);
            collection_mulu_null.setVisibility(View.VISIBLE);
        }
        // 下拉刷新  加载
        collection_mulu_fragment_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                collection_mulu_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                collection_mulu_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });

        collection_mulu_fragment_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MyUtils.setToast("点击了藏品目录条目==="+position);
                Intent intent = new Intent(activity, Collection_Directory_Detail_Activity.class);
                intent.putExtra("Collection_directory_Bean",topList.get(position));
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }


    public void setcollection_mulu_Adapter(List<Collection_directory_Bean> topList) {
        if(collection_directory_adapter==null){
            collection_directory_adapter = new Collection_directory_Adapter(activity, topList);
            collection_mulu_fragment_MyListView.setAdapter(collection_directory_adapter);
        }else{
            collection_directory_adapter.notifyDataSetChanged();
        }
    }

    private void initView(View view) {
        collection_mulu_fragment_MyListView = (MyListView) view.findViewById(R.id.collection_mulu_fragment_MyListView);
        collection_mulu_fragment_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.collection_mulu_fragment_pulltoscroll);
        collection_mulu_null = (LinearLayout) view.findViewById(R.id.collection_mulu_null);
        // 设置刷新
        initRefreshListView();
    }
    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        collection_mulu_fragment_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = collection_mulu_fragment_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    private List<Collection_directory_Bean> getTopList(String top) {

        if (top.equals("全部")) {
            return arrs;
        } else if (top.equals("新中国邮票")) {
            return arr1;
        } else if (top.equals("邮资封片")) {
            return arr2;
        } else if (top.equals("“J”字头邮票")) {
            return arr3;
        } else {
            return null;
        }
    }

    // 模拟数据
    private void initData() {
        // 新中国邮票
        for (int i = 0; i < 6; i++) {
            arr1.add(new Collection_directory_Bean(R.drawable.guangao, "2007-7《扬州园林》特种邮票  " +1, 1000111158,99.99,2000,1589754656));
        }

        // 邮资封片
        for (int i = 0; i < 6; i++) {
            arr2.add(new Collection_directory_Bean(R.drawable.guangao, "2007-7《扬州园林》特种邮票  " +2, 1000111158,99.99,2000,1589754656));
        }
        // “J”字头邮票
        for (int i = 0; i < 6; i++) {
            arr3.add(new Collection_directory_Bean(R.drawable.guangao, "2007-7《扬州园林》特种邮票  " +3, 1000111158,99.99,2000,1589754656));
        }

        arrs.addAll(arr1);
        arrs.addAll(arr2);
        arrs.addAll(arr3);
    }


}
