package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzt on 2018/5/10.
 *
 *  仓库
 */

public class WarehouseBean implements Serializable{

    /**
     * code : 2000
     * message : 操作成功
     * data : {"MaxTrasferableAmountAndFee":{"deliveryFee":10,"commonFeeRate":0.55,"TransferFee":6.1,"availableQty":"10"},"pageInfo":{"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":3,"resultNum":3},"list":[{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980005.jpg","canReWarehousing":0,"clearDate":null,"addTime":1528341035000,"lockQty":0,"holdQty":10,"isTransfer":1,"goodsId":1434,"accountType":null,"holdFunds":0,"commodityId":1434,"userId":123,"userCode":"000000000123","frozenQty":0,"id":141,"availableQty":10,"goodsCode":"1980005","goodsName":"1980年第四套人民币伍元","evenPrice":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980001.jpg","canReWarehousing":0,"clearDate":null,"addTime":1528341039000,"lockQty":0,"holdQty":10,"isTransfer":1,"goodsId":1433,"accountType":null,"holdFunds":0,"commodityId":1433,"userId":123,"userCode":"000000000123","frozenQty":0,"id":142,"availableQty":10,"goodsCode":"1980001","goodsName":"1980年第四套人民币一元","evenPrice":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980002.jpg","canReWarehousing":0,"clearDate":null,"addTime":1528341042000,"lockQty":0,"holdQty":10,"isTransfer":1,"goodsId":1432,"accountType":null,"holdFunds":0,"commodityId":1432,"userId":123,"userCode":"000000000123","frozenQty":0,"id":143,"availableQty":10,"goodsCode":"1980002","goodsName":"1980年第四套人民币两角","evenPrice":0}]}
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
         * MaxTrasferableAmountAndFee : {"deliveryFee":10,"commonFeeRate":0.55,"TransferFee":6.1,"availableQty":"10"}
         * pageInfo : {"pageNum":1,"pageSize":10,"totalPage":1,"totalNum":3,"resultNum":3}
         * list : [{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980005.jpg","canReWarehousing":0,"clearDate":null,"addTime":1528341035000,"lockQty":0,"holdQty":10,"isTransfer":1,"goodsId":1434,"accountType":null,"holdFunds":0,"commodityId":1434,"userId":123,"userCode":"000000000123","frozenQty":0,"id":141,"availableQty":10,"goodsCode":"1980005","goodsName":"1980年第四套人民币伍元","evenPrice":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980001.jpg","canReWarehousing":0,"clearDate":null,"addTime":1528341039000,"lockQty":0,"holdQty":10,"isTransfer":1,"goodsId":1433,"accountType":null,"holdFunds":0,"commodityId":1433,"userId":123,"userCode":"000000000123","frozenQty":0,"id":142,"availableQty":10,"goodsCode":"1980001","goodsName":"1980年第四套人民币一元","evenPrice":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980002.jpg","canReWarehousing":0,"clearDate":null,"addTime":1528341042000,"lockQty":0,"holdQty":10,"isTransfer":1,"goodsId":1432,"accountType":null,"holdFunds":0,"commodityId":1432,"userId":123,"userCode":"000000000123","frozenQty":0,"id":143,"availableQty":10,"goodsCode":"1980002","goodsName":"1980年第四套人民币两角","evenPrice":0}]
         */

        private MaxTrasferableAmountAndFeeBean MaxTrasferableAmountAndFee;
        private PageInfoBean pageInfo;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class MaxTrasferableAmountAndFeeBean implements Serializable{
            /**
             * deliveryFee : 10
             * commonFeeRate : 0.55
             * TransferFee : 6.1
             * availableQty : 10
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

        public static class PageInfoBean implements Serializable{
            /**
             * pageNum : 1
             * pageSize : 10
             * totalPage : 1
             * totalNum : 3
             * resultNum : 3
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
             * goodsImg : http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1980005.jpg
             * canReWarehousing : 0
             * clearDate : null
             * addTime : 1528341035000
             * lockQty : 0
             * holdQty : 10
             * isTransfer : 1
             * goodsId : 1434
             * accountType : null
             * holdFunds : 0
             * commodityId : 1434
             * userId : 123
             * userCode : 000000000123
             * frozenQty : 0
             * id : 141
             * availableQty : 10
             * goodsCode : 1980005
             * goodsName : 1980年第四套人民币伍元
             * evenPrice : 0
             */

            boolean flag=false;
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
            private int frozenQty;
            private int id;
            private int availableQty;
            private String goodsCode;
            private String goodsName;
            private int evenPrice;

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

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

            public int getEvenPrice() {
                return evenPrice;
            }

            public void setEvenPrice(int evenPrice) {
                this.evenPrice = evenPrice;
            }
        }
    }
}
