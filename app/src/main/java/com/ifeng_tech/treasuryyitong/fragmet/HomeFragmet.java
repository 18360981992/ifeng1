package com.ifeng_tech.treasuryyitong.fragmet;

import android.app.ProgressDialog;
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
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.HomeAdapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.Collect_Bean;
import com.ifeng_tech.treasuryyitong.bean.FirstGpsBean;
import com.ifeng_tech.treasuryyitong.bean.SelectAdvertise_Bean;
import com.ifeng_tech.treasuryyitong.bean.my.Collocation_Subscribe_bean;
import com.ifeng_tech.treasuryyitong.bean.zixun.HotList_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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
    List<Collect_Bean.DataBean.ListBean> collectlist = new ArrayList<>();
    List<Collocation_Subscribe_bean.DataBean.ListBean> trusteeshiplist = new ArrayList<>();

    List<HotList_Bean.DataBean.ListBean> informationlist = new ArrayList<>();

    List<Integer> imgs = new ArrayList<>();
    List<SelectAdvertise_Bean.DataBean.ListBean> imagsList=new ArrayList<>();
    private HomePageActivity activity;
    private PullToRefreshScrollView shouye_pulltoscroll;
    private View shouye_view_title;
    private HomeAdapter homeAdapter;

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

        // 首页的下拉刷新
        shouye_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                shouye_view_title.setVisibility(View.VISIBLE);

                if(homeAdapter.home_xBanner!=null){
                    homeAdapter.home_xBanner.stopAutoPlay();
                    if(homeAdapter.home_xBanner.getRealCount()>0){
                        //  进度框
                        ProgressDialog aniDialog = MyUtils.getProgressDialog(activity, SP_String.JIAZAI);
                        aniDialog.dismiss();
                        getBannder(aniDialog);  // 轮播图
                    }else{
                        shouye_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                    }
                }


            }
        });
    }

    // 仿数据
    private void initData(){

//        imgs.add(R.mipmap.band1);
//        imgs.add(R.mipmap.band2);
//        // 导航图
//        gpslist.add(new FirstGpsBean(R.drawable.saoyisao,"扫一扫"));
//        gpslist.add(new FirstGpsBean(R.drawable.shouhuo,"收货"));
//        gpslist.add(new FirstGpsBean(R.drawable.zhuanzeng,"转赠"));
//        gpslist.add(new FirstGpsBean(R.drawable.jianding,"鉴定"));


        getHuanCun_SP();   // 先加载缓存数据  即使加载上数据 网络也同样需要请求（为了获取最新的数据） 在加载网络数据失败或者错误的时候我们还是去显示缓存数据
        //  进度框
        ProgressDialog aniDialog = MyUtils.getProgressDialog(activity, SP_String.JIAZAI);
        getBannder(aniDialog);  // 轮播图

    }

    public void getHuanCun_SP(){
        String banner = DashApplication.sp_huan.getString(SP_String.SHOUYE_BANNER, "");
        String zhengji = DashApplication.sp_huan.getString(SP_String.SHOUYE_ZHENGJI, "");  // 征集暂时不上
        String tuoguan = DashApplication.sp_huan.getString(SP_String.SHOUYE_TUOGUAN, "");
        String zixun = DashApplication.sp_huan.getString(SP_String.SHOUYE_ZIXUN, "");
        if(!banner.equals("")&!tuoguan.equals("")&!zixun.equals("")){
            list.clear();
            imgs.clear();

            imagsList.clear();
            gpslist.clear();
            collectlist.clear();
            trusteeshiplist.clear();
            informationlist.clear();

            imgs.add(R.mipmap.band1);
            imgs.add(R.mipmap.band2);

            // 导航图
            gpslist.add(new FirstGpsBean(R.drawable.saoyisao,"扫一扫"));
            gpslist.add(new FirstGpsBean(R.drawable.shouhuo,"收货"));
            gpslist.add(new FirstGpsBean(R.drawable.zhuanzeng,"转赠"));
            gpslist.add(new FirstGpsBean(R.drawable.jianding,"鉴定"));

            SelectAdvertise_Bean selectAdvertise_bean = new Gson().fromJson(banner, SelectAdvertise_Bean.class);
            List<SelectAdvertise_Bean.DataBean.ListBean> zilist = selectAdvertise_bean.getData().getList();
            imagsList.addAll(zilist);

            list.add(imagsList);
            list.add(gpslist);

            list.add(collectlist);  // 征集暂时注掉，第一版不上征集

            Collocation_Subscribe_bean collocation_Subscribe_bean = new Gson().fromJson(tuoguan, Collocation_Subscribe_bean.class);
            List<Collocation_Subscribe_bean.DataBean.ListBean> zilist1 = collocation_Subscribe_bean.getData().getList();
            trusteeshiplist.clear();
            trusteeshiplist.addAll(zilist1);
            list.add(trusteeshiplist); // 将托管预约列表装入集合

            list.add(R.drawable.cangpinmulu);  // 将广告装入集合

            HotList_Bean hotList_Bean = new Gson().fromJson(zixun, HotList_Bean.class);
            List<HotList_Bean.DataBean.ListBean> zilist2 = hotList_Bean.getData().getList();
            informationlist.clear();
            if(zilist.size()>0){
                informationlist.addAll(zilist2);  // 将资讯列表装入集合
            }

            list.add(informationlist);  // 将资讯装入总集合

            homeAdapter = new HomeAdapter(activity, HomeFragmet.this.list);
            shouye_RecyclerView.setAdapter(homeAdapter);

        }

    }

    // 轮播图
    public void getBannder(final ProgressDialog aniDialog){
        list.clear();
        imgs.clear();

        imagsList.clear();
        gpslist.clear();
        collectlist.clear();
        trusteeshiplist.clear();
        informationlist.clear();

        imgs.add(R.mipmap.band1);
        imgs.add(R.mipmap.band2);

        // 导航图
        gpslist.add(new FirstGpsBean(R.drawable.saoyisao,"扫一扫"));
        gpslist.add(new FirstGpsBean(R.drawable.shouhuo,"收货"));
        gpslist.add(new FirstGpsBean(R.drawable.zhuanzeng,"转赠"));
        gpslist.add(new FirstGpsBean(R.drawable.jianding,"鉴定"));


        HashMap<String, String> map = new HashMap<>();
        map.put("belongTo","0");
        map.put("position","1");

        activity.myPresenter.postPreContent(APIs.selectAdvertise, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        SelectAdvertise_Bean selectAdvertise_bean = new Gson().fromJson(json, SelectAdvertise_Bean.class);
                        List<SelectAdvertise_Bean.DataBean.ListBean> zilist = selectAdvertise_bean.getData().getList();
                        DashApplication.edit_huan.putString(SP_String.SHOUYE_BANNER,json).commit(); // 缓存轮播图

                        imagsList.addAll(zilist);

                        list.add(imagsList);
                        list.add(gpslist);

                        // 征集
//                        getCollectList(aniDialog);

                        list.add(collectlist);  // 征集暂时注掉，第一版不上征集


                        getTrusteeshiplist(aniDialog);  // 成功以后调托管

                    }else{
                        shouye_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                        shouye_view_title.setVisibility(View.GONE);
                        aniDialog.dismiss();
                        MyUtils.setToast("首页轮播图获取错误");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                shouye_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                shouye_view_title.setVisibility(View.GONE);
                aniDialog.dismiss();
                getHuanCun_SP();   // 网络获取失败的时候 加载缓存
                MyUtils.setToast("首页轮播图获取错误");
            }
        });
    }


    // 征集   成功以后调托管
    private void getCollectList(final ProgressDialog aniDialog) {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum","1");
        map.put("pageSize","15");
        activity.myPresenter.postPreContent(APIs.getCollectList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        DashApplication.edit_huan.putString(SP_String.SHOUYE_ZHENGJI,json).commit();  // 缓存征集
                        Collect_Bean Collect_Bean = new Gson().fromJson(json, Collect_Bean.class);
                        List<Collect_Bean.DataBean.ListBean> zilist = Collect_Bean.getData().getList();
                        collectlist.clear();
                        if(zilist.size()>0){
                            for (Collect_Bean.DataBean.ListBean bean: zilist) {
                                if(bean.getStage()==4||bean.getStage()==3){
                                    if(collectlist.size()<3){
                                        collectlist.add(bean);
                                    }
                                }
                            }
                            list.add(collectlist);  // 将征集列表装入集合
                            getTrusteeshiplist(aniDialog);  // 成功以后调托管
                        }

                    }else{
                        shouye_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                        shouye_view_title.setVisibility(View.GONE);
                        aniDialog.dismiss();
                        MyUtils.setToast("首页征集列表获取错误");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                shouye_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                shouye_view_title.setVisibility(View.GONE);
                aniDialog.dismiss();

                getHuanCun_SP();   // 网络获取失败的时候 加载缓存
                MyUtils.setToast("首页征集列表获取错误");
            }
        });
    }

    // 托管
    public void getTrusteeshiplist(final ProgressDialog aniDialog){
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum","1");
        map.put("pageSize","3");
        activity.myPresenter.postPreContent(APIs.getCurrentTrusteeshipList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        DashApplication.edit_huan.putString(SP_String.SHOUYE_TUOGUAN,json).commit();  // 缓存托管
                        Collocation_Subscribe_bean collocation_Subscribe_bean = new Gson().fromJson(json, Collocation_Subscribe_bean.class);
                        List<Collocation_Subscribe_bean.DataBean.ListBean> zilist = collocation_Subscribe_bean.getData().getList();
                        trusteeshiplist.clear();
                        trusteeshiplist.addAll(zilist);
                        list.add(trusteeshiplist); // 将托管预约列表装入集合

                        list.add(R.drawable.cangpinmulu);  // 将广告装入集合

                        getInformationlist(aniDialog);  // 完成以后调资讯
                    }else{
                        shouye_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                        shouye_view_title.setVisibility(View.GONE);
                        aniDialog.dismiss();
                        MyUtils.setToast("首页托管预约列表获取错误");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                shouye_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                shouye_view_title.setVisibility(View.GONE);
                aniDialog.dismiss();
                getHuanCun_SP();   // 网络获取失败的时候 加载缓存
                MyUtils.setToast("首页托管预约列表获取错误");
            }
        });
    }

    // 获取资讯列表 ====》 热门
    public void getInformationlist(final ProgressDialog aniDialog){
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", "1");
        map.put("pageSize", "3");
        activity.myPresenter.postPreContent(APIs.getHotList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                shouye_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                shouye_view_title.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        DashApplication.edit_huan.putString(SP_String.SHOUYE_ZIXUN,json).commit();  // 缓存资讯
                        HotList_Bean hotList_Bean = new Gson().fromJson(json, HotList_Bean.class);
                        List<HotList_Bean.DataBean.ListBean> zilist = hotList_Bean.getData().getList();
                        informationlist.clear();
                        if(zilist.size()>0){
                            informationlist.addAll(zilist);  // 将资讯列表装入集合
                        }

                        list.add(informationlist);  // 将资讯装入总集合

//                        DashApplication.edit.putString(SP_String.SHOUYE_HUANCUN,list.toString()).commit();
                        homeAdapter = new HomeAdapter(activity, HomeFragmet.this.list);
                        shouye_RecyclerView.setAdapter(homeAdapter);

                        aniDialog.dismiss();
                    }else{
                        aniDialog.dismiss();
                        MyUtils.setToast("首页资讯列表获取错误");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                shouye_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                shouye_view_title.setVisibility(View.GONE);
                aniDialog.dismiss();
                getHuanCun_SP();   // 网络获取失败的时候 加载缓存
                MyUtils.setToast("首页资讯列表获取错误");
            }
        });
    }


    private void initView(View view) {
        shouye_view_title = view.findViewById(R.id.shouye_view_title);
        shouye_RecyclerView = (RecyclerView) view.findViewById(R.id.shouye_RecyclerView);
        shouye_pulltoscroll = (PullToRefreshScrollView)view.findViewById(R.id.shouye_pulltoscroll);
        View shouye_view = view.findViewById(R.id.shouye_view);
        initRefreshListView(shouye_pulltoscroll);

        /**
         * 解决scrollview 显示不在顶部问题
         */
        shouye_view.setFocusable(true);
        shouye_view.setFocusableInTouchMode(true);
        shouye_view.requestFocus();
    }


    public void initRefreshListView(PullToRefreshScrollView my_collocation_pulltoscroll) {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        my_collocation_pulltoscroll.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ILoadingLayout Labels = my_collocation_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
