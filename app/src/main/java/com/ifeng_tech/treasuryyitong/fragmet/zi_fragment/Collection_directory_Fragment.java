package com.ifeng_tech.treasuryyitong.fragmet.zi_fragment;

import android.app.ProgressDialog;
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
import com.ifeng_tech.treasuryyitong.adapter.Collection_directory_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.bean.cangpin.Collection_directory_Fragment_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.Collection_Directory_Detail_Activity;
import com.ifeng_tech.treasuryyitong.ui.Collection_directory_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2018/5/24.
 *
 *  藏品目录 子页面
 */

public class Collection_directory_Fragment extends Fragment {
    private MyListView collection_mulu_fragment_MyListView;
    private PullToRefreshScrollView collection_mulu_fragment_pulltoscroll;
    private LinearLayout collection_mulu_null;

    List<Collection_directory_Fragment_Bean.DataBean.ListBean> list = new ArrayList<>();

    private String top;
    private Collection_directory_Activity activity;
    private Collection_directory_Adapter collection_directory_adapter;
    private ProgressDialog aniDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collection_directory_fragment, container, false);
        initView(view);

        activity = (Collection_directory_Activity) getActivity();
        //获取传递的top值，，，
        top = getArguments().getString("top");

        pageNum=1;
        String url = APIs.queryCommodityList(pageNum, getTopList(top));

        aniDialog = MyUtils.getProgressDialog(activity, SP_String.JIAZAI);

        getFristConect(url);  // 进入页面首次获取数据

        return view;
    }

    int pageNum=1;
    @Override
    public void onResume() {
        super.onResume();

        // 下拉刷新  加载
        collection_mulu_fragment_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                MyUtils.setToast("下拉了。。。");
                pageNum=1;
                String url = APIs.queryCommodityList(pageNum, getTopList(top));
                getFristConect(url);  // 进入页面首次获取数据
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageNum++;
                String url = APIs.queryCommodityList(pageNum, getTopList(top));
                getNextConect(url); // 上拉加载 获取数据
            }
        });

        collection_mulu_fragment_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MyUtils.setToast("点击了藏品目录条目==="+position);
                int goodsId = list.get(position).getId();
                Intent intent = new Intent(activity, Collection_Directory_Detail_Activity.class);
                intent.putExtra("goodsId",""+goodsId);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }
    // 进入页面首次获取数据
    private void getFristConect(String url) {
        activity.myPresenter.getPreContent(url, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Collection_directory_Fragment_Bean collection_directory_fragment_bean = new Gson().fromJson(json, Collection_directory_Fragment_Bean.class);
                        List<Collection_directory_Fragment_Bean.DataBean.ListBean> zilist = collection_directory_fragment_bean.getData().getList();
                        list.clear();
                        list.addAll(zilist);
                        setcollection_mulu_Adapter();
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                    collection_mulu_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
                collection_mulu_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 上拉加载 获取数据
    private void getNextConect(String url) {
        activity.myPresenter.getPreContent(url, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Collection_directory_Fragment_Bean collection_directory_fragment_bean = new Gson().fromJson(json, Collection_directory_Fragment_Bean.class);
                        if(pageNum <= collection_directory_fragment_bean.getData().getPage().getPages()){
                            List<Collection_directory_Fragment_Bean.DataBean.ListBean> zilist = collection_directory_fragment_bean.getData().getList();
                            list.addAll(zilist);
                            setcollection_mulu_Adapter();
                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                    collection_mulu_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                collection_mulu_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }


    // 初始化适配器
    public void setcollection_mulu_Adapter() {
        if (list.size()>0) {
            collection_mulu_fragment_pulltoscroll.setVisibility(View.VISIBLE);
            collection_mulu_null.setVisibility(View.GONE);
            if(collection_directory_adapter==null){
                collection_directory_adapter = new Collection_directory_Adapter(activity, list);
                collection_mulu_fragment_MyListView.setAdapter(collection_directory_adapter);
            }else{
                collection_directory_adapter.notifyDataSetChanged();
            }
        } else {
            collection_mulu_fragment_pulltoscroll.setVisibility(View.GONE);
            collection_mulu_null.setVisibility(View.VISIBLE);
        }

    }

    private void initView(View view) {
        collection_mulu_fragment_MyListView = (MyListView) view.findViewById(R.id.collection_mulu_fragment_MyListView);
        collection_mulu_fragment_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.collection_mulu_fragment_pulltoscroll);
        collection_mulu_null = (LinearLayout) view.findViewById(R.id.collection_mulu_null);
        // 设置刷新
        initRefreshListView(collection_mulu_fragment_pulltoscroll);
    }

    public void initRefreshListView(PullToRefreshScrollView my_collocation_pulltoscroll) {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        my_collocation_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = my_collocation_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");

        ILoadingLayout Labels1 = my_collocation_pulltoscroll.getLoadingLayoutProxy(false, true);
        Labels1.setPullLabel("上拉加载...");
        Labels1.setRefreshingLabel("正在加载...");
        Labels1.setReleaseLabel("放开刷新...");
    }

    private String getTopList(String top) {
        String ss="";
        if (top.equals("全部")) {
            ss="";
        } else {
            ss=top;
        }
        return ss;
    }



}
