package com.ifeng_tech.treasuryyitong.bean;

/**
 * Created by zzt on 2018/5/10.
 *
 *  入库bean
 */

public class Incoming_bean {

    String trade_name;
    String word;
    String shopping_name;
    int num;
    String type;
    String time;

    public Incoming_bean(String trade_name, String word, String shopping_name, int num, String type, String time) {
        this.trade_name = trade_name;
        this.word = word;
        this.shopping_name = shopping_name;
        this.num = num;
        this.type = type;
        this.time = time;
    }

    public String getTrade_name() {
        return trade_name;
    }

    public String getWord() {
        return word;
    }

    public String getShopping_name() {
        return shopping_name;
    }

    public int getNum() {
        return num;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public void setTrade_name(String trade_name) {
        this.trade_name = trade_name;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setShopping_name(String shopping_name) {
        this.shopping_name = shopping_name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
