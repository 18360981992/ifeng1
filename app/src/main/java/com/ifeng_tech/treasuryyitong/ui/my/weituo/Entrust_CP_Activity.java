package com.ifeng_tech.treasuryyitong.ui.my.weituo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Entrust_CP_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.weituo.Entrust_ClientGoodsByClientCode_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entrust_CP_Activity extends BaseMVPActivity<Entrust_CP_Activity, MyPresenter<Entrust_CP_Activity>>  {

    private RelativeLayout entrust_CP_Fan;
    private TextView entrust_CP_goodsCode;
    private MyListView entrust_CP_MyListView;
    private PullToRefreshScrollView entrust_CP_pulltoscroll;
    private Button entrust_CP_btn;
    private RelativeLayout entrust_CP_RelativeLayout;
    private LinearLayout entrust_CP_null;
    private String orgCode="";
    public String orgName="";
    List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> list=new ArrayList<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean>();
    private Entrust_CP_Adapter entrust_cp_adapter;
    private ProgressDialog aniDialog;
    Map<String, String> map = new HashMap<>();
    String goodsCode="";
    String goodsName="";

    @Override
    public MyPresenter<Entrust_CP_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    int pageNum=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrust__cp_);
        initView();
        orgCode = getIntent().getStringExtra("orgCode");
        orgName = getIntent().getStringExtra("orgName");

        if(orgCode.equals("")){
            MyUtils.setToast("请先选择交货平台!");
        }else {
            map.put("clientCode", orgCode);
            map.put("pageNum", pageNum + "");
            map.put("pageSize", "10");
            aniDialog = MyUtils.getProgressDialog(Entrust_CP_Activity.this, SP_String.JIAZAI);

            getFristConect(map, aniDialog,goodsCode);
        }


        // 返回
        entrust_CP_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 点击下一步
        entrust_CP_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                if(goodsName.equals("")||goodsCode.equals("")){
                    MyUtils.setToast("请选择提货藏品");
                }else{
                    Intent intent = new Intent(Entrust_CP_Activity.this, Entrust_SQ_Activity.class);
                    intent.putExtra("orgCode",orgCode);
                    intent.putExtra("goodsCode",goodsCode);
                    intent.putExtra("orgName",orgName);
                    intent.putExtra("goodsName",goodsName);
                    startActivityForResult(intent, DashApplication.CP_TO_SQ_req);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();

        entrust_CP_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                MyUtils.setToast("下拉了。。。");
                pageNum=1;
                map.clear();
                map.put("clientCode", orgCode);
                map.put("pageNum", pageNum + "");
                map.put("pageSize", "10");
                getFristConect(map,aniDialog,goodsCode);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageNum++;
                map.clear();
                map.put("clientCode", orgCode);
                map.put("pageNum", pageNum + "");
                map.put("pageSize", "10");
                getNextConect(map,aniDialog);
            }
        });

        entrust_CP_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).isCpFlag()){
                    for (int i=0;i<list.size();i++){
                        list.get(i).setCpFlag(false);
                        list.set(i,list.get(i));
                    }
                    goodsCode="";
                    goodsName="";
                    entrust_CP_goodsCode.setText("");
                }else{
                    for (int i=0;i<list.size();i++){
                        list.get(i).setCpFlag(false);
                        list.set(i,list.get(i));
                    }
                    list.get(position).setCpFlag(true);
                    list.set(position,list.get(position));
                    goodsCode=list.get(position).getCommodityCode();
                    goodsName=list.get(position).getCommodityName();
                    entrust_CP_goodsCode.setText(list.get(position).getCommodityCode()+"");
                }
                setAdapter();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DashApplication.CP_TO_SQ_req&&resultCode==DashApplication.CP_TO_SQ_res){
            setResult(DashApplication.PT_TO_CP_res);
            finish();
        }
    }

    private void getFristConect(Map<String, String> map, final ProgressDialog aniDialog, final String goods) {
        myPresenter.postPreContent(APIs.getClientGoodsByClientCode, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if (code.equals("2000")) {
                        Entrust_ClientGoodsByClientCode_Bean entrust_clientGoodsByClientCode_bean = new Gson().fromJson(json, Entrust_ClientGoodsByClientCode_Bean.class);
                        List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> zilist = entrust_clientGoodsByClientCode_bean.getData().getList();
                        if(zilist.size()>0){
                            Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean listBean = zilist.get(0);
                            listBean.setCpFlag(true);
                            zilist.set(0,listBean);
                            if(goods.equals("")){
                                goodsCode = listBean.getCommodityCode();
                                goodsName=listBean.getCommodityName();
                            }
                            entrust_CP_goodsCode.setText(listBean.getCommodityCode()+"");
                            list.clear();
                            list.addAll(zilist);
                        }
                        setAdapter();
                    } else {
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                    entrust_CP_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
                entrust_CP_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    private void getNextConect(final Map<String, String> map, final ProgressDialog aniDialog) {

        myPresenter.postPreContent(APIs.getClientGoodsByClientCode, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if (code.equals("2000")) {
                        Entrust_ClientGoodsByClientCode_Bean entrust_clientGoodsByClientCode_bean = new Gson().fromJson(json, Entrust_ClientGoodsByClientCode_Bean.class);
                        String pageNum = map.get("pageNum");
                        if(Integer.valueOf(pageNum) <= entrust_clientGoodsByClientCode_bean.getData().getPageInfo().getPageCount()){
                            List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> zilist = entrust_clientGoodsByClientCode_bean.getData().getList();
                            if(zilist.size()>0){
                                list.addAll(zilist);
                                setAdapter();
                            }
                        }else{
                            MyUtils.setToast("没有更多数据了");
                        }

                    } else {
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                    entrust_CP_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
                entrust_CP_pulltoscroll.onRefreshComplete();//完成刷新,关闭刷新
            }
        });
    }

    public void setAdapter(){
        if(list.size()>0){
            entrust_CP_RelativeLayout.setVisibility(View.VISIBLE);
            entrust_CP_null.setVisibility(View.GONE);
            if(entrust_cp_adapter==null) {
                entrust_cp_adapter = new Entrust_CP_Adapter(Entrust_CP_Activity.this, list);
                entrust_CP_MyListView.setAdapter(entrust_cp_adapter);
            }else{
                entrust_cp_adapter.notifyDataSetChanged();
            }
        }else{
            entrust_CP_RelativeLayout.setVisibility(View.GONE);
            entrust_CP_null.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        entrust_CP_Fan = (RelativeLayout) findViewById(R.id.entrust_CP_Fan);
        entrust_CP_goodsCode = (TextView) findViewById(R.id.entrust_CP_goodsCode);
        entrust_CP_MyListView = (MyListView) findViewById(R.id.entrust_CP_MyListView);
        entrust_CP_pulltoscroll = (PullToRefreshScrollView) findViewById(R.id.entrust_CP_pulltoscroll);
        entrust_CP_btn = (Button) findViewById(R.id.entrust_CP_btn);
        entrust_CP_RelativeLayout = (RelativeLayout) findViewById(R.id.entrust_CP_RelativeLayout);
        entrust_CP_null = (LinearLayout) findViewById(R.id.entrust_CP_null);

        initRefreshListView(entrust_CP_pulltoscroll);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }


}
