package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.CollectAdapter;
import com.ifeng_tech.treasuryyitong.bean.CollectBean;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.my.Collect_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 * <p>
 * 征集
 */

public class CollectFragmet extends Fragment {

    private HomePageActivity activity;
    private MyListView collect_MyListView;
    private PullToRefreshScrollView collect_pulltoscroll;
    private XBanner collect_XBanner;

    private List<String> imgs;
    List<CollectBean> collectlist = new ArrayList<>();
    String[] IMAGES = {
            "http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/3/2d/dc14dd1e40b8e561eae91584432262d3_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/8/10/1b26d9f0a258255b0431c03a21c0d108_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/3/89/9f5f869c0b6a14d5132550176c761893_512_512.png",
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collect_fragmet, container, false);
        initView(view);

        activity = (HomePageActivity) getActivity();

        /**
         * 解决scrollview 显示不在顶部问题
         */
        collect_XBanner.setFocusable(true);
        collect_XBanner.setFocusableInTouchMode(true);
        collect_XBanner.requestFocus();

        // 设置刷新
        initRefreshListView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        collect_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                collect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                collect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });


        collect_XBanner.setData(imgs,null);//设置数据源
        collect_XBanner.setmAdapter(new XBanner.XBannerAdapter() {//xbanner的适配器，加载图片
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(activity).load(imgs.get(position)).into((ImageView) view);
            }
        });

        CollectAdapter collectAdapter = new CollectAdapter(activity, collectlist);
        collect_MyListView.setAdapter(collectAdapter);


        collect_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(collectlist.get(position).getType()==0){ // 为0的时候可以点击进入征集页面
                    Intent intent = new Intent(activity, Collect_Activity.class);
                    intent.putExtra("CollectBean",collectlist.get(position));
                    startActivity(intent);
                }else{
                    MyUtils.setToast("该商品还未开始征集。。。");
                }
            }
        });

    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        collect_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = collect_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("用力拉...");
        Labels.setRefreshingLabel("释放立即刷新...");
        Labels.setReleaseLabel("正在刷新...");
    }


    private void initData() {
        // 征集
        for (int i = 0; i < 15; i++) {
            if(i%2==0)
                collectlist.add(new CollectBean(R.drawable.guangao, "世博四连体", "福利特寄卖商城", "托管进度10/20",0));
            else
                collectlist.add(new CollectBean(R.drawable.guangao, "世博四连体", "福利特寄卖商城", "托管进度10/20", 1));
        }

        imgs = Arrays.asList(IMAGES);
    }

    private void initView(View view) {
        collect_MyListView = (MyListView) view.findViewById(R.id.collect_MyListView);
        collect_XBanner = view.findViewById(R.id.collect_XBanner);
        collect_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.collect_pulltoscroll);

        initData();
    }
}
