package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.ui.Collection_directory_Activity;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.login.Login_New_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.weituo.Entrust_Warehousing_Activity;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

/**
 * Created by zzt on 2018/7/17.
 */

public class FindFragment extends Fragment {
    private LinearLayout fx_weituo;
    private LinearLayout fx_cangping;
    private LinearLayout fx_zixun;
    private LinearLayout fx_hezuo;
    private HomePageActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment, container, false);
        initView(view);
        activity = (HomePageActivity) getActivity();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        final boolean aBoolean = DashApplication.sp.getBoolean(SP_String.ISLOGIN, false);
        // 点击委托入库
        fx_weituo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aBoolean){
                    Intent intent = new Intent(activity, Entrust_Warehousing_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{
                    Intent intent1 = new Intent(activity, Login_New_Activity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            }
        });

        // 点击藏品目录
        fx_cangping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Collection_directory_Activity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

//        // 点击咨询
//        fx_zixun.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyUtils.setToast("该功能正在开发中，敬请期待！！");
//            }
//        });
//
//        // 点击合作平台
//        fx_hezuo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyUtils.setToast("该功能正在开发中，敬请期待！！");
//            }
//        });
    }

    private void initView(View view) {
        fx_weituo = (LinearLayout) view.findViewById(R.id.fx_weituo);
        fx_cangping = (LinearLayout) view.findViewById(R.id.fx_cangping);
        fx_zixun = (LinearLayout) view.findViewById(R.id.fx_zixun);
        fx_hezuo = (LinearLayout) view.findViewById(R.id.fx_hezuo);
    }
}
