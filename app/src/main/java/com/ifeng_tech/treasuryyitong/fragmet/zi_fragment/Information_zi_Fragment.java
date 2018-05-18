package com.ifeng_tech.treasuryyitong.fragmet.zi_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Information_zi_Adapter;
import com.ifeng_tech.treasuryyitong.bean.Information_Zi_Bean;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.Information_Details_Activity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 2018/5/7.
 *
 *  资讯的 子fragment
 */

public class Information_zi_Fragment extends Fragment {
    private XRecyclerView information_zi_fragment_xRecyclerView;
    private HomePageActivity activity;

    List<Information_Zi_Bean> arrs = new ArrayList<>();
    List<Information_Zi_Bean> arr1 = new ArrayList<>();
    List<Information_Zi_Bean> arr2 = new ArrayList<>();
    List<Information_Zi_Bean> arr3 = new ArrayList<>();
    List<Information_Zi_Bean> arr4 = new ArrayList<>();
    private Information_zi_Adapter information_zi_adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_zi_fragment, container, false);
        initView(view);

        activity = (HomePageActivity) getActivity();

        information_zi_fragment_xRecyclerView.setLayoutManager(new LinearLayoutManager(activity, OrientationHelper.VERTICAL,false));


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //获取传递的top值，，，接口有问题，只能获取type=1
        String top = getArguments().getString("top");

        final List<Information_Zi_Bean> topList = getTopList(top);

        setAdapter(topList);

        information_zi_adapter.setInformation_zi_Adapter_JieKou(new Information_zi_Adapter.Information_zi_Adapter_JieKou() {
            @Override
            public void chuan(int postion) {
//                MyUtils.setToast("点击了=="+topList.get(postion).getTop()+"的第"+postion+"条");

                Intent intent = new Intent(activity, Information_Details_Activity.class);
//                intent.putExtra("InformationBean",topList.get(postion));
                activity.startActivity(intent);

            }
        });


        information_zi_fragment_xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                information_zi_fragment_xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                information_zi_fragment_xRecyclerView.loadMoreComplete();
            }
        });

    }

    private void setAdapter(List<Information_Zi_Bean> topList) {
        if(information_zi_adapter==null){
            information_zi_adapter = new Information_zi_Adapter(activity, topList);
            information_zi_fragment_xRecyclerView.setAdapter(information_zi_adapter);
        }else{
            information_zi_adapter.notifyDataSetChanged();
        }

    }


    private List<Information_Zi_Bean> getTopList(String top){

        if(top.equals("全部")){
            return arrs;
        }else if(top.equals("热门")){
            return arr1;
        }else if(top.equals("关注")){
            return arr2;
        }else if(top.equals("栏目1")){
            return arr3;
        }else if(top.equals("栏目2")){
            return arr4;
        }else{
            return arrs;
        }
    }



    String[] img={"http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png"};
    String[] imgs={ "http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png",
            "http://img.wdjimg.com/mms/icon/v1/d/f1/1c8ebc9ca51390cf67d1c3c3d3298f1d_512_512.png"};

    // 模拟数据
    private void initData(){
        // 热门
       for (int i=0;i<4;i++){
           arr1.add(new Information_Zi_Bean(img,"热门","这是内容...这是内容...这是内容...这是内容...","2018-05-02"));
           if(i==3){
               arr1.add(new Information_Zi_Bean(imgs,"热门","这是内容..这是内容...这是内容...这是内容...这是内容....","2018-05-02"));
           }
       }

        // 关注
        for (int i=0;i<4;i++){
            arr2.add(new Information_Zi_Bean(img,"关注","这是内容.这是内容...这是内容...这是内容.....","2018-05-02"));
            if(i==3){
                arr2.add(new Information_Zi_Bean(imgs,"关注","这是内容..这是内容...这是内容...这是内容....","2018-05-02"));
            }
        }
        // 栏目1
        for (int i=0;i<4;i++){
            arr3.add(new Information_Zi_Bean(img,"栏目1","这是内容..这是内容...这是内容...这是内容....","2018-05-02"));
            if(i==3){
                arr3.add(new Information_Zi_Bean(imgs,"栏目1","这是内容..这是内容...这是内容...这是内容....","2018-05-02"));
            }
        }
        // 栏目2
        for (int i=0;i<4;i++){
            arr4.add(new Information_Zi_Bean(img,"栏目2","这是内容...这是内容...这是内容...这是内容...这是内容.....","2018-05-02"));
            if(i==3){
                arr4.add(new Information_Zi_Bean(imgs,"栏目2","这是内容.这是内容...这是内容...这是内容...这是内容......","2018-05-02"));
            }
        }

        arrs.addAll(arr1);
        arrs.addAll(arr2);
        arrs.addAll(arr3);
        arrs.addAll(arr4);
    }

    private void initView(View view) {
        information_zi_fragment_xRecyclerView = (XRecyclerView) view.findViewById(R.id.information_zi_fragment_xRecyclerView);

        information_zi_fragment_xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        information_zi_fragment_xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SemiCircleSpin);

        initData();
    }
}
