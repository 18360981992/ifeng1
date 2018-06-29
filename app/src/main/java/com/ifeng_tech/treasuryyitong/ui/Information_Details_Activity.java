package com.ifeng_tech.treasuryyitong.ui;

import android.Manifest;
import android.app.AlertDialog;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.SelectAdvertise_Bean;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;

/**
 * 资讯详情
 */
public class Information_Details_Activity extends BaseMVPActivity<Information_Details_Activity, MyPresenter<Information_Details_Activity>> {

    private RelativeLayout information_Details_Fan;
    private WebView webView;
    private ProgressBar information_Details_ProgressBar;

    String url = "file:///android_asset/test.html";


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
            String id = getIntent().getStringExtra("id");
//            LogUtils.i("jiba","==="+APIs.infodetail+""+id);

            webView.loadUrl(APIs.infodetail+""+id);//加载本地路径文件，，url
        }else{
            webView.loadUrl(imagesBean.getImgeLink());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 下方2行代码是指在当前的webview中跳转到新的url
                view.loadUrl(url);
                return true;
            }
        });//不去跳转到别的浏览器

        WebSettings settings = webView.getSettings();//获得设置页面的权限

        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置javascript弹窗

        settings.setJavaScriptEnabled(true);//是安卓支持js脚本

        webView.setInitialScale(25);//为25%，最小缩放等级
        settings.setSupportZoom(true);//支持缩放网页
        settings.setBuiltInZoomControls(true);//支持缩放网页
        settings.setUseWideViewPort(true);//支持缩放网页

        webView.setWebChromeClient(new WebChromeClient());//使安卓支持网页的弹出框

        //加载进度条
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    information_Details_ProgressBar.setVisibility(View.GONE);
                } else {
                    information_Details_ProgressBar.setVisibility(View.VISIBLE);
                    information_Details_ProgressBar.setProgress(newProgress);
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
        information_Details_ProgressBar = (ProgressBar) findViewById(R.id.information_Details_ProgressBar);
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
