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
import com.ifeng_tech.treasuryyitong.adapter.Information_zi_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.bean.zixun.Information_Zi_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.Information_Activity;
import com.ifeng_tech.treasuryyitong.ui.Information_Details_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zzt on 2018/5/7.
 * <p>
 * 资讯的 子fragment
 */

public class Information_zi_Fragment extends Fragment {

    private Information_Activity activity;
    private LinearLayout information_zi_null;
    private MyListView information_zi_fragment_MyListView;
    private PullToRefreshScrollView information_zi_fragment_pulltoscroll;

    List<Information_Zi_Bean.DataBean.ListBean> list = new ArrayList<>();

    private Information_zi_Adapter information_zi_adapter;
    private String id;
    private String type;
    private String top;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_zi_fragment, container, false);
        initView(view);

        activity = (Information_Activity) getActivity();
        return view;
    }

    int pageNum_Quanbu=1;
    int pageNum_LanMu=1;

    @Override
    public void onResume() {
        super.onResume();

        //获取传递的top值，，
        id = getArguments().getString("id");
        type = getArguments().getString("type");
        top = getArguments().getString("top");  // 暂时不用

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(activity, SP_String.JIAZAI);

        if(type.equals("1")){  // 查询全部
            HashMap<String, String> map = new HashMap<>();
            map.put("mainColumnId", id);
            map.put("pageNum", pageNum_Quanbu+"");
            map.put("pageSize", 10+"");

            getFirstConect(map,APIs.getArticleListByMainColumnId,top,aniDialog);
        }else{
            HashMap<String, String> map = new HashMap<>();
            map.put("subColumnId", id);
            map.put("pageNum", pageNum_LanMu+"");
            map.put("pageSize", 10+"");
            getFirstConect(map,APIs.getArticleListBySubColumnId,top,aniDialog);
        }


        // 下拉刷新  加载
        information_zi_fragment_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                MyUtils.setToast("下拉了。。。");

                if(type.equals("1")){  // 查询全部
                    HashMap<String, String> map = new HashMap<>();
                    map.put("mainColumnId", id);
                    map.put("pageNum", pageNum_Quanbu+"");
                    map.put("pageSize", 10+"");
                    getFirstConect(map,APIs.getArticleListByMainColumnId,top,aniDialog);
                }else{
                    HashMap<String, String> map = new HashMap<>();
                    map.put("subColumnId", id);
                    map.put("pageNum", pageNum_LanMu+"");
                    map.put("pageSize", 10+"");
                    getFirstConect(map,APIs.getArticleListBySubColumnId,top,aniDialog);
                }

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                if(type.equals("1")){  // 查询全部
                    pageNum_Quanbu++;
                    HashMap<String, String> map = new HashMap<>();
                    map.put("mainColumnId", id);
                    map.put("pageNum", pageNum_Quanbu+"");
                    map.put("pageSize", 10+"");
                    getNextConect(map,APIs.getArticleListByMainColumnId,top);
                }else{
                    pageNum_LanMu++;
                    HashMap<String, String> map = new HashMap<>();
                    map.put("subColumnId", id);
                    map.put("pageNum", pageNum_LanMu+"");
                    map.put("pageSize", 10+"");
                    getNextConect(map,APIs.getArticleListBySubColumnId,top);
                }
            }
        });

        // 条目点击事件
        information_zi_fragment_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                MyUtils.setToast("点击了=="+topList.get(position).getTop()+"的第"+position+"条");
                Intent intent = new Intent(activity, Information_Details_Activity.class);
                intent.putExtra("id",list.get(position).getId()+"");
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }

    // 首次进入页面获取列表
    private void getFirstConect(final HashMap<String, String> map , String url, final String top,final ProgressDialog aniDialog) {
        activity.myPresenter.postPreContent(url, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Information_Zi_Bean information_Zi_Bean = new Gson().fromJson(json, Information_Zi_Bean.class);
                        List<Information_Zi_Bean.DataBean.ListBean> zilist = information_Zi_Bean.getData().getList();
                        list.clear();
                        list.addAll(zilist);
                        // 初始化数据 与适配器
                        setInformation_Adapter(top);
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                information_zi_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
                information_zi_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }


    // 下拉加载更多
    private void getNextConect(final HashMap<String, String> map , String url,final String top) {
        activity.myPresenter.postPreContent(url, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Information_Zi_Bean information_Zi_Bean = new Gson().fromJson(json, Information_Zi_Bean.class);
                        String pageNum = map.get("pageNum");
                        if(Integer.valueOf(pageNum)<=information_Zi_Bean.getData().getPageInfo().getTotalPage()){
                            List<Information_Zi_Bean.DataBean.ListBean> zilist = information_Zi_Bean.getData().getList();

                            list.addAll(zilist);
                            // 初始化数据 与适配器
                            setInformation_Adapter(top);
                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                information_zi_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                information_zi_fragment_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }


    // 初始化适配器
    private void setInformation_Adapter(String top) {
        if (list.size()>0) {
            information_zi_fragment_pulltoscroll.setVisibility(View.VISIBLE);
            information_zi_null.setVisibility(View.GONE);
            if (information_zi_adapter == null) {
                information_zi_adapter = new Information_zi_Adapter(activity, list,top);
                information_zi_fragment_MyListView.setAdapter(information_zi_adapter);
            } else {
                information_zi_adapter.notifyDataSetChanged();
            }
        }else{
            information_zi_fragment_pulltoscroll.setVisibility(View.GONE);
            information_zi_null.setVisibility(View.VISIBLE);
        }
    }


    private void initView(View view) {
        information_zi_fragment_MyListView = (MyListView) view.findViewById(R.id.information_zi_fragment_MyListView);
        information_zi_fragment_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.information_zi_fragment_pulltoscroll);
        information_zi_null = view.findViewById(R.id.information_zi_null);
        // 设置刷新
        initRefreshListView(information_zi_fragment_pulltoscroll);
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

}
