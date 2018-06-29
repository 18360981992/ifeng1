package com.ifeng_tech.treasuryyitong.ui.my;

import android.app.ProgressDialog;
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
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 我的仓库
 */

public class My_Warehouse_Activity extends BaseMVPActivity<My_Warehouse_Activity, MyPresenter<My_Warehouse_Activity>> {

    private RelativeLayout my_warehouse_Fan;
    private ImageView my_warehouse_zhangdan;

    List<WarehouseBean.DataBean.ListBean> list = new ArrayList<WarehouseBean.DataBean.ListBean>();
    private Warehouse_Adapter warehouse_adapter;
    private LinearLayout my_warehouse_null;
    private MyListView my_warehouse_MyListView;
    private PullToRefreshScrollView my_warehouse_pulltoscroll;
    private WarehouseBean warehouseBean;
    private ProgressDialog aniDialog;

    @Override
    public MyPresenter<My_Warehouse_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__warehouse_);
        initView();

        my_warehouse_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        my_warehouse_zhangdan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                MyUtils.setToast("点击了账单。。。");
//            }
//        });

    }

    HashMap<String, String> map = new HashMap<>();
    int pageNum=1;
    @Override
    protected void onResume() {
        super.onResume();

        pageNum=1;
        map.put("pageNum",pageNum+"");
        map.put("pageSize",""+10);

        //  进度框
        aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        getFirstConect(map);

        my_warehouse_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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

        my_warehouse_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i=0;i<list.size();i++){
                    WarehouseBean.DataBean.ListBean listBean = list.get(i);
                    listBean.setFlag(false);
                    list.set(i,listBean);
                }
                warehouse_adapter = null;
                setWarehouseAdapter();

                WarehouseBean.DataBean.MaxTrasferableAmountAndFeeBean maxTrasferableAmountAndFee = warehouseBean.getData().getMaxTrasferableAmountAndFee();
                Intent intent = new Intent(My_Warehouse_Activity.this, My_Warehouse_Datail_Activity.class);
                intent.putExtra("WarehouseBean", list.get(position));
                intent.putExtra("MaxTrasferableAmountAndFeeBean",maxTrasferableAmountAndFee);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        my_warehouse_MyListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                for (int i=0;i<list.size();i++){
                    WarehouseBean.DataBean.ListBean listBean = list.get(i);
                    listBean.setFlag(false);
                    list.set(i,listBean);
                }
                warehouse_adapter = null;
                setWarehouseAdapter();

                WarehouseBean.DataBean.ListBean listBean = list.get(position);
                listBean.setFlag(true);
                list.set(position,listBean);
                warehouse_adapter = null;
                setWarehouseAdapter();

                return true;
            }
        });

    }


    // 首次进入页面获取列表
    private void getFirstConect(final HashMap<String, String> map) {
        myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
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
                my_warehouse_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
                my_warehouse_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
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
                        String pageNum = map.get("pageNum");
                        if(Integer.valueOf(pageNum)<=warehouseBean.getData().getPageInfo().getTotalPage()){
                            List<WarehouseBean.DataBean.ListBean> zilist = warehouseBean.getData().getList();
                            list.addAll(zilist);
                            // 初始化数据 与适配器
                            setWarehouseAdapter();

                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                my_warehouse_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                my_warehouse_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 初始化数据 与适配器
    public void setWarehouseAdapter() {
        if (list.size() > 0) {
            my_warehouse_null.setVisibility(View.GONE);
            my_warehouse_pulltoscroll.setVisibility(View.VISIBLE);
            if (warehouse_adapter == null) {
                warehouse_adapter = new Warehouse_Adapter(My_Warehouse_Activity.this, list,warehouseBean);
                my_warehouse_MyListView.setAdapter(warehouse_adapter);
            } else {
                warehouse_adapter.notifyDataSetChanged();
            }
        }else {
            my_warehouse_null.setVisibility(View.VISIBLE);
            my_warehouse_pulltoscroll.setVisibility(View.GONE);
        }
    }

    private void initView() {
        my_warehouse_Fan = (RelativeLayout) findViewById(R.id.my_warehouse_Fan);
//        my_warehouse_zhangdan = (ImageView) findViewById(R.id.my_warehouse_zhangdan);
        my_warehouse_null = (LinearLayout) findViewById(R.id.my_warehouse_null);
        my_warehouse_MyListView = (MyListView) findViewById(R.id.my_warehouse_MyListView);
        my_warehouse_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.my_warehouse_pulltoscroll);
        View viewById = findViewById(R.id.wview);
        /**
         * 解决scrollview 显示不在顶部问题
         */
        viewById.setFocusable(true);
        viewById.setFocusableInTouchMode(true);
        viewById.requestFocus();

        initRefreshListView(my_warehouse_pulltoscroll);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
