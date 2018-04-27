package com.ifeng_tech.treasuryyitong.model;


import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.utils.BaseServer;
import com.ifeng_tech.treasuryyitong.utils.RetrofitFacety;

import java.util.Map;

/**
 * Created by mypc on 2018/1/10.
 *
 * model层里，我做了retrofit+RxJava的封装处理，这里就不再展示代码了，向看代码可以到我的博客去看！
 *
 * 地址：http://blog.csdn.net/weixin_40430041/article/details/78988137
 */

public class MyModel {

    /**
     * model中的get请求方式
     * @param url
     * @param myInterfaces
     */
    public void getModContent(String url, final MyInterfaces myInterfaces){
        RetrofitFacety.get(url)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        myInterfaces.chenggong(json);
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
    public void postModContent(String url, Map<String,String> map, final MyInterfaces myInterfaces){

        RetrofitFacety.post(url,map)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        myInterfaces.chenggong(json);
                    }

                    @Override
                    public void onErroy(String ss) {
                        myInterfaces.shibai(ss);
                    }
                });
    }
}
