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
import com.ifeng_tech.treasuryyitong.adapter.Information_zi_Adapter;
import com.ifeng_tech.treasuryyitong.bean.Information_Zi_Bean;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.Information_Details_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2018/5/7.
 * <p>
 * 资讯的 子fragment
 */

public class Information_zi_Fragment extends Fragment {

    private HomePageActivity activity;
    private LinearLayout information_zi_null;
    private MyListView information_zi_fragment_MyListView;
    private PullToRefreshScrollView information_zi_fragment_pulltoscroll;

    List<Information_Zi_Bean> arrs = new ArrayList<>();
    List<Information_Zi_Bean> arr1 = new ArrayList<>();
    List<Information_Zi_Bean> arr2 = new ArrayList<>();
    List<Information_Zi_Bean> arr3 = new ArrayList<>();
    List<Information_Zi_Bean> arr4 = new ArrayList<>();
    private Information_zi_Adapter information_zi_adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_zi_fragment, container, false);
        initView(view);
        initData();

        activity = (HomePageActivity) getActivity();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //获取传递的top值，，
        String top = getArguments().getString("top");

        final List<Information_Zi_Bean> topList = getTopList(top);

        if (topList != null) {
            information_zi_fragment_pulltoscroll.setVisibility(View.VISIBLE);
            information_zi_null.setVisibility(View.GONE);
            setAdapter(topList);
        } else {
            information_zi_fragment_pulltoscroll.setVisibility(View.GONE);
            information_zi_null.setVisibility(View.VISIBLE);
        }

        // 下拉刷新  加载
        information_zi_fragment_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                information_zi_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                information_zi_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });

        // 条目点击事件
        information_zi_fragment_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                MyUtils.setToast("点击了=="+topList.get(position).getTop()+"的第"+position+"条");

                Intent intent = new Intent(activity, Information_Details_Activity.class);
//                intent.putExtra("InformationBean",topList.get(postion));
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }

    private void setAdapter(List<Information_Zi_Bean> topList) {
        if (information_zi_adapter == null) {
            information_zi_adapter = new Information_zi_Adapter(activity, topList);
            information_zi_fragment_MyListView.setAdapter(information_zi_adapter);
        } else {
            information_zi_adapter.notifyDataSetChanged();
        }
    }

    private List<Information_Zi_Bean> getTopList(String top) {

        if (top.equals("全部")) {
            return arrs;
        } else if (top.equals("热门")) {
            return arr1;
        } else if (top.equals("关注")) {
            return arr2;
        } else if (top.equals("栏目1")) {
            return arr3;
        } else if (top.equals("栏目2")) {
            return arr4;
        } else {
            return null;
        }
    }


    String[] img = {"http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png"};
    String[] imgs = {"http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png"};

    // 模拟数据
    private void initData() {
        // 热门
        for (int i = 0; i < 4; i++) {
            arr1.add(new Information_Zi_Bean(img, "热门", "这是内容...这是内容...这是内容...这是内容...", "2018-05-02"));
            if (i == 3) {
                arr1.add(new Information_Zi_Bean(imgs, "热门", "这是内容..这是内容...这是内容...这是内容...这是内容....", "2018-05-02"));
            }
        }

        // 关注
        for (int i = 0; i < 4; i++) {
            arr2.add(new Information_Zi_Bean(img, "关注", "这是内容.这是内容...这是内容...这是内容.....", "2018-05-02"));
            if (i == 3) {
                arr2.add(new Information_Zi_Bean(imgs, "关注", "这是内容..这是内容...这是内容...这是内容....", "2018-05-02"));
            }
        }
        // 栏目1
        for (int i = 0; i < 4; i++) {
            arr3.add(new Information_Zi_Bean(img, "栏目1", "这是内容..这是内容...这是内容...这是内容....", "2018-05-02"));
            if (i == 3) {
                arr3.add(new Information_Zi_Bean(imgs, "栏目1", "这是内容..这是内容...这是内容...这是内容....", "2018-05-02"));
            }
        }
        // 栏目2
        for (int i = 0; i < 4; i++) {
            arr4.add(new Information_Zi_Bean(img, "栏目2", "这是内容...这是内容...这是内容...这是内容...这是内容.....", "2018-05-02"));
            if (i == 3) {
                arr4.add(new Information_Zi_Bean(imgs, "栏目2", "这是内容.这是内容...这是内容...这是内容...这是内容......", "2018-05-02"));
            }
        }

        arrs.addAll(arr1);
        arrs.addAll(arr2);
        arrs.addAll(arr3);
        arrs.addAll(arr4);
    }

    private void initView(View view) {
        information_zi_fragment_MyListView = (MyListView) view.findViewById(R.id.information_zi_fragment_MyListView);
        information_zi_fragment_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.information_zi_fragment_pulltoscroll);
        information_zi_null = view.findViewById(R.id.information_zi_null);
        // 设置刷新
        initRefreshListView();
    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        information_zi_fragment_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = information_zi_fragment_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

}
