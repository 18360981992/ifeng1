package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzt on 2018/5/18.
 */

public class Give_List_Bean implements Serializable{

    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"pageNum":1,"pageSize":10,"totalPage":3,"totalNum":25,"resultNum":10},"list":[{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348719218","orderType":0,"amount":20,"orderNo":"00001781526298139000062","addTime":1526298140000,"goodsId":782,"fee":60.5,"updateTime":1526298140000,"userName":null,"userId":178,"userCode":"000000000178","agencyName":"","oppositeUserId":123,"oppositeUserName":null,"deadDay":30,"agencyOrderNo":"","oppositeUserCode":"000000000123","oppositeAgencyName":"","id":95,"goodsCode":"003","profit":0.55,"goodsName":"吴映洁","status":2},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348662720","orderType":0,"amount":10,"orderNo":"00001231526375569060516","addTime":1526375570000,"goodsId":781,"fee":60.5,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":120,"goodsCode":"002","profit":0.55,"goodsName":"袁娅维","status":5},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348719218","orderType":0,"amount":10,"orderNo":"00001231526380171029348","addTime":1526380172000,"goodsId":782,"fee":60.5,"updateTime":1526380257000,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":123,"goodsCode":"003","profit":0.55,"goodsName":"吴映洁","status":1},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348662720","orderType":0,"amount":10,"orderNo":"00001231526535943032014","addTime":1526535944000,"goodsId":781,"fee":50,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":124,"goodsCode":"002","profit":0.55,"goodsName":"袁娅维","status":5},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348662720","orderType":0,"amount":10,"orderNo":"00001231526962885046369","addTime":1526962886000,"goodsId":781,"fee":5,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":125,"goodsCode":"002","profit":0.55,"goodsName":"袁娅维","status":1},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348719218","orderType":0,"amount":10,"orderNo":"00001231527041388047933","addTime":1527041388000,"goodsId":782,"fee":1.235598,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":126,"goodsCode":"003","profit":0.55,"goodsName":"吴映洁","status":1},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348719218","orderType":0,"amount":10,"orderNo":"00001231527041631072102","addTime":1527041632000,"goodsId":782,"fee":1.235598,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":127,"goodsCode":"003","profit":0.55,"goodsName":"吴映洁","status":1},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348662720","orderType":0,"amount":10,"orderNo":"00001231527042044032084","addTime":1527042045000,"goodsId":781,"fee":1.235598,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":252,"oppositeUserName":"","deadDay":1,"agencyOrderNo":"","oppositeUserCode":"","oppositeAgencyName":"","id":128,"goodsCode":"002","profit":0.55,"goodsName":"袁娅维","status":5},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","orderType":0,"amount":200,"orderNo":"00001231527495620087408","addTime":1527495621000,"goodsId":1374,"fee":7.5,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":254,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000254","oppositeAgencyName":"","id":145,"goodsCode":"1070020030","profit":0.55,"goodsName":"普31中国鸟（第一组）","status":2},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","orderType":0,"amount":200,"orderNo":"00001231527495641072883","addTime":1527495642000,"goodsId":1374,"fee":7.5,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":254,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000254","oppositeAgencyName":"","id":146,"goodsCode":"1070020030","profit":0.55,"goodsName":"普31中国鸟（第一组）","status":2}]}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * pageInfo : {"pageNum":1,"pageSize":10,"totalPage":3,"totalNum":25,"resultNum":10}
         * list : [{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348719218","orderType":0,"amount":20,"orderNo":"00001781526298139000062","addTime":1526298140000,"goodsId":782,"fee":60.5,"updateTime":1526298140000,"userName":null,"userId":178,"userCode":"000000000178","agencyName":"","oppositeUserId":123,"oppositeUserName":null,"deadDay":30,"agencyOrderNo":"","oppositeUserCode":"000000000123","oppositeAgencyName":"","id":95,"goodsCode":"003","profit":0.55,"goodsName":"吴映洁","status":2},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348662720","orderType":0,"amount":10,"orderNo":"00001231526375569060516","addTime":1526375570000,"goodsId":781,"fee":60.5,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":120,"goodsCode":"002","profit":0.55,"goodsName":"袁娅维","status":5},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348719218","orderType":0,"amount":10,"orderNo":"00001231526380171029348","addTime":1526380172000,"goodsId":782,"fee":60.5,"updateTime":1526380257000,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":123,"goodsCode":"003","profit":0.55,"goodsName":"吴映洁","status":1},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348662720","orderType":0,"amount":10,"orderNo":"00001231526535943032014","addTime":1526535944000,"goodsId":781,"fee":50,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":124,"goodsCode":"002","profit":0.55,"goodsName":"袁娅维","status":5},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348662720","orderType":0,"amount":10,"orderNo":"00001231526962885046369","addTime":1526962886000,"goodsId":781,"fee":5,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":125,"goodsCode":"002","profit":0.55,"goodsName":"袁娅维","status":1},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348719218","orderType":0,"amount":10,"orderNo":"00001231527041388047933","addTime":1527041388000,"goodsId":782,"fee":1.235598,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":126,"goodsCode":"003","profit":0.55,"goodsName":"吴映洁","status":1},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348719218","orderType":0,"amount":10,"orderNo":"00001231527041631072102","addTime":1527041632000,"goodsId":782,"fee":1.235598,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":178,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000178","oppositeAgencyName":"","id":127,"goodsCode":"003","profit":0.55,"goodsName":"吴映洁","status":1},{"goodsImg":"http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348662720","orderType":0,"amount":10,"orderNo":"00001231527042044032084","addTime":1527042045000,"goodsId":781,"fee":1.235598,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":252,"oppositeUserName":"","deadDay":1,"agencyOrderNo":"","oppositeUserCode":"","oppositeAgencyName":"","id":128,"goodsCode":"002","profit":0.55,"goodsName":"袁娅维","status":5},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","orderType":0,"amount":200,"orderNo":"00001231527495620087408","addTime":1527495621000,"goodsId":1374,"fee":7.5,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":254,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000254","oppositeAgencyName":"","id":145,"goodsCode":"1070020030","profit":0.55,"goodsName":"普31中国鸟（第一组）","status":2},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","orderType":0,"amount":200,"orderNo":"00001231527495641072883","addTime":1527495642000,"goodsId":1374,"fee":7.5,"updateTime":null,"userName":null,"userId":123,"userCode":"000000000123","agencyName":"","oppositeUserId":254,"oppositeUserName":null,"deadDay":1,"agencyOrderNo":"","oppositeUserCode":"000000000254","oppositeAgencyName":"","id":146,"goodsCode":"1070020030","profit":0.55,"goodsName":"普31中国鸟（第一组）","status":2}]
         */

        private PageInfoBean pageInfo;
        private List<ListBean> list;

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PageInfoBean implements Serializable{
            /**
             * pageNum : 1
             * pageSize : 10
             * totalPage : 3
             * totalNum : 25
             * resultNum : 10
             */

            private int pageNum;
            private int pageSize;
            private int totalPage;
            private int totalNum;
            private int resultNum;

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public int getResultNum() {
                return resultNum;
            }

            public void setResultNum(int resultNum) {
                this.resultNum = resultNum;
            }
        }

        public static class ListBean implements Serializable{
            /**
             * goodsImg : http://aixiaoyun.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/1511348719218
             * orderType : 0
             * amount : 20
             * orderNo : 00001781526298139000062
             * addTime : 1526298140000
             * goodsId : 782
             * fee : 60.5
             * updateTime : 1526298140000
             * userName : null
             * userId : 178
             * userCode : 000000000178
             * agencyName :
             * oppositeUserId : 123
             * oppositeUserName : null
             * deadDay : 30
             * agencyOrderNo :
             * oppositeUserCode : 000000000123
             * oppositeAgencyName :
             * id : 95
             * goodsCode : 003
             * profit : 0.55
             * goodsName : 吴映洁
             * status : 2
             */

            private String goodsImg;
            private int orderType;
            private int amount;
            private String orderNo;
            private long addTime;
            private int goodsId;
            private double fee;
            private long updateTime;
            private Object userName;
            private int userId;
            private String userCode;
            private String agencyName;
            private int oppositeUserId;
            private Object oppositeUserName;
            private int deadDay;
            private String agencyOrderNo;
            private String oppositeUserCode;
            private String oppositeAgencyName;
            private int id;
            private String goodsCode;
            private double profit;
            private String goodsName;
            private int status;

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public int getOrderType() {
                return orderType;
            }

            public void setOrderType(int orderType) {
                this.orderType = orderType;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getAgencyName() {
                return agencyName;
            }

            public void setAgencyName(String agencyName) {
                this.agencyName = agencyName;
            }

            public int getOppositeUserId() {
                return oppositeUserId;
            }

            public void setOppositeUserId(int oppositeUserId) {
                this.oppositeUserId = oppositeUserId;
            }

            public Object getOppositeUserName() {
                return oppositeUserName;
            }

            public void setOppositeUserName(Object oppositeUserName) {
                this.oppositeUserName = oppositeUserName;
            }

            public int getDeadDay() {
                return deadDay;
            }

            public void setDeadDay(int deadDay) {
                this.deadDay = deadDay;
            }

            public String getAgencyOrderNo() {
                return agencyOrderNo;
            }

            public void setAgencyOrderNo(String agencyOrderNo) {
                this.agencyOrderNo = agencyOrderNo;
            }

            public String getOppositeUserCode() {
                return oppositeUserCode;
            }

            public void setOppositeUserCode(String oppositeUserCode) {
                this.oppositeUserCode = oppositeUserCode;
            }

            public String getOppositeAgencyName() {
                return oppositeAgencyName;
            }

            public void setOppositeAgencyName(String oppositeAgencyName) {
                this.oppositeAgencyName = oppositeAgencyName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoodsCode() {
                return goodsCode;
            }

            public void setGoodsCode(String goodsCode) {
                this.goodsCode = goodsCode;
            }

            public double getProfit() {
                return profit;
            }

            public void setProfit(double profit) {
                this.profit = profit;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
