package com.ifeng_tech.treasuryyitong.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.Collection_Directory_Detail_List_Adapter;
import com.ifeng_tech.treasuryyitong.api.APIs;
import com.ifeng_tech.treasuryyitong.base.BaseMVPActivity;
import com.ifeng_tech.treasuryyitong.bean.Collection_directory_Bean;
import com.ifeng_tech.treasuryyitong.bean.cangpin.Collection_Directory_Detail_Bean;
import com.ifeng_tech.treasuryyitong.interfaces.MyInterfaces;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.ifeng_tech.treasuryyitong.view.MyListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 藏品详情
 */
public class Collection_Directory_Detail_Activity extends BaseMVPActivity<Collection_Directory_Detail_Activity, MyPresenter<Collection_Directory_Detail_Activity>> {

    private ImageView collection_mulu_detail_Fan;
    private TextView collocation_mulu_detail_guige;
    private TextView collocation_mulu_detail_shoujia;
    private TextView collocation_mulu_detail_faxingliang;
    private TextView collocation_mulu_detail_jianjie;
    private TextView collocation_mulu_detail_name;
    private TextView collocation_mulu_detail_cword;
    private Collection_directory_Bean collection_directory_bean;
    private String goodsId;
    private ImageView collocation_mulu_detail_img;
    private MyListView collocation_mulu_detail_listview;
    private LinearLayout collocation_mulu_detail_listview_linearLayout;
    private View collocation_mulu_detail_View;
    private LinearLayout collocation_mulu_detail_listview_LinearLayout;
    private LinearLayout collocation_mulu_detail_youshuju;
    private LinearLayout collocation_mulu_detail_null;

    @Override
    public MyPresenter<Collection_Directory_Detail_Activity> initPresenter() {
        if (myPresenter == null) {
            myPresenter = new MyPresenter();
        }
        return myPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection__directory__detail_);
        initView();
        collection_mulu_detail_Fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        goodsId = getIntent().getStringExtra("goodsId");

    }

    // 获取状态栏高度
    private int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    @Override
    protected void onResume() {
        super.onResume();

//        Map<String, String> map = new HashMap<>();
//        map.put("commodityId",goodsId);
        myPresenter.getPreContent(APIs.getonestamp + goodsId, new MyInterfaces() {
            @Override
            public void chenggong(String json) {
                collocation_mulu_detail_youshuju.setVisibility(View.VISIBLE);
                collocation_mulu_detail_null.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String code = (String) jsonObject.get("code");
                    if (code.equals("2000")) {
                        Collection_Directory_Detail_Bean collection_directory_detail_bean = new Gson().fromJson(json, Collection_Directory_Detail_Bean.class);

                        if (collection_directory_detail_bean.getData().getCommodity().getCommodityLink().size() <= 0) {
                            collocation_mulu_detail_img.setImageResource(R.drawable.img_erroy);
                        } else {
                            Glide.with(Collection_Directory_Detail_Activity.this).load(collection_directory_detail_bean.getData().getCommodity().getCommodityLink().get(0)).error(R.drawable.img_erroy).into(collocation_mulu_detail_img);
                        }

                        collocation_mulu_detail_name.setText("藏品名称：" + collection_directory_detail_bean.getData().getCommodity().getCommodityName());

                        collocation_mulu_detail_cword.setText("编号：" + collection_directory_detail_bean.getData().getCommodity().getCommodityCode());
                        final List<Collection_Directory_Detail_Bean.DataBean.DescriptionListBean> list = collection_directory_detail_bean.getData().getDescriptionList();
                        if (list.size() > 0) {
                            collocation_mulu_detail_listview_linearLayout.setVisibility(View.VISIBLE);
                            collocation_mulu_detail_listview.setAdapter(new Collection_Directory_Detail_List_Adapter(Collection_Directory_Detail_Activity.this, list));
                        } else {
                            collocation_mulu_detail_listview_linearLayout.setVisibility(View.GONE);
                        }

                        if (collection_directory_detail_bean.getData().getCommodity().getCommodityDetails() == null) {
                            collocation_mulu_detail_jianjie.setText("2007年11月26日发行，全套1枚（1-1）帖特6-2007全套1枚邮票 销售日纪念邮戳，首日封、戳设计者：陈曼株");
                        } else {
                            collocation_mulu_detail_jianjie.setText(collection_directory_detail_bean.getData().getCommodity().getCommodityDetails());
                        }
                    } else {
                        MyUtils.setToast((String) jsonObject.get("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void shibai(String ss) {
                collocation_mulu_detail_youshuju.setVisibility(View.GONE);
                collocation_mulu_detail_null.setVisibility(View.VISIBLE);
//                MyUtils.setToast("" + ss);
            }
        });

    }

    private void initView() {
        View collocation_mulu_detail_View = (View) findViewById(R.id.collocation_mulu_detail_View);
        collection_mulu_detail_Fan = (ImageView) findViewById(R.id.collection_mulu_detail_Fan);
        collocation_mulu_detail_img = (ImageView) findViewById(R.id.collocation_mulu_detail_img);
        collocation_mulu_detail_name = (TextView) findViewById(R.id.collocation_mulu_detail_name);
        collocation_mulu_detail_cword = (TextView) findViewById(R.id.collocation_mulu_detail_cword);
//        collocation_mulu_detail_guige = (TextView) findViewById(R.id.collocation_mulu_detail_guige);
//        collocation_mulu_detail_shoujia = (TextView) findViewById(R.id.collocation_mulu_detail_shoujia);
//        collocation_mulu_detail_faxingliang = (TextView) findViewById(R.id.collocation_mulu_detail_faxingliang);
        collocation_mulu_detail_listview_linearLayout = (LinearLayout) findViewById(R.id.collocation_mulu_detail_listview_LinearLayout);
        collocation_mulu_detail_listview = (MyListView) findViewById(R.id.collocation_mulu_detail_listview);
        collocation_mulu_detail_jianjie = (TextView) findViewById(R.id.collocation_mulu_detail_jianjie);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) collocation_mulu_detail_View.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
            linearParams.height = getStatusBarHeight(Collection_Directory_Detail_Activity.this);// 控件的宽强制设成30
            collocation_mulu_detail_View.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        collocation_mulu_detail_youshuju = (LinearLayout) findViewById(R.id.collocation_mulu_detail_youshuju);
        collocation_mulu_detail_null = (LinearLayout) findViewById(R.id.collocation_mulu_detail_null);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.xiao_in_kuai, R.anim.xiao_out_kuai);
    }
}
