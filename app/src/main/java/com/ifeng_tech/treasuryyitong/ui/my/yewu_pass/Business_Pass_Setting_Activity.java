package com.ifeng_tech.treasuryyitong.ui.my.yewu_pass;

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

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
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
 *  业务密码设置 需求变动后的
 */
public class Business_Pass_Setting_Activity extends BaseMVPActivity<Business_Pass_Setting_Activity, MyPresenter<Business_Pass_Setting_Activity>> {

    private RelativeLayout business_Pass_Setting_Fan;
    private EditText business_Pass_Setting_new_pass;
    private EditText business_Pass_Setting_zaici_pass;
    private Button business_Pass_Setting_btn;
    private ImageView business_Pass_Setting_weitanchuan_img;
    private TextView business_Pass_Setting_weitanchuan_text;
    private LinearLayout business_Pass_Setting_weitanchuan;
    private String email;
    private int weitanchuan_height;

    @Override
    public MyPresenter<Business_Pass_Setting_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business__pass__setting_);
        initView();

        business_Pass_Setting_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 强制关闭输入框
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(business_Pass_Setting_zaici_pass.getWindowToken(), 0);
                submit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        email = getIntent().getStringExtra("email");  // 获取邮箱号，用于本地更新数据

    }

    private void initView() {
        business_Pass_Setting_Fan = (RelativeLayout) findViewById(R.id.business_Pass_Setting_Fan);
        business_Pass_Setting_new_pass = (EditText) findViewById(R.id.business_Pass_Setting_new_pass);
        business_Pass_Setting_zaici_pass = (EditText) findViewById(R.id.business_Pass_Setting_zaici_pass);
        business_Pass_Setting_btn = (Button) findViewById(R.id.business_Pass_Setting_btn);
        business_Pass_Setting_weitanchuan_img = (ImageView) findViewById(R.id.business_Pass_Setting_weitanchuan_img);
        business_Pass_Setting_weitanchuan_text = (TextView) findViewById(R.id.business_Pass_Setting_weitanchuan_text);
        business_Pass_Setting_weitanchuan = (LinearLayout) findViewById(R.id.business_Pass_Setting_weitanchuan);

        // 解决键盘挡住输入框
        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        business_Pass_Setting_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                business_Pass_Setting_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = business_Pass_Setting_weitanchuan.getMeasuredHeight();
            }
        });
    }

    private void submit() {
        // validate
        String pass = business_Pass_Setting_new_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(pass.length()!=6){
            Toast.makeText(this, "请输入6位的交易密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String zaipass = business_Pass_Setting_zaici_pass.getText().toString().trim();
        if (TextUtils.isEmpty(zaipass)) {
            Toast.makeText(this, "请再次输入业务密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!pass.equals(zaipass)){
            MyUtils.setToast("两次密码输入不一致！");
            return;
        }

        // TODO validate success, do something
        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);
        HashMap<String, String> map = new HashMap<>();
        map.put("businessPassword",pass);
        map.put("email",email);
        map.put("examineBusinessPwd",zaipass);
        myPresenter.postPreContent(APIs.modifyOrResetBusinessPassword, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        MyUtils.setObjectAnimator_anquan(business_Pass_Setting_weitanchuan,
                                business_Pass_Setting_weitanchuan_img,
                                business_Pass_Setting_weitanchuan_text,
                                weitanchuan_height,
                                true, "设置成功!");

                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                DashApplication.edit
                                        .putString(SP_String.ISUSERYEWUPASS,"0")  // 更改业务密码的状态值
                                        .putString(SP_String.EMAIL,email)
                                        .commit();
                                setResult(DashApplication.EMAIL2_TO_YEWU_res);
                                finish();
                            }
                        });
                    }else{
                        MyUtils.setObjectAnimator(business_Pass_Setting_weitanchuan,
                                business_Pass_Setting_weitanchuan_img,
                                business_Pass_Setting_weitanchuan_text,
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
                MyUtils.setObjectAnimator(business_Pass_Setting_weitanchuan,
                        business_Pass_Setting_weitanchuan_img,
                        business_Pass_Setting_weitanchuan_text,
                        weitanchuan_height,
                        false, "设置失败!");
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
