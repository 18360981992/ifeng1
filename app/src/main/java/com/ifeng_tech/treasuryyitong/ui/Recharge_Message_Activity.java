package com.ifeng_tech.treasuryyitong.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Message_List_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Message_Lists_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 充值消息
 */
public class Recharge_Message_Activity extends BaseMVPActivity<Recharge_Message_Activity, MyPresenter<Recharge_Message_Activity>> {

    private RelativeLayout recharge_Fan;
    private MyListView recharge_MyListView;
    private PullToRefreshScrollView recharge_pulltoscroll;
    private LinearLayout recharge_null;

    List<Message_Lists_Bean.DataBean.ListBean> list = new ArrayList<>();
    private Message_List_Adapter message_list_adapter;

    @Override
    public MyPresenter<Recharge_Message_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge__message_);
        initView();

        recharge_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    Map<String, String> map = new HashMap<>();
    int start=0;

    @Override
    protected void onResume() {
        super.onResume();

        start=0*10;
        map.put("start",start+"");
        map.put("number",10+"");
        map.put("messageType","1");
        getFristConect();

        recharge_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                start=0*10;
                map.put("start",start+"");
                map.put("number",10+"");
                map.put("messageType","1");
                getFristConect();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                start++;
                map.put("start",(start*10)+"");
                map.put("number",10+"");
                map.put("messageType","1");
                getNextConect();
            }
        });
    }

    private void getFristConect() {
        myPresenter.postPreContent(APIs.getMessageList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){

                        Message_Lists_Bean message_lists_bean = new Gson().fromJson(json, Message_Lists_Bean.class);
                        List<Message_Lists_Bean.DataBean.ListBean> zilist = message_lists_bean.getData().getList();
                        list.clear();
                        list.addAll(zilist);

//                        DashApplication.edit_message_chongzhi.putInt(SP_String.NEWS_CHONGZHI_NUM,message_lists_bean.getData().getPageInfo().getTotalNum()).commit();

                        setMessageAdapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                    recharge_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                recharge_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    boolean isFlag=true;
    private void getNextConect() {
        myPresenter.postPreContent(APIs.getMessageList, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        Message_Lists_Bean message_lists_bean = new Gson().fromJson(json, Message_Lists_Bean.class);
                        List<Message_Lists_Bean.DataBean.ListBean> zilist = message_lists_bean.getData().getList();
                        if(zilist.size()>0){
                            list.addAll(zilist);
                            setMessageAdapter();
                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                    recharge_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                recharge_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });

    }

    private void setMessageAdapter() {
        if(list.size()>0){
            recharge_null.setVisibility(View.GONE);
            recharge_pulltoscroll.setVisibility(View.VISIBLE);
            if(message_list_adapter==null){
                message_list_adapter = new Message_List_Adapter(Recharge_Message_Activity.this, list,"资金动态");
                recharge_MyListView.setAdapter(message_list_adapter);
            }else{
                message_list_adapter.notifyDataSetChanged();
            }
        }else{
            recharge_null.setVisibility(View.VISIBLE);
            recharge_pulltoscroll.setVisibility(View.GONE);
        }

    }

    private void initView() {
        recharge_Fan = (RelativeLayout) findViewById(R.id.recharge_Fan);
        recharge_MyListView = (MyListView) findViewById(R.id.recharge_MyListView);
        recharge_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.recharge_pulltoscroll);
        recharge_null = (LinearLayout) findViewById(R.id.recharge_null);

        // 设置刷新
        initRefreshListView(recharge_pulltoscroll);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
