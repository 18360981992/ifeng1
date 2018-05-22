package com.ifeng_tech.treasuryyitong.bean;

/**
 * Created by zzt on 2018/5/22.
 */

public class Message_Lists_Bean {
    long time;
    String title;
    String text;

    @Override
    public String toString() {
        return "Message_Lists_Bean{" +
                "time=" + time +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {

        return title;
    }

    public Message_Lists_Bean(long time, String title, String text) {

        this.time = time;
        this.title = title;
        this.text = text;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {

        return time;
    }

    public String getText() {
        return text;
    }
}
