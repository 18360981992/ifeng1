package com.ifeng_tech.treasuryyitong.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.MyPopList_Atapter;
import com.ifeng_tech.treasuryyitong.bean.tihuo.DepotListByGoodsId_Bean;
import com.ifeng_tech.treasuryyitong.bean.weituo.Entrust_ClientGoodsByClientCode_Bean;
import com.ifeng_tech.treasuryyitong.bean.weituo.Entrust_Client_Bean;
import com.ifeng_tech.treasuryyitong.pull.ILoadingLayout;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshBase;
import com.ifeng_tech.treasuryyitong.pull.PullToRefreshScrollView;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;

import java.util.List;

/**
 * Created by zzt on 2018/5/17.
 */

public class Search_Pop_View extends PopupWindow {

    public View conentView;

    private ListView search_pop_listview;
    Context context;
    ImageView imageView;
    private MyListView search_pop_shua_myListView;


    public interface Search_Pop_JieKou{
        void chuan(List<DepotListByGoodsId_Bean.DataBean.ListBean> list,int postion);
    }

    Search_Pop_JieKou search_Pop_JieKou;

    public void setSearch_Pop_JieKou(Search_Pop_JieKou search_Pop_JieKou){
        this.search_Pop_JieKou=search_Pop_JieKou;
    }


    // 第三方平台的选择
    public interface Search_Pop_JieKou_String{
        void chuan(List<Entrust_Client_Bean.DataBean.ClientBean> list,int postion);
    }

    Search_Pop_JieKou_String search_Pop_JieKou_String;

    public void setSearch_Pop_JieKou_String(Search_Pop_JieKou_String search_Pop_JieKou_String){
        this.search_Pop_JieKou_String=search_Pop_JieKou_String;
    }

    // 藏品名称的选择
    public interface Search_Pop_Code_JieKou_String{
        void chuan(List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> list,int postion);
    }

    Search_Pop_Code_JieKou_String search_Pop_Code_JieKou_String;

    public void setSearch_Pop_Code_JieKou_String(Search_Pop_Code_JieKou_String search_Pop_Code_JieKou_String){
        this.search_Pop_Code_JieKou_String=search_Pop_Code_JieKou_String;
    }

    // 藏品名称上拉加载更多的接口回调
    public interface Search_Pop_Code_Shua_JieKou{
        void chuan(PullToRefreshScrollView search_pop_shua_pulltoscroll);
    }

    Search_Pop_Code_Shua_JieKou search_Pop_Code_Shua_JieKou;

    public void setSearch_Pop_Code_Shua_JieKou(Search_Pop_Code_Shua_JieKou search_Pop_Code_Shua_JieKou){
        this.search_Pop_Code_Shua_JieKou=search_Pop_Code_Shua_JieKou;
    }

    public Search_Pop_View(Context context) {
        super(context);
        this.context = context;
    }

    /**
     *
     * @param context
     * @param width  对应输入框的宽
     * @param list   数据源
     */
    public Search_Pop_View(Context context, int width, List<DepotListByGoodsId_Bean.DataBean.ListBean> list, ImageView imageView) {
        super(context);
        this.context=context;
        this.imageView=imageView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.search_pop_view, null);
        search_pop_listview = conentView.findViewById(R.id.search_pop_listview);
        if(list.size()>5){
            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) search_pop_listview.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
            linearParams.height = 400;// 控件的高强制设成400
//        LogUtils.i("wc","状态栏高度===="+getStatusBarHeight(Shop_Detailed_Datail_Activity.this));
            search_pop_listview.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        int h = context.getResources().getDisplayMetrics().heightPixels; // 屏幕的高度
        int w = context.getResources().getDisplayMetrics().widthPixels; // 屏幕的宽度
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击,  还有一个作用可以使pop框不抢占焦点
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#00000000"));
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

        setPopShuJu(list);

    }

    /**
     *
     *  委托入库中的平台选择
     * @param context
     * @param width  对应输入框的宽
     * @param list   数据源
     */
    public void Search_Pop_View_String(final Context context, int width, final List<Entrust_Client_Bean.DataBean.ClientBean> list, ImageView imageView) {

        this.context=context;
        this.imageView=imageView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.search_pop_view, null);
        search_pop_listview = conentView.findViewById(R.id.search_pop_listview);
        int h = context.getResources().getDisplayMetrics().heightPixels; // 屏幕的高度
        int w = context.getResources().getDisplayMetrics().widthPixels; // 屏幕的宽度
        if(list.size()>5){
            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) search_pop_listview.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
            linearParams.height = 400;// 控件的高强制设成400
