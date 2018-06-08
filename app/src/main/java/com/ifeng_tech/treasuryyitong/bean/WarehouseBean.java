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
     * data : {"deliveryFee":5.2,"pageInfo":{"pageNum":1,"pageSize":4,"totalPage":1,"totalNum":4,"resultNum":4},"list":[{"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg","canReWarehousing":0,"clearDate":null,"addTime":1527498162000,"lockQty":0,"holdQty":148,"goodsId":839,"accountType":null,"holdFunds":0,"commodityId":839,"userId":123,"userCode":"000000000123","frozenQty":50,"id":93,"availableQty":98,"goodsCode":"3010070030","goodsName":"2007-2《第六届亚洲冬季运动会》纪念邮票","evenPrice":0},{"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070040.jpg","canReWarehousing":0,"clearDate":null,"addTime":1527498168000,"lockQty":0,"holdQty":192,"goodsId":840,"accountType":null,"holdFunds":0,"commodityId":840,"userId":123,"userCode":"000000000123","frozenQty":90,"id":94,"availableQty":102,"goodsCode":"3010070040","goodsName":"2007-3《石湾陶瓷》特种邮票","evenPrice":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","canReWarehousing":0,"clearDate":null,"addTime":1527677372000,"lockQty":0,"holdQty":10,"goodsId":1374,"accountType":null,"holdFunds":0,"commodityId":1374,"userId":123,"userCode":"000000000123","frozenQty":0,"id":114,"availableQty":10,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）","evenPrice":0},{"goodsImg":null,"canReWarehousing":0,"clearDate":null,"addTime":1527677562000,"lockQty":0,"holdQty":70,"goodsId":1419,"accountType":null,"holdFunds":0,"commodityId":1419,"userId":123,"userCode":"000000000123","frozenQty":0,"id":115,"availableQty":70,"goodsCode":"050701","goodsName":"测试商品0507","evenPrice":0}]}
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
         * deliveryFee : 5.2
         * pageInfo : {"pageNum":1,"pageSize":4,"totalPage":1,"totalNum":4,"resultNum":4}
         * list : [{"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg","canReWarehousing":0,"clearDate":null,"addTime":1527498162000,"lockQty":0,"holdQty":148,"goodsId":839,"accountType":null,"holdFunds":0,"commodityId":839,"userId":123,"userCode":"000000000123","frozenQty":50,"id":93,"availableQty":98,"goodsCode":"3010070030","goodsName":"2007-2《第六届亚洲冬季运动会》纪念邮票","evenPrice":0},{"goodsImg":"http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070040.jpg","canReWarehousing":0,"clearDate":null,"addTime":1527498168000,"lockQty":0,"holdQty":192,"goodsId":840,"accountType":null,"holdFunds":0,"commodityId":840,"userId":123,"userCode":"000000000123","frozenQty":90,"id":94,"availableQty":102,"goodsCode":"3010070040","goodsName":"2007-3《石湾陶瓷》特种邮票","evenPrice":0},{"goodsImg":"http://flt-picture.oss-cn-beijing.aliyuncs.com/postage/1070020030-1.jpg","canReWarehousing":0,"clearDate":null,"addTime":1527677372000,"lockQty":0,"holdQty":10,"goodsId":1374,"accountType":null,"holdFunds":0,"commodityId":1374,"userId":123,"userCode":"000000000123","frozenQty":0,"id":114,"availableQty":10,"goodsCode":"1070020030","goodsName":"普31中国鸟（第一组）","evenPrice":0},{"goodsImg":null,"canReWarehousing":0,"clearDate":null,"addTime":1527677562000,"lockQty":0,"holdQty":70,"goodsId":1419,"accountType":null,"holdFunds":0,"commodityId":1419,"userId":123,"userCode":"000000000123","frozenQty":0,"id":115,"availableQty":70,"goodsCode":"050701","goodsName":"测试商品0507","evenPrice":0}]
         */

        private double deliveryFee;
        private PageInfoBean pageInfo;
        private List<ListBean> list;

        public double getDeliveryFee() {
            return deliveryFee;
        }

        public void setDeliveryFee(double deliveryFee) {
            this.deliveryFee = deliveryFee;
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

        public static class PageInfoBean implements Serializable{
            /**
             * pageNum : 1
             * pageSize : 4
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
             * goodsImg : http://ifeng-shop.oss-cn-beijing.aliyuncs.com/upload/commodityPicture/3010070030.jpg
             * canReWarehousing : 0
             * clearDate : null
             * addTime : 1527498162000
             * lockQty : 0
             * holdQty : 148
             * goodsId : 839
             * accountType : null
             * holdFunds : 0
             * commodityId : 839
             * userId : 123
             * userCode : 000000000123
             * frozenQty : 50
             * id : 93
             * availableQty : 98
             * goodsCode : 3010070030
             * goodsName : 2007-2《第六届亚洲冬季运动会》纪念邮票
             * evenPrice : 0
             */

            boolean flag=false;

            private String goodsImg;
            private int canReWarehousing;
            private Object clearDate;
            private long addTime;
            private int lockQty;
            private int holdQty;
            private int goodsId;
            private Object accountType;
            private int holdFunds;
            private int commodityId;
            private int userId;
            private String userCode;
            private int frozenQty;  // 冻结
            private int id;
            private int availableQty;  // 可用
            private String goodsCode;
            private String goodsName;
            private int evenPrice;

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

            public boolean isFlag() {

                return flag;
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
