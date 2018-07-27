package com.ifeng_tech.treasuryyitong.ui.my.tuoguan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.My_Collocation_list_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.my.My_Colloction_Bean;
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

/**
 *  我的托管
 */
public class My_Collocation_Activity extends BaseMVPActivity<My_Collocation_Activity, MyPresenter<My_Collocation_Activity>> {

    private RelativeLayout my_collocation_Fan;
    private MyListView my_collocation_MyListView;
    private PullToRefreshScrollView my_collocation_pulltoscroll;
    List<My_Colloction_Bean.DataBean.ListBean> list = new ArrayList<>();
    private My_Collocation_list_Adapter my_collocation_adapter;
    private LinearLayout my_collocation_null;

    @Override
    public MyPresenter<My_Collocation_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__collocation_);
        initView();

        my_collocation_Fan.setOnClickListener(new View.OnClickListener() {
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

        my_collocation_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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

        my_collocation_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(My_Collocation_Activity.this, My_Collocation_Detail_Activity.class);
                intent.putExtra("Collocation_list_Bean",list.get(position));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }

    // 首次进入页面获取列表
    private void getFirstConect(final HashMap<String, String> map) {
        myPresenter.postPreContent(APIs.getUserTrusteeshipList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        My_Colloction_Bean my_Colloction_Bean = new Gson().fromJson(json, My_Colloction_Bean.class);
                        List<My_Colloction_Bean.DataBean.ListBean> zilist = my_Colloction_Bean.getData().getList();
                        list.clear();
                        list.addAll(zilist);
                        // 初始化数据 与适配器
                        setMy_Collocation_list_Adapter();
                    }else if(code.equals("4000")){
                        my_collocation_null.setVisibility(View.VISIBLE);
                        my_collocation_pulltoscroll.setVisibility(View.GONE);
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                my_collocation_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                my_collocation_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 下拉加载更多
    private void getNextConect(final HashMap<String, String> map) {
        myPresenter.postPreContent(APIs.getUserTrusteeshipList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        My_Colloction_Bean my_Colloction_Bean = new Gson().fromJson(json, My_Colloction_Bean.class);
                        String pageNum = map.get("pageNum");
                        if(Integer.valueOf(pageNum) <= my_Colloction_Bean.getData().getPageInfo().getTotalPage()){
                            List<My_Colloction_Bean.DataBean.ListBean> zilist = my_Colloction_Bean.getData().getList();
                            list.addAll(zilist);
                            // 初始化数据 与适配器
                            setMy_Collocation_list_Adapter();
                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                my_collocation_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                my_collocation_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 适配器的刷新
    private void setMy_Collocation_list_Adapter() {
        if(list.size()>0) {
            my_collocation_null.setVisibility(View.GONE);
            my_collocation_pulltoscroll.setVisibility(View.VISIBLE);
            if (my_collocation_adapter == null) {
                my_collocation_adapter = new My_Collocation_list_Adapter(My_Collocation_Activity.this, list);
                my_collocation_MyListView.setAdapter(my_collocation_adapter);
            } else {
                my_collocation_adapter.notifyDataSetChanged();
            }
        }else {
            my_collocation_null.setVisibility(View.VISIBLE);
            my_collocation_pulltoscroll.setVisibility(View.GONE);
        }
    }


    private void initView() {
        my_collocation_Fan = (RelativeLayout) findViewById(R.id.my_collocation_Fan);
        my_collocation_MyListView = (MyListView) findViewById(R.id.my_collocation_MyListView);
        my_collocation_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.my_collocation_pulltoscroll);
        my_collocation_null = (LinearLayout) findViewById(R.id.my_collocation_null);

        // 设置刷新
        initRefreshListView(my_collocation_pulltoscroll);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
