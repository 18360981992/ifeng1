package com.ifeng_tech.treasuryyitong.ui.my.cangku;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.NetWorkUtil;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import java.io.InputStream;

/**
 * 区块链详情页
 */
public class Shop_Detailed_Datail_Activity extends BaseMVPActivity<Shop_Detailed_Datail_Activity, MyPresenter<Shop_Detailed_Datail_Activity>> {

    private WebView webView;
    private String id;
    private ProgressDialog aniDialog;

    @Override
    public MyPresenter<Shop_Detailed_Datail_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__detailed__datail_);
        initView();
        id = getIntent().getStringExtra("id");

        String url= APIs.queryInventory_Detailed+""+id;
//        String url=APIs.queryInventory_Detailed+"5b3b1fae6f92e8b9b7bdee3c";
        LogUtils.i("wc","Shop_Detailed_Datail_Activity===="+url);
        webView.loadUrl(url);
        //  进度框
        aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebSettings settings = webView.getSettings();//获得设置页面的权限
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置javascript弹窗
        settings.setJavaScriptEnabled(true);//是安卓支持js脚本
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH); //提高渲染的优先级
        settings.setBlockNetworkImage(true); // 把图片加载放在最后来加载渲染

        settings.setSupportZoom(true);//支持缩放网页
        settings.setBuiltInZoomControls(true);//支持缩放网页
        settings.setUseWideViewPort(true);//支持缩放网页
        settings.setLoadWithOverviewMode(true);
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
         // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
         */
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        // 设置默认字体大小
        settings.setTextZoom(100);

        // 应用可以有数据库
        settings.setDatabaseEnabled(true);
        // 根据网络连接情况，设置缓存模式，
        if (NetWorkUtil.isConn(Shop_Detailed_Datail_Activity.this)) {
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
        });//不去跳转到别的浏览器


        webView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);  // webview硬件加速。
        webView.setInitialScale(25);  //为25%，最小缩放等级
        webView.setWebChromeClient(new WebChromeClient());//使安卓支持网页的弹出框

        //加载进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    aniDialog.dismiss();
                }
            }
        });
    }

    private void initView() {
         View shop_Detailed_Datail_view = (View) findViewById(R.id.shop_Detailed_Datail_view);
        webView = (WebView) findViewById(R.id.shop_Detailed_Datail_WebView);

        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) shop_Detailed_Datail_view.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        linearParams.height = getStatusBarHeight(Shop_Detailed_Datail_Activity.this);// 控件的宽强制设成30
//        LogUtils.i("wc","状态栏高度===="+getStatusBarHeight(Shop_Detailed_Datail_Activity.this));
        shop_Detailed_Datail_view.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
    }

    // 获取状态栏高度
    private int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

}
