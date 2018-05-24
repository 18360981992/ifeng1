package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.HomeAdapter;
import com.ifeng_tech.treasuryyitong.bean.CollectBean;
import com.ifeng_tech.treasuryyitong.bean.CollocationBean;
import com.ifeng_tech.treasuryyitong.bean.FirstGpsBean;
import com.ifeng_tech.treasuryyitong.bean.InformationBean;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2018/4/27.
 *
 *  首页
 *
 */

public class HomeFragmet extends Fragment {

    private RecyclerView shouye_RecyclerView;
    List<Object> list = new ArrayList<>();
    List<FirstGpsBean> gpslist=new ArrayList<>();
    List<CollectBean> collectlist=new ArrayList<>();
    List<CollocationBean> trusteeshiplist=new ArrayList<>();
    List<InformationBean> informationlist=new ArrayList<>();
    List<Integer> imgs = new ArrayList<>();
    private HomePageActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragmet, container, false);

        activity = (HomePageActivity) getActivity();

        // 设置状态栏
        setActionBar();

        initView(view);
        initData();

        shouye_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL,false));

        return view;
    }
    // 设置状态栏
    private void setActionBar() {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = getStatusBarHeight(window.getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackground(getResources().getDrawable(R.drawable.zichan_jianbian));
        decorViewGroup.addView(statusBarView);
    }
    // 获取状态栏高度
    private int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


    @Override
    public void onResume() {
        super.onResume();
        shouye_RecyclerView.setAdapter(new HomeAdapter(activity,list));
    }

    // 仿数据
    private void initData(){

        imgs.add(R.mipmap.band1);
        imgs.add(R.mipmap.band2);
        // 导航图
        gpslist.add(new FirstGpsBean(R.drawable.saoyisao,"扫一扫"));
        gpslist.add(new FirstGpsBean(R.drawable.shouhuo,"收货"));
        gpslist.add(new FirstGpsBean(R.drawable.zhuanzeng,"转赠"));
        gpslist.add(new FirstGpsBean(R.drawable.jianding,"鉴定"));

        // 征集
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体",689715675,"福利特寄卖商城","托管进度10/20",0));
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体",689715675,"福利特寄卖商城","托管进度10/20",1));
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体",689715675,"福利特寄卖商城","托管进度10/20",0));

        // 托管
        trusteeshiplist.add(new CollocationBean(R.drawable.guangao,"世博四连体",689715675,1025689468,"托管进度10/20",0));
        trusteeshiplist.add(new CollocationBean(R.drawable.guangao,"世博四连体",689715675,1025689468,"托管进度10/20",1));
        trusteeshiplist.add(new CollocationBean(R.drawable.guangao,"世博四连体",689715675,1025689468,"托管进度10/20",0));



        // 资讯
        informationlist.add(new InformationBean("子栏目","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));
        informationlist.add(new InformationBean("子栏目","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));
        informationlist.add(new InformationBean("子栏目","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));


        list.add(imgs);
        list.add(gpslist);
        list.add(collectlist);
        list.add(R.drawable.cangpinmulu);
        list.add(trusteeshiplist);
        list.add(informationlist);
    }



    private void initView(View view) {
        shouye_RecyclerView = (RecyclerView) view.findViewById(R.id.shouye_RecyclerView);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
