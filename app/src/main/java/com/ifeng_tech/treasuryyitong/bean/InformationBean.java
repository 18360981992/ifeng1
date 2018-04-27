package com.ifeng_tech.treasuryyitong.bean;

/**
 * Created by zzt on 2018/4/27.
 */

public class InformationBean {
    String title;
    String text;
    String time;

    public InformationBean(String title, String text, String time) {
        this.title = title;
        this.text = text;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(String time) {
        this.time = time;
    }
}