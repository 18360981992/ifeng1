package com.ifeng_tech.treasuryyitong.ui.my.tuoguan;

import android.app.ProgressDialog;
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

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Authenticate_Details_list_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Authenticate_Details_Time_Bean;
import com.ifeng_tech.treasuryyitong.bean.my.Collocation_Subscribe_bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.ui.Collection_Directory_Detail_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.bind_email.Bind_Email_Activity1;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.MyListView;
import com.ifeng_tech.treasuryyitong.view.TakeCommonDialog;
import com.ifeng_tech.treasuryyitong.view.Take_Authenticate_Dialog1;
import com.ifeng_tech.treasuryyitong.view.Take_Authenticate_Dialog2;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

    private LinearLayout authenticate_details_weitanchuan;
    private TextView authenticate_details_weitanchuan_text;
    private ImageView authenticate_details_weitanchuan_img;
    private int weitanchuan_height;
    private RelativeLayout authenticate_details_toobar;
    private int toobar_height;


    String trusteeshipId="";  //  托管时间列表的id
    String goodsId=""; //  商品的id
    private Authenticate_Details_list_Adapter authenticate_details_list_adapter;
    private LinearLayout authenticate_details_xiangqing;

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

        authenticate_Details_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 顶部的商品信息暂时是模拟的数据，有可能是查询，有可能是传递 先做成传递
        Intent intent = getIntent();
        final Collocation_Subscribe_bean.DataBean.ListBean collocationBean = (Collocation_Subscribe_bean.DataBean.ListBean) intent.getSerializableExtra("CollocationBean");

        trusteeshipId=collocationBean.getId()+"";
        goodsId=collocationBean.getGoodsId()+"";

