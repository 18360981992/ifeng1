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
     * data : {"pageInfo":{"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":9,"resultNum":9},"list":[{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070130.jpg","orderType":0,"addTime":1531118781000,"goodsId":836,"fee":0,"userCode":"000000000368","isInto":"1","oppositeUserId":368,"appraisalFeeRate":0.012,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":97,"profit":0,"goodsName":"2007-11《内蒙古自治区成立六十周年》纪念邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118781068409","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000368","oppositeAgencyName":"","goodsCode":"3010070130","remarks":"8888","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531118475000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":365,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":94,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118474046301","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000365","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531118475000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":365,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":95,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118474030578","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000365","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531118475000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":365,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":96,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118474001835","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000365","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531118319000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"1","oppositeUserId":368,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":93,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118319027720","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000368","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":1},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531116579000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":367,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":92,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531116579024587","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000367","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070130.jpg","orderType":0,"addTime":1531103555000,"goodsId":836,"fee":0,"userCode":"000000000368","isInto":"1","oppositeUserId":368,"appraisalFeeRate":0.012,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":91,"profit":0,"goodsName":"2007-11《内蒙古自治区成立六十周年》纪念邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531103554091065","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000368","oppositeAgencyName":"","goodsCode":"3010070130","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070130.jpg","orderType":0,"addTime":1530757620000,"goodsId":836,"fee":0,"userCode":"000000000368","isInto":"1","oppositeUserId":368,"appraisalFeeRate":0.012,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":90,"profit":0,"goodsName":"2007-11《内蒙古自治区成立六十周年》纪念邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681530757620055649","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000368","oppositeAgencyName":"","goodsCode":"3010070130","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1530696917000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":363,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":89,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681530696917001176","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000363","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0}]}
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
         * pageInfo : {"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":9,"resultNum":9}
         * list : [{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070130.jpg","orderType":0,"addTime":1531118781000,"goodsId":836,"fee":0,"userCode":"000000000368","isInto":"1","oppositeUserId":368,"appraisalFeeRate":0.012,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":97,"profit":0,"goodsName":"2007-11《内蒙古自治区成立六十周年》纪念邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118781068409","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000368","oppositeAgencyName":"","goodsCode":"3010070130","remarks":"8888","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531118475000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":365,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":94,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118474046301","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000365","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531118475000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":365,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":95,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118474030578","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000365","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531118475000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":365,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":96,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118474001835","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000365","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531118319000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"1","oppositeUserId":368,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":93,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531118319027720","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000368","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":1},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1531116579000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":367,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":92,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531116579024587","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000367","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070130.jpg","orderType":0,"addTime":1531103555000,"goodsId":836,"fee":0,"userCode":"000000000368","isInto":"1","oppositeUserId":368,"appraisalFeeRate":0.012,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":91,"profit":0,"goodsName":"2007-11《内蒙古自治区成立六十周年》纪念邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681531103554091065","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000368","oppositeAgencyName":"","goodsCode":"3010070130","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070130.jpg","orderType":0,"addTime":1530757620000,"goodsId":836,"fee":0,"userCode":"000000000368","isInto":"1","oppositeUserId":368,"appraisalFeeRate":0.012,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":90,"profit":0,"goodsName":"2007-11《内蒙古自治区成立六十周年》纪念邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681530757620055649","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000368","oppositeAgencyName":"","goodsCode":"3010070130","remarks":"","orderSerial":null,"status":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070010.jpg","orderType":0,"addTime":1530696917000,"goodsId":837,"fee":0,"userCode":"000000000368","isInto":"0","oppositeUserId":363,"appraisalFeeRate":0.013000000000000001,"oppositeUserName":null,"guidingPrice":5.38,"deadDay":1,"id":89,"profit":0,"goodsName":"特6-2007《中国探月首飞成功纪念》邮票","storageFeeRate":0.01,"amount":1,"orderNo":"00003681530696917001176","isTransfer":1,"updateTime":null,"userName":null,"userId":368,"agencyName":"","serialNo":"","agencyOrderNo":"","oppositeUserCode":"000000000363","oppositeAgencyName":"","goodsCode":"3010070010","remarks":"","orderSerial":null,"status":0}]
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
             * totalPage : 1
             * totalNum : 9
             * resultNum : 9
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
             * goodsImg : http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/3010070130.jpg
             * orderType : 0
             * addTime : 1531118781000
             * goodsId : 836
             * fee : 0
             * userCode : 000000000368
             * isInto : 1
             * oppositeUserId : 368
             * appraisalFeeRate : 0.012
             * oppositeUserName : null
             * guidingPrice : 5.38
             * deadDay : 1
             * id : 97
             * profit : 0
             * goodsName : 2007-11《内蒙古自治区成立六十周年》纪念邮票
             * storageFeeRate : 0.01
             * amount : 1
             * orderNo : 00003681531118781068409
             * isTransfer : 1
             * updateTime : null
             * userName : null
             * userId : 368
             * agencyName :
             * serialNo :
             * agencyOrderNo :
             * oppositeUserCode : 000000000368
             * oppositeAgencyName :
             * goodsCode : 3010070130
             * remarks : 8888
             * orderSerial : null
             * status : 0
             */

            private String goodsImg;
            private int orderType;
            private long addTime;
            private int goodsId;
            private int fee;
            private String userCode;
            private String isInto;
            private int oppositeUserId;
            private double appraisalFeeRate;
            private String oppositeUserName;
            private double guidingPrice;
            private int deadDay;
            private int id;
            private double profit;
            private String goodsName;
            private double storageFeeRate;
            private int amount;
            private String orderNo;
            private int isTransfer;
            private Object updateTime;
            private Object userName;
            private int userId;
            private String agencyName;
            private String serialNo;
            private String agencyOrderNo;
            private String oppositeUserCode;
            private String oppositeAgencyName;
            private String goodsCode;
            private String remarks;
            private Object orderSerial;
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

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public String getUserCode() {
                return userCode;
            }

            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }

            public String getIsInto() {
                return isInto;
            }

            public void setIsInto(String isInto) {
                this.isInto = isInto;
            }

            public int getOppositeUserId() {
                return oppositeUserId;
            }

            public void setOppositeUserId(int oppositeUserId) {
                this.oppositeUserId = oppositeUserId;
            }

            public double getAppraisalFeeRate() {
                return appraisalFeeRate;
            }

            public void setAppraisalFeeRate(double appraisalFeeRate) {
                this.appraisalFeeRate = appraisalFeeRate;
            }

            public String getOppositeUserName() {
                return oppositeUserName;
            }

            public void setOppositeUserName(String oppositeUserName) {
                this.oppositeUserName = oppositeUserName;
            }

            public double getGuidingPrice() {
                return guidingPrice;
            }

            public void setGuidingPrice(double guidingPrice) {
                this.guidingPrice = guidingPrice;
            }

            public int getDeadDay() {
                return deadDay;
            }

            public void setDeadDay(int deadDay) {
                this.deadDay = deadDay;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public double getStorageFeeRate() {
                return storageFeeRate;
            }

            public void setStorageFeeRate(double storageFeeRate) {
                this.storageFeeRate = storageFeeRate;
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

            public int getIsTransfer() {
                return isTransfer;
            }

            public void setIsTransfer(int isTransfer) {
                this.isTransfer = isTransfer;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
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

            public String getAgencyName() {
                return agencyName;
            }

            public void setAgencyName(String agencyName) {
                this.agencyName = agencyName;
            }

            public String getSerialNo() {
                return serialNo;
            }

            public void setSerialNo(String serialNo) {
                this.serialNo = serialNo;
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

            public String getGoodsCode() {
                return goodsCode;
            }

            public void setGoodsCode(String goodsCode) {
                this.goodsCode = goodsCode;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public Object getOrderSerial() {
                return orderSerial;
            }

            public void setOrderSerial(Object orderSerial) {
                this.orderSerial = orderSerial;
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
