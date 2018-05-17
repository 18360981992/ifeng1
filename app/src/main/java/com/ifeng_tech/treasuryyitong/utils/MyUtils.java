package com.ifeng_tech.treasuryyitong.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mypc on 2018/4/27.
 */

public class MyUtils {

    private static Toast toast;
    public static void setToast(String ss){
        if(toast==null){
            toast=Toast.makeText(DashApplication.getAppContext(),ss,Toast.LENGTH_SHORT);
        }
        toast.setText(ss);
        toast.show();
    }

    /**
     * 判断手机号是否符合规范
     * @param phoneNo 输入的手机号
     * @return
     */
    public static boolean isPhoneNumber(String phoneNo) {
        if (TextUtils.isEmpty(phoneNo)) {
            return false;
        }
        if (phoneNo.length() == 11) {
            for (int i = 0; i < 11; i++) {
                if (!PhoneNumberUtils.isISODigit(phoneNo.charAt(i))) {
                    return false;
                }
            }
            Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" +
                    "|(14[5,7])" +
                    "|(15[^4,\\D])" +
                    "|(17[3,6-8])" +
                    "|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(phoneNo);
            return m.matches();
        }
        return false;
    }

    /**
     * 判断密码的格式 6-20位字母，数字组合密码
     * @param input
     * @return
     */
    public static boolean isNumber(String input){
        boolean matches = input.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$");
        return matches;
    }

    // 简单的判断字符串是否是数字且是15-18位
    public static boolean isShenFengZheng(String ss){
        Pattern pattern = Pattern.compile("[0-9]*");
        int length = ss.length();
        if(pattern.matcher(ss).matches()&length>=15){
            //数字
            return true;
        } else {
            //非数字
            return false;
        }
    }


    //字符串转指定格式时间  "yyyy-MM-dd" 到 "yyyy年MM月dd日"
    public static String getMyDate(String str) {
        return StringToDate(str, "yyyy-MM-dd", "yyyy年MM月dd日");
    }

    //字符串转指定格式时间   "yyyy年MM月dd日" 到 "yyyy-MM-dd"
    public static String getMyDate2(String str) {
        return StringToDate(str, "yyyy年MM月dd日", "yyyy-MM-dd");
    }

    // 时间转换
    public static String StringToDate(String dateStr, String dateFormatStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(dateFormatStr);
        Date date = null;
        try{
            date = sdf.parse(dateStr);
        } catch (Exception e){
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatStr);

        return s.format(date);
    }


    /**
     *
     * @param file  文件
     * @return
     */
    public static byte[] Filebyte(File file)
    {
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 检测
     * @return
     */
    public static String getSDPath(){
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Log.i("wc",
                    "SD card is not avaiable/writeable right now.");
            return null;
        }
        return sdStatus;

    }

//    public static void setSubccedIntent(Intent intent, String title, int img, String text, String btn) {
//        intent.putExtra("title",title)
//                .putExtra("img", img)
//                .putExtra("text",text)
//                .putExtra("btn",btn);
//    }

    // 将自定义dialog 步骤 抽成方法 ====> 普通的dialog弹出  这样做的 好处 省去屏幕适配的代码适配
    public static void getPuTongDiaLog(Context context, Dialog dialog) {
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.show();
        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth());
        dialog.getWindow().setAttributes(params);
    }

    // 将共用的生成dialog步骤抽成方法 ====> 底部滑出
    public static void getDiaLogDiBu(Context context, Dialog dialog) {
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dialog_animation); // 添加动画
        dialog.show();
        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (display.getWidth());
        dialog.getWindow().setAttributes(params);
    }

}
