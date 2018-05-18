package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by zzt on 2018/5/7.
 *
 *  首页中的资讯 bean
 */

public class Information_Zi_Bean implements Serializable{
    String[] imags;
    String top;
    String text;
    String time;

    public Information_Zi_Bean(String[] imags, String top, String text, String time) {
        this.imags = imags;
        this.top = top;
        this.text = text;
        this.time = time;
    }

    public String[] getImags() {
        return imags;
    }

    public String getTop() {
        return top;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public void setImags(String[] imags) {
        this.imags = imags;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Information_Zi_Bean{" +
                "imags=" + Arrays.toString(imags) +
                ", top='" + top + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
