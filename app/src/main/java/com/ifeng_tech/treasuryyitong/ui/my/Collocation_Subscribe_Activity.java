package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Collocation_Subscribe_list_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.CollocationBean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.Authenticate_Details_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 托管预约列表
 */
public class Collocation_Subscribe_Activity extends BaseMVPActivity<Collocation_Subscribe_Activity,MyPresenter<Collocation_Subscribe_Activity>> {

    private RelativeLayout collocation_Subscribe_Fan;
    private RelativeLayout authenticate_Details_Toobar;
    private XBanner collocation_Subscribe_XBanner;
    private MyListView collocation_Subscribe_MyListView;
    private PullToRefreshScrollView collocation_Subscribe_pulltoscroll;

    private List<String> imgs;
    String[] IMAGES = {
            "http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/3/2d/dc14dd1e40b8e561eae91584432262d3_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/8/10/1b26d9f0a258255b0431c03a21c0d108_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/3/89/9f5f869c0b6a14d5132550176c761893_512_512.png",
    };

    List<CollocationBean> list = new ArrayList<>();
    private Collocation_Subscribe_list_Adapter collocation_subscribe_list_adapter;
    private LinearLayout collocation_subscribe_null;

    @Override
    public MyPresenter<Collocation_Subscribe_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collocation__subscribe_);
        initView();

        collocation_Subscribe_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /**
         * 解决scrollview 显示不在顶部问题
         */
        collocation_Subscribe_XBanner.setFocusable(true);
        collocation_Subscribe_XBanner.setFocusableInTouchMode(true);
        collocation_Subscribe_XBanner.requestFocus();

        // 设置刷新
        initRefreshListView();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(imgs.size()<=0||list.size()<=0){
            collocation_subscribe_null.setVisibility(View.VISIBLE);
            collocation_Subscribe_pulltoscroll.setVisibility(View.GONE);
        }else{
            collocation_subscribe_null.setVisibility(View.GONE);
            collocation_Subscribe_pulltoscroll.setVisibility(View.VISIBLE);

            collocation_Subscribe_XBanner.setData(imgs,null);//设置数据源
            collocation_Subscribe_XBanner.setmAdapter(new XBanner.XBannerAdapter() {//xbanner的适配器，加载图片
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(Collocation_Subscribe_Activity.this).load(imgs.get(position)).into((ImageView) view);
                }
            });
            // 初始数据
            setCollocation_sub_list_Adapter();

        }



        collocation_Subscribe_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                MyUtils.setToast("下拉了。。。");

                collocation_Subscribe_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                collocation_Subscribe_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });

        collocation_Subscribe_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).getType()==0){ // 0==等待 1==未开始
                    Intent intent = new Intent(Collocation_Subscribe_Activity.this, Authenticate_Details_Activity.class);
                    intent.putExtra("CollocationBean",list.get(position));
                    startActivity(intent);
                }else{
                    MyUtils.setToast("该商品还未开始托管。。。");
                }
            }
        });


    }

    // 适配器的设置
    private void setCollocation_sub_list_Adapter() {
        if(collocation_subscribe_list_adapter==null){
            collocation_subscribe_list_adapter = new Collocation_Subscribe_list_Adapter(Collocation_Subscribe_Activity.this, list);
            collocation_Subscribe_MyListView.setAdapter(collocation_subscribe_list_adapter);
        }else{
            collocation_subscribe_list_adapter.notifyDataSetChanged();
        }

    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        collocation_Subscribe_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = collocation_Subscribe_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    private void initView() {
        collocation_Subscribe_Fan = (RelativeLayout) findViewById(R.id.collocation_Subscribe_Fan);
        authenticate_Details_Toobar = (RelativeLayout) findViewById(R.id.authenticate_Details_Toobar);
        collocation_Subscribe_XBanner = (XBanner) findViewById(R.id.collocation_Subscribe_XBanner);
        collocation_Subscribe_MyListView = (MyListView) findViewById(R.id.collocation_Subscribe_MyListView);
        collocation_Subscribe_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.collocation_Subscribe_pulltoscroll);
        collocation_subscribe_null = (LinearLayout) findViewById(R.id.collocation_Subscribe_null);

        initData();
    }


    private void initData() {

        // 托管预约
        for (int i = 0; i < 15; i++) {
            if(i%2==0)
                list.add(new CollocationBean(R.drawable.guangao,"世博四连体",689715675,1025689468,"托管进度10/20",0));
            else
                list.add(new CollocationBean(R.drawable.guangao,"世博四连体",689715675,1025689468,"托管进度10/20",1));
        }

        imgs = Arrays.asList(IMAGES);
    }
}
