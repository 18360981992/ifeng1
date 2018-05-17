package com.ifeng_tech.treasuryyitong.bean;

/**
 * Created by zzt on 2018/5/17.
 */

public class Authenticate_Details_Bean {
    long time;
    int AMsy;
    int PMsy;
    double price;

    public Authenticate_Details_Bean(long time, int AMsy, int PMsy, double price) {
        this.time = time;
        this.AMsy = AMsy;
        this.PMsy = PMsy;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {

        return price;
    }

    public long getTime() {
        return time;
    }

    public int getAMsy() {
        return AMsy;
    }

    public int getPMsy() {
        return PMsy;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setAMsy(int AMsy) {
        this.AMsy = AMsy;
    }

    public void setPMsy(int PMsy) {
        this.PMsy = PMsy;
    }
}
