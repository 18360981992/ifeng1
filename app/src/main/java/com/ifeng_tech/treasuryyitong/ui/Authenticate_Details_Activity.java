package com.ifeng_tech.treasuryyitong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Authenticate_Details_list_Adapter;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Authenticate_Details_Bean;
import com.ifeng_tech.treasuryyitong.bean.CollectBean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.MyListView;
import com.ifeng_tech.treasuryyitong.view.Take_Authenticate_Dialog1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 托管鉴定 详情页
 */
public class Authenticate_Details_Activity extends BaseMVPActivity<Authenticate_Details_Activity,MyPresenter<Authenticate_Details_Activity>> {

    private RelativeLayout authenticate_Details_Fan;
    private ImageView authenticate_Details_img;
    private TextView authenticate_Details_bianma;
    private TextView authenticate_Details_name;
    private TextView authenticate_Details_danwei;
    private TextView authenticate_Details_jindu;
    private TextView authenticate_Details_shuliang;
    private TextView authenticate_Details_time;
    private MyListView authenticate_Details_MyListView;

    List<Authenticate_Details_Bean> list = new ArrayList<>();
    @Override
    public MyPresenter<Authenticate_Details_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate_);
        initView();

        initData();

        authenticate_Details_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 顶部的商品信息暂时是模拟的数据，有可能是查询，有可能是传递 先做成传递
        Intent intent = getIntent();
        CollectBean collectBean = (CollectBean) intent.getSerializableExtra("CollectBean");
        authenticate_Details_img.setImageResource(collectBean.getImg());
        authenticate_Details_bianma.setText(""+235998647);
        authenticate_Details_name.setText(collectBean.getName());
        authenticate_Details_danwei.setText("枚");
        authenticate_Details_jindu.setText("100/200");
        authenticate_Details_shuliang.setText(""+100);
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        authenticate_Details_time.setText(simpleDateFormat.format(date));

        // 实例化适配器
        Authenticate_Details_list_Adapter authenticate_details_list_adapter = new Authenticate_Details_list_Adapter(Authenticate_Details_Activity.this, list);
        // 设置适配器
        authenticate_Details_MyListView.setAdapter(authenticate_details_list_adapter);
        // 适配器的接口回调
        authenticate_details_list_adapter.setAuthenticate_details_list_jieKou(new Authenticate_Details_list_Adapter.Authenticate_Details_list_JieKou() {
            @Override
            public void shangWu(int postion) { // 点击上午
                //设置dialog的样式
                final Take_Authenticate_Dialog1 dialog = new Take_Authenticate_Dialog1(Authenticate_Details_Activity.this, R.style.dialog_setting);
                MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this,dialog); // 设置dialog弹出框弹出时的动画
                dialog.setTake_authenticate_dialog1_zuida(list.get(postion).getAMsy());
                dialog.setTake_authenticate_dialog1_shouxufei(75.6); // 传递手续费

                dialog.setTake_Authenticate_Dialog1_JieKou(new Take_Authenticate_Dialog1.Take_Authenticate_Dialog1_JieKou() {
                    @Override
                    public void QuanRen() {
                        MyUtils.setToast("再弹一个框。。。");
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void xiaWu(int postion) { // 点击下午

                //设置dialog的样式
                final Take_Authenticate_Dialog1 dialog = new Take_Authenticate_Dialog1(Authenticate_Details_Activity.this, R.style.dialog_setting);
                MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this,dialog); // 设置dialog弹出框弹出时的动画
                dialog.setTake_authenticate_dialog1_zuida(list.get(postion).getAMsy());
                dialog.setTake_authenticate_dialog1_shouxufei(99.99); // 传递手续费

                dialog.setTake_Authenticate_Dialog1_JieKou(new Take_Authenticate_Dialog1.Take_Authenticate_Dialog1_JieKou() {
                    @Override
                    public void QuanRen() {
                        MyUtils.setToast("再弹一个框。。。");


                        dialog.dismiss();
                    }
                });
            }
        });

    }

    private void initView() {
        authenticate_Details_Fan = (RelativeLayout) findViewById(R.id.authenticate_Details_Fan);
        authenticate_Details_img = (ImageView) findViewById(R.id.authenticate_Details_img);
        authenticate_Details_bianma = (TextView) findViewById(R.id.authenticate_Details_bianma);
        authenticate_Details_name = (TextView) findViewById(R.id.authenticate_Details_name);
        authenticate_Details_danwei = (TextView) findViewById(R.id.authenticate_Details_danwei);
        authenticate_Details_jindu = (TextView) findViewById(R.id.authenticate_Details_jindu);
        authenticate_Details_shuliang = (TextView) findViewById(R.id.authenticate_Details_shuliang);
        authenticate_Details_time = (TextView) findViewById(R.id.authenticate_Details_time);
        authenticate_Details_MyListView = (MyListView) findViewById(R.id.authenticate_Details_MyListView);

        // 解决scrollview默认显示不在顶部
        authenticate_Details_img.setFocusable(true);
        authenticate_Details_img.setFocusableInTouchMode(true);
        authenticate_Details_img.requestFocus();

        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);


    }

    public void initData(){

        for (int i=0;i<10;i++){
            list.add(new Authenticate_Details_Bean(125689453,60,80));
        }
    }
}
