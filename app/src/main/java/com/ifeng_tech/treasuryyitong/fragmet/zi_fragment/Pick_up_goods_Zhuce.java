package com.ifeng_tech.treasuryyitong.fragmet.zi_fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.appliction.DashApplication;
import com.ifeng_tech.treasuryyitong.bean.WarehouseBean;
import com.ifeng_tech.treasuryyitong.bean.tihuo.DepotListByGoodsId_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.ui.my.My_Warehouse_Activity2;
import com.ifeng_tech.treasuryyitong.ui.my.Pick_up_goods_Activity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.ForbidClickListener;
import com.ifeng_tech.treasuryyitong.view.Search_Pop_View;
import com.ifeng_tech.treasuryyitong.view.rili.TakeCalendar_Dialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzt on 2018/5/14.
 *
 *  注册提货信息页
 */

public class Pick_up_goods_Zhuce extends Fragment  {
    public LinearLayout fu;
    private EditText up_goods_zhuce_name;
    private EditText up_goods_zhuce_word;
    private TextView up_goods_zhuce_cangku;
    private LinearLayout up_goods_zhuce_cangku_img;
    private EditText up_goods_zhuce_jiaoge_jianshu;
    private EditText up_goods_zhuce_jiaoge_shuliang;
    private TextView up_goods_zhuce_rili;
    private ImageView up_goods_zhuce_rili_img;
    private TextView up_goods_zhuce_pass_tishi;
    private EditText up_goods_zhuce_pass;
    private TextView up_goods_zhuce_zai_tishi;
    private EditText up_goods_zhuce_zai;
    private Button up_goods_tijiao;
    private Button up_goods_chongzhi;
    private Pick_up_goods_Activity activity;

    private int up_goods_zhuce_cangku_width;   // 获取 仓库选择 控件的宽度

    List<DepotListByGoodsId_Bean.DataBean.ListBean> list=new ArrayList<>();
    private Search_Pop_View search_pop_view;
    private int goodsId;  // 藏品id
    private String goodsCode; // 藏品code
    private int depotId;    // 仓库id
    private String takeDate;  // 提货的时间
    private LinearLayout up_goods_zhuce_name_weitanchuan;
    private ImageView up_goods_zhuce_name_weitanchuan_img;
    private TextView up_goods_zhuce_name_weitanchuan_text;
    private int weitanchuan_height;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pick_up_goods_zhuce, container, false);

        initView(view);

        activity = (Pick_up_goods_Activity) getActivity();

        // 点击输入名称 跳
        up_goods_zhuce_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, My_Warehouse_Activity2.class);
                intent.putExtra("select","提货");
                activity.startActivityForResult(intent, DashApplication.TIHUO_TO_CANGKU_req);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);
            }
        });

        // 点击输入藏品代码 跳
        up_goods_zhuce_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, My_Warehouse_Activity2.class);
                intent.putExtra("select","提货");
                activity.startActivityForResult(intent, DashApplication.TIHUO_TO_CANGKU_req);
                activity.overridePendingTransition(R.anim.slide_in_kuai, R.anim.slide_out_kuai);

            }
        });

        up_goods_zhuce_cangku_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyUtils.setToast("点击了仓库选择。。。");
                Map<String, String> map = new HashMap<>();
                map.put("goodsId",goodsId+"");
                activity.myPresenter.postPreContent(APIs.getDepotListByGoodsId, map, new MyInterfaces() {
                    @Override
                    public void chenggong(String json) {
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String code = (String) jsonObject.get("code");
                            if(code.equals("2000")){
                                DepotListByGoodsId_Bean depotListByGoodsId_bean = new Gson().fromJson(json, DepotListByGoodsId_Bean.class);
                                List<DepotListByGoodsId_Bean.DataBean.ListBean> zilist = depotListByGoodsId_bean.getData().getList();
                                list.clear();
                                list.addAll(zilist);
                                search_pop_view = new Search_Pop_View(activity,up_goods_zhuce_cangku_width,list);
                                search_pop_view.setBackgroundDrawable(new BitmapDrawable());
                                search_pop_view.showAsDropDown(up_goods_zhuce_cangku);
                                pick_up_goods_zhuce_jieKou.chuan();
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
        });

        up_goods_zhuce_rili_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(up_goods_zhuce_rili_img);
            }
        });

        up_goods_tijiao.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
