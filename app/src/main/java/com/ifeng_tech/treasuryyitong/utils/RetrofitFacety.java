package com.ifeng_tech.treasuryyitong.utils;

import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.service.SearchApi;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mypc on 2018/1/5.
 *
 * http://www.yulin520.com/
 */

public class RetrofitFacety {

    /**
     *
     * @param file 设置数据缓存的路径
     * @param url  接口路劲
     * @param mvpJieKou  回调数据的接口
     */

    //使全局就一个OKHttpClient对象
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .cookieJar(new CookiesManager())
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
//            .addInterceptor(new ApplictionInterceptor())
            .build();
    //使全局就一个Retrofit对象,设置基础Url
    public static SearchApi apiService = new Retrofit.Builder()
            .baseUrl(APIs.debugApi)
            //使我们能高度自定义转化器
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClient)
            //把 以前的 call 转化成 Observable,这是Retrofit与RxJava结合使用的关键
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            .build().create(SearchApi.class);

    /**
     *  retrofit的get请求
     * @param url
     * @return
     */
    public static Observable<String> get(String url) {
        return apiService.get(url)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        LogUtils.i("wc","这是处理缓存本地操作==="+s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * retrofit的post请求
     * @param url
     * @param map
     * @return
     */
    public static Observable<String> post(String url, Map<String,String> map){
        return apiService.post(url,map)
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        LogUtils.i("wc","===这是个什么");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    //参数拦截器
//    public static class ApplictionInterceptor implements Interceptor {
//
//     private String getSign(Map<String, String> map) throws IOException {
//         String signature = SignUtils.getSignature(map, APIs.PRIVATE_KEY);
//         return signature;
//     }
//
//     @Override
//     public Response intercept(Chain chain) throws IOException {
//         //获取到请求
//         Request request = chain.request();
//         //获取请求的方式
//         String method = request.method();
//         //获取请求的路径
//         String oldUrl = request.url().toString();
//
//         Log.e("---拦截器",request.url()+"---"+request.method()+"--"+request.header("User-agent"));
//
//         //要添加的公共参数...map
//         Map<String,String> map = new HashMap<>();
//         // 获取时间搓 （精确到秒）
//         long time = new Date().getTime() / 1000;
//         // 将时间搓加入集合
//         map.put("timestamp",String.valueOf(time));
//         //根据时间搓生成签名的公共参数
//         String sign = getSign(map);
//        //将签名加入集合
//         map.put("sign",sign);
//
//         if ("GET".equals(method)){
//
//             StringBuilder stringBuilder = new StringBuilder();//创建一个stringBuilder
//
//             stringBuilder.append(oldUrl);
//
//             if (oldUrl.contains("?")){
//                 //?在最后面....2类型
//                 if (oldUrl.indexOf("?") == oldUrl.length()-1){
//
//                 }else {
//                     //3类型...拼接上&
//                     stringBuilder.append("&");
//                 }
//             }else {
//                 //不包含? 属于1类型,,,先拼接上?号
//                 stringBuilder.append("?");
//             }
//
//             //添加公共参数....
//             for (Map.Entry<String,String> entry: map.entrySet()) {
//                 //拼接
//                 stringBuilder.append(entry.getKey())
//                         .append("=")
//                         .append(entry.getValue())
//                         .append("&");
//             }
//
//             //删掉最后一个&符号
//             if (stringBuilder.indexOf("&") != -1){
//                 stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"));
//             }
//
//             String newUrl = stringBuilder.toString();//新的路径
//
//             //拿着新的路径重新构建请求
//             request = request.newBuilder()
//                     .url(newUrl)
//                     .build();
//
//
//         }else if ("POST".equals(method)){
//             //先获取到老的请求的实体内容
//             RequestBody oldRequestBody = request.body();//....之前的请求参数,,,需要放到新的请求实体内容中去
//
//             //如果请求调用的是上面doPost方法
//             if (oldRequestBody instanceof FormBody){
//                 FormBody oldBody = (FormBody) oldRequestBody;
//
//                 //构建一个新的请求实体内容
//                 FormBody.Builder builder = new FormBody.Builder();
//                 //1.添加老的参数
//                 for (int i=0;i<oldBody.size();i++){
//                     builder.add(oldBody.name(i),oldBody.value(i));
//                 }
//                 //2.添加公共参数
//                 for (Map.Entry<String,String> entry:map.entrySet()) {
//
//                     builder.add(entry.getKey(),entry.getValue());
//                 }
//
//                 FormBody newBody = builder.build();//新的请求实体内容
//
//                 //构建一个新的请求
//                 request = request.newBuilder()
//                         .url(oldUrl)
//                         .post(newBody)
//                         .build();
//             }
//
//
//         }
//
//
//         Response response = chain.proceed(request);
//
//         return response;
//     }
// }

}
