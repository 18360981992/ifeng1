package com.ifeng_tech.treasuryyitong.bean;

/**
 * Created by zzt on 2018/5/9.
 *
 * 资金明细bean
 */

public class DetailBean {

    String title;
    String time;
    String detail;

    public DetailBean(String title, String time, String detail) {
        this.title = title;
        this.time = time;
        this.detail = detail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDetail() {
        return detail;
    }
}
