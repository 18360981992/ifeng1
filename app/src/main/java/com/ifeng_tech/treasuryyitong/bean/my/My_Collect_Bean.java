package com.ifeng_tech.treasuryyitong.bean.my;

import java.util.List;

/**
 * Created by zzt on 2018/5/31.
 */

public class My_Collect_Bean {


    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":2,"resultNum":2},"list":[{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","publishTime":1526313600000,"amount":10,"addTime":1526380693000,"oppAccount":"11","goodsId":1374,"userPhone":"1234444","upsideFee":100,"agencyId":145,"agencyCode":"0058","userId":2,"mechanismId":"0058","agencyName":"福利特寄卖平台","publishId":107,"applyNo":"1920cbb5-207c-429e-9067-b480d8bd896f","commodityCode":"1070020030","userIp":"192.168.1.16","collectCode":"C0001477795201805151833119507","id":77,"applyCode":"0000000002201805151838131849","goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）","status":4},{"goodsImg":"0104980249-1.jpg","publishTime":1526227200000,"amount":3,"addTime":1526296767000,"oppAccount":"2","goodsId":1303,"userPhone":"123465","upsideFee":1.5,"agencyId":111,"agencyCode":"0046","userId":2,"mechanismId":"0046","agencyName":"13121838011","publishId":90,"applyNo":"38593c9c-9aea-49ce-a61f-03126b10addb","commodityCode":"1049802490","userIp":"","collectCode":"C0001477762201805141918024171","id":48,"applyCode":"0000000002201805141919271922","goodsCode":"1049802490","goodsName":"T49邮政运输","status":2}]}
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

    public static class DataBean {
        /**
         * pageInfo : {"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":2,"resultNum":2}
         * list : [{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","publishTime":1526313600000,"amount":10,"addTime":1526380693000,"oppAccount":"11","goodsId":1374,"userPhone":"1234444","upsideFee":100,"agencyId":145,"agencyCode":"0058","userId":2,"mechanismId":"0058","agencyName":"福利特寄卖平台","publishId":107,"applyNo":"1920cbb5-207c-429e-9067-b480d8bd896f","commodityCode":"1070020030","userIp":"192.168.1.16","collectCode":"C0001477795201805151833119507","id":77,"applyCode":"0000000002201805151838131849","goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）","status":4},{"goodsImg":"0104980249-1.jpg","publishTime":1526227200000,"amount":3,"addTime":1526296767000,"oppAccount":"2","goodsId":1303,"userPhone":"123465","upsideFee":1.5,"agencyId":111,"agencyCode":"0046","userId":2,"mechanismId":"0046","agencyName":"13121838011","publishId":90,"applyNo":"38593c9c-9aea-49ce-a61f-03126b10addb","commodityCode":"1049802490","userIp":"","collectCode":"C0001477762201805141918024171","id":48,"applyCode":"0000000002201805141919271922","goodsCode":"1049802490","goodsName":"T49邮政运输","status":2}]
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

        public static class PageInfoBean {
            /**
             * pageNum : 1
             * pageSize : 10
             * totalPage : 1
             * totalNum : 2
             * resultNum : 2
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

        public static class ListBean {
            /**
             * goodsImg : http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg
             * publishTime : 1526313600000
             * amount : 10
             * addTime : 1526380693000
             * oppAccount : 11
             * goodsId : 1374
             * userPhone : 1234444
             * upsideFee : 100.0
             * agencyId : 145
             * agencyCode : 0058
             * userId : 2
             * mechanismId : 0058
             * agencyName : 福利特寄卖平台
             * publishId : 107
             * applyNo : 1920cbb5-207c-429e-9067-b480d8bd896f
             * commodityCode : 1070020030
             * userIp : 192.168.1.16
             * collectCode : C0001477795201805151833119507
             * id : 77
             * applyCode : 0000000002201805151838131849
             * goodsCode : 1070020030
             * goodsName : 普31中国鸟（第一组）
             * status : 4
             */

            private String goodsImg;
            private long publishTime;
            private int amount;
            private long addTime;
            private String oppAccount;
            private int goodsId;
            private String userPhone;
            private double upsideFee;
            private int agencyId;
            private String agencyCode;
            private int userId;
            private String mechanismId;
            private String agencyName;
            private int publishId;
            private String applyNo;
            private String commodityCode;
            private String userIp;
            private String collectCode;
            private int id;
            private String applyCode;
            private String goodsCode;
            private String goodsName;
            private int status;

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public String getOppAccount() {
                return oppAccount;
            }

            public void setOppAccount(String oppAccount) {
                this.oppAccount = oppAccount;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getUserPhone() {
                return userPhone;
            }

            public void setUserPhone(String userPhone) {
                this.userPhone = userPhone;
            }

            public double getUpsideFee() {
                return upsideFee;
            }

            public void setUpsideFee(double upsideFee) {
                this.upsideFee = upsideFee;
            }

            public int getAgencyId() {
                return agencyId;
            }

            public void setAgencyId(int agencyId) {
                this.agencyId = agencyId;
            }

            public String getAgencyCode() {
                return agencyCode;
            }

            public void setAgencyCode(String agencyCode) {
                this.agencyCode = agencyCode;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getMechanismId() {
                return mechanismId;
            }

            public void setMechanismId(String mechanismId) {
                this.mechanismId = mechanismId;
            }

            public String getAgencyName() {
                return agencyName;
            }

            public void setAgencyName(String agencyName) {
                this.agencyName = agencyName;
            }

            public int getPublishId() {
                return publishId;
            }

            public void setPublishId(int publishId) {
                this.publishId = publishId;
            }

            public String getApplyNo() {
                return applyNo;
            }

            public void setApplyNo(String applyNo) {
                this.applyNo = applyNo;
            }

            public String getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(String commodityCode) {
                this.commodityCode = commodityCode;
            }

            public String getUserIp() {
                return userIp;
            }

            public void setUserIp(String userIp) {
                this.userIp = userIp;
            }

            public String getCollectCode() {
                return collectCode;
            }

            public void setCollectCode(String collectCode) {
                this.collectCode = collectCode;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getApplyCode() {
                return applyCode;
            }

            public void setApplyCode(String applyCode) {
                this.applyCode = applyCode;
            }

            public String getGoodsCode() {
                return goodsCode;
            }

            public void setGoodsCode(String goodsCode) {
                this.goodsCode = goodsCode;
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
