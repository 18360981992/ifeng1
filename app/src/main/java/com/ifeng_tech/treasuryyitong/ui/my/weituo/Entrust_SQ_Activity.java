package com.ifeng_tech.treasuryyitong.ui.my.weituo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.my.UpLode_bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.ImageUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.TakeCalendar_Dialog2;
import com.ifeng_tech.treasuryyitong.view.TakePhotosDialog;
import com.ifeng_tech.treasuryyitong.view.Take_Entrust_SQ_Dialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import me.ele.download.FinalHttp;
import me.ele.download.http.AjaxCallBack;
import me.ele.download.http.AjaxParams;
import me.ele.download.http.HttpHandler;

import static com.ifeng_tech.treasuryyitong.utils.ImageUtils.getPhotos;

public class Entrust_SQ_Activity extends BaseMVPActivity<Entrust_SQ_Activity, MyPresenter<Entrust_SQ_Activity>> {

    private RelativeLayout entrust_SQ_Fan;
    private EditText entrust_SQ_tihuo_danhao;
    private EditText entrust_SQ_pass;
    private EditText entrust_SQ_shuliang;
    private TextView entrust_SQ_riqi;
    private ImageView entrust_SQ_img;
    private TextView entrust_SQ_fanlie;
    private TextView entrust_SQ_xiazai;
    private Button entrust_SQ_btn;
    private ImageView entrust_SQ_weitanchuan_img;
    private TextView entrust_SQ_weitanchuan_text;
    private LinearLayout entrust_SQ_weitanchuan;
    public String orgCode="";
    public String goodsCode="";
    public String idCardUrl="";
    public int weitanchuan_height=0;
    // 设置下载路径
    private  final String WORD_PATH = Environment.getExternalStorageDirectory() + "/Download/fanli_wendang";
    public String goodsName="";
    public String orgName="";
    public ScrollView entrust_sq_scrollView;

