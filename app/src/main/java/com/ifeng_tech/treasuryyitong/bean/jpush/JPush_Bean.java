package com.ifeng_tech.treasuryyitong.bean.jpush;

/**
 * Created by zzt on 2018/6/28.
 */

public class JPush_Bean {
    /**
     * safeType : 2   安全设置
     * sysType : 0   系统消息
     * sysNum : 0
     * safeNum : 5
     * goldSum : 0
     * goldType : 1   资金类消息
     */

    private int safeType;
    private int sysType;
    private int sysNum;
    private int safeNum;
    private int goldSum;
    private int goldType;

    public int getSafeType() {
        return safeType;
    }

    public void setSafeType(int safeType) {
        this.safeType = safeType;
    }

    public int getSysType() {
        return sysType;
    }

    public void setSysType(int sysType) {
        this.sysType = sysType;
    }

    public int getSysNum() {
        return sysNum;
    }

    public void setSysNum(int sysNum) {
        this.sysNum = sysNum;
    }

    public int getSafeNum() {
        return safeNum;
    }

    public void setSafeNum(int safeNum) {
        this.safeNum = safeNum;
    }

    public int getGoldSum() {
        return goldSum;
    }

    public void setGoldSum(int goldSum) {
        this.goldSum = goldSum;
    }

    public int getGoldType() {
        return goldType;
    }

    public void setGoldType(int goldType) {
        this.goldType = goldType;
    }
}

