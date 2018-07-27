package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.zixun.Information_LanMu_Bean;
import com.ifeng_tech.treasuryyitong.fragmet.zi_fragment.Information_zi_Fragment;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyTabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *  资讯列表
 */
public class Information_Activity extends BaseMVPActivity<Information_Activity, MyPresenter<Information_Activity>> {

    private TabLayout zixun_TabLayout;
    private FrameLayout zixun_FrameLayout;
    private RelativeLayout zixun_Fan;


    private List<Information_LanMu_Bean.DataBean.ListBean> list;

    @Override
    public MyPresenter<Information_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_);
        initView();

        zixun_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

//        HashMap<String, String> map = new HashMap<>();
//        map.put("mainColumnId","147");

        myPresenter.getPreContent(APIs.getSubColumnList, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Information_LanMu_Bean information_lanMu_bean = new Gson().fromJson(json, Information_LanMu_Bean.class);
                        list = information_lanMu_bean.getData().getList();
                        if(list.size()>0){
                            List<String> listTitle=new ArrayList<>();
                            listTitle.clear();
                            for (Information_LanMu_Bean.DataBean.ListBean bean: list){
                                listTitle.add(bean.getName());
                            }
                            listTitle.add(0,"全部");
                            for (int i = 0; i < listTitle.size(); i++) {
                                //添加tab
                                zixun_TabLayout.addTab(zixun_TabLayout.newTab().setText(listTitle.get(i)));
                            }

                            //设置传值，，将title的值穿走，，默认展示“全部”
                            setSelected(listTitle.get(0));
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast("资讯主栏目=="+ss);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        //设置tablayout的横杆器的长度
        zixun_TabLayout.post(new Runnable() {
            @Override
            public void run() {
                MyTabLayout.reflex(zixun_TabLayout);
            }
        });
    }

    //设置传值方法  id = 147    id = 194
    int id=0;
    private void setSelected(String value) {

        String type="";
        if(value.equals("全部")){
            id=147;
            type="1";
        }else{
            for (Information_LanMu_Bean.DataBean.ListBean bean: list){
                if(value.equals(bean.getName())){
                    id=bean.getId();
                    break;
                }
            }
            type="2";
        }


        Bundle bundle = new Bundle();
        bundle.putString("id", id+"");
        bundle.putString("type",type);
        bundle.putString("top",value);

        Information_zi_Fragment information_zi_Fragment = new Information_zi_Fragment();
        information_zi_Fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.zixun_FrameLayout, information_zi_Fragment).commit();
    }


    private void initView() {
        zixun_TabLayout = (TabLayout) findViewById(R.id.zixun_TabLayout);
        zixun_FrameLayout = (FrameLayout) findViewById(R.id.zixun_FrameLayout);
        zixun_Fan = (RelativeLayout) findViewById(R.id.zixun_Fan);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
