package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.PropertyListAdapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.my.My_Property_list_Bean;
import com.ifeng_tech.treasuryyitong.bean.my.PersonalUserAccount_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.my.yewu_pass.Business_Pass_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

// 我的资产

public class My_Property_Activity extends BaseMVPActivity<My_Property_Activity,MyPresenter<My_Property_Activity>> implements View.OnClickListener {

    private RelativeLayout property_Fan;
    private ImageView property_zhangdan;
    private TextView property_money;
    private TextView property_dongjie;
    private TextView property_keyong;
    private TextView qiankuan;
    private ListView property_ListView;
    private Button property_tixian;
    private Button property_congzhi;

    private LinearLayout my_property_null;

    @Override
    public MyPresenter<My_Property_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__property_);
        initView();

        // 点击返回
        property_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        property_zhangdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了账单。。。");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getUser();// 获取个人信息

        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum",1+"");
        map.put("pageSize",""+5);

        getMyProperty_list(map);  // 获取资金流水
    }

    // 获取最新的资金流水
    private void getMyProperty_list(final HashMap<String, String> map) {
        myPresenter.postPreContent(APIs.findUserPaymentRecords, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        My_Property_list_Bean<BaseMVPActivity<Business_Pass_Activity, MyPresenter<Business_Pass_Activity>>> my_property_list_bean = new Gson().fromJson(json, My_Property_list_Bean.class);
                        if(my_property_list_bean.getData().getList().size()>0){
                            my_property_null.setVisibility(View.GONE);
                            property_ListView.setVisibility(View.VISIBLE);
                            List<My_Property_list_Bean.DataBean.ListBean> list = my_property_list_bean.getData().getList();
                            property_ListView.setAdapter(new PropertyListAdapter(My_Property_Activity.this,list));
                        }else{
                            my_property_null.setVisibility(View.VISIBLE);
                            property_ListView.setVisibility(View.GONE);
                        }
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

    // 获取个人信息
    private void getUser() {
        myPresenter.getPreContent(APIs.findPersonalUserAccount, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        PersonalUserAccount_Bean userBean = new Gson().fromJson(json, PersonalUserAccount_Bean.class);
                        property_money.setText(""+ DashApplication.decimalFormat.format(userBean.getData().getAccountInfo().getBalance()));
                        property_dongjie.setText(""+DashApplication.decimalFormat.format(userBean.getData().getAccountInfo().getFrozenFunds()));
                        property_keyong.setText(""+DashApplication.decimalFormat.format(userBean.getData().getAccountInfo().getDesirableFunds()));
                        qiankuan.setText(""+DashApplication.decimalFormat.format(userBean.getData().getAccountInfo().getArrearage()));
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

    private void initView() {
        property_Fan = (RelativeLayout) findViewById(R.id.property_Fan);
        property_zhangdan = (ImageView) findViewById(R.id.property_zhangdan);
        property_money = (TextView) findViewById(R.id.property_money);
        property_dongjie = (TextView) findViewById(R.id.property_dongjie);
        property_keyong = (TextView) findViewById(R.id.property_keyong);
        qiankuan = (TextView) findViewById(R.id.qiankuan);
        property_ListView = (ListView) findViewById(R.id.property_ListView);
        property_tixian = (Button) findViewById(R.id.property_tixian);
        property_congzhi = (Button) findViewById(R.id.property_congzhi);
        my_property_null = (LinearLayout) findViewById(R.id.my_property_null);

        property_tixian.setOnClickListener(this);
        property_congzhi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.property_tixian:
//                MyUtils.setToast("点击了提现。。。");
                Intent intent = new Intent(My_Property_Activity.this, Withdraw_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                break;
            case R.id.property_congzhi:
                MyUtils.setToast("点击了充值。。。");

                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
