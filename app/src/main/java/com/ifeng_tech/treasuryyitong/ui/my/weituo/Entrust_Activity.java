package com.ifeng_tech.treasuryyitong.ui.my.weituo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
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
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.my.UpLode_bean;
import com.ifeng_tech.treasuryyitong.bean.weituo.Entrust_ClientGoodsByClientCode_Bean;
import com.ifeng_tech.treasuryyitong.bean.weituo.Entrust_Client_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.ImageUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoftHideKeyBoardUtil;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.Search_Pop_View;
import com.ifeng_tech.treasuryyitong.view.TakeCalendar_Dialog2;
import com.ifeng_tech.treasuryyitong.view.TakePhotosDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ele.download.FinalHttp;
import me.ele.download.http.AjaxCallBack;
import me.ele.download.http.AjaxParams;
import me.ele.download.http.HttpHandler;

import static com.ifeng_tech.treasuryyitong.utils.ImageUtils.getPhotos;

/**
 * 委托单的提交
 */
public class Entrust_Activity extends BaseMVPActivity<Entrust_Activity,MyPresenter<Entrust_Activity>>  {

    private RelativeLayout entrust_Fan;
    private TextView entrust_pingtai;
    private ImageView entrust_jiantou;
    private EditText entrust_tihuo_danhao;
    private EditText entrust_pass;
    private TextView entrust_rili;
    private LinearLayout entrust_rili_img;
    private EditText entrust_name;
    private EditText entrust_shouji;
    private ImageView entrust_img;
    private TextView entrust_fanlie;
    private TextView entrust_xiazai;
    private Button entrust_btn;
    private ImageView entrust_weitanchuan_img;
    private TextView entrust_weitanchuan_text;
    private LinearLayout entrust_weitanchuan;
    private int measuredWidth;
    private int weitanchuan_height;

    String idCardUrl="";  // 上传图片路径

    List<Entrust_Client_Bean.DataBean.ClientBean> list = new ArrayList<>();
    List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> list_code=new ArrayList<>();
    private Search_Pop_View search_pop_view;
    private Search_Pop_View search_pop_view1;
    // 设置下载路径
    private  final String WORD_PATH = Environment.getExternalStorageDirectory() + "/Download/fanli_wendang";
    private TextView entrust_cname;
    private EditText entrust_shuliang;
    private int cname_measuredWidth;
    private String clientCode="";
    private ImageView entrust_cname_jiantou;
    private String orgCode;
    private String goodsCode;
    private ScrollView entrust_scrollView;

