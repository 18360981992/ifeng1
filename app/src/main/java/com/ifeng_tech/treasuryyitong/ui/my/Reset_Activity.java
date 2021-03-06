package com.ifeng_tech.treasuryyitong.ui.my;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
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
import com.ifeng_tech.treasuryyitong.bean.my.ChangePwdBean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 重置密码
 */
public class Reset_Activity extends BaseMVPActivity<Reset_Activity, MyPresenter<Reset_Activity>> {

    private RelativeLayout reset_Fan;
    private EditText reset_old;
    private EditText reset_new;
    private EditText reset_queren;
    private Button reset_btn;
    private ImageView reset_weitanchuan_img;
    private TextView reset_weitanchuan_text;
    private LinearLayout reset_weitanchuan;
    private int weitanchuan_height;
    private String news;

    @Override
    public MyPresenter<Reset_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_);
        initView();

        reset_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reset_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(reset_queren.getWindowToken(), 0);

                submit();
            }
        });

        // 失焦后判断两次密码是否一致
        isPass_Old_New(reset_queren,reset_new);
    }

    private void initView() {
        reset_Fan = (RelativeLayout) findViewById(R.id.reset_Fan);
        reset_old = (EditText) findViewById(R.id.reset_old);
        reset_new = (EditText) findViewById(R.id.reset_new);
        reset_queren = (EditText) findViewById(R.id.reset_queren);
        reset_btn = (Button) findViewById(R.id.reset_btn);

        reset_weitanchuan_img = (ImageView) findViewById(R.id.reset_weitanchuan_img);
        reset_weitanchuan_text = (TextView) findViewById(R.id.reset_weitanchuan_text);
        reset_weitanchuan = (LinearLayout) findViewById(R.id.reset_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        reset_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                reset_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = reset_weitanchuan.getMeasuredHeight();
            }
        });
    }


    private void submit() {
        // validate
        String old = reset_old.getText().toString().trim();
        if (TextUtils.isEmpty(old)) {
            Toast.makeText(this, "请输入原登录密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (MyUtils.isPassWord(old)==false) {
            Toast.makeText(this, SP_String.IS_PASS, Toast.LENGTH_SHORT).show();
            return;
        }


        news = reset_new.getText().toString().trim();
        if (TextUtils.isEmpty(news)) {
            Toast.makeText(this, "请输入新登录密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if (MyUtils.isPassWord(news)==false) {
            Toast.makeText(this, SP_String.IS_PASS, Toast.LENGTH_SHORT).show();
            return;
        }

        String queren = reset_queren.getText().toString().trim();
        if (TextUtils.isEmpty(queren)) {
            Toast.makeText(this, "请输入确认新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!news.equals(queren)){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

//        MyUtils.setToast("请求网络。。。");

        HashMap<String, String> map = new HashMap<>();
        map.put("oldPassword",old);
        map.put("newPassword", news);
        map.put("rePassword",queren);

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        setChangePwd(map,aniDialog);
    }

    private void setChangePwd(final HashMap<String, String> map, final ProgressDialog aniDialog) {

        myPresenter.postPreContent(APIs.changePwd, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
//                LogUtils.i("jiba","===="+json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        aniDialog.dismiss();
                        ChangePwdBean changePwdBean = new Gson().fromJson(json, ChangePwdBean.class);
                        // 手机号和密码重新保存
                        DashApplication.edit.putString(SP_String.SHOUJI, changePwdBean.getData().getUser().getMobile())
                                .putString(SP_String.PASS,news)
                                .commit();
                        MyUtils.setObjectAnimator_anquan(reset_weitanchuan,
                                reset_weitanchuan_img,
                                reset_weitanchuan_text,
                                weitanchuan_height,
                                true,"修改成功!");
                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                DashApplication.edit
                                        .putString(SP_String.PASS,news)
                                        .commit();
                                finish();
                            }
                        });
                    }else{
                        aniDialog.dismiss();
                        MyUtils.setObjectAnimator(reset_weitanchuan,
                                reset_weitanchuan_img,
                                reset_weitanchuan_text,
                                weitanchuan_height,
                                false,(String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setObjectAnimator(reset_weitanchuan,
                        reset_weitanchuan_img,
                        reset_weitanchuan_text,
                        weitanchuan_height,
                        false,"修改失败!");
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
