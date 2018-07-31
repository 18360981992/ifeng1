package com.ifeng_tech.treasuryyitong.ui.my.bangzhu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.NetWorkUtil;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import java.io.InputStream;

public class Help_Dails_Activity extends BaseMVPActivity<Help_Dails_Activity, MyPresenter<Help_Dails_Activity>> {

    private RelativeLayout help_dails_Fan;
    private WebView webView;
    public String infodetail;
    public ProgressDialog aniDialog;

    @Override
    public MyPresenter<Help_Dails_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__dails_);
        initView();

        help_dails_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        int erjid = intent.getIntExtra("erjid", 0);
        int index = intent.getIntExtra("index", 0);
        int mainId = intent.getIntExtra("mainId", 0);
        infodetail = APIs.infodetail(erjid, index, mainId);
        webView.loadUrl(infodetail);
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
        if (NetWorkUtil.isConn(Help_Dails_Activity.this)) {
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
    }

    private void initView() {
        help_dails_Fan = (RelativeLayout) findViewById(R.id.help_dails_Fan);
        webView = (WebView) findViewById(R.id.help_dails_WebView);
    }
}
