package com.ifeng_tech.treasuryyitong.ui;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.jpush.JPush_Bean;
import com.ifeng_tech.treasuryyitong.bean.my.QR_Bean;
import com.ifeng_tech.treasuryyitong.fragmet.FindFragment;
import com.ifeng_tech.treasuryyitong.fragmet.HomeFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.MessageFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.MyFragmet;
import com.ifeng_tech.treasuryyitong.fragmet.WarehouseFragment;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.service.MessageService;
import com.ifeng_tech.treasuryyitong.ui.login.Login_New_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Donation_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_list_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Setting_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.bind_email.Bind_Email_Activity1;
import com.ifeng_tech.treasuryyitong.ui.my.tuoguan.Collocation_Subscribe_Activity;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.utils.SignUtils;
import com.ifeng_tech.treasuryyitong.utils.SoundCtrol;
import com.ifeng_tech.treasuryyitong.view.TakeCollPhoneDialog;
import com.ifeng_tech.treasuryyitong.view.TakeCommonDialog;
import com.jwsd.libzxing.OnQRCodeListener;
import com.jwsd.libzxing.QRCodeManager;

import cn.jpush.android.api.JPushInterface;

import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

public class HomePageActivity extends BaseMVPActivity<HomePageActivity,MyPresenter<HomePageActivity>> {

    private TextView homepage_title;
    private RelativeLayout homepage_RelativeLayout;

    private FrameLayout homepage_FrameLayout;
    private ImageView shouyeImg;
    private TextView shouyeName;
    private LinearLayout shouye;
    private ImageView zixunImg;
    private TextView zixunName;
    private LinearLayout zixun;
    private ImageView zhengjiImg;
    private TextView zhengjiName;
    private LinearLayout zhengji;
    private ImageView xiaoxiImg;
    private TextView xiaoxiName;
    private LinearLayout xiaoxi;
    private ImageView wodeImg;
    private TextView wodeName;
    private LinearLayout wode;
    private FragmentManager fragmentManager;

    long exitTim=0;
    private boolean aBoolean;
    public static TextView xiaoxi_shumu;

    public static boolean isForeground = false;
    private SharedPreferences sp_message;
    private SharedPreferences.Editor edit;
    private ImageView faxianImg;
    private TextView faxianName;
    private LinearLayout faxian;

    // 首页
    HomeFragmet homeFragmet = new HomeFragmet();
    // 仓库
    WarehouseFragment treasuryFragmet = new WarehouseFragment();
//      CollectFragmet collectFragmet = new CollectFragmet(); // 征集
    // 消息
    MessageFragmet authenticateFragmet = new MessageFragmet();
    // 发现
    FindFragment findFragment = new FindFragment();
    // 我的
    MyFragmet myFragmet = new MyFragmet();

    public interface HomePageActivity_JieKou{
        void chuan(int i);
    }
    public static HomePageActivity_JieKou homePageActivity_JieKou;

    public void setHomePageActivity_JieKou(HomePageActivity_JieKou homePageActivity_JieKou) {
        this.homePageActivity_JieKou = homePageActivity_JieKou;
    }

