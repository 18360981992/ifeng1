package com.ifeng_tech.treasuryyitong.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.Message_Lists_Bean;
import com.ifeng_tech.treasuryyitong.utils.BaseServer;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.RetrofitFacety;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.edit_message_xitong;

/**
 * Created by zzt on 2018/4/17.
 *
 * 心跳机制
 */

public class HeartbeatService extends Service {

    private static int time_xitong=3;
    private static int time_chongzhi=3;
    private static int time_anquuan=3;
    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){           // 系统
                time_xitong=time_xitong+3;
                if(time_xitong>=30) time_xitong=3;  // 监测心跳 间隔不易太长，已10次为一轮回，也就是30秒
                getXiTong();
            }else if(msg.what==1){      // 充值
                time_chongzhi=time_chongzhi+3;
                if(time_chongzhi>=30) time_chongzhi=3;  // 监测心跳 间隔不易太长，已10次为一轮回，也就是30秒
                getChongZhi();
            }else if(msg.what==2){     // 安全
                time_anquuan=time_anquuan+3;
                if(time_anquuan>=30) time_anquuan=3;  // 监测心跳 间隔不易太长，已10次为一轮回，也就是30秒
                getAnQuan();
            }

        }
    };

    public interface HearbeatJieKou{
        void hearbeatChuan(int type,int num);
    }

    public static HearbeatJieKou hearbeatJieKou;

    public static void setHearbeatJieKou(HearbeatJieKou hearbeatJieKou) {
        HeartbeatService.hearbeatJieKou = hearbeatJieKou;
    }


    public interface Hearbeat_Home_JieKou{
        void hearbeat_Home_Chuan(int num);
    }

    public static Hearbeat_Home_JieKou hearbeat_Home_JieKou;

    public static void setHearbeat_Home_JieKou(Hearbeat_Home_JieKou hearbeat_Home_JieKou) {
        HeartbeatService.hearbeat_Home_JieKou = hearbeat_Home_JieKou;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getXiTong(); // 首次监测 心跳

        getChongZhi();

        getAnQuan();

        return super.onStartCommand(intent, flags, startId);
    }



    private void getXiTong() {
        Map<String, String> map = new HashMap<>();
        map.put("start","0");
        map.put("number","10");
        map.put("messageType","0");
        RetrofitFacety.post(APIs.getMessageList,map)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                Message_Lists_Bean message_lists_bean = new Gson().fromJson(json, Message_Lists_Bean.class);

                                int totalNum = message_lists_bean.getData().getPageInfo().getTotalNum();
                                // 比较消息的总数目
//                                LogUtils.i("jiba","time_xitong==数目=="+ DashApplication.sp_message_xitong.getInt(SP_String.NEWS_XITONG_NUM,0));
                                if( totalNum== DashApplication.sp_message_xitong.getInt(SP_String.NEWS_XITONG_NUM,0)){ // 如果网络获取到的数据和本地保存的相同，继续下次的心跳

                                    h.sendEmptyMessageDelayed(0,time_xitong*1000);

                                }else{     //  如果消息数目不同 将获取到的消息数目 以及消息的类型 传回消息页面，并跟新ui界面
                                    // 同时将时间重新开始计数,并重新开始心跳
                                    time_xitong=3;
                                    /**
                                     *  第一个参数  消息类型
                                     *  第二个参数  消息数目
                                     */
                                    // 计算出消息差 并传回
                                    int anInt = DashApplication.sp_message_xitong.getInt(SP_String.NEWS_XITONG_NUM, 0);
                                    int num=0;
                                    if(totalNum>=anInt){
                                        num= totalNum - anInt;
                                    }else{
                                        num= totalNum;
                                    }

                                    // 计算/比较完成以后将网络获取的最新消息数目存入本地
                                    edit_message_xitong.putInt(SP_String.NEWS_XITONG_NUM,totalNum).commit();

                                    int type=1;
                                    hearbeatJieKou.hearbeatChuan(type,num);
                                    hearbeat_Home_JieKou.hearbeat_Home_Chuan(num);
                                    getXiTong();
                                }

                            }else{
                                MyUtils.setToast("心跳包="+(String) jsonObject.get("message"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErroy(String ss) {
                        MyUtils.setToast("心跳包==系统=="+ss);
                    }
                });
    }

    public void getChongZhi(){
        Map<String, String> map = new HashMap<>();
        map.put("start","0");
        map.put("number","10");
        map.put("messageType","1");
        RetrofitFacety.post(APIs.getMessageList,map)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){

                                Message_Lists_Bean message_lists_bean = new Gson().fromJson(json, Message_Lists_Bean.class);
                                int totalNum = message_lists_bean.getData().getPageInfo().getTotalNum();
                                // 比较消息的总数目
                                if( totalNum== DashApplication.sp_message_chongzhi.getInt(SP_String.NEWS_CHONGZHI_NUM,0)){ // 如果网络获取到的数据和本地保存的相同，继续下次的心跳
                                    h.sendEmptyMessageDelayed(1,time_chongzhi*1000);
                                }else{     //  如果消息数目不同 将获取到的消息数目 以及消息的类型 传回消息页面，并跟新ui界面
                                    // 同时将时间重新开始计数,并重新开始心跳
                                    time_chongzhi=3;
                                    /**
                                     *  第一个参数  消息类型
                                     *  第二个参数  消息数目
                                     */
                                    // 计算出消息差 并传回
                                    int anInt = DashApplication.sp_message_chongzhi.getInt(SP_String.NEWS_CHONGZHI_NUM, 0);
                                    int num=0;
                                    if(totalNum>=anInt){
                                        num= totalNum - anInt;
                                    }else{
                                        num= totalNum;
                                    }

                                    // 计算/比较完成以后将网络获取的最新消息数目存入本地
                                    DashApplication.edit_message_chongzhi.putInt(SP_String.NEWS_CHONGZHI_NUM,totalNum).commit();

                                    int type=2;
                                    hearbeatJieKou.hearbeatChuan(type,num);
                                    hearbeat_Home_JieKou.hearbeat_Home_Chuan(num);
                                    getChongZhi();
                                }

                            }else{
                                MyUtils.setToast("心跳包="+(String) jsonObject.get("message"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onErroy(String ss) {
                        MyUtils.setToast("心跳包==充值=="+ss);
                    }
                });

    }

    public void getAnQuan(){
        Map<String, String> map = new HashMap<>();
        map.put("start","0");
        map.put("number","10");
        map.put("messageType","2");
        RetrofitFacety.post(APIs.getMessageList,map)
                .subscribe(new BaseServer() {
                    @Override
                    public void onSuccess(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){

                                Message_Lists_Bean message_lists_bean = new Gson().fromJson(json, Message_Lists_Bean.class);
                                int totalNum = message_lists_bean.getData().getPageInfo().getTotalNum();
                                // 比较消息的总数目
                                if( totalNum== DashApplication.sp_message_anquan.getInt(SP_String.NEWS_ANQUAN_NUM,0)){ // 如果网络获取到的数据和本地保存的相同，继续下次的心跳
                                    h.sendEmptyMessageDelayed(2,time_anquuan*1000);
                                }else{     //  如果消息数目不同 将获取到的消息数目 以及消息的类型 传回消息页面，并跟新ui界面
                                    // 同时将时间重新开始计数,并重新开始心跳
                                    time_anquuan=3;
                                    /**
                                     *  第一个参数  消息类型
                                     *  第二个参数  消息数目
                                     */
                                    // 计算出消息差 并传回
                                    int anInt = DashApplication.sp_message_anquan.getInt(SP_String.NEWS_ANQUAN_NUM, 0);
                                    int num=0;
                                    if(totalNum>=anInt){
                                        num= totalNum - anInt;
                                    }else{
                                        num= totalNum;
                                    }

                                    // 计算/比较完成以后将网络获取的最新消息数目存入本地
                                    DashApplication.edit_message_anquan.putInt(SP_String.NEWS_ANQUAN_NUM,totalNum).commit();

                                    int type=3;
                                    hearbeatJieKou.hearbeatChuan(type,num);
                                    hearbeat_Home_JieKou.hearbeat_Home_Chuan(num);
                                    getAnQuan();
                                }

                            }else{
                                MyUtils.setToast("心跳包="+(String) jsonObject.get("message"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onErroy(String ss) {
                        MyUtils.setToast("心跳包==安全=="+ss);
                    }
                });
    }


}
