package com.ifeng_tech.treasuryyitong.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.jpush.JPush_Bean;
import com.ifeng_tech.treasuryyitong.fragmet.MessageFragmet;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_list_Activity;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SoundCtrol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by zzt on 2018/7/31.
 */

public class MessageService extends Service {

    public SharedPreferences.Editor edit;

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
        registerMessageReceiver();  // 动态注册广播
        String uid = DashApplication.sp.getString(SP_String.UID, "");
        setTagAndAlias(uid);  // 注册极光的别名

        SharedPreferences sp_message = getSharedPreferences("ifeng_message_" + uid, MODE_PRIVATE);
        edit = sp_message.edit();
        return super.onStartCommand(intent, flags, startId);
    }

    public void setTagAndAlias(String uid) {

//        LogUtils.i("jiba","uid==="+uid);
        /**
         *这里设置了别名，在这里获取的用户登录的信息
         *并且此时已经获取了用户的userId,然后就可以用用户的userId来设置别名了
         **/
        //false状态为未设置标签与别名成功
        //if (UserUtils.getTagAlias(getHoldingActivity()) == false) {
        Set<String> tags = new HashSet<String>();
        //这里可以设置你要推送的人，一般是用户uid 不为空在设置进去 可同时添加多个
        if (!TextUtils.isEmpty(uid)){
            tags.add(uid);//设置tag
        }
        //上下文、别名【Sting行】、标签【Set型】、回调
        JPushInterface.setAliasAndTags(MessageService.this,uid, tags, mAliasCallback);

        String rid = JPushInterface.getRegistrationID(getApplicationContext());
        LogUtils.e("jba", "JPushInterface===="+rid);
    }

    /**
     * /**
     * TagAliasCallback类是JPush开发包jar中的类，用于
     * 设置别名和标签的回调接口，成功与否都会回调该方法
     * 同时给定回调的代码。如果code=0,说明别名设置成功。
     * /**
     * 6001   无效的设置，tag/alias 不应参数都为 null
     * 6002   设置超时    建议重试
     * 6003   alias 字符串不合法    有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
     * 6004   alias超长。最多 40个字节    中文 UTF-8 是 3 个字节
     * 6005   某一个 tag 字符串不合法  有效的别名、标签组成：字母（区分大小写）、数字、下划线、汉字。
     * 6006   某一个 tag 超长。一个 tag 最多 40个字节  中文 UTF-8 是 3 个字节
     * 6007   tags 数量超出限制。最多 100个 这是一台设备的限制。一个应用全局的标签数量无限制。
     * 6008   tag/alias 超出总长度限制。总长度最多 1K 字节
     * 6011   10s内设置tag或alias大于3次 短时间内操作过于频繁
     **/
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    //这里可以往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                    //UserUtils.saveTagAlias(getHoldingActivity(), true);
                    logs = "Set tag and alias success极光推送别名设置成功";
                    LogUtils.e("jba", logs);
                    break;
                case 6002:
                    //极低的可能设置失败 我设置过几百回 出现3次失败 不放心的话可以失败后继续调用上面那个方面 重连3次即可 记得return 不要进入死循环了...
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试";
                    LogUtils.e("jba", logs);
                    break;
                default:
                    logs = "极光推送设置失败，Failed with errorCode = " + code;
                    LogUtils.e("jba", logs);
                    break;
            }
        }
    };

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (extras!=null) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                    LogUtils.i("jba","===="+showMsg.toString());
                    edit.putString(SP_String.XIAOXI_SHUMU,extras).commit();
                    setExtras(extras);  // 解析推送过来的消息，并进行ui更新
                }
            } catch (Exception e){
            }
        }
    }

    public static int i=0;
    public static List<Integer> list=new ArrayList<>();
    private void setExtras(String extras) {
        if(!extras.equals("")&&extras!=null){
            JPush_Bean jPush_bean = new Gson().fromJson(extras, JPush_Bean.class);
            int jNum=jPush_bean.getSafeNum()+jPush_bean.getGoldSum()+jPush_bean.getSysNum();

            LogUtils.i("jba","jNum===="+jNum);
            if(jNum>0){
                HomePageActivity.xiaoxi_shumu.setText(""+jNum);
                HomePageActivity.xiaoxi_shumu.setVisibility(View.VISIBLE);
                if(jPush_bean.getSysNum()>0) {
                    MessageFragmet.message_xitong_shumu.setText(jPush_bean.getSysNum() + "");
                    MessageFragmet.message_xitong_shumu.setVisibility(View.VISIBLE);
                }else{
                    MessageFragmet.message_xitong_shumu.setVisibility(View.GONE);
                }
                if(jPush_bean.getGoldSum()>0){
                    MessageFragmet.message_congzhi_shumu.setText(jPush_bean.getGoldSum()+"");
                    MessageFragmet.message_congzhi_shumu.setVisibility(View.VISIBLE);
                }else{
                    MessageFragmet.message_congzhi_shumu.setVisibility(View.GONE);
                }

                if(jPush_bean.getSafeNum()>0){
                    MessageFragmet.message_anquan_shumu.setText(jPush_bean.getSafeNum()+"");
                    MessageFragmet.message_anquan_shumu.setVisibility(View.VISIBLE);
                }else{
                    MessageFragmet.message_anquan_shumu.setVisibility(View.GONE);
                }

                if(!jPush_bean.getMsg().equals("")&&jPush_bean.getSysNum()>0){
                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, My_Given_list_Activity.class), PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MessageService.this);
                    builder.setContentTitle("宝库易通")
                            .setContentText(jPush_bean.getMsg())
                            .setSmallIcon(R.drawable.logo)
                            .setTicker("您有一条新的消息，请注意查收!")//通知首次出现在通知栏，带上升动画效果的
                            .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                            .setContentIntent(pendingIntent);//设置通知栏点击意图
                    builder.setDefaults(Notification.DEFAULT_SOUND| Notification.DEFAULT_VIBRATE);
                    manager.notify(i++,builder.build());
                    list.add(i);
                }else{
                    MediaPlayer mediaPlayer = new MediaPlayer();//这个我定义了一个成员函数

                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    SoundCtrol.playSound(MessageService.this,mediaPlayer);
                }
            }else{
                HomePageActivity.xiaoxi_shumu.setVisibility(View.GONE);
                MessageFragmet.message_xitong_shumu.setVisibility(View.GONE);
                MessageFragmet.message_congzhi_shumu.setVisibility(View.GONE);
                MessageFragmet.message_anquan_shumu.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        i=0;
    }
}
