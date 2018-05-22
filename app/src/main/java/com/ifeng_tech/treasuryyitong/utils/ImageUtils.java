package com.ifeng_tech.treasuryyitong.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ifeng_tech.treasuryyitong.utils.MyUtils.setToast;

/**
 *
 * 图片处理工具类
 * Created by kelvin on 16/4/20.
 */
public class ImageUtils {

    /**
     * 删除一条图片Uri
     */
    public static void deleteImageUri(Context context, Uri uri){
        context.getContentResolver().delete(uri, null, null);
    }

    /**
     * 裁剪图片返回
     */
    public static void startPhotoZoom(Context context, Uri uri) {
        int  dp = 500;

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);//输出是X方向的比例
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高，切忌不要再改动下列数字，会卡死
        intent.putExtra("outputX", dp);//输出X方向的像素
        intent.putExtra("outputY", dp);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);//设置为不返回数据
        ((Activity) context).startActivityForResult(intent, 300);
    }

    /**
     * 将uri转成bitmap
     * @param uri
     * @param mContext
     * @return
     */

    public static Bitmap getBitmapFromUri(Uri uri,Context mContext){
        try{
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  将uri 转成 file
     * @param uri
     * @param mContext
     * @return
     */
    public static File getFileUri(Uri uri,Context mContext){
        //将uri路径转成绝对路径
        String[] proj = { MediaStore.Images.Media.DATA };

        Cursor actualimagecursor = ((Activity)mContext).managedQuery(uri,proj,null,null,null);

        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        actualimagecursor.moveToFirst();

        String img_path = actualimagecursor.getString(actual_image_column_index);

        File file = new File(img_path);
        return file;
    }


    // 根据intent来获取所拍摄的照片
    public static File getPhotos(Intent data) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            Log.i("wc","SD card is not avaiable/writeable right now.");
            setToast("没有检测到sd卡！！");
            return null;
        }

        Bundle bundle = data.getExtras();
        Bitmap bitmap = bundle.getParcelable("data");

        FileOutputStream b = null;
        String photoName = getPhotoName();  // 获取刚拍照片的地址
        try {
            b = new FileOutputStream(photoName);
            /**
             *   mBitmap.compress 压缩图片
             *
             *  Bitmap.CompressFormat.PNG   图片的格式
             *   100  图片的质量（0-100）
             *   out  文件输出流
             */
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(photoName);
        return file;
    }

    // 封装照片路径
    public static String getPhotoName() {
        File file = new File("/sdcard/Image/");
        if(!file.exists()){
            file.mkdirs();// 创建文件夹
        }
        String  fileName = "/sdcard/Image/"+getPhotoFileName();
        return fileName;
    }

    // 为刚拍出的照片起名
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

}