//                MyUtils.setToast("点击提交。。。");
                submit();
            }
        });
        up_goods_chongzhi.setOnClickListener(new ForbidClickListener() {
            @Override
            public void forbidClick(View v) {
//                MyUtils.setToast("点击重置。。。");
                goodsId = 0;  // 藏品id
                goodsCode = "";// 藏品代码
                depotId=0;
                takeDate="";

                up_goods_zhuce_name.setText("");  // 藏品名称
                up_goods_zhuce_word.setText("");
                up_goods_zhuce_cangku.setText("");

                up_goods_zhuce_jiaoge_jianshu.setHint("");

                up_goods_zhuce_jiaoge_shuliang.setText("");
                up_goods_zhuce_rili.setText("");

                up_goods_zhuce_pass.setText("");
                up_goods_zhuce_zai.setText("");
            }
        });
        return view;
    }

    int ZUIDAJIANSHU=0;   // 记录最大交割数量
    @Override
    public void onResume() {
        super.onResume();

        WarehouseBean.DataBean.ListBean warehouseBean = (WarehouseBean.DataBean.ListBean) activity.getIntent().getSerializableExtra("WarehouseBean");
        if(warehouseBean !=null){
            goodsId = warehouseBean.getGoodsId();  // 藏品id
            goodsCode = warehouseBean.getGoodsCode();// 藏品代码

            up_goods_zhuce_name.setText(warehouseBean.getGoodsName());  // 藏品名称
            up_goods_zhuce_word.setText(goodsCode);

            up_goods_zhuce_word.requestFocus();
            up_goods_zhuce_word.setSelection(warehouseBean.getGoodsCode().length());
            up_goods_zhuce_jiaoge_jianshu.setHint("您的最大交割件数"+ warehouseBean.getAvailableQty());

            ZUIDAJIANSHU=warehouseBean.getAvailableQty();  // 记录最大交割数量
        }

        // 从父类中的回调
        activity.setPick_up_goods_Activity_JieKou(new Pick_up_goods_Activity.Pick_up_goods_Activity_JieKou() {
            @Override
            public void chuan(WarehouseBean.DataBean.ListBean warehouseBean) {
                if(warehouseBean!=null){
                    goodsId = warehouseBean.getGoodsId();  // 藏品id
                    goodsCode = warehouseBean.getGoodsCode();// 藏品代码

                    up_goods_zhuce_name.setText(warehouseBean.getGoodsName());  // 藏品名称
                    up_goods_zhuce_word.setText(goodsCode);

                    up_goods_zhuce_word.requestFocus();
                    up_goods_zhuce_word.setSelection(warehouseBean.getGoodsCode().length());
                    up_goods_zhuce_jiaoge_jianshu.setHint("您的最大交割件数"+warehouseBean.getAvailableQty());

                    ZUIDAJIANSHU=warehouseBean.getAvailableQty();    // 记录最大交割数量
                }
            }
        });

        // 外面嵌套一层接口是为了避免 search_pop_view 的空异常
        setPick_up_goods_zhuce_jieKou(new Pick_up_goods_Zhuce_JieKou() {
            @Override
            public void chuan() {
                search_pop_view.setSearch_Pop_JieKou(new Search_Pop_View.Search_Pop_JieKou() {
                    @Override
                    public void chuan(List<DepotListByGoodsId_Bean.DataBean.ListBean> list, int position) {
                        if(list.get(position).getDepotName()!=null && !list.get(position).getDepotName().equals("")){
                            up_goods_zhuce_cangku.setText(list.get(position).getDepotName());
                        }else{
                            up_goods_zhuce_cangku.setText("测试仓库");
                        }
                    }
                });
            }
        });

    }


    private void initView(View view) {
        fu = (LinearLayout)view.findViewById(R.id.fu);
        up_goods_zhuce_name = (EditText) view.findViewById(R.id.up_goods_zhuce_name);
        up_goods_zhuce_word = (EditText) view.findViewById(R.id.up_goods_zhuce_word);

        setTextView(up_goods_zhuce_name,false);  // 禁止输入
        setTextView(up_goods_zhuce_word,false);

        up_goods_zhuce_cangku = (TextView) view.findViewById(R.id.up_goods_zhuce_cangku);
        up_goods_zhuce_cangku_img = (LinearLayout) view.findViewById(R.id.up_goods_zhuce_cangku_img);
        up_goods_zhuce_jiaoge_jianshu = (EditText) view.findViewById(R.id.up_goods_zhuce_jiaoge_jianshu);
        up_goods_zhuce_jiaoge_shuliang = (EditText) view.findViewById(R.id.up_goods_zhuce_jiaoge_shuliang);
        up_goods_zhuce_rili = (TextView) view.findViewById(R.id.up_goods_zhuce_rili);
        up_goods_zhuce_rili_img = (ImageView) view.findViewById(R.id.up_goods_zhuce_rili_img);
        up_goods_zhuce_pass_tishi = (TextView) view.findViewById(R.id.up_goods_zhuce_pass_tishi);
        up_goods_zhuce_pass = (EditText) view.findViewById(R.id.up_goods_zhuce_pass);
        up_goods_zhuce_zai_tishi = (TextView) view.findViewById(R.id.up_goods_zhuce_zai_tishi);
        up_goods_zhuce_zai = (EditText) view.findViewById(R.id.up_goods_zhuce_zai);
        up_goods_tijiao = (Button) view.findViewById(R.id.up_goods_tijiao);
        up_goods_chongzhi = (Button) view.findViewById(R.id.up_goods_chongzhi);

        up_goods_zhuce_name_weitanchuan = (LinearLayout) view.findViewById(R.id.up_goods_zhuce_name_weitanchuan);
        up_goods_zhuce_name_weitanchuan_img = (ImageView) view.findViewById(R.id.up_goods_zhuce_name_weitanchuan_img);
        up_goods_zhuce_name_weitanchuan_text = (TextView) view.findViewById(R.id.up_goods_zhuce_name_weitanchuan_text);

        //通过设置监听来获取 仓库选择 控件的宽度
        up_goods_zhuce_cangku.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                up_goods_zhuce_cangku.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                up_goods_zhuce_cangku_width = up_goods_zhuce_cangku.getMeasuredWidth();
            }
        });

        //通过设置监听来获取 微弹窗 控件的高度
        up_goods_zhuce_name_weitanchuan.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                up_goods_zhuce_name_weitanchuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //获取ImageView控件的初始高度  用来图片回弹时
                weitanchuan_height = up_goods_zhuce_name_weitanchuan.getMeasuredHeight();
            }
        });
    }

    // 设置textview 是否可点击
    private void setTextView(TextView textView,boolean focusable) {
        textView.setFocusable(focusable);
        textView.setFocusableInTouchMode(focusable); // user touches widget on phone with touch screen
        textView.setClickable(focusable);
    }

    // 弹出日历的pop框
    private void showPopupWindow(View v) {

        final TakeCalendar_Dialog dialog = new TakeCalendar_Dialog(activity, R.style.dialog_setting);
        MyUtils.getDiaLogDiBu(activity,dialog);

        dialog.setOnItemClick(new TakeCalendar_Dialog.OnItemClick() {
            @Override
            public void onItemClick(String date) {
//                MyUtils.setToast("点击了：" + date);
                up_goods_zhuce_rili.setText(date);

                takeDate = date;  // 提货的时间
            }
        });

    }

    private void submit() {
        // validate
        String name = up_goods_zhuce_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "请输入藏品名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String word = up_goods_zhuce_word.getText().toString().trim();
        if (TextUtils.isEmpty(word)) {
            Toast.makeText(getContext(), "请输入藏品代码", Toast.LENGTH_SHORT).show();
            return;
        }

        String jianshu = up_goods_zhuce_jiaoge_jianshu.getText().toString().trim();
        if (TextUtils.isEmpty(jianshu)) {
            Toast.makeText(getContext(), "您的最大交割件数0", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Integer.valueOf(jianshu) > ZUIDAJIANSHU){
            MyUtils.setToast("您已超出最大交割件数");
            return;
        }


        String shuliang = up_goods_zhuce_jiaoge_shuliang.getText().toString().trim();
        if (TextUtils.isEmpty(shuliang)) {
            Toast.makeText(getContext(), "请输入交割数量", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!shuliang.equals(jianshu)){
            MyUtils.setToast("您交割数量与交割件数不相同");
            return;
        }

        String pass = up_goods_zhuce_pass.getText().toString().trim();
        if (TextUtils.isEmpty(pass)) {
            up_goods_zhuce_pass_tishi.setText("请输入交易密码");
            up_goods_zhuce_pass_tishi.setVisibility(View.VISIBLE);
            return;
        }else{
            up_goods_zhuce_pass_tishi.setVisibility(View.INVISIBLE);
        }

        if(pass.length()<6){
            up_goods_zhuce_pass_tishi.setText("6-12个字符,区分大小写");
            up_goods_zhuce_pass_tishi.setVisibility(View.VISIBLE);
            return;
        }

        String zai = up_goods_zhuce_zai.getText().toString().trim();
        if (TextUtils.isEmpty(zai)) {
            Toast.makeText(getContext(), "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!zai.equals(pass)){
            up_goods_zhuce_zai_tishi.setVisibility(View.VISIBLE);
            return;
        }else{
            up_goods_zhuce_zai_tishi.setVisibility(View.INVISIBLE);
        }

        // TODO validate success, do something

        Map<String, String> map = new HashMap<>();
        map.put("goodsId",goodsId+"");
        map.put("goodsCode",goodsCode+"");
        map.put("takeDate",takeDate+"");
        map.put("password",pass+"");
        map.put("amount",shuliang+"");
        map.put("piece",jianshu+"");
        map.put("depotId",depotId+"");

        //  进度框
        final ProgressDialog aniDialog = new ProgressDialog(activity);
        aniDialog.setCancelable(true);
        aniDialog.setMessage("正在登录...");
        aniDialog.show();

        activity.myPresenter.postPreContent(APIs.applyTakeDelivery, map, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                aniDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if(code.equals("2000")){
                        MyUtils.setObjectAnimator(up_goods_zhuce_name_weitanchuan,
                                up_goods_zhuce_name_weitanchuan_img,
                                up_goods_zhuce_name_weitanchuan_text,
                                weitanchuan_height,
                                true,"征集成功！");
                    }else{
                        MyUtils.setObjectAnimator(up_goods_zhuce_name_weitanchuan,
                                up_goods_zhuce_name_weitanchuan_img,
                                up_goods_zhuce_name_weitanchuan_text,
                                weitanchuan_height,
                                false,(String) jsonObject.get("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                aniDialog.dismiss();
                MyUtils.setObjectAnimator(up_goods_zhuce_name_weitanchuan,
                        up_goods_zhuce_name_weitanchuan_img,
                        up_goods_zhuce_name_weitanchuan_text,
                        weitanchuan_height,
                        false,ss);
            }
        });



    }

    public interface Pick_up_goods_Zhuce_JieKou{
        void chuan();
    }

    Pick_up_goods_Zhuce_JieKou pick_up_goods_zhuce_jieKou;

    public void setPick_up_goods_zhuce_jieKou(Pick_up_goods_Zhuce_JieKou pick_up_goods_zhuce_jieKou) {
        this.pick_up_goods_zhuce_jieKou = pick_up_goods_zhuce_jieKou;
    }



}
