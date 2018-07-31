package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.jpush.JPush_Bean;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.Recharge_Message_Activity;
import com.ifeng_tech.treasuryyitong.ui.Safety_Message_Activity;
import com.ifeng_tech.treasuryyitong.ui.System_Message_Activity;
import com.ifeng_tech.treasuryyitong.ui.login.Login_New_Activity;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import static android.content.Context.MODE_PRIVATE;
import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

/**
 * Created by zzt on 2018/4/27.
 * <p>
 * 消息
 */

public class MessageFragmet extends Fragment {
    private ImageView message_xitong_img;
    private RelativeLayout message_xitong;
    private ImageView message_congzhi_img;
    private RelativeLayout message_congzhi;
    private ImageView message_anquan_img;
    private RelativeLayout message_anquan;
    private ImageView xitong_jiantou;
    public static TextView message_xitong_shumu;
    private ImageView congzhi_jiantou;
    public static TextView message_congzhi_shumu;
    private ImageView anquan_jiantou;
    public static TextView message_anquan_shumu;
    private HomePageActivity activity;
    private boolean aBoolean;
    private SharedPreferences.Editor edit;
    private SharedPreferences sp_message;
    private SharedPreferences.Editor edit1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragmet, container, false);

        initView(view);

        activity = (HomePageActivity) getActivity();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        sp = activity.getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();

        aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);

        message_xitong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了系统消息。。。");
                if(aBoolean){
                    message_xitong_shumu.setVisibility(View.GONE);
                    // 获取本地的消息总数目 并设置隐藏/显示
                    // 创建保存消息的本地文件
                    String uid = DashApplication.sp.getString(SP_String.UID, "");
                    sp_message = activity.getSharedPreferences("ifeng_message_" + uid, MODE_PRIVATE);
                    edit1 = sp_message.edit();
                    String extras = sp_message.getString(SP_String.XIAOXI_SHUMU, "");
                    JPush_Bean jPush_bean = new Gson().fromJson(extras, JPush_Bean.class);
                    if(jPush_bean!=null){
                        jPush_bean.setSysNum(0);
                        jPush_bean.setMsg("");
                        String json = new Gson().toJson(jPush_bean);
                        edit1.putString(SP_String.XIAOXI_SHUMU,json).commit();
                    }

                    Intent intent = new Intent(activity, System_Message_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    Intent intent1 = new Intent(activity, Login_New_Activity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });

        message_congzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了充值消息。。。");
                if(aBoolean){
                    message_congzhi_shumu.setVisibility(View.GONE);

                    // 获取本地的消息总数目 并设置隐藏/显示
                    // 创建保存消息的本地文件
                    String uid = DashApplication.sp.getString(SP_String.UID, "");
                    sp_message = activity.getSharedPreferences("ifeng_message_"+uid, MODE_PRIVATE);
                    edit1 = sp_message.edit();
                    String extras = sp_message.getString(SP_String.XIAOXI_SHUMU, "");
                    JPush_Bean jPush_bean = new Gson().fromJson(extras, JPush_Bean.class);
                    if(jPush_bean!=null) {
                        jPush_bean.setGoldSum(0);
                        String json = new Gson().toJson(jPush_bean);
                        edit1.putString(SP_String.XIAOXI_SHUMU, json).commit();
                    }

                    Intent intent = new Intent(activity, Recharge_Message_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    Intent intent1 = new Intent(activity, Login_New_Activity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            }
        });

        message_anquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了安全消息。。。");
                if(aBoolean){
                    message_anquan_shumu.setVisibility(View.GONE);
                    // 获取本地的消息总数目 并设置隐藏/显示
                    // 创建保存消息的本地文件
                    String uid = DashApplication.sp.getString(SP_String.UID, "");
                    sp_message = activity.getSharedPreferences("ifeng_message_"+uid, MODE_PRIVATE);
                    edit1 = sp_message.edit();

                    String extras = sp_message.getString(SP_String.XIAOXI_SHUMU, "");
                    JPush_Bean jPush_bean = new Gson().fromJson(extras, JPush_Bean.class);
                    if(jPush_bean!=null) {
                        jPush_bean.setSafeNum(0);
                        String json = new Gson().toJson(jPush_bean);
                        LogUtils.i("jba","message_anquan===="+json);
                        edit1.putString(SP_String.XIAOXI_SHUMU, json).commit();
                    }
                    Intent intent = new Intent(activity, Safety_Message_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    Intent intent1 = new Intent(activity, Login_New_Activity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            }
        });

    }

    private void initView(View view) {
        message_xitong_img = (ImageView) view.findViewById(R.id.message_xitong_img);
        message_xitong = (RelativeLayout) view.findViewById(R.id.message_xitong);
        message_congzhi_img = (ImageView) view.findViewById(R.id.message_congzhi_img);
        message_congzhi = (RelativeLayout) view.findViewById(R.id.message_congzhi);
        message_anquan_img = (ImageView) view.findViewById(R.id.message_anquan_img);
        message_anquan = (RelativeLayout) view.findViewById(R.id.message_anquan);
        xitong_jiantou = (ImageView) view.findViewById(R.id.xitong_jiantou);
        message_xitong_shumu = (TextView) view.findViewById(R.id.message_xitong_shumu);
        congzhi_jiantou = (ImageView) view.findViewById(R.id.congzhi_jiantou);
        message_congzhi_shumu = (TextView) view.findViewById(R.id.message_congzhi_shumu);
        anquan_jiantou = (ImageView) view.findViewById(R.id.anquan_jiantou);
        message_anquan_shumu = (TextView) view.findViewById(R.id.message_anquan_shumu);
    }
}
