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
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.MyPopList_Atapter;
import com.ifeng_tech.treasuryyitong.bean.tihuo.DepotListByGoodsId_Bean;
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


    public interface Search_Pop_JieKou{
        void chuan(List<DepotListByGoodsId_Bean.DataBean.ListBean> list,int postion);
    }

    Search_Pop_JieKou search_Pop_JieKou;

    public void setSearch_Pop_JieKou(Search_Pop_JieKou search_Pop_JieKou){
        this.search_Pop_JieKou=search_Pop_JieKou;
    }


    public interface Search_Pop_JieKou_String{
        void chuan(List<String> list,int postion);
    }

    Search_Pop_JieKou_String search_Pop_JieKou_String;

    public void setSearch_Pop_JieKou_String(Search_Pop_JieKou_String search_Pop_JieKou_String){
        this.search_Pop_JieKou_String=search_Pop_JieKou_String;
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
        search_pop_listview = conentView.findViewById(R.id.search_pop_listview);

        setPopShuJu(list);

    }

    /**
     *
     * @param context
     * @param width  对应输入框的宽
     * @param list   数据源
     */
    public void Search_Pop_View_String(final Context context, int width, final List<String> list, ImageView imageView) {
        this.context=context;
        this.imageView=imageView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.search_pop_view, null);
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
        search_pop_listview = conentView.findViewById(R.id.search_pop_listview);

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
                pop_list_text.setText(list.get(position));

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
