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

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.HomeAdapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.bean.Collect_Bean;
import com.ifeng_tech.treasuryyitong.bean.FirstGpsBean;
import com.ifeng_tech.treasuryyitong.bean.Information_Zi_Bean;
import com.ifeng_tech.treasuryyitong.bean.my.Collocation_Subscribe_bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

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

    List<Information_Zi_Bean.DataBean.ListBean> informationlist = new ArrayList<>();

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
        list.add(imgs);
        list.add(gpslist);

        //  进度框
        final ProgressDialog aniDialog = new ProgressDialog(activity);
        aniDialog.setCancelable(true);
        aniDialog.setMessage("正在加载...");
        aniDialog.show();

        // 征集
        getCollectList(aniDialog);

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
                        MyUtils.setToast("首页征集列表获取错误");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
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
                        Collocation_Subscribe_bean collocation_Subscribe_bean = new Gson().fromJson(json, Collocation_Subscribe_bean.class);
                        List<Collocation_Subscribe_bean.DataBean.ListBean> zilist = collocation_Subscribe_bean.getData().getList();
                        trusteeshiplist.clear();
                        trusteeshiplist.addAll(zilist);
                        list.add(trusteeshiplist); // 将托管预约列表装入集合

                        list.add(R.drawable.cangpinmulu);  // 将广告装入集合

                        getInformationlist(aniDialog);  // 完成以后调资讯
                    }else{
                        MyUtils.setToast("首页托管预约列表获取错误");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast("首页托管预约列表获取错误");
            }
        });
    }

    // 获取资讯列表
    public void getInformationlist(final ProgressDialog aniDialog){
        HashMap<String, String> map = new HashMap<>();
        map.put("mainColumnId", "147");
        map.put("pageNum", "1");
        map.put("pageSize", "3");
        activity.myPresenter.postPreContent(APIs.getArticleListByMainColumnId, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Information_Zi_Bean information_Zi_Bean = new Gson().fromJson(json, Information_Zi_Bean.class);
                        List<Information_Zi_Bean.DataBean.ListBean> zilist = information_Zi_Bean.getData().getList();
                        informationlist.clear();
                        informationlist.addAll(zilist);  // 将资讯列表装入集合

                        list.add(informationlist);  // 将资讯装入总集合
                    }else{
                        MyUtils.setToast("首页资讯列表获取错误");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast("首页资讯列表获取错误");
            }
        });
    }


    private void initView(View view) {
        shouye_RecyclerView = (RecyclerView) view.findViewById(R.id.shouye_RecyclerView);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
