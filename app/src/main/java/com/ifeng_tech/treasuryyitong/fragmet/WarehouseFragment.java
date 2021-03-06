package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Warehouse_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.login.Login_New_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.cangku.My_Warehouse_Datail_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

/**
 * Created by zzt on 2018/5/29.
 *
 *  一级页面的仓库
 */

public class WarehouseFragment extends Fragment implements View.OnClickListener {
    private Button warehouse_fra_btn;
    private View wview;
    private MyListView warehouse_fra_MyListView;
    private PullToRefreshScrollView warehouse_fra_pulltoscroll;
    private LinearLayout warehouse_fra_null;
    private HomePageActivity activity;
    private RelativeLayout warehouse_fra_weidenglu;
    private LinearLayout warehouse_fra_denglu;
    private SharedPreferences.Editor edit;
    private boolean aBoolean;

    List<WarehouseBean.DataBean.ListBean> list = new ArrayList<WarehouseBean.DataBean.ListBean>();
    private Warehouse_Adapter warehouse_adapter;
    private WarehouseBean warehouseBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.warehouse_fragmet, container, false);
        activity = (HomePageActivity) getActivity();
        initView(view);

        return view;
    }

    HashMap<String, String> map = new HashMap<>();
    int pageNum=1;
    @Override
    public void onResume() {
        super.onResume();

        sp = activity.getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();
        // 登录状态
        aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);
        if(aBoolean){
            warehouse_fra_weidenglu.setVisibility(View.GONE);
            warehouse_fra_denglu.setVisibility(View.VISIBLE);

            pageNum=1;
            map.put("pageNum",pageNum+"");
            map.put("pageSize",""+10);
            getFirstConect(map);

            warehouse_fra_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                    MyUtils.setToast("下拉了。。。");
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

            warehouse_fra_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    WarehouseBean.DataBean.MaxTrasferableAmountAndFeeBean maxTrasferableAmountAndFee = warehouseBean.getData().getMaxTrasferableAmountAndFee();
                    Intent intent = new Intent(activity, My_Warehouse_Datail_Activity.class);
                    intent.putExtra("WarehouseBean", list.get(position));
                    intent.putExtra("MaxTrasferableAmountAndFeeBean",maxTrasferableAmountAndFee);   // 这个对象用来设定提货/转赠的手续费
                    startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            });

            warehouse_fra_MyListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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

        }else{
            warehouse_fra_weidenglu.setVisibility(View.VISIBLE);
            warehouse_fra_denglu.setVisibility(View.GONE);
        }

    }


    // 首次进入页面获取列表
    private void getFirstConect(final HashMap<String, String> map) {
        activity.myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
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
                warehouse_fra_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                warehouse_fra_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 下拉加载更多
    private void getNextConect(final HashMap<String, String> map) {
        activity.myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        warehouseBean = new Gson().fromJson(json, WarehouseBean.class);
                        String pageNum = map.get("pageNum");

                        if( Integer.valueOf(pageNum) <= warehouseBean.getData().getPageInfo().getTotalPage()){
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
                warehouse_fra_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                warehouse_fra_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 初始化适配器
    public void setWarehouseAdapter() {
        if (list.size() > 0) {
            warehouse_fra_null.setVisibility(View.GONE);
            warehouse_fra_pulltoscroll.setVisibility(View.VISIBLE);
            warehouse_adapter=null;
            if (warehouse_adapter == null) {
                warehouse_adapter = new Warehouse_Adapter(activity, list,warehouseBean);
                warehouse_fra_MyListView.setAdapter(warehouse_adapter);
            } else {
                warehouse_adapter.notifyDataSetChanged();
            }
        }else {
            warehouse_fra_null.setVisibility(View.VISIBLE);
            warehouse_fra_pulltoscroll.setVisibility(View.GONE);
        }
    }


    private void initView(View view) {
        warehouse_fra_btn = (Button) view.findViewById(R.id.warehouse_fra_btn);
        wview = (View) view.findViewById(R.id.wview);
        warehouse_fra_MyListView = (MyListView) view.findViewById(R.id.warehouse_fra_MyListView);
        warehouse_fra_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.warehouse_fra_pulltoscroll);
        warehouse_fra_null = (LinearLayout) view.findViewById(R.id.warehouse_fra_null);
        warehouse_fra_weidenglu = (RelativeLayout) view.findViewById(R.id.warehouse_fra_weidenglu);
        warehouse_fra_denglu = (LinearLayout) view.findViewById(R.id.warehouse_fra_denglu);

        warehouse_fra_btn.setOnClickListener(this);

        View viewById = view.findViewById(R.id.wview);
        /**
         * 解决scrollview 显示不在顶部问题
         */
        viewById.setFocusable(true);
        viewById.setFocusableInTouchMode(true);
        viewById.requestFocus();

        activity.initRefreshListView(warehouse_fra_pulltoscroll);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.warehouse_fra_btn:
                Intent intent = new Intent(activity, Login_New_Activity.class);
                startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                break;
        }
    }
}
