package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.Search_Pop_View;
import com.ifeng_tech.treasuryyitong.view.TakeDonation_Dialog;

import java.util.ArrayList;
import java.util.List;

/**
 *  转赠
 */
public class Donation_Activity extends BaseMVPActivity<Donation_Activity,MyPresenter<Donation_Activity>> implements View.OnClickListener {

    private RelativeLayout donation_Fan;
    private EditText donation_huiyuan_word;
    private EditText donation_cangpin_wrod;
    private EditText donation_name;
    private EditText donation_shuliang;
    private TextView donation_danjia;
    private EditText donation_duanxin;
    private TextView donation_duan_btn;
    private Button donation_tijiao;

    private int measuredWidth;
    private Search_Pop_View search_pop_view;

    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            if(time==0){
                time=60;
                donation_duan_btn.setText("点击发送");
                donation_duan_btn.setEnabled(true);
            }else{
                donation_duan_btn.setText("重新发送"+time+"(s)");
                h.sendEmptyMessageDelayed(0,1000);
            }
        }
    };
    private TextView donation_zuidae;

    int time=60;
    private LinearLayout donation_weitanchuan;
    private ImageView donation_weitanchuan_img;
    private TextView donation_weitanchuan_text;
    private int weitanchuan_height;

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
        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);

        initView();

        // 模拟数据 做页面回显，其中暂无单价回显
        Intent intent = getIntent();
        WarehouseBean warehouseBean = (WarehouseBean) intent.getSerializableExtra("WarehouseBean");
        if(warehouseBean!=null){
            donation_cangpin_wrod.setText(warehouseBean.getWord());
            donation_name.setText(warehouseBean.getShopping_name());
            donation_zuidae.setText("最大转赠数量:"+warehouseBean.getKeyong_num());
            donation_danjia.setText("￥"+DashApplication.decimalFormat.format(120));// 这里是模拟数据
        }else{
            donation_shuliang.setText("");
        }


        // 点击返回
        donation_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 数量输入的监听
        donation_shuliang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if(s1.length()>0){
                    Integer integer = new Integer(s1);
                    if(integer<=0){
                        donation_danjia.setText("￥0.00");  // 这里的单价是从bean类中直接获取
                    }else{
                        String format = DashApplication.decimalFormat.format(integer * 120);
                        donation_danjia.setText("￥"+format);
                    }
                }else{
                    donation_danjia.setText("￥0.00");
                }

            }
        });

        // 短信发送
        donation_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation_duan_btn.setText("重新发送"+time+"(s)");
                donation_duan_btn.setEnabled(false);
                h.sendEmptyMessageDelayed(0,1000);

                MyUtils.setToast("请求网络。。。");
            }
        });

        // 藏品代码输入监听  弹出pop框
        donation_cangpin_wrod.addTextChangedListener(new TextWatcher() {



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>4){ // 当输入框中数据长度大于4时，先去请求网络，然后将请求出来的数据装入集合，传入pop框

                    if(search_pop_view==null){
                        search_pop_view = new Search_Pop_View(Donation_Activity.this,measuredWidth,list);
                        search_pop_view.setBackgroundDrawable(new BitmapDrawable());
                        search_pop_view.showAsDropDown(donation_cangpin_wrod);
                        donation_Pop_JieKou.chuan();
                    }else{
                        if(search_pop_view.isShowing()){  // 判断pop是隐藏/显示
                            search_pop_view.setPopShuJu(list);
                        }else{
                            search_pop_view.showAsDropDown(donation_cangpin_wrod);
                            search_pop_view.setPopShuJu(list);
                        }
                        donation_Pop_JieKou.chuan();
                    }

                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        setDonation_Pop_JieKou(new Donation_Pop_JieKou() {
            @Override
            public void chuan() {
                search_pop_view.setSearch_Pop_JieKou(new Search_Pop_View.Search_Pop_JieKou() {
                    @Override
                    public void chuan(int postion) {
                        MyUtils.setToast("请做回显操作。。。");
                        // 模拟数据
                        donation_cangpin_wrod.setText(list.get(postion)+"");
                        donation_name.setText("两只老虎");
                        donation_danjia.setText("￥"+DashApplication.decimalFormat.format(120)); // 价格会从请求的数据来获取
                        donation_zuidae.setText("最大转赠数量:"+120);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeMessages(0);
    }

    private void initView() {
        donation_Fan = (RelativeLayout) findViewById(R.id.donation_Fan);
        donation_huiyuan_word = (EditText) findViewById(R.id.donation_huiyuan_word);
        donation_cangpin_wrod = (EditText) findViewById(R.id.donation_cangpin_wrod);
        donation_name = (EditText) findViewById(R.id.donation_name);
        donation_shuliang = (EditText) findViewById(R.id.donation_shuliang);
        donation_danjia = (TextView) findViewById(R.id.donation_danjia);
        donation_duanxin = (EditText) findViewById(R.id.donation_duanxin);
        donation_duan_btn = (TextView) findViewById(R.id.donation_duan_btn);
        donation_zuidae = (TextView) findViewById(R.id.donation_zuidae);
        donation_tijiao = (Button) findViewById(R.id.donation_tijiao);
        donation_weitanchuan = (LinearLayout) findViewById(R.id.donation_weitanchuan);
        donation_weitanchuan_img = (ImageView) findViewById(R.id.donation_weitanchuan_img);
        donation_weitanchuan_text = (TextView) findViewById(R.id.donation_weitanchuan_text);

        donation_tijiao.setOnClickListener(this);

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

        initData();
    }
    List<String> list = new ArrayList<>();

    public void initData(){
        for (int i=0;i<15;i++){
            list.add("2365894==");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.donation_tijiao:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String word = donation_huiyuan_word.getText().toString().trim();
        if (TextUtils.isEmpty(word)) {
            Toast.makeText(this, "请输入对方交易会员代码", Toast.LENGTH_SHORT).show();
            return;
        }

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
        if (TextUtils.isEmpty(shuliang)||Integer.valueOf(shuliang)<=0) {
            Toast.makeText(this, "转赠数量不能为空且不能小于0", Toast.LENGTH_SHORT).show();
            return;
        }

        String duanxin = donation_duanxin.getText().toString().trim();
        if (TextUtils.isEmpty(duanxin)) {
            Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        //设置dialog的样式
        final TakeDonation_Dialog dialog = new TakeDonation_Dialog(this, R.style.dialog_setting);
        MyUtils.getDiaLogDiBu(this,dialog); // 设置dialog弹出框弹出时的动画
        dialog.setTitle("确认转赠信息");
        dialog.setWord(word);
        dialog.setShuLiang(Integer.valueOf(shuliang));
        dialog.setZongJia(donation_danjia.getText().toString());
        dialog.setTakeDonation_Dialog_JieKou(new TakeDonation_Dialog.TakeDonation_Dialog_JieKou() {
            @Override
            public void QuanRen() { // 点击确认
                MyUtils.setToast("正在请求网络。。。");
                dialog.dismiss();
                MyUtils.setObjectAnimator(donation_weitanchuan,
                        donation_weitanchuan_img,
                        donation_weitanchuan_text,
                        weitanchuan_height,
                        true);


            }

            @Override
            public void QuXiao() {// 点击取消
                dialog.dismiss();
            }
        });

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
