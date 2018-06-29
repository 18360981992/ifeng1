package com.ifeng_tech.treasuryyitong.ui.my.weituo;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Entrust_List_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Entrust_List_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 委托列表
 */
public class Entrust_List_Activity extends BaseMVPActivity<Entrust_List_Activity, MyPresenter<Entrust_List_Activity>> {

    private RelativeLayout entrust_List_Fan;
    private View entrust_List_view;
    private MyListView entrust_List_MyListView;
    private PullToRefreshScrollView entrust_List_pulltoscroll;
    private LinearLayout entrust_List_null;

    List<Entrust_List_Bean.DataBean.ListBean> list = new ArrayList<>();
    private Entrust_List_Adapter entrust_list_adapter;
    private int pageNum;

    @Override
    public MyPresenter<Entrust_List_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrust__list_);
        initView();

        entrust_List_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    Map<String, String> map = new HashMap<>();
    @Override
    protected void onResume() {
        super.onResume();
        pageNum = 1;
        map.put("pageNum", pageNum +"");
        map.put("pageSize",""+10);
        getFirstConect(map);
        // 初始化数据 与适配器
        setEntrust_List_Adapter();

        entrust_List_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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

                entrust_List_pulltoscroll.onRefreshComplete();
            }
        });

    }



    public void getFirstConect(Map<String,String> map){
        myPresenter.postPreContent(APIs.entrustedList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Entrust_List_Bean entrust_List_Bean = new Gson().fromJson(json, Entrust_List_Bean.class);
                        List<Entrust_List_Bean.DataBean.ListBean> zilist = entrust_List_Bean.getData().getList();
                        list.clear();
                        list.addAll(zilist);

                        // 初始化数据 与适配器
                        setEntrust_List_Adapter();
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                entrust_List_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                entrust_List_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    private void getNextConect(final Map<String, String> map) {
        myPresenter.postPreContent(APIs.entrustedList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Entrust_List_Bean entrust_List_Bean = new Gson().fromJson(json, Entrust_List_Bean.class);

                        String pageNum = map.get("pageNum");
                        if(Integer.valueOf(pageNum)<=entrust_List_Bean.getData().getPage().getPages()){
                            List<Entrust_List_Bean.DataBean.ListBean> zilist = entrust_List_Bean.getData().getList();
                            list.addAll(zilist);
                            // 初始化数据 与适配器
                            setEntrust_List_Adapter();
                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }



                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                entrust_List_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                entrust_List_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });

    }
    private void setEntrust_List_Adapter() {
        if (list.size() > 0) {
            entrust_List_null.setVisibility(View.GONE);
            entrust_List_pulltoscroll.setVisibility(View.VISIBLE);

            if (entrust_list_adapter == null) {
                entrust_list_adapter = new Entrust_List_Adapter(Entrust_List_Activity.this, list);
                entrust_List_MyListView.setAdapter(entrust_list_adapter);
            } else {
                entrust_list_adapter.notifyDataSetChanged();
            }
        }else {
            entrust_List_null.setVisibility(View.VISIBLE);
            entrust_List_pulltoscroll.setVisibility(View.GONE);
        }
    }

    private void initView() {
        entrust_List_Fan = (RelativeLayout) findViewById(R.id.entrust_List_Fan);
        entrust_List_view = (View) findViewById(R.id.entrust_List_view);
        entrust_List_MyListView = (MyListView) findViewById(R.id.entrust_List_MyListView);
        entrust_List_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.entrust_List_pulltoscroll);
        entrust_List_null = (LinearLayout) findViewById(R.id.entrust_List_null);

        /**
         * 解决scrollview 显示不在顶部问题
         */
        entrust_List_view.setFocusable(true);
        entrust_List_view.setFocusableInTouchMode(true);
        entrust_List_view.requestFocus();

        initRefreshListView(entrust_List_pulltoscroll);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }



}
