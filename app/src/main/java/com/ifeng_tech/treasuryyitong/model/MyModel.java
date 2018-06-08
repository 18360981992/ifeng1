package com.ifeng_tech.treasuryyitong.model;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.interfaces.MyJieKou;
import com.ifeng_tech.treasuryyitong.utils.BaseServer;
import com.ifeng_tech.treasuryyitong.utils.RetrofitFacety;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by mypc on 2018/1/10.
 *
 * model层里，我做了retrofit+RxJava的封装处理，这里就不再展示代码了，向看代码可以到我的博客去看！
 *
 * 地址：http://blog.csdn.net/weixin_40430041/article/details/78988137
 */

public class MyModel {

    private void setLogin_GET(final String url, final MyInterfaces myInterfaces) {
        String shouji = DashApplication.sp.getString(SP_String.SHOUJI,"");
        String pass = DashApplication.sp.getString(SP_String.PASS,"");
        HashMap<String, String> map = new HashMap<>();
        map.put("userName",shouji);
        map.put("password",pass);
        map.put("loginType","0");
        postModContent(APIs.login, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        getModContent(url, new MyInterfaces() {
                            @Override
                            public void chenggong(String json) {
                                myInterfaces.chenggong(json);
                            }

                            @Override
                            public void shibai(String ss) {

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {

            }
        });
    }

    private void setLogin_POST(final String url,final Map<String,String> map1, final MyInterfaces myInterfaces) {
        String shouji = DashApplication.sp.getString(SP_String.SHOUJI,"");
        String pass = DashApplication.sp.getString(SP_String.PASS,"");
        HashMap<String, String> map = new HashMap<>();
        map.put("userName",shouji);
        map.put("password",pass);
        map.put("loginType","0");
        postModContent(APIs.login, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        postModContent(url,map1, new MyInterfaces() {
                            @Override
                            public void chenggong(String json) {
                                myInterfaces.chenggong(json);
                            }

                            @Override
                            public void shibai(String ss) {

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {

            }
        });
    }

    /**
     * model中的get请求方式
     * @param url
     * @param myInterfaces
     */
    public void getModContent(final String url, final MyInterfaces myInterfaces){
        RetrofitFacety.get(url)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("4001")){
                                setLogin_GET(url,myInterfaces);
                            }else{
                                myInterfaces.chenggong(json);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onErroy(String ss) {
                        myInterfaces.shibai(ss);
                    }
                });
    }

    /**
     * model中的post请求
     * @param url
     * @param map
     * @param myInterfaces
     */
    public void postModContent(final String url, final Map<String,String> map, final MyInterfaces myInterfaces){

        RetrofitFacety.post(url,map)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("4001")){
                                setLogin_POST(url,map,myInterfaces);
                            }else{
                                myInterfaces.chenggong(json);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErroy(String ss) {
                        myInterfaces.shibai(ss);
                    }
                });
    }


    /**
     *  M 层的获取图形验证码
     * @param url
     * @param myJieKou
     */
    public void getMod_TuXingYanZheng(final String url,final MyJieKou myJieKou){
        RetrofitFacety.get_img(url)
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        myJieKou.chenggong(bitmap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        myJieKou.shibai("请求失败！");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
