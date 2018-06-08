package com.ifeng_tech.treasuryyitong.view.rili;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zzt on 2018/5/28.
 *
 *   自定义日历
 */

public class TakeCalendar_Dialog extends Dialog {

    Context context;
    private TextView tv_pre;
    private TextView tv_month;
    private TextView tv_next;
    private CalendarView calendar;
    private SimpleDateFormat formatter;
    private Date curDate;
    private Calendar cal;

    public TakeCalendar_Dialog(@NonNull Context context) {
        super(context);
        this.context=context;
        this.show();
    }

    public TakeCalendar_Dialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context=context;
        this.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takecalendar_dialog);
        initData();

        calendar.setOnSelectChangeListener(new CalendarView.OnSelectChangeListener() {
            @Override
            public void selectChange(CalendarView calendarView, Date date) {
                SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
                String format = dd.format(date);
                onItemClick.onItemClick(format);
                dismiss();
            }
        });

        tv_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH,-1);
                init();
                calendar.setCalendar(cal);
            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH,+1);
                init();
                calendar.setCalendar(cal);
            }
        });
    }


    public void initData(){
        tv_pre = (TextView) findViewById(R.id.tv_pre);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_next = (TextView) findViewById(R.id.tv_next);
        calendar = (CalendarView) findViewById(R.id.calendar);
        cal = Calendar.getInstance();
        //初始化界面
        init();
    }

    /**
     * 初始化界面
     */
    private void init() {

        formatter = new SimpleDateFormat("yyyy年MM月");
        //获取当前时间
        curDate = cal.getTime();
        String str = formatter.format(curDate);
        tv_month.setText(str);
        String strPre=(cal.get(Calendar.MONTH))+"月";
        if (strPre.equals("0月")){
            strPre="12月";
        }
        tv_pre.setText(strPre);
        String strNext=(cal.get(Calendar.MONTH)+2)+"月";
        if(strNext.equals("13月")){
            strNext="1月";
        }
        tv_next.setText(strNext);

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
