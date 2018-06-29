package com.ifeng_tech.treasuryyitong.ui.my;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.bean.my.QR_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SignUtils;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.Search_Pop_View;
import com.ifeng_tech.treasuryyitong.view.TakeDonation_Dialog;
import com.jwsd.libzxing.OnQRCodeListener;
import com.jwsd.libzxing.QRCodeManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  转赠  表单填写  两种情况  从仓库进入  从首页进入
 */
public class Donation_Activity extends BaseMVPActivity<Donation_Activity,MyPresenter<Donation_Activity>> {

    private RelativeLayout donation_Fan;
    private EditText donation_huiyuan_word;
    private EditText donation_cangpin_wrod;
    private EditText donation_name;
    private EditText donation_shuliang;
    private TextView donation_danjia;
    private EditText donation_yewu_pass;
    private Button donation_tijiao;

    private int measuredWidth;
    private Search_Pop_View search_pop_view;

    private TextView donation_zuidae;

    private LinearLayout donation_weitanchuan;
    private ImageView donation_weitanchuan_img;
    private TextView donation_weitanchuan_text;
    private int weitanchuan_height;

    String goodsId="";  // 个人转赠 藏品id
    String amount="";   // 转赠数量
    String oppositeUserCode="";   //  转赠对方账号
    String businessPwd="";    //  业务密码

    List<WarehouseBean.DataBean.ListBean> list = new ArrayList<WarehouseBean.DataBean.ListBean>();
    private String type;
    private ImageView donation_saoyisao;
    private RelativeLayout donation_title;

    @Override
    public MyPresenter<Donation_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_);

        initView();

        // 藏品代码失焦
        setTextView(donation_cangpin_wrod,false);

        // 藏品名称失焦
        setTextView(donation_name,false);

        // 模拟数据 做页面回显，其中暂无单价回显
        final Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if(type ==null){

            setTextView(donation_huiyuan_word,true);
            WarehouseBean.DataBean.ListBean warehouseBean = (WarehouseBean.DataBean.ListBean) intent.getSerializableExtra("WarehouseBean");

            if(warehouseBean!=null){
                donation_cangpin_wrod.setText(warehouseBean.getGoodsCode());
                if(warehouseBean.getGoodsName().length()>10){
                    String name = warehouseBean.getGoodsName().substring(0, 10);
                    donation_name.setText(name+"...");
                }else{
                    donation_name.setText(warehouseBean.getGoodsName());
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("goodsId",warehouseBean.getGoodsId()+"");
                getShouXuFei(map,donation_danjia,donation_zuidae);  // 根据藏品id获取手续费

                donation_shuliang.setSelection(donation_shuliang.getText().length());

                goodsId= warehouseBean.getGoodsId()+"";  // 为藏品id赋值

            }else{
                donation_shuliang.setText("");
            }

        }else if(type.equals("1")){   //  从二维码进来后 , 从新仓库进来后  会员账号不让改
            get_QR_Moth(intent, type);
        }else if(type.equals("2")){  // 从旧仓库进来后 ，所有输入框禁止输入
            get_QR_Moth(intent, type);
        }

        // 点击返回
        donation_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        // 藏品代码输入监听  弹出pop框
//        donation_cangpin_wrod.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(s.toString().length()>4){ // 当输入框中数据长度大于4时，先去请求网络，然后将请求出来的数据装入集合，传入pop框
//                    String json = DashApplication.sp.getString(SP_String.CHICANG, "");
////                    LogUtils.i("jiba","===="+json);
//                    WarehouseBean warehouseBean1 = new Gson().fromJson(json, WarehouseBean.class);
//                    List<WarehouseBean.DataBean.ListBean> zilist = warehouseBean1.getData().getList();
//                    list.clear();
//                    for (WarehouseBean.DataBean.ListBean bean:zilist){
//                        if(bean.getGoodsCode().contains(s.toString())){
//                            list.addAll(zilist);
//                        }
//                    }
//                    if(list.size()>0){
//                        if(search_pop_view==null){
//                            search_pop_view = new Search_Pop_View(getApplicationContext(),measuredWidth,list);
//                            search_pop_view.setBackgroundDrawable(new BitmapDrawable());
//                            search_pop_view.showAsDropDown(donation_cangpin_wrod);
//                            donation_Pop_JieKou.chuan();
//                        }else{
//                            if(search_pop_view.isShowing()){  // 判断pop是隐藏/显示
//                                search_pop_view.setPopShuJu(list);
//                            }else{
//                                search_pop_view.showAsDropDown(donation_cangpin_wrod);
//                                search_pop_view.setPopShuJu(list);
//                            }
//                            donation_Pop_JieKou.chuan();
//                        }
//                    }
//                }else{
//                    if(search_pop_view!=null){
//                        search_pop_view.dismiss();
//                    }
//                }
//            }
//        });


        // 点击藏品代码跳转仓库  新仓库页面
        donation_cangpin_wrod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Donation_Activity.this, My_Warehouse_Activity2.class);
                String word = donation_huiyuan_word.getText().toString().trim();
                intent1.putExtra("userCode",word+"");
                intent1.putExtra("select","转赠");
                startActivityForResult(intent1,DashApplication.ZHUAN_TO_CANGKU_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        donation_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Donation_Activity.this, My_Warehouse_Activity2.class);
                String word = donation_huiyuan_word.getText().toString().trim();
                intent1.putExtra("userCode",word+"");
                intent1.putExtra("select","转赠");
                startActivityForResult(intent1,DashApplication.ZHUAN_TO_CANGKU_req);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });



        // 点击提交事件
        donation_tijiao.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                submit();
            }
        });

        // 点击扫描二维码
        donation_saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(Donation_Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Donation_Activity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    return;
                }
                QRCodeManager.getInstance()
                        .with(Donation_Activity.this)
                        .setReqeustType(DashApplication.DONATION_SAOMIAO_req)