//        LogUtils.i("wc","状态栏高度===="+getStatusBarHeight(Shop_Detailed_Datail_Activity.this));
            search_pop_listview.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击,  还有一个作用可以使pop框不抢占焦点
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#00000000"));
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);


        search_pop_listview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView=View.inflate(context, R.layout.pop_list_item,null);
                }
                TextView pop_list_text = convertView.findViewById(R.id.pop_list_text);
                pop_list_text.setText(list.get(position).getClientName());

                return convertView;
            }
        });

        search_pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                search_Pop_JieKou_String.chuan(list,position);
                dismiss();
            }
        });

    }

    /**
     *
     *  委托入库中藏品名称选择
     * @param context
     * @param width  对应输入框的宽
     * @param list   数据源
     */
    public void Search_Pop_View_Code(final Context context, int width, final List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> list, ImageView imageView) {
        this.context=context;
        this.imageView=imageView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.search_pop_view_shua, null);
        // 自定义listview
        search_pop_shua_myListView = conentView.findViewById(R.id.search_pop_shua_MyListView);
        final PullToRefreshScrollView search_pop_shua_pulltoscroll = conentView.findViewById(R.id.search_pop_shua_pulltoscroll); //
        initRefreshListView(search_pop_shua_pulltoscroll);
        int h = context.getResources().getDisplayMetrics().heightPixels; // 屏幕的高度
        int w = context.getResources().getDisplayMetrics().widthPixels; // 屏幕的宽度
        if(list.size()>5){
            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) search_pop_shua_pulltoscroll.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
            linearParams.height = 400;// 控件的高强制设成400
//        LogUtils.i("wc","状态栏高度===="+getStatusBarHeight(Shop_Detailed_Datail_Activity.this));
            search_pop_shua_pulltoscroll.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(width);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击,  还有一个作用可以使pop框不抢占焦点
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#00000000"));
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

        setPop_Shua_Adapter(context, list);

        search_pop_shua_myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search_Pop_Code_JieKou_String.chuan(list,position);
                dismiss();
            }
        });

        //  上拉加载更多
        search_pop_shua_pulltoscroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                search_Pop_Code_Shua_JieKou.chuan(search_pop_shua_pulltoscroll);
            }
        });




    }

    // 设置上拉加载更多的适配器
    public void setPop_Shua_Adapter(final Context context, final List<Entrust_ClientGoodsByClientCode_Bean.DataBean.ListBean> list) {
        search_pop_shua_myListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView=View.inflate(context, R.layout.pop_list_item,null);
                }
                TextView pop_list_text = convertView.findViewById(R.id.pop_list_text);
                pop_list_text.setText(list.get(position).getCommodityName());

                return convertView;
            }
        });
    }


    private void initRefreshListView(PullToRefreshScrollView my_collocation_pulltoscroll) {
        /*设置pullToRefreshListView的刷新模式，BOTH代表支持上拉和下拉，PULL_FROM_END代表上拉,PULL_FROM_START代表下拉 */
        my_collocation_pulltoscroll.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
//        ILoadingLayout Labels = my_collocation_pulltoscroll.getLoadingLayoutProxy(true, false);
//        Labels.setPullLabel("下拉刷新...");
//        Labels.setRefreshingLabel("正在刷新...");
//        Labels.setReleaseLabel("放开刷新...");

        ILoadingLayout Labels1 = my_collocation_pulltoscroll.getLoadingLayoutProxy(false, true);
        Labels1.setPullLabel("上拉加载...");
        Labels1.setRefreshingLabel("正在加载...");
        Labels1.setReleaseLabel("放开刷新...");
    }


    /**
     * 更新pop框中的数据源
     * @param list
     */
    public void setPopShuJu(final List<DepotListByGoodsId_Bean.DataBean.ListBean> list) {

        search_pop_listview.setAdapter(new MyPopList_Atapter(context,list));

        search_pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                search_Pop_JieKou.chuan(list,position);
                dismiss();
            }
        });
    }


    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        MyUtils.rotateArrow(imageView,true);  // 箭头向上
    }

    @Override
    public void dismiss() {
        super.dismiss();
        MyUtils.rotateArrow(imageView,false);  // 箭头向下
    }

}
