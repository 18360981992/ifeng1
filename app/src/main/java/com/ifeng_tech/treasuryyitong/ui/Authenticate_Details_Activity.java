package com.ifeng_tech.treasuryyitong.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.ifeng_tech.treasuryyitong.view.Take_Authenticate_Dialog2;

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
    private LinearLayout authenticate_details_weitanchuan;
    private TextView authenticate_details_weitanchuan_text;
    private ImageView authenticate_details_weitanchuan_img;
    private int weitanchuan_height;
    private RelativeLayout authenticate_details_toobar;
    private int toobar_height;

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
            public void shangWu(final int postion) { // 点击上午
                //设置dialog的样式
                final Take_Authenticate_Dialog1 dialog = new Take_Authenticate_Dialog1(Authenticate_Details_Activity.this, R.style.dialog_setting);
                MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this,dialog); // 设置dialog弹出框弹出时的动画
                dialog.setTake_authenticate_dialog1_zuida(list.get(postion).getAMsy());
                dialog.setTake_authenticate_dialog1_shouxufei(list.get(postion).getPrice()); // 传递手续费
                // 第一次弹出框的接口回调
                dialog.setTake_Authenticate_Dialog1_JieKou(new Take_Authenticate_Dialog1.Take_Authenticate_Dialog1_JieKou() {
                    @Override
                    public void QuanRen(int num) {
                        dialog.dismiss();

                        //设置dialog的样式
                        final Take_Authenticate_Dialog2 dialog = new Take_Authenticate_Dialog2(Authenticate_Details_Activity.this, R.style.dialog_setting);
                        MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this,dialog); // 设置dialog弹出框弹出时的动画

                        Date date1 = new Date(list.get(postion).getTime());
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                        dialog.setTake_Authenticate_Dialog2_time(simpleDateFormat1.format(date1)+" 上午");

                        dialog.setTake_Authenticate_Dialog2_shuliang(num);
                        dialog.setTtake_Authenticate_Dialog2_shouxufei(list.get(postion).getPrice()*num);

                        dialog.setTake_Authenticate_Dialog2_JieKou(new Take_Authenticate_Dialog2.Take_Authenticate_Dialog2_JieKou() {
                            @Override
                            public void QuanRen() {
                                MyUtils.setToast("点击确认按钮，进行支付交易。。。");
                                dialog.dismiss();
                                // 微弹窗
                                MyUtils.setObjectAnimator(authenticate_details_weitanchuan,
                                        authenticate_details_weitanchuan_img,
                                        authenticate_details_weitanchuan_text,
                                        weitanchuan_height,
                                        false);
                            }
                        });

                    }
                });
            }

            @Override
            public void xiaWu(final int postion) { // 点击下午
                //设置dialog的样式
                final Take_Authenticate_Dialog1 dialog = new Take_Authenticate_Dialog1(Authenticate_Details_Activity.this, R.style.dialog_setting);
                MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this,dialog); // 设置dialog弹出框弹出时的动画
                dialog.setTake_authenticate_dialog1_zuida(list.get(postion).getPMsy());
                dialog.setTake_authenticate_dialog1_shouxufei(list.get(postion).getPrice()); // 传递手续费

                dialog.setTake_Authenticate_Dialog1_JieKou(new Take_Authenticate_Dialog1.Take_Authenticate_Dialog1_JieKou() {
                    @Override
                    public void QuanRen(int num) {
                        dialog.dismiss();
                        //设置dialog的样式
                        final Take_Authenticate_Dialog2 dialog = new Take_Authenticate_Dialog2(Authenticate_Details_Activity.this, R.style.dialog_setting);
                        MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this,dialog); // 设置dialog弹出框弹出时的动画

                        Date date1 = new Date(list.get(postion).getTime());
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                        dialog.setTake_Authenticate_Dialog2_time(simpleDateFormat1.format(date1)+" 下午");
                        dialog.setTake_Authenticate_Dialog2_shuliang(num);
                        dialog.setTtake_Authenticate_Dialog2_shouxufei(list.get(postion).getPrice()*num);

                        dialog.setTake_Authenticate_Dialog2_JieKou(new Take_Authenticate_Dialog2.Take_Authenticate_Dialog2_JieKou() {
                            @Override
                            public void QuanRen() {
                                MyUtils.setToast("点击确认按钮，进行支付交易。。。");
                                dialog.dismiss();
                                // 微弹窗
                                MyUtils.setObjectAnimator(authenticate_details_weitanchuan,
                                                        authenticate_details_weitanchuan_img,
                                                        authenticate_details_weitanchuan_text,
                                                        weitanchuan_height,
                                                        true);

                            }
                        });
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
        authenticate_details_weitanchuan = (LinearLayout) findViewById(R.id.authenticate_Details_weitanchuan);
        authenticate_details_weitanchuan_img = (ImageView) findViewById(R.id.authenticate_Details_weitanchuan_img);
        authenticate_details_weitanchuan_text = (TextView) findViewById(R.id.authenticate_Details_weitanchuan_text);
        authenticate_details_toobar = (RelativeLayout) findViewById(R.id.authenticate_Details_Toobar);

        // 解决scrollview默认显示不在顶部
        authenticate_Details_img.setFocusable(true);
        authenticate_Details_img.setFocusableInTouchMode(true);
        authenticate_Details_img.requestFocus();

        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        authenticate_details_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                authenticate_details_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = authenticate_details_weitanchuan.getMeasuredHeight();
            }
        });

        //通过设置监听来获取 toobar 控件的高度
        authenticate_details_toobar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                authenticate_details_toobar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                toobar_height = authenticate_details_toobar.getMeasuredHeight();
            }
        });

    }



    public void initData(){

        for (int i=0;i<10;i++){
            list.add(new Authenticate_Details_Bean(125689453,60,80,99.99));
        }
    }
}
