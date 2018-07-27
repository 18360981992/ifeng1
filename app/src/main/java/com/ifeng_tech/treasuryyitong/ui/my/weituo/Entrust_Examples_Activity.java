package com.ifeng_tech.treasuryyitong.ui.my.weituo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

/**
 *  委托查看范例图片
 */
public class Entrust_Examples_Activity extends AppCompatActivity {

    private RelativeLayout entrust_Examples_Fan;
    private WebView entrust_Examples_WebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrust__examples_);
        initView();

        entrust_Examples_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        entrust_Examples_WebView.loadUrl(APIs.fanLie_url);//加载本地路径文件，，url

        entrust_Examples_WebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 下方2行代码是指在当前的webview中跳转到新的url
                view.loadUrl(url);
                return true;
            }
        });//不去跳转到别的浏览器

        WebSettings settings = entrust_Examples_WebView.getSettings();//获得设置页面的权限

        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置javascript弹窗

        settings.setJavaScriptEnabled(true);//是安卓支持js脚本

        entrust_Examples_WebView.setInitialScale(25);//为25%，最小缩放等级
        settings.setSupportZoom(true);//支持缩放网页
        settings.setBuiltInZoomControls(true);//支持缩放网页
        settings.setUseWideViewPort(true);//支持缩放网页

        entrust_Examples_WebView.setWebChromeClient(new WebChromeClient());//使安卓支持网页的弹出框

        //  进度框
        final ProgressDialog aniDialog = MyUtils.getProgressDialog(this, SP_String.JIAZAI);
        //加载进度条
        entrust_Examples_WebView.setWebChromeClient(new WebChromeClient() {
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
        entrust_Examples_Fan = (RelativeLayout) findViewById(R.id.entrust_Examples_Fan);
        entrust_Examples_WebView = (WebView) findViewById(R.id.entrust_Examples_WebView);
    }
}
