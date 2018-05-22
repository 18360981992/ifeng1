package com.ifeng_tech.treasuryyitong.ui.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.ImageUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.TakePhotosDialog;

import java.io.File;

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
    };


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

        certification_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        certification_tu_yan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setToast("请求网络，获取图形验证码。。。");
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

        certification_duan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                certification_duan_btn.setText("重新发送" + time + "(s)");
                certification_duan_btn.setEnabled(false);
                h.sendEmptyMessageDelayed(0, 1000);

                MyUtils.setToast("请求网络，获取短信验证码。。。");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 接口回调 选择相册/相机
        setBuyerJieKou(new BuyerJieKou() {
            @Override
            public void xiangce(ImageView img, int XIANGCE) {
                // 启动相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, XIANGCE);
            }

            @Override
            public void xiangji(ImageView img, int XIANGJI) {
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
        // 正面照的请求结果
        if(requestCode==100&resultCode==RESULT_OK){
            Uri uri = data.getData();
            File fileUri = ImageUtils.getFileUri(uri, this); // 将uri转成file
//            imgfront=fileUri.length()+""; // 为正面照的参数大小赋值
//            byte[] filebyte = MyUtils.Filebyte(fileUri);  // 将file转成字节 byte[]
//            imgfrontstr = Base64.encodeToString(filebyte, Base64.DEFAULT); // base64加密  并为参数赋值

            certification_shangchuan_zheng_img.setImageURI(uri);
        }
        if(requestCode==1000&resultCode==RESULT_OK){
            File photos = ImageUtils.getPhotos(data);

//            imgfront=photos.length()+""; // 为正面照的参数大小赋值
//            byte[] filebyte = MyUtlis.Filebyte(photos);  // 将file转成字节 byte[]
//            imgfrontstr = Base64.encodeToString(filebyte, Base64.DEFAULT); // base64加密  并为参数赋值

            Glide.with(this).load(photos).into(certification_shangchuan_zheng_img);
        }

        // 背面照的请求结果
        if(requestCode==200&resultCode==RESULT_OK){
            Uri uri = data.getData();
            File fileUri = ImageUtils.getFileUri(uri, this); // 将uri转成file
//            imgback=fileUri.length()+""; // 为正面照的参数大小赋值
//            byte[] filebyte = MyUtlis.Filebyte(fileUri);  // 将file转成字节 byte[]
//            imgbackstr = Base64.encodeToString(filebyte, Base64.DEFAULT); // base64加密  并为参数赋值
            certification_shangchuan_bei_img.setImageURI(uri);
        }
        if(requestCode==2000&resultCode==RESULT_OK){
            File photos = getPhotos(data);
//            imgback=photos.length()+""; // 为正面照的参数大小赋值
//            byte[] filebyte = MyUtlis.Filebyte(photos);  // 将file转成字节 byte[]
//            imgbackstr = Base64.encodeToString(filebyte, Base64.DEFAULT); // base64加密  并为参数赋值
            Glide.with(this).load(photos).into(certification_shangchuan_bei_img);
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

        MyUtils.setToast("请求网络，进行认证。。。");

        if(true){
            // 根据状态 选择隐藏/显示  1== 认证中 2==认证失败
            Intent intent = new Intent(Certification_Activity.this, ADVP_R_Activity.class);
            intent.putExtra("rengzheng_type",1);
            startActivityForResult(intent, DashApplication.CERTIFICATION_TO_ADVP_req);
        }else{
            Intent intent = new Intent(Certification_Activity.this, ADVP_R_Activity.class);
            intent.putExtra("rengzheng_type",2);
            startActivityForResult(intent, DashApplication.CERTIFICATION_TO_ADVP_req);
        }

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

    public interface BuyerJieKou{
        void xiangce(ImageView img, int XIANGCE);
        void xiangji(ImageView img,int XIANGJI);
    }

    public BuyerJieKou buyerJieKou;

    public void setBuyerJieKou(BuyerJieKou buyerJieKou) {
        this.buyerJieKou = buyerJieKou;
    }
}
