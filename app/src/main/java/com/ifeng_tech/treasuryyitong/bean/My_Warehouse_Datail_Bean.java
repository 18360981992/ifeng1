package com.ifeng_tech.treasuryyitong.bean;

import java.util.List;

/**
 * Created by zzt on 2018/6/20.
 */

public class My_Warehouse_Datail_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"MaxTrasferableAmountAndFee":{"deliveryFee":10,"commonFeeRate":0.55,"TransferFee":6.1,"availableQty":"120"},"goodsCountForStorageFeePayment":[{"holdQty":22,"commodityId":1432,"userId":2}],"pageInfo":{"pageNum":1,"pageSize":1,"totalPage":1,"totalNum":1,"resultNum":1},"list":[{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980002.jpg","canReWarehousing":0,"clearDate":null,"addTime":1529463958000,"lockQty":0,"holdQty":122,"isTransfer":1,"goodsId":1432,"accountType":null,"holdFunds":0,"commodityId":1432,"userId":2,"userCode":"000000000002","appraisalFeeRate":0.01,"guidingPrice":6,"frozenQty":2,"id":5,"availableQty":120,"goodsCode":"1980002","goodsName":"1980年第四套人民币两角","evenPrice":0,"storageFeeRate":0.01}]}
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
         * MaxTrasferableAmountAndFee : {"deliveryFee":10,"commonFeeRate":0.55,"TransferFee":6.1,"availableQty":"120"}
         * goodsCountForStorageFeePayment : [{"holdQty":22,"commodityId":1432,"userId":2}]
         * pageInfo : {"pageNum":1,"pageSize":1,"totalPage":1,"totalNum":1,"resultNum":1}
         * list : [{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980002.jpg","canReWarehousing":0,"clearDate":null,"addTime":1529463958000,"lockQty":0,"holdQty":122,"isTransfer":1,"goodsId":1432,"accountType":null,"holdFunds":0,"commodityId":1432,"userId":2,"userCode":"000000000002","appraisalFeeRate":0.01,"guidingPrice":6,"frozenQty":2,"id":5,"availableQty":120,"goodsCode":"1980002","goodsName":"1980年第四套人民币两角","evenPrice":0,"storageFeeRate":0.01}]
         */

        private MaxTrasferableAmountAndFeeBean MaxTrasferableAmountAndFee;
        private PageInfoBean pageInfo;
        private List<GoodsCountForStorageFeePaymentBean> goodsCountForStorageFeePayment;
        private List<ListBean> list;

        public MaxTrasferableAmountAndFeeBean getMaxTrasferableAmountAndFee() {
            return MaxTrasferableAmountAndFee;
        }

        public void setMaxTrasferableAmountAndFee(MaxTrasferableAmountAndFeeBean MaxTrasferableAmountAndFee) {
            this.MaxTrasferableAmountAndFee = MaxTrasferableAmountAndFee;
        }

        public PageInfoBean getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoBean pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<GoodsCountForStorageFeePaymentBean> getGoodsCountForStorageFeePayment() {
            return goodsCountForStorageFeePayment;
        }

        public void setGoodsCountForStorageFeePayment(List<GoodsCountForStorageFeePaymentBean> goodsCountForStorageFeePayment) {
            this.goodsCountForStorageFeePayment = goodsCountForStorageFeePayment;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class MaxTrasferableAmountAndFeeBean {
            /**
             * deliveryFee : 10
             * commonFeeRate : 0.55
             * TransferFee : 6.1
             * availableQty : 120
             */

            private double deliveryFee;
            private double commonFeeRate;
            private double TransferFee;
            private String availableQty;

            public double getDeliveryFee() {
                return deliveryFee;
            }

            public void setDeliveryFee(double deliveryFee) {
                this.deliveryFee = deliveryFee;
            }

            public double getCommonFeeRate() {
                return commonFeeRate;
            }

            public void setCommonFeeRate(double commonFeeRate) {
                this.commonFeeRate = commonFeeRate;
            }

            public double getTransferFee() {
                return TransferFee;
            }

            public void setTransferFee(double TransferFee) {
                this.TransferFee = TransferFee;
            }

            public String getAvailableQty() {
                return availableQty;
            }

            public void setAvailableQty(String availableQty) {
                this.availableQty = availableQty;
            }
        }

        public static class PageInfoBean {
            /**
             * pageNum : 1
             * pageSize : 1
             * totalPage : 1
             * totalNum : 1
             * resultNum : 1
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

        public static class GoodsCountForStorageFeePaymentBean {
            /**
             * holdQty : 22
             * commodityId : 1432
             * userId : 2
             */

            private int holdQty;
            private int commodityId;
            private int userId;

            public int getHoldQty() {
                return holdQty;
            }

            public void setHoldQty(int holdQty) {
                this.holdQty = holdQty;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }

        public static class ListBean {
            /**
             * goodsImg : http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980002.jpg
             * canReWarehousing : 0
             * clearDate : null
             * addTime : 1529463958000
             * lockQty : 0
             * holdQty : 122
             * isTransfer : 1
             * goodsId : 1432
             * accountType : null
             * holdFunds : 0
             * commodityId : 1432
             * userId : 2
             * userCode : 000000000002
             * appraisalFeeRate : 0.01
             * guidingPrice : 6
             * frozenQty : 2
             * id : 5
             * availableQty : 120
             * goodsCode : 1980002
             * goodsName : 1980年第四套人民币两角
             * evenPrice : 0
             * storageFeeRate : 0.01
             */

            private String goodsImg;
            private int canReWarehousing;
            private Object clearDate;
            private long addTime;
            private int lockQty;
            private int holdQty;
            private int isTransfer;
            private int goodsId;
            private Object accountType;
            private int holdFunds;
            private int commodityId;
            private int userId;
            private String userCode;
            private double appraisalFeeRate;
            private double guidingPrice;
            private int frozenQty;
            private int id;
            private int availableQty;
            private String goodsCode;
            private String goodsName;
            private double evenPrice;
            private double storageFeeRate;

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public int getCanReWarehousing() {
                return canReWarehousing;
            }

            public void setCanReWarehousing(int canReWarehousing) {
                this.canReWarehousing = canReWarehousing;
            }

            public Object getClearDate() {
                return clearDate;
            }

            public void setClearDate(Object clearDate) {
                this.clearDate = clearDate;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public int getLockQty() {
                return lockQty;
            }

            public void setLockQty(int lockQty) {
                this.lockQty = lockQty;
            }

            public int getHoldQty() {
                return holdQty;
            }

            public void setHoldQty(int holdQty) {
                this.holdQty = holdQty;
            }

            public int getIsTransfer() {
                return isTransfer;
            }

            public void setIsTransfer(int isTransfer) {
                this.isTransfer = isTransfer;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public Object getAccountType() {
                return accountType;
            }

            public void setAccountType(Object accountType) {
                this.accountType = accountType;
            }

            public int getHoldFunds() {
                return holdFunds;
            }

            public void setHoldFunds(int holdFunds) {
                this.holdFunds = holdFunds;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
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

            public double getAppraisalFeeRate() {
                return appraisalFeeRate;
            }

            public void setAppraisalFeeRate(double appraisalFeeRate) {
                this.appraisalFeeRate = appraisalFeeRate;
            }

            public double getGuidingPrice() {
                return guidingPrice;
            }

            public void setGuidingPrice(double guidingPrice) {
                this.guidingPrice = guidingPrice;
            }

            public int getFrozenQty() {
                return frozenQty;
            }

            public void setFrozenQty(int frozenQty) {
                this.frozenQty = frozenQty;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAvailableQty() {
                return availableQty;
            }

            public void setAvailableQty(int availableQty) {
                this.availableQty = availableQty;
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

            public double getEvenPrice() {
                return evenPrice;
            }

            public void setEvenPrice(double evenPrice) {
                this.evenPrice = evenPrice;
            }

            public double getStorageFeeRate() {
                return storageFeeRate;
            }

            public void setStorageFeeRate(double storageFeeRate) {
                this.storageFeeRate = storageFeeRate;
            }
        }
    }
}
