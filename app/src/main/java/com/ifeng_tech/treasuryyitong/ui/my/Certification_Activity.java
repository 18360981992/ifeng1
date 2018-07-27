package com.ifeng_tech.treasuryyitong.ui.my;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.login.SmsCodeBean;
import com.ifeng_tech.treasuryyitong.bean.my.UpLode_bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.interfaces.MyJieKou;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.ImageUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.TakePhotosDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import me.ele.download.FinalHttp;
import me.ele.download.http.AjaxCallBack;
import me.ele.download.http.AjaxParams;

import static com.ifeng_tech.treasuryyitong.utils.ImageUtils.getPhotos;

/**
 * 实名认证
 */
public class Certification_Activity extends BaseMVPActivity<Certification_Activity, MyPresenter<Certification_Activity>> implements View.OnClickListener {

    private RelativeLayout certification_Fan;
    private EditText certification_name;
    private TextView certification_type_text;
    private LinearLayout certification_type_btn;
    private EditText certification_shenfenzheng;
    private ImageView certification_shangchuan_zheng_img;
    private TextView certification_shangchuan_zheng_btn;
    private ImageView certification_shangchuan_bei_img;
    private TextView certification_shangchuan_bei_btn;
    private EditText certification_tu_yan;
    private ImageView certification_tu_yan_btn;
    private EditText certification_duan;
    private TextView certification_duan_btn;
    private Button certification_btn;

