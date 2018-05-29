package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.fragmet.zi_fragment.My_Given_Change_into_Fragment;
import com.ifeng_tech.treasuryyitong.fragmet.zi_fragment.My_Given_Turn_out_Fragment;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.view.MyTabLayout;

/**
 * 转赠列表
 */
public class My_Given_list_Activity extends BaseMVPActivity<My_Given_list_Activity, MyPresenter<My_Given_list_Activity>> {

    private RelativeLayout my_Given_Fan;
    String[] tabtitle = {"转入", "转出"};
    private TabLayout my_Given_TabLayout;
    private int position;
    private FragmentManager fragmentManager;
    My_Given_Change_into_Fragment my_given_change_into_fragment=new My_Given_Change_into_Fragment();
    My_Given_Turn_out_Fragment my_given_turn_out_fragment =new My_Given_Turn_out_Fragment();
    @Override
    public MyPresenter<My_Given_list_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__given_list_);
        initView();

        my_Given_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置tablayout的横杆器的长度
        my_Given_TabLayout.post(new Runnable() {
            @Override
            public void run() {
                MyTabLayout.setIndicator(my_Given_TabLayout, 40, 40);
            }
        });

        for (int i = 0; i < tabtitle.length; i++) {
            //添加tab
            my_Given_TabLayout.addTab(my_Given_TabLayout.newTab().setText(tabtitle[i]));
        }


        //设置tab的点击监听器
        my_Given_TabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                setSelected(position);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.my_Given_FrameLayout,my_given_change_into_fragment)
                .add(R.id.my_Given_FrameLayout,my_given_turn_out_fragment)
                .show(my_given_change_into_fragment)
                .hide(my_given_turn_out_fragment)
                .commit();

    }

    //设置传值方法
    private void setSelected(int value) {

        if("转入".equals(tabtitle[value])){
            fragmentManager.beginTransaction()
                    .show(my_given_change_into_fragment)
                    .hide(my_given_turn_out_fragment)
                    .commit();
        }else{
            fragmentManager.beginTransaction()
                    .hide(my_given_change_into_fragment)
                    .show(my_given_turn_out_fragment)
                    .commit();
        }
    }


    private void initView() {
        my_Given_Fan = (RelativeLayout) findViewById(R.id.my_Given_Fan);
        my_Given_TabLayout = (TabLayout) findViewById(R.id.my_Given_TabLayout);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
