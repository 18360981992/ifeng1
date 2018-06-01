package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Incoming_Test_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.my.My_Collect_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 我的征集
 */
public class MyCollectActivity extends BaseMVPActivity<MyCollectActivity, MyPresenter<MyCollectActivity>> {

    private RelativeLayout mycollect_Fan;

    List<My_Collect_Bean.DataBean.ListBean> list = new ArrayList<>();
    private Incoming_Test_Adapter incoming_test_adapter;
    private LinearLayout mycollect_null;
    private MyListView mycollect_MyListView;
    private PullToRefreshScrollView mycollect_pulltoscroll;


    @Override
    public MyPresenter<MyCollectActivity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        initView();

        mycollect_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    HashMap<String, String> map = new HashMap<>();
    int pageNum=1;
    @Override
    protected void onResume() {
        super.onResume();
        pageNum=1;
        map.put("pageNum",pageNum+"");
        map.put("pageSize",""+10);
        getFirstConect(map);

        // 下拉刷新  加载
        mycollect_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                MyUtils.setToast("下拉了。。。");
                pageNum=1;
                map.put("pageNum",pageNum+"");
                map.put("pageSize",""+10);
                getFirstConect(map);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageNum++;
                map.put("pageNum",pageNum+"");
                map.put("pageSize",""+10);
                getNextConect(map);
            }
        });

    }

    // 进入页面的首次请求
    private void getFirstConect(final HashMap<String, String> map) {
        myPresenter.postPreContent(APIs.getUserCollectList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        My_Collect_Bean my_collect_bean = new Gson().fromJson(json, My_Collect_Bean.class);
                        List<My_Collect_Bean.DataBean.ListBean> zilist = my_collect_bean.getData().getList();
                        list.clear();
                        list.addAll(zilist);
                        setAdapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mycollect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                mycollect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }


    // 上拉加载更多的请求
    private void getNextConect(final HashMap<String, String> map) {
        myPresenter.postPreContent(APIs.getUserCollectList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        My_Collect_Bean my_collect_bean = new Gson().fromJson(json, My_Collect_Bean.class);
                        List<My_Collect_Bean.DataBean.ListBean> zilist = my_collect_bean.getData().getList();
                        list.addAll(zilist);
                        setAdapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mycollect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                mycollect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    public void setAdapter() {
        if (list.size() > 0) {
            mycollect_null.setVisibility(View.GONE);
            mycollect_pulltoscroll.setVisibility(View.VISIBLE);
            if (incoming_test_adapter == null) {
                incoming_test_adapter = new Incoming_Test_Adapter(MyCollectActivity.this, list);
                mycollect_MyListView.setAdapter(incoming_test_adapter);
            } else {
                incoming_test_adapter.notifyDataSetChanged();
            }
        }else{
            mycollect_null.setVisibility(View.VISIBLE);
            mycollect_pulltoscroll.setVisibility(View.GONE);
        }
    }

    private void initView() {
        mycollect_Fan = (RelativeLayout) findViewById(R.id.mycollect_Fan);
        mycollect_MyListView = (MyListView) findViewById(R.id.mycollect_MyListView);
        mycollect_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.mycollect_pulltoscroll);
        mycollect_null = (LinearLayout) findViewById(R.id.mycollect_null);
        // 设置刷新
        initRefreshListView();
    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        mycollect_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = mycollect_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
