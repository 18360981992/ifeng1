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
import com.ifeng_tech.treasuryyitong.adapter.My_Given_list_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_Datail_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_list_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zzt on 2018/5/29.
 * <p>
 * 转入
 */

public class My_Given_Change_into_Fragment extends Fragment {

    private MyListView my_Given_Change_into_MyListView;
    private PullToRefreshScrollView my_Given_Change_into_pulltoscroll;
    private LinearLayout my_Given_Change_into_null;

    List<Give_List_Bean.DataBean.ListBean> list = new ArrayList<>();

    private My_Given_list_Adapter my_given_list_adapter;
    private My_Given_list_Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_given_change_into_fragment, container, false);
        activity = (My_Given_list_Activity) getActivity();

        initView(view);

        return view;
    }
    HashMap<String, String> map = new HashMap<>();
    int pageNum=1;

    @Override
    public void onResume() {
        super.onResume();

        // 网络加载框  暂时禁用
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(activity, SP_String.JIAZAI);
        aniDialog.dismiss();

        pageNum=1;
        map.put("pageNum",pageNum+"");
        map.put("pageSize",""+10);
        map.put("queryMode","2");  // 查询类型 ：0：查询全部与自己有关的 2：查询转入 1：查询转出（必须填参数）
        // 获取缓存中的转入
        if(!DashApplication.sp.getString(SP_String.ZHUANRU,"").equals("")){
            Give_List_Bean give_List_Bean = new Gson().fromJson(DashApplication.sp.getString(SP_String.ZHUANRU,""), Give_List_Bean.class);
            List<Give_List_Bean.DataBean.ListBean> zilist = give_List_Bean.getData().getList();
            list.clear();
            list.addAll(zilist);
            setMy_Given_list_Adapter();
        }
        // 不管缓存与否，都要去获取最新的数据
        getFirstConect(map,aniDialog);

        my_Given_Change_into_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                MyUtils.setToast("下拉了。。。");
                pageNum=1;
                map.put("pageNum",pageNum+"");
                map.put("pageSize",""+10);
                getFirstConect(map,aniDialog);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageNum++;
                map.put("pageNum",pageNum+"");
                map.put("pageSize",""+10);
                getNextConect(map);
            }
        });


        // 条目点击事件
        my_Given_Change_into_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, My_Given_Datail_Activity.class);
                intent.putExtra("Give_List_Bean",list.get(position));
                intent.putExtra("leibie",0);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }

    // 首次进入页面获取列表
    private void getFirstConect(final HashMap<String, String> map, final ProgressDialog aniDialog) {
        activity.myPresenter.postPreContent(APIs.getBestowList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        DashApplication.edit.putString(SP_String.ZHUANRU,json).commit();
                        Give_List_Bean give_List_Bean = new Gson().fromJson(json, Give_List_Bean.class);
                        List<Give_List_Bean.DataBean.ListBean> zilist = give_List_Bean.getData().getList();
                        list.clear();
                        list.addAll(zilist);
                        setMy_Given_list_Adapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                my_Given_Change_into_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
                my_Given_Change_into_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 下拉加载更多
    private void getNextConect(final HashMap<String, String> map) {
        activity.myPresenter.postPreContent(APIs.getBestowList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {

                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Give_List_Bean give_List_Bean = new Gson().fromJson(json, Give_List_Bean.class);

                        String pageNum = map.get("pageNum");
                        if(Integer.valueOf(pageNum) <= give_List_Bean.getData().getPageInfo().getTotalPage()){
                            List<Give_List_Bean.DataBean.ListBean> zilist = give_List_Bean.getData().getList();
                            list.addAll(zilist);
                            // 初始化数据 与适配器
                            setMy_Given_list_Adapter();
                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                my_Given_Change_into_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                my_Given_Change_into_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 初始化数据 与适配器
    private void setMy_Given_list_Adapter() {
        if(list.size()>0) {
            my_Given_Change_into_null.setVisibility(View.GONE);
            my_Given_Change_into_pulltoscroll.setVisibility(View.VISIBLE);

            if (my_given_list_adapter == null) {
                my_given_list_adapter = new My_Given_list_Adapter(activity, list, 0); // 0==转入
                my_Given_Change_into_MyListView.setAdapter(my_given_list_adapter);
            } else {
                my_given_list_adapter.notifyDataSetChanged();
            }
        }else{
            my_Given_Change_into_null.setVisibility(View.VISIBLE);
            my_Given_Change_into_pulltoscroll.setVisibility(View.GONE);
        }

    }

    private void initView(View view) {
        my_Given_Change_into_MyListView = (MyListView) view.findViewById(R.id.my_Given_Change_into_MyListView);
        my_Given_Change_into_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.my_Given_Change_into_pulltoscroll);
        my_Given_Change_into_null = (LinearLayout) view.findViewById(R.id.my_Given_Change_into_null);

        activity.initRefreshListView(my_Given_Change_into_pulltoscroll);
    }


}
