package com.ifeng_tech.treasuryyitong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzt on 2018/5/23.
 */

public class Pick_Up_Goods_Bean implements Serializable{


    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"pageNum":1,"pageSize":5,"totalPage":2,"totalNum":9,"resultNum":5},"list":[{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":11,"deliveryQty":11,"addTime":1527063113000,"deliveryTime":1527436800000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":null,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":0,"delPassword":"c4ca4238a0b923820dcc509a6f75849b","billStage":0,"deleteStatus":false,"billId":"od00001231611532001111q30p2","commodityCode":"1070020030","id":173,"endTime":null,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":9,"deliveryQty":9,"addTime":1527474523000,"deliveryTime":1527523200000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":1527474822000,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":6.5,"delPassword":"e10adc3949ba59abbe56e057f20f883e","billStage":3,"deleteStatus":false,"billId":"od00001231028429231111p79p0","commodityCode":"1070020030","id":192,"endTime":1527474822000,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":9,"deliveryQty":9,"addTime":1527474531000,"deliveryTime":1527523200000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":null,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":6.5,"delPassword":"e10adc3949ba59abbe56e057f20f883e","billStage":0,"deleteStatus":false,"billId":"od000012310285146411118kg50","commodityCode":"1070020030","id":193,"endTime":null,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":9,"deliveryQty":9,"addTime":1527474540000,"deliveryTime":1527523200000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":1527675572000,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":6.5,"delPassword":"e10adc3949ba59abbe56e057f20f883e","billStage":5,"deleteStatus":false,"billId":"od00001231028596111111a7h54","commodityCode":"1070020030","id":194,"endTime":null,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":2,"deliveryQty":2,"addTime":1527496514000,"deliveryTime":1527523200000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":null,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":6.5,"delPassword":"c4ca4238a0b923820dcc509a6f75849b","billStage":0,"deleteStatus":false,"billId":"od00001231635141701111vx611","commodityCode":"1070020030","id":199,"endTime":null,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"}]}
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
         * pageInfo : {"pageNum":1,"pageSize":5,"totalPage":2,"totalNum":9,"resultNum":5}
         * list : [{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":11,"deliveryQty":11,"addTime":1527063113000,"deliveryTime":1527436800000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":null,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":0,"delPassword":"c4ca4238a0b923820dcc509a6f75849b","billStage":0,"deleteStatus":false,"billId":"od00001231611532001111q30p2","commodityCode":"1070020030","id":173,"endTime":null,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":9,"deliveryQty":9,"addTime":1527474523000,"deliveryTime":1527523200000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":1527474822000,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":6.5,"delPassword":"e10adc3949ba59abbe56e057f20f883e","billStage":3,"deleteStatus":false,"billId":"od00001231028429231111p79p0","commodityCode":"1070020030","id":192,"endTime":1527474822000,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":9,"deliveryQty":9,"addTime":1527474531000,"deliveryTime":1527523200000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":null,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":6.5,"delPassword":"e10adc3949ba59abbe56e057f20f883e","billStage":0,"deleteStatus":false,"billId":"od000012310285146411118kg50","commodityCode":"1070020030","id":193,"endTime":null,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":9,"deliveryQty":9,"addTime":1527474540000,"deliveryTime":1527523200000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":1527675572000,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":6.5,"delPassword":"e10adc3949ba59abbe56e057f20f883e","billStage":5,"deleteStatus":false,"billId":"od00001231028596111111a7h54","commodityCode":"1070020030","id":194,"endTime":null,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","depotName":"测试仓库","quantity":2,"deliveryQty":2,"addTime":1527496514000,"deliveryTime":1527523200000,"outQuantity":null,"goodsId":1374,"accountType":null,"depotId":26,"updateTime":null,"commodityId":1374,"accountId":123,"outBillId":null,"deliveryFee":6.5,"delPassword":"c4ca4238a0b923820dcc509a6f75849b","billStage":0,"deleteStatus":false,"billId":"od00001231635141701111vx611","commodityCode":"1070020030","id":199,"endTime":null,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）"}]
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
             * pageSize : 5
             * totalPage : 2
             * totalNum : 9
             * resultNum : 5
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
             * goodsImg : http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg
             * depotName : 测试仓库
             * quantity : 11
             * deliveryQty : 11
             * addTime : 1527063113000
             * deliveryTime : 1527436800000
             * outQuantity : null
             * goodsId : 1374
             * accountType : null
             * depotId : 26
             * updateTime : null
             * commodityId : 1374
             * accountId : 123
             * outBillId : null
             * deliveryFee : 0
             * delPassword : c4ca4238a0b923820dcc509a6f75849b
             * billStage : 0
             * deleteStatus : false
             * billId : od00001231611532001111q30p2
             * commodityCode : 1070020030
             * id : 173
             * endTime : null
             * goodsCode : 1070020030
             * goodsName : 普31中国鸟（第一组）
             */

            private String goodsImg;
            private String depotName;
            private int quantity;
            private int deliveryQty;
            private long addTime;
            private long deliveryTime;
            private Object outQuantity;
            private int goodsId;
            private Object accountType;
            private int depotId;
            private Object updateTime;
            private int commodityId;
            private int accountId;
            private int outBillId;
            private double deliveryFee;
            private String delPassword;
            private int billStage;
            private boolean deleteStatus;
            private String billId;
            private String commodityCode;
            private int id;
            private Object endTime;
            private String goodsCode;
            private String goodsName;

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public String getDepotName() {
                return depotName;
            }

            public void setDepotName(String depotName) {
                this.depotName = depotName;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getDeliveryQty() {
                return deliveryQty;
            }

            public void setDeliveryQty(int deliveryQty) {
                this.deliveryQty = deliveryQty;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public long getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(long deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public Object getOutQuantity() {
                return outQuantity;
            }

            public void setOutQuantity(Object outQuantity) {
                this.outQuantity = outQuantity;
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

            public int getDepotId() {
                return depotId;
            }

            public void setDepotId(int depotId) {
                this.depotId = depotId;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public int getAccountId() {
                return accountId;
            }

            public void setAccountId(int accountId) {
                this.accountId = accountId;
            }

            public int getOutBillId() {
                return outBillId;
            }

            public void setOutBillId(int outBillId) {
                this.outBillId = outBillId;
            }

            public double getDeliveryFee() {
                return deliveryFee;
            }

            public void setDeliveryFee(double deliveryFee) {
                this.deliveryFee = deliveryFee;
            }

            public String getDelPassword() {
                return delPassword;
            }

            public void setDelPassword(String delPassword) {
                this.delPassword = delPassword;
            }

            public int getBillStage() {
                return billStage;
            }

            public void setBillStage(int billStage) {
                this.billStage = billStage;
            }

            public boolean isDeleteStatus() {
                return deleteStatus;
            }

            public void setDeleteStatus(boolean deleteStatus) {
                this.deleteStatus = deleteStatus;
            }

            public String getBillId() {
                return billId;
            }

            public void setBillId(String billId) {
                this.billId = billId;
            }

            public String getCommodityCode() {
                return commodityCode;
            }

            public void setCommodityCode(String commodityCode) {
                this.commodityCode = commodityCode;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }
        }
    }
}