    @Override
    public MyPresenter<Entrust_SQ_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrust__sq_);
        initView();

        Intent intent = getIntent();
        orgCode = intent.getStringExtra("orgCode");
        goodsCode = intent.getStringExtra("goodsCode");
        orgName = intent.getStringExtra("orgName");
        goodsName = intent.getStringExtra("goodsName");
        // 返回
        entrust_SQ_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 点击日历
        entrust_SQ_riqi.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                showPopupWindow();
            }
        });

        // 点击相机/相册
        entrust_SQ_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog(entrust_SQ_img,100,1000);
            }
        });

        // 查看示例
        entrust_SQ_fanlie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrust_SQ_Activity.this, Entrust_Examples_Activity.class);
                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 点击下载示例
        entrust_SQ_xiazai.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                getDownLoad();
            }
        });

        // 点击提交
        entrust_SQ_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击 强制关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(entrust_SQ_shuliang.getWindowToken(), 0);
                submit();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // edittext默认不显示软键盘，只有edittext被点击时，软键盘才弹出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // 接口回调 选择相册/相机
        setBuyerJieKou(new BuyerJieKou() {
            @Override
            public void xiangce(ImageView img, int XIANGCE) {
                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(Entrust_SQ_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Entrust_SQ_Activity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, DashApplication.WRITE_EXTERNAL_STORAGE);
                }else{
                    // 启动相册
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, XIANGCE);
                }

            }

            @Override
            public void xiangji(ImageView img, int XIANGJI) {
                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(Entrust_SQ_Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Entrust_SQ_Activity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, DashApplication.CAMERA);
                }else{
                    // 启动相机
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,XIANGJI);
                }

            }
        });
    }

    // 弹出日历的pop框
    private void showPopupWindow() {

        final TakeCalendar_Dialog2 dialog = new TakeCalendar_Dialog2(Entrust_SQ_Activity.this, R.style.dialog_setting);
        MyUtils.getDiaLogDiBu(Entrust_SQ_Activity.this,dialog);

        dialog.setOnItemClick(new TakeCalendar_Dialog2.OnItemClick() {
            @Override
            public void onItemClick(String date) {
//                MyUtils.setToast("点击了：" + date);
                entrust_SQ_riqi.setText(date);
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

    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==DashApplication.WRITE_EXTERNAL_STORAGE){  // 写入权限
            if(grantResults[0]!=-1){
                // 启动相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }else{
                return;
            }
        }

        if(requestCode==DashApplication.CAMERA){  // 吊起相机权限
            if(grantResults[0]!=-1&&grantResults[1]!=-1){
                // 启动相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1000);
            }else{
                return;
            }
        }

        if(requestCode==1001){  // 读取权限
            if(grantResults[0]!=-1){
                // 下载文档
                getXiaZai();
            }else{
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 正面照的请求结果  相册
        if(requestCode==100&resultCode==RESULT_OK){
            Uri uri = data.getData();
            File fileUri = ImageUtils.getFileUri(uri, this); // 将uri转成file
            File photos = getPhotos(fileUri); // 将相册中获取到的照片重新选择路径存储
            entrust_SQ_img.setBackgroundResource(0);
            Glide.with(this).load(photos).into(entrust_SQ_img);
            getUplode(photos);  // 上传文件
        }
        if(requestCode==1000&resultCode==RESULT_OK){   // 相机
            File photos = getPhotos(data); // 将相机中拍摄到的照片重新选择路径存储
            entrust_SQ_img.setBackgroundResource(0);
            Glide.with(this).load(photos).into(entrust_SQ_img);
            getUplode(photos);  // 上传文件
        }
    }

    /**
     * 上传文件
     * @param fileUri  文件类型
     * @param i  // 状态值  1==正面 2==背面
     */
    FinalHttp fh = new FinalHttp();  // 框架的实例化
    private void getUplode(File fileUri) {
        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);
        AjaxParams params = new AjaxParams();
        try {
            params.put("file", fileUri);
            fh.post(APIs.upload, params, new AjaxCallBack<String>() {
                @Override
                public void onSuccess(String json) {
                    super.onSuccess(json);
                    aniDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String code = (String) jsonObject.get("code");
                        if(code.equals("2000")){
                            UpLode_bean upLode_bean = new Gson().fromJson(json, UpLode_bean.class);
//                            LogUtils.i("jiba","upLode_bean.getData()===="+upLode_bean.getData());

                            idCardUrl = upLode_bean.getData();

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
                    aniDialog.dismiss();
                    MyUtils.setToast("上传失败！");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  下载文件
     */
    private void getDownLoad() {
        if (ActivityCompat.checkSelfPermission(Entrust_SQ_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Entrust_SQ_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
        }else{
            // 下载文档
            getXiaZai();
        }

    }

    // 下载文档
    private void getXiaZai() {
        // 点击下载范例文档
        // 调用download方法开始下载
        final File dirPath=new File(WORD_PATH);

        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
        File mUpApkFile=new File(dirPath, "baoku_az.rtf");
        // 如果文件存在 先删除 避免报错
        if(mUpApkFile.isFile()){
            mUpApkFile.delete(); // 删除所有文件
        }
        //true:断点续传 false:不断点续传（全新下载）
        HttpHandler<File> httpHandler = fh.download(APIs.download, mUpApkFile.getAbsolutePath(), true, new AjaxCallBack<File>() {
            @Override
            public void onSuccess(File t) {
                super.onSuccess(t);
                MyUtils.setToast("下载完成!路径为"+t.toString());
            }

            @Override
            public void onLoading(long count, long current) {
//                Log.i("jiba", "onLoading: 走了。。。");
                double cou = count / 100;
                double curr = current / cou;

//                        pd.setMax(100);
//                        pd.setProgress((int) (curr));
                super.onLoading(100, (long) curr);
            }

            @Override
            public void onStart() {
                super.onStart();
                MyUtils.setToast("开始下载...");
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                MyUtils.setToast("下载失败!");
            }
        });
    }

    private void initView() {
        entrust_sq_scrollView = (ScrollView) findViewById(R.id.entrust_SQ_ScrollView);
        entrust_SQ_Fan = (RelativeLayout) findViewById(R.id.entrust_SQ_Fan);
        entrust_SQ_tihuo_danhao = (EditText) findViewById(R.id.entrust_SQ_tihuo_danhao);
        entrust_SQ_pass = (EditText) findViewById(R.id.entrust_SQ_pass);
        entrust_SQ_shuliang = (EditText) findViewById(R.id.entrust_SQ_shuliang);
        entrust_SQ_riqi = (TextView) findViewById(R.id.entrust_SQ_riqi);
        entrust_SQ_img = (ImageView) findViewById(R.id.entrust_SQ_img);
        entrust_SQ_fanlie = (TextView) findViewById(R.id.entrust_SQ_fanlie);
        entrust_SQ_xiazai = (TextView) findViewById(R.id.entrust_SQ_xiazai);
        entrust_SQ_btn = (Button) findViewById(R.id.entrust_SQ_btn);
        entrust_SQ_weitanchuan_img = (ImageView) findViewById(R.id.entrust_SQ_weitanchuan_img);
        entrust_SQ_weitanchuan_text = (TextView) findViewById(R.id.entrust_SQ_weitanchuan_text);
        entrust_SQ_weitanchuan = (LinearLayout) findViewById(R.id.entrust_SQ_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取 微弹窗 控件的高度
        entrust_SQ_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                entrust_SQ_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = entrust_SQ_weitanchuan.getMeasuredHeight();
            }
        });


    }

    private void submit() {
        // validate

        if (TextUtils.isEmpty(orgName)||TextUtils.isEmpty(orgCode)) {
            Toast.makeText(this, "请选择平台", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(goodsCode)||TextUtils.isEmpty(goodsName)) {
            Toast.makeText(this, "请选择藏品名称", Toast.LENGTH_SHORT).show();
            return;
        }

        final String danhao = entrust_SQ_tihuo_danhao.getText().toString().trim();
        if (TextUtils.isEmpty(danhao)) {
            Toast.makeText(this, "请输入提货单号", Toast.LENGTH_SHORT).show();
            return;
        }

        final String pass = entrust_SQ_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入提货密码", Toast.LENGTH_SHORT).show();
            return;
        }

        final String shuliang = entrust_SQ_shuliang.getText().toString().trim();
        if (TextUtils.isEmpty(shuliang)) {
            Toast.makeText(this, "请输入数量", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Integer.valueOf(shuliang)==0){
            MyUtils.setToast("数量不能小于0");
            return;
        }
        if(shuliang.length()>10){
            MyUtils.setToast("超过最大提货数量");
            return;
        }

        final String rili = entrust_SQ_riqi.getText().toString().trim();
        if (TextUtils.isEmpty(rili)) {
            Toast.makeText(this, "请选择提货日期", Toast.LENGTH_SHORT).show();
            return;
        }

        if(idCardUrl.equals("")){
            MyUtils.setToast("请上传委托书");
            return;
        }

        // TODO validate success, do something

        //设置dialog的样式
        final Take_Entrust_SQ_Dialog dialog = new Take_Entrust_SQ_Dialog(Entrust_SQ_Activity.this, R.style.dialog_setting);
        MyUtils.getDiaLogDiBu(Entrust_SQ_Activity.this, dialog); // 设置dialog弹出框弹出时的动画
        dialog.setTake_Entrust_SQ_Dialog_ptname(orgName);
        dialog.setTake_Entrust_SQ_Dialog_goodsname(goodsName);
        dialog.setTake_Entrust_SQ_Dialog_danhao(danhao);
        dialog.setTake_Entrust_SQ_Dialog_shuliang(shuliang);
        dialog.setTake_Entrust_SQ_Dialog_riqi(rili);

        dialog.setTake_Entrust_SQ_Dialog_JieKou(new Take_Entrust_SQ_Dialog.Take_Entrust_SQ_Dialog_JieKou() {
            @Override
            public void chuan() {
                entrust_sq_scrollView.smoothScrollTo(0,0); // scrollview滚动到顶部
                Map<String, String> map = new HashMap<>();
                map.put("orgCode",orgCode);
                map.put("goodsCode",goodsCode);
                map.put("amount",shuliang);
                map.put("deliveryNo",danhao);
                map.put("deliveryPwd",pass);
                map.put("deliveryDate",rili);
//                map.put("deliveryName",name);
//                map.put("deliveryTel",shouji);
                map.put("idCardUrl",idCardUrl);
                //  进度框
                final ProgressDialog aniDialog = MyUtils.getProgressDialog(Entrust_SQ_Activity.this, SP_String.JIAZAI);
                myPresenter.postPreContent(APIs.entrustedStorage, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        aniDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                MyUtils.setObjectAnimator_anquan(entrust_SQ_weitanchuan,
                                        entrust_SQ_weitanchuan_img,
                                        entrust_SQ_weitanchuan_text,
                                        weitanchuan_height,
                                        true,"委托成功！");

                                MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                                    @Override
                                    public void chuan() {
                                        setResult(DashApplication.CP_TO_SQ_res);
                                        finish();
                                    }
                                });
                            }else{
                                MyUtils.setObjectAnimator_anquan(entrust_SQ_weitanchuan,
                                        entrust_SQ_weitanchuan_img,
                                        entrust_SQ_weitanchuan_text,
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
                        MyUtils.setObjectAnimator_anquan(entrust_SQ_weitanchuan,
                                entrust_SQ_weitanchuan_img,
                                entrust_SQ_weitanchuan_text,
                                weitanchuan_height,
                                false,ss);
                    }
                });
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
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
