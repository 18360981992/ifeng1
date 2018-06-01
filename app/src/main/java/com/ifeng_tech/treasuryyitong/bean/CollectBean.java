package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;

/**
 * Created by zzt on 2018/4/27.
 *
 *  征集bean
 */

public class CollectBean implements Serializable{
    int img;
    String name;
    int cword;
    String title;
    String text;
    int type;

    public CollectBean(int img, String name, int cword, String title, String text, int type) {
        this.img = img;
        this.name = name;
        this.cword = cword;
        this.title = title;
        this.text = text;
        this.type = type;
    }

    public void setCword(int cword) {
        this.cword = cword;
    }

    public int getCword() {

        return cword;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
                ", cword=" + cword +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}
