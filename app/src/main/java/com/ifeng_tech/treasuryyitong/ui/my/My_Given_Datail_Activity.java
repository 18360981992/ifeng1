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
import com.ifeng_tech.treasuryyitong.ui.Collection_Directory_Detail_Activity;
import com.ifeng_tech.treasuryyitong.utils.EGiven_ListStage;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.TakeGiven_Dialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private LinearLayout my_Given_Datail_xiangqing;
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
    private TextView my_given_datail_duifang;
    private TextView my_given_datail_riqi;
    private TextView my_given_datail_beizhu;
    private TextView my_given_datail_youqingtishi;

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
        my_Given_Datail_cword.setText("编号："+ give_list_bean.getGoodsCode());
        my_Given_Datail_dword.setText(""+ give_list_bean.getOrderNo());

        double price= give_list_bean.getFee()* give_list_bean.getProfit();
        my_Given_Datail_shouxufei.setText("￥"+ DashApplication.decimalFormat.format(price));
        my_Given_Datail_zhangtai.setText(EGiven_ListStage.getName(give_list_bean.getStatus())); // 状态
        // 设置状态颜色
        if(give_list_bean.getStatus()==0){
            my_Given_Datail_zhangtai.setTextColor(getResources().getColor(R.color.shenhe));
        }else if(give_list_bean.getStatus()==2){
            my_Given_Datail_zhangtai.setTextColor(getResources().getColor(R.color.shibai));
        }else {
            my_Given_Datail_zhangtai.setTextColor(getResources().getColor(R.color.name_se));
        }

        if(leibie==0 ){// 0==转入
            /**
             *  PENDING(0, "等待确认"),
                 AUDIT_REBUTTED(1, "已完成"),
                 WAITING(2, "已失败"),
                 ONGOING(3, "已取消"),
                 TIMEOUT(4,"已取消"),  // 一拒绝
                 STOPPED(5, "已过期");
             */

            if(give_list_bean.getStatus()==0){
                my_given_datail_anniu.setVisibility(View.VISIBLE);
            }else{
                my_given_datail_anniu.setVisibility(View.INVISIBLE);
            }
            my_Given_Datail_leibie.setText("转入");
            my_given_datail_duifang.setText(give_list_bean.getOppositeUserCode()+"");
            orderNo=give_list_bean.getOrderNo();  // 为转赠单号赋值
            my_Given_Datail_cangku.setText("+ "+give_list_bean.getAmount());  // 仓库改成数量
        }else{

            if(give_list_bean.getStatus()==0){
                my_Given_Datail_zhangtai.setText("等待对方确认");
            }

            my_Given_Datail_leibie.setText("转出");
            my_given_datail_anniu.setVisibility(View.INVISIBLE);
            my_given_datail_duifang.setText(give_list_bean.getOppositeUserCode()+"");
            my_Given_Datail_cangku.setText("- "+give_list_bean.getAmount());  // 仓库改成数量
        }




        Date date = new Date(give_list_bean.getAddTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        my_given_datail_riqi.setText(simpleDateFormat.format(date));

        // 展示备注
        my_given_datail_beizhu.setText(give_list_bean.getRemarks()+"");

        // 获取友情提示的预收仓储费
        double cangchu_fee = give_list_bean.getGuidingPrice() * give_list_bean.getStorageFeeRate();
        String format = DashApplication.decimalFormat.format(cangchu_fee);
        my_given_datail_youqingtishi.setText("确认收货后会收取相应的仓储费用，预计单枚仓储费用为¥"+format+"/天。");

        // 点击顶部大波浪跳到藏品目录详情
        my_Given_Datail_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsId = give_list_bean.getGoodsId();
                Intent intent = new Intent(My_Given_Datail_Activity.this, Collection_Directory_Detail_Activity.class);
                intent.putExtra("goodsId",""+goodsId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });
    }

    private void initView() {
        my_Given_Datail_Fan = (RelativeLayout) findViewById(R.id.my_Given_Datail_Fan);
        my_Given_Datail_name = (TextView) findViewById(R.id.my_Given_Datail_name);
        my_Given_Datail_cword = (TextView) findViewById(R.id.my_Given_Datail_cword);
        my_Given_Datail_xiangqing = (LinearLayout) findViewById(R.id.my_Given_Datail_xiangqing);
        my_Given_Datail_dword = (TextView) findViewById(R.id.my_Given_Datail_dword);
        my_Given_Datail_cangku = (TextView) findViewById(R.id.my_Given_Datail_cangku);
        my_Given_Datail_shouxufei = (TextView) findViewById(R.id.my_Given_Datail_shouxufei);
        my_Given_Datail_leibie = (TextView) findViewById(R.id.my_Given_Datail_leibie);
        my_Given_Datail_zhangtai = (TextView) findViewById(R.id.my_Given_Datail_zhangtai);
        my_given_datail_duifang = (TextView) findViewById(R.id.my_Given_Datail_duifang);
        my_given_datail_riqi = (TextView) findViewById(R.id.my_Given_Datail_riqi);
        my_given_datail_beizhu = (TextView) findViewById(R.id.my_Given_Datail_beizhu);
        my_given_datail_youqingtishi = (TextView) findViewById(R.id.my_Given_Datail_youqingtishi);

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

                // 使用自定义的dialog框
                final TakeGiven_Dialog takeGiven_Dialog = new TakeGiven_Dialog(My_Given_Datail_Activity.this, R.style.dialog_setting);
                MyUtils.getPuTongDiaLog(My_Given_Datail_Activity.this,takeGiven_Dialog);
                takeGiven_Dialog.setTakeGiven_Dialog_text("是否同意该笔转让？");
                takeGiven_Dialog.setTakeGiven_Dialog_JieKou(new TakeGiven_Dialog.TakeGiven_Dialog_JieKou() {
                    @Override
                    public void chuan() {

                        HashMap<String, String> map = new HashMap<>();
                        map.put("orderNo",orderNo);

                        //  进度框
                        final ProgressDialog aniDialog = MyUtils.getProgressDialog(My_Given_Datail_Activity.this, SP_String.JIAZAI);

                        setButton(APIs.getUserZhuanzeng_accept,map, aniDialog,"同意转让成功");
                    }
                });

                break;
            case R.id.my_Given_Datail_jujue:
//                MyUtils.setToast("点击拒绝。。。");

                // 使用自定义的dialog框
                final TakeGiven_Dialog takeGiven_Dialog1 = new TakeGiven_Dialog(My_Given_Datail_Activity.this, R.style.dialog_setting);
                MyUtils.getPuTongDiaLog(My_Given_Datail_Activity.this,takeGiven_Dialog1);
                takeGiven_Dialog1.setTakeGiven_Dialog_text("是否拒绝该笔转让？");
                takeGiven_Dialog1.setTakeGiven_Dialog_JieKou(new TakeGiven_Dialog.TakeGiven_Dialog_JieKou() {
                    @Override
                    public void chuan() {
                        HashMap<String, String> map1 = new HashMap<>();
                        map1.put("orderNo",orderNo);

                        //  进度框
                        final ProgressDialog aniDialog1 = MyUtils.getProgressDialog(My_Given_Datail_Activity.this, SP_String.JIAZAI);

                        setButton(APIs.getUserZhuanzeng_refuse,map1, aniDialog1,"拒绝转让成功");
                    }
                });

                break;
        }
    }

    private void setButton(String url,HashMap<String, String> map, final ProgressDialog aniDialog, final String text) {
        myPresenter.postPreContent(url, map, new MyInterfaces() {
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
