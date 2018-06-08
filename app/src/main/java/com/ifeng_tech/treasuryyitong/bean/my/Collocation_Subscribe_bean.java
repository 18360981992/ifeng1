package com.ifeng_tech.treasuryyitong.bean.my;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzt on 2018/6/4.
 */

public class Collocation_Subscribe_bean implements Serializable{

    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":4,"resultNum":4},"list":[{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":null,"trusteeTime":null,"addTime":null,"goodsId":836,"applyBeginTime":null,"pmORam":null,"number":1234,"minNumber":11,"inhouseFee":0.02,"deleteStatus":null,"price":null,"commodityCode":null,"id":88,"state":"1","beginTime":null,"applyTime":"2018-06-04/2018-06-30","goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":null,"orderNo":null,"passnumber":null,"pictureUrl":null,"count":0,"articleId":null,"commodityId":836,"collectionGuidePrice":50,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":20,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":null,"trusteeTime":null,"addTime":null,"goodsId":836,"applyBeginTime":null,"pmORam":null,"number":888,"minNumber":7,"inhouseFee":0.02,"deleteStatus":null,"price":null,"commodityCode":null,"id":87,"state":"1","beginTime":null,"applyTime":"2018-06-04/2018-06-08","goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":null,"orderNo":null,"passnumber":null,"pictureUrl":null,"count":0,"articleId":null,"commodityId":836,"collectionGuidePrice":50,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":11,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg","applyEndTime":null,"trusteeTime":null,"addTime":null,"goodsId":839,"applyBeginTime":null,"pmORam":null,"number":100,"minNumber":3,"inhouseFee":0.01,"deleteStatus":null,"price":null,"commodityCode":null,"id":85,"state":"1","beginTime":null,"applyTime":"2018-06-01/2018-06-06","goodsName":"2007-2《第六届亚洲冬季运动会》纪念邮票","appraisalFee":0.4,"address":null,"orderNo":null,"passnumber":null,"pictureUrl":null,"count":0,"articleId":null,"commodityId":839,"collectionGuidePrice":20,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":20,"endTime":null,"goodsCode":"3010070030","commodityName":null},{"insurance":null,"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1079900020-1.jpg","applyEndTime":null,"trusteeTime":null,"addTime":null,"goodsId":1364,"applyBeginTime":null,"pmORam":null,"number":1000,"minNumber":5,"inhouseFee":0.1,"deleteStatus":null,"price":null,"commodityCode":null,"id":84,"state":"1","beginTime":null,"applyTime":"2018-05-31/2018-06-07","goodsName":"普26民居","appraisalFee":17.2,"address":null,"orderNo":null,"passnumber":null,"pictureUrl":null,"count":305,"articleId":null,"commodityId":1364,"collectionGuidePrice":86,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":20,"endTime":null,"goodsCode":"1079900020","commodityName":null}]}
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
         * pageInfo : {"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":4,"resultNum":4}
         * list : [{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":null,"trusteeTime":null,"addTime":null,"goodsId":836,"applyBeginTime":null,"pmORam":null,"number":1234,"minNumber":11,"inhouseFee":0.02,"deleteStatus":null,"price":null,"commodityCode":null,"id":88,"state":"1","beginTime":null,"applyTime":"2018-06-04/2018-06-30","goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":null,"orderNo":null,"passnumber":null,"pictureUrl":null,"count":0,"articleId":null,"commodityId":836,"collectionGuidePrice":50,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":20,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg","applyEndTime":null,"trusteeTime":null,"addTime":null,"goodsId":836,"applyBeginTime":null,"pmORam":null,"number":888,"minNumber":7,"inhouseFee":0.02,"deleteStatus":null,"price":null,"commodityCode":null,"id":87,"state":"1","beginTime":null,"applyTime":"2018-06-04/2018-06-08","goodsName":"2007-10《中国话剧诞生一百周年》纪念邮票11","appraisalFee":0.5,"address":null,"orderNo":null,"passnumber":null,"pictureUrl":null,"count":0,"articleId":null,"commodityId":836,"collectionGuidePrice":50,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":11,"endTime":null,"goodsCode":"3010070130","commodityName":null},{"insurance":null,"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg","applyEndTime":null,"trusteeTime":null,"addTime":null,"goodsId":839,"applyBeginTime":null,"pmORam":null,"number":100,"minNumber":3,"inhouseFee":0.01,"deleteStatus":null,"price":null,"commodityCode":null,"id":85,"state":"1","beginTime":null,"applyTime":"2018-06-01/2018-06-06","goodsName":"2007-2《第六届亚洲冬季运动会》纪念邮票","appraisalFee":0.4,"address":null,"orderNo":null,"passnumber":null,"pictureUrl":null,"count":0,"articleId":null,"commodityId":839,"collectionGuidePrice":20,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":20,"endTime":null,"goodsCode":"3010070030","commodityName":null},{"insurance":null,"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1079900020-1.jpg","applyEndTime":null,"trusteeTime":null,"addTime":null,"goodsId":1364,"applyBeginTime":null,"pmORam":null,"number":1000,"minNumber":5,"inhouseFee":0.1,"deleteStatus":null,"price":null,"commodityCode":null,"id":84,"state":"1","beginTime":null,"applyTime":"2018-05-31/2018-06-07","goodsName":"普26民居","appraisalFee":17.2,"address":null,"orderNo":null,"passnumber":null,"pictureUrl":null,"count":305,"articleId":null,"commodityId":1364,"collectionGuidePrice":86,"fakepassrate":null,"warehousingFee":null,"unit":null,"firmId":null,"leaseFate":20,"endTime":null,"goodsCode":"1079900020","commodityName":null}]
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
             * totalNum : 4
             * resultNum : 4
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
             * insurance : null
             * goodsImg : http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070120.jpg
             * applyEndTime : null
             * trusteeTime : null
             * addTime : null
             * goodsId : 836
             * applyBeginTime : null
             * pmORam : null
             * number : 1234
             * minNumber : 11
             * inhouseFee : 0.02
             * deleteStatus : null
             * price : null
             * commodityCode : null
             * id : 88
             * state : 1
             * beginTime : null
             * applyTime : 2018-06-04/2018-06-30
             * goodsName : 2007-10《中国话剧诞生一百周年》纪念邮票11
             * appraisalFee : 0.5
             * address : null
             * orderNo : null
             * passnumber : null
             * pictureUrl : null
             * count : 0
             * articleId : null
             * commodityId : 836
             * collectionGuidePrice : 50
             * fakepassrate : null
             * warehousingFee : null
             * unit : null
             * firmId : null
             * leaseFate : 20
             * endTime : null
             * goodsCode : 3010070130
             * commodityName : null
             */

            private Object insurance;
            private String goodsImg;
            private Object applyEndTime;
            private Object trusteeTime;
            private Object addTime;
            private int goodsId;
            private Object applyBeginTime;
            private Object pmORam;
            private int number;
            private int minNumber;
            private double inhouseFee;
            private Object deleteStatus;
            private Object price;
            private Object commodityCode;
            private int id;
            private String state;
            private Object beginTime;
            private String applyTime;
            private String goodsName;
            private double appraisalFee;
            private Object address;
            private Object orderNo;
            private Object passnumber;
            private Object pictureUrl;
            private int count;
            private Object articleId;
            private int commodityId;
            private int collectionGuidePrice;
            private Object fakepassrate;
            private Object warehousingFee;
            private Object unit;
            private Object firmId;
            private int leaseFate;
            private Object endTime;
            private String goodsCode;
            private Object commodityName;

            public Object getInsurance() {
                return insurance;
            }

            public void setInsurance(Object insurance) {
                this.insurance = insurance;
            }

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public Object getApplyEndTime() {
                return applyEndTime;
            }

            public void setApplyEndTime(Object applyEndTime) {
                this.applyEndTime = applyEndTime;
            }

            public Object getTrusteeTime() {
                return trusteeTime;
            }

            public void setTrusteeTime(Object trusteeTime) {
                this.trusteeTime = trusteeTime;
            }

            public Object getAddTime() {
                return addTime;
            }

            public void setAddTime(Object addTime) {
                this.addTime = addTime;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public Object getApplyBeginTime() {
                return applyBeginTime;
            }

            public void setApplyBeginTime(Object applyBeginTime) {
                this.applyBeginTime = applyBeginTime;
            }

            public Object getPmORam() {
                return pmORam;
            }

            public void setPmORam(Object pmORam) {
                this.pmORam = pmORam;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getMinNumber() {
                return minNumber;
            }

            public void setMinNumber(int minNumber) {
                this.minNumber = minNumber;
            }

            public double getInhouseFee() {
                return inhouseFee;
            }

            public void setInhouseFee(double inhouseFee) {
                this.inhouseFee = inhouseFee;
            }

            public Object getDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(Object deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(Object commodityCode) {
                this.commodityCode = commodityCode;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public Object getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(Object beginTime) {
                this.beginTime = beginTime;
            }

            public String getApplyTime() {
                return applyTime;
            }

            public void setApplyTime(String applyTime) {
                this.applyTime = applyTime;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public double getAppraisalFee() {
                return appraisalFee;
            }

            public void setAppraisalFee(double appraisalFee) {
                this.appraisalFee = appraisalFee;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(Object orderNo) {
                this.orderNo = orderNo;
            }

            public Object getPassnumber() {
                return passnumber;
            }

            public void setPassnumber(Object passnumber) {
                this.passnumber = passnumber;
            }

            public Object getPictureUrl() {
                return pictureUrl;
            }

            public void setPictureUrl(Object pictureUrl) {
                this.pictureUrl = pictureUrl;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public Object getArticleId() {
                return articleId;
            }

            public void setArticleId(Object articleId) {
                this.articleId = articleId;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public int getCollectionGuidePrice() {
                return collectionGuidePrice;
            }

            public void setCollectionGuidePrice(int collectionGuidePrice) {
                this.collectionGuidePrice = collectionGuidePrice;
            }

            public Object getFakepassrate() {
                return fakepassrate;
            }

            public void setFakepassrate(Object fakepassrate) {
                this.fakepassrate = fakepassrate;
            }

            public Object getWarehousingFee() {
                return warehousingFee;
            }

            public void setWarehousingFee(Object warehousingFee) {
                this.warehousingFee = warehousingFee;
            }

            public Object getUnit() {
                return unit;
            }

            public void setUnit(Object unit) {
                this.unit = unit;
            }

            public Object getFirmId() {
                return firmId;
            }

            public void setFirmId(Object firmId) {
                this.firmId = firmId;
            }

            public int getLeaseFate() {
                return leaseFate;
            }

            public void setLeaseFate(int leaseFate) {
                this.leaseFate = leaseFate;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public String getGoodsCode() {
                return goodsCode;
            }

            public void setGoodsCode(String goodsCode) {
                this.goodsCode = goodsCode;
            }

            public Object getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(Object commodityName) {
                this.commodityName = commodityName;
            }
        }
    }
}
