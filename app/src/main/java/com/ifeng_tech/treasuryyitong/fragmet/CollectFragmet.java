package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.CollectAdapter;
import com.ifeng_tech.treasuryyitong.bean.CollectBean;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.LoginActivity;
import com.ifeng_tech.treasuryyitong.ui.my.Collect_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

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

    List<Integer> imgs = new ArrayList<>();
    List<CollectBean> collectlist = new ArrayList<>();

    private LinearLayout collect_null;
    private CollectAdapter collectAdapter;
    private boolean aBoolean;
    private SharedPreferences.Editor edit;


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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sp = activity.getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();
        aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);

        if(imgs.size()<=0||collectlist.size()<=0){
            collect_null.setVisibility(View.VISIBLE);
            collect_pulltoscroll.setVisibility(View.GONE);
        }else{
            collect_null.setVisibility(View.GONE);
            collect_pulltoscroll.setVisibility(View.VISIBLE);

            collect_XBanner.setData(imgs,null);//设置数据源
            collect_XBanner.setmAdapter(new XBanner.XBannerAdapter() {//xbanner的适配器，加载图片
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    ((ImageView) view).setImageResource(imgs.get(position));
//                    Glide.with(activity).load(imgs.get(position)).into((ImageView) view);
                }
            });
            setCollectAdapter();
        }

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

        collect_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(aBoolean){
                    if(collectlist.get(position).getType()==0){ // 为0的时候可以点击进入征集页面
                        Intent intent = new Intent(activity, Collect_Activity.class);
                        intent.putExtra("CollectBean",collectlist.get(position));
                        startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                    }else{
                        MyUtils.setToast("该商品还未开始征集。。。");
                    }
                }else{
                    Intent intent1 = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });

    }

    /**
     *  初始化适配器
     */
    private void setCollectAdapter() {
        if(collectAdapter==null){
            collectAdapter = new CollectAdapter(activity, collectlist);
            collect_MyListView.setAdapter(collectAdapter);
        }else{
            collectAdapter.notifyDataSetChanged();
        }

    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        collect_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = collect_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }


    private void initData() {

        imgs.add(R.mipmap.band1);
        imgs.add(R.mipmap.band2);
        // 征集
        for (int i = 0; i < 15; i++) {
            if(i%2==0)
                collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体",689715675,"福利特寄卖商城","托管进度10/20",0));
            else
                collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体",689715675,"福利特寄卖商城","托管进度10/20",1));
        }

    }

    private void initView(View view) {
        collect_MyListView = (MyListView) view.findViewById(R.id.collect_MyListView);
        collect_XBanner = view.findViewById(R.id.collect_XBanner);
        collect_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.collect_pulltoscroll);
        collect_null = view.findViewById(R.id.collect_null);

        // 设置刷新
        initRefreshListView();

        initData();
    }
}
