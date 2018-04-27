package com.ifeng_tech.treasuryyitong.bean;

/**
 * Created by zzt on 2018/4/27.
 */

public class CollectBean {
    int img;
    String name;
    String title;
    String text;

    public CollectBean(int img, String name, String title, String text) {
        this.img = img;
        this.name = name;
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CollectBean{" +
                "img=" + img +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