    int time = 60;
    Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                time--;
                if (time == 0) {
                    time = 60;
                    certification_duan_btn.setText("点击发送");
                    certification_duan_btn.setEnabled(true);
                } else {
                    certification_duan_btn.setText("重新发送" + time + "(s)");
                    h.sendEmptyMessageDelayed(0, 1000);
                }
            }

        }
    };
    private String shouji;
    String frontUrl="";
    String backUrl="";

    @Override
    public MyPresenter<Certification_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_);
        initView();

        shouji = DashApplication.sp.getString(SP_String.SHOUJI, "");

        certification_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 点击更换图形验证码
        certification_tu_yan_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                //                MyUtils.setToast("请求网络，获取图形验证码。。。");
                myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
                    @Override
                    public void chenggong(Bitmap bitmap) {
                        if(bitmap!=null){
                            certification_tu_yan_btn.setImageBitmap(bitmap);
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        MyUtils.setToast("图形验证码获取失败！");
                    }
                });
            }
        });


        certification_shangchuan_zheng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("正面==调用相机。。。");
                Dialog(certification_shangchuan_zheng_img,100,1000);
            }
        });


        certification_shangchuan_bei_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("背面==调用相机。。。");
                Dialog(certification_shangchuan_bei_img,200,2000);
            }
        });

        // 短信验证
        certification_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String yan = certification_tu_yan.getText().toString().trim();
                if (TextUtils.isEmpty(yan)) {
                    Toast.makeText(Certification_Activity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, String> map = new HashMap<>();
                map.put("verifyCode",yan);
                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Certification_Activity.this, SP_String.JIAZAI);

                myPresenter.postPreContent(APIs.verifyCode, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                certification_duan_btn.setText("重新发送" + time + "(s)");
                                certification_duan_btn.setEnabled(false);
                                h.sendEmptyMessageDelayed(0, 1000);
//                              MyUtils.setToast("请求网络，获取短信验证码。。。");
                                HashMap<String, String> map = new HashMap<>();
                                map.put("mobile", shouji);
                                map.put("codeType","0");  // ("通用", 0)
                                map.put("verifyCode",yan);
                                myPresenter.postPreContent(APIs.getSmsCode, map, new MyInterfaces() {
                                    @Override
                                    public void chenggong(String json) {
                                        SmsCodeBean smCodeBean = new Gson().fromJson(json, SmsCodeBean.class);
                                        if(smCodeBean.getCode().equals("2000")){
                                            MyUtils.setToast("短信发送成功");
                                        }else {
                                            MyUtils.setToast(smCodeBean.getMessage());
                                        }
                                    }
                                    @Override
                                    public void shibai(String ss) {
                                        MyUtils.setToast("短信发送失败");
                                    }
                                });
                            }else{
                                MyUtils.setToast((String) jsonObject.get("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void shibai(String ss) {
                        aniDialog.dismiss();
                        MyUtils.setToast(ss);
                    }
                });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 获取图形验证码
        myPresenter.getPro_TuXingYanZheng(APIs.newImageCode, new MyJieKou() {
            @Override
            public void chenggong(Bitmap bitmap) {
                if(bitmap!=null){
                    certification_tu_yan_btn.setImageBitmap(bitmap);
                }
            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast("图形验证码获取失败！");
            }
        });

        // 接口回调 选择相册/相机
        setBuyerJieKou(new BuyerJieKou() {
            @Override
            public void xiangce(ImageView img, int XIANGCE) {
                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(Certification_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Certification_Activity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                    return;
                }
                // 启动相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, XIANGCE);
            }

            @Override
            public void xiangji(ImageView img, int XIANGJI) {

                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(Certification_Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Certification_Activity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    return;
                }
                // 启动相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,XIANGJI);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 三连跳 回调
        if(requestCode==DashApplication.CERTIFICATION_TO_ADVP_req&&resultCode==DashApplication.CERTIFICATION_TO_ADVP_res){
            finish();
        }
        // 正面照的请求结果  相册
        if(requestCode==100&resultCode==RESULT_OK){
            Uri uri = data.getData();
            File fileUri = ImageUtils.getFileUri(uri, this); // 将uri转成file
            File photos = getPhotos(fileUri); // 将相册中获取到的照片重新选择路径存储
            certification_shangchuan_zheng_img.setBackgroundResource(0);
            Glide.with(this).load(photos).into(certification_shangchuan_zheng_img);
            getUplode(fileUri,1);  // 上传文件
        }
        if(requestCode==1000&resultCode==RESULT_OK){   // 相机
            File photos = getPhotos(data);
            certification_shangchuan_zheng_img.setBackgroundResource(0);
            Glide.with(this).load(photos).into(certification_shangchuan_zheng_img);
            getUplode(photos,1);  // 上传文件
        }

        // 背面照的请求结果  相册
        if(requestCode==200&resultCode==RESULT_OK){
            Uri uri = data.getData();
            File fileUri = ImageUtils.getFileUri(uri, this); // 将uri转成file
            File photos = getPhotos(fileUri); // 将相册中获取到的照片重新选择路径存储
            certification_shangchuan_bei_img.setBackgroundResource(0);
            Glide.with(this).load(photos).into(certification_shangchuan_bei_img);
            getUplode(fileUri,2);  // 上传文件
        }
        if(requestCode==2000&resultCode==RESULT_OK){
            File photos = getPhotos(data);
            certification_shangchuan_bei_img.setBackgroundResource(0);
            Glide.with(this).load(photos).into(certification_shangchuan_bei_img);
            getUplode(photos,2);  // 上传文件
        }

    }

    /**
     * 上传文件
     * @param fileUri  文件类型
     * @param i  // 状态值  1==正面 2==背面
     */
    FinalHttp fh = new FinalHttp();  // 框架的实例化
    private void getUplode(File fileUri, final int i) {
        AjaxParams params = new AjaxParams();
        try {
            params.put("file", fileUri);
            fh.post(APIs.upload, params, new AjaxCallBack<String>() {
                @Override
                public void onSuccess(String json) {
                    super.onSuccess(json);
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String code = (String) jsonObject.get("code");
                        if(code.equals("2000")){
                            UpLode_bean upLode_bean = new Gson().fromJson(json, UpLode_bean.class);
//                            LogUtils.i("jiba","upLode_bean.getData()===="+upLode_bean.getData());
                            if(i==1){
                                frontUrl=upLode_bean.getData();
                            }else{
                                backUrl=upLode_bean.getData();
                            }
                            MyUtils.setToast((String) jsonObject.get("message"));
                        }else{
                            MyUtils.setToast((String) jsonObject.get("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    MyUtils.setToast("上传失败！");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        h.removeMessages(0);
    }

    private void initView() {
        certification_Fan = (RelativeLayout) findViewById(R.id.certification_Fan);
        certification_name = (EditText) findViewById(R.id.certification_name);
        certification_type_text = (TextView) findViewById(R.id.certification_type_text);
        certification_type_btn = (LinearLayout) findViewById(R.id.certification_type_btn);
        certification_shenfenzheng = (EditText) findViewById(R.id.certification_shenfenzheng);
        certification_shangchuan_zheng_img = (ImageView) findViewById(R.id.certification_shangchuan_zheng_img);
//        certification_shangchuan_zheng_btn = (TextView) findViewById(R.id.certification_shangchuan_zheng_btn);
        certification_shangchuan_bei_img = (ImageView) findViewById(R.id.certification_shangchuan_bei_img);
//        certification_shangchuan_bei_btn = (TextView) findViewById(R.id.certification_shangchuan_bei_btn);
        certification_tu_yan = (EditText) findViewById(R.id.certification_tu_yan);
        certification_tu_yan_btn = (ImageView) findViewById(R.id.certification_tu_yan_btn);
        certification_duan = (EditText) findViewById(R.id.certification_duan);
        certification_duan_btn = (TextView) findViewById(R.id.certification_duan_btn);
        certification_btn = (Button) findViewById(R.id.certification_btn);

        certification_btn.setOnClickListener(this);

        SoftHideKeyBoardUtil.assistActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certification_btn:
                // 点击 强制关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(certification_duan.getWindowToken(), 0);

                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String name = certification_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "输入您的真实姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String shenfenzheng = certification_shenfenzheng.getText().toString().trim();
        if (TextUtils.isEmpty(shenfenzheng)) {
            Toast.makeText(this, "输入您的身份证号", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isIDCard(shenfenzheng)==false){
            Toast.makeText(this, "输入的身份证号有误", Toast.LENGTH_SHORT).show();
            return;
        }

        String yan = certification_tu_yan.getText().toString().trim();
        if (TextUtils.isEmpty(yan)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String duan = certification_duan.getText().toString().trim();
        if (TextUtils.isEmpty(duan)) {
            Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something

        /**
         *  从这里的入口永远也不会进到失败的页面，所以不需要
         */
//        MyUtils.setToast("请求网络，进行认证。。。");
        HashMap<String, String> map = new HashMap<>();
        map.put("realName", name);
        map.put("idCard", shenfenzheng);
        map.put("frontUrl", frontUrl);
        map.put("backUrl", backUrl);
        map.put("smsCode", duan);
        map.put("verifyCode", yan);

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        // 设置实名认证 确认按钮
        myPresenter.postPreContent(APIs.applyIdentify, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        // 根据状态 选择隐藏/显示  1== 认证中 2==认证失败
                        Intent intent = new Intent(Certification_Activity.this, ADVP_R_Activity.class);
                        intent.putExtra("rengzheng_type",1);
                        startActivityForResult(intent, DashApplication.CERTIFICATION_TO_ADVP_req);
                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setToast(ss);
            }
        });

    }

    /**
     * // 点击弹出 的dialog框
     * @param img  需要显示照片的imageview
     * @param XIANGCE  相册的请求吗
     * @param XIANGJI  相机的请求吗
     */
    private void Dialog(final ImageView img, final int XIANGCE, final int XIANGJI) {
        //设置dialog的样式
        final TakePhotosDialog dialog = new TakePhotosDialog(this, R.style.dialog_setting);

        MyUtils.getDiaLogDiBu(this,dialog);

        Button touxiag_xiangce = (Button) dialog.findViewById(R.id.touxiag_xiangce);
        Button touxiag_paizhao = (Button) dialog.findViewById(R.id.touxiag_paizhao);
        Button touxiag_quxiao = (Button) dialog.findViewById(R.id.touxiag_quxiao);

        //  点击取消
        touxiag_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // 点击调用相册
        touxiag_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyerJieKou.xiangce(img,XIANGCE);
                dialog.dismiss();
            }
        });
        // 点击调用拍照
        touxiag_paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyerJieKou.xiangji(img,XIANGJI);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }

    public interface BuyerJieKou{
        void xiangce(ImageView img, int XIANGCE);
        void xiangji(ImageView img,int XIANGJI);
    }

    public BuyerJieKou buyerJieKou;

    public void setBuyerJieKou(BuyerJieKou buyerJieKou) {
        this.buyerJieKou = buyerJieKou;
    }
}
