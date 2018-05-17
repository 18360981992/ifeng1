package com.ifeng_tech.treasuryyitong.ui.my;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.fragmet.zi_fragment.Pick_up_goods_ChaXun;
import com.ifeng_tech.treasuryyitong.fragmet.zi_fragment.Pick_up_goods_Zhuce;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.MyTabLayout;

/**
 * 提货详情
 */
public class Pick_up_goods_Activity extends BaseMVPActivity<Pick_up_goods_Activity,MyPresenter<Pick_up_goods_Activity>> {


    String[] tabtitle = {"提货单注册", "提货单查询"};
    private RelativeLayout up_goods_Fan;
    private TabLayout up_goods_TabLayout;


    @Override
    public MyPresenter<Pick_up_goods_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_goods_);
        initView();

        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);

        up_goods_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置tablayout的横杆器的长度
        up_goods_TabLayout.post(new Runnable() {
            @Override
            public void run() {
                MyTabLayout.setIndicator(up_goods_TabLayout,40,40);
            }
        });

        for (int i = 0; i < tabtitle.length; i++) {
            //添加tab
            up_goods_TabLayout.addTab(up_goods_TabLayout.newTab().setText(tabtitle[i]));
        }

        //设置tab的点击监听器
        up_goods_TabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    }


    @Override
    protected void onResume() {
        super.onResume();
        setSelected(tabtitle[0]);
    }

    //设置传值方法
    private void setSelected(String value) {
       if(value.equals(tabtitle[0])){
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.up_goods_FrameLayout, new Pick_up_goods_Zhuce()).commit();
       }else{
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.up_goods_FrameLayout, new Pick_up_goods_ChaXun()).commit();
       }

    }

    private void initView() {

        up_goods_Fan = (RelativeLayout) findViewById(R.id.up_goods_Fan);
        up_goods_TabLayout = (TabLayout) findViewById(R.id.up_goods_TabLayout);
    }
}
