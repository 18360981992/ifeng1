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

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Pick_Up_Goods_Cha_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.bean.Pick_Up_Goods_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.my.Pick_Up_Goods_Detail_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zzt on 2018/5/14.
 *
 *  提货单查询
 */

public class Pick_up_goods_ChaXun extends Fragment {

    private Pick_up_goods_Activity activity;
    List<Pick_Up_Goods_Bean.DataBean.ListBean> list = new ArrayList<>();

    private MyListView pick_up_goods_cha_MyListView;
    private PullToRefreshScrollView pick_up_goods_cha_pulltoscroll;
    private LinearLayout pick_up_goods_cha_null;
    Pick_Up_Goods_Cha_Adapter pick_Up_Goods_Cha_Adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pick_up_goods_chaxun, container, false);
        initView(view);
        activity = (Pick_up_goods_Activity) getActivity();

        return view;
    }

    HashMap<String, String> map = new HashMap<>();
    int start=1;

    @Override
    public void onResume() {
        super.onResume();
        start=1;
        map.put("start",start+"");
        map.put("number",""+10);
        getFirstConect(map);


        // 刷新 加载
        pick_up_goods_cha_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                MyUtils.setToast("下拉了。。。");
                start=1;
                map.put("start",start+"");
                map.put("number",""+10);
                getFirstConect(map);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                start++;
                map.put("start",start+"");
                map.put("number",""+10);
                getNextConect(map);
            }
        });

        // 条目点击跳到详情
        pick_up_goods_cha_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, Pick_Up_Goods_Detail_Activity.class);
                intent.putExtra("Pick_Up_Goods_Bean",list.get(position));
                startActivity(intent);
            }
        });
    }


    // 首次进入页面获取列表
    private void getFirstConect(final HashMap<String, String> map) {
        activity.myPresenter.postPreContent(APIs.getTakeDeliveryList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Pick_Up_Goods_Bean pick_Up_Goods_Bean = new Gson().fromJson(json, Pick_Up_Goods_Bean.class);
                        List<Pick_Up_Goods_Bean.DataBean.ListBean> zilist = pick_Up_Goods_Bean.getData().getList();
                        list.clear();
                        list.addAll(zilist);
                        // 初始化数据 与适配器
                        setPick_Up_Goods_Cha_Adapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pick_up_goods_cha_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                pick_up_goods_cha_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 下拉加载更多
    private void getNextConect(final HashMap<String, String> map) {
        activity.myPresenter.postPreContent(APIs.getTakeDeliveryList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Pick_Up_Goods_Bean pick_Up_Goods_Bean = new Gson().fromJson(json, Pick_Up_Goods_Bean.class);
                        List<Pick_Up_Goods_Bean.DataBean.ListBean> zilist = pick_Up_Goods_Bean.getData().getList();
                        list.addAll(zilist);
                        // 初始化数据 与适配器
                        setPick_Up_Goods_Cha_Adapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pick_up_goods_cha_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                pick_up_goods_cha_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 初始化 适配器
    private void setPick_Up_Goods_Cha_Adapter() {
        if(list.size()>0) {
            pick_up_goods_cha_null.setVisibility(View.GONE);
            pick_up_goods_cha_pulltoscroll.setVisibility(View.VISIBLE);
            if (pick_Up_Goods_Cha_Adapter == null) {
                pick_Up_Goods_Cha_Adapter = new Pick_Up_Goods_Cha_Adapter(activity, list);
                pick_up_goods_cha_MyListView.setAdapter(pick_Up_Goods_Cha_Adapter);
            } else {
                pick_Up_Goods_Cha_Adapter.notifyDataSetChanged();
            }
        }else{
            pick_up_goods_cha_null.setVisibility(View.VISIBLE);
            pick_up_goods_cha_pulltoscroll.setVisibility(View.GONE);
        }

    }


    private void initView(View view) {
        pick_up_goods_cha_MyListView = (MyListView) view.findViewById(R.id.pick_up_goods_cha_MyListView);
        pick_up_goods_cha_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.pick_up_goods_cha_pulltoscroll);
        pick_up_goods_cha_null = (LinearLayout) view.findViewById(R.id.pick_up_goods_cha_null);
        // 设置刷新
        initRefreshListView();
    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        pick_up_goods_cha_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = pick_up_goods_cha_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

}