    @Override
    public MyPresenter<Entrust_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }
    public interface Entrust_Activity_JieKou{
        void chuan( );
    }
    Entrust_Activity_JieKou entrust_Activity_JieKou;

    public void setEntrust_Activity_JieKou(Entrust_Activity_JieKou entrust_Activity_JieKou) {
        this.entrust_Activity_JieKou = entrust_Activity_JieKou;
    }

    public interface Entrust_Client_Activity_JieKou{
        void chuan( );
    }
    Entrust_Client_Activity_JieKou entrust_Client_Activity_JieKou;

    public void setEntrust_Client_Activity_JieKou(Entrust_Client_Activity_JieKou entrust_Client_Activity_JieKou) {
        this.entrust_Client_Activity_JieKou = entrust_Client_Activity_JieKou;
    }

    int pageNum=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrust_);
        initView();

        // 返回
        entrust_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 选择平台  entrust_Activity_JieKou.chuan();
        entrust_pingtai.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("","");
                if(search_pop_view==null){
                    final ProgressDialog aniDialog = MyUtils.getProgressDialog(Entrust_Activity.this, SP_String.JIAZAI);
                    myPresenter.postPreContent(APIs.getClient, map, new MyInterfaces() {
                        @Override
                        public void chenggong(String json) {
                            aniDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String code = (String) jsonObject.get("code");
                                if(code.equals("2000")){
                                    Entrust_Client_Bean entrust_client_bean = new Gson().fromJson(json, Entrust_Client_Bean.class);
                                    List<Entrust_Client_Bean.DataBean.ClientBean> zilist = entrust_client_bean.getData().getClient();
                                    if(zilist.size()>0){
                                        list.clear();
                                        list.addAll(zilist);
                                        entrust_Activity_JieKou.chuan();
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
                            aniDialog.dismiss();
                            MyUtils.setToast(ss);
                        }
                    });
                }else{
                    if(list.size()>0){
                        entrust_Activity_JieKou.chuan();
                    }
                }
            }
        });

        // 藏品名称点击
        entrust_cname.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                if(clientCode.equals("")){
                    MyUtils.setToast("请先选择交货平台!");
                }else{
                    Map<String, String> map = new HashMap<>();
                    map.put("clientCode",clientCode);
                    map.put("pageNum",pageNum+"");
                    map.put("pageSize","10");
//                    if(search_pop_view1==null){
                        final ProgressDialog aniDialog = MyUtils.getProgressDialog(Entrust_Activity.this, SP_String.JIAZAI);
                        myPresenter.postPreContent(APIs.getClientGoodsByClientCode, map, new MyInterfaces() {
                            @Override
                            public void chenggong(String json) {
                                aniDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(json);
                                    String code = (String) jsonObject.get("code");
                                    if(code.equals("2000")){
                                        Entrust_ClientGoodsByClientCode_Bean entrust_clientGoodsByClientCode_bean = new Gson().fromJson(json, Entrust_ClientGoodsByClientCode_Bean.class);
                                        List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> zilist = entrust_clientGoodsByClientCode_bean.getData().getList();
                                        list_code.clear();
                                        if(zilist.size()>0){
                                            list_code.addAll(zilist);
                                            entrust_Client_Activity_JieKou.chuan();
                                        }else{
                                            MyUtils.setToast("该平台中暂无藏品");
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
                                aniDialog.dismiss();
                                MyUtils.setToast(ss);
                            }
                        });
//                    }else{
//                        if(list_code.size()>0){
//                            entrust_Client_Activity_JieKou.chuan();
//                        }
//                    }
                }
            }
        });

        // 点击日历
        entrust_rili.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                showPopupWindow(entrust_rili_img);
            }
        });

        // 点击相机/相册
        entrust_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog(entrust_img,100,1000);
            }
        });

        // 查看示例
        entrust_fanlie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrust_Activity.this, Entrust_Examples_Activity.class);
                startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 点击下载示例
        entrust_xiazai.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                getDownLoad();
            }
        });

        // 提交申请的按钮
        entrust_btn.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 点击 强制关闭软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(entrust_shouji.getWindowToken(), 0);
                submit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 选择平台的接口回调
        setEntrust_Activity_JieKou(new Entrust_Activity_JieKou() {
            @Override
            public void chuan() {
                if(search_pop_view!=null&&search_pop_view.isShowing()){
                    search_pop_view.dismiss();
                }else if(search_pop_view!=null&&!search_pop_view.isShowing()) {
                    search_pop_view.showAsDropDown(entrust_pingtai);
                }else{
                    search_pop_view = new Search_Pop_View(Entrust_Activity.this);
                    search_pop_view.Search_Pop_View_String(Entrust_Activity.this,measuredWidth,list,entrust_jiantou);
                    search_pop_view.setBackgroundDrawable(new BitmapDrawable());
                    search_pop_view.showAsDropDown(entrust_pingtai);
                }

                search_pop_view.setSearch_Pop_JieKou_String(new Search_Pop_View.Search_Pop_JieKou_String() {
                    @Override
                    public void chuan(List<Entrust_Client_Bean.DataBean.ClientBean> list, int postion) {
                        if(!list.get(postion).getClientName().equals(entrust_pingtai.getText().toString().trim())){
                            entrust_cname.setText("");
                        }
                        entrust_pingtai.setText(list.get(postion).getClientName()+"");
                        clientCode = list.get(postion).getClientCode()+"";
                        orgCode = list.get(postion).getClientCode()+"";  // 平台code

                    }
                });
            }
        });

        // 选择藏品名称 回调接口
        setEntrust_Client_Activity_JieKou(new Entrust_Client_Activity_JieKou() {
            @Override
            public void chuan() {

                if(search_pop_view1!=null&&search_pop_view1.isShowing()){
                    search_pop_view1.dismiss();
                }else if(search_pop_view1!=null&&!search_pop_view1.isShowing()) {
                    search_pop_view1.showAsDropDown(entrust_cname);
                }else{
                    search_pop_view1 = new Search_Pop_View(Entrust_Activity.this);
                    search_pop_view1.Search_Pop_View_Code(Entrust_Activity.this,cname_measuredWidth,list_code,entrust_cname_jiantou);
                    search_pop_view1.setBackgroundDrawable(new BitmapDrawable());
                    search_pop_view1.showAsDropDown(entrust_cname);
                }

                search_pop_view1.setSearch_Pop_Code_JieKou_String(new Search_Pop_View.Search_Pop_Code_JieKou_String() {
                    @Override
                    public void chuan(List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> list, int postion) {
                        entrust_cname.setText(list.get(postion).getCommodityName()+"");
                        goodsCode = list.get(postion).getCommodityCode()+"";  // 藏品code
                    }
                });

                search_pop_view1.setSearch_Pop_Code_Shua_JieKou(new Search_Pop_View.Search_Pop_Code_Shua_JieKou() {
                    @Override
                    public void chuan(final PullToRefreshScrollView search_pop_shua_pulltoscroll) {
                        final Map<String, String> map = new HashMap<>();
                        pageNum++;
                        map.put("clientCode",clientCode);
                        map.put("pageNum",pageNum+"");
                        map.put("pageSize","10");
                        myPresenter.postPreContent(APIs.getClientGoodsByClientCode, map, new MyInterfaces() {
                            @Override
                            public void chenggong(String json) {
                                try {
                                    JSONObject jsonObject = new JSONObject(json);
                                    String code = (String) jsonObject.get("code");
                                    if(code.equals("2000")){
                                        Entrust_ClientGoodsByClientCode_Bean entrust_clientGoodsByClientCode_bean = new Gson().fromJson(json, Entrust_ClientGoodsByClientCode_Bean.class);
                                        String pageNum = map.get("pageNum");
                                        if(Integer.valueOf(pageNum) <= entrust_clientGoodsByClientCode_bean.getData().getPageInfo().getPageCount()){
                                            List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> zilist = entrust_clientGoodsByClientCode_bean.getData().getList();
                                            if(zilist.size()>0){
                                                list_code.addAll(zilist);
//                                                entrust_Client_Activity_JieKou.chuan();
                                                search_pop_view1.setPop_Shua_Adapter(Entrust_Activity.this,list_code);
                                            }
                                        }else{
                                            MyUtils.setToast("没有更多数据了");
                                        }

                                    }else{
                                        MyUtils.setToast((String) jsonObject.get("message"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }finally {
                                    search_pop_shua_pulltoscroll.onRefreshComplete();
                                }
                            }

                            @Override
                            public void shibai(String ss) {
                                search_pop_shua_pulltoscroll.onRefreshComplete();
                                MyUtils.setToast(ss);
                            }
                        });
                    }
                });
            }
        });


        // 接口回调 选择相册/相机
        setBuyerJieKou(new BuyerJieKou() {
            @Override
            public void xiangce(ImageView img, int XIANGCE) {
                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(Entrust_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Entrust_Activity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
//                    return;
                }
                // 启动相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, XIANGCE);
            }

            @Override
            public void xiangji(ImageView img, int XIANGJI) {

                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(Entrust_Activity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Entrust_Activity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
        // 正面照的请求结果  相册
        if(requestCode==100&resultCode==RESULT_OK){
            Uri uri = data.getData();
            File fileUri = ImageUtils.getFileUri(uri, this); // 将uri转成file
            File photos = getPhotos(fileUri); // 将相册中获取到的照片重新选择路径存储
            entrust_img.setBackgroundResource(0);
            Glide.with(this).load(photos).into(entrust_img);
            getUplode(photos);  // 上传文件
        }
        if(requestCode==1000&resultCode==RESULT_OK){   // 相机
            File photos = getPhotos(data); // 将相机中拍摄到的照片重新选择路径存储
            entrust_img.setBackgroundResource(0);
            Glide.with(this).load(photos).into(entrust_img);
            getUplode(photos);  // 上传文件
        }

    }

    // 弹出日历的pop框
    private void showPopupWindow(View v) {

        final TakeCalendar_Dialog2 dialog = new TakeCalendar_Dialog2(Entrust_Activity.this, R.style.dialog_setting);
        MyUtils.getDiaLogDiBu(Entrust_Activity.this,dialog);

        dialog.setOnItemClick(new TakeCalendar_Dialog2.OnItemClick() {
            @Override
            public void onItemClick(String date) {
//                MyUtils.setToast("点击了：" + date);
                entrust_rili.setText(date);
            }
        });

    }

    private void initView() {
        entrust_scrollView = findViewById(R.id.entrust_ScrollView);
        entrust_Fan = (RelativeLayout) findViewById(R.id.entrust_Fan);
        entrust_pingtai = (TextView) findViewById(R.id.entrust_pingtai);
        entrust_cname = (TextView) findViewById(R.id.entrust_cname);
        entrust_cname_jiantou = (ImageView) findViewById(R.id.entrust_cname_jiantou);
        entrust_shuliang = (EditText) findViewById(R.id.entrust_shuliang);
        entrust_jiantou = (ImageView) findViewById(R.id.entrust_jiantou);
        entrust_tihuo_danhao = (EditText) findViewById(R.id.entrust_tihuo_danhao);
        entrust_pass = (EditText) findViewById(R.id.entrust_pass);
        entrust_rili = (TextView) findViewById(R.id.entrust_rili);
        entrust_rili_img = (LinearLayout) findViewById(R.id.entrust_rili_img);
        entrust_name = (EditText) findViewById(R.id.entrust_name);
        entrust_shouji = (EditText) findViewById(R.id.entrust_shouji);
        entrust_img = (ImageView) findViewById(R.id.entrust_img);
        entrust_fanlie = (TextView) findViewById(R.id.entrust_fanlie);
        entrust_xiazai = (TextView) findViewById(R.id.entrust_xiazai);
        entrust_btn = (Button) findViewById(R.id.entrust_btn);
        entrust_weitanchuan_img = (ImageView) findViewById(R.id.entrust_weitanchuan_img);
        entrust_weitanchuan_text = (TextView) findViewById(R.id.entrust_weitanchuan_text);
        entrust_weitanchuan = (LinearLayout) findViewById(R.id.entrust_weitanchuan);

        SoftHideKeyBoardUtil.assistActivity(this);

        //通过设置监听来获取控件的宽度
        entrust_pingtai.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                entrust_pingtai.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取平台的宽度
                measuredWidth = entrust_pingtai.getMeasuredWidth();
                cname_measuredWidth = entrust_cname.getMeasuredWidth();
            }
        });


        //通过设置监听来获取 微弹窗 控件的高度
        entrust_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                entrust_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = entrust_weitanchuan.getMeasuredHeight();
            }
        });

    }


    private void submit() {
        // validate
        String pingtai = entrust_pingtai.getText().toString().trim();
        if (TextUtils.isEmpty(pingtai)) {
            Toast.makeText(this, "请选择平台", Toast.LENGTH_SHORT).show();
            return;
        }
        String cname = entrust_cname.getText().toString().trim();
        if (TextUtils.isEmpty(cname)) {
            Toast.makeText(this, "请选择藏品名称", Toast.LENGTH_SHORT).show();
            return;
        }
        String shuliang = entrust_shuliang.getText().toString().trim();
        if (TextUtils.isEmpty(shuliang)) {
            Toast.makeText(this, "请输入数量", Toast.LENGTH_SHORT).show();
            return;
        }
        if(Integer.valueOf(shuliang)==0){
            MyUtils.setToast("数量不能小于0");
            return;
        }

        String danhao = entrust_tihuo_danhao.getText().toString().trim();
        if (TextUtils.isEmpty(danhao)) {
            Toast.makeText(this, "请输入提货单号", Toast.LENGTH_SHORT).show();
            return;
        }

        String pass = entrust_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "请输入提货密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String rili = entrust_rili.getText().toString().trim();
        if (TextUtils.isEmpty(rili)) {
            Toast.makeText(this, "请选择提货日期", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = entrust_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String shouji = entrust_shouji.getText().toString().trim();
        if (TextUtils.isEmpty(shouji)) {
            Toast.makeText(this, "请输入联系方式", Toast.LENGTH_SHORT).show();
            return;
        }

        if(MyUtils.isPhoneNumber(shouji)==false){
            MyUtils.setToast("请输入正确的手机号");
            return;
        }

        if(idCardUrl.equals("")){
            MyUtils.setToast("请选择身份信息上传");
            return;
        }

        entrust_scrollView.smoothScrollTo(0,0); // scrollview滚动到顶部
        // TODO validate success, do something
        Map<String, String> map = new HashMap<>();
        map.put("orgCode",orgCode);
        map.put("goodsCode",goodsCode);
        map.put("amount",shuliang);
        map.put("deliveryNo",danhao);
        map.put("deliveryPwd",pass);
        map.put("deliveryDate",rili);
        map.put("deliveryName",name);
        map.put("deliveryTel",shouji);
        map.put("idCardUrl",idCardUrl);

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

        myPresenter.postPreContent(APIs.entrustedStorage, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        MyUtils.setObjectAnimator_anquan(entrust_weitanchuan,
                                entrust_weitanchuan_img,
                                entrust_weitanchuan_text,
                                weitanchuan_height,
                                true,"委托成功！");

                        MyUtils.setMyUtils_jieKou(new MyUtils.MyUtils_JieKou() {
                            @Override
                            public void chuan() {
                                finish();
                            }
                        });
                    }else{
                        MyUtils.setObjectAnimator_anquan(entrust_weitanchuan,
                                entrust_weitanchuan_img,
                                entrust_weitanchuan_text,
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
                MyUtils.setObjectAnimator_anquan(entrust_weitanchuan,
                        entrust_weitanchuan_img,
                        entrust_weitanchuan_text,
                        weitanchuan_height,
                        false,ss);
            }
        });

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

                            idCardUrl=upLode_bean.getData();

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

    private void getDownLoad() {
        if (ActivityCompat.checkSelfPermission(Entrust_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Entrust_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
            return;
        }
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
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
