package com.ifeng_tech.treasuryyitong.ui.my.weituo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Entrust_Client_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.weituo.Entrust_Client_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entrust_PT_Activity extends BaseMVPActivity<Entrust_PT_Activity, MyPresenter<Entrust_PT_Activity>> {

    private RelativeLayout entrust_PT_Fan;
    private ListView entrust_PT_ListView;
    private Button entrust_PT_btn;
    private TextView textv;
    private RelativeLayout entrust_PT_RelativeLayout;
    private LinearLayout entrust_PT_null;
    private Entrust_Client_Adapter entrust_client_adapter;
    List<Entrust_Client_Bean.DataBean.ClientBean> list=new ArrayList<Entrust_Client_Bean.DataBean.ClientBean>();
    private String orgCode="";
    private String orgName="";

    @Override
    public MyPresenter<Entrust_PT_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrust__pt_);
        initView();

        // 返回
        entrust_PT_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 下一步按钮
        entrust_PT_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                if(orgCode.equals("")||orgName.equals("")){
                    MyUtils.setToast("请选择提货平台");
                }else{
                    Intent intent = new Intent(Entrust_PT_Activity.this, Entrust_CP_Activity.class);
                    intent.putExtra("orgCode",orgCode );
                    intent.putExtra("orgName",orgName );
                    startActivityForResult(intent, DashApplication.PT_TO_CP_req);
                    overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }

            }
        });


        Map<String, String> map = new HashMap<>();
        map.put("", "");
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(Entrust_PT_Activity.this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.getClient, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if (code.equals("2000")) {
                        Entrust_Client_Bean entrust_client_bean = new Gson().fromJson(json, Entrust_Client_Bean.class);
                        List<Entrust_Client_Bean.DataBean.ClientBean> zilist = entrust_client_bean.getData().getClient();
                        if(zilist.size()>0){
                            Entrust_Client_Bean.DataBean.ClientBean clientBean = zilist.get(0);
                            clientBean.setPtFlag(true);
                            zilist.set(0,clientBean);
                            list.clear();
                            list.addAll(zilist);
                            orgCode = list.get(0).getClientCode();
                            orgName= list.get(0).getClientName();
                        }
                        setAdapter();
                    } else {
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 列表点击
        entrust_PT_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).isPtFlag()){
                    for (int i=0;i<list.size();i++) {
                        Entrust_Client_Bean.DataBean.ClientBean clientBean = list.get(i);
                        clientBean.setPtFlag(false);
                        list.set(i,clientBean);
                    }
                    orgCode = "";
                    orgName= "";
                }else{
                    for (int i=0;i<list.size();i++) {
                        Entrust_Client_Bean.DataBean.ClientBean clientBean = list.get(i);
                        clientBean.setPtFlag(false);
                        list.set(i,clientBean);
                    }
                    list.get(position).setPtFlag(true);
                    list.set(position, list.get(position));
                    orgCode = list.get(position).getClientCode();
                    orgName= list.get(0).getClientName();
                }

                setAdapter();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DashApplication.PT_TO_CP_req&&resultCode==DashApplication.PT_TO_CP_res){
            finish();
        }
    }

    public void setAdapter(){
        if(list.size()>0){
            entrust_PT_RelativeLayout.setVisibility(View.VISIBLE);
            entrust_PT_null.setVisibility(View.GONE);
            if(entrust_client_adapter==null) {
                entrust_client_adapter = new Entrust_Client_Adapter(Entrust_PT_Activity.this, list);
                entrust_PT_ListView.setAdapter(entrust_client_adapter);
            }else{
                entrust_client_adapter.notifyDataSetChanged();
            }
        }else{
            entrust_PT_RelativeLayout.setVisibility(View.GONE);
            entrust_PT_null.setVisibility(View.VISIBLE);
        }
    }


    private void initView() {
        entrust_PT_Fan = (RelativeLayout) findViewById(R.id.entrust_PT_Fan);
        entrust_PT_ListView = (ListView) findViewById(R.id.entrust_PT_ListView);
        entrust_PT_btn = (Button) findViewById(R.id.entrust_PT_btn);
        entrust_PT_RelativeLayout = (RelativeLayout) findViewById(R.id.entrust_PT_RelativeLayout);
        entrust_PT_null = (LinearLayout) findViewById(R.id.entrust_PT_null);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }

}
