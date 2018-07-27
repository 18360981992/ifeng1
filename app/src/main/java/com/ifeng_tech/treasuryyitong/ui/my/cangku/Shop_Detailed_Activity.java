package com.ifeng_tech.treasuryyitong.ui.my.cangku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Shop_Detailed_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Shop_Detailed_List_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 藏品明细  区块链电子身份信息表
 */
public class Shop_Detailed_Activity extends BaseMVPActivity<Shop_Detailed_Activity, MyPresenter<Shop_Detailed_Activity>> {

    private RelativeLayout shop_Detailed_Fan;
    private MyListView shop_Detailed_MyListView;
    private PullToRefreshScrollView shop_Detailed_PullToRefreshScrollView;
    private TextView xinxi;
    private String goodsId;
    private ProgressDialog aniDialog;

    List<Shop_Detailed_List_Bean.DataBean.ListBean.ContentBean> list = new ArrayList<Shop_Detailed_List_Bean.DataBean.ListBean.ContentBean>();
    private Shop_Detailed_Adapter shop_detailed_adapter;

    @Override
    public MyPresenter<Shop_Detailed_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__detailed_);
        initView();

        shop_Detailed_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        goodsId = getIntent().getStringExtra("goodsId");
    }

    HashMap<String, String> map = new HashMap<>();
    int pageNum=1;

    @Override
    protected void onResume() {
        super.onResume();
        pageNum=1;
        map.put("pageNum",pageNum+"");
        map.put("pageSize",""+10);
        map.put("goodsId",goodsId);
        //  进度框
        aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);
        getFirstConect(map);  // 首次获取区块链信息

        shop_Detailed_PullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                MyUtils.setToast("下拉了。。。");
                pageNum=1;
                map.put("pageNum",pageNum+"");
                map.put("pageSize",""+10);
                map.put("goodsId",goodsId);
                getFirstConect(map);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageNum++;
                map.put("pageNum",pageNum+"");
                map.put("pageSize",""+10);
                map.put("goodsId",goodsId);
                getNextConect(map);
            }
        });

        // 电子身份列表点击
        shop_Detailed_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MyUtils.setToast(list.get(position).getId()+"");

                Intent intent = new Intent(Shop_Detailed_Activity.this, Shop_Detailed_Datail_Activity.class);
                intent.putExtra("id",""+list.get(position).getId());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

    }

    // 下拉加载更多
    private void getNextConect(HashMap<String, String> map) {

        myPresenter.postPreContent(APIs.queryInventory, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Shop_Detailed_List_Bean shop_detailed_list_bean = new Gson().fromJson(json, Shop_Detailed_List_Bean.class);
                        if(Integer.valueOf(pageNum) <= shop_detailed_list_bean.getData().getList().getTotalPages()){
                            List<Shop_Detailed_List_Bean.DataBean.ListBean.ContentBean> zilist = shop_detailed_list_bean.getData().getList().getContent();
                            list.addAll(zilist);
                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }
                        // 初始化数据 与适配器
                        setShop_Detailed_Adapter();
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                shop_Detailed_PullToRefreshScrollView.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
                shop_Detailed_PullToRefreshScrollView.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 首次进入页面获取列表
    private void getFirstConect(final HashMap<String, String> map) {
        myPresenter.postPreContent(APIs.queryInventory, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Shop_Detailed_List_Bean shop_detailed_list_bean = new Gson().fromJson(json, Shop_Detailed_List_Bean.class);

                        List<Shop_Detailed_List_Bean.DataBean.ListBean.ContentBean> zilist = shop_detailed_list_bean.getData().getList().getContent();
                        list.clear();
                        list.addAll(zilist);

                        // 初始化数据 与适配器
                        setShop_Detailed_Adapter();
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                shop_Detailed_PullToRefreshScrollView.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
                shop_Detailed_PullToRefreshScrollView.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 设置适配器
    private void setShop_Detailed_Adapter() {
        if(list.size()>0){
            if(shop_detailed_adapter==null){
                shop_detailed_adapter = new Shop_Detailed_Adapter(Shop_Detailed_Activity.this, list);
                shop_Detailed_MyListView.setAdapter(shop_detailed_adapter);
            }else{
                shop_detailed_adapter.notifyDataSetChanged();
            }
        }
    }

    private void initView() {
        shop_Detailed_Fan = (RelativeLayout) findViewById(R.id.shop_Detailed_Fan);
        shop_Detailed_MyListView = (MyListView) findViewById(R.id.shop_Detailed_MyListView);
        shop_Detailed_PullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.shop_Detailed_PullToRefreshScrollView);
        xinxi = (TextView) findViewById(R.id.xinxi);

        /**
         * 解决scrollview 显示不在顶部问题
         */
        xinxi.setFocusable(true);
        xinxi.setFocusableInTouchMode(true);
        xinxi.requestFocus();

        initRefreshListView(shop_Detailed_PullToRefreshScrollView);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
