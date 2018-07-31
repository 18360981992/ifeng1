package com.ifeng_tech.treasuryyitong.fragmet;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.MyListAdapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.MyListBean;
import com.ifeng_tech.treasuryyitong.bean.my.PersonalUserAccount_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.login.Login_New_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.ADVP_R_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Certification_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.My_Given_list_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Safe_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.Setting_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.bangzhu.Help_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.bind_email.Bind_Email_Activity1;
import com.ifeng_tech.treasuryyitong.ui.my.cangku.My_Warehouse_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.tihuo.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.tuoguan.My_Collocation_Activity;
import com.ifeng_tech.treasuryyitong.ui.my.zhifu_chongzhi.My_Property_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.utils.SP_String;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.MyListView;
import com.ifeng_tech.treasuryyitong.view.TakeCollPhoneDialog;
import com.ifeng_tech.treasuryyitong.view.TakeCommonDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.content.Context.MODE_PRIVATE;
import static com.ifeng_tech.treasuryyitong.appliction.DashApplication.sp;

/**
 * Created by zzt on 2018/4/27.
 * <p>
 * 我的
 */

public class MyFragmet extends Fragment {
    private ImageView wode_touxiang;
//    private TextView wode_name;
    private TextView wode_hao;
    private TextView wode_weirenzheng;
    private ListView wode_MyListView;
    List<MyListBean> myListBeen = new ArrayList<>();
    private HomePageActivity activity;
    private Intent intent;
    private RelativeLayout wode_anquanbaohu;
    private RelativeLayout wode_shezhi;
    private LinearLayout wode_denglu;
    private LinearLayout wode_weidenglu;
    private ImageView wode_yirenzheng;
    private boolean aBoolean;
    private int shiming_type;   // 0==失败 1 == 认证中 2 == 已认证 3 == 未认证
    private SharedPreferences.Editor edit;
    public RelativeLayout wode_bangzhu;
    public RelativeLayout wode_kefu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragmet, container, false);
        initView(view);

        activity = (HomePageActivity) getActivity();
        wode_weidenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(activity, Login_New_Activity.class);
                startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        /**
         * 点击认证按钮需要判断当前状态
         */
        wode_weirenzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (shiming_type){
                    case 2:// 已认证
                        break;
                    case 1:// 认证中
                        // 根据状态 选择隐藏/显示  1== 认证中
                        intent = new Intent(activity, ADVP_R_Activity.class);
                        intent.putExtra("rengzheng_type",1);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        break;
                    case 0:// 认证失败
                        // 根据状态 选择隐藏/显示  1== 认证中 2==认证失败
                        intent = new Intent(activity, ADVP_R_Activity.class);
                        intent.putExtra("rengzheng_type",2);
                        activity.startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        break;
                    default: // 未认证
                        intent = new Intent(activity, Certification_Activity.class);
                        startActivity(intent);
                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                        break;
                }
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        /**
         * 进入页面后，现获取用户信息，判断是否登录状态
         */
        sp = activity.getSharedPreferences("ifeng", MODE_PRIVATE);
        edit = sp.edit();

        aBoolean = sp.getBoolean(SP_String.ISLOGIN, false);// 登录状态

        if(aBoolean){
            wode_denglu.setVisibility(View.VISIBLE);
            wode_weidenglu.setVisibility(View.GONE);
            getUser(); // 获取个人信息

        }else{
            wode_denglu.setVisibility(View.GONE);
            wode_weidenglu.setVisibility(View.VISIBLE);
            wode_weirenzheng.setVisibility(View.GONE);
//            wode_touxiang.setImageResource(R.drawable.wode_weidenglu_img); // 以前的未登录

            Glide.with(activity)
                    .load(R.drawable.guangao)
                    .bitmapTransform(new CropCircleTransformation(DashApplication.getAppContext()))
                    .into(wode_touxiang);
        }

        wode_MyListView.setAdapter(new MyListAdapter(activity, myListBeen));

        // listview的点击事件
        wode_MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 判断是否登录
                if(aBoolean){
                    switch (position) {
                        case 0:  // 我的资产
                            intent = new Intent(activity, My_Property_Activity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;
//                        case 1: // 我的征集
//                            intent = new Intent(activity, MyCollectActivity.class);
//                            activity.startActivity(intent);
//                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
//                            break;
                        case 1:  // 我的托管
                            intent = new Intent(activity, My_Collocation_Activity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;
                        case 2:  // 转赠列表
                            intent = new Intent(activity, My_Given_list_Activity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;
//                    case 4:  //  入库管理
//                        intent = new Intent(activity, Incoming_Test_Activity.class);
//                        activity.startActivity(intent);
//                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
//                        break;
//                    case 5:  // 出库管理
//                        intent = new Intent(activity, Stock_Removal_Activity.class);
//                        activity.startActivity(intent);
//                        break;
                        case 3:   // 我的仓库
                            intent = new Intent(activity, My_Warehouse_Activity.class);
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            break;
                        case 4:   // 提货注册

                            // 判断是否有绑定过业务密码
                            String yewu_pass = DashApplication.sp.getString(SP_String.ISUSERYEWUPASS, "");
                            if(yewu_pass.equals("0")){
                                intent = new Intent(activity, Pick_up_goods_Activity.class);
                                activity.startActivity(intent);
                                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                            }else{
                                // 使用自定义的dialog框
                                final TakeCommonDialog takeCommonDialog = new TakeCommonDialog(activity, R.style.dialog_setting,"请先设置业务密码！");
                                MyUtils.getPuTongDiaLog(activity,takeCommonDialog);
                                takeCommonDialog.setCommonJieKou(new TakeCommonDialog.CommonJieKou() {
                                    @Override
                                    public void quxiao() {
                                        takeCommonDialog.dismiss();
                                    }

                                    @Override
                                    public void queren() {
                                        takeCommonDialog.dismiss();
                                        intent = new Intent(activity, Bind_Email_Activity1.class);
                                        intent.putExtra("title","业务密码（设置）");
                                        intent.putExtra("select",SP_String.YEWUMIMA);
                                        activity.startActivity(intent);
                                        activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                                    }
                                });
                            }

                            break;
//                        case 5:    // 委托申请
//                           // intent = new Intent(activity, Entrust_Activity.class);
//                            intent = new Intent(activity, Entrust_PT_Activity.class);
//                            activity.startActivity(intent);
//                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
//                            break;
//                        case 6:    // 委托列表
//                            intent = new Intent(activity, Entrust_List_Activity.class);
//                            activity.startActivity(intent);
//                            activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
//                            break;
                    }
                }else{ // 没登录直接跳到登录页面
                    intent = new Intent(activity, Login_New_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            }
        });
        // 安全保护 点击
        wode_anquanbaohu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aBoolean){
                    intent = new Intent(activity, Safe_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{ // 没登录直接跳到登录页面
                    intent = new Intent(activity, Login_New_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            }
        });

        // 帮助中心点击
        wode_bangzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(activity, Help_Activity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 客服电话的点击
        wode_kefu.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
                // 6.0权限适配
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CALL_PHONE}, DashApplication.CollPhone);
                }else{
                    // 使用自定义的dialog框  拨打电话
                    setCollPhone();
                }
            }
        });

        // 设置 点击
        wode_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aBoolean){
                    intent = new Intent(activity, Setting_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }else{ // 没登录直接跳到登录页面
                    intent = new Intent(activity, Login_New_Activity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
                }
            }
        });

