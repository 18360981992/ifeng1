package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;

/**
 * Created by zzt on 2018/5/18.
 */

public class Give_List_Bean implements Serializable{

    String danhao;
    int cword;
    String name;
    int num;
    int dfuid; // 对方的uid
    long time;
    int type;

    public Give_List_Bean(String danhao, int cword, String name, int num, int dfuid, long time, int type) {
        this.danhao = danhao;
        this.cword = cword;
        this.name = name;
        this.num = num;
        this.dfuid = dfuid;
        this.time = time;
        this.type = type;
    }

    public void setDanhao(String danhao) {
        this.danhao = danhao;
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

    public void setDfuid(int dfuid) {
        this.dfuid = dfuid;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDanhao() {

        return danhao;
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

    public int getDfuid() {
        return dfuid;
    }

    public long getTime() {
        return time;
    }

    public int getType() {
        return type;
    }
}
