package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;

/**
 * Created by zzt on 2018/5/24.
 */

public class Collection_directory_Bean implements Serializable{
    int img;
    String name;
    int cword;
    double price;
    int num;
    long time;


    public void setPrice(double price) {
        this.price = price;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getPrice() {

        return price;
    }

    public int getNum() {
        return num;
    }

    public long getTime() {
        return time;
    }

    public Collection_directory_Bean(int img, String name, int cword, double price, int num, long time) {

        this.img = img;
        this.name = name;
        this.cword = cword;
        this.price = price;
        this.num = num;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Collection_directory_Bean{" +
                "img=" + img +
                ", name='" + name + '\'' +
                ", cword=" + cword +
                '}';
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

    public int getImg() {

        return img;
    }

    public String getName() {
        return name;
    }

    public int getCword() {
        return cword;
    }


}