//        Setting_Activity.setSetting_JieKou(new Setting_Activity.Setting_JieKou() {
//            @Override
//            public void chuan() {
//                wode_yirenzheng.setVisibility(View.GONE);
//                wode_weirenzheng.setVisibility(View.GONE);
//            }
//        });
    }

    // 使用自定义的dialog框  拨打电话
    private void setCollPhone() {
        final TakeCollPhoneDialog takeCollPhoneDialog = new TakeCollPhoneDialog(activity, R.style.dialog_setting);
        MyUtils.getPuTongDiaLog(activity,takeCollPhoneDialog);
        takeCollPhoneDialog.setTakeCollPhoneDialog_JieKou(new TakeCollPhoneDialog.TakeCollPhoneDialog_JieKou() {
            @Override
            public void chuan() {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + SP_String.baoku_kefu));
                activity.startActivity(intent);
            }
        });
    }

    Map<String, String> map = new HashMap<>();
    // 获取用户信息
    private void getUser() {
        map.clear();
        map.put("","");
        activity.myPresenter.postPreContent(APIs.findPersonalUserAccount,map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
//                        LogUtils.i("jiba","===="+json);
                        PersonalUserAccount_Bean userBean = new Gson().fromJson(json, PersonalUserAccount_Bean.class);
                        wode_hao.setText(userBean.getData().getUser().getUserCode()+"");
                        shiming_type=userBean.getData().getAccountInfo().getInfoState();   // 获取个人认证状态

                        DashApplication.edit
                                .putString(SP_String.ISUSERYEWUPASS,userBean.getData().getAccountInfo().getIsNoBusinessPwd()+"")
                                .putString(SP_String.EMAIL,userBean.getData().getAccountInfo().getEmail()+"")
                                .commit();

                        // 设置个人头像
                        if(userBean.getData().getAccountInfo().getImgUrl()==null){
                            Glide.with(activity)
                                    .load(R.drawable.guangao)
                                    .bitmapTransform(new CropCircleTransformation(DashApplication.getAppContext()))
                                    .into(wode_touxiang);
                        }else{
                            DashApplication.edit.putString(SP_String.USERIMG,userBean.getData().getAccountInfo().getImgUrl()).commit();
                            Glide.with(activity)
                                    .load(R.drawable.guangao)  // 成功
                                    .error(R.drawable.guangao)  // 失败
                                    .bitmapTransform(new CropCircleTransformation(DashApplication.getAppContext()))
                                    .into(wode_touxiang);
                        }

                        // 登录状态以后还需要判断用户信息是否实名认证状态
                        switch (shiming_type){
                            case 2:  // 已认证
                                wode_weirenzheng.setVisibility(View.GONE);
                                wode_yirenzheng.setVisibility(View.VISIBLE);
                                break;
                            case 1: // 认证中
                                wode_weirenzheng.setVisibility(View.VISIBLE);
                                wode_yirenzheng.setVisibility(View.GONE);
                                wode_weirenzheng.setText("认证中...");
//                                wode_weirenzheng.setTextColor(getResources().getColor(R.color.name_se));
//                                wode_weirenzheng.setBackgroundColor(getResources().getColor(R.color.fengouxian));
                                wode_weirenzheng.setEnabled(true);
                                break;
                            case 0:// 认证失败
                                wode_weirenzheng.setVisibility(View.VISIBLE);
                                wode_yirenzheng.setVisibility(View.GONE);
                                wode_weirenzheng.setText("认证失败");
//                                wode_weirenzheng.setTextColor(getResources().getColor(R.color.name_se));
//                                wode_weirenzheng.setBackgroundColor(getResources().getColor(R.color.fengouxian));
                                wode_weirenzheng.setEnabled(true);
                                break;
                            default:  // 未认证
                                wode_weirenzheng.setVisibility(View.VISIBLE);
                                wode_yirenzheng.setVisibility(View.GONE);
                                wode_weirenzheng.setText("未认证");
                                wode_weirenzheng.setEnabled(true);
                                break;
                        }

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


    private void initView(View view) {
        wode_touxiang = (ImageView) view.findViewById(R.id.wode_touxiang);
//        wode_name = (TextView) view.findViewById(R.id.wode_name);
        wode_hao = (TextView) view.findViewById(R.id.wode_hao);
        wode_weirenzheng = (TextView) view.findViewById(R.id.wode_weirenzheng);
        wode_MyListView = (MyListView) view.findViewById(R.id.wode_MyListView);

        wode_anquanbaohu = (RelativeLayout)view.findViewById(R.id.wode_anquanbaohu);
        wode_bangzhu = (RelativeLayout)view.findViewById(R.id.wode_bangzhu);
        wode_kefu = (RelativeLayout)view.findViewById(R.id.wode_kefu);
        wode_shezhi = (RelativeLayout)view.findViewById(R.id.wode_shezhi);

        wode_denglu = (LinearLayout) view.findViewById(R.id.wode_denglu);
        wode_weidenglu = (LinearLayout) view.findViewById(R.id.wode_weidenglu);
        wode_yirenzheng = (ImageView) view.findViewById(R.id.wode_yirenzheng);

        /**
         * 解决scrollview 显示不在顶部问题
         */
        wode_touxiang.setFocusable(true);
        wode_touxiang.setFocusableInTouchMode(true);
        wode_touxiang.requestFocus();

        initData();
    }

    public void initData() {
        myListBeen.add(new MyListBean(R.drawable.wode_zican, "我的资产"));
//        myListBeen.add(new MyListBean(R.drawable.wode_zhengji, "我的征集"));
        myListBeen.add(new MyListBean(R.drawable.wode_tuoguan, "我的托管"));
        myListBeen.add(new MyListBean(R.drawable.wode_zhuanzeng, "转让列表"));
//        myListBeen.add(new MyListBean(R.drawable.wode_ruku,"入库管理"));
//        myListBeen.add(new MyListBean(R.drawable.wode_chuku,"出库管理"));
        myListBeen.add(new MyListBean(R.drawable.wode_cangku, "我的宝库"));
        myListBeen.add(new MyListBean(R.drawable.wode_tihuo, "提货查询"));
//        myListBeen.add(new MyListBean(R.drawable.weituo_shenqing, "委托申请"));
//        myListBeen.add(new MyListBean(R.drawable.weituo_liebiao, "委托列表"));
    }
}
