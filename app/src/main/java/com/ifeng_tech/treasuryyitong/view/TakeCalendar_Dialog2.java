package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.utils.CalendarUtil;
import com.othershe.calendarview.weiget.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zzt on 2018/6/28.
 */

public class TakeCalendar_Dialog2 extends Dialog {

    Context context;
    private CalendarView calendarView;

    private int[] cDate = CalendarUtil.getCurrentDate();

    public TakeCalendar_Dialog2(@NonNull Context context) {
        super(context);
        this.context=context;
        this.show();
    }

    public TakeCalendar_Dialog2(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context=context;
        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takecalendar_dialog);

        initData();
    }

    public void initData(){
        final TextView title = (TextView) findViewById(R.id.title);
        calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setSevenDate(new ArrayList<String>());
        calendarView
                .setSpecifyMap(new HashMap<String, String>())
                .setStartEndDate("2016.1", "2028.12")
                .setDisableStartEndDate("2016.10.10", "2028.10.10")
                .setInitDate(cDate[0] + "." + cDate[1])
                .setSingleDate(cDate[0] + "." + cDate[1] + "." + cDate[2])
                .init();
        title.setText(cDate[0] + "年" + cDate[1] + "月");

        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                title.setText(date[0] + "年" + date[1] + "月");
            }
        });

        findViewById(R.id.lastMonth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.lastMonth();
            }
        });
        findViewById(R.id.nextMonth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.nextMonth();
            }
        });

        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                title.setText(date.getSolar()[0] + "年" + date.getSolar()[1] + "月");
                String i = String.valueOf(date.getSolar()[0]);
                String i1 = String.valueOf(date.getSolar()[1]);
                String i2 = String.valueOf(date.getSolar()[2]);
                if(i1.length()==1){
                    i1="0"+i1;
                }
                if(i2.length()==1){
                    i2="0"+i2;
                }

                String key= i+ "-" +i1+ "-" +i2;

                // 设置点击时间
                if(date.getSolar()[1]<cDate[1]){
                    MyUtils.setToast("日期选择有误！");
                }else if(date.getSolar()[1] > cDate[1]){
                    dismiss();
                    onItemClick.onItemClick(key);
                }else{
                    if(date.getSolar()[2]<=cDate[2]){
                        MyUtils.setToast("日期选择有误！");
                    }else{
                        dismiss();
                        onItemClick.onItemClick(key);
                    }
                }

            }
        });

    }

    /**
     * 点击回调接口
     */
    public interface OnItemClick{
        void onItemClick(String date);
    }
    public OnItemClick onItemClick ;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
