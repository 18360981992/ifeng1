package com.ifeng_tech.treasuryyitong.ui.my;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

/**
 *  设置
 */
public class Setting_Activity extends BaseMVPActivity<Setting_Activity,MyPresenter<Setting_Activity>>  {

    private RelativeLayout setting_Fan;
    private RelativeLayout setting_guanyu;
    private RelativeLayout setting_banben;
    private Button setting_btn;
    private TextView setting_banben_code;

    @Override
    public MyPresenter<Setting_Activity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_);
        initView();

        setting_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 点击关于我们
        setting_guanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了关于我们。。。");
                Intent intent = new Intent(Setting_Activity.this, About_My_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 检索版本
        setting_banben.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("检索版本。。。");
            }
        });



        setting_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {

//                MyUtils.setToast("退出登录。。。");
                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Setting_Activity.this, "正在退出...");

                myPresenter.getPreContent(APIs.logout, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                aniDialog.dismiss();
                                DashApplication.sp = getSharedPreferences("ifeng", MODE_PRIVATE);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.clear().commit();
                                setting_JieKou.chuan();
                                finish();
                            }else{
                                aniDialog.dismiss();
                                MyUtils.setToast("退出失败！");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void shibai(String ss) {
                        aniDialog.dismiss();
                        MyUtils.setToast("退出失败！");
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setting_banben_code.setText("当前版本 "+getLocalVersion());
    }

    private void initView() {
        setting_Fan = (RelativeLayout) findViewById(R.id.setting_Fan);
        setting_guanyu = (RelativeLayout) findViewById(R.id.setting_guanyu);
        setting_banben = (RelativeLayout) findViewById(R.id.setting_banben);
        setting_btn = (Button) findViewById(R.id.setting_btn);
        setting_banben_code = (TextView) findViewById(R.id.setting_banben_code);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }

    public interface Setting_JieKou{
        void chuan();
    }
    public static Setting_JieKou setting_JieKou;

    public static void setSetting_JieKou(Setting_JieKou setting_JieKou) {
        Setting_Activity.setting_JieKou = setting_JieKou;
    }
}
