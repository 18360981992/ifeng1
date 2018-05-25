package com.ifeng_tech.treasuryyitong.fragmet;

import android.app.Notification;
import android.app.NotificationManager;
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

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.service.HeartbeatService;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.LoginActivity;
import com.ifeng_tech.treasuryyitong.ui.Recharge_Message_Activity;
import com.ifeng_tech.treasuryyitong.ui.Safety_Message_Activity;
import com.ifeng_tech.treasuryyitong.ui.System_Message_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;
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
    private TextView message_xitong_shumu;
    private ImageView congzhi_jiantou;
    private TextView message_congzhi_shumu;
    private ImageView anquan_jiantou;
    private TextView message_anquan_shumu;
    private HomePageActivity activity;
    private boolean aBoolean;
    private SharedPreferences.Editor edit;

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
        // 心跳监听回调
        HeartbeatService.setHearbeatJieKou(new HeartbeatService.HearbeatJieKou() {
            @Override
            public void hearbeatChuan(int type,int num) {
                MyUtils.setToast("有新的消息,更新ui界面。。。");
                switch (type){
                    case 1:
                        setNotification("系统消息");
                        message_xitong_shumu.setVisibility(View.VISIBLE);
                        message_xitong_shumu.setText(""+num);
                        break;
                    case 2:
                        setNotification("充值提醒");
                        message_congzhi_shumu.setVisibility(View.VISIBLE);
                        message_congzhi_shumu.setText(""+num);
                        break;
                    case 3:
                        setNotification("安全设置");
                        message_anquan_shumu.setVisibility(View.VISIBLE);
                        message_anquan_shumu.setText(""+num);
                        break;
                }
            }
        });

        message_xitong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了系统消息。。。");
                if(aBoolean){
                    message_xitong_shumu.setVisibility(View.GONE);
                    Intent intent = new Intent(activity, System_Message_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    Intent intent1 = new Intent(activity, LoginActivity.class);
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
                    Intent intent = new Intent(activity, Recharge_Message_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    Intent intent1 = new Intent(activity, LoginActivity.class);
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
                    Intent intent = new Intent(activity, Safety_Message_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    Intent intent1 = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            }
        });

    }

    private  void setNotification(String ss) {
        // 发送通知,通知都将在状态栏
        NotificationManager notiManager = (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE); // 常量字符串
        // 创建通知
        Notification.Builder builder = new Notification.Builder(activity)
                .setContentTitle("您有一条新的消息！")
                .setContentText(ss+": 有新消息")
                .setSmallIcon(R.drawable.logo)
                .setAutoCancel(true)
                .setTicker("宝库易通：有一条新消息。。。")
                .setDefaults(Notification.DEFAULT_SOUND| Notification.DEFAULT_VIBRATE);  //震动和声音
//        Intent intent = new Intent(this, HomePageActivity.class);
//        intent.putExtra("xiaoxi","message");
//        long time = new Date().getTime() / 1000;  //以时间戳为请求唯一值
//        Log.i("jiba","时间戳===="+time);
//        PendingIntent pintIntent = PendingIntent
//                .getActivity(this, (int) time, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        //参数: PendingIntent
//        builder.setContentIntent(pintIntent);
        builder.setAutoCancel(true);  //单击后,状态栏的图标消失
        Notification notification = builder.build();
        // 管理器,进行让通知进行在状态栏出现
        notiManager.notify(1, notification);
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
