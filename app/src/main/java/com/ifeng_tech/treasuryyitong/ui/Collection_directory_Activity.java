package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.cangpin.Collection_directory_Bean;
import com.ifeng_tech.treasuryyitong.fragmet.zi_fragment.Collection_directory_Fragment;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyTabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 藏品目录
 */
public class Collection_directory_Activity extends BaseMVPActivity<Collection_directory_Activity, MyPresenter<Collection_directory_Activity>> {

    private RelativeLayout collection_mulu_Fan;
    private LinearLayout collection_mulu_sousuo;
    private TabLayout collection_mulu_TabLayout;
    private FrameLayout collection_mulu_FrameLayout;

    List<String> list = new ArrayList<>();

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


        myPresenter.getPreContent(APIs.getFirstCategoryList, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Collection_directory_Bean collection_directory_bean = new Gson().fromJson(json, Collection_directory_Bean.class);
                        List<Collection_directory_Bean.DataBean.FirstCategoryListBean> firstCategoryList = collection_directory_bean.getData().getFirstCategoryList();
                        for (Collection_directory_Bean.DataBean.FirstCategoryListBean bean :firstCategoryList
                                ) {
                            list.add(bean.getCategoryName());
                        }
                        list.add(0,"全部");
                        for (int i = 0; i < list.size(); i++) {
                            //添加tab
                            collection_mulu_TabLayout.addTab(collection_mulu_TabLayout.newTab().setText(list.get(i)));
                        }

                        //设置传值，，将title的值穿走，，默认展示“全部”
                        setSelected(list.get(0));

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

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
