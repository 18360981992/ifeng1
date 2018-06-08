package com.ifeng_tech.treasuryyitong.bean.my;

import java.io.Serializable;

/**
 * Created by zzt on 2018/6/7.
 */

public class QR_Bean implements Serializable{

    String userCode;
    GoodsInfo goodsInfo;

    public QR_Bean(String userCode, GoodsInfo goodsInfo) {
        this.userCode = userCode;
        this.goodsInfo = goodsInfo;
    }

    public QR_Bean() {

    }

    @Override
    public String toString() {
        return "QR_Bean{" +
                "userCode='" + userCode + '\'' +
                ", goodsInfo=" + goodsInfo +
                '}';
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getUserCode() {

        return userCode;
    }

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public static class GoodsInfo implements Serializable{
        private int goodsId;
        private String goodsCode;
        private String goodsName;
        private String goodsNum;

        public GoodsInfo() {
        }

        public GoodsInfo(int goodsId, String goodsCode, String goodsName, String goodsNum) {
            this.goodsId = goodsId;
            this.goodsCode = goodsCode;
            this.goodsName = goodsName;
            this.goodsNum = goodsNum;
        }

        @Override
        public String toString() {
            return "GoodsInfo{" +
                    "goodsId=" + goodsId +
                    ", goodsCode='" + goodsCode + '\'' +
                    ", goodsName='" + goodsName + '\'' +
                    ", goodsNum='" + goodsNum + '\'' +
                    '}';
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getGoodsNum() {

            return goodsNum;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getGoodsId() {

            return goodsId;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public String getGoodsName() {
            return goodsName;
        }
    }
}
