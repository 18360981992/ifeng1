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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.MyListAdapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.MyListBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.LoginActivity;
import com.ifeng_tech.treasuryyitong.ui.my.ADVP_R_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Certification_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.MyCollectActivity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Collocation_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_list_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Property_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Warehouse_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Safe_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Setting_Activity;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.content.Context.MODE_PRIVATE;
import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

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
    private ListView wode_MyListView;
    List<MyListBean> myListBeen = new ArrayList<>();
    private HomePageActivity activity;
    private Intent intent;
    private RelativeLayout wode_anquanbaohu;
    private RelativeLayout wode_shezhi;
    private LinearLayout wode_denglu;
    private LinearLayout wode_weidenglu;
    private ImageView wode_yirenzheng;
    private boolean aBoolean;
    private int shiming_type;
    private SharedPreferences.Editor edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragmet, container, false);
        initView(view);

        activity = (HomePageActivity) getActivity();



        wode_weidenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });


        /**
         * 点击认证按钮需要判断当前状态
         */
        wode_weirenzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (shiming_type){
                    case 1:// 已认证
                        break;
                    case 2:// 认证中
                        // 根据状态 选择隐藏/显示  1== 认证中 2==认证失败
                        intent = new Intent(activity, ADVP_R_Activity.class);
                        intent.putExtra("rengzheng_type",1);
                        startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        break;
                    case 3:// 认证失败
                        // 根据状态 选择隐藏/显示  1== 认证中 2==认证失败
                        intent = new Intent(activity, ADVP_R_Activity.class);
                        intent.putExtra("rengzheng_type",2);
                        startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        break;
                    case 4:// 未认证
                        intent = new Intent(activity, Certification_Activity.class);
                        startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        break;
                }
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        /**
         * 进入页面后，现获取用户信息，判断是否登录状态
         *
         * 这里先做模拟数据  未登录状态
         */
        sp = activity.getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();

        aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);// 登录状态

        shiming_type = 4;   // 实名认证的状态

        if(aBoolean){
            wode_denglu.setVisibility(View.VISIBLE);
            wode_weidenglu.setVisibility(View.GONE);
            activity.myPresenter.getPreContent(APIs.getUser, new MyInterfaces() {
                @Override
                public void chenggong(String json) {
                    LogUtils.i("jiba","==="+json);
                }

                @Override
                public void shibai(String ss) {

                }
            });

            Glide.with(activity)
                    .load(R.drawable.guangao)
                    .bitmapTransform(new CropCircleTransformation(DashApplication.getAppContext()))
                    .into(wode_touxiang);

            // 登录状态以后还需要判断用户信息是否实名认证状态
            switch (shiming_type){
                case 1:  // 已认证
                    wode_weirenzheng.setVisibility(View.GONE);
                    wode_yirenzheng.setVisibility(View.VISIBLE);
                    break;
                case 2: // 认证中
                    wode_weirenzheng.setVisibility(View.VISIBLE);
                    wode_yirenzheng.setVisibility(View.GONE);
                    wode_weirenzheng.setText("认证中...");
                    wode_weirenzheng.setTextColor(getResources().getColor(R.color.name_se));
                    wode_weirenzheng.setBackgroundColor(getResources().getColor(R.color.fengouxian));
                    wode_weirenzheng.setEnabled(false);
                    break;
                case 3:// 认证失败
                    wode_weirenzheng.setVisibility(View.VISIBLE);
                    wode_yirenzheng.setVisibility(View.GONE);
                    wode_weirenzheng.setText("认证失败");
                    wode_weirenzheng.setTextColor(getResources().getColor(R.color.name_se));
                    wode_weirenzheng.setBackgroundColor(getResources().getColor(R.color.fengouxian));
                    wode_weirenzheng.setEnabled(true);
                    break;
                case 4: // 未认证
                    wode_weirenzheng.setVisibility(View.VISIBLE);
                    wode_yirenzheng.setVisibility(View.GONE);
                    wode_weirenzheng.setEnabled(true);
                    break;
            }

        }else{
            wode_denglu.setVisibility(View.GONE);
            wode_weidenglu.setVisibility(View.VISIBLE);
            wode_touxiang.setImageResource(R.drawable.wode_weidenglu_img);
        }


        wode_MyListView.setAdapter(new MyListAdapter(activity, myListBeen));

        // listview的点击事件
        wode_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 判断是否登录
                if(aBoolean){
                    switch (position) {
                        case 0:  // 我的资产
                            intent = new Intent(activity, My_Property_Activity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;
                        case 1: // 我的征集
                            intent = new Intent(activity, MyCollectActivity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;
                        case 2:  // 我的托管
                            intent = new Intent(activity, My_Collocation_Activity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;
                        case 3:  // 转赠列表
                            intent = new Intent(activity, My_Given_list_Activity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;
//                    case 4:  //  入库管理
//                        intent = new Intent(activity, Incoming_Test_Activity.class);
//                        activity.startActivity(intent);
//                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
//                        break;
//                    case 5:  // 出库管理
//                        intent = new Intent(activity, Stock_Removal_Activity.class);
//                        activity.startActivity(intent);
//                        break;
                        case 4:   // 我的仓库
                            intent = new Intent(activity, My_Warehouse_Activity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;
                        case 5:   // 提货注册
                            intent = new Intent(activity, Pick_up_goods_Activity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;

                    }
                }else{ // 没登录直接跳到登录页面
                    intent = new Intent(activity, LoginActivity.class);
                    startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });
        // 安全保护 点击
        wode_anquanbaohu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aBoolean){
                    intent = new Intent(activity, Safe_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{ // 没登录直接跳到登录页面
                    intent = new Intent(activity, LoginActivity.class);
                    startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });

        // 设置 点击
        wode_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aBoolean){
                    intent = new Intent(activity, Setting_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{ // 没登录直接跳到登录页面
                    intent = new Intent(activity, LoginActivity.class);
                    startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden==false&&aBoolean){

        }
    }



    private void initView(View view) {
        wode_touxiang = (ImageView) view.findViewById(R.id.wode_touxiang);
        wode_name = (TextView) view.findViewById(R.id.wode_name);
        wode_hao = (TextView) view.findViewById(R.id.wode_hao);
        wode_weirenzheng = (TextView) view.findViewById(R.id.wode_weirenzheng);
        wode_MyListView = (MyListView) view.findViewById(R.id.wode_MyListView);
        wode_anquanbaohu = view.findViewById(R.id.wode_anquanbaohu);
        wode_shezhi = view.findViewById(R.id.wode_shezhi);
        wode_denglu = (LinearLayout) view.findViewById(R.id.wode_denglu);
        wode_weidenglu = (LinearLayout) view.findViewById(R.id.wode_weidenglu);
        wode_yirenzheng = (ImageView) view.findViewById(R.id.wode_yirenzheng);


        initData();
    }

    public void initData() {
        myListBeen.add(new MyListBean(R.drawable.wode_zican, "我的资产"));
        myListBeen.add(new MyListBean(R.drawable.wode_zhengji, "我的征集"));
        myListBeen.add(new MyListBean(R.drawable.wode_tuoguan, "我的托管"));
        myListBeen.add(new MyListBean(R.drawable.wode_zhuanzeng, "转赠列表"));
//        myListBeen.add(new MyListBean(R.drawable.wode_ruku,"入库管理"));
//        myListBeen.add(new MyListBean(R.drawable.wode_chuku,"出库管理"));
        myListBeen.add(new MyListBean(R.drawable.wode_cangku, "我的仓库"));
        myListBeen.add(new MyListBean(R.drawable.wode_tihuo, "提货查询"));
    }
}
