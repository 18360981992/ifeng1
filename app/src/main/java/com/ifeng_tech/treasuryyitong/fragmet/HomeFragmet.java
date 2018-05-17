package com.ifeng_tech.treasuryyitong.fragmet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.HomeAdapter;
import com.ifeng_tech.treasuryyitong.bean.CollectBean;
import com.ifeng_tech.treasuryyitong.bean.FirstGpsBean;
import com.ifeng_tech.treasuryyitong.bean.InformationBean;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;

import java.util.ArrayList;
import java.util.Arrays;
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
    List<CollectBean> trusteeshiplist=new ArrayList<>();
    List<InformationBean> informationlist=new ArrayList<>();
    List<String> imgs;
    private HomePageActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragmet, container, false);
        initView(view);
        initData();
        activity = (HomePageActivity) getActivity();

        shouye_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL,false));

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        shouye_RecyclerView.setAdapter(new HomeAdapter(activity,list));
    }

    // 仿数据
    private void initData(){
        String[] IMAGES = {
                "http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/3/2d/dc14dd1e40b8e561eae91584432262d3_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/8/10/1b26d9f0a258255b0431c03a21c0d108_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/3/89/9f5f869c0b6a14d5132550176c761893_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/d/29/dc596253e9e80f28ddc84fe6e52b929d_512_512.png"
        };

        imgs = Arrays.asList(IMAGES);
        // 导航图
        gpslist.add(new FirstGpsBean(R.drawable.saoyisao,"扫一扫"));
        gpslist.add(new FirstGpsBean(R.drawable.shouhuo,"收货"));
        gpslist.add(new FirstGpsBean(R.drawable.zhuanzeng,"转赠"));
        gpslist.add(new FirstGpsBean(R.drawable.jianding,"鉴定"));

        // 征集
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","托管进度10/20",0));
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","托管进度10/20",1));
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","托管进度10/20",0));

        // 托管
        trusteeshiplist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","截止时间:18/05/02",0));
        trusteeshiplist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","截止时间:18/05/02",1));
        trusteeshiplist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","截止时间:18/05/02",0));



        // 资讯
        informationlist.add(new InformationBean("子栏目","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));
        informationlist.add(new InformationBean("子栏目","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));
        informationlist.add(new InformationBean("子栏目","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));


        list.add(imgs);
        list.add(gpslist);
        list.add(collectlist);
        list.add("http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F19%2F02%2F76%2F5672530f7d928_1024.jpg&thumburl=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D2176610467%2C2174515257%26fm%3D27%26gp%3D0.jpg");
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
