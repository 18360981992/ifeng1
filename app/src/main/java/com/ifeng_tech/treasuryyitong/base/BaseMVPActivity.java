package com.ifeng_tech.treasuryyitong.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.TransferFee_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.NetWorkUtil;
import com.ifeng_tech.treasuryyitong.utils.SP_String;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 *
 * @param <V>  v是activity
 * @param <T>  t是p层
 */
public abstract class BaseMVPActivity<V,T extends MyPresenter<V>> extends AppCompatActivity {

    public T myPresenter;
    //将子类共有方法抽成公有，，，因为每一个Activity都会需要new一个P层，所以将new的这个过程，抽成抽象方法才是最佳良策
    public abstract T initPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_mvp);
        myPresenter=initPresenter();
        boolean conn = NetWorkUtil.isConn((Context) this);
        if(!conn){
            NetWorkUtil.showNoNetWorkDlg((Context) this);
        }
    }

    //在获取焦点是将当前对象与p层绑定
    @Override
    protected void onResume() {
        super.onResume();
        myPresenter.attch((V) this);
    }

    //在页面销毁时，调用p层方法，将view对象置空，方便垃圾回收
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPresenter.setonDestroy();
    }

    public static double shouxufei=0;

    public static int zhuidashuliang=0;
    // 获取转赠手续费   在刚进来调用一次   根据藏品代码再调一次
    public void getShouXuFei(HashMap<String, String> map, final EditText textView) {
        myPresenter.postPreContent(APIs.findTransferFee, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        TransferFee_Bean transferFee_bean = new Gson().fromJson(json, TransferFee_Bean.class);
                        double price = transferFee_bean.getData().getTransferFee() * transferFee_bean.getData().getCommonFeeRate();
                        shouxufei = price; // 这里是手续费
                        textView.setHint("最大转让数量:"+transferFee_bean.getData().getAvailableQty());
                        zhuidashuliang = Integer.valueOf(transferFee_bean.getData().getAvailableQty());
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
            }
        });
    }

    // 获取提货手续费   在刚进来调用一次   根据藏品代码再调一次
    public void getTiHuo_ShouXuFei(HashMap<String, String> map, final TextView textView) {
        myPresenter.postPreContent(APIs.findTransferFee, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        TransferFee_Bean transferFee_bean = new Gson().fromJson(json, TransferFee_Bean.class);
                        double price = transferFee_bean.getData().getDeliveryFee() * transferFee_bean.getData().getCommonFeeRate();
                        textView.setText("￥"+ DashApplication.decimalFormat.format(price)); // 这里是手续费
                    }else{
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {
                MyUtils.setToast(ss);
            }
        });
    }

    /**
     *  登录
     * @param myInterfaces
     */
    public void setLogin( final MyInterfaces myInterfaces) {
        String shouji = DashApplication.sp.getString(SP_String.SHOUJI,"");
        String pass = DashApplication.sp.getString(SP_String.PASS,"");
        HashMap<String, String> map = new HashMap<>();
        map.put("userName",shouji);
        map.put("password",pass);
        map.put("loginType","0");
        myPresenter.postPreContent(APIs.login, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        myInterfaces.chenggong("");
                    }else{
                        myInterfaces.shibai("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void shibai(String ss) {
                myInterfaces.shibai("");
            }
        });
    }


    /**
     * 获取本地软件版本号
     */
    public static String getLocalVersion() {
        int localVersionCode = 0;
        String localVersionName="";
        try {
            PackageInfo packageInfo = DashApplication.getAppContext()
                    .getPackageManager()
                    .getPackageInfo(DashApplication.getAppContext().getPackageName(), 0);
             localVersionName = packageInfo.versionName;
             localVersionCode = packageInfo.versionCode;
            LogUtils.i("jba", "本软件的版本号。。" + localVersionName+"."+localVersionCode);
            localVersionName=localVersionName+"."+localVersionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersionName;
    }


    // eidttext的获焦并软件盘的显示
    public void setEditTexttHuo(EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.findFocus();
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);// 显示输入法
    }

    public void initRefreshListView(PullToRefreshScrollView my_collocation_pulltoscroll) {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        my_collocation_pulltoscroll.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout Labels = my_collocation_pulltoscroll.getLoadingLayoutProxy(true, false);
        Labels.setPullLabel("下拉刷新...");
        Labels.setRefreshingLabel("正在刷新...");
        Labels.setReleaseLabel("放开刷新...");

        ILoadingLayout Labels1 = my_collocation_pulltoscroll.getLoadingLayoutProxy(false, true);
        Labels1.setPullLabel("上拉加载...");
        Labels1.setRefreshingLabel("正在加载...");
        Labels1.setReleaseLabel("放开刷新...");
    }

    /**
     * 判断两次密码是否一致
     * @param ed1  再次密码
     * @param ed2  新密码
     */
    public void isPass_Old_New(final EditText ed1, final EditText ed2){
        // 再次确认密码的失焦事件
        ed1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==false){
                    String s = ed1.getText().toString().trim();  // 再次确认
                    String s1 = ed2.getText().toString().trim();  // 新密码
                    if(!s.equals(s1)){
                        MyUtils.setToast("两次密码不一致");
                        return;
                    }
                }
            }
        });
    }

    /**
     * 设置标签与别名
     */
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
        JPushInterface.setAliasAndTags(BaseMVPActivity.this,uid, tags, mAliasCallback);

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
                    LogUtils.e("TAG", logs);
                    break;
                case 6002:
                    //极低的可能设置失败 我设置过几百回 出现3次失败 不放心的话可以失败后继续调用上面那个方面 重连3次即可 记得return 不要进入死循环了...
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试";
                    LogUtils.e("TAG", logs);
                    break;
                default:
                    logs = "极光推送设置失败，Failed with errorCode = " + code;
                    LogUtils.e("TAG", logs);
                    break;
            }
        }
    };

}
