package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
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
    private int position=0;
    private FragmentManager fragmentManager;
    Pick_up_goods_Zhuce pick_up_goods_zhuce = new Pick_up_goods_Zhuce();
    Pick_up_goods_ChaXun pick_up_goods_chaXun = new Pick_up_goods_ChaXun();

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
                .add(R.id.up_goods_FrameLayout,pick_up_goods_zhuce)
                .add(R.id.up_goods_FrameLayout,pick_up_goods_chaXun)
                .show(pick_up_goods_zhuce)
                .hide(pick_up_goods_chaXun)
                .commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 根据状态值做显示  状态值在查询的时候回用到
        if(position==0){
            setSelected(0);
        }else{
            setSelected(1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DashApplication.TIHUO_TO_CANGKU_req&&resultCode==DashApplication.TIHUO_TO_CANGKU_res){
            WarehouseBean.DataBean.ListBean warehouseBean = (WarehouseBean.DataBean.ListBean)data.getSerializableExtra("WarehouseBean");
            pick_up_goods_Activity_JieKou.chuan(warehouseBean);
        }
    }

    //设置传值方法
    private void setSelected(int value) {

       if("提货单注册".equals(tabtitle[value])){
           fragmentManager.beginTransaction()
                   .show(pick_up_goods_zhuce)
                   .hide(pick_up_goods_chaXun)
                   .commit();
       }else{
           fragmentManager.beginTransaction()
                   .hide(pick_up_goods_zhuce)
                   .show(pick_up_goods_chaXun)
                   .commit();
       }
    }

    private void initView() {

        up_goods_Fan = (RelativeLayout) findViewById(R.id.up_goods_Fan);
        up_goods_TabLayout = (TabLayout) findViewById(R.id.up_goods_TabLayout);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }

    public interface Pick_up_goods_Activity_JieKou{
        void chuan(WarehouseBean.DataBean.ListBean warehouseBean);
    }

    public  Pick_up_goods_Activity_JieKou pick_up_goods_Activity_JieKou;

    public void setPick_up_goods_Activity_JieKou(Pick_up_goods_Activity_JieKou pick_up_goods_Activity_JieKou) {
        this.pick_up_goods_Activity_JieKou = pick_up_goods_Activity_JieKou;
    }
}
