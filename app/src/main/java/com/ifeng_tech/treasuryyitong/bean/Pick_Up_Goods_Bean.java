package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;

/**
 * Created by zzt on 2018/5/23.
 */

public class Pick_Up_Goods_Bean implements Serializable{

    String dword;
    int cword;
    String name;
    int num;
    double price;
    long time;
    int type;

    public Pick_Up_Goods_Bean(String dword, int cword, String name, int num, double price, long time, int type) {
        this.dword = dword;
        this.cword = cword;
        this.name = name;
        this.num = num;
        this.price = price;
        this.time = time;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pick_Up_Goods_Bean{" +
                "dword='" + dword + '\'' +
                ", cword=" + cword +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", time=" + time +
                ", type=" + type +
                '}';
    }

    public void setDword(String dword) {
        this.dword = dword;
    }

    public void setCword(int cword) {
        this.cword = cword;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDword() {

        return dword;
    }

    public int getCword() {
        return cword;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public double getPrice() {
        return price;
    }

    public long getTime() {
        return time;
    }

    public int getType() {
        return type;
    }
}
