package com.ifeng_tech.treasuryyitong.service;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 *
 * Created by liqy on 2018/1/28.
 *
 * 京东
 */

public interface SearchApi {

    /**
     *  retrofit的get请求
     * @param url
     * @return
     */
    @GET
    Observable<String> get(@Url String url);

    /**
     * 商机头条 轮播图
     * @return
     * "App/BusinessOpportunity/news_list"
     */
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, String> map);



    @FormUrlEncoded
    @POST
    Call<ResponseBody> postFormFieldMap(@Url String url,@FieldMap Map<String , String> map);


    @GET
    Observable<ResponseBody> downloadPicFromNet_Get(@Url String fileUrl);


}
