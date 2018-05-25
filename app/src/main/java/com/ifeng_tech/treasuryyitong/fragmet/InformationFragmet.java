package com.ifeng_tech.treasuryyitong.fragmet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.fragmet.zi_fragment.Information_zi_Fragment;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.view.MyTabLayout;

/**
 * Created by zzt on 2018/4/27.
 * <p>
 * 资讯
 */

public class InformationFragmet extends Fragment {
    private TabLayout zixun_TabLayout;
    private FrameLayout zixun_FrameLayout;
    String[] title={"全部","热门","关注","栏目1","栏目2","栏目3","栏目4","栏目5","栏目6","栏目7","栏目8"};
    private HomePageActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_fragmet, container, false);
        initView(view);

        activity = (HomePageActivity) getActivity();


        //设置tablayout的横杆器的长度
        zixun_TabLayout.post(new Runnable() {
            @Override
            public void run() {
                MyTabLayout.reflex(zixun_TabLayout);
            }
        });

        for (int i = 0; i < title.length; i++) {
            //添加tab
            zixun_TabLayout.addTab(zixun_TabLayout.newTab().setText(title[i]));
        }


        //设置tab的点击监听器
        zixun_TabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setSelected(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //设置传值，，将title的值穿走，，默认展示“全部”
        setSelected(title[0]);
    }

    //设置传值方法
    private void setSelected(String value) {
        Bundle bundle = new Bundle();
        bundle.putString("top", value);
        Information_zi_Fragment information_zi_Fragment = new Information_zi_Fragment();
        information_zi_Fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.zixun_FrameLayout, information_zi_Fragment).commit();
    }

    private void initView(View view) {
        zixun_TabLayout = (TabLayout) view.findViewById(R.id.zixun_TabLayout);
        zixun_FrameLayout = (FrameLayout) view.findViewById(R.id.zixun_FrameLayout);
    }
}
