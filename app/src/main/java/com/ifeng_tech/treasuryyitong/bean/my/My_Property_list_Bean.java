package com.ifeng_tech.treasuryyitong.bean.my;

import java.util.List;

/**
 * Created by zzt on 2018/5/31.
 */

public class My_Property_list_Bean {

    /**
     * code : 2000
     * message : 操作成功
     * data : {"pageInfo":{"totalPages":1,"pageSize":5,"pageNum":1,"totalElements":3},"list":[{"amount":1.1,"addTime":1527730747000,"paymentPlatform":1,"outTradeNo":"P00000000502018053109390675461140","paymentRecordId":4,"updateTime":1527730747000,"paymentAccountInfo":"A10102939393","paymentState":5,"userId":2,"paymentType":1,"paymentDescription":"充值预存款1000元"},{"amount":1.1,"addTime":1527730787000,"paymentPlatform":2,"outTradeNo":"P00000000502018053109394736596276","paymentRecordId":5,"updateTime":1527730787000,"paymentAccountInfo":"A10102939393","paymentState":2,"userId":2,"paymentType":1,"paymentDescription":"微信充值1块1"},{"amount":1.2,"addTime":1527730804000,"paymentPlatform":1,"outTradeNo":"P00000000502018053109400441354730","paymentRecordId":6,"updateTime":1527730804000,"paymentAccountInfo":"A10102939393","paymentState":2,"userId":2,"paymentType":1,"paymentDescription":"支付宝充值1块2"}]}
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
         * pageInfo : {"totalPages":1,"pageSize":5,"pageNum":1,"totalElements":3}
         * list : [{"amount":1.1,"addTime":1527730747000,"paymentPlatform":1,"outTradeNo":"P00000000502018053109390675461140","paymentRecordId":4,"updateTime":1527730747000,"paymentAccountInfo":"A10102939393","paymentState":5,"userId":2,"paymentType":1,"paymentDescription":"充值预存款1000元"},{"amount":1.1,"addTime":1527730787000,"paymentPlatform":2,"outTradeNo":"P00000000502018053109394736596276","paymentRecordId":5,"updateTime":1527730787000,"paymentAccountInfo":"A10102939393","paymentState":2,"userId":2,"paymentType":1,"paymentDescription":"微信充值1块1"},{"amount":1.2,"addTime":1527730804000,"paymentPlatform":1,"outTradeNo":"P00000000502018053109400441354730","paymentRecordId":6,"updateTime":1527730804000,"paymentAccountInfo":"A10102939393","paymentState":2,"userId":2,"paymentType":1,"paymentDescription":"支付宝充值1块2"}]
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
             * totalPages : 1
             * pageSize : 5
             * pageNum : 1
             * totalElements : 3
             */

            private int totalPages;
            private int pageSize;
            private int pageNum;
            private int totalElements;

            public int getTotalPages() {
                return totalPages;
            }

            public void setTotalPages(int totalPages) {
                this.totalPages = totalPages;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getTotalElements() {
                return totalElements;
            }

            public void setTotalElements(int totalElements) {
                this.totalElements = totalElements;
            }
        }

        public static class ListBean {
            /**
             * amount : 1.1
             * addTime : 1527730747000
             * paymentPlatform : 1
             * outTradeNo : P00000000502018053109390675461140
             * paymentRecordId : 4
             * updateTime : 1527730747000
             * paymentAccountInfo : A10102939393
             * paymentState : 5
             * userId : 2
             * paymentType : 1
             * paymentDescription : 充值预存款1000元
             */

            private double amount;
            private long addTime;
            private int paymentPlatform;
            private String outTradeNo;
            private int paymentRecordId;
            private long updateTime;
            private String paymentAccountInfo;
            private int paymentState;
            private int userId;
            private int paymentType;
            private String paymentDescription;

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public int getPaymentPlatform() {
                return paymentPlatform;
            }

            public void setPaymentPlatform(int paymentPlatform) {
                this.paymentPlatform = paymentPlatform;
            }

            public String getOutTradeNo() {
                return outTradeNo;
            }

            public void setOutTradeNo(String outTradeNo) {
                this.outTradeNo = outTradeNo;
            }

            public int getPaymentRecordId() {
                return paymentRecordId;
            }

            public void setPaymentRecordId(int paymentRecordId) {
                this.paymentRecordId = paymentRecordId;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getPaymentAccountInfo() {
                return paymentAccountInfo;
            }

            public void setPaymentAccountInfo(String paymentAccountInfo) {
                this.paymentAccountInfo = paymentAccountInfo;
            }

            public int getPaymentState() {
                return paymentState;
            }

            public void setPaymentState(int paymentState) {
                this.paymentState = paymentState;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getPaymentType() {
                return paymentType;
            }

            public void setPaymentType(int paymentType) {
                this.paymentType = paymentType;
            }

            public String getPaymentDescription() {
                return paymentDescription;
            }

            public void setPaymentDescription(String paymentDescription) {
                this.paymentDescription = paymentDescription;
            }
        }
    }
}
