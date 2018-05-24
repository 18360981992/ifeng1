package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.fragmet.zi_fragment.Collection_directory_Fragment;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyTabLayout;

/**
 * 藏品目录
 */
public class Collection_directory_Activity extends BaseMVPActivity<Collection_directory_Activity, MyPresenter<Collection_directory_Activity>> {

    String[] title = {"全部", "新中国邮票", "邮资封片", "“J”字头邮票", "栏目2", "栏目3", "栏目4", "栏目5"};
    private RelativeLayout collection_mulu_Fan;
    private LinearLayout collection_mulu_sousuo;
    private TabLayout collection_mulu_TabLayout;
    private FrameLayout collection_mulu_FrameLayout;

    @Override
    public MyPresenter<Collection_directory_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_directory_);
        initView();

        //设置tablayout的横杆器的长度
        collection_mulu_TabLayout.post(new Runnable() {
            @Override
            public void run() {
                MyTabLayout.reflex(collection_mulu_TabLayout);
            }
        });

        for (int i = 0; i < title.length; i++) {
            //添加tab
            collection_mulu_TabLayout.addTab(collection_mulu_TabLayout.newTab().setText(title[i]));
        }

        collection_mulu_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collection_mulu_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("点击了搜索。。。");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        //设置传值，，将title的值穿走，，默认展示“全部”
        setSelected(title[0]);

        //设置tab的点击监听器
        collection_mulu_TabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    //设置传值方法
    private void setSelected(String value) {

        Bundle bundle = new Bundle();
        bundle.putString("top", value);
        Collection_directory_Fragment collection_directory_Fragment = new Collection_directory_Fragment();
        collection_directory_Fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.collection_mulu_FrameLayout, collection_directory_Fragment).commit();
    }


    private void initView() {
        collection_mulu_Fan = (RelativeLayout) findViewById(R.id.collection_mulu_Fan);
        collection_mulu_sousuo = (LinearLayout) findViewById(R.id.collection_mulu_sousuo);
        collection_mulu_TabLayout = (TabLayout) findViewById(R.id.collection_mulu_TabLayout);
        collection_mulu_FrameLayout = (FrameLayout) findViewById(R.id.collection_mulu_FrameLayout);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai,R.anim.xiao_out_kuai);
    }
}