//                .setRequestCode(1001)
                        .scanningQRCode(new OnQRCodeListener() {
                            @Override
                            public void onCompleted(String des) {
                                LogUtils.i("jiba","donation==="+des);
                                String result = null;
                                try {
                                    result = SignUtils.decode(des);
                                    if(result.contains(SP_String.QR_ZHUANZENG)){
                                        if(result.length()>20){
                                            String path = result.substring(0, result.indexOf("?"));
                                            String referralCode = result.substring(result.indexOf("=")+1, result.length());

//                            LogUtils.i("jiba","referralCode===="+referralCode);

                                            QR_Bean qr_bean = new Gson().fromJson(referralCode, QR_Bean.class);
                                            if(path.equals(SP_String.QR_ZHUANZENG)){
                                                Intent intent = new Intent(Donation_Activity.this, Donation_Activity.class);
                                                intent.putExtra("QR_Bean", referralCode);
                                                if(qr_bean.getGoodsInfo()==null) intent.putExtra("type","1");  // 表示从扫描二维码跳入转赠  1 == 输入框可输入

                                                else  intent.putExtra("type","2");  // 表示从扫描二维码跳入转赠  2 == 输入框不可输入

                                                startActivityForResult(intent,500);
                                                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                            }
                                        }else{
                                            MyUtils.setToast(des);
                                        }
                                    }else{
                                        MyUtils.setToast(des);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    MyUtils.setToast(des);
                                }
                            }

                            @Override
                            public void onError(Throwable errorMsg) {
                                MyUtils.setToast("解析二维码失败=="+errorMsg);
                            }

                            @Override
                            public void onCancel() {
                                MyUtils.setToast("扫描任务取消了");
                            }

                            /**
                             * 当点击手动添加时回调
                             *
                             * @param requestCode
                             * @param resultCode
                             * @param data
                             */
                            @Override
                            public void onManual(int requestCode, int resultCode, Intent data) {
                                Log.i("jiba","点击了手动添加了");
                            }


                        });
            }
        });
    }

    // 获取从 二维码 跳转过来的json传
    private void get_QR_Moth(Intent intent,String type) {
        String json = intent.getStringExtra("QR_Bean");
        QR_Bean qr_bean = new Gson().fromJson(json, QR_Bean.class);

        if(!qr_bean.getUserCode().equals("")){
            // 会员号 赋值  并禁止输入
            donation_huiyuan_word.setText(qr_bean.getUserCode());
            setTextView(donation_huiyuan_word,false);
        }

        if(qr_bean.getGoodsInfo()==null){

            donation_shuliang.setText("");

        }else{
            donation_cangpin_wrod.setText(qr_bean.getGoodsInfo().getGoodsCode());  // 藏品代码

            if(qr_bean.getGoodsInfo().getGoodsName().length()>10){
                String name = qr_bean.getGoodsInfo().getGoodsName().substring(0, 10);
                donation_name.setText(name+"...");      // 藏品名称
            }else{
                donation_name.setText(qr_bean.getGoodsInfo().getGoodsName());
            }

            HashMap<String, String> map = new HashMap<>();
            map.put("goodsId",qr_bean.getGoodsInfo().getGoodsId()+"");
            getShouXuFei(map,donation_danjia,donation_zuidae);  // 根据藏品id获取手续费  以及最大可用数量

            donation_shuliang.setText(qr_bean.getGoodsInfo().getGoodsNum());  // 设置转赠数量
            donation_shuliang.setSelection(qr_bean.getGoodsInfo().getGoodsNum().length());

            if(type.equals("2")){
                // 数量的失焦
                setTextView(donation_shuliang,false);
            }
            // 业务密码的获取焦点
            setTextView(donation_yewu_pass,true);
            donation_yewu_pass.setGravity(Gravity.RIGHT);

            goodsId= qr_bean.getGoodsInfo().getGoodsId()+"";
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //注册onActivityResult
        QRCodeManager.getInstance().with(this).onActivityResult(requestCode, resultCode, data);

        if(requestCode==DashApplication.ZHUAN_TO_CANGKU_req&&resultCode==DashApplication.ZHUAN_TO_CANGKU_res){
            String type = data.getStringExtra("type");
            get_QR_Moth(data, type);
        }

        if(requestCode==500){
            finish();
        }

    }

    // 设置textview 是否可点击
    private void setTextView(TextView textView,boolean focusable) {
        textView.setFocusable(focusable);
        textView.setFocusableInTouchMode(focusable); // user touches widget on phone with touch screen
        textView.setClickable(focusable);
    }


//    // 输入框获取焦点的时候获取我的持仓
//    private void getFirstConect(final HashMap<String, String> map) {
//        myPresenter.postPreContent(APIs.getHoldList, map, new MyInterfaces() {
//            @Override
//            public void chenggong(String json) {
//                try {
//                    JSONObject jsonObject = new JSONObject(json);
//                    String code = (String) jsonObject.get("code");
//                    if(code.equals("2000")){
//                        SharedPreferences.Editor edit = DashApplication.edit;
//                        edit.putString(SP_String.CHICANG,json).commit();
////                        LogUtils.i("jiba","==="+json);
//                    }else{
//                        MyUtils.setToast((String) jsonObject.get("message"));
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void shibai(String ss) {
//                MyUtils.setToast("获取我的持仓=="+ss);
//            }
//        });
//    }

    @Override
    protected void onResume() {
        super.onResume();
//        setDonation_Pop_JieKou(new Donation_Pop_JieKou() {
//            @Override
//            public void chuan() {
//                search_pop_view.setSearch_Pop_JieKou(new Search_Pop_View.Search_Pop_JieKou() {
//                    @Override
//                    public void chuan(List<WarehouseBean.DataBean.ListBean> list,int postion) {
////                        MyUtils.setToast("请做回显操作。。。");
//                        // 模拟数据
//                        donation_cangpin_wrod.setText(list.get(postion).getGoodsCode());
//                        donation_cangpin_wrod.setSelection(list.get(postion).getGoodsCode().length());
//                        donation_name.setText(list.get(postion).getGoodsName());
//
//                        // 价格会从请求的数据来获取
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put("goodsId",list.get(postion).getGoodsId()+"");
//                        getShouXuFei(map,donation_danjia,donation_zuidae);  // 根据藏品id获取手续费
//
//                        goodsId=list.get(postion).getGoodsId()+"";  // 为藏品id赋值
//                    }
//                });
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        donation_title = (RelativeLayout) findViewById(R.id.donation_title);
        donation_Fan = (RelativeLayout) findViewById(R.id.donation_Fan);
        donation_huiyuan_word = (EditText) findViewById(R.id.donation_huiyuan_word);
        donation_saoyisao = (ImageView) findViewById(R.id.donation_saoyisao);
        donation_cangpin_wrod = (EditText) findViewById(R.id.donation_cangpin_wrod);
        donation_name = (EditText) findViewById(R.id.donation_name);
        donation_shuliang = (EditText) findViewById(R.id.donation_shuliang);
        donation_danjia = (TextView) findViewById(R.id.donation_danjia);
        donation_yewu_pass = (EditText) findViewById(R.id.donation_yewu_pass);
        donation_zuidae = (TextView) findViewById(R.id.donation_zuidae);
        donation_tijiao = (Button) findViewById(R.id.donation_tijiao);
        donation_weitanchuan = (LinearLayout) findViewById(R.id.donation_weitanchuan);
        donation_weitanchuan_img = (ImageView) findViewById(R.id.donation_weitanchuan_img);
        donation_weitanchuan_text = (TextView) findViewById(R.id.donation_weitanchuan_text);
        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取控件的高度
        donation_cangpin_wrod.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                donation_cangpin_wrod.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                measuredWidth = donation_cangpin_wrod.getMeasuredWidth();
            }
        });

        //通过设置监听来获取 微弹窗 控件的高度
        donation_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                donation_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = donation_weitanchuan.getMeasuredHeight();
            }
        });


    }

    private void submit() {
        // validate
        String word = donation_huiyuan_word.getText().toString().trim();
        if (TextUtils.isEmpty(word)) {
            Toast.makeText(this, "请输入对方交易会员代码", Toast.LENGTH_SHORT).show();
            return;
        }

        oppositeUserCode=word;  // 为对方账号赋值

        String cword = donation_cangpin_wrod.getText().toString().trim();
        if (TextUtils.isEmpty(cword)) {
            Toast.makeText(this, "请输入藏品代码", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = donation_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入藏品名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String shuliang = donation_shuliang.getText().toString().trim();
        if(shuliang.length()>=10){
            MyUtils.setToast("您已超出最大转赠件数");
            return;
        }

        if (TextUtils.isEmpty(shuliang)||Integer.valueOf(shuliang)<=0) {
            Toast.makeText(this, "转赠数量不能为空且不能小于0", Toast.LENGTH_SHORT).show();
            return;
        }

        amount=shuliang;   // 为转赠数量赋值

        String yewu_pass = donation_yewu_pass.getText().toString().trim();
        if (TextUtils.isEmpty(yewu_pass)) {
            Toast.makeText(this, "请输入业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(yewu_pass.length()!=6){
            Toast.makeText(this, "请输入6位的业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        businessPwd=yewu_pass;


        //设置dialog的样式
        final TakeDonation_Dialog dialog = new TakeDonation_Dialog(this, R.style.dialog_setting);
        MyUtils.getDiaLogDiBu(this,dialog); // 设置dialog弹出框弹出时的动画
        dialog.setTitle("确认转赠信息");
        dialog.setFeiName("手续费");
        dialog.setWord(word);
        dialog.setShuLiang(Integer.valueOf(shuliang));
        dialog.setZongJia(donation_danjia.getText().toString());
        dialog.setTakeDonation_Dialog_JieKou(new TakeDonation_Dialog.TakeDonation_Dialog_JieKou() {
            @Override
            public void QuanRen() { // 点击确认
//                MyUtils.setToast("正在请求网络。。。");
                dialog.dismiss();

                HashMap<String, String> map = new HashMap<>();
                map.put("goodsId",goodsId);
                map.put("amount",amount);
                map.put("oppositeUserCode",oppositeUserCode);
                map.put("businessPwd",businessPwd);

                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Donation_Activity.this, SP_String.JIAZAI);

                myPresenter.postPreContent(APIs.getUserZhuanzeng, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){

                                MyUtils.setObjectAnimator_anquan(donation_weitanchuan,
                                        donation_weitanchuan_img,
                                        donation_weitanchuan_text,
                                        weitanchuan_height,
                                        true,"转赠成功");
                                MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                                    @Override
                                    public void chuan() {
                                        goodsId="";
                                        amount="";
                                        oppositeUserCode="";
                                        businessPwd="";
                                        donation_huiyuan_word.setText("");
                                        donation_cangpin_wrod.setText("");
                                        donation_name.setText("");
                                        donation_shuliang.setText("");
                                        donation_yewu_pass.setText("");
                                        setTextView(donation_huiyuan_word,true);
                                        setTextView(donation_shuliang,true);
                                        setTextView(donation_yewu_pass,true);

                                    }
                                });


                            }else{
                                MyUtils.setObjectAnimator(donation_weitanchuan,
                                        donation_weitanchuan_img,
                                        donation_weitanchuan_text,
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
                        MyUtils.setObjectAnimator(donation_weitanchuan,
                                donation_weitanchuan_img,
                                donation_weitanchuan_text,
                                weitanchuan_height,
                                false,ss);
                    }
                });
            }

            @Override
            public void QuXiao() {// 点击取消
                dialog.dismiss();
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }

    /**
     *  这个接口回调用于输入藏品代码的pop的弹出
     *
     */
    public interface Donation_Pop_JieKou{
        void chuan();
    }

    Donation_Pop_JieKou donation_Pop_JieKou;

    public void setDonation_Pop_JieKou(Donation_Pop_JieKou donation_Pop_JieKou) {
        this.donation_Pop_JieKou = donation_Pop_JieKou;
    }
}
