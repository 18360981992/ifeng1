package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.MyListAdapter;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.MyListBean;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.my.Incoming_Test_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.MyCollectActivity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Property_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Warehouse_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Stock_Removal_Activity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zzt on 2018/4/27.
 * <p>
 * 我的
 */

public class MyFragmet extends Fragment {
    private ImageView wode_touxiang;
    private TextView wode_name;
    private TextView wode_hao;
    private TextView wode_weirenzheng;
    private ListView wode_listview;
    List<MyListBean> myListBeen = new ArrayList<>();
    private HomePageActivity activity;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragmet, container, false);
        initView(view);

        activity = (HomePageActivity) getActivity();

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Glide.with(activity)
                .load(R.drawable.guangao)
                .bitmapTransform(new CropCircleTransformation(DashApplication.getAppContext()))
                .into(wode_touxiang);

        wode_listview.setAdapter(new MyListAdapter(activity,myListBeen));

        wode_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:  // 我的资产
                        intent = new Intent(activity, My_Property_Activity.class);
                        activity.startActivity(intent);
                        break;
                    case 1: // 我的征集
                        intent = new Intent(activity, MyCollectActivity.class);
                        activity.startActivity(intent);
                        break;
                    case 2:  // 我的托管
                        break;
                    case 3:  // 转赠列表
                        break;
                    case 4:  //  入库管理
                        intent = new Intent(activity, Incoming_Test_Activity.class);
                        activity.startActivity(intent);
                        break;
                    case 5:  // 出库管理
                        intent = new Intent(activity, Stock_Removal_Activity.class);
                        activity.startActivity(intent);
                        break;
                    case 6:   // 我的仓库
                        intent = new Intent(activity, My_Warehouse_Activity.class);
                        activity.startActivity(intent);
                        break;
                    case 7:   // 提货注册
                        intent = new Intent(activity, Pick_up_goods_Activity.class);
                        activity.startActivity(intent);
                        break;
                    case 8:  // 安全保护
                        break;
                    case 9:  // 设置
                        break;

                }
            }
        });
    }

    private void initView(View view) {
        wode_touxiang = (ImageView) view.findViewById(R.id.wode_touxiang);
        wode_name = (TextView) view.findViewById(R.id.wode_name);
        wode_hao = (TextView) view.findViewById(R.id.wode_hao);
        wode_weirenzheng = (TextView) view.findViewById(R.id.wode_weirenzheng);
        wode_listview = (ListView) view.findViewById(R.id.wode_listview);

        initData();
    }

    public void initData(){
        myListBeen.add(new MyListBean(R.drawable.wode_zichan,"我的资产"));
        myListBeen.add(new MyListBean(R.drawable.wode_zhengji,"我的征集"));
        myListBeen.add(new MyListBean(R.drawable.wode_tuoguan,"我的托管"));
        myListBeen.add(new MyListBean(R.drawable.wode_zhuanzeng,"转赠列表"));
        myListBeen.add(new MyListBean(R.drawable.wode_ruku,"入库管理"));
        myListBeen.add(new MyListBean(R.drawable.wode_chuku,"出库管理"));
        myListBeen.add(new MyListBean(R.drawable.wode_cangku,"我的仓库"));
        myListBeen.add(new MyListBean(R.drawable.wode_chuku,"提货查询"));
        myListBeen.add(new MyListBean(R.drawable.wode_anquan,"安全保护"));
        myListBeen.add(new MyListBean(R.drawable.wode_shezhi,"设置"));

    }
}
