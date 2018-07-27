package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.CollectAdapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.bean.Collect_Bean;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.login.LoginActivity;
import com.ifeng_tech.treasuryyitong.ui.my.Collect_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.MyListView;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

/**
 * Created by zzt on 2018/4/27.
 * <p>
 *  一级页面 征集
 */

public class CollectFragmet extends Fragment {

    private HomePageActivity activity;
    private MyListView collect_MyListView;
    private PullToRefreshScrollView collect_pulltoscroll;
    private XBanner collect_XBanner;

    List<Integer> imgs = new ArrayList<>();
    List<Collect_Bean.DataBean.ListBean> list = new ArrayList<>();

    private LinearLayout collect_null;
    private CollectAdapter collectAdapter;
    private boolean aBoolean;
    private SharedPreferences.Editor edit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collect_fragmet, container, false);
        initView(view);
        activity = (HomePageActivity) getActivity();
        /**
         * 解决scrollview 显示不在顶部问题
         */
        collect_XBanner.setFocusable(true);
        collect_XBanner.setFocusableInTouchMode(true);
        collect_XBanner.requestFocus();

        return view;
    }

    HashMap<String, String> map = new HashMap<>();
    int pageNum=1;

    @Override
    public void onResume() {
        super.onResume();
        sp = activity.getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();
        aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);

        collect_XBanner.setData(imgs,null);//设置数据源
        collect_XBanner.setmAdapter(new XBanner.XBannerAdapter() {//xbanner的适配器，加载图片
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ((ImageView) view).setImageResource(imgs.get(position));
//                    Glide.with(activity).load(imgs.get(position)).into((ImageView) view);
            }
        });

        pageNum=1;
        map.put("pageNum",pageNum+"");
        map.put("pageSize",""+10);
        getFirstConect(map);

        collect_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageNum=1;
                map.put("pageNum",pageNum+"");
                map.put("pageSize",""+10);
                getFirstConect(map);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageNum++;
                map.put("pageNum",pageNum+"");
                map.put("pageSize",""+10);
                getNextConect(map);
            }
        });

        collect_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                if(aBoolean){
                    if(list.get(position).getStage()==4){ // 为4的时候可以点击进入征集页面
                        // 获取最大征集数量  和仓库进行比较
                        final int JIN_DU = list.get(position).getAllAmount() - list.get(position).getResidueAmount();

                        HashMap<String, String> map = new HashMap<>();
                        map.put("goodsId",list.get(position).getGoodsId()+"");
                        activity.myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
                            @Override
                            public void chenggong(String json) {
                                WarehouseBean warehouseBean = new Gson().fromJson(json, WarehouseBean.class);
                                if(warehouseBean.getCode().equals("2000")){
                                    if(warehouseBean.getData().getList().size()>0){
                                        WarehouseBean.DataBean.ListBean listBean = warehouseBean.getData().getList().get(0);
                                        if(listBean.getAvailableQty()>=JIN_DU){
                                            Intent intent = new Intent(activity, Collect_Activity.class);
                                            intent.putExtra("CollectBean",list.get(position));
                                            intent.putExtra("MAX_NUM",JIN_DU);
                                            activity.startActivity(intent);
                                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                        }else{
                                            Intent intent = new Intent(activity, Collect_Activity.class);
                                            intent.putExtra("CollectBean",list.get(position));
                                            intent.putExtra("MAX_NUM",listBean.getAvailableQty());
                                            activity.startActivity(intent);
                                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                        }
                                    }else{
                                        MyUtils.setToast("您的仓库中暂无该商品！");
                                    }
                                }else{
                                    MyUtils.setToast(warehouseBean.getMessage());
                                }

                            }

                            @Override
                            public void shibai(String ss) {
                                MyUtils.setToast(ss);
                            }
                        });

                    }else{
                        MyUtils.setToast("该商品还未开始征集。。。");
                    }
                }else{
                    Intent intent1 = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent1);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });

    }


    // 首次进入页面获取列表
    private void getFirstConect(final HashMap<String, String> map) {
        activity.myPresenter.postPreContent(APIs.getCollectList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Collect_Bean Collect_Bean = new Gson().fromJson(json, Collect_Bean.class);
                        List<Collect_Bean.DataBean.ListBean> zilist = Collect_Bean.getData().getList();
                        list.clear();
                        if(zilist.size()>0){
                            for (Collect_Bean.DataBean.ListBean bean: zilist) {
                                if(bean.getStage()==4||bean.getStage()==3){
                                    list.add(bean);
                                }
                            }
                        }
                        // 初始化数据 与适配器
                        setCollectAdapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                collect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                collect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    // 下拉加载更多
    private void getNextConect(final HashMap<String, String> map) {
        activity.myPresenter.postPreContent(APIs.getCollectList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","==="+json);
                        Collect_Bean Collect_Bean = new Gson().fromJson(json, Collect_Bean.class);
                        List<Collect_Bean.DataBean.ListBean> zilist = Collect_Bean.getData().getList();
                        if(zilist.size()>0){
                            for (Collect_Bean.DataBean.ListBean bean: zilist) {
                                if(bean.getStage()==4||bean.getStage()==3){
                                    list.add(bean);
                                }
                            }
                        }
                        // 初始化数据 与适配器
                        setCollectAdapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                collect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                collect_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }


    /**
     *  初始化适配器
     */
    private void setCollectAdapter() {
        if(list.size()>0){
            collect_null.setVisibility(View.GONE);
            collect_MyListView.setVisibility(View.VISIBLE);
            if (collectAdapter == null) {
                collectAdapter = new CollectAdapter(activity, list);
                collect_MyListView.setAdapter(collectAdapter);
            } else {
                collectAdapter.notifyDataSetChanged();
            }
        }else{
            collect_null.setVisibility(View.VISIBLE);
            collect_MyListView.setVisibility(View.GONE);
        }
    }



    private void initView(View view) {
        collect_MyListView = (MyListView) view.findViewById(R.id.collect_MyListView);
        collect_XBanner = view.findViewById(R.id.collect_XBanner);
        collect_pulltoscroll = (PullToRefreshScrollView) view.findViewById(R.id.collect_pulltoscroll);
        collect_null = view.findViewById(R.id.collect_null);

        // 设置刷新
        activity.initRefreshListView(collect_pulltoscroll);

        imgs.add(R.mipmap.band1);
        imgs.add(R.mipmap.band2);

    }

}
