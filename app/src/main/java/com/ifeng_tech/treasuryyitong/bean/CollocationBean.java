package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;

/**
 * Created by zzt on 2018/5/18.
 *
 *  托管bean
 */

public class CollocationBean implements Serializable{

    int img;
    String name;
    int cword;
    long time;
    String text;
    int type;

    public CollocationBean(int img, String name, int cword, long time, String text, int type) {
        this.img = img;
        this.name = name;
        this.cword = cword;
        this.time = time;
        this.text = text;
        this.type = type;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCword(int cword) {
        this.cword = cword;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getImg() {

        return img;
    }

    public String getName() {
        return name;
    }

    public int getCword() {
        return cword;
    }

    public long getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public int getType() {
        return type;
    }
}
