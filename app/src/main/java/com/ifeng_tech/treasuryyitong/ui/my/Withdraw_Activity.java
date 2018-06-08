package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.my.PersonalUserAccount_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 提现
 */
public class Withdraw_Activity extends BaseMVPActivity<Withdraw_Activity,MyPresenter<Withdraw_Activity>> implements View.OnClickListener {

    private RelativeLayout withdraw_Fan;
    private EditText withdraw_qian;
    private TextView withdraw_zuida_qian;
    private ImageView zhifubao_fuxuan;
    private ImageView weixin_fuxuan;
    private Button withdraw_xiayibu;

    int paymentPlatform=1;  // 支付平台的选择
    private double withdrawMax;   // 最大可转金额

    @Override
    public MyPresenter<Withdraw_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_);
        initView();

        withdraw_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 获取最大可转金额
        myPresenter.getPreContent(APIs.findPersonalUserAccount, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        PersonalUserAccount_Bean userBean = new Gson().fromJson(json, PersonalUserAccount_Bean.class);
                        withdrawMax = userBean.getData().getAccountInfo().getWithdrawMax();
                        withdraw_zuida_qian.setText(DashApplication.decimalFormat.format(withdrawMax));

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

        // 支付平台 1支付宝 2微信
        zhifubao_fuxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentPlatform=1;
                zhifubao_fuxuan.setImageResource(R.drawable.zhuce_lan);
                weixin_fuxuan.setImageResource(R.drawable.zhuce_hui);

            }
        });

        weixin_fuxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentPlatform=2;
                zhifubao_fuxuan.setImageResource(R.drawable.zhuce_hui);
                weixin_fuxuan.setImageResource(R.drawable.zhuce_lan);
            }
        });


    }

    private void initView() {
        withdraw_Fan = (RelativeLayout) findViewById(R.id.withdraw_Fan);
        withdraw_qian = (EditText) findViewById(R.id.withdraw_qian);
        withdraw_zuida_qian = (TextView) findViewById(R.id.withdraw_zuida_qian);
        zhifubao_fuxuan = (ImageView) findViewById(R.id.zhifubao_fuxuan);
        weixin_fuxuan = (ImageView) findViewById(R.id.weixin_fuxuan);
        withdraw_xiayibu = (Button) findViewById(R.id.withdraw_xiayibu);

        withdraw_xiayibu.setOnClickListener(this);

        withdraw_qian.setSelection(withdraw_qian.getText().length());

        zhifubao_fuxuan.setImageResource(R.drawable.zhuce_lan);


        SoftHideKeyBoardUtil.assistActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.withdraw_xiayibu:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String qian = withdraw_qian.getText().toString().trim();
        if (TextUtils.isEmpty(qian)) {
            Toast.makeText(this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Integer.valueOf(qian)>withdrawMax){
            MyUtils.setToast("您已超出最大提现额度");
            return;
        }
        if(Integer.valueOf(qian) <= 0){
            MyUtils.setToast("提现金额不能低于0");
            return;
        }


        // TODO validate success, do something
        Intent intent = new Intent(Withdraw_Activity.this, Withdraw_Activity2.class);
        intent.putExtra("paymentPlatform",""+paymentPlatform);
        intent.putExtra("withdrawalAmount",qian);
        startActivityForResult(intent, DashApplication.WITHDRAW_TO_WITHDRAW2_req);
        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DashApplication.WITHDRAW_TO_WITHDRAW2_req&&resultCode==DashApplication.WITHDRAW_TO_WITHDRAW2_res){
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
