package com.ifeng_tech.treasuryyitong.ui.my;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Collect_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.TakeDonation_Dialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 *
 *  征集  表单填写
 *
 */
public class Collect_Activity extends BaseMVPActivity<Collect_Activity,MyPresenter<Collect_Activity>> {

    private RelativeLayout collect_ac_Fan;
    private EditText collect_ac_pingtai;
    private EditText collect_ac_zhanghu;
    private EditText collect_ac_shouji;
    private EditText collect_ac_cword;
    private EditText collect_ac_name;
    private EditText collect_ac_shuliang;
    private TextView collect_ac_shuliang_zuida;
    private TextView collect_ac_daijia;
    private Button collect_ac_tijiao;

    private LinearLayout collect_ac_weitanchuan;
    private ImageView collect_ac_weitanchuan_img;
    private TextView collect_ac_weitanchuan_text;
    private int weitanchuan_height;
    private EditText collect_ac_yewu_pass;
    private double upsideFee;   // 上盘费的单价
    private int max_num;

    String collectId="";  // 征集id
    String goodsId="";
    @Override
    public MyPresenter<Collect_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_);
        initView();

        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);

        // 模拟 控制页面回显
        Intent intent = getIntent();
        Collect_Bean.DataBean.ListBean collectBean = (Collect_Bean.DataBean.ListBean) intent.getSerializableExtra("CollectBean");
        max_num = intent.getIntExtra("MAX_NUM", 0);  // 获取最大转赠数量
        collectId=collectBean.getId()+"";
        goodsId=collectBean.getGoodsId()+"";

        if(collectBean!=null){
            if(collectBean.getAgencyName().equals("")||collectBean.getAgencyName()==null){
                collect_ac_pingtai.setText("福利特");
            }else{
                collect_ac_pingtai.setText(collectBean.getAgencyName());
            }
            collect_ac_pingtai.setSelection(collect_ac_pingtai.length());

            collect_ac_cword.setText(collectBean.getGoodsCode());
            collect_ac_name.setText(collectBean.getGoodsName());
            collect_ac_shuliang_zuida.setText("最大转赠数量:"+max_num);

            upsideFee = collectBean.getUpsideFee();    // 上盘费的单价
            collect_ac_daijia.setText("￥"+DashApplication.decimalFormat.format(upsideFee));
        }

        // 点击返回
        collect_ac_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 数量输入的监听
        collect_ac_shuliang.addTextChangedListener(new TextWatcher() {
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
                        collect_ac_daijia.setText("￥0.00");
                    }else{
                        String format = DashApplication.decimalFormat.format(integer * upsideFee);// 这里的单价是从bean类中直接获取
                        collect_ac_daijia.setText("￥"+format);
                    }
                }else{
                    collect_ac_daijia.setText("￥0.00");
                }
            }
        });


        // 征集提交按钮
        collect_ac_tijiao.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                submit();
            }
        });

    }

    private void initView() {
        collect_ac_Fan = (RelativeLayout) findViewById(R.id.collect_ac_Fan);
        collect_ac_pingtai = (EditText) findViewById(R.id.collect_ac_pingtai);
        collect_ac_zhanghu = (EditText) findViewById(R.id.collect_ac_zhanghu);
        collect_ac_shouji = (EditText) findViewById(R.id.collect_ac_shouji);
        collect_ac_cword = (EditText) findViewById(R.id.collect_ac_cword);
        collect_ac_name = (EditText) findViewById(R.id.collect_ac_name);
        collect_ac_shuliang = (EditText) findViewById(R.id.collect_ac_shuliang);
        collect_ac_shuliang_zuida = (TextView) findViewById(R.id.collect_ac_shuliang_zuida);
        collect_ac_daijia = (TextView) findViewById(R.id.collect_ac_daijia);
        collect_ac_yewu_pass = (EditText) findViewById(R.id.collect_ac_yewu_pass);

        collect_ac_tijiao = (Button) findViewById(R.id.collect_ac_tijiao);
        collect_ac_weitanchuan = (LinearLayout) findViewById(R.id.collect_ac_weitanchuan);
        collect_ac_weitanchuan_img = (ImageView) findViewById(R.id.collect_ac_weitanchuan_img);
        collect_ac_weitanchuan_text = (TextView) findViewById(R.id.collect_ac_weitanchuan_text);

        //通过设置监听来获取 微弹窗 控件的高度
        collect_ac_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                collect_ac_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = collect_ac_weitanchuan.getMeasuredHeight();
            }
        });


    }



    private void submit() {
        // validate
        String pingtai = collect_ac_pingtai.getText().toString().trim();
        if (TextUtils.isEmpty(pingtai)) {
            Toast.makeText(this, "请输入转赠平台", Toast.LENGTH_SHORT).show();
            return;
        }

        final String zhanghu = collect_ac_zhanghu.getText().toString().trim();
        if (TextUtils.isEmpty(zhanghu)) {
            Toast.makeText(this, "账户为选择平台注册的账户", Toast.LENGTH_SHORT).show();
            return;
        }

        final String shouji = collect_ac_shouji.getText().toString().trim();
        if (TextUtils.isEmpty(shouji)) {
            Toast.makeText(this, "手机号为转赠平台绑定手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isPhoneNumber(shouji)==false){
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String cword = collect_ac_cword.getText().toString().trim();
        if (TextUtils.isEmpty(cword)) {
            Toast.makeText(this, "请输入藏品代码", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = collect_ac_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入藏品名称", Toast.LENGTH_SHORT).show();
            return;
        }

        final String shuliang = collect_ac_shuliang.getText().toString().trim();
        if (TextUtils.isEmpty(shuliang)||Integer.valueOf(shuliang)<=0) {
            Toast.makeText(this, "转赠数量不能为空且不能小于0", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Integer.valueOf(shuliang)>max_num){
            MyUtils.setToast("转赠的数量大于最大转赠数量");
            return;
        }

        final String yewu_pass = collect_ac_yewu_pass.getText().toString().trim();
        if (TextUtils.isEmpty(yewu_pass)) {
            Toast.makeText(this, "请输入业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(yewu_pass.length()<6){
            Toast.makeText(this, "请输入6-12位的业务密码", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something

        //设置dialog的样式
        final TakeDonation_Dialog dialog = new TakeDonation_Dialog(this, R.style.dialog_setting);
        MyUtils.getDiaLogDiBu(this,dialog);
        dialog.setTitle("确认转赠信息");
        //dialog.setFeiName("上盘费");
        dialog.setWord(zhanghu);
        dialog.setShuLiang(Integer.valueOf(shuliang));
        dialog.setZongJia(collect_ac_daijia.getText().toString());
        dialog.setTakeDonation_Dialog_JieKou(new TakeDonation_Dialog.TakeDonation_Dialog_JieKou() {
            @Override
            public void QuanRen() { // 点击确认
//                MyUtils.setToast("正在请求网络。。。");

                HashMap<String, String> map = new HashMap<>();
                map.put("oppAccountCode",zhanghu);
                map.put("mobile",shouji);
                map.put("collectId",collectId);
                map.put("amount",shuliang);
                map.put("goodsId",goodsId);
                map.put("businessPwd",yewu_pass);

                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Collect_Activity.this, SP_String.JIAZAI);

                myPresenter.postPreContent(APIs.applyCollect, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                dialog.dismiss();
                                MyUtils.setObjectAnimator(collect_ac_weitanchuan,
                                        collect_ac_weitanchuan_img,
                                        collect_ac_weitanchuan_text,
                                        weitanchuan_height,
                                        true,"征集成功！");
                            }else{
                                dialog.dismiss();
                                MyUtils.setObjectAnimator(collect_ac_weitanchuan,
                                        collect_ac_weitanchuan_img,
                                        collect_ac_weitanchuan_text,
                                        weitanchuan_height,
                                        false,(String) jsonObject.get("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        dialog.dismiss();
                        aniDialog.dismiss();
                        MyUtils.setObjectAnimator(collect_ac_weitanchuan,
                                collect_ac_weitanchuan_img,
                                collect_ac_weitanchuan_text,
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
}
