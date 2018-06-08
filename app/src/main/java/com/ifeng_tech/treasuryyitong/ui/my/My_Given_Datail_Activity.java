package com.ifeng_tech.treasuryyitong.ui.my;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Give_List_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.EGiven_ListStage;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *
 *  转入列表的详情页面
 *
 */
public class My_Given_Datail_Activity extends BaseMVPActivity<My_Given_Datail_Activity, MyPresenter<My_Given_Datail_Activity>> implements View.OnClickListener {

    private RelativeLayout my_Given_Datail_Fan;
    private TextView my_Given_Datail_name;
    private TextView my_Given_Datail_cword;
    private TextView my_Given_Datail_xiangqing;
    private TextView my_Given_Datail_dword;
    private TextView my_Given_Datail_cangku;
    private TextView my_Given_Datail_shouxufei;
    private TextView my_Given_Datail_leibie;
    private TextView my_Given_Datail_zhangtai;
    private Button my_Given_Datail_tongyi;
    private Button my_Given_Datail_jujue;
    private LinearLayout my_given_datail_anniu;
    private Give_List_Bean.DataBean.ListBean give_list_bean;


    String orderNo="";  // 同意/拒绝  转赠参数
    private LinearLayout my_given_datail_weitanchuan;
    private ImageView my_given_datail_weitanchuan_img;
    private TextView my_given_datail_weitanchuan_text;
    private int weitanchuan_height;

    @Override
    public MyPresenter<My_Given_Datail_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__given__datail_);
        initView();
        my_Given_Datail_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        give_list_bean = (Give_List_Bean.DataBean.ListBean) intent.getSerializableExtra("Give_List_Bean");
        int leibie = intent.getIntExtra("leibie", 0);

        my_Given_Datail_name.setText(give_list_bean.getGoodsName());
        my_Given_Datail_cword.setText(""+ give_list_bean.getGoodsCode());
        my_Given_Datail_dword.setText(""+ give_list_bean.getOrderNo());
        my_Given_Datail_cangku.setText("福利特仓库");
        double price= give_list_bean.getFee()* give_list_bean.getProfit();
        my_Given_Datail_shouxufei.setText("￥"+ DashApplication.decimalFormat.format(price));
        if(leibie==0 ){

            /*
            状态:
            0: 操作中
            1: 完成
            2: 失败
            3: 取消
            4: 拒绝
            5: 超时
             */

            if(give_list_bean.getStatus()==0){
                my_given_datail_anniu.setVisibility(View.VISIBLE);
            }else{
                my_given_datail_anniu.setVisibility(View.INVISIBLE);
            }
            my_Given_Datail_leibie.setText("转入");
            orderNo=give_list_bean.getOrderNo();  // 为转赠单号赋值
        }else{
            my_Given_Datail_leibie.setText("转出");
            my_given_datail_anniu.setVisibility(View.INVISIBLE);

        }
        my_Given_Datail_zhangtai.setText(EGiven_ListStage.getName(give_list_bean.getStatus()));

    }

    private void initView() {
        my_Given_Datail_Fan = (RelativeLayout) findViewById(R.id.my_Given_Datail_Fan);
        my_Given_Datail_name = (TextView) findViewById(R.id.my_Given_Datail_name);
        my_Given_Datail_cword = (TextView) findViewById(R.id.my_Given_Datail_cword);
        my_Given_Datail_xiangqing = (TextView) findViewById(R.id.my_Given_Datail_xiangqing);
        my_Given_Datail_dword = (TextView) findViewById(R.id.my_Given_Datail_dword);
        my_Given_Datail_cangku = (TextView) findViewById(R.id.my_Given_Datail_cangku);
        my_Given_Datail_shouxufei = (TextView) findViewById(R.id.my_Given_Datail_shouxufei);
        my_Given_Datail_leibie = (TextView) findViewById(R.id.my_Given_Datail_leibie);
        my_Given_Datail_zhangtai = (TextView) findViewById(R.id.my_Given_Datail_zhangtai);
        my_Given_Datail_tongyi = (Button) findViewById(R.id.my_Given_Datail_tongyi);
        my_Given_Datail_jujue = (Button) findViewById(R.id.my_Given_Datail_jujue);
        my_given_datail_anniu = (LinearLayout)findViewById(R.id.my_Given_Datail_anniu);
        my_given_datail_weitanchuan = (LinearLayout) findViewById(R.id.my_Given_Datail_weitanchuan);
        my_given_datail_weitanchuan_img = (ImageView)findViewById(R.id.my_Given_Datail_weitanchuan_img);
        my_given_datail_weitanchuan_text = (TextView) findViewById(R.id.my_Given_Datail_weitanchuan_text);

        my_Given_Datail_tongyi.setOnClickListener(this);
        my_Given_Datail_jujue.setOnClickListener(this);

        //通过设置监听来获取 微弹窗 控件的高度
        my_given_datail_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                my_given_datail_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = my_given_datail_weitanchuan.getMeasuredHeight();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_Given_Datail_tongyi:
//                MyUtils.setToast("点击同意。。。");

                HashMap<String, String> map = new HashMap<>();
                map.put("orderNo",orderNo);

                final ProgressDialog aniDialog = new ProgressDialog(My_Given_Datail_Activity.this);
                aniDialog.setCancelable(true);
                aniDialog.setMessage("正在加载。。。");
                aniDialog.show();

                setButton(map, aniDialog,"同意转赠成功");


                break;
            case R.id.my_Given_Datail_jujue:
//                MyUtils.setToast("点击拒绝。。。");

                HashMap<String, String> map1 = new HashMap<>();
                map1.put("orderNo",orderNo);

                final ProgressDialog aniDialog1 = new ProgressDialog(My_Given_Datail_Activity.this);
                aniDialog1.setCancelable(true);
                aniDialog1.setMessage("正在加载。。。");
                aniDialog1.show();

                setButton(map1, aniDialog1,"拒绝转赠成功");

                break;
        }
    }

    private void setButton(HashMap<String, String> map, final ProgressDialog aniDialog, final String text) {
        myPresenter.postPreContent(APIs.getUserZhuanzeng_accept, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        MyUtils.setObjectAnimator_anquan(my_given_datail_weitanchuan,
                                my_given_datail_weitanchuan_img,
                                my_given_datail_weitanchuan_text,
                                weitanchuan_height,
                                true,text);
                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                my_Given_Datail_tongyi.setEnabled(false);
                                my_Given_Datail_jujue.setEnabled(false);
                                finish();
                            }
                        });
                    }else{
                        MyUtils.setObjectAnimator(my_given_datail_weitanchuan,
                                my_given_datail_weitanchuan_img,
                                my_given_datail_weitanchuan_text,
                                weitanchuan_height,
                                false, (String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setObjectAnimator(my_given_datail_weitanchuan,
                        my_given_datail_weitanchuan_img,
                        my_given_datail_weitanchuan_text,
                        weitanchuan_height,
                        false, ss);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
