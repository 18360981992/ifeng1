package com.ifeng_tech.treasuryyitong.ui.my.tuoguan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Collocation_Subscribe_list_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.SelectAdvertise_Bean;
import com.ifeng_tech.treasuryyitong.bean.my.Collocation_Subscribe_bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.Information_Details_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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

    List<Integer> imgs = new ArrayList<>();

    List<Collocation_Subscribe_bean.DataBean.ListBean> list = new ArrayList<>();
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

    }


    int pageNum=1;
    @Override
    protected void onResume() {
        super.onResume();
        getBannder(); // 获取轮播图


        pageNum=1;
        String currentTrusteeshipList = APIs.getCurrentTrusteeshipList(pageNum,10);
        getFirstConect(currentTrusteeshipList);
        collocation_Subscribe_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                MyUtils.setToast("下拉了。。。");
                try{
                    collocation_Subscribe_XBanner.stopAutoPlay();
                    if(collocation_Subscribe_XBanner.getRealCount()>0){
                        getBannder(); // 获取轮播图
                    }
                    pageNum=1;
                    String currentTrusteeshipList = APIs.getCurrentTrusteeshipList(pageNum,10);
                    getFirstConect(currentTrusteeshipList);
                }catch (Exception e){
                    collocation_Subscribe_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageNum++;
                String currentTrusteeshipList = APIs.getCurrentTrusteeshipList(pageNum,10);
                getNextConect(pageNum,currentTrusteeshipList);
            }
        });

        collocation_Subscribe_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).getState().equals("1")){ // 1==等待 2==未开始
                    Intent intent = new Intent(Collocation_Subscribe_Activity.this, Authenticate_Details_Activity.class);
                    intent.putExtra("CollocationBean",list.get(position));
                    startActivity(intent);
                }else{
                    MyUtils.setToast("该商品还未开始托管...");
                }
            }
        });
    }

    // 获取轮播图
    private void getBannder() {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("belongTo","1");
        map1.put("position","1");
        map1.put("state","2");
        myPresenter.postPreContent(APIs.selectAdvertise, map1, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        SelectAdvertise_Bean selectAdvertise_bean = new Gson().fromJson(json, SelectAdvertise_Bean.class);
                        final List<SelectAdvertise_Bean.DataBean.ListBean> list = selectAdvertise_bean.getData().getList();
                        collocation_Subscribe_XBanner.setData(list,null);//设置数据源

                        collocation_Subscribe_XBanner.setmAdapter(new XBanner.XBannerAdapter() {//xbanner的适配器，加载图片
                            @Override
                            public void loadBanner(XBanner banner, Object model, View view, int position) {
                                if(list.get(position).getImgeUrl()==null){
                                    ((ImageView) view).setImageResource(R.drawable.img_erroy);
                                }else{
                                    Glide.with(Collocation_Subscribe_Activity.this).load(list.get(position).getImgeUrl()).error(R.drawable.img_erroy).into((ImageView) view);
                                }
                            }
                        });

                        collocation_Subscribe_XBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                            @Override
                            public void onItemClick(XBanner banner, int position) {
                                if(list.get(position).getImgeLink()!=null&&!list.get(position).getImgeLink().equals("")){
                                    Intent intent = new Intent(Collocation_Subscribe_Activity.this, Information_Details_Activity.class);
                                    intent.putExtra("SelectAdvertise_Bean.DataBean.ListBean",list.get(position));
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                }
                            }
                        });

                    }else{
                        MyUtils.setToast("托管预约轮播图获取错误");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast("托管预约轮播图=="+ss);
            }
        });
    }


    // 首次进入页面获取列表
    private void getFirstConect(final String url) {
        myPresenter.getPreContent(url, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Collocation_Subscribe_bean collocation_Subscribe_bean = new Gson().fromJson(json, Collocation_Subscribe_bean.class);
                        List<Collocation_Subscribe_bean.DataBean.ListBean> zilist = collocation_Subscribe_bean.getData().getList();
                        list.clear();
                        list.addAll(zilist);
                        // 初始化数据 与适配器
                        setCollocation_sub_list_Adapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                collocation_Subscribe_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                collocation_Subscribe_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 下拉加载更多
    private void getNextConect(final int pageNum,final String url) {
        myPresenter.getPreContent(url, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Collocation_Subscribe_bean collocation_Subscribe_bean = new Gson().fromJson(json, Collocation_Subscribe_bean.class);
                        if(pageNum <= collocation_Subscribe_bean.getData().getPageInfo().getTotalPage()){
                            List<Collocation_Subscribe_bean.DataBean.ListBean> zilist = collocation_Subscribe_bean.getData().getList();
                            list.addAll(zilist);
                            // 初始化数据 与适配器
                            setCollocation_sub_list_Adapter();
                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                collocation_Subscribe_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                collocation_Subscribe_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 适配器的设置
    private void setCollocation_sub_list_Adapter() {
        if(imgs.size()<=0||list.size()<=0) {
            collocation_subscribe_null.setVisibility(View.VISIBLE);
            collocation_Subscribe_pulltoscroll.setVisibility(View.GONE);
        }else{
            collocation_subscribe_null.setVisibility(View.GONE);
            collocation_Subscribe_pulltoscroll.setVisibility(View.VISIBLE);

            if(collocation_subscribe_list_adapter==null){
                collocation_subscribe_list_adapter = new Collocation_Subscribe_list_Adapter(Collocation_Subscribe_Activity.this, list);
                collocation_Subscribe_MyListView.setAdapter(collocation_subscribe_list_adapter);
            }else{
                collocation_subscribe_list_adapter.notifyDataSetChanged();
            }
        }


    }

    private void initView() {
        collocation_Subscribe_Fan = (RelativeLayout) findViewById(R.id.collocation_Subscribe_Fan);
        authenticate_Details_Toobar = (RelativeLayout) findViewById(R.id.authenticate_Details_Toobar);
        collocation_Subscribe_XBanner = (XBanner) findViewById(R.id.collocation_Subscribe_XBanner);
        collocation_Subscribe_MyListView = (MyListView) findViewById(R.id.collocation_Subscribe_MyListView);
        collocation_Subscribe_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.collocation_Subscribe_pulltoscroll);
        collocation_subscribe_null = (LinearLayout) findViewById(R.id.collocation_Subscribe_null);

        initRefreshListView(collocation_Subscribe_pulltoscroll);

        imgs.add(R.mipmap.band1);
        imgs.add(R.mipmap.band2);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai,R.anim.xiao_out_kuai);
    }
}
