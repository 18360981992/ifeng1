package com.ifeng_tech.treasuryyitong.bean;

/**
 * Created by zzt on 2018/5/9.
 */

public class MyListBean {
    int img;
    String text;

    public MyListBean(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public String getText() {
        return text;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setText(String text) {
        this.text = text;
    }
}