    @Override
    public MyPresenter<HomePageActivity> initPresenter() {
        if(myPresenter==null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);

        initView();

//        registerMessageReceiver();  // 动态注册广播

        setBeiJing(true,false,false,false,false,false);

        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.homepage_FrameLayout,homeFragmet).commit();
        fragmentManager.beginTransaction()
                .add(R.id.homepage_FrameLayout, homeFragmet)
                .add(R.id.homepage_FrameLayout, treasuryFragmet)
//                .add(R.id.homepage_FrameLayout,collectFragmet)
                .add(R.id.homepage_FrameLayout, authenticateFragmet)
                .add(R.id.homepage_FrameLayout,findFragment)
                .add(R.id.homepage_FrameLayout, myFragmet)
                .show(homeFragmet)
                .hide(treasuryFragmet)
//                .hide(collectFragmet)
                .hide(authenticateFragmet)
                .hide(findFragment)
                .hide(myFragmet)
                .commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
        sp = getSharedPreferences("ifeng", MODE_PRIVATE);
        aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);
        if(aBoolean){
            String uid = DashApplication.sp.getString(SP_String.UID, "");
            setTagAndAlias(uid);  // 注册极光的别名

            // 获取本地的消息总数目 并设置隐藏/显示
            // 创建保存消息的本地文件
            sp_message = getSharedPreferences("ifeng_message_" + uid, MODE_PRIVATE);
            edit = sp_message.edit();
            String extras = sp_message.getString(SP_String.XIAOXI_SHUMU, "");
            LogUtils.i("jba","homepage===="+extras);
            setExtras(extras);  // 解析推送过来的消息，并进行ui更新
        }

        shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(true,false,false,false,false,false);
//                fragmentManager.beginTransaction().replace(R.id.homepage_FrameLayout,homeFragmet).commit();
                fragmentManager.beginTransaction()
                        .show(homeFragmet)
                        .hide(treasuryFragmet)
//                        .hide(collectFragmet)
                        .hide(authenticateFragmet)
                        .hide(findFragment)
                        .hide(myFragmet)
                        .commit();
            }
        });

        // 仓库
        zixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(false,true,false,false,false,false);
//                fragmentManager.beginTransaction().replace(R.id.homepage_FrameLayout,treasuryFragmet).commit();
                fragmentManager.beginTransaction()
                        .hide(homeFragmet)
                        .show(treasuryFragmet)
//                        .hide(collectFragmet)
                        .hide(authenticateFragmet)
                        .hide(findFragment)
                        .hide(myFragmet)
                        .commit();
            }
        });

//        zhengji.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setBeiJing(false,false,true,false,false,false);
//                fragmentManager.beginTransaction()
//                        .hide(homeFragmet)
//                        .hide(treasuryFragmet)
//                        .show(collectFragmet)
//                        .hide(authenticateFragmet)
//                        .hide(myFragmet)
//                        .commit();
//            }
//        });

        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(false,false,false,true,false,false);
//                fragmentManager.beginTransaction().replace(R.id.homepage_FrameLayout,authenticateFragmet).commit();
                fragmentManager.beginTransaction()
                        .hide(homeFragmet)
                        .hide(treasuryFragmet)
//                        .hide(collectFragmet)
                        .show(authenticateFragmet)
                        .hide(findFragment)
                        .hide(myFragmet)
                        .commit();
            }
        });

        faxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(false,false,false,false,true,false);
//                fragmentManager.beginTransaction().replace(R.id.homepage_FrameLayout,authenticateFragmet).commit();
                fragmentManager.beginTransaction()
                        .hide(homeFragmet)
                        .hide(treasuryFragmet)
//                        .hide(collectFragmet)
                        .hide(authenticateFragmet)
                        .show(findFragment)
                        .hide(myFragmet)
                        .commit();
            }
        });

        wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBeiJing(false,false,false,false,false,true);
//                fragmentManager.beginTransaction().replace(R.id.homepage_FrameLayout,myFragmet).commit();
                fragmentManager.beginTransaction()
                        .hide(homeFragmet)
                        .hide(treasuryFragmet)
