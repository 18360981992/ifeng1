package com.ifeng_tech.treasuryyitong.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.view.TakeCommonDialog;


/**
 * Created by Administrator on 2017/10/20/020.
 */

public class NetWorkUtil {

    public static boolean isConn(Context context){
        boolean bisConnFlag=false;
        //1.获取网络连接的管理对象
        ConnectivityManager conManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //2.通过管理者对象拿到网络的信息
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if(network!=null){
            //3.网络状态是否可用的返回值
            bisConnFlag=network.isAvailable();
        }
        return bisConnFlag;
    }


    /**
     * 如果没有网络 弹出dialog对话框,,,是否进入设置网络的页面
     * @param context
     */
    public static void showNoNetWorkDlg(final Context context) {

        // 使用自定义的dialog框
        final TakeCommonDialog takeCommonDialog = new TakeCommonDialog(context, R.style.dialog_setting,"当前无网络,是否去设置?");
        MyUtils.getPuTongDiaLog(context,takeCommonDialog);
        takeCommonDialog.setCommonJieKou(new TakeCommonDialog.CommonJieKou() {
            @Override
            public void quxiao() {
                takeCommonDialog.dismiss();
            }

            @Override
            public void queren() {
                takeCommonDialog.dismiss();
                // 跳转到系统的网络设置界面
                Intent intent = null;
                // 先判断当前系统版本
                if(android.os.Build.VERSION.SDK_INT > 10){  // 3.0以上
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                }else{
                    intent = new Intent();
                    intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                }
                context.startActivity(intent);

            }
        });

//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder
////                .setIcon(R.mipmap.ic_launcher)         //
//                .setTitle("警告")            //
//                .setMessage("当前无网络,是否去设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // 跳转到系统的网络设置界面
//                Intent intent = null;
//                // 先判断当前系统版本
//                if(android.os.Build.VERSION.SDK_INT > 10){  // 3.0以上
//                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
//                }else{
//                    intent = new Intent();
//                    intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
//                }
//                context.startActivity(intent);
//
//            }
//        }).setNegativeButton("取消", null).show();

    }
}
