package com.ifeng_tech.treasuryyitong.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.TransferFee_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.NetWorkUtil;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 *
 * @param <V>  v是activity
 * @param <T>  t是p层
 */
public abstract class BaseMVPActivity<V,T extends MyPresenter<V>> extends AppCompatActivity {

    public T myPresenter;
    //将子类共有方法抽成公有，，，因为每一个Activity都会需要new一个P层，所以将new的这个过程，抽成抽象方法才是最佳良策
    public abstract T initPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_mvp);
        myPresenter=initPresenter();

        boolean conn = NetWorkUtil.isConn((Context) this);
        if(!conn){
            NetWorkUtil.showNoNetWorkDlg((Context) this);
        }
    }

    //在获取焦点是将当前对象与p层绑定
    @Override
    protected void onResume() {
        super.onResume();
        myPresenter.attch((V) this);
    }

    //在页面销毁时，调用p层方法，将view对象置空，方便垃圾回收
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPresenter.setonDestroy();
    }


    // 获取转赠手续费   在刚进来调用一次   根据藏品代码再调一次
    public void getShouXuFei(HashMap<String, String> map, final TextView textView,final TextView textView1) {
        myPresenter.postPreContent(APIs.findTransferFee, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        TransferFee_Bean transferFee_bean = new Gson().fromJson(json, TransferFee_Bean.class);
                        double price = transferFee_bean.getData().getTransferFee() * transferFee_bean.getData().getCommonFeeRate();
                        textView.setText("￥"+ DashApplication.decimalFormat.format(price)); // 这里是手续费

                        textView1.setText("最大转赠数量:"+transferFee_bean.getData().getAvailableQty());
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
            }
        });
    }

    // 获取提货手续费   在刚进来调用一次   根据藏品代码再调一次
    public void getTiHuo_ShouXuFei(HashMap<String, String> map, final TextView textView) {
        myPresenter.postPreContent(APIs.findTransferFee, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        TransferFee_Bean transferFee_bean = new Gson().fromJson(json, TransferFee_Bean.class);
                        double price = transferFee_bean.getData().getDeliveryFee() * transferFee_bean.getData().getCommonFeeRate();
                        textView.setText("￥"+ DashApplication.decimalFormat.format(price)); // 这里是手续费
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
            }
        });
    }

    /**
     *  登录
     * @param myInterfaces
     */
    public void setLogin( final MyInterfaces myInterfaces) {
        String shouji = DashApplication.sp.getString(SP_String.SHOUJI,"");
        String pass = DashApplication.sp.getString(SP_String.PASS,"");
        HashMap<String, String> map = new HashMap<>();
        map.put("userName",shouji);
        map.put("password",pass);
        map.put("loginType","0");
        myPresenter.postPreContent(APIs.login, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        myInterfaces.chenggong("");
                    }else{
                        myInterfaces.shibai("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {
                myInterfaces.shibai("");
            }
        });
    }


    /**
     * 获取本地软件版本号
     */
    public static String getLocalVersion() {
        int localVersionCode = 0;
        String localVersionName="";
        try {
            PackageInfo packageInfo = DashApplication.getAppContext()
                    .getPackageManager()
                    .getPackageInfo(DashApplication.getAppContext().getPackageName(), 0);
             localVersionName = packageInfo.versionName;
             localVersionCode = packageInfo.versionCode;
            LogUtils.i("jiba", "本软件的版本号。。" + localVersionName+"."+localVersionCode);
            localVersionName=localVersionName+"."+localVersionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersionName;
    }

}
