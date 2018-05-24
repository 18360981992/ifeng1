package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Intent;
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
import com.ifeng_tech.treasuryyitong.ui.Recharge_Message_Activity;
import com.ifeng_tech.treasuryyitong.ui.Safety_Message_Activity;
import com.ifeng_tech.treasuryyitong.ui.System_Message_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragmet, container, false);

        initView(view);

        activity = (HomePageActivity) getActivity();

        // 心跳监听回调
        HeartbeatService.setHearbeatJieKou(new HeartbeatService.HearbeatJieKou() {
            @Override
            public void hearbeatChuan(int num) {
                MyUtils.setToast("有新的消息。。。");

            }
        });

        message_xitong_shumu.setVisibility(View.VISIBLE);

        message_xitong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了系统消息。。。");
                Intent intent = new Intent(activity, System_Message_Activity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        message_congzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了充值消息。。。");
                Intent intent = new Intent(activity, Recharge_Message_Activity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        message_anquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了安全消息。。。");
                Intent intent = new Intent(activity, Safety_Message_Activity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
        return view;
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
