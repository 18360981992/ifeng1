package com.ifeng_tech.treasuryyitong.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.adapter.MyPopList_Atapter;
import com.ifeng_tech.treasuryyitong.bean.GoodsListByCode_Bean;

import java.util.List;

/**
 * Created by zzt on 2018/5/17.
 */

public class Search_Pop_View extends PopupWindow {

    public View conentView;

    private final ListView search_pop_listview;
    Context context;

    public interface Search_Pop_JieKou{
        void chuan(List<GoodsListByCode_Bean.DataBean.ListBean> list,int postion);
    }

    Search_Pop_JieKou search_Pop_JieKou;

    public void setSearch_Pop_JieKou(Search_Pop_JieKou search_Pop_JieKou){
        this.search_Pop_JieKou=search_Pop_JieKou;
    }

    /**
     *
     * @param context
     * @param width  对应输入框的宽
     * @param list   数据源
     */
    public Search_Pop_View(Context context,int width, List<GoodsListByCode_Bean.DataBean.ListBean> list ) {
        super(context);
        this.context=context;
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
        this.setFocusable(false);
        this.setOutsideTouchable(false);
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
     * 更新pop框中的数据源
     * @param list
     */
    public void setPopShuJu(final List<GoodsListByCode_Bean.DataBean.ListBean> list) {

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
    public void dismiss() {
        super.dismiss();
    }

}
