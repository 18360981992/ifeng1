package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Warehouse_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.bean.my.QR_Bean;
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

public class My_Warehouse_Activity2 extends BaseMVPActivity<My_Warehouse_Activity2, MyPresenter<My_Warehouse_Activity2>> {

    private RelativeLayout my_warehouse_Fan2;
    private ImageView my_warehouse_zhangdan2;
    private View wview2;
    private MyListView my_warehouse_MyListView2;
    private PullToRefreshScrollView my_warehouse_pulltoscroll2;
    private LinearLayout my_warehouse_null2;
    List<WarehouseBean.DataBean.ListBean> list = new ArrayList<WarehouseBean.DataBean.ListBean>();
    private WarehouseBean warehouseBean;
    Warehouse_Adapter warehouse_adapter;
    @Override
    public MyPresenter<My_Warehouse_Activity2> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__warehouse_2);
        initView();

        my_warehouse_Fan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        my_warehouse_zhangdan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了账单。。。");
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

        my_warehouse_pulltoscroll2.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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

        my_warehouse_MyListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WarehouseBean.DataBean.ListBean listBean = list.get(position);
                Intent intent = getIntent();
                if(intent.getStringExtra("select").equals("转赠")){
                    QR_Bean userCode = new QR_Bean(intent.getStringExtra("userCode"), new QR_Bean.GoodsInfo(listBean.getGoodsId(), listBean.getGoodsCode(), listBean.getGoodsName(), "1"));
                    String json = new Gson().toJson(userCode, QR_Bean.class);
                    intent.putExtra("QR_Bean",json);
                    intent.putExtra("type","1");  // 表示从仓库跳入转赠
                    setResult( DashApplication.ZHUAN_TO_CANGKU_res,intent);
                    finish();
                }else if(intent.getStringExtra("select").equals("提货")){
                    intent.putExtra("WarehouseBean", list.get(position));
                    setResult(DashApplication.TIHUO_TO_CANGKU_res,intent);
                    finish();
                }

            }
        });

    }


    // 首次进入页面获取列表
    private void getFirstConect(final HashMap<String, String> map) {
        myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        warehouseBean = new Gson().fromJson(json, WarehouseBean.class);
                        List<WarehouseBean.DataBean.ListBean> zilist = warehouseBean.getData().getList();
                        list.clear();
                        list.addAll(zilist);

                        // 初始化数据 与适配器
                        setWarehouseAdapter();
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                my_warehouse_pulltoscroll2.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                my_warehouse_pulltoscroll2.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 下拉加载更多
    private void getNextConect(final HashMap<String, String> map) {
        myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        warehouseBean = new Gson().fromJson(json, WarehouseBean.class);
                        List<WarehouseBean.DataBean.ListBean> zilist = warehouseBean.getData().getList();
                        list.addAll(zilist);
                        // 初始化数据 与适配器
                        setWarehouseAdapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                my_warehouse_pulltoscroll2.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                my_warehouse_pulltoscroll2.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }


    // 初始化数据 与适配器
    public void setWarehouseAdapter() {
        if (list.size() > 0) {
            my_warehouse_null2.setVisibility(View.GONE);
            my_warehouse_pulltoscroll2.setVisibility(View.VISIBLE);

            if (warehouse_adapter == null) {
                warehouse_adapter = new Warehouse_Adapter(My_Warehouse_Activity2.this, list);
                my_warehouse_MyListView2.setAdapter(warehouse_adapter);
            } else {
                warehouse_adapter.notifyDataSetChanged();
            }
        }else {
            my_warehouse_null2.setVisibility(View.VISIBLE);
            my_warehouse_pulltoscroll2.setVisibility(View.GONE);
        }
    }

    private void initView() {
        my_warehouse_Fan2 = (RelativeLayout) findViewById(R.id.my_warehouse_Fan2);
        my_warehouse_zhangdan2 = (ImageView) findViewById(R.id.my_warehouse_zhangdan2);
        wview2 = (View) findViewById(R.id.wview2);
        my_warehouse_MyListView2 = (MyListView) findViewById(R.id.my_warehouse_MyListView2);
        my_warehouse_pulltoscroll2 = (PullToRefreshScrollView) findViewById(R.id.my_warehouse_pulltoscroll2);
        my_warehouse_null2 = (LinearLayout) findViewById(R.id.my_warehouse_null2);

        /**
         * 解决scrollview 显示不在顶部问题
         */
        wview2.setFocusable(true);
        wview2.setFocusableInTouchMode(true);
        wview2.requestFocus();

        initRefreshListView();
    }


    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        my_warehouse_pulltoscroll2.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = my_warehouse_pulltoscroll2.getLoadingLayoutProxy(true, false);
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
