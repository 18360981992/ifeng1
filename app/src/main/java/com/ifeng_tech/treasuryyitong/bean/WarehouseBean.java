package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;

/**
 * Created by zzt on 2018/5/10.
 */

public class WarehouseBean implements Serializable{
    String word;
    int img;
    String shopping_name;
    int keyong_num;
    int dongjie_num;

    public WarehouseBean(String word, int img, String shopping_name, int keyong_num, int dongjie_num) {
        this.word = word;
        this.img = img;
        this.shopping_name = shopping_name;
        this.keyong_num = keyong_num;
        this.dongjie_num = dongjie_num;
    }

    public String getWord() {
        return word;
    }

    public int getImg() {
        return img;
    }

    public String getShopping_name() {
        return shopping_name;
    }

    public int getKeyong_num() {
        return keyong_num;
    }

    public int getDongjie_num() {
        return dongjie_num;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setShopping_name(String shopping_name) {
        this.shopping_name = shopping_name;
    }

    public void setKeyong_num(int keyong_num) {
        this.keyong_num = keyong_num;
    }

    public void setDongjie_num(int dongjie_num) {
        this.dongjie_num = dongjie_num;
    }
}
