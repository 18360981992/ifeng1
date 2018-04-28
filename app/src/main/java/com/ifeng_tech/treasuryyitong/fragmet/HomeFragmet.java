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
        View view = inflater.inflate(R.layout.homefragmet, container, false);
        initView(view);
        initData();
        activity = (HomePageActivity) getActivity();

        shouye_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL,false));

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        shouye_RecyclerView.setAdapter(new HomeAdapter(getActivity(),list));
    }

    // 仿数据
    private void initData(){
        String[] IMAGES = {
                "http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/3/2d/dc14dd1e40b8e561eae91584432262d3_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/8/10/1b26d9f0a258255b0431c03a21c0d108_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/3/89/9f5f869c0b6a14d5132550176c761893_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/d/29/dc596253e9e80f28ddc84fe6e52b929d_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/e/d0/03a49009c73496fb8ba6f779fec99d0e_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/2/bf/939a67b179e75326aa932fc476cbdbf2_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/b/fe/718d7c213ce633fd4e25c278c19acfeb_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/f/29/cf90d1294ac84da3b49561a6f304029f_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/4/43/0318ce32731600bfa66cbb5018e1a434_512_512.png",
                "http://img.wdjimg.com/mms/icon/v1/7/08/2b3858e31efdee8a7f28b06bdb83a087_512_512.png"
        };

        imgs = Arrays.asList(IMAGES);
        // 导航图
        gpslist.add(new FirstGpsBean(R.drawable.zhuce,"注册"));
        gpslist.add(new FirstGpsBean(R.drawable.cangpin,"藏品目录"));
        gpslist.add(new FirstGpsBean(R.drawable.zixun,"资讯"));
        gpslist.add(new FirstGpsBean(R.drawable.changchu,"智能仓储"));
        gpslist.add(new FirstGpsBean(R.drawable.shimingrenzheng,"实名认证"));

        // 征集
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","托管进度10/20"));
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","托管进度10/20"));
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","托管进度10/20"));
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","托管进度10/20"));
        collectlist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","托管进度10/20"));

        // 托管
        trusteeshiplist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","截止时间:18/05/02"));
        trusteeshiplist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","截止时间:18/05/02"));
        trusteeshiplist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","截止时间:18/05/02"));
        trusteeshiplist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","截止时间:18/05/02"));
        trusteeshiplist.add(new CollectBean(R.drawable.guangao,"世博四连体","福利特寄卖商城","截止时间:18/05/02"));

        // 资讯
        informationlist.add(new InformationBean("文章标题","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));
        informationlist.add(new InformationBean("文章标题","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));
        informationlist.add(new InformationBean("文章标题","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));
        informationlist.add(new InformationBean("文章标题","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));
        informationlist.add(new InformationBean("文章标题","这里是文章内容，这里是文章内容，这里是文章内容","2018-05-02"));


        list.add(imgs);
        list.add(gpslist);
        list.add(collectlist);
        list.add(trusteeshiplist);
        list.add(informationlist);
    }



    private void initView(View view) {
        shouye_RecyclerView = (RecyclerView) view.findViewById(R.id.shouye_RecyclerView);
    }
}