//                        .hide(collectFragmet)
                        .hide(authenticateFragmet)
                        .hide(findFragment)
                        .show(myFragmet)
                        .commit();
            }
        });

        setHomePageActivity_JieKou(new HomePageActivity_JieKou() {
            @Override
            public void chuan(int i) {
                switch (i){
                    case 0: // 跳转 征集列表
                        setBeiJing(false,false,true,false,false,false);
                        fragmentManager.beginTransaction()
                                .hide(homeFragmet)
                                .hide(treasuryFragmet)
//                                .show(collectFragmet)
                                .hide(authenticateFragmet)
                                .hide(myFragmet)
                                .commit();

                        break;
                    case 1:   // 跳转  藏品目录
//                        MyUtils.setToast("点击了广告。。。");

                        Intent intent1 = new Intent(HomePageActivity.this, Collection_directory_Activity.class);
                        startActivity(intent1);
                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        break;
                    case 2:  // 跳转 托管预约列表
                        Intent intent = new Intent(HomePageActivity.this, Collocation_Subscribe_Activity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        break;
                    case 3:  // 跳转 资讯列表
                        Intent intent2 = new Intent(HomePageActivity.this, Information_Activity.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        break;
                    case 4:  // 点击了扫一扫
                        if(aBoolean){
                            // 判断是否有绑定过业务密码
                            String yewu_pass = DashApplication.sp.getString(SP_String.ISUSERYEWUPASS, "");
                            if(yewu_pass.equals("0")){
                                if (ActivityCompat.checkSelfPermission(HomePageActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(HomePageActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, DashApplication.CAMERA);
                                }else{
                                    // 吊起二维码扫一扫
                                    setShaoYiShao();
                                }
                            }else{
                                // 使用自定义的dialog框
                                final TakeCommonDialog takeCommonDialog = new TakeCommonDialog(HomePageActivity.this, R.style.dialog_setting,"请先设置业务密码！");
                                MyUtils.getPuTongDiaLog(HomePageActivity.this,takeCommonDialog);
                                takeCommonDialog.setCommonJieKou(new TakeCommonDialog.CommonJieKou() {
                                    @Override
                                    public void quxiao() {
                                        takeCommonDialog.dismiss();
                                    }

                                    @Override
                                    public void queren() {
                                        takeCommonDialog.dismiss();
                                        Intent intent = new Intent(HomePageActivity.this, Bind_Email_Activity1.class);
                                        intent.putExtra("title","业务密码（设置）");
                                        intent.putExtra("select",SP_String.YEWUMIMA);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                    }
                                });
                            }

                        }else{
                            Intent intent4 = new Intent(HomePageActivity.this, Login_New_Activity.class);
                            startActivity(intent4);
                            overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        }
                        break;
                    case 5:   //  点击了收货
                        if(aBoolean){
                            Intent intent3 = new Intent(HomePageActivity.this, Delivery_Activity.class);
                            startActivity(intent3);
                            overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        }else{
                            Intent intent4 = new Intent(HomePageActivity.this, Login_New_Activity.class);
                            startActivity(intent4);
                            overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        }
                        break;
                    case 6:   //  转赠
                        if(aBoolean){
                            // 判断是否有绑定过业务密码
                            String yewu_pass = DashApplication.sp.getString(SP_String.ISUSERYEWUPASS, "");
                            if(yewu_pass.equals("0")) {
                                Intent intent3 = new Intent(HomePageActivity.this, Donation_Activity.class);
                                startActivity(intent3);
                                overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            }else{
                                // 使用自定义的dialog框
                                final TakeCommonDialog takeCommonDialog = new TakeCommonDialog(HomePageActivity.this, R.style.dialog_setting,"请先设置业务密码！");
                                MyUtils.getPuTongDiaLog(HomePageActivity.this,takeCommonDialog);
                                takeCommonDialog.setCommonJieKou(new TakeCommonDialog.CommonJieKou() {
                                    @Override
                                    public void quxiao() {
                                        takeCommonDialog.dismiss();
                                    }

                                    @Override
                                    public void queren() {
                                        takeCommonDialog.dismiss();
                                        Intent intent = new Intent(HomePageActivity.this, Bind_Email_Activity1.class);
                                        intent.putExtra("title","业务密码（设置）");
                                        intent.putExtra("select",SP_String.YEWUMIMA);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                    }
                                });
                            }
                        }else{
                            Intent intent4 = new Intent(HomePageActivity.this, Login_New_Activity.class);
                            startActivity(intent4);
                            overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        }
                        break;
                }
            }
        });


        // 退出登录的接口回调 退出登录直接跳到登录页面
        Setting_Activity.setSetting_JieKou(new Setting_Activity.Setting_JieKou() {
            @Override
            public void chuan() {
                Intent intent = new Intent(HomePageActivity.this, Login_New_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    // 吊起二维码扫一扫
    private void setShaoYiShao() {
        QRCodeManager.getInstance()
                .with(HomePageActivity.this)
                .setReqeustType(DashApplication.ERWIMA_SAOMIAO_req)
                //                .setRequestCode(1001)
                .scanningQRCode(new OnQRCodeListener() {
                    @Override
                    public void onCompleted(String des) {
                        LogUtils.i("jiba","home==="+des);
                        String result = null;
                        try {
                            result = SignUtils.decode(des);
                            if(result.contains(SP_String.QR_ZHUANZENG)){
                                if(result.length()>20){
                                    String path = result.substring(0, result.indexOf("?"));
                                    LogUtils.i("jiba","path===="+path);
                                    String referralCode = result.substring(result.indexOf("=")+1, result.length());

//                            LogUtils.i("jiba","referralCode===="+referralCode);
                                    QR_Bean qr_bean = new Gson().fromJson(referralCode, QR_Bean.class);

                                    if(path.equals(SP_String.QR_ZHUANZENG)){
                                        Intent intent = new Intent(HomePageActivity.this, Donation_Activity.class);
                                        intent.putExtra("QR_Bean", referralCode);
                                        if(qr_bean.getGoodsInfo()==null||qr_bean.getGoodsInfo().getGoodsNum().equals(""))
                                            intent.putExtra("type","1");  // 表示从扫描二维码跳入转赠  1 == 输入框可输入

                                        else  intent.putExtra("type","2");  // 表示从扫描二维码跳入转赠  2 == 输入框不可输入

                                        startActivity(intent);
                                        overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                    }
                                }else{
                                    MyUtils.setToast(des);
                                }
                            }else{
                                MyUtils.setToast(des);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            MyUtils.setToast(des);
                        }
                    }

                    @Override
                    public void onError(Throwable errorMsg) {
                        MyUtils.setToast("解析二维码失败");
                    }

                    @Override
                    public void onCancel() {
                        MyUtils.setToast("扫描任务取消了");
                    }

                    /**
                     * 当点击手动添加时回调
                     *
                     * @param requestCode
                     * @param resultCode
                     * @param data
                     */
                    @Override
                    public void onManual(int requestCode, int resultCode, Intent data) {
                        LogUtils.i("jiba","点击了手动添加了");
                    }


                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //注册onActivityResult  二维码注册
        QRCodeManager.getInstance().with(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==DashApplication.CollPhone){  // 打电话权限
            if (grantResults[0]!=-1){  // 设置成功
                // 使用自定义的dialog框  拨打电话
                setCollPhone();
            }else {
                return;
            }
        }

        if(requestCode==DashApplication.CAMERA){  // 吊起相机权限
            if(grantResults[0]!=-1&&grantResults[1]!=-1){
                setShaoYiShao();
            }else{
                return;
            }
        }
    }

    // 使用自定义的dialog框  拨打电话  这个方法是为了回调个人中心的客服电话
    private void setCollPhone() {
        final TakeCollPhoneDialog takeCollPhoneDialog = new TakeCollPhoneDialog(this, R.style.dialog_setting);
        MyUtils.getPuTongDiaLog(this,takeCollPhoneDialog);
        takeCollPhoneDialog.setTakeCollPhoneDialog_JieKou(new TakeCollPhoneDialog.TakeCollPhoneDialog_JieKou() {
            @Override
            public void chuan() {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + SP_String.baoku_kefu));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
//        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
        i=0;
    }


//    //for receive customer msg from jpush server
//    private MessageReceiver mMessageReceiver;
//    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
//    public static final String KEY_TITLE = "title";
//    public static final String KEY_MESSAGE = "message";
//    public static final String KEY_EXTRAS = "extras";
//
//    public void registerMessageReceiver() {
//        mMessageReceiver = new MessageReceiver();
//        IntentFilter filter = new IntentFilter();
//        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
//        filter.addAction(MESSAGE_RECEIVED_ACTION);
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
//    }
//
//    public class MessageReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            try {
//                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
//                    String messge = intent.getStringExtra(KEY_MESSAGE);
//                    String extras = intent.getStringExtra(KEY_EXTRAS);
//                    StringBuilder showMsg = new StringBuilder();
//                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
//                    if (extras!=null) {
//                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//                    }
//                    LogUtils.i("jba","===="+showMsg.toString());
//
//                    edit.putString(SP_String.XIAOXI_SHUMU,extras).commit();
//                    MediaPlayer mediaPlayer = new MediaPlayer();//这个我定义了一个成员函数
//
//                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    SoundCtrol.playSound(HomePageActivity.this,mediaPlayer);
//                    setExtras(extras);  // 解析推送过来的消息，并进行ui更新
//                }
//            } catch (Exception e){
//            }
//        }
//    }

    int i=0;
    private void setExtras(String extras) {
        if(!extras.equals("")&&extras!=null){
            JPush_Bean jPush_bean = new Gson().fromJson(extras, JPush_Bean.class);
            int jNum=jPush_bean.getSafeNum()+jPush_bean.getGoldSum()+jPush_bean.getSysNum();
            LogUtils.i("jba","homepage===jNum===="+jNum);
            if(jNum>0){
                xiaoxi_shumu.setText(""+jNum);
                xiaoxi_shumu.setVisibility(View.VISIBLE);
                if(jPush_bean.getSysNum()>0){
                    MessageFragmet.message_xitong_shumu.setText(jPush_bean.getSysNum()+"");
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
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(HomePageActivity.this);
                    builder.setContentTitle("宝库易通")
                            .setContentText(jPush_bean.getMsg())
                            .setSmallIcon(R.drawable.logo)
                            .setTicker("您有一条新的消息，请注意查收!")//通知首次出现在通知栏，带上升动画效果的
                            .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                            .setContentIntent(pendingIntent);//设置通知栏点击意图
                    builder.setDefaults(Notification.DEFAULT_SOUND| Notification.DEFAULT_VIBRATE);
                    if(!MessageService.list.contains(MessageService.i)){
                        manager.notify(MessageService.i,builder.build());
                    }
                }else{
                    MediaPlayer mediaPlayer = new MediaPlayer();//这个我定义了一个成员函数

                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    SoundCtrol.playSound(HomePageActivity.this,mediaPlayer);
                }
            }else{
                xiaoxi_shumu.setVisibility(View.GONE);
                MessageFragmet.message_xitong_shumu.setVisibility(View.GONE);
                MessageFragmet.message_congzhi_shumu.setVisibility(View.GONE);
                MessageFragmet.message_anquan_shumu.setVisibility(View.GONE);
            }
        }

    }

    // 设置状态栏  渐变色
    private void setActionBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = getStatusBarHeight(window.getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackground(getResources().getDrawable(R.drawable.zichan_jianbian));
        decorViewGroup.addView(statusBarView);
    }

    // 设置状态栏  深蓝色
    private void setActionBar1() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = new View(window.getContext());
        int statusBarHeight = getStatusBarHeight(window.getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
        params.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(getResources().getColor(R.color.shenlan));
        decorViewGroup.addView(statusBarView);
    }
    // 获取状态栏高度
    private int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        if("Meizu".equals(android.os.Build.MANUFACTURER)){
            return statusBarHeight+5;
        }
        return statusBarHeight;
    }

    //主页面点击切换视图
    public void setBeiJing(boolean syFlag, boolean zxFlag, boolean zjFlag,boolean xxFlag,boolean fxFlag,boolean wdFlag) {
        if (syFlag) {
            shouyeImg.setImageResource(R.drawable.shouye_lan);
            shouyeName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_RelativeLayout.setVisibility(View.GONE);
        } else {
            shouyeImg.setImageResource(R.drawable.shouye_hui);
            shouyeName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
            homepage_RelativeLayout.setVisibility(View.VISIBLE);
        }
        if (zxFlag) {
            zixunImg.setImageResource(R.drawable.cangku_lan);
            zixunName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("宝库");
        } else {
            zixunImg.setImageResource(R.drawable.cangku_hui);
            zixunName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }
//        if (zjFlag) {
//            zhengjiImg.setImageResource(R.drawable.zhengji_lan);
//            zhengjiName.setTextColor(getResources().getColor(R.color.zhuse));
//            homepage_title.setText("征集");
//        } else {
//            zhengjiImg.setImageResource(R.drawable.zhengji_hui);
//            zhengjiName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
//        }
        if (xxFlag) {
            xiaoxiImg.setImageResource(R.drawable.xiaoxi_lan);
            xiaoxiName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("消息");
        } else {
            xiaoxiImg.setImageResource(R.drawable.xiaoxi_hui);
            xiaoxiName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }

        if (fxFlag) {
            faxianImg.setImageResource(R.drawable.faxian_lan);
            faxianName.setTextColor(getResources().getColor(R.color.zhuse));
            homepage_title.setText("发现");
        } else {
            faxianImg.setImageResource(R.drawable.faxian_hui);
            faxianName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
        }

        if (wdFlag) {
            wodeImg.setImageResource(R.drawable.wode_lan);
            wodeName.setTextColor(getResources().getColor(R.color.zhuse));
//            homepage_title.setText("个人中心");
            homepage_RelativeLayout.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setActionBar1();
            }

        } else {
            wodeImg.setImageResource(R.drawable.wode_hui);
            wodeName.setTextColor(getResources().getColor(R.color.zhuse_ziti));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setActionBar();
            }
        }
    }


    private void initView() {
        homepage_title = (TextView) findViewById(R.id.homepage_title);
        homepage_RelativeLayout = (RelativeLayout) findViewById(R.id.homepage_RelativeLayout);

        homepage_FrameLayout = (FrameLayout) findViewById(R.id.homepage_FrameLayout);
        shouyeImg = (ImageView) findViewById(R.id.shouyeImg);
        shouyeName = (TextView) findViewById(R.id.shouyeName);
        shouye = (LinearLayout) findViewById(R.id.shouye);
        zixunImg = (ImageView) findViewById(R.id.zixunImg);
        zixunName = (TextView) findViewById(R.id.zixunName);
        zixun = (LinearLayout) findViewById(R.id.zixun);
//        zhengjiImg = (ImageView) findViewById(R.id.zhengjiImg);
//        zhengjiName = (TextView) findViewById(R.id.zhengjiName);
//        zhengji = (LinearLayout) findViewById(R.id.zhengji);
        xiaoxiImg = (ImageView) findViewById(R.id.xiaoxiImg);
        xiaoxiName = (TextView) findViewById(R.id.xiaoxiName);
        xiaoxi = (LinearLayout) findViewById(R.id.xiaoxi);
        faxianImg = (ImageView) findViewById(R.id.faxianImg);
        faxianName = (TextView) findViewById(R.id.faxianName);
        faxian = (LinearLayout) findViewById(R.id.faxian);

        wodeImg = (ImageView) findViewById(R.id.wodeImg);
        wodeName = (TextView) findViewById(R.id.wodeName);
        wode = (LinearLayout) findViewById(R.id.wode);

        xiaoxi_shumu = (TextView)findViewById(R.id.xiaoxi_shumu);

        JPushInterface.init(getApplicationContext());// 初始化 JPushs。如果已经初始化，但没有登录成功，则执行重新登录。
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTim) > 2000) {
                MyUtils.setToast("再按一次退出程序");
                exitTim = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