//        if(collocationBean.getGoodsImg()==null){
//            authenticate_Details_img.setImageResource(R.drawable.guangao);
//        }else{
//            Glide.with(Authenticate_Details_Activity.this).load(collocationBean.getGoodsImg()).error(R.drawable.img_erroy).into(authenticate_Details_img);
//        }

        authenticate_Details_bianma.setText("编号："+collocationBean.getGoodsCode());
        authenticate_Details_name.setText(collocationBean.getGoodsName());
        authenticate_Details_danwei.setText("枚");

        authenticate_Details_jindu.setText(collocationBean.getCount()+"/"+collocationBean.getNumber());
        authenticate_Details_shuliang.setText(""+collocationBean.getMinNumber());

        String[] times = collocationBean.getApplyTime().split("\\/");
        authenticate_Details_time.setText(times[1]);


        // 点击大波浪布局跳到藏品目录详情
        authenticate_details_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsId = collocationBean.getGoodsId();
                Intent intent = new Intent(Authenticate_Details_Activity.this, Collection_Directory_Detail_Activity.class);
                intent.putExtra("goodsId",""+goodsId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        HashMap<String, String> map = new HashMap<>();
        map.put("trusteeshipId",trusteeshipId);
        map.put("goodsId",goodsId);
        myPresenter.postPreContent(APIs.getTrusteeshipDetail, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")) {
                        Authenticate_Details_Time_Bean authenticate_details_time_bean = new Gson().fromJson(json, Authenticate_Details_Time_Bean.class);
                        List<Authenticate_Details_Time_Bean.DataBean.ListBean> list = authenticate_details_time_bean.getData().getList();
                        if(list.size()>0){
                           setAuthenticate_Details_Adapter(authenticate_details_time_bean,list);
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


    public void setAuthenticate_Details_Adapter(final Authenticate_Details_Time_Bean authenticate_details_time_bean, final List<Authenticate_Details_Time_Bean.DataBean.ListBean> list){
        // 实例化适配器
        authenticate_details_list_adapter = new Authenticate_Details_list_Adapter(Authenticate_Details_Activity.this, list);
        // 设置适配器
        authenticate_Details_MyListView.setAdapter(authenticate_details_list_adapter);

        // 适配器的接口回调
        authenticate_details_list_adapter.setAuthenticate_details_list_jieKou(new Authenticate_Details_list_Adapter.Authenticate_Details_list_JieKou() {
            @Override
            public void shangWu(final int postion) { // 点击上午
                String yewu_pass = DashApplication.sp.getString(SP_String.ISUSERYEWUPASS, "");
                if(yewu_pass.equals("0")) {
                    //设置dialog的样式
                    final Take_Authenticate_Dialog1 dialog = new Take_Authenticate_Dialog1(Authenticate_Details_Activity.this, R.style.dialog_setting);
                    MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this, dialog); // 设置dialog弹出框弹出时的动画

                    if (list.get(postion).getRestNumber_am() == null) {
                        dialog.setTake_authenticate_dialog1_zuida(100);  // 最大数量
                    } else {
                        dialog.setTake_authenticate_dialog1_zuida(Integer.valueOf(list.get(postion).getRestNumber_am()));
                    }

                    dialog.setTake_authenticate_dialog1_zuixiao(authenticate_details_time_bean.getData().getTrusteeInfo().getMinNumber());

                    double price = authenticate_details_time_bean.getData().getTrusteeInfo().getAppraisalFee();
                    dialog.setTake_authenticate_dialog1_shouxufei(price); // 传递手续费  只传费率  这里的费率已经算好了 直接传走

                    // 第一次弹出框的接口回调
                    dialog.setTake_Authenticate_Dialog1_JieKou(new Take_Authenticate_Dialog1.Take_Authenticate_Dialog1_JieKou() {
                        @Override
                        public void QuanRen(final int num, final String pass) {  // num是输入框中的值
                            dialog.dismiss();

                            //设置dialog的样式
                            final Take_Authenticate_Dialog2 dialog = new Take_Authenticate_Dialog2(Authenticate_Details_Activity.this, R.style.dialog_setting);
                            MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this, dialog); // 设置dialog弹出框弹出时的动画

                            Date date1 = new Date(list.get(postion).getOrderDate());
                            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                            dialog.setTake_Authenticate_Dialog2_time(simpleDateFormat1.format(date1) + " 上午");

                            dialog.setTake_Authenticate_Dialog2_shuliang(num);
                            double price = authenticate_details_time_bean.getData().getTrusteeInfo().getAppraisalFee() * num;
                            dialog.setTtake_Authenticate_Dialog2_shouxufei(price);

                            dialog.setTake_Authenticate_Dialog2_JieKou(new Take_Authenticate_Dialog2.Take_Authenticate_Dialog2_JieKou() {
                                @Override
                                public void QuanRen() {
//                                MyUtils.setToast("点击确认按钮，进行支付交易。。。");
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("amount", num + "");
                                    map.put("ReservationHostingId", list.get(postion).getIds_am()+"");
                                    map.put("businessPwd", pass);

                                    //  进度框
                                    final ProgressDialog aniDialog = MyUtils.getProgressDialog(Authenticate_Details_Activity.this, SP_String.JIAZAI);

                                    myPresenter.postPreContent(APIs.applyTrusteeship, map, new MyInterfaces() {
                                        @Override
                                        public void chenggong(String json) {
                                            aniDialog.dismiss();
                                            try {
                                                JSONObject jsonObject = new JSONObject(json);
                                                String code = (String) jsonObject.get("code");
                                                if (code.equals("2000")) {
                                                    dialog.dismiss();
                                                    // 微弹窗
                                                    MyUtils.setObjectAnimator(authenticate_details_weitanchuan,
                                                            authenticate_details_weitanchuan_img,
                                                            authenticate_details_weitanchuan_text,
                                                            weitanchuan_height,
                                                            true, "预约成功!");
                                                } else {
                                                    dialog.dismiss();
                                                    // 微弹窗
                                                    MyUtils.setObjectAnimator(authenticate_details_weitanchuan,
                                                            authenticate_details_weitanchuan_img,
                                                            authenticate_details_weitanchuan_text,
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
                                            // 微弹窗
                                            MyUtils.setObjectAnimator(authenticate_details_weitanchuan,
                                                    authenticate_details_weitanchuan_img,
                                                    authenticate_details_weitanchuan_text,
                                                    weitanchuan_height,
                                                    false, ss);
                                        }
                                    });
                                }
                            });

                        }
                    });
                }else{
                    // 使用自定义的dialog框
                    final TakeCommonDialog takeCommonDialog = new TakeCommonDialog(Authenticate_Details_Activity.this, R.style.dialog_setting,"请先设置业务密码！");
                    MyUtils.getPuTongDiaLog(Authenticate_Details_Activity.this,takeCommonDialog);
                    takeCommonDialog.setCommonJieKou(new TakeCommonDialog.CommonJieKou() {
                        @Override
                        public void quxiao() {
                            takeCommonDialog.dismiss();
                        }

                        @Override
                        public void queren() {
                            takeCommonDialog.dismiss();
                            Intent intent = new Intent(Authenticate_Details_Activity.this, Bind_Email_Activity1.class);
                            intent.putExtra("title","业务密码（设置）");
                            intent.putExtra("select",SP_String.YEWUMIMA);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        }
                    });
                }
            }

            @Override
            public void xiaWu(final int postion) { // 点击下午
                String yewu_pass = DashApplication.sp.getString(SP_String.ISUSERYEWUPASS, "");
                if(yewu_pass.equals("0")) {
                    //设置dialog的样式
                    final Take_Authenticate_Dialog1 dialog = new Take_Authenticate_Dialog1(Authenticate_Details_Activity.this, R.style.dialog_setting);
                    MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this, dialog); // 设置dialog弹出框弹出时的动画

                    dialog.setTake_authenticate_dialog1_zuida(Integer.valueOf(list.get(postion).getRestNumber_pm()));  // 最大数量

                    dialog.setTake_authenticate_dialog1_zuixiao(authenticate_details_time_bean.getData().getTrusteeInfo().getMinNumber());  // 最小数量

                    double price = authenticate_details_time_bean.getData().getTrusteeInfo().getAppraisalFee();
                    dialog.setTake_authenticate_dialog1_shouxufei(price); // 传递手续费

                    dialog.setTake_Authenticate_Dialog1_JieKou(new Take_Authenticate_Dialog1.Take_Authenticate_Dialog1_JieKou() {
                        @Override
                        public void QuanRen(final int num, final String pass) {
                            dialog.dismiss();
                            //设置dialog的样式
                            final Take_Authenticate_Dialog2 dialog = new Take_Authenticate_Dialog2(Authenticate_Details_Activity.this, R.style.dialog_setting);
                            MyUtils.getDiaLogDiBu(Authenticate_Details_Activity.this, dialog); // 设置dialog弹出框弹出时的动画

                            Date date1 = new Date(list.get(postion).getOrderDate());
                            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                            dialog.setTake_Authenticate_Dialog2_time(simpleDateFormat1.format(date1) + " 下午");

                            dialog.setTake_Authenticate_Dialog2_shuliang(num);

                            double price = authenticate_details_time_bean.getData().getTrusteeInfo().getAppraisalFee() * num;
                            dialog.setTtake_Authenticate_Dialog2_shouxufei(price);

                            dialog.setTake_Authenticate_Dialog2_JieKou(new Take_Authenticate_Dialog2.Take_Authenticate_Dialog2_JieKou() {
                                @Override
                                public void QuanRen() {
//                                MyUtils.setToast("点击确认按钮，进行支付交易。。。");
                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("amount", num + "");
                                    map.put("ReservationHostingId", list.get(postion).getIds_pm()+"");
                                    map.put("businessPwd", pass);

                                    //  进度框
                                    final ProgressDialog aniDialog = MyUtils.getProgressDialog(Authenticate_Details_Activity.this, SP_String.JIAZAI);

                                    myPresenter.postPreContent(APIs.applyTrusteeship, map, new MyInterfaces() {
                                        @Override
                                        public void chenggong(String json) {
                                            aniDialog.dismiss();
                                            try {
                                                JSONObject jsonObject = new JSONObject(json);
                                                String code = (String) jsonObject.get("code");
                                                if (code.equals("2000")) {
                                                    dialog.dismiss();
                                                    // 微弹窗
                                                    MyUtils.setObjectAnimator(authenticate_details_weitanchuan,
                                                            authenticate_details_weitanchuan_img,
                                                            authenticate_details_weitanchuan_text,
                                                            weitanchuan_height,
                                                            true, "预约成功!");
                                                } else {
                                                    dialog.dismiss();
                                                    // 微弹窗
                                                    MyUtils.setObjectAnimator(authenticate_details_weitanchuan,
                                                            authenticate_details_weitanchuan_img,
                                                            authenticate_details_weitanchuan_text,
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
                                            // 微弹窗
                                            MyUtils.setObjectAnimator(authenticate_details_weitanchuan,
                                                    authenticate_details_weitanchuan_img,
                                                    authenticate_details_weitanchuan_text,
                                                    weitanchuan_height,
                                                    false, ss);
                                        }
                                    });
                                }
                            });
                        }
                    });
                }else{
                    // 使用自定义的dialog框
                    final TakeCommonDialog takeCommonDialog = new TakeCommonDialog(Authenticate_Details_Activity.this, R.style.dialog_setting,"请先设置业务密码！");
                    MyUtils.getPuTongDiaLog(Authenticate_Details_Activity.this,takeCommonDialog);
                    takeCommonDialog.setCommonJieKou(new TakeCommonDialog.CommonJieKou() {
                        @Override
                        public void quxiao() {
                            takeCommonDialog.dismiss();
                        }

                        @Override
                        public void queren() {
                            takeCommonDialog.dismiss();
                            Intent intent = new Intent(Authenticate_Details_Activity.this, Bind_Email_Activity1.class);
                            intent.putExtra("title","业务密码（设置）");
                            intent.putExtra("select",SP_String.YEWUMIMA);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        }
                    });
                }
            }

        });


    }

    private void initView() {
        authenticate_Details_Fan = (RelativeLayout) findViewById(R.id.authenticate_Details_Fan);
//        authenticate_Details_img = (ImageView) findViewById(R.id.authenticate_Details_img);

        authenticate_details_xiangqing = (LinearLayout) findViewById(R.id.authenticate_Details_xiangqing);
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
        authenticate_details_xiangqing.setFocusable(true);
        authenticate_details_xiangqing.setFocusableInTouchMode(true);
        authenticate_details_xiangqing.requestFocus();

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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
