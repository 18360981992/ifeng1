package com.ifeng_tech.treasuryyitong.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.SelectAdvertise_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.NetWorkUtil;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import java.io.InputStream;

/**
 * 资讯详情
 */
public class Information_Details_Activity extends BaseMVPActivity<Information_Details_Activity, MyPresenter<Information_Details_Activity>> {

    private RelativeLayout information_Details_Fan;
    private WebView webView;

    String url = "file:///android_asset/test.html";
    private ProgressDialog aniDialog;


    @Override
    public MyPresenter<Information_Details_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information__details_);
        initView();

        information_Details_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SelectAdvertise_Bean.DataBean.ListBean imagesBean = (SelectAdvertise_Bean.DataBean.ListBean) getIntent().getSerializableExtra("SelectAdvertise_Bean.DataBean.ListBean");

        if(imagesBean==null){
            String type = getIntent().getStringExtra("type");
            if(type.equals("all")){
                String id = getIntent().getStringExtra("id");
                String index = getIntent().getStringExtra("index");
                String mainId = getIntent().getStringExtra("mainId");
                if(!mainId.equals("147")){
                    String subId = getIntent().getStringExtra("subId");
                    String zi_infodetail = APIs.zi_infodetail(id, index, subId);
                    LogUtils.i("wc","zi_infodetail===="+zi_infodetail);
                    webView.loadUrl(zi_infodetail);//加载本地路径文件，，url
                }else{
                    String zhu_infodetail = APIs.zhu_infodetail(id, index);
                    LogUtils.i("wc","zhu_infodetail===="+zhu_infodetail);
                    webView.loadUrl(zhu_infodetail);//加载本地路径文件，，url
                }
            }else{
                String id = getIntent().getStringExtra("id");
                String index = getIntent().getStringExtra("index");
                String home_infodetail = APIs.home_infodetail(id, type, index);
                LogUtils.i("wc","home_infodetail===="+home_infodetail);
                webView.loadUrl(home_infodetail);//加载本地路径文件，，url
            }
        }else{
            webView.loadUrl(imagesBean.getImgeLink());
        }

        //  进度框
        aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);

    }

    @Override
    protected void onResume() {
        super.onResume();

        final WebSettings settings = webView.getSettings();//获得设置页面的权限
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置javascript弹窗
        settings.setJavaScriptEnabled(true);//是安卓支持js脚本
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH); //提高渲染的优先级
        settings.setBlockNetworkImage(true); // 把图片加载放在最后来加载渲染

//        settings.setAppCacheEnabled(true);  // 开启缓存
//        settings.setDatabaseEnabled(true);  // 开启缓存数据库

        settings.setSupportZoom(true);//支持缩放网页
        settings.setBuiltInZoomControls(true);//支持缩放网页
        settings.setUseWideViewPort(true);//支持缩放网页
        settings.setDomStorageEnabled(true);//打开DOM存储API
        settings.setAppCacheMaxSize(1024*1024*10);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        settings.setAppCachePath(appCachePath);
        settings.setAppCacheEnabled(true);

        /*
        有三个值：
        1.NARROW_COLUMNS：可能的话使所有列的宽度不超过屏幕宽度；
        2.NORMAL：正常显示不做任何渲染；
        3.SINGLE_COLUMN：把所有内容放大webview等宽的一列中。
        WebSettings.LayoutAlgorithm.SINGLE_COLUMN
         */
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置默认字体大小
        settings.setDefaultFontSize(17);
        settings.setTextZoom(100);   // 设置页面字体的百分比  用于屏幕适配

        // 应用可以有数据库
        settings.setDatabaseEnabled(true);
        // 根据网络连接情况，设置缓存模式，
        if (NetWorkUtil.isConn(Information_Details_Activity.this)) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);// 根据cache-control决定是否从网络上取数据
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 先查找缓存，没有的情况下从网络获取。
        }
        // 可以读取文件缓存(manifest生效)
        settings.setAllowFileAccess(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 下方2行代码是指在当前的webview中跳转到新的url
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                view.getSettings().setBlockNetworkImage(false);
                super.onPageFinished(view, url);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url)
            {
                if (url.contains("[tag]"))
                {
                    String localPath = url.replaceFirst("^http.*[tag]\\]", "");
                    try
                    {
                        InputStream is = getApplicationContext().getAssets().open(localPath);
                        LogUtils.i("wc", "shouldInterceptRequest: localPath " + localPath);
                        String mimeType = "text/javascript";
                        if (localPath.endsWith("css"))
                        {
                            mimeType = "text/css";
                        }
                        return new WebResourceResponse(mimeType, "UTF-8", is);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        return null;
                    }
                }
                else
                {
                    return null;
                }

            }

        });//不去跳转到别的浏览器   并缓存javascript

        webView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);  // webview硬件加速。
        webView.setInitialScale(25);//为25%，最小缩放等级
        webView.setWebChromeClient(new WebChromeClient());//使安卓支持网页的弹出框

        //加载进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    settings.setBlockNetworkImage(false);
                    aniDialog.dismiss();
                }
            }

        });

        // 设置JavascriptInterface
        // javainterface实际就是一个普通的java类，里面是我们本地实现的java代码
        // 将object 传递给webview，并指定别名，这样js脚本就可以通过我们给的这个别名来调用我们的方法
        // 在代码中，TestInterface是实例化的对象，testInterface是这个对象在js中的别名
        webView.addJavascriptInterface(new TestInterface(Information_Details_Activity.this), "testInterface");

    }

    private void initView() {
        information_Details_Fan = (RelativeLayout) findViewById(R.id.information_Details_Fan);
        webView = (WebView) findViewById(R.id.information_Details_WebView);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }

    /**
     * Js调用的JavascriptInterface
     */
    public class TestInterface {

        Context context;

        public TestInterface(Context context) {
            this.context = context;
        }

        /**
         * 因为安全问题，在Android4.2以后(如果应用的android:targetSdkVersion数值为17+)
         * JS只能访问带有 @JavascriptInterface注解的Java函数。
         */
        @JavascriptInterface
        public void startCall() {

            AlertDialog.Builder builder = new AlertDialog.Builder(Information_Details_Activity.this);
            builder.setTitle("是否确认拨打电话？");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 10086));

                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Information_Details_Activity.this,new String[]{Manifest.permission.CALL_PHONE}, 0);
                        return;
                    }
                    context.startActivity(intent);

                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();
        }

        @JavascriptInterface
        public void showToast(String content) {
            Toast.makeText(Information_Details_Activity.this, "js调用了java函数并传递了参数：" + content, Toast.LENGTH_SHORT).show();
        }
    }

}
