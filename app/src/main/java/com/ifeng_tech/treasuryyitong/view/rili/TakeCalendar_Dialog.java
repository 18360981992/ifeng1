package com.ifeng_tech.treasuryyitong.view.rili;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.TextView;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.utils.LogUtils;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.utils.CalendarUtil;
import com.othershe.calendarview.weiget.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by zzt on 2018/5/28.
 *
 *   自定义日历
 */

public class TakeCalendar_Dialog extends Dialog {

    Context context;
    private CalendarView calendarView;

    private int[] cDate = CalendarUtil.getCurrentDate();
    private List<String> sevendate;

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

        sevendate = getSevendate();
        LogUtils.i("jiba","sevendate==="+ sevendate.toString());
        initData();
    }

    /**
     * 获取今天往后30天的日期（几月几号）
     */

    public static List<String> getSevendate() {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int zuida_Day = MaxDayFromDay_OF_MONTH(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1);  // 获取当前年当前月的最大天数
        int j=1;
        for (int i = 0; i < 30; i++) {
            String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            if(mMonth.length()==1){
                mMonth="0"+mMonth;
            }
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + i);// 获取当前日份的日期号码

            if(mDay.length()==1){
                mDay="0"+mDay;
            }
//            LogUtils.i("jiba","得到当年当月的最大日期==="+ zuida_Day);
//            LogUtils.i("jiba","获取当前日份的日期号码==="+ Integer.parseInt(mDay));
            if(Integer.parseInt(mDay) > zuida_Day){
                mMonth=String.valueOf(c.get(Calendar.MONTH) + 2);
                if(mMonth.length()==1){
                    mMonth="0"+mMonth;
                }
                mDay = String.valueOf(j++);
                if(mDay.length()==1){
                    mDay="0"+mDay;
                }
                String date = mYear+"-"+mMonth + "-" + mDay ;
                String weekByDateStr = MyUtils.getWeekByDateStr(date);
                if(!weekByDateStr.equals("SUNDAY")&&!weekByDateStr.equals("SATURDAY"))
                dates.add(date);
            }else{
                String date =mYear+"-" + mMonth+ "-" + mDay ;
                String weekByDateStr = MyUtils.getWeekByDateStr(date);
                if(!weekByDateStr.equals("SUNDAY")&&!weekByDateStr.equals("SATURDAY"))
                dates.add(date);
            }
        }
        return dates;
    }

    /**得到当年当月的最大日期**/
    public static int MaxDayFromDay_OF_MONTH(int year,int month){
        Calendar time=Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR,year);
        time.set(Calendar.MONTH,month-1);//注意,Calendar对象默认一月为0
        int day=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }



    public void initData(){
        final TextView title = (TextView) findViewById(R.id.title);
        //当前选中的日期
//        chooseDate = (TextView) findViewById(R.id.choose_date);

        calendarView = (CalendarView) findViewById(R.id.calendar);
        final HashMap<String, String> map = new HashMap<>();
        map.put("2017.12.30", "休");
        map.put("2017.12.31", "休");
        map.put("2018.1.1", "休");
        map.put("2018.2.15", "休");
        map.put("2018.2.16", "休");
        map.put("2018.2.17", "休");
        map.put("2018.2.18", "休");
        map.put("2018.2.19", "休");
        map.put("2018.2.20", "休");
        map.put("2018.2.21", "休");
        map.put("2018.4.5", "休");
        map.put("2018.4.6", "休");
        map.put("2018.4.7", "休");
        map.put("2018.4.29", "休");
        map.put("2018.4.30", "休");
        map.put("2018.5.1", "休");

        map.put("2018.6.16", "休");
        map.put("2018.6.17", "休");
        map.put("2018.6.18", "休");

        map.put("2018.9.22", "休");
        map.put("2018.9.23", "休");
        map.put("2018.9.24", "休");

        map.put("2018.10.1", "休");
        map.put("2018.10.2", "休");
        map.put("2018.10.3", "休");
        map.put("2018.10.4", "休");
        map.put("2018.10.5", "休");
        map.put("2018.10.6", "休");
        map.put("2018.10.7", "休");

        map.put("2018.12.30", "休");
        map.put("2018.12.31", "休");
        map.put("2019.1.1", "休");

        calendarView.setSevenDate(sevendate);

        calendarView
                .setSpecifyMap(map)
                .setStartEndDate("2016.1", "2028.12")
                .setDisableStartEndDate("2016.10.10", "2028.10.10")
                .setInitDate(cDate[0] + "." + cDate[1])
                .setSingleDate(cDate[0] + "." + cDate[1] + "." + cDate[2])
                .init();

//        LogUtils.i("jiba","title===="+cDate[0] + "年" + cDate[1] + "月");
        title.setText(cDate[0] + "年" + cDate[1] + "月");

        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                title.setText(date[0] + "年" + date[1] + "月");
            }
        });

        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                title.setText(date.getSolar()[0] + "年" + date.getSolar()[1] + "月");
                String i = String.valueOf(date.getSolar()[0]);
                String i1 = String.valueOf(date.getSolar()[1]);
                String i2 = String.valueOf(date.getSolar()[2]);
                String key= i+ "." +i1+ "." +i2;

                String yue="";
                if(i1.length()==1){
                    yue="0"+i1;
                }else{
                    yue=i1;
                }

                String ri="";
                if(i2.length()==1){
                    ri="0"+i2;
                }else{
                    ri=i2;
                }

                String riqi=i+"-"+yue+"-"+ri;

//                LogUtils.i("jiba","==="+riqi);

                if(sevendate.contains(riqi)&&map.containsKey(key)==false){
                    if(key.equals(cDate[0] + "." + cDate[1] + "." + cDate[2])){
                        MyUtils.setToast("不在工作日内");
                    }else{
                        dismiss();
                        onItemClick.onItemClick(date.getSolar()[0] + "-" + date.getSolar()[1] + "-" + date.getSolar()[2] );
                    }

                }else{
//                    LogUtils.i("jiba","==="+riqi);
                    MyUtils.setToast("不在工作日内");
                }
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
