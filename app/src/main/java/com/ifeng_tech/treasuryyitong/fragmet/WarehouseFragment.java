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

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Warehouse_Adapter;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.LoginActivity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Warehouse_Datail_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

/**
 * Created by zzt on 2018/5/29.
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

    List<WarehouseBean> list = new ArrayList<WarehouseBean>();
    private Warehouse_Adapter warehouse_adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.warehouse_fragmet, container, false);
        activity = (HomePageActivity) getActivity();

        initView(view);
        initData();

        sp = activity.getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();
        // 登录状态
        aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if(aBoolean){
            warehouse_fra_weidenglu.setVisibility(View.GONE);
            warehouse_fra_denglu.setVisibility(View.VISIBLE);
            if (list.size() > 0) {
                warehouse_fra_null.setVisibility(View.GONE);
                warehouse_fra_pulltoscroll.setVisibility(View.VISIBLE);
                // 初始化数据 与适配器
                setAdapter();
            } else {
                warehouse_fra_null.setVisibility(View.VISIBLE);
                warehouse_fra_pulltoscroll.setVisibility(View.GONE);
            }

            warehouse_fra_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                    MyUtils.setToast("下拉了。。。");
                    warehouse_fra_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                    warehouse_fra_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                }
            });

            warehouse_fra_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(activity, My_Warehouse_Datail_Activity.class);
                    intent.putExtra("WarehouseBean", list.get(position));
                    startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            });

        }else{
            warehouse_fra_weidenglu.setVisibility(View.VISIBLE);
            warehouse_fra_denglu.setVisibility(View.GONE);
        }

    }

    public void setAdapter() {
        if (warehouse_adapter == null) {
            warehouse_adapter = new Warehouse_Adapter(activity, list);
            warehouse_fra_MyListView.setAdapter(warehouse_adapter);
        } else {
            warehouse_adapter.notifyDataSetChanged();
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

        initRefreshListView();

    }

    private void initRefreshListView() {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        warehouse_fra_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = warehouse_fra_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");
    }

    public void initData() {
        for (int i = 0; i < 20; i++) {
            list.add(new WarehouseBean("655286224", R.drawable.guangao, "世博四连体", 2000, 1000));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.warehouse_fra_btn:
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                break;
        }
    }
}