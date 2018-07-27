package com.ifeng_tech.treasuryyitong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_list_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统消息
 */
public class System_Message_Activity extends BaseMVPActivity<System_Message_Activity,MyPresenter<System_Message_Activity>> {

    private RelativeLayout system_Fan;
    private MyListView system_MyListView;
    private PullToRefreshScrollView system_pulltoscroll;
    private LinearLayout system_null;

    List<Message_Lists_Bean.DataBean.ListBean> list = new ArrayList<>();
    private Message_List_Adapter message_list_adapter;

    @Override
    public MyPresenter<System_Message_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system__message_);
        initView();

        system_Fan.setOnClickListener(new View.OnClickListener() {
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
        map.put("messageType","0");
        getFristConect();


        system_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                MyUtils.setToast("下拉了。。。");
                start=0;
                map.put("start",start+"");
                map.put("number",10+"");
                map.put("messageType","0");
                getFristConect();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

                start++;
                map.put("start",(start*10)+"");
                map.put("number",10+"");
                map.put("messageType","0");
                getNextConect();
            }
        });

        system_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int msgType = list.get(position).getMsgType();
                if(msgType==1){
                    Intent intent = new Intent(System_Message_Activity.this, My_Given_list_Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
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

//                        DashApplication.edit_message_xitong.putInt(SP_String.NEWS_XITONG_NUM,message_lists_bean.getData().getPageInfo().getTotalNum()).commit();

                        setMessageAdapter();

                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }

                    system_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                system_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

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
                    system_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
                system_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }


    private void setMessageAdapter() {
        if(list.size()>0){
            system_null.setVisibility(View.GONE);
            system_pulltoscroll.setVisibility(View.VISIBLE);
            if(message_list_adapter==null){
                message_list_adapter = new Message_List_Adapter(System_Message_Activity.this, list,"系统消息");
                system_MyListView.setAdapter(message_list_adapter);
            }else{
                message_list_adapter.notifyDataSetChanged();
            }
        }else{
            system_null.setVisibility(View.VISIBLE);
            system_pulltoscroll.setVisibility(View.GONE);
        }

    }


    private void initView() {
        system_Fan = (RelativeLayout) findViewById(R.id.system_Fan);
        system_MyListView = (MyListView) findViewById(R.id.system_MyListView);
        system_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.system_pulltoscroll);
        system_null = (LinearLayout) findViewById(R.id.system_null);

        // 设置刷新
        initRefreshListView(system_pulltoscroll);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
